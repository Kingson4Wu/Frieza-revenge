<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="<%=request.getContextPath()%>/dwr/engine.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/dwr/interface/MyDwr.js"></script>
<script type="text/javascript">

MyDwr.list(listUser);
function listUser(users){
	
	for(var i=0;i<users.length;i++){
		alert(users[i].username+","+users[i].group.name);
	}
	
}


</script>
</head>
<body>

</body>
</html>