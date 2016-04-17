<%@ page language="java" import="java.util.*" pageEncoding="GBK" %>
<%
	String[] userList={"明日科技","mr","wgh","mrsoft"};
	String user=new String(request.getParameter("user").getBytes("ISO-8859-1"),"GBK");			//获取用户名
	System.out.println("用户名："+user);
	Arrays.sort(userList);								//对数组排序	
	int result=Arrays.binarySearch(userList,user);		//搜索数组
	if(result>-1){
		out.println("很抱歉，该用户名已经被注册！");		//输出检测结果
	}else{
		out.println("恭喜您，该用户名没有被注册！");		//输出检测结果
	}
%>