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

function callAdd(){
	var a=dwr.util.getValue("a");
	var b=dwr.util.getValue("b");
	MyDwr.add(parseInt(a),parseInt(b),function(data){
		alert(data);
	});
}

function addAddress(){
	var a=dwr.util.getValue("addressName");
	var data=[{id:a,name:a},{id:10,name:"Messi"},{id:7,name:"Ronaldo"}];
	dwr.util.addOptions("address",data,"id","name");
	
}

function initAddress(){
	
	dwr.util.removeAllOptions("address");
	var data=[{id:14,name:"Henry"},{id:8,name:"Mata"},{id:9,name:"Torres"}];
	dwr.util.addOptions("address",data,"id","name");
}

function initPlayers(){
	
	MyDwr.list(function(data){
		dwr.util.removeAllRows("player");
		dwr.util.addRows("player", data,cellFuncs, { escapeHtml:false });
	});
}

var cellFuncs = [
                 function(data) { return data.username; },
                 function(data) { return data.id; },
                 function(data) { return data.group.name; }
                 
               ];


</script>
</head>
<body>
<!-- 01 -->
<input type="text" id="a"/><input type="text" id="b"/>
<input type="button" value="获取" onclick="callAdd()">
<br>
<!-- 02 -->
<select id="address">
</select>
<input type="button" value="初始化地址" onclick="initAddress()"/>
<input type="text" id="addressName">
<input type="button" value="添加" onclick="addAddress()"/>

<!-- 03 -->
<table border="1" width="600">
<thead>
<tr>
<th>name</th>
<th>id</th>
<th>club</th>
</tr>
</thead>
<tbody id="player"> </tbody>
</table>
<input type="button" value="init players" onclick="initPlayers()"/>

</body>
</html>