package com.kxw.Observer.abstractExtends;

public class Main {  
    
    public static void main(String[] args) {  
        //定义多个观察者  
        Observer miui = new ConcreteObserver("小米天气");  
        Observer hammer = new ConcreteObserver("锤子天气");  
      
        //订阅天气主题   
        ConcreteSubject subject = new ConcreteSubject();  
        subject.addObserver(miui);  
        subject.addObserver(hammer);  
          
        //上午天气状况是这样的  
        WeatherInfo weatherInfo = new WeatherInfo(38,204);  
          
        //通知观察者更新天气  
        subject.setSubjectstatus("1");  
        subject.nofity(weatherInfo);  
          
        //下午天气温度发生了变化  
        weatherInfo.setTemperature(12);  
          
        //通知观察者更新天气,并且此时小米天气取消了订阅  
        System.out.println("-----小米取消订阅后-----");  
        subject.removeObserver(miui);  
        subject.setSubjectstatus("1");  
        subject.nofity(weatherInfo);  
    }  
}  