package com.kxw.basic;

import java.util.*;

public class TestCalendar{
	public static void main(String[] args){
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

/**

 cal1.add(Calendar.DAY_OF_MONTH,1);
 cal1.add(Calendar.DAY_OF_YEAR,1);
 cal1.add(Calendar.DATE,1);
 就单纯的add操作结果都一样，因为都是将日期+1


 DAY_OF_MONTH的主要作用是cal.get(DAY_OF_MONTH)，用来获得这一天在是这个月的第多少天

 Calendar.DAY_OF_YEAR的主要作用是cal.get(DAY_OF_YEAR)，用来获得这一天在是这个年的第多少天。

 同样，还有DAY_OF_WEEK，用来获得当前日期是一周的第几天
 */