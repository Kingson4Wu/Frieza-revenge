package com.kxw.basic;

import java.util.Date;

public class TestVarargs{
	public static void main(String[] args){
		TestVarargs tv = new TestVarargs();
		tv.myprint("china",5000,new Integer(54),new Date(),5,7.89);
	}		
	
	
	public void myprint(String s,int i,Object... objs){
		System.out.println(s.toUpperCase());
		System.out.println(100 * i);
		for(Object o: objs){
			System.out.println(o);	
		}
		/*
		Object[] a = objs;
		for(int j=0 ; j<a.length ; j++){
			System.out.println(a[j]);
		}
		*/
	}	
}
