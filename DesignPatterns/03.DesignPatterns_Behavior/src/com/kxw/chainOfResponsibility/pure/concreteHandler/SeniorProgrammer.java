package com.kxw.chainOfResponsibility.pure.concreteHandler;

import com.kxw.chainOfResponsibility.pure.handler.Handler;
/**
 * 
 *高级程序员
 */
public class SeniorProgrammer extends Handler {  
	  
    @Override  
  public  void handleRequest(int bugLevel) {  
        if (3 == bugLevel) {  
            System.out.println("bug等级高级，大牛亲自出马");  
        } else {  
            // 将bug传给谁  
            if (null != getNextHandler()) {  
                getNextHandler().handleRequest(bugLevel);  
            } else {  
                System.out.println("bug太严重了，大牛也处理不了");  
            }  
        }  
    }  
  
}  