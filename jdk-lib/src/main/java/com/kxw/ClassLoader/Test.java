package com.kxw.ClassLoader;


//import com.sun.java.accessibility.AccessBridge;
//import org.easymock.EasyMock;

/**
 * Created by Kingson.wu on 2015/8/27.
 */
public class Test {

     public static void main( String[] args ){

         ClassLoader cl = Thread.currentThread().getContextClassLoader();
         System.out.println(cl.toString());

         System.out.println("Test:"+ Test.class.getClassLoader());

         System.out.println("Test's parent :"+Test.class.getClassLoader().getParent());

         System.out.println(Test.class.getClassLoader().getParent().getParent());

         /** BootstrapLoader只会加载核心API里的类，它匹配到核心API(JAVA_HOME\jre\lib)里的String类 */
         System.out.println("String :" + String.class.getClassLoader());
         /** ExtClassLoader (JAVA_HOME\jre\lib\ext\)*/
         //System.out.println("AccessBridge:" +  AccessBridge.class.getClassLoader());
         /**AppClassLoader (java.class.path)*/
         //System.out.println("EasyMock :" + EasyMock.class.getClassLoader());

         /**vm查找类，不仅仅是按名字找，还有包，如果包不同，也视为为同的class，如果package+class都相同，则根据classpath的设置顺序，前面的优先加载，一旦前面的被加载，后面的就再也不会被加载了 */
         System.out.println(System.getProperty("java.class.path"));
     }
}
