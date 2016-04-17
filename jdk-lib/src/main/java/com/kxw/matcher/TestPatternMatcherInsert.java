package com.kxw.matcher;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestPatternMatcherInsert {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String sql = "insert into employee(id,name,salary,email) values(0,ejdef,2,fefefe)";
		String sql2 = "insert into employee(id,name,salary,email) ";
		String RegExp = "(insert into)(.+)([(])";

		Pattern pattern = Pattern.compile(RegExp, Pattern.CASE_INSENSITIVE);

		Matcher matcher = pattern.matcher(sql2);
		// System.out.println(matcher.find());
		//System.out.println(matcher.find());
		// System.out.println(matcher.find());


		// System.out.println(matcher.lookingAt());
		System.out.println(matcher.groupCount());
	/*	 while(matcher.find())
		 {
		     System.out.println(matcher.group());
		 }
		 */
		if (matcher.find()) {


			String start = matcher.group(1);

			String body = matcher.group(2);
			//测试body部分

			String end = matcher.group(3);
			//测试相应的end部分
			System.out.println("------------------------------------------------------");
			System.out.println(start);
			System.out.println(body);
			System.out.println(end);
			System.out.println("------------------------------------------------------");
		}
	        
	    /*     Pattern p = Pattern.compile("cat");
	         Matcher m = p.matcher("one cat two cats in the yard");
	         StringBuffer sb = new StringBuffer();
	         while (m.find()) {
	             //m.appendReplacement(sb, "dog");
	             m.appendReplacement(sb, m.group(0) +"@"); 
	             System.out.println(sb+"******");
	             
	            
	         }
	         m.appendTail(sb);
	         System.out.println(sb.toString());*/
	}

	public  void test(){
		//String dayBeforeYesterday =Test.addDay(new Date(), -1);
		//System.out.println(dayBeforeYesterday);
		String parentPath="/messagecache/service/322.32.32.43:9900_";
		String[] dirs = parentPath.replaceAll("(/)(.*)", "$2").split("/");
		//去掉第一个"/"
		//"(/)(.*)"匹配全部内容
		//$2表示第二个小括号的内容即messagecache/service/322.32.32.43:9900_


		System.out.println(parentPath.replaceAll("(/)(.*)", "$2"));
		for (int j = 0; j < dirs.length; j++) {
			System.out.println(dirs[j]);
		}
	}

}
