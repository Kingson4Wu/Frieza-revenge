package com.kxw.strategy.recharge.example;

public class Example {  
	  
    /** 
     *  
     *作者：alaric 
     *时间：2013-8-5上午11:00:06 
     *描述：计算用户所付金额 
     */  
    public Double calRecharge(Double charge ,RechargeTypeEnum type ){  
          
        if(type.equals(RechargeTypeEnum.E_BANK)){  
            return charge*0.85;  
        }else if(type.equals(RechargeTypeEnum.BUSI_ACCOUNTS)){  
            return charge*0.90;  
        }else if(type.equals(RechargeTypeEnum.MOBILE)){  
            return charge;  
        }else if(type.equals(RechargeTypeEnum.CARD_RECHARGE)){  
            return charge+charge*0.01;  
        }else{  
            return null;  
        }  
  
    }  
      
}  