package cn.itheima;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class TraditionalTimerDemo
{

	private static class Task extends TimerTask
	{
		@Override
		public void run()
		{
			// TODO Auto-generated method stub
			System.out.println("Õ¨ÁË£¡");
			new Timer().schedule(new Task(), 2000);
		}
	}

	/**´«Í³¶¨Ê±Æ÷
	 * @param args
	 */
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		new Timer().schedule(new Task(), 3000);
		while (true)
		{
			System.out.println(new Date().getSeconds());
			try
			{
				Thread.sleep(1000);
			} catch (InterruptedException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}