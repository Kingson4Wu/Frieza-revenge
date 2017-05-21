package org.kxw.quartz;

/* 
 * @author sixi 
 * @version 0.1 
 * Company:Tsinghua
 * Date:2012-09-15
 * Description:这是一个执行调度的具体类，在Spring配置文件中指定调用此类的work方法
 * */


import java.util.Date;

/*
 * 使用spring+Quartz执行任务调度的具体类
 * */
public class MyJob {

    /*
     * Description:具体工作的方法，此方法只是向控制台输出当前时间，
     * 输入的日志在：%tomcatRoot%\logs\tomcat7-stdout.yyyy-MM-dd.log中，
     * 其中，yyyy-MM-dd为部署的日期,经试验发现默认情况下并不是每天都生成一个stdout的日志文件
     * @return 返回void
     * */
    public void work()
    {
         System.out.println("当前时间:"+new Date().toString());  
    }
}//End of MyJob