package com.kxw.Observer.javaAPI;


import java.util.ArrayList;  
import java.util.List;  
import java.util.Observer;  
/** 
*  
*作者：alaric 
*时间：2013-8-13下午9:32:26 
*描述：测试 
*/  
public class Client {  

  /** 
   * @param args 
   */  
  public static void main(String[] args) {  
      UndercoverA o1 = new UndercoverA();  
      UndercoverB o2 = new UndercoverB();  
      List<Observer> list = new ArrayList<>();  
      list.add(o1);  
      list.add(o2);  
      Police subject = new Police(list);  
      subject.change("02:25");  
      System.out.println("===========由于消息败露，行动时间提前=========");  
      subject.change("01:05");  
        
  }  

}  