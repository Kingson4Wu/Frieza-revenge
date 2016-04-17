<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="<%=request.getContextPath()%>/dwr/engine.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/dwr/util.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/dwr/interface/MyDwr.js"></script>
<script type="text/javascript">

/*
MyDwr.deleteUser(function(data){
	alert(data);
});*/

MyDwr.deleteUser({
	callback:deleteUser,
	errorHandler:function(msg,exception){
		alert(msg);
	/*	alert(exception.javaClassName);
		//alert(exception.lineNumber);//失败
		for(var ea in exception){
			alert(ea);
		}
		alert(exception.stackTrace);*/
		
		 alert("Error message is: " + msg + " - Error Details: " + dwr.util.toDescriptiveString(exception, 2));
	}
	
});

function deleteUser(data){
	
}


</script>
</head>
<body>

</body>
</html>