package com.kxw.command.jstack;

/**
 * @author hollis
 */
public class JStackDemo2 {
    public static void main(String[] args) {
        Thread thread = new Thread(new Thread1());
        thread.start();
    }
}
class Thread1 implements Runnable{
    @Override
    public void run() {
        while(true){
            System.out.println(1);
        }
    }
}

/**
 "Finalizer" #3 daemon prio=8 os_prio=1 tid=0x0000000007c2e000 nid=0x7348 in Object.wait() [0x0000000018e6e000]
 java.lang.Thread.State: WAITING (on object monitor)
 at java.lang.Object.wait(Native Method)
 - waiting on <0x00000000d929da58> (a java.lang.ref.ReferenceQueue$Lock)
 at java.lang.ref.ReferenceQueue.remove(ReferenceQueue.java:143)
 - locked <0x00000000d929da58> (a java.lang.ref.ReferenceQueue$Lock)
 at java.lang.ref.ReferenceQueue.remove(ReferenceQueue.java:164)
 at java.lang.ref.Finalizer$FinalizerThread.run(Finalizer.java:209)

 "Reference Handler" #2 daemon prio=10 os_prio=2 tid=0x0000000007be5000 nid=0x2e0c in Object.wait() [0x000000001903f000]
 java.lang.Thread.State: WAITING (on object monitor)
 at java.lang.Object.wait(Native Method)
 - waiting on <0x00000000d929dc88> (a java.lang.ref.Reference$Lock)
 at java.lang.Object.wait(Object.java:502)
 at java.lang.ref.Reference.tryHandlePending(Reference.java:191)
 - locked <0x00000000d929dc88> (a java.lang.ref.Reference$Lock)
 at java.lang.ref.Reference$ReferenceHandler.run(Reference.java:153)

 */