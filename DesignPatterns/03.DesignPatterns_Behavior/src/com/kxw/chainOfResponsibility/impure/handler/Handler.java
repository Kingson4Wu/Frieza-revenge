package com.kxw.chainOfResponsibility.impure.handler;

/** 
 *  
 *描述：审批处理抽象类 
 */  
public abstract class Handler {  
  
    protected Handler handler;  
  
    /** 
     *  
     *描述：审批 
     */  
    public abstract int approve(double day,int signCount);  
      
    public Handler getHandler() {  
        return handler;  
    }  
    public void setHandler(Handler handler) {  
        this.handler = handler;  
    }  
      
}  
 