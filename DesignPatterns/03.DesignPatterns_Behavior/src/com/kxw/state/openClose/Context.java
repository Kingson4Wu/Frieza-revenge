package com.kxw.state.openClose;

/** 
 *  
 *作者：alaric 
 *时间：2013-9-3下午10:13:20 
 *描述：环境角色类 
 */  
public class Context {  
   
    private State state;  
      
    public void change(){  
        state.change(this);  
    }  
      
  
    public Context(State state) {  
        super();  
        this.state = state;  
    }  
  
  
    public State getState() {  
        return state;  
    }  
  
  
    public void setState(State state) {  
        this.state = state;  
    }  
       
      
}  