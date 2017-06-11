package com.kxw.servlet3;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
//http://blog.csdn.net/kingson_wu/article/details/48466633
//这样的话只需要将class文件放入WEB-INF\classes 中，不需要再web.xml中作任何改动就完成部署
/*
       name == <servlet-name>
       urlPatterns == <url-pattern>,
       loadOnStartup == <load-on-startup>
       initParam == <init-param>
       name == <param-name>
       value == <param-value>
*/
@WebServlet(name = "HelloServlet", urlPatterns = {"/HelloServlet"}, loadOnStartup = 1,
        initParams = {
                @WebInitParam(name = "name", value = "xiazdong"),
                @WebInitParam(name = "age", value = "20")
        })
public class HelloServlet extends HttpServlet {
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    @Override
    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("GBK");
        ServletConfig config = getServletConfig();
        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<body>");
        out.println("Hello world" + "<br />");
        out.println(config.getInitParameter("name"));
        out.println("</body>");
        out.println("</html>");
    }
}