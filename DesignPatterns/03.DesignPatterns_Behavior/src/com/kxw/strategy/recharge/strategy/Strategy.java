package com.kxw.strategy.recharge.strategy;

import com.kxw.strategy.recharge.example.RechargeTypeEnum;

/** 
 *  
 *作者：alaric 
 *时间：2013-8-5上午11:03:17 
 *描述：策略抽象类 
 */  
public interface Strategy {  
  
    /** 
     *  
     *作者：alaric 
     *时间：2013-8-5上午11:05:11 
     *描述：策略行为方法 
     */  
    public Double calRecharge(Double charge ,RechargeTypeEnum type );  
}  