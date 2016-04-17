package com.kxw.memento.white;

/** 
 *  
 *作者：alaric 
 *时间：2013-8-25下午2:48:05 
 *描述：管理者 
 */  
public class Caretaker {  
  
    private Memento memento;  
      
    /** 
     *  
     *作者：alaric 
     *时间：2013-8-25下午3:47:18 
     *描述：取值 
     */  
    public Memento retrieveMemento(){  
        return memento;  
    }  
    /** 
     *  
     *作者：alaric 
     *时间：2013-8-25下午4:05:01 
     *描述：设置 
     */  
    public void saveMemento(Memento memento){  
        this.memento = memento;  
    }  
}  