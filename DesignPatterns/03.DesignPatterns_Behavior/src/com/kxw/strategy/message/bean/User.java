package com.kxw.strategy.message.bean;

import com.kxw.strategy.message.strategy.ISMSStrategy;

public class User {  
    public int money;  
    /*实现策略模式的重点在这里*/  
    public ISMSStrategy smsStrategy;//该用户 办理的业务套餐  
  
   
    public int getMoney() {  
        return money;  
    }  
  
    public void setMoney(int money) {  
        this.money = money;  
    }  
  
    public ISMSStrategy getSmsStrategy() {  
        return smsStrategy;  
    }  
  
    public void setSmsStrategy(ISMSStrategy smsStrategy) {  
        this.smsStrategy = smsStrategy;  
    }  
}  