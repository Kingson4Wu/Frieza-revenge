package com.kxw.monitor;

import java.io.File;
import java.lang.management.BufferPoolMXBean;
import java.lang.management.ManagementFactory;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.management.MBeanServerConnection;
import javax.management.ObjectName;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;

import com.sun.tools.attach.VirtualMachine;

/**
 * <a href='https://my.oschina.net/shipley/blog/422204'>@link</a>
 *
 * 监控分析工具介绍：

 1. 调整JVM启动参数，如下：
 -Dcom.sun.management.jmxremote.port=9988 -Dcom.sun.management.jmxremote.ssl=false -Dcom.sun.management.jmxremote.authenticate=false -XX:+PrintGC -XX:+HeapDumpBeforeFullGC -XX:+HeapDumpAfterFullGC

 开启JMX服务，可实现远程通过jconsole.exe 和 jvisualvm.exe 来监控JVM的性能参数，其中jconsole可看到堆外内存的使用情况（使用了BufferPoolMXBean）；
 开启了JVM做full gc前后做一次dump操作，用于分析堆内存的使用。

 2.另外使用Oracle官网上找到的MonBuffers 类来实时监控堆外内存的使用， 代码见附录；

 3. Memory Analysis工具用于分析dump文件；

 4.tcpdump，wireshake分别用于捕获和分析Thrift服务接收到的字节流。


 */
public class MonBuffers {

    static final String CONNECTOR_ADDRESS =

        "com.sun.management.jmxremote.localConnectorAddress";

    public static void main(String[] args) throws Exception {

        // attach to target VM to get connector address

        VirtualMachine vm = VirtualMachine.attach(args[0]);

        String connectorAddress = vm.getAgentProperties().getProperty(CONNECTOR_ADDRESS);

        if (connectorAddress == null) {

            // start management agent

            String agent = vm.getSystemProperties().getProperty("java.home") +

                File.separator + "lib" + File.separator + "management-agent.jar";

            vm.loadAgent(agent);

            connectorAddress = vm.getAgentProperties().getProperty(CONNECTOR_ADDRESS);

            assert connectorAddress != null;

        }

        // connect to agent

        JMXServiceURL url = new JMXServiceURL(connectorAddress);

        JMXConnector c = JMXConnectorFactory.connect(url);

        MBeanServerConnection server = c.getMBeanServerConnection();

        // get the list of pools

        Set<ObjectName> mbeans = server.queryNames(

            new ObjectName("java.nio:type=BufferPool,*"), null);

        List<BufferPoolMXBean> pools = new ArrayList<BufferPoolMXBean>();

        for (ObjectName name : mbeans) {

            BufferPoolMXBean pool = ManagementFactory

                .newPlatformMXBeanProxy(server, name.toString(), BufferPoolMXBean.class);

            pools.add(pool);

        }

        // print headers

        for (BufferPoolMXBean pool : pools) {

            System.out.format("         %18s             ", pool.getName());
        }

        System.out.println();

        System.out.format("%20s", "timestamp");

        for (int i = 0; i < pools.size(); i++) {

            System.out.format("%6s %10s %10s  ", "Count", "Capacity", "Memory");
        }

        System.out.println();

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        // poll and print usage

        for (; ; ) {

            System.out.format("%10s", df.format(new Date()));

            for (BufferPoolMXBean pool : pools) {

                System.out.format("%6d %10d %10d  ",

                    pool.getCount(), pool.getTotalCapacity(), pool.getMemoryUsed());

            }

            System.out.println();

            Thread.sleep(2000);

        }

    }
}
