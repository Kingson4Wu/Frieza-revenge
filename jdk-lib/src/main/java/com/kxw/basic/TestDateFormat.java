package com.kxw.basic;

import java.util.Date;
import java.util.Locale;
import java.text.DateFormat;

public class TestDateFormat{
	public static void main(String args[]){
		Date d = new Date();
		Locale locale1 = new Locale("zh","CN");
		Locale locale2 = new Locale("en","US");
		
		test(d,locale1);
		System.out.println("----------------------");
		test(d,locale2);
	}
	public static void test(Date d,Locale locale){
		DateFormat df1 = DateFormat.getDateTimeInstance();
		DateFormat df2 = DateFormat.getDateTimeInstance(DateFormat.SHORT,DateFormat.SHORT,locale);
		DateFormat df3 = DateFormat.getDateTimeInstance(DateFormat.MEDIUM,DateFormat.MEDIUM,locale);
		DateFormat df4 = DateFormat.getDateTimeInstance(DateFormat.LONG,DateFormat.LONG,locale);
		DateFormat df5 = DateFormat.getDateTimeInstance(DateFormat.FULL,DateFormat.FULL,locale);		
		System.out.println(df1.format(d));
		System.out.println(df2.format(d));
		System.out.println(df3.format(d));
		System.out.println(df4.format(d));
		System.out.println(df5.format(d));
	}		
}