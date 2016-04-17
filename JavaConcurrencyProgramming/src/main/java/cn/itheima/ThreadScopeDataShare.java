package cn.itheima;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class ThreadScopeDataShare
{

	/**线程范围内的数据共享
	 * 两个模块在两个不同的进程中各自的数据共享问题
	 * 将ThreadScopeDataShare文件中的集合换成了ThreadLocal对象
	 */
	private static ThreadLocal<Integer> threadLocal = new ThreadLocal<Integer>();
	public static void main(String[] args)
	{
		//创建两个线程
		for (int i=0; i<2; i++)
		{
			new Thread(
					new Runnable()
					{
						@Override
						public void run()
						{
							//先设置该线程中的数据 
							int data = new Random().nextInt();
							//存放起来
							threadLocal.set(data);
							System.out.println(Thread.currentThread().getName()+"共享数据设置为："+data);
							new A().get();
							new B().get();
						}
					}).start();
		}
	}

	//两个模块
	static class A
	{
		public void get()
		{
			int data = threadLocal.get();
			System.out.println(Thread.currentThread().getName()+"--A 模块数据："+data);
		}
	}
	static class B
	{
		public void get()
		{
			int data = threadLocal.get();
			System.out.println(Thread.currentThread().getName()+"--B 模块数据："+data);
		}
	}
}