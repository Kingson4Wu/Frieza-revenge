package cn.itheima;


import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
//空中网面试题 再写，同步集合  只要用到同步集合就可以了  自定义的类没有同步好
public class Test1
{
	/*
	 private static List<String> logs = new Vector<String>();
	 static class Log
	{
		public synchronized String get()
		{
			if (logs.size()>0)
				return logs.remove(0);
			else
				return null;
		}
		public void put(String log)
		{
			logs.add(log);
		}
	}
	*/
	public static void main(String[] args)
	{
		//final Log loger = new Log();
		final List<String> loger = new Vector<String>();
		System.out.println("begin:"+System.currentTimeMillis()/1000);
		for (int i=0; i<4; i++)
		{
			new Thread(new Runnable()
			{
				@Override
				public void run()
				{
					while (true)
					{
						String log;
						if (loger.size()>0) {
							log = loger.remove(0);
							if (log!=null)
							{
								if (log.contains("16"))
								{	parseLog(log);
									System.exit(0);
								}
								else {
									parseLog(log);
								}
							}}
					}
				}
			}).start();
		}
		for (int i=0; i<16; i++)	//这行代码不能改动
		{
			String log = "" + (i+1);	//这行代码不能改动
			{
				loger.add(log);
				//Test1.parseLog(log);
			}
		}
	}
	//parseLog方法内部代码不能改动
	public static void parseLog(String log)
	{
		System.out.println(log+":"+(System.currentTimeMillis()/1000));
		try
		{
			Thread.sleep(1000);
		}
		catch(InterruptedException e)
		{
			e.printStackTrace();
		}
	}
}