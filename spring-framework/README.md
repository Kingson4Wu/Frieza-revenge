#### spring
+ Spring JdbcTemplate:<http://peirenlei.iteye.com/blog/353170>
+ 使用spring的@Async异步执行方法 :<http://www.cnblogs.com/yangzhilong/p/3725071.html>
+ Spring4新特性——集成Bean Validation 1.1(JSR-349)到SpringMVC:<http://jinnianshilongnian.iteye.com/blog/1990081>
+ Spring4新特性——Web开发的增强:<http://jinnianshilongnian.iteye.com/blog/1989381>

+  Spring的InitializingBean和init-method
<http://www.blogjava.net/jjwwhmm/archive/2008/04/14/192800.html>
Spring在设置完一个bean所有的属性后，会检查bean是否实现了InitializingBean接口，
如果实现就调用bean的afterPropertiesSet方法。另外,如果bean是单例的,
则afterPropertiesSet方法只会被调用一次;否则每次创建bean时afterPropertiesSet方法都会被重新调用.
Spring虽然可以通过InitializingBean完成一个bean初始化后对这个bean的回调，
但是这种方式要求bean实现 InitializingBean接口。一但bean实现了InitializingBean接口，那么这个bean的代码就和Spring耦合到一起了。通常情况下不建议直接实现InitializingBean，而是用Spring提供的init-method的功能来执行一个bean 子定义的初始化方法,这可以在一个bean的配置文件中通过init-method声明:
	<bean id="testBean" class="TestClass" init-method="initialize"/>
spring要求这个init-method方法是一个无参数的方法
如果一个bean同时实现了这两种方式的初始化配置,则spring会先调用afterPropertiesSet方法,
然后通过反射调用init-method,任何一个方法出错都会导致spring创建bean失败.如果afterPropertiesSet方法调用失败,也不会再继续执行init-mehtod方法.
ps : InitializingBean的初始化依赖于spring框架，而其他两种（xml或annotation）不依赖。方便以后更换容器

+ 关于在spring  容器初始化 bean 和销毁前所做的操作定义方式
      第一种：通过@PostConstruct 和 @PreDestroy 方法 实现初始化和销毁bean之前进行的操作
      第二种是：通过 在xml中定义init-method 和  destory-method方法
      第三种是： 通过bean实现InitializingBean和 DisposableBean接口

+ 获取bean对象的注解
1. 若bean是annotation方式注入的
```java
Map<String, IabcDBReloader> reloaders = context.getBeansOfType(IabcDBReloader.class);
		for(Object object : reloaders.values()){
			IabcDBReloader reloader = (IabcDBReloader)object;
			int id = reloader.getClass().getAnnotation(Reloader.class).id();
	        reloaderMap.put(id, reloader);
	        }
```
2. 若bean是xml方式注入的
```java
Map<String, ICacheReloader> reloaders = context
				.getBeansOfType(ICacheReloader.class);
		for (Object object : reloaders.values()) {
			ICacheReloader reloader = (ICacheReloader) object;
			Class<?> clazz = reloader.getClass();
			if (reloader instanceof TargetClassAware) {
				TargetClassAware targetClassAware = (TargetClassAware) reloader;
				clazz = targetClassAware.getTargetClass();
			}
			int id = clazz.getAnnotation(CacheReloader.class).id();
			reloaderMap.put(id, reloader);
```

+ 当spring 容器初始化完成后执行某个方法
<http://www.cnblogs.com/rollenholt/p/3612440.html>
在做web项目开发中，尤其是企业级应用开发的时候，往往会在工程启动的时候做许多的前置检查。
　　比如检查是否使用了我们组禁止使用的Mysql的group_concat函数，如果使用了项目就不能启动，并指出哪个文件的xml文件使用了这个函数。
而在Spring的web项目中，我们可以介入Spring的启动过程。我们希望在Spring容器将所有的Bean都初始化完成之后，做一些操作，这个时候我们就可以实现一个接口：

```java
package com.yk.test.executor.processor
public class InstantiationTracingBeanPostProcessor implements ApplicationListener<ContextRefreshedEvent> {
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
      //需要执行的逻辑代码，当spring容器初始化完成后就会执行该方法。
 }
}
```
　　同时在Spring的配置文件中，添加注入：
```xml
<!--      当Spring容器启动完成后执行下面的这个Bean -->
    <bean class="com.yk.test.executor.processor.InstantiationTracingBeanPostProcessor"/>
```
但是这个时候，会存在一个问题，在web 项目中（spring mvc），系统会存在两个容器，一个是root application context ,另一个就是我们自己的 projectName-servlet  context（作为root application context的子容器）。
这种情况下，就会造成onApplicationEvent方法被执行两次。为了避免上面提到的问题，我们可以只在root application context初始化完成后调用逻辑代码，其他的容器的初始化完成，则不做任何处理，修改后代码
如下：
```java
@Override
  public void onApplicationEvent(ContextRefreshedEvent event) {
      if(event.getApplicationContext().getParent() == null){//root application context 没有parent，他就是老大.
           //需要执行的逻辑代码，当spring容器初始化完成后就会执行该方法。
      }
  }
```　　

+ 在 Spring Bean 內获得 HttpServletRequest
<<http://www.educity.cn/wenda/375127.html>>
在 Spring Bean 內取得 HttpServletRequest
　　使用 Java 去開發 Web 應用程式時，大部份時候都會使用到 HttpServletRequest 來做各項操作，例如拿取用戶的 IP 地址:
　　`String ip = request.getRemoteAddr();`
　　又例如要拿取用戶的 Session 物件:
　　`HTTPSession session = request.getSession();`
　　
有很多操作也是靠 HttpServletRequest 來做。
　　需要在 Spring bean 要取得 HttpServletRequest 物件時，如果每次都經由 HttpServlet 拿到 HttpServletRequest 物件後再傳入去 Spring bean，這樣的做法太煩人了，Spring Framework 提供了一個方法可以不用靠自己的 HttpServlet 傳入就可以取得 HttpServletRequest。
　　以下是程式碼:
```java
ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
HttpServletRequest request = attr.getRequest();
```
　　
在 web.xml 內亦要加上一個 listener 以防 HttpServletRequest 在其他地方使用時會發生 Thread 方面的問題:
```xml　
　<listener>　　
　　<listener-class>　　
　　org.springframeworntext.request.RequestContextListener　　
　　</listener-class>
　　</listener>
```　
使用 Spring Framework 3 的也可以使用 @Autowired 的方式令 Spring 自動注入 HttpServletRequest:
　　`@Autowiredprivate HttpServletRequest request;`

+ 用spring注解读取配置文件中作为初始值
```java
import org.springframework.beans.factory.annotation.Value;
@Value("${api}")
	public void setApi(String api) {
		this.api = api;
	}
```
+ Spring配置文件中用${XXX}读取属性值
```xml
<bean id="RequestEntity" class="com.abc.entity.RequestEntity">
		<property name="service" value="${abc.abc.soon.get.service}" />
		<property name="ver" value="${abc.abc.soon.get.ver}" />
		<property name="format" value="${abc.abc.soon.get.format}" />
</bean>
```


+ 在controller输出对象到页面，页面显示
`mv.addObject("channels", channelInfoService.getChannelInfoMap());`
```html
<c:forEach items="${channels}" var="channel">
		<span name="channelDecorator">
		<input id="channelId_${channel.key}" name="channel"
				type="checkbox" value="${channel.key}" style="margin-left:10px;">${channel.value.channelName}
		</span>
</c:forEach>
```

+ spring mvc json 直接把一个对象转换成json格式输出到jsp页面
<http://dwz.cn/2vKjaA>
这个很简单可以使用spring mvc自带的jackson
1、web工程lib中加入jackson所需jar包：jackson-core-asl-1.9.9.jar、jackson-mapper-asl-1.9.9.jar
2、在applicationContext.xml中加入jackson的配置
<!-- json转换器 -->
<bean id="jsonConverter"
 class="org.springframework.http.converter.json.MappingJacksonHttpMessageConverter">  <property name="supportedMediaTypes" value="application/json" />
</bean>
3、在你的action中直接使用注解的方式"@ResponseBody"自动将返回值转化成json格式
@Controller
@RequestMapping("task")
public class TaskControl {
     @RequestMapping("getUserById")
     @ResponseBody
     public List getUserById(Integer id) {
         return this.taskService.getUserById(id);
     }
}
4、jsp页面的js写法跟普通ajax格式一样
functon getUserById(id){
    $.getJSON("task/getUserById",{id:id},function(data){

    });
}

+ Spring实现任务调度
配置文件：
  <task:annotation-driven/>
```java
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import com.abc.kxw.abc.reload.service.IabcCacheReloadService;
import com.abc.kxw.abc.reload.service.IabcsDetailCacheReloadService;
@Service
public class AutoReloadabcsJob {
final Logger logger = LoggerFactory.getLogger(AutoReloadabcsJob.class);
	
	@Autowired
	private IabcCacheReloadService service;
	
	@Autowired
	private IabcsDetailCacheReloadService abcsDetailCacheReloadService;
	
	@Scheduled(cron="10 0 * * * ?")
	public void autoReloadabcs(){
		logger.info("AutoReloadabcsJob.autoReloadabcs execute started!");
		try{
			service.reloadAll();
		}catch(Exception e){
			logger.error("error occured:", e);
		}
		logger.info("AutoSychabcsJob.autoSychabcs execute successful!");
	}
	
	/**
	 * auto reload abc detail to memcache job
	 * execute time:everyday ,per hour(10 hour ), 12 minute,40 second, 18:12:40 etc
	 * 
	 */
	@Scheduled(cron="40 16 0-9,11-23 * * ?")
	public void autoReloadabcsDetail(){
		logger.info("AutoReloadabcsJob.autoReloadabcsDetail execute started!");
		try{
			abcsDetailCacheReloadService.reloadabcsByWarehouse();
		}catch(Exception e){
			logger.error("error occured:", e);
		}
		logger.info("AutoReloadabcsJob.autoReloadabcsDetail execute successful!");
	}
}
```
+ Spring自定义PropertyPlaceholderConfigurer读取properties文件
```xml
<bean id="propertyConfigurer" class="com.util.PropertyPlaceholder">
        <property name="locations">
            <value>classpath:sysconfig.properties</value>
        </property>
</bean>
```
```java
package com.util;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
/**
 * 自定义PropertyPlaceholderConfigurer返回properties内容
 * 
 * 
 */
public class PropertyPlaceholder extends PropertyPlaceholderConfigurer {
	private static Map<String, String> ctxPropertiesMap = new HashMap<String, String>();
	@Override
	protected void processProperties(
			ConfigurableListableBeanFactory beanFactoryToProcess,
			Properties props) throws BeansException {
		super.processProperties(beanFactoryToProcess, props);
		String keyStr = null;
		String value = null;
		for (Object key : props.keySet()) {
			keyStr = key.toString();
			value = props.getProperty(keyStr);
			ctxPropertiesMap.put(keyStr, value);
		}
	}
	public static Object getContextProperty(String name) {
		return ctxPropertiesMap.get(name);
	}
	public static Map<String, String> getContextPropertyMap() {
		return ctxPropertiesMap;
	}
}
```



#### skill
1. Autowired
```java
@Autowired
public void setInterceptors(List<ServiceInterceptor> interceptors){
this.interceptors = interceptors;
}
//自动注入所有实现了ServiceInterceptor的bean
```
2. FactoryBean:<http://blog.csdn.net/is_zhoufeng/article/details/38422549>

#### mybatis
+ spring,mybatis事务管理配置与@Transactional注解使用: <http://www.cnblogs.com/xusir/p/3650522.html>

#### websocket
详解 Spring Framework 4.0 M1 中的 WebSocket 支持: <http://www.iteye.com/news/27808-springframework-websocket>

#### dwr framework

### official website 
<http://docs.spring.io/spring/docs/4.1.0.BUILD-SNAPSHOT/spring-framework-reference/htmlsingle/>
<http://docs.spring.io/spring/docs/4.1.0.BUILD-SNAPSHOT/javadoc-api/>


