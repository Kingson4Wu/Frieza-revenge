package com.kxw.chainOfResponsibility.pure.concreteHandler;

import com.kxw.chainOfResponsibility.pure.handler.Handler;
/**
 * 
 *初级程序员
 */
public class JuniorProgrammer extends Handler{  
	  
    @Override
	public  void handleRequest(int bugLevel) {  
              
        if(1==bugLevel){  
            System.out.println("bug等级为低级，初级程序员可以完成");  
        }else{  
            //将bug传给中级程序员  
            System.out.println("bug等级太高，初级程序员处理不了，给中级程序员");  
            getNextHandler().handleRequest(bugLevel);  
        }  
        System.out.println("------------------------");  
    }  
  
}  