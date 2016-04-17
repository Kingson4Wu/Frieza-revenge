package com.kxw.Observer.javaAPI;

import java.util.Observable;  
import java.util.Observer;  
/** 
 *  
 *作者：alaric 
 *时间：2013-8-13下午9:33:14 
 *描述：卧底B 
 */  
public class UndercoverB implements Observer {  
    private String time;  
    @Override  
    public void update(Observable o, Object arg) {  
        time = (String) arg;  
        System.out.println("卧底B接到消息，行动时间为："+time);  
    }  
  
  
  
}  