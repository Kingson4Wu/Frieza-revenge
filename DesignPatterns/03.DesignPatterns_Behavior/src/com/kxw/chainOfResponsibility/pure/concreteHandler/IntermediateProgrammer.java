package com.kxw.chainOfResponsibility.pure.concreteHandler;

import com.kxw.chainOfResponsibility.pure.handler.Handler;
/**
 * 
 *中级程序员
 */
public class IntermediateProgrammer extends Handler{  
	  
    @Override  
    public void handleRequest(int bugLevel) {  
        if(2==bugLevel){  
            System.out.println("bug等级中级，中级程序员可以完成");  
        }else{  
            //将bug传给高级程序员  
            System.out.println("bug等级太高，中级程序员处理不了，给高级程序员");  
            getNextHandler().handleRequest(bugLevel);  
        }  
        System.out.println("------------------------");  
    }  
  
}  