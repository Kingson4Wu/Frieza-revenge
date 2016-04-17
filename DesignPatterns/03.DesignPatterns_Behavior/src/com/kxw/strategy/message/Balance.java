package com.kxw.strategy.message;

import com.kxw.strategy.message.bean.User;

public class Balance {  
	   //月末结算了  
	    public void deductMoney(User user){  
	        user.getSmsStrategy().deductMoney(user);  
	    }  
	}  