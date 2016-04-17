package com.kxw.basic;

import java.util.Set;
import java.util.HashMap;
import java.util.Collection;
import java.util.Iterator;

public class TestHashMap{
	public static void main(String[] args) {
		HashMap hm = new HashMap();
		hm.put(new Integer(1003),new Person(1003,"张三",15));
		hm.put(new Integer(1008),new Person(1008,"李四",25));
		hm.put(1015,new Person(1015,"王五",73));   //自动封装
		hm.put(1001,new Person(1001,"赵六",49));

		System.out.println("----检索单个元素----");
		Person p = (Person)hm.get(1008);
		System.out.println(p);

		System.out.println("----遍历所有\"键\"（元素名）----");
		Set names = hm.keySet();
		for(Object o : names){
			System.out.println(o);
		}

		System.out.println("----遍历所有\"值\"（元素值）----");
		Collection values = hm.values();
		for(Object o : values){
			System.out.println(o);
		}
	}
}