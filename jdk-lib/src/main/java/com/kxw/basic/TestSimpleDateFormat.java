package com.kxw.basic;

import java.util.Date;
import java.util.Locale;
import java.text.SimpleDateFormat;

/**
 * SimpleDateFormat(下面简称sdf)类内部有一个Calendar对象引用,它用来储存和这个sdf相关的日期信息,例如sdf.parse(dateStr),
 * sdf.format(date) 诸如此类的方法参数传入的日期相关String, Date等等, 都是交友Calendar引用来储存的.这样就会导致一个问题,
 * 如果你的sdf是个static的, 那么多个thread 之间就会共享这个sdf
 *
 *  Date formats are not synchronized. It is recommended to create separate format instances for each thread.
 *  If multiple threads access a format concurrently, it must be synchronized externally.
 *  Date formats不是同步的，建议每个线程都分别创建format实例变量；或者如果多个线共享一个format的话，必须保持在使用format时是同步的
 *
 *
 This parsing operation uses the DateFormat#calendar
 to produce a Date. All of the
 calendar's date-time fields are Calendar#clear()
 cleared before parsing, and the calendar's default
 values of the date-time fields are used for any missing
 date-time information.
 大概意思就是parse()方法使用calendar来生成返回的Date实例，而每次parse之前，都会先把calendar里的相关属性清除掉。
 问题是这个calendar是个全局变量，也就是线程共享的。因此就会出现一个线程刚把calendar设置好，另一个线程把它给清空了，
 这时第一个线程再parse的话就会有问题了。

 解决方案
 1. 需要的时候创建局部变量

 public Date formatDate(Date d) {
 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
 return sdf.parse(d);
 }
 2. 创建一个共享的SimpleDateFormat实例变量，但是在使用的时候，需要对这个变量进行同步

 private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
 public Date formatDate(Date d) {
 synchronized(sdf) {
 return sdf.parse(d);
 }
 }
 3. 使用ThreadLocal为每个线程都创建一个线程独享SimpleDateFormat变量

 private ThreadLocal<SimpleDateFormat> tl = new ThreadLocal<SimpleDateFormat>();
 public Date formatDate(Date d) {
 SimpleDateFormat sdf = tl.get();
 if(sdf == null) {
 sdf = new SimpleDateFormat("yyyy-MM-dd");
 tl.set(sdf);
 }
 return sdf.parse(d);
 }

 ---
 SimpleDateFormat 是 Java 中一个非常常用的类用来对日期字符串进行解析和格式化输出，但如果使用不小心会导致非常微妙和难以调试的问题，因为 DateFormat 和 SimpleDateFormat 类不都是线程安全的，在多线程环境下调用 format() 和 parse() 方法应该使用同步代码来避免问题。

 下面是你在使用 SimpleDateFormat 应该要小心的几点：

 确保不会在多线程状态下使用同一个 DateFormat 或者 SimpleDateFormat 实例
 如果多线程情况下需要访问同一个实例，那么请用同步方法
 可以使用 JODA 日期时间处理库来避免这些问题
 你也可以使用 commons-lang 包中的 FastDateFormat 工具类
 另外你也可以使用 ThreadLocal 来处理这个问题

 */

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

