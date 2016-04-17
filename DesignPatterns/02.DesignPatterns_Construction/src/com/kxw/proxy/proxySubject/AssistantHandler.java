package com.kxw.proxy.proxySubject;

import java.lang.reflect.InvocationHandler;  
import java.lang.reflect.Method;  
import java.lang.reflect.Proxy;  
/** 
 *  
 *作者：alaric 
 *时间：2013-7-24下午10:46:04 
 *描述：动态代理的Handler 
 */  
public class AssistantHandler implements InvocationHandler {  
      
    /** 
     * 目标对象 
     */  
    private Object targetObject;    
        
     
    /** 
     *  
     *作者：alaric 
     *时间：2013-7-24下午10:46:59 
     *描述：创建代理对象 这段也可以不在此类，也可以放在客户端里面 
     */  
    public Object createProxy(Object targetOjbect){    
        this.targetObject = targetOjbect;    
        return Proxy.newProxyInstance(targetOjbect.getClass().getClassLoader(),    
                targetOjbect.getClass().getInterfaces(), this);    
    };    
    
   
    /** 
     * 此方法为必须实现的，在代理实例上处理方法调用并返回结果。在与方法关联的代理实例上调用方法时，将在调用处理程序上调用此方法。  
     */  
    @Override    
    public Object invoke(Object proxy, Method method, Object[] args)    
            throws Throwable {    
        Object result = null;    
        System.out.println("递给领导");  
        result = method.invoke(this.targetObject, args);    
        System.out.println("装入袋子，送出");  
        return result;    
    }    
  
}  