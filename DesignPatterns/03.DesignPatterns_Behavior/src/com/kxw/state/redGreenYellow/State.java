package com.kxw.state.redGreenYellow;

/** 
 *  
 *作者：alaric 
 *时间：2013-9-7上午11:14:32 
 *描述：抽象状态类 
 */  
public interface State {  
  
    public void change(Light light);  
}  