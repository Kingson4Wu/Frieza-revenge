package com.kxw.interator.test;

import com.kxw.interator.concreteContainer.ConcreteAggregate;
import com.kxw.interator.container.Aggregate;
import com.kxw.interator.iterator.Iterator;

public class Client {  
	  
	 public static void main(String[] args){  
	  Aggregate<String> ag = new ConcreteAggregate<String>();  
	  //其实List等所有集合就是用了迭代器模式，这里就是使自己创建的类有List一样的迭代器效果
	  ag.add("red");  
	  ag.add("green");  
	  ag.add("blue");  
	  Iterator<String>it = ag.iterator();  
	  while(it.hasNext()){  
	     String str = (String)it.next();  
	     System.out.println(str);  
	  }  
	 }  
	  
	}  