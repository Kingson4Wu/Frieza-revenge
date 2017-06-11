package com.kxw.strategy.message.concreteStrategy;

import com.kxw.strategy.message.bean.User;
import com.kxw.strategy.message.strategy.ISMSStrategy;

public class EasyownStrategy implements ISMSStrategy{  
	  
    //神州行用户-200  
    @Override
    public void deductMoney(User user) {
        System.out.println("神州行执行策略以前"+user.getMoney());  
        user.setMoney(user.getMoney()-100);  
        System.out.println("神州行执行策略以后"+user.getMoney());  
    }  
}  