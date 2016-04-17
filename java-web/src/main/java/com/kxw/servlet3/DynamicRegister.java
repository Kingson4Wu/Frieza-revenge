package com.kxw.servlet3;

import javax.servlet.*;
import java.util.EnumSet;

/**
 * Created by kingsonwu on 15/12/26.
 */
public class DynamicRegister implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {

        ServletContext sc = servletContextEvent.getServletContext();

        // Register Servlet
        ServletRegistration sr = sc.addServlet("DynamicServlet",
                "web.servlet.dynamicregistration_war.TestServlet");
        sr.setInitParameter("servletInitName", "servletInitValue");
        sr.addMapping("/*");

        // Register Filter
        FilterRegistration fr = sc.addFilter("DynamicFilter",
                "web.servlet.dynamicregistration_war.TestFilter");
        fr.setInitParameter("filterInitName", "filterInitValue");
        fr.addMappingForServletNames(EnumSet.of(DispatcherType.REQUEST),
                true, "DynamicServlet");

        // Register ServletRequestListener
        sc.addListener("web.servlet.dynamicregistration_war.TestServletRequestListener");
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
/**
 *
 * 下面谈谈动态注册Servlet，但不要希望太高，只能在初始化时进行注册。在运行时为了安全原因，无法完成注册。在初始化情况下的注册Servlet组件有两种方法：
 1.实现ServletContextListener接口,在contextInitialized方法中完成注册.
 2.在jar文件中放入实现ServletContainerInitializer接口的初始化器
 */
/**
 * 再说说在jar文件中的servlet组件注册，需要在jar包含META-INF/services/javax.servlet.ServletContainerInitializer文件，
 * 文件内容为已经实现ServletContainerInitializer接口的类
 *
 * jar文件中不但可以包含需要自定义注册的servlet，也可以包含应用注解的servlet，具体怎么做，视具体环境而定。
 * 把处理某类事物的servlet组件打包成jar文件，有利于部署和传输，功能不要了，直接去除掉jar即可
 */