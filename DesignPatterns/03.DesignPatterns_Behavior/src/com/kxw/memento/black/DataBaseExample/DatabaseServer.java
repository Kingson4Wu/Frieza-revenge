package com.kxw.memento.black.DataBaseExample;

/** 
 *  
 *作者：alaric 
 *时间：2013-8-25下午2:48:32 
 *描述：数据库系统（发起人角色） 
 */  
public class DatabaseServer {  
  
    private boolean isUseable;  
      
    /** 
     *  
     *作者：alaric 
     *时间：2013-8-25下午6:18:36 
     *描述：穿件备忘录 
     */  
    public IMemento createMemento(){  
        return (IMemento) new Memento(isUseable);  
    }  
      
    /** 
     *  
     *作者：alaric 
     *时间：2013-8-25下午4:05:39 
     *描述：还原 
     */  
    public boolean restoreMemento(IMemento memento){  
        Memento m = (Memento) memento;  
        setUseable(m.isUseable());  
        return this.isUseable;  
    }  
  
      
    public boolean isUseable() {  
        return isUseable;  
    }  
  
    public void setUseable(boolean isUseable) {  
        this.isUseable = isUseable;  
        System.out.println("DB state useable is: "+isUseable);  
    }  
  
  
    /** 
     *  
     *作者：alaric 
     *时间：2013-8-25下午9:22:02 
     *描述：内部类 
     */  
    public class Memento implements IMemento{  
  
        private boolean isUseable;  
          
        public Memento(boolean isUseable) {  
            super();  
            this.isUseable = isUseable;  
        }  
  
        public boolean isUseable() {  
            return isUseable;  
        }  
  
        public void setUseable(boolean isUseable) {  
            this.isUseable = isUseable;  
        }  
  
          
    }  
}  