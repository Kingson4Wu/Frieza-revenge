基于HTML5和Tomcat WebSocketServlet的聊天室简单实现



 找不到"javax.servlet.annotation.WebServlet"解决方法
2010-11-21 23:44 5275人阅读 评论(3) 收藏 举报
tomcatserverimport
以前创建的一个项目，打开的时候总是报错。

import javax.servlet.annotation.WebServlet;  
 

后来想起当时这个项目是发布在tomcat7.0下面的， 也就是说当时这个项目buildpath下"add library->Server runtime是tomcat7.0，
而现在我给这个项目添加的server runtime是tomcat 6.0，所以会出现这样的错误。当我改了server runtime为tomcat 7.0以后就没有报错了。


(ATA)WebSocket 开发指南