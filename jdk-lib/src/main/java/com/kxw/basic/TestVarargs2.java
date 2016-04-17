package com.kxw.basic;

interface A{
	void ma(String... names);
}

class B implements A{
	public void ma(String[] names){
		for(String name : names)
			System.out.println(name);
	}
}

public class TestVarargs2{
	public static void main(String[] args){
		A a = new B();
		a.ma("Tom","Billy","Nancy");
		B b = new B();
		//b.ma("Tom","Billy","Nancy");  //非法
		String[] friends = {"Tom","Billy","Nancy"};
		b.ma(friends);
	}
}
