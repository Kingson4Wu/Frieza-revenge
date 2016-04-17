package cn.itheima;

import java.util.Random;

public class ThreadLocalShareDataDemo
{

	/**06. ThreadLocal类及应用技巧
	 * 将线程范围内共享数据进行封装，封装到一个单独的数据类中，提供设置获取方法
	 * 将该类单例化，提供获取实例对象的方法，获取到的实例对象是已经封装好的当前线程范围内的对象
	 * @param args
	 */
	public static void main(String[] args)
	{
		for (int i=0; i<2; i++)
		{
			new Thread(
					new Runnable()
					{
						@Override
						public void run()
						{
							int data = new Random().nextInt(889);
							System.out.println(Thread.currentThread().getName()+"产生数据："+data);
							MyData myData = MyData.getInstance();
							myData.setAge(data);
							myData.setName("Name:"+data);
							new A().get();
							new B().get();
						}
					}).start();
		}

	}

	static class A
	{
		//可以直接使用获取到的线程范围内的对象实例调用相应方法
		String name = MyData.getInstance().getName();
		int age = MyData.getInstance().getAge();
		public void get()
		{
			System.out.println(Thread.currentThread().getName()+"-- AA name:"+name+"...age:"+age);
		}
	}

	static class B
	{
		//可以直接使用获取到的线程范围内的对象实例调用相应方法
		String name = MyData.getInstance().getName();
		int age = MyData.getInstance().getAge();
		public void get()
		{
			System.out.println(Thread.currentThread().getName()+"-- BB name:"+name+"...age:"+age);
		}
	}

	static class MyData
	{
		private String name;
		private int age;
		public String getName()
		{
			return name;
		}
		public void setName(String name)
		{
			this.name = name;
		}
		public int getAge()
		{
			return age;
		}
		public void setAge(int age)
		{
			this.age = age;
		}
		//单例
		private MyData() {};
		//提供获取实例方法
		public static MyData getInstance()
		{
			//从当前线程范围内数据集中获取实例对象
			MyData instance = threadLocal.get();
			if (instance==null)
			{
				instance = new MyData();
				threadLocal.set(instance);
			}
			return instance;
		}
		//将实例对象存入当前线程范围内数据集中
		static ThreadLocal<MyData> threadLocal = new ThreadLocal<MyData>();
	}

}