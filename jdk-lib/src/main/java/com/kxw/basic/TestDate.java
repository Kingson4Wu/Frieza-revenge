package com.kxw.basic;

import java.util.*;

public class TestDate{
	public static void main(String[] args){
		Date d1 = new Date(2000);
		long time = d1.getTime();
		System.out.println(time);
		System.out.println(d1);   		  
		
		Date d2 = new Date();
		System.out.println(d2);  		
		
		System.out.println(d2.compareTo(d1));
		System.out.println(d1.equals(new Date(2000)));	
	}
}