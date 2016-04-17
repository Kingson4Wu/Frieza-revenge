package com.kxw.memento.black;

/** 
 *  
 *作者：alaric 
 *时间：2013-8-25下午2:48:05 
 *描述：管理者 
 */  
public class Caretaker {  
  
    private IMemento memento;  
      
    /** 
     *  
     *作者：alaric 
     *时间：2013-8-25下午3:47:18 
     *描述：取值 
     */  
    public IMemento retrieveMemento(){  
        return memento;  
    }  
    /** 
     *  
     *作者：alaric 
     *时间：2013-8-25下午4:05:01 
     *描述：设值 
     */  
    public void saveMemento(IMemento memento){  
        this.memento = memento;  
    }  
}  