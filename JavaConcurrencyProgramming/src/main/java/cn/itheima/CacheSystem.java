package cn.itheima;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class CacheSystem
{
	/**
	 面试题：设计一个缓存系统
	 缓存系统：你要取数据，需调用我的public Object getData(String key)方法，
	 我要检查我内部有没有这个数据，如果有就直接返回，如果没有，就从数据库中查找这个数，
	 查到后将这个数据存入我内部的存储器中，下次再有人来要这个数据，我就直接返回这个数
	 不用再到数据库中找了。		你要取数据不要找数据库，来找我。
	 */
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		//测试缓存器
		final CacheSystem cache = new CacheSystem();

		for (int i=0; i<3; i++)
		{
			new Thread(new Runnable()
			{
				@Override
				public void run()
				{
					while (true)
					{
						int num = new Random().nextInt(10);
						String key = num + "";
						Object result = cache.get(key);
						System.out.println(Thread.currentThread().getName()+"正在查询："+key);
						try
						{
							Thread.sleep(num*1000);
						} catch (InterruptedException e)
						{
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						System.out.println(Thread.currentThread().getName()+"查询结果为："+key+"="+result);
					}
				}
			}).start();
		}
	}
	//内部存储器
	private Map<String, Object> cache = new HashMap<String, Object>();

	//获取数据方法
	//可能有多个线程来取数据，没有数据的话又会去数据库查询，需要互斥,加同步关键字即可
	public Object get(String key)
	{
		//先从内部存储器中查有没有要的数据,查到直接返回
		Object value = cache.get(key);
		//如果没有查到，就去数据库中找
		if (value==null)
		{
			//实际代码是从数据库中获取   queryDB()
			value = key + "Value";
			//将数据库中获取到的结果放入内部存储器中
			cache.put(key, value);
		}
		return value;
	}
}
/*
上面的get方法加synchronized后每次只能有一个线程来查询，但只有写的时候才需要互斥，修改如下
来一个读写锁
private ReadWriteLock rwl = new ReentrantReadWriteLock();
public Object get(String key)
{	
	上读锁
	rwl.readLock().lock();
	先查询内部存储器中有没有要的值
	Object value = cache.get(key);
	if (value==null)如果没有，就去数据库中查询，并将查到的结果存入内部存储器中
	{
		释放读锁  上写锁
		rwl.readLock().unlock();
		rwl.writeLock().lock();
		if (value==null)再次进行判断，防止多个写线程堵在这个地方重复写
		{
			value = “aaaa”;
			cache.put(key, value);
		}
		设置完成 释放写锁，恢复读写状态
		rwl.readLock().lock();
		rwl.writeLock().unlock();
	}
	释放读锁
	rwl.readLock().unlock();
	return value;										
}注意：try finally中unlock
*/