package com.kxw.servlet3;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

/**
 * {<a href='http://blog.csdn.net/xiazdong/article/details/7208316'>@link</a>}
 */
@WebServlet(name="UploadServlet" ,urlPatterns={"/upload"})
@MultipartConfig
public class UploadServlet extends HttpServlet{
    public void init(ServletConfig config)throws ServletException{
        super.init(config);
    }
    public void service(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
        Part part = request.getPart("file");
        PrintWriter out = response.getWriter();
        out.println("此文件的大小："+part.getSize()+"<br />");
        out.println("此文件类型："+part.getContentType()+"<br />");
        out.println("文本框内容："+request.getParameter("name")+"<br />");
        out.println(UploadUtil.getFileName(part)+"<br />");
        part.write("F:\\1."+UploadUtil.getFileType(part));
    }
}
/**
 * 原本文件上传时通过 common-fileupload或者SmartUpload，上传比较麻烦，在Servlet 3.0 中不需要导入任何第三方jar包，并且提供了很方便进行文件上传的功能；

 注意点：
 1. html中 <input type="file">表示文件上传控件；
 2. form的 enctype="multipart/form-data"；
 3.在Servlet类前加上 @MultipartConfig
 4.request.getPart()获得；

 下面是一个文件上传的例子：
 upload.html

 [html] view plaincopy
 <html>
 <body>
 <form method="post" enctype="multipart/form-data" action="upload">
 <input type="file" id="file" name="file"/>
 <input type="text" id="name" name="name"/>
 <input type="submit" value="提交"/>
 </form>
 </body>
 </html>
 */