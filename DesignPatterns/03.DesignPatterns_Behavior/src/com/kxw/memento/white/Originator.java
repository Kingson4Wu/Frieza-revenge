package com.kxw.memento.white;

/** 
 *  
 *作者：alaric 
 *时间：2013-8-25下午2:48:32 
 *描述：发起人 
 */  
public class Originator {  
  
    private String state;  
      
    public Memento createMemento(){  
        return new Memento(state);  
    }  
      
    /** 
     *  
     *作者：alaric 
     *时间：2013-8-25下午4:05:39 
     *描述：还原 
     */  
    public void restoreMemento(Memento memento){  
        this.state = memento.getState();  
    }  
  
    public String getState() {  
        return state;  
    }  
  
    public void setState(String state) {  
        this.state = state;  
    }  
}