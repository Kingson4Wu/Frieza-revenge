package com.kxw.chainOfResponsibility.pure.handler;

/** 
 *  处理器 
 */  
public abstract class Handler {  
      
    private Handler nextHandler;//下一个处理者  
  
    public Handler getNextHandler() {  
        return nextHandler;  
    }  
  
    public void setNextHandler(Handler nextHandler) {  
        this.nextHandler = nextHandler;  
    }  
      
    public abstract void handleRequest(int bugLevel); //处理请求的方法  
  
} 