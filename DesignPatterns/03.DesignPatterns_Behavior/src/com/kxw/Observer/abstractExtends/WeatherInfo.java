package com.kxw.Observer.abstractExtends;

public class WeatherInfo {  
    
    private double temperature;//温度  
      
    private double humidity;//湿度  
  
    public WeatherInfo(double temperature, double humidity) {  
        super();  
        this.temperature = temperature;  
        this.humidity = humidity;  
    }  
  
    public double getTemperature() {  
        return temperature;  
    }  
  
    public void setTemperature(double temperature) {  
        this.temperature = temperature;  
    }  
  
    public double getHumidity() {  
        return humidity;  
    }  
  
    public void setHumidity(double humidity) {  
        this.humidity = humidity;  
    }  
  
}  