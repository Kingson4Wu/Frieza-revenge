<http://lishaorui.iteye.com/blog/1051823>
使用Spring的taskExecutor实现线程池

博客分类： Spring
Spring多线程BeanthreadApache
最近，由于项目里需要用到线程池来提高处理速度，记录一下spring的taskExecutor执行器来实现线程池。

这里省略了Service接口的定义和在applicationContext.xml文件中配置相应的bean（service）。

1、处理器实现类
Java代码  收藏代码
package com.shine.job;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.task.TaskExecutor;

import com.shine.service.Service;

/**
 *  处理器接口实现类
 */
public class ProcessorImpl implements Processor {

    // 日志对象
    private static Log logger = LogFactory.getLog(ProcessorImpl.class);

    // 执行器
    private TaskExecutor taskExecutor;

    // 业务接口
    private Service service;

    public void process() {
            // 使用多线程处理
            taskExecutor.execute(new Runnable(){
                public void run() {
                    try {
                        logger.debug("[Thread "
                        + Thread.currentThread().getId()
                        + " start]");
                        // 业务处理
                        service.handle(name);
                        logger.debug("[Thread "
                        + Thread.currentThread().getId()
                        + " end]");
                    } catch (RuntimeException e) {
                        logger.error("Service handle exception",e);
                    }
                }
            });
        }
    }

    public void setService(Service service) {
        this.service = service;
    }

    public void setTaskExecutor(TaskExecutor taskExecutor) {
        this.taskExecutor = taskExecutor;
    }

}


2、applicationContext.xml配置
Xml代码  收藏代码
<!--  线程池（执行器） -->
<task:executor id="taskExecutor" pool-size="1-4" queue-capacity="128" />

<!--  处理接口  -->
<bean id="processor" class="com.shine.job.ProcessorImpl">
      <property name="service" ref="service" />
      <property name="taskExecutor">
        <ref bean="taskExecutor" />
      </property>
</bean>


其中：
pool-size="1-4"，表示线程池活跃的线程数为1，最大线程数为4；
queue-capacity="128"，表示任务队列的最大容量。

PS：关于taskExecutor还有一种配置bean来实现的，其配置的写法和参数与上面基本一致。

渠道推广代码！！