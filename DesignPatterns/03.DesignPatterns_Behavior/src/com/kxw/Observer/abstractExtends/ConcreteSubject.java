package com.kxw.Observer.abstractExtends;

public class ConcreteSubject extends Subject{  
	  
    
    private String subjectstatus;//主题的状态  
  
    public String getSubjectstatus() {  
        return subjectstatus;  
    }  
  
    public void setSubjectstatus(String subjectstatus) {  
        this.subjectstatus = subjectstatus;  
    }  
      
      
    @Override  
    //1 表示需要更新  
    public void nofity(WeatherInfo weatherInfo) {  
        if("1".equals(subjectstatus)){  
            super.nofity(weatherInfo);  
        }  
    }  
}  