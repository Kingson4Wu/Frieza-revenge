package cn.itheima;

import java.util.Random;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockDemo
{

	/**读写所使用
	 * 三个线程读，三个线程写
	 */
	public static void main(String[] args)
	{
		//共享对象
		final Source source = new Source();
		//创建线程
		for (int i=0; i<3; i++)
		{
			//读
			new Thread(new Runnable()
			{
				@Override
				public void run()
				{
					while (true)
					{
						source.get();
					}
				}
			}).start();
			//写
			new Thread(new Runnable()
			{
				@Override
				public void run()
				{
					while (true)
					{
						source.put(new Random().nextInt(999));
					}
				}
			}).start();
		}
	}

	static class Source
	{
		//共享数据
		private int data = 0;
		//要操作同一把锁上的读或写锁
		ReadWriteLock rwl = new ReentrantReadWriteLock();

		//读方法
		public void get()
		{
			////上读锁
			rwl.readLock().lock();
			try
			{
				//获取数据并输出
				System.out.println("读——"+Thread.currentThread().getName()+"正在获取数据。。。");
				try
				{
					Thread.sleep(new Random().nextInt(6)*1000);
				} catch (InterruptedException e)
				{
					e.printStackTrace();
				}
				System.out.println("读——"+Thread.currentThread().getName()+"获取到的数据："+data);
			}finally
			{
				////解锁
				rwl.readLock().unlock();
			}
		}
		//写方法
		public void put(int data)
		{
			////上写锁
			rwl.writeLock().lock();
			try
			{
				//提示信息
				System.out.println("写——"+Thread.currentThread().getName()+"正在改写数据。。。");
				try
				{
					Thread.sleep(new Random().nextInt(6)*1000);
				} catch (InterruptedException e)
				{
					e.printStackTrace();
				}
				this.data = data;
				System.out.println("写——"+Thread.currentThread().getName()+"已将数据改写为："+data);
			}finally
			{
				////解锁
				rwl.writeLock().unlock();
			}
		}
	}
}