package com.kxw.strategy.message.concreteStrategy;

import com.kxw.strategy.message.bean.User;
import com.kxw.strategy.message.strategy.ISMSStrategy;

public class GSMSMSStrategy implements  ISMSStrategy{  
    
    //全球通-100块钱  
    public void deductMoney(User user){  
        System.out.println("全球通执行策略以前"+user.getMoney());  
        user.setMoney(user.getMoney()-200);  
        System.out.println("全球通执行策略以后"+user.getMoney());  
    }  
}  