package com.kxw.Observer.abstractExtends;

public class ConcreteObserver extends Observer{  
	  
    private String observerName;  
    public ConcreteObserver(String observerName) {  
        super();  
        this.observerName = observerName;  
    }  
 public   void update(WeatherInfo weatherInfo) {  
        System.out.println("软件"+observerName+"的天气更新为:");  
        System.out.println("温度:"+weatherInfo.getTemperature());  
        System.out.println("湿度:"+weatherInfo.getHumidity());  
    }  
}  