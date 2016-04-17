package com.kxw.command.receiver;

/** 
 *  
 *作者：alaric 
 *时间：2013-8-21上午7:15:53 
 *描述：接收者 
 */  
public class GameMachine {  
  
    public void fist() {  
        System.out.println("出拳");  
    }  
      
    public void kick() {  
        System.out.println("出脚");  
    }  
      
    public void move(String direction){  
        System.out.println("向"+direction+"移动");  
    }  
  
}  