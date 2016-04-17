package com.kxw.dynamicproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

//客户端

/**
 * 使用Java提供的代理类实现简单的动态代理
 */

public class Client
{
	static public void main(String[] args) throws Throwable
	{

		RealSubject rs = new RealSubject(); // 在这里指定被代理类
		InvocationHandler ds = new DynamicSubject(rs);
		Class<?> cls = rs.getClass();

		// 以下是一次性生成代理

		Subject subject = (Subject) Proxy.newProxyInstance(
				cls.getClassLoader(), cls.getInterfaces(), ds);

		subject.request();//实际上执行代理类的invoke方法，隐式执行
	}
}