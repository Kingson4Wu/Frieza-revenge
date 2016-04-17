package com.kxw.strategy.recharge.concreteStrategy;

import com.kxw.strategy.recharge.example.RechargeTypeEnum;
import com.kxw.strategy.recharge.strategy.Strategy;

/** 
 *  
 *作者：alaric 
 *时间：2013-8-5上午11:14:23 
 *描述：网银充值 
 */  
public class EBankStrategy implements Strategy{  
  
    @Override  
    public Double calRecharge(Double charge, RechargeTypeEnum type) {  
        return charge*0.85;  
    }  
  
      
  
}  