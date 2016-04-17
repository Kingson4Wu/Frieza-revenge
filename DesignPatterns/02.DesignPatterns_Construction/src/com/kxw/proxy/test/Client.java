package com.kxw.proxy.test;

import java.lang.reflect.Proxy;

import com.kxw.proxy.proxySubject.Assistant;
import com.kxw.proxy.proxySubject.AssistantHandler;
import com.kxw.proxy.realSubject.CEO;
import com.kxw.proxy.subject.Leader;

/** 
 *  
 *作者：alaric 
 *时间：2013-7-24下午10:44:37 
 *描述：测试类  包括静态代理和动态代理 
 */  
public class Client {  
  
    /** 
     * @param args 
     */  
    public static void main(String[] args) {  
        //静态代理测试  
        CEO ceo = new CEO();  
        Leader leader1 = new Assistant(ceo);  
        leader1.sign();  
          
        System.out.println("====================2=====================");  
        //动态代理测试，一些三种方式都可以获得动态代理对象  
        AssistantHandler ah = new AssistantHandler();  
        Leader leader2 = (Leader) ah.createProxy(ceo);  
        leader2.sign();  
        System.out.println("—— —— ——— —— ——— ——— 3—— ——— ——— ——— ———— —"); 
        Leader leader3 = (Leader) Proxy.newProxyInstance(CEO.class.getClassLoader(),    
              ceo.getClass().getInterfaces(), ah);    
        leader3.sign();  
        System.out.println("—— ——— ——— —— ——— —— 4— —— —— ——— —— ——— ——");
        Leader leader4 = (Leader) Proxy.newProxyInstance(Leader.class.getClassLoader(),  
                 new Class[] { Leader.class },  ah);  
        leader4.sign();  
          
    }  
  
}  