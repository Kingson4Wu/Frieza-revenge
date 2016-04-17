
<%@ pageimport="com.caucho.hessian.client.HessianProxyFactory,app.demo.BasicAPI"%>
<%@page language="java"%>
<%
    HessianProxyFactory factory = new HessianProxyFactory();
    String url = ("http://" + request.getServerName() + ":" + request.getServerPort()
            + request.getContextPath() + "/hello");
    out.println(url+"<br>");
    BasicAPI basic = (BasicAPI) factory.create(BasicAPI.class, url);
    out.println("Hello: " + basic.hello()+"<br>");
    out.println("Hello: " + basic.getUser().getUserName()+"<br>");
    out.println("Hello: " + basic.getUser().getPassword()+"<br>");
%>