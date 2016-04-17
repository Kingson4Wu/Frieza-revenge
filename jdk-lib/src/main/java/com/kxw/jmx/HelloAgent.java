package com.kxw.jmx;

import java.lang.management.ManagementFactory;

import javax.management.MBeanServer;
//import javax.management.MBeanServerFactory;
import javax.management.ObjectName;

//import com.sun.jdmk.comm.HtmlAdaptorServer;

public class HelloAgent {

    /*public static void main(String[] args) throws Exception {
      //  MBeanServer server = MBeanServerFactory.createMBeanServer();//不能在jconsole中使用
       MBeanServer server = ManagementFactory.getPlatformMBeanServer();//可在jconsole中使用
//F:\Program Files\Java\jdk1.7.0_11\bin\jconcole.exe
        ObjectName helloName = new ObjectName("chengang:name=HelloWorld");
      //将MBean注册到MBeanServer中
        server.registerMBean(new Hello(), helloName);

        ObjectName adapterName = new ObjectName("HelloAgent:name=htmladapter,port=8082");
      //创建适配器，用于能够通过浏览器访问MBean
        HtmlAdaptorServer adapter = new HtmlAdaptorServer();


        server.registerMBean(adapter, adapterName);

        adapter.start();
        System.out.println("start.....");

    }*/
} 
