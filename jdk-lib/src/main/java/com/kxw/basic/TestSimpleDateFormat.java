package com.kxw.basic;

import java.util.Date;
import java.util.Locale;
import java.text.SimpleDateFormat;

public class TestSimpleDateFormat{
	public static void main(String args[]){
		Locale locale1 = new Locale("zh","CN");
		Locale locale2 = new Locale("en","US");

		SimpleDateFormat sdf1 = new SimpleDateFormat();
		SimpleDateFormat sdf2 = new SimpleDateFormat(
				"yyyy/MM/dd hh:mm:ss");
		SimpleDateFormat sdf3 = new SimpleDateFormat(
				"yyyy/MM/dd hh:mm:ss E a z",locale1);
		SimpleDateFormat sdf4 = new SimpleDateFormat(
				"yyyy/MM/dd hh:mm:ss E a z",locale2);
		SimpleDateFormat sdf5 = new SimpleDateFormat(
				"yyyy年MM月dd日 E a HH点mm分ss秒");
		SimpleDateFormat sdf6 = new SimpleDateFormat(
				"'On 'yyyy-MM-dd HH' 0''clock,We tested it.'");

		Date d = new Date();
		System.out.println(sdf1.format(d));
		System.out.println(sdf2.format(d));
		System.out.println(sdf3.format(d));
		System.out.println(sdf4.format(d));
		System.out.println(sdf5.format(d));
		System.out.println(sdf6.format(d));

		try{
			d = sdf2.parse("2008/08/08 17:23:45");
			System.out.println(d);
			d = sdf5.parse("2007年04月17日 星期二 上午 10点02分54秒");
			System.out.println(d);
		}catch(java.text.ParseException e){
			e.printStackTrace();
		}
	}
}