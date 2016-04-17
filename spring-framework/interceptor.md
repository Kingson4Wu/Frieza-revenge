
默认拦截所有的Controller

SpringMVC拦截器简单使用

博客分类： SpringMVC - 基础篇

基于上一篇文章的基础上

一、拦截器的配置
   1、传统的配置

Xml代码
<bean
        class="org.springframework.web.servlet.mvc.annotation.DefaultAnnotationHandlerMapping" >
        <property name="interceptors">
           <!-- 多个拦截器,顺序执行 -->
           <list>
              <ref bean="commonInterceptor"/>
           </list>
        </property>
    </bean>
    <!--
              如果不定义mappingURL，则默认拦截所有对Controller的请求 ;
             可以使用正则表达式对url进行匹配，从而更细粒度的进行拦截(.*/entryOrJsonController\.do\?action=reg.*);
    -->
    <bean id="commonInterceptor" class="com.wy.interceptor.CommonInterceptor">
       <property name="mappingURL" value=".*/entryOrJsonController\.do\?action=reg.*"/>
    </bean>

  2、基于注解的配置

Xml代码
<!-- 拦截器 -->
    <mvc:interceptors>
        <!-- 多个拦截器,顺序执行 -->
        <mvc:interceptor>
           <mvc:mapping path="/entryOrJsonController/*" /><!-- 如果不配置或/*,将拦截所有的Controller -->
           <bean class="com.wy.interceptor.CommonInterceptor"></bean>
        </mvc:interceptor>
    </mvc:interceptors>




二、拦截器类

Java代码
package com.wy.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class CommonInterceptor implements HandlerInterceptor {

    private Logger log = Logger.getLogger(CommonInterceptor.class);

    public CommonInterceptor() {
        // TODO Auto-generated constructor stub
    }

    private String mappingURL;//利用正则映射到需要拦截的路径
        public void setMappingURL(String mappingURL) {
               this.mappingURL = mappingURL;
       }

    /**
     * 在业务处理器处理请求之前被调用
     * 如果返回false
     *     从当前的拦截器往回执行所有拦截器的afterCompletion(),再退出拦截器链
     *
     * 如果返回true
     *    执行下一个拦截器,直到所有的拦截器都执行完毕
     *    再执行被拦截的Controller
     *    然后进入拦截器链,
     *    从最后一个拦截器往回执行所有的postHandle()
     *    接着再从最后一个拦截器往回执行所有的afterCompletion()
     */
    @Override
    public boolean preHandle(HttpServletRequest request,
            HttpServletResponse response, Object handler) throws Exception {
        // TODO Auto-generated method stub
        log.info("==============执行顺序: 1、preHandle================");
        String url=request.getRequestURL().toString();
        if(mappingURL==null || url.matches(mappingURL)){
            request.getRequestDispatcher("/msg.jsp").forward(request, response);
            return false;
        }
        return true;
    }

    //在业务处理器处理请求执行完成后,生成视图之前执行的动作
    @Override
    public void postHandle(HttpServletRequest request,
            HttpServletResponse response, Object handler,
            ModelAndView modelAndView) throws Exception {
        // TODO Auto-generated method stub
        log.info("==============执行顺序: 2、postHandle================");
    }

    /**
     * 在DispatcherServlet完全处理完请求后被调用
     *
     *   当有拦截器抛出异常时,会从当前拦截器往回执行所有的拦截器的afterCompletion()
     */
    @Override
    public void afterCompletion(HttpServletRequest request,
            HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        // TODO Auto-generated method stub
        log.info("==============执行顺序: 3、afterCompletion================");
    }

}


注意：注释中的说明。

完整的spring配置文件

Java代码
<?xml version="1.0" encoding="UTF-8" ?>
<beans xmlns="http://www.springframework.org/schema/beans"
        xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
        xmlns:context="http://www.springframework.org/schema/context"
        xmlns:mvc="http://www.springframework.org/schema/mvc"
        xmlns:util="http://www.springframework.org/schema/util"
        xsi:schemaLocation="
          http://www.springframework.org/schema/beans
          http://www.springframework.org/schema/beans/spring-beans-3.0.xsd
          http://www.springframework.org/schema/context
          http://www.springframework.org/schema/context/spring-context-3.0.xsd
          http://www.springframework.org/schema/mvc
          http://www.springframework.org/schema/mvc/spring-mvc-3.0.xsd
          http://www.springframework.org/schema/util
          http://www.springframework.org/schema/util/spring-util-3.0.xsd">

    <!-- 默认的注解映射的支持 ,它会自动注册DefaultAnnotationHandlerMapping 与AnnotationMethodHandlerAdapter
    <mvc:annotation-driven />
    -->

    <!-- 配置静态资源，直接映射到对应的文件夹，不被DispatcherServlet处理，3.04新增功能，需要重新设置spring-mvc-3.0.xsd -->
    <mvc:resources mapping="/js/**" location="/js/" />

    <!-- 自动扫描注解的Controller -->
    <context:component-scan base-package="com.wy.controller.annotation" />

    <!-- 处理在类级别上的@RequestMapping注解-->
    <bean
        class="org.springframework.web.servlet.mvc.annotation.DefaultAnnotationHandlerMapping" >
        <property name="interceptors">
           <!-- 多个拦截器,顺序执行 -->
           <list>
              <ref bean="commonInterceptor"/>
           </list>
        </property>
    </bean>
    <!--
             如果不定义mappingURL，则默认拦截所有对Controller的请求 ;
             可以使用正则表达式对url进行匹配，从而更细粒度的进行拦截(.*/entryOrJsonController\.do\?action=reg.*);
    -->
    <bean id="commonInterceptor" class="com.wy.interceptor.CommonInterceptor">
       <property name="mappingURL" value=".*/entryOrJsonController\.do\?action=reg.*"/>
    </bean>

    <!-- 处理方法级别上的@RequestMapping注解-->
    <bean
        class="org.springframework.web.servlet.mvc.annotation.AnnotationMethodHandlerAdapter" >
        <property name="messageConverters">
            <util:list id="beanList">
                <ref bean="mappingJacksonHttpMessageConverter"/>
            </util:list>
        </property>
    </bean>

    <!--
              将指定路径的请求直接转到对应的view上，而不需要特定的controller来处理请求 .
              注意：此处的映射路径是/hello,请求时http://localhost:8080/SpringMVC/hello
              不能在hello.xxx,而不使用此种方式的映射可以加的,因为web.xml配置的是‘/’
     -->
    <mvc:view-controller path="/hello" view-name="hello" />

    <!-- 视图解析器策略 和 视图解析器 -->
    <!-- 对JSTL提供良好的支持 -->
    <bean
        class="org.springframework.web.servlet.view.InternalResourceViewResolver">
        <!-- 默认的viewClass,可以不用配置
        <property name="viewClass" value="org.springframework.web.servlet.view.InternalResourceView" />
         -->
        <property name="prefix" value="/WEB-INF/page/" />
        <property name="suffix" value=".jsp" />
    </bean>

    <!-- 处理JSON数据转换的 -->
    <bean id="mappingJacksonHttpMessageConverter"
        class="org.springframework.http.converter.json.MappingJacksonHttpMessageConverter">
        <!-- 为了处理返回的JSON数据的编码，默认是ISO-88859-1的，这里把它设置为UTF-8,解决有乱码的情况 -->
        <property name="supportedMediaTypes">
            <list>
                  <value>text/html;charset=UTF-8</value>
            </list>
        </property>
    </bean>

    <!-- 拦截器 -->
    <mvc:interceptors>
        <!-- 多个拦截器,顺序执行 -->
        <mvc:interceptor>
           <mvc:mapping path="/entryOrJsonController/*" /><!-- 如果不配置或/*,将拦截所有的Controller -->
           <bean class="com.wy.interceptor.CommonInterceptor"></bean>
        </mvc:interceptor>
    </mvc:interceptors>

    <!--
       ResourceBundleViewResolver通过basename所指定的ResourceBundle解析视图名。
                  对每个待解析的视图，ResourceBundle里的[视图名].class所对应的值就是实现该视图的类。
                  同样，[视图名].url所对应的值是该视图所对应的URL。
                  可以指定一个parent view，其它的视图都可以从parent view扩展。
                  用这种方法，可以声明一个默认的视图。

    <bean id="messageSource"
        class="org.springframework.context.support.ResourceBundleMessageSource">
        <property name="basename" value="welcome" />
    </bean>
    -->

</beans>
来源： <http://exceptioneye.iteye.com/blog/1303842>
