package cn.itheima;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class CopyOfTest3 extends Thread
{
	private TestDo33 testDo;
	private String key;
	private String value;
	public CopyOfTest3(String key, String key2, String value)
	{
		this.testDo = TestDo33.getInstance();
		/*常量“1”和 “1”是同一个对象，下面这行代码就是要用“1”+“”的
			方式产生新的对象，以实现内容没有改变，仍然相等（都还为“1”），
			但对象却不再是同一个的效果
		 */
		this.key = key + key2;
		this.value = value;
	}
	public static void main(String[] args) throws InterruptedException
	{
		CopyOfTest3 a = new CopyOfTest3("1", "", "1");
		CopyOfTest3 b = new CopyOfTest3("1", "", "2");
		CopyOfTest3 c = new CopyOfTest3("3", "", "3");
		CopyOfTest3 d = new CopyOfTest3("4", "", "4");
		System.out.println("begin"+":"+System.currentTimeMillis()/1000);
		a.start();
		b.start();
		c.start();
		d.start();
	}
	public void run()
	{
		testDo.doSome(key, value);
	}
}

class TestDo33
{
	private TestDo33(){}
	private static TestDo33 _instance = new TestDo33();
	public static TestDo33 getInstance()
	{
		return _instance;
	}

	//将所有传过来的key都存起来
	//private List<Object> keys = new ArrayList<Object>();
	private CopyOnWriteArrayList<Object> keys = new CopyOnWriteArrayList<Object>();
	public void doSome(Object key, String value)
	{
		//先用这个key当锁，用过一次就存到集合中
		Object o = key;
		//判断这个锁用过没有
		if (!keys.contains(o))
		{
			//如果这个key没有用过，就用它当锁，把它存到锁集合中
			keys.add(o);
		}
		else	//锁集合中已经有了这个key
		{
			//这个key已经当过锁了，就把它拿出来，还用它做锁，就和现在的key互斥了
			//因为不知道原来key的位置，所有需要进行遍历
			for (Iterator<Object> it = keys.iterator(); it.hasNext();)
			{
				//当前遍历到的对象
				Object oo = it.next();
				//如果找到了，就让它做锁
				if (oo.equals(o))
				{
					o = oo;
					break;	//找到了，不用再循环了
				}
			}
			//o = keys.get(keys.indexOf(o));	//key和o不是同一个对象，拿不到
		}

		synchronized (o)
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
		}
	}
}