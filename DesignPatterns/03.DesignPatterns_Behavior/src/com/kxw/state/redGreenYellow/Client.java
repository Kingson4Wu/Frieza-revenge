package com.kxw.state.redGreenYellow;

/** 
 *  
 *作者：alaric 
 *时间：2013-9-7上午11:27:41 
 *描述：测试客户端 
 */  
public class Client {  
  
    /** 
     *作者：alaric 
     *时间：2013-9-7上午11:27:34 
     *描述： 
     */  
    public static void main(String[] args) {  
          
        //假设路灯开始是绿灯  
        State state = new GreenState();  
        Light light = new Light(state);  
        light.work();  
                  
    }  
  
}  