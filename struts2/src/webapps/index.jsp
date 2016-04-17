<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>

<head>

<meta http-equiv="Content-Type" content="text/html; charset=gbk">

<title>Insert title here</title>


<%@ taglib prefix="s" uri="/struts-tags"%> 


<script type="text/javascript">

    function clickme()

    {

        var divs=document.getElementById('mydiv');

        var br=document.createElement('br');

        var input=document.createElement('input');

        input.type="file";

        input.name="file"

        var button=document.createElement('input');

        button.type="button";

        button.value="remove";

        divs.appendChild(br);

        divs.appendChild(input);

        divs.appendChild(button);

        button.onclick=function()

        {

            divs.removeChild(br);

            divs.removeChild(input);

            divs.removeChild(button);

        }

    }
    </script>


</head>



<body>

<h1>&gt;Struts2</h1>

<s:fielderror name="file"/>

<s:form action="Upload" enctype="multipart/form-data" method="post">

    <s:file name="file" theme="simple"></s:file>

    <input type="button" value="Add More" onclick="clickme()"/>

    <div id="mydiv">

    </div>

    <s:textfield name="memo"></s:textfield>

    <s:submit></s:submit>

</s:form>

</body>

</html>
