package com.kxw.servlet3;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.*;


/**
 * Servlet在MVC中作为控制器，控制器负责分发任务给MODEL完成，然后把结果交给JSP显示；
 * 而如果有许多MODEL，其中有一个MODEL处理时间很长，则会导致整个页面的显示很慢；
 * 异步处理关键点：将复杂业务处理另外开一个线程，而Servlet将执行好的业务先送往jsp输出，等到耗时业务做完后再送往JSP页面；
 * 一句话：先显示一部分，再显示一部分；
 * 异步处理Servlet的注意点是：
 * 1.需要在Annotation中注明 asyncSupported=true;
 */


@WebServlet(name = "AsyncServlet", urlPatterns = {"/AsyncServlet"}, asyncSupported = true)
public class AsyncServlet extends HttpServlet {
    @Override
    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //final AsyncContext actx = request.startAsync(request, response);


        request.setCharacterEncoding("GBK");
        response.setContentType("text/html;charset=GBK");
        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<body>");
        out.println("====页面开始====<hr />");
        AsyncContext actx = request.startAsync();
        actx.setTimeout(30 * 3000);
        actx.start(new MyThread(actx));
        out.println("====页面结束====<hr />");
        out.println("</body>");
        out.println("</html>");
        out.flush();
    }
}

class MyThread implements Runnable {
    private AsyncContext actx;

    public MyThread(AsyncContext actx) {
        this.actx = actx;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(5 * 1000); //消耗5秒
            actx.dispatch("/1.jsp");
        } catch (Exception e) {
        }
    }
}

/**
 * jsp
 * <%@ page contentType="text/html;charset=GBK" pageEncoding="GBK" session="false"%>
 * <html>
 * <body>
 * <%
 * out.println("======复杂业务方法====");
 * %>
 * </body>
 * </html>
 */