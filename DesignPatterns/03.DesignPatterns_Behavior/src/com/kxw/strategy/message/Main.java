package com.kxw.strategy.message;

import com.kxw.strategy.message.bean.User;
import com.kxw.strategy.message.concreteStrategy.EasyownStrategy;
import com.kxw.strategy.message.concreteStrategy.GSMSMSStrategy;
import com.kxw.strategy.message.strategy.ISMSStrategy;

public class Main {  
    
    public static void main(String[] args) {  
        Balance balance = new Balance();  
        //定义 一个神州行的用户  
        ISMSStrategy easyownStrategy =  new EasyownStrategy();  
        User easyownUser = new User();  
        easyownUser.setMoney(1000);  
        //办理了一个神州行短信套餐  
        easyownUser.setSmsStrategy(easyownStrategy);  
        System.err.println("神州行的用户到了月末结算的时候");  
        //到了月末结算  
        balance.deductMoney(easyownUser);  
        // 定义 一个全球通的用户  
        ISMSStrategy GSMStrategy = new GSMSMSStrategy();  
        User gsmUser = new User();  
        gsmUser.setMoney(1000);  
        // 办理了一个全球通短信套餐  
        gsmUser.setSmsStrategy(GSMStrategy);  
        System.err.println("全球通的用户到了月末结算的时候");  
        // 到了月末结算  
        balance.deductMoney(gsmUser);  
    }  //如果哪天全球通的资费需要做修改，只需要修改GSMSMSStrategy类里面的deductMoney(User user)方法，User类是不用修改的
}  