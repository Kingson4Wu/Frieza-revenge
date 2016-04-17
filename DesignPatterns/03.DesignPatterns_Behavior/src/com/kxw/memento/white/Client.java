package com.kxw.memento.white;

/** 
 *  
 *作者：alaric 
 *时间：2013-8-25下午2:03:49 
 *描述：测试类 
 */  
public class Client {  
  
    private static Originator o = new Originator();  
    private static Caretaker c = new Caretaker();  
      
    /** 
     *作者：alaric 
     *时间：2013-8-25下午2:03:43 
     *描述： 
     */  
    public static void main(String[] args) {  
        //改变发起人的状态  
        o.setState("on");  
        //创建备忘录对象，并保持于管理保持  
        c.saveMemento(o.createMemento());  
        //改变发起人的状态  
        o.setState("off");  
        //还原状态  
        o.restoreMemento(c.retrieveMemento());  
    }  
  
}  