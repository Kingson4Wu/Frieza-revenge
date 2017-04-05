package com.kxw.jvm.instrumentation.agentmain;

import java.lang.instrument.Instrumentation;

import com.kxw.jvm.instrumentation.agentmainjar.BugFixAgent;
import net.bytebuddy.agent.ByteBuddyAgent;

/**
 * Created by kingsonwu on 17/4/3.
 *
 * @author kingsonwu
 * @date 2017/04/03
 */
public class HeaderUtilBuddyTest {

    public static void main(String[] args) {

        String input = "X-Priority";
        System.out.println(HeaderUtility.isPriorityCall(input));

        while (true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(HeaderUtility.isPriorityCall(input));


            /**-------------------------------------------------*/
            /** 使用buddy替换,成功了。。。*/
            ByteBuddyAgent.install();
            /**
             * <a href='http://www.jianshu.com/p/f55bfa7d472c'>@link</a>
             * 调用install，attach到当前vm，虚拟一个文件（Agent-Class: net.bytebuddy.agent.Installer）
             * (后续执行net.bytebuddy.agent.Installer的agentmain方法)，
             * 并保存instrumentation对象到net.bytebuddy.agent.Installer对象
             * 最后调用<code>virtualMachineType
             * .getMethod(DETACH_METHOD_NAME)
             * invoke(virtualMachineInstance);</code> detach vm
             *
             */
            Instrumentation instrumentation = ByteBuddyAgent.getInstrumentation();
            try {
                BugFixAgent.agentmain("",instrumentation);
            } catch (Exception e) {
                e.printStackTrace();
            }
            /**-------------------------------------------------*/

        }

        //waitIndefinitely();
    }

    public static void waitIndefinitely() {
        Object lock = new Object();
        try {
            synchronized (lock) {
                lock.wait();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
