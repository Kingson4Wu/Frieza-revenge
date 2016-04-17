package com.kxw.basic;

import java.util.*;

public class TestCalendar{
	public static void main(String args[]){
		Calendar c = Calendar.getInstance();
		TestCalendar tc = new TestCalendar();
		tc.display(c);
		c.set(2008,8,8);
		tc.display(c);
		c.set(2008,8,8,10,23,15);
		tc.display(c);
		Date d = c.getTime();
		System.out.println(d);
	}	
	public void display(Calendar c){	
		String s = 	c.get(Calendar.YEAR) + "-" + 
				   (c.get(Calendar.MONTH) + 1) + "-" +
					c.get(Calendar.DATE) + " " +
					c.get(Calendar.HOUR_OF_DAY) + ":" +
					c.get(Calendar.MINUTE) + ":" +
					c.get(Calendar.SECOND) + " " +
				   (c.get(Calendar.AM_PM)==0?"����":"����"); 
		System.out.println(s);			
	}
}