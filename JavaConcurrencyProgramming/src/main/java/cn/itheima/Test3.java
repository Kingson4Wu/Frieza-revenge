package cn.itheima;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Test3 extends Thread
{
	private TestDo3 testDo;
	private String key;
	private String value;
	public Test3(String key, String key2, String value)
	{
		this.testDo = TestDo3.getInstance();
		/*常量“1”和 “1”是同一个对象，下面这行代码就是要用“1”+“”的
			方式产生新的对象，以实现内容没有改变，仍然相等（都还为“1”），
			但对象却不再是同一个的效果
		 */
		this.key = key + key2;
		this.value = value;
	}
	public static void main(String[] args) throws InterruptedException
	{
		Test3 a = new Test3("1", "", "1");
		Test3 b = new Test3("1", "", "2");
		Test3 c = new Test3("3", "", "3");
		Test3 d = new Test3("4", "", "4");
		System.out.println("begin"+":"+System.currentTimeMillis()/1000);
		a.start();
		b.start();
		c.start();
		d.start();
	}
	@Override
	public void run()
	{
		testDo.doSome(key, value);
	}
}

class TestDo3
{
	private TestDo3(){}
	private static TestDo3 _instance = new TestDo3();
	public static TestDo3 getInstance()
	{
		return _instance;
	}
	Lock lock = new ReentrantLock();
	public void doSome(Object key, String value)
	{
		if ("1".equals(key)) {
			lock.lock();
		}
		//System.out.println("OKOKOOK");
		//synchronized ("1")
		try
		//此大括号内的代码是需要局部同步的代码，不能改动！
		{
			try
			{
				Thread.sleep(1000);
				System.out.println(key+":"+value+":"+System.currentTimeMillis()/1000);
			}
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}
		}finally
		{
			if ("1".equals(key)) {
				lock.unlock();
			}
		}
	}
}