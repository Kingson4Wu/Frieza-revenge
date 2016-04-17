package com.kxw.strategy.message.strategy;

import com.kxw.strategy.message.bean.User;

public interface ISMSStrategy {  
    //做一件事情，扣钱  
    public void deductMoney(User user);  
}