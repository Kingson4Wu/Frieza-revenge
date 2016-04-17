package cn.itheima;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Test2
{
	public static void main(String[] args)
	{
		//和上一题一样思路来
		final BlockingQueue<String> queue = new ArrayBlockingQueue<String>(10);
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
						String output = queue.take();
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
				queue.put(TestDo.doSome(input));
			} catch (InterruptedException e)
			{
				e.printStackTrace();
			}
			//String output = TestDo.doSome(input);
			//System.out.println(Thread.currentThread().getName()+":"+output);
		}
	}
}
//不能改动此TestDo类
class TestDo
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