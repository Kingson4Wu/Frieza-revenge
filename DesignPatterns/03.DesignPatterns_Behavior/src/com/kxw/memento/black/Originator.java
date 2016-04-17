package com.kxw.memento.black;

/** 
 *  
 *作者：alaric 
 *时间：2013-8-25下午2:48:32 
 *描述：发起人 
 */  
public class Originator {  
  
    private String state;  
      
    /** 
     *  
     *作者：alaric 
     *时间：2013-8-25下午6:18:36 
     *描述：创建备忘录 
     */  
    public IMemento createMemento(){  
        return (IMemento) new Memento(state);  
    }  
      
    /** 
     *  
     *作者：alaric 
     *时间：2013-8-25下午4:05:39 
     *描述：还原 
     */  
    public void restoreMemento(IMemento memento){  
        Memento m = (Memento) memento;  
        setState(m.getState());  
    }  
  
    public String getState() {  
        return state;  
    }  
  
    public void setState(String state) {  
        this.state = state;  
        System.out.println("current state:"+state);  
    }  
    /** 
     *  
     *作者：alaric 
     *时间：2013-8-25下午9:22:02 
     *描述：内部类 
     */  
    public class Memento implements IMemento{  
  
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
}