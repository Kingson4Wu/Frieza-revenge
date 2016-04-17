<%@ page language="java" contentType="text/html; charset=UTF-8"  
    pageEncoding="UTF-8"%>  
<%  
    String path = request.getContextPath();  
    String WsBasePath = "ws://" + request.getServerName() + ":"  
            + request.getServerPort() + path + "/";  
%>  
<html>  
<head>  
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">  
<title>websocket聊天室</title>  
<style type="text/css">  
#chat {  
    text-align: left;  
    width: 600px;  
    height: 500px;  
    width: 600px;  
}  
  
#up {  
    text-align: left;  
    width: 100%;  
    height: 400px;  
    border: 1px solid green;  
    OVERFLOW-Y: auto;  
}  
  
#down {  
    text-align: left;  
    height: 100px;  
    width: 100%;  
}  
</style>  
</head>  
<body>  
    <h2 align="center">基于HTML5的聊天室</h2>  
    <div align="center" style="width: 100%; height: 700px;">  
        <div id="chat">  
            <div id="up"></div>  
            <div id="down">  
                <textarea style="width: 602px; height: 100%;" id="send"></textarea>  
            </div>  
        </div>  
        <br/>  
        <input type="button" value="连接" onclick="chat(this);"> <input  
            type="button" value="发送" onclick="send(this);" disabled="disabled"  
            id="send_btn" title="Ctrl+Enter发送">  
    </div>  
</body>  
<script type="text/javascript">  
    var socket;  
    var receive_text = document.getElementById("up");  
    var send_text = document.getElementById("send");  
    function addText(msg) {  
        receive_text.innerHTML += "<br/>" + msg;  
        receive_text.scrollTop = receive_text.scrollHeight;  
    }  
    var chat = function(obj) {  
        obj.disabled = "disabled";  
        try{  
            socket = new WebSocket('<%=WsBasePath + "chat"%>');  
            receive_text.innerHTML += "<font color=green>正在连接服务器……</font>";  
        }catch(e){  
            receive_text.innerHTML += "<font color=red>抱歉，您的浏览器不支持html5，请使用IE10或者最新版本的谷歌、火狐等浏览器！</font>";  
        }  
        //打开Socket   
        socket.onopen = function(event) {  
            falg=false;  
            addText("<font color=green>连接成功！</font>");  
            document.getElementById("send_btn").disabled = false;  
            send_text.focus();  
            document.onkeydown = function(event) {  
                if (event.keyCode == 13 && event.ctrlKey) {  
                    send();  
                }  
            };  
        };  
        socket.onmessage = function(event) {  
            addText(event.data);  
        };  
  
        socket.onclose = function(event) {  
            addText("<font color=red>连接断开！</font>");  
            obj.disabled = "";  
        };  
    };  
    var send = function(obj) {  
        if (send_text.value == "") {  
            return;  
        }  
        socket.send(send_text.value);  
        send_text.value = "";  
        send_text.focus();  
    };  
</script>  
</html>  