package com.kxw.Observer.javaAPI;


import java.util.List;  
import java.util.Observable;  
import java.util.Observer;  
/** 
*  
*作者：alaric 
*时间：2013-8-13下午9:32:40 
*描述：警察张三 
*/  
public class Police extends Observable {  

  private String time ;  
  public Police(List<Observer> list) {  
      super();  
      for (Observer o:list) {  
          addObserver(o);  
      }  
  }  
  public void change(String time){  
      this.time = time;  
      setChanged();  
      notifyObservers(this.time);  
  }  
}  