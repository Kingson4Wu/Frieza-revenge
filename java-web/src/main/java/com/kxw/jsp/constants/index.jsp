<%@page import="com.abc.mlisting.constants.SalePlatformConstants"%>
<%@include file="../main/header.jsp"%>
<%
	String path = request.getContextPath();
%>
<!-- app 常量 -->
<c:set var="abc" value="<%=SalePlatformConstants.abc.getName()%>"/>

<c:if test ="${abc==params['platform']}"><!-- app -->
						</c:if>
						<th>图片预览</th>
						<c:if test ="${abc==params['platform']}"><!-- app -->
						<th>
						子类型
						</th>
</c:if>