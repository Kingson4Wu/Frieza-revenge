package com.kxw.dynamicproxy3;

import java.lang.reflect.Proxy;

//感觉类似实现了多态---------------------------------------------

public class Demo
{
	public static void main(String[] args)
	{

		// 1.通用的动态代理实现

		CommonInvocationHandler handler = new CommonInvocationHandler();

		Foo f;

		// 2.接口实现1

		handler.setTarget(new FooImpl());//--------------------------------------

		// 方法参数说明：代理类、代理类实现的接口列表、代理类的处理器

		// 关联代理类、代理类中接口方法、处理器，当代理类中接口方法被调用时，会自动分发到处理器的invoke方法

		// 如果代理类没有实现指定接口列表，会抛出非法参数异常

		f = (Foo) Proxy.newProxyInstance(Foo.class.getClassLoader(),

				new Class[] { Foo.class },

				handler);

		f.doAction();

		// 3.接口实现2

		handler.setTarget(new FooImpl2());//---------其实这里就是运用了反射的原理，JDK的代理类就是运用的反射，所以实际上我们可以利用反射类自己实现一个代理类

		f = (Foo) Proxy.newProxyInstance(Foo.class.getClassLoader(),

				new Class[] { Foo.class },

				handler);

		f.doAction();




	}
}