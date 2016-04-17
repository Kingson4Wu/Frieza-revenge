package com.kxw.path;

/**
 * Created by Kingson.wu on 2015/10/27.
 */
public class ProjectPath {

    public static void main(String[] args) {
       // 1.Servlet
       // this.getServletContext().getRealPath("/");
        //request.getSession().getServletContext().getRealPath("/")

        //2.struts
        //this.getServlet().getServletContext().getRealPath("/");

        //3.spring
        //  ContextLoader.getCurrentWebApplicationContext().getServletContext().getRealPath("/")

    }
}
