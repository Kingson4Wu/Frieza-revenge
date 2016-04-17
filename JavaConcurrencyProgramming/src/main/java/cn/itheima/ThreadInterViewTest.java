package cn.itheima;

public class ThreadInterViewTest
{

	/**
	 * 刚看到面试题没看答案之前试写
	 * 子线程循环10次，回主线程循环100次，
	 * 再到子线程循环10次，再回主线程循环100次
	 * 如此循环50次	 
	 */
	public static void main(String[] args)
	{
		int num = 0;
		while (num++<50)
		{
			new Thread(new Runnable()
			{
				@Override
				public void run()
				{
					circle("子线程运行", 10);
				}
			}).start();
			try
			{
				//加这句是保证上边的子线程先运行，刚开始没加，主线程就先开了
				Thread.sleep(2000);
			} catch (InterruptedException e)
			{
				e.printStackTrace();
			}
			circle("主线程", 100);
		}
	}

	public static  void circle(String name, int count)
	{
		for (int i=1; i<=count; i++)
		{
			System.out.println(name+"::"+i);
		}
		try
		{
			Thread.sleep(1000);
		} catch (InterruptedException e)
		{
			e.printStackTrace();
		}
	}
}