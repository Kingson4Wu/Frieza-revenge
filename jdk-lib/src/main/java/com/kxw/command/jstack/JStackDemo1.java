package com.kxw.command.jstack;

/**
 * @author hollis
 */
public class JStackDemo1 {
    public static void main(String[] args) {
        while (true) {
            //Do Nothing
        }
    }

    /**
     *
     "main" #1 prio=5 os_prio=0 tid=0x000000000271e800 nid=0x5ab8 runnable [0x0000000002aae000]
     java.lang.Thread.State: RUNNABLE
     at com.kxw.command.jstack.JStackDemo1.main(JStackDemo1.java:8)
     at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
     at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
     at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
     at java.lang.reflect.Method.invoke(Method.java:498)
     at com.intellij.rt.execution.application.AppMain.main(AppMain.java:147)
     */
}