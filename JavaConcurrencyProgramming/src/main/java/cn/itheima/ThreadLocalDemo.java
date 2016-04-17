package cn.itheima;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class ThreadLocalDemo
{

	/**线程范围内的数据共享
	 * 两个模块在两个不同的进程中各自的数据共享问题
	 *
	 * 该程序存在的问题：
	 * 		数据获取不同步
	 * 			Thread-1共享数据设置为：-997057737
	 Thread-1--A 模块数据：-997057737
	 Thread-0共享数据设置为：11858818
	 Thread-0--A 模块数据：11858818
	 Thread-0--B 模块数据：-997057737
	 Thread-1--B 模块数据：-997057737
	 最好将Runnable中设置数据的方法也写在对应的模块中，与获取数据模块互斥
	 * @param args
	 */
	private static int data = 0;
	//需要将线程对应的数据保存起来
	private static Map<Thread, Integer> threadData = new HashMap<Thread, Integer>();
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
							//先设置该线程中的数据  线程中的数据需要单独设置，不能修改了全局值
							int data = new Random().nextInt();
							//存放起来
							threadData.put(Thread.currentThread(), data);
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
			data = threadData.get(Thread.currentThread());
			System.out.println(Thread.currentThread().getName()+"--A 模块数据："+data);
		}
	}
	static class B
	{
		public void get()
		{
			data = threadData.get(Thread.currentThread());
			System.out.println(Thread.currentThread().getName()+"--B 模块数据："+data);
		}
	}
}