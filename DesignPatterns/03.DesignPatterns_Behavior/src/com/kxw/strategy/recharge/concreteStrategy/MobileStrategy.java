package com.kxw.strategy.recharge.concreteStrategy;

import com.kxw.strategy.recharge.example.RechargeTypeEnum;
import com.kxw.strategy.recharge.strategy.Strategy;

/** 
 *  
 *作者：alaric 
 *时间：2013-8-5上午11:14:43 
 *描述：手机充值 
 */  
public class MobileStrategy implements Strategy {  
  
    @Override  
    public Double calRecharge(Double charge, RechargeTypeEnum type) {  
        // TODO Auto-generated method stub  
        return charge;  
    }  
  
}  