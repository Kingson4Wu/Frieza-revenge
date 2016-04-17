package com.kxw.Observer.javaAPI;


import java.util.Observable;  
import java.util.Observer;  
/** 
*  
*作者：alaric 
*时间：2013-8-13下午9:32:59 
*描述：卧底A 
*/  
public class UndercoverA implements Observer {  

  private String time;  
  @Override  
  public void update(Observable o, Object arg) {  
      time = (String) arg;  
      System.out.println("卧底A接到消息，行动时间为："+time);  
  }  


}  