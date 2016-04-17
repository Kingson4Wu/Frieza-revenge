package com.kxw.state.openClose;

 
/** 
 *  
 *作者：alaric 
 *时间：2013-9-3下午10:13:02 
 *描述：实现状态类 
 */  
public class CloseState implements State {  
  
    @Override  
    public void change(Context context) {  
        System.out.println("this is CloseState");  
        context.setState(new OpenState());  
    }  
}  