package cn.itheima;

public class ThreadDemo
{

	/**创建线程的两种传统方式
	 * @param args
	 */
	public static void main(String[] args)
	{
		//1、创建Thread类的子类，覆盖其中的run方法
		Thread thread1 = new Thread(){
			@Override
			public void run()
			{
				while (true)
				{
					System.out.println("Thread--1::"+Thread.currentThread().getName());
					try	//run方法中的异常不可抛出，只能try
					{
						Thread.sleep(1000);
					} catch (InterruptedException e)
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		};
		thread1.start();

		//2、创建Thread对象的同时传递一个实现Runnable接口的实例对象
		Thread thread2 = new Thread(new Runnable()
		{
			@Override
			public void run()
			{
				while (true)
				{
					System.out.println("Thread--2::"+Thread.currentThread().getName());
					try	//run方法中的异常不可抛出，只能try
					{
						Thread.sleep(1000);
					} catch (InterruptedException e)
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			};
		});
		thread2.start();
	}

}