package com.kxw.strategy.recharge;

import com.kxw.strategy.recharge.example.RechargeTypeEnum;
import com.kxw.strategy.recharge.strategy.Strategy;

/** 
 *  
 *作者：alaric 
 *时间：2013-8-5上午11:03:38 
 *描述：场景类 
 */  
public class Context {  
  
    private Strategy strategy;  
      
    public Double calRecharge(Double charge, Integer type) {  
        strategy = StrategyFactory.getInstance().creator(type);  
        return strategy.calRecharge(charge, RechargeTypeEnum.valueOf(type));  
    }  
  
    public Strategy getStrategy() {  
        return strategy;  
    }  
  
    public void setStrategy(Strategy strategy) {  
        this.strategy = strategy;  
    }  
      
}  