package com.kxw.Observer.abstractExtends;

import java.util.ArrayList;
import java.util.List;

import com.kxw.Observer.abstractExtends.*;

/** 
 *  主题类 
 *  
 * @author wangkechao.dream 
 * 
 */  
public abstract class Subject {  
      
    private List<Observer> observers = new ArrayList<Observer>();//注册的观察者  
      
    //增加一个观察者  
     public void addObserver(Observer observer){  
         observers.add(observer);  
     }  
     //删除一个观察者  
      public void removeObserver(Observer observer){  
          observers.remove(observer);  
      }  
      //通知观察者  
      public void nofity(WeatherInfo weatherInfo){  
          for(Observer observer:observers){  
              observer.update(weatherInfo);  
          }  
      }  
}  
