<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>发送GET请求</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<link rel="stylesheet" type="text/css" href="CSS/style.css">
<script language="javascript">
	function checkUser(userName){
		if(userName.value==""){
			alert("请输入用户名！");userName.focus();return;
		}else{
			createRequest('checkUser.jsp?user='+userName.value);
		}
	}
    function createRequest(url) {
        http_request = false;
        if (window.XMLHttpRequest) {     // Mozilla⋯⋯等非IE浏览器
            http_request = new XMLHttpRequest();	//创建XMLHttpRequest对象
        } else if (window.ActiveXObject) {     // IE浏览器
            try {
                http_request = new ActiveXObject("Msxml2.XMLHTTP");	//创建XMLHttpRequest对象
            } catch (e) {
                try {
                    http_request = new ActiveXObject("Microsoft.XMLHTTP");	//创建XMLHttpRequest对象
               } catch (e) {}
            }
        }
        if (!http_request) {
            alert("不能创建XMLHttpRequest对象实例！");
            return false;
        }
        http_request.onreadystatechange = getResult;     //调用返回结果处理函数
        http_request.open('POST', url, true);	//创建与服务器的连接
        http_request.send(null);		//向服务器发送请求
    }
	//返回结果处理函数
    function getResult() {
        if (http_request.readyState == 4) {     // 判断请求状态
            if (http_request.status == 200) {     // 请求成功，开始处理返回结果
                alert(http_request.responseText);	//显示判断结果
            } else {     //请求页面有错误
                alert("您所请求的页面有错误！");
            }
        }
    }
</script>
  </head>
  
  <body>
  <form name="form1" method="post" action="">
  <table width="402" border="0" align="center" cellpadding="0" cellspacing="1" bordercolor="#FFFFFF" bordercolorlight="#FFFFFF" bordercolordark="#777777" bgcolor="#999999">
    <tr>
      <td height="30" colspan="2" bgcolor="#EEEEEE">·用户注册</td>
    </tr>
    <tr>
      <td width="113" height="30" align="center" bgcolor="#FFFFFF">用 户 名：</td>
      <td width="387" bgcolor="#FFFFFF"><input name="username" type="text" id="username" size="30">
        <input name="b_checkUser" type="button" class="btn_grey" id="b_checkUser" value="检测用户名" onClick="checkUser(this.form.username);"></td>
    </tr>
    <tr>
      <td height="30" align="center" bgcolor="#FFFFFF">密&nbsp;&nbsp;&nbsp;&nbsp;码：</td>
      <td bgcolor="#FFFFFF"><input name="pwd" type="password" id="pwd" size="30"></td>
    </tr>
    <tr>
      <td height="30" align="center" bgcolor="#FFFFFF">确认密码：</td>
      <td bgcolor="#FFFFFF"><input name="pwd1" type="password" id="pwd1" size="30"></td>
    </tr>
    <tr>
      <td height="40" colspan="2" align="center" bgcolor="#FFFFFF"><input name="b_submit" type="submit" class="btn_grey" id="b_submit" value="提交">
        &nbsp;
      <input name="b_reset" type="reset" class="btn_grey" id="b_reset" value="重置"></td>
    </tr>
  </table>
  </form>  
  </body>
  
</html>
