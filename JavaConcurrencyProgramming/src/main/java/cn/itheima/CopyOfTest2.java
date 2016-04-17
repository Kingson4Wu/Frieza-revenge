package cn.itheima;

import java.util.concurrent.Semaphore;
import java.util.concurrent.SynchronousQueue;

//将第二题换种方式解决
public class CopyOfTest2
{
	//只定义一个许可
	final Semaphore semaphore = new Semaphore(1);
	final static SynchronousQueue<String> queue = new SynchronousQueue<String>();
	public static void main(String[] args)
	{
		System.out.println("begin:"+System.currentTimeMillis()/1000);
		for (int i=0; i<10; i++)
		{
			new Thread(new Runnable()
			{
				public void run()
				{
					//先得到在打印
					try
					{
						/*
						String output = queue.take();
						*/
						String output = TestDo.doSome(queue.take());
						System.out.println(Thread.currentThread().getName()+":"+output);
					} catch (InterruptedException e)
					{
						e.printStackTrace();
					}
				}
			}).start();
		}

		for (int i=0; i<10; i++)	//这行不能改动
		{
			String input = i+"";	//这行不能改动
			//把处理后的数据放进去
			try
			{
				//先将产生的数据存起来，取出来再处理
				queue.put(input);
				//将处理完的结果存起来
				//queue.put(TestDo.doSome(input));
			} catch (InterruptedException e)
			{
				e.printStackTrace();
			}
			//String output = TestDo.doSome(input);
			//System.out.println(Thread.currentThread().getName()+":"+output);
		}
	}
	//不能改动此TestDo类
	static class TestDo
	{
		public static String doSome(String input)
		{
			try
			{
				Thread.sleep(1000);
			}
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}
			String output = input + ":" + (System.currentTimeMillis()/1000);
			return output;
		}
	}
}