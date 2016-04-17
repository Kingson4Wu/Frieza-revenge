动态取得实现同一个接口的不同类型的具体实例（使用注解和context.getBeansOfType）

package com.abc.kxw.Abc.reload.service.impl;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;
import com.abc.kxw.Abc.reload.annotation.Reloader;
import com.abc.kxw.Abc.reload.domain.Warehouse;
import com.abc.kxw.Abc.reload.service.IAbcCacheReloadService;
import com.abc.kxw.Abc.reload.service.IAbcDBReloader;
import com.abc.kxw.cache.key.SellingDimension;
import com.abc.kxw.cache.service.BaseCacheService;
import com.abc.kxw.cache.service.MemcachedService;
import com.abc.kxw.channel.service.ChannelInfoService;
import com.abc.kxw.warehouse.service.IWarehouseService;
import com.abc.kxw.api.Abc.domain.AbcBean;

@Service
public class AbcCacheReloadServiceImpl implements IAbcCacheReloadService, ApplicationContextAware, InitializingBean {
	private ApplicationContext context;

	private final Logger logger = LoggerFactory.getLogger(AbcCacheReloadServiceImpl.class);

	private Map<Integer, IAbcDBReloader> reloaderMap = new HashMap<>();

	private ExecutorService exec = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() * 2);

	@Autowired
	private MemcachedService cacheService;

	@Autowired
	private ChannelInfoService channelInfoService;

	@Autowired
	private IWarehouseService warehouseService;

	@Override
	public boolean reloadByType(int type) {
		final IAbcDBReloader reloader = reloaderMap.get(type);
		if(null == reloader){
			logger.warn("Unsupported type:" + type);
			return false;
		}

		logger.info("Reload Abc cache for type:" + type + " started!");

		List<Integer> list = getChannelsByType(type);
		StringBuffer loadedChannels = new StringBuffer();
		if(list.size() > 0){
			for(int i=0; i<list.size()-1; i++){
				loadedChannels.append(list.get(i)).append(",");
			}
			loadedChannels.append(list.get(list.size() - 1));
		}
		logger.info("Reloading channels are:{}", loadedChannels.toString());

//		final CountDownLatch latch = new CountDownLatch(list.size() * Warehouse.values.length);
		final CountDownLatch latch = new CountDownLatch(list.size() * warehouseService.getAll().size());
		for(final Integer channel : list){
//			for(final String warehouse : Warehouse.values){
			for(final String warehouse : warehouseService.getAll()){
				exec.submit(new Runnable(){
					@Override
					public void run() {
						List<AbcBean> list = reloader.reloadFromDB(warehouse, channel);
						String key = new SellingDimension(warehouse, channel).getKey();
						cacheService.set(key, BaseCacheService.EXPIRY_NEVER, list);
						latch.countDown();
					}
				});
			}
		}

		try {
			latch.await(5, TimeUnit.MINUTES);
		} catch (InterruptedException e) {
			logger.warn("Interuppted while waiting completion of all reload tasks, maybe some of the reloading task not finished!");
			return false;
		}
		logger.info("Reload Abc cache for type:" + type + " finished!");

		return true;
	}

	private List<Integer> getChannelsByType(int type){
		List<Integer> list = new LinkedList<>();
		if(type != -1){
			list.add(type);
			return list;
		}
		else{
			Set<Integer> allChannelId = channelInfoService.getChannelInfoMap().keySet();
			Set<Integer> excludedChannelId = reloaderMap.keySet();
			for(Integer id : allChannelId){
				if(!excludedChannelId.contains(id)){
					list.add(id);
				}
			}
		}
		return list;
	}

	@Override
	public boolean reloadByChannel(final int channel) {
		final IAbcDBReloader reloader = reloaderMap.get(channel) != null ? reloaderMap.get(channel) : reloaderMap.get(-1);

//		final CountDownLatch latch = new CountDownLatch(Warehouse.values.length);
		final CountDownLatch latch = new CountDownLatch(warehouseService.getAll().size());
//		for(final String warehouse : Warehouse.values){
		for(final String warehouse : warehouseService.getAll()){
			exec.submit(new Runnable(){
				@Override
				public void run() {
					List<AbcBean> list = reloader.reloadFromDB(warehouse, channel);
					String key = new SellingDimension(warehouse, channel).getKey();
					cacheService.set(key, BaseCacheService.EXPIRY_FOUR_HOURS, list);
					latch.countDown();
				}
			});
		}

		try {
			latch.await(5, TimeUnit.MINUTES);
		} catch (InterruptedException e) {
			logger.warn("Interuppted while waiting completion of all reload tasks, maybe some of the reloading task not finished!");
			return false;
		}

		return true;

	}


	@Override
	public boolean reloadAll() {
		if(null == reloaderMap || reloaderMap.isEmpty()){
			logger.warn("Reloader map is empty, something must be wrong in the initialization of this map.");
			return false;
		}
		logger.info("Reloading all started!");
		long start = System.currentTimeMillis();
		for(Integer type : reloaderMap.keySet()){
			this.reloadByType(type);
		}
		logger.info("Reloading all finished, it costs {} milliseconds.", (System.currentTimeMillis() - start));
		return true;
	}
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.context = applicationContext;
	}
	/* (non-Javadoc)
	 * @see org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
	 */
	@Override
	public void afterPropertiesSet() throws Exception {
//		Map<String, Object> reloaders = context.getBeansWithAnnotation(Reloader.class);
		Map<String, IAbcDBReloader> reloaders = context.getBeansOfType(IAbcDBReloader.class);
		for(Object object : reloaders.values()){
			IAbcDBReloader reloader = (IAbcDBReloader)object;
			int id = reloader.getClass().getAnnotation(Reloader.class).id();
			reloaderMap.put(id, reloader);
		}
	}

}
在应用启动的时候就把集中不同类型的实例放到map中

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
/**
 * @author longtuan
 * @date 2014年8月12日
 * @description
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Inherited
public @interface Reloader {
	int id();
}
用注解实现在注入的时候设置id值

/**
 * @author longtuan
 * @date 2014年8月12日
 * @description
 *
 */
@Service
@Reloader(id = -1)
public class SellingAbcDBReloader implements IAbcDBReloader {
	@Autowired
	private AbcReloadMapper mapper;

	@Override
	public List<AbcBean> reloadFromDB(String warehouse, int channel) {
		long now = System.currentTimeMillis() / 1000;
		Map<String, Object> map = new HashMap<>();
		map.put("warehouse", warehouse);
		map.put("channelId", channel);
		map.put("now", now);
		return mapper.getSellingAbc(map);
	}
}