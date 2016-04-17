package com.kxw.memento.white;

/** 
 *  
 *作者：alaric 
 *时间：2013-8-25下午2:04:27 
 *描述：备忘录角色 
 */  
public class Memento {  
  
    private String state;  
      
    public Memento(String state){  
        this.state = state;  
    }  
  
    public String getState() {  
        return state;  
    }  
  
    public void setState(String state) {  
        this.state = state;  
    }     
      
}  