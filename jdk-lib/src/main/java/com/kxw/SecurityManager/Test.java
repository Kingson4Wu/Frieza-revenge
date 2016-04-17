package com.kxw.SecurityManager;

/**
 * Created by Kingson.wu on 2015/8/25.
 */
public class Test {
     public static void main( String[] args ){
         //System.out.println(System.getProperty("java.class.path"));

         /** -Djava.security.manager */
         /**  -Djava.security.policy=my.policy 默认java.proxy*/
        /* SecurityManager sm=new SecurityManager();
         System.setSecurityManager(sm);*/

         System.out.println(System.getProperty("os.name"));

         /**
          * Exception in thread "main" java.security.AccessControlException: access denied ("java.util.PropertyPermission" "java.security.policy" "read")
          Disconnected from the     target VM, address: '127.0.0.1:14831', transport: 'socket'
          at java.security.AccessControlContext.checkPermission(AccessControlContext.java:366)
          at java.security.AccessController.checkPermission(AccessController.java:560)
          at java.lang.SecurityManager.checkPermission(SecurityManager.java:549)
          at java.lang.SecurityManager.checkPropertyAccess(SecurityManager.java:1302)
          at java.lang.System.getProperty(System.java:706)
          at kxw.SecurityManager.Test.main(Test.java:14)
          Process finished with exit code 1
          * */

         /**
          * F:\Program Files\Java\jre7\lib\security\java.proxy 添加
          * permission java.util.PropertyPermission "java.security.policy", "read";
          * 重启计算机？？
          */

         System.out.println(System.getProperty("java.security.policy"));



     }
}
