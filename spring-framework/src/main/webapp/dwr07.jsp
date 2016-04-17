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


function upload(){
	var file=dwr.util.getValue("myfile");
	MyDwr.upload(file,file.value,function(data){
		alert(data);
	} );
}

</script>
</head>
<body>

<input type="file" id="myfile" />
<input type="button" value="上传文件" onclick="upload()"/>

</body>
</html>