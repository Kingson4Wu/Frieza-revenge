package com.kxw.command.invoke;

import com.kxw.command.command.Command;

/** 
 *  
 *作者：alaric 
 *时间：2013-8-21上午7:43:14 
 *描述：控制类 
 */  
public class Control {  
      
    private Command fistCommand;  
    private Command kickCommand;  
    private Command moveCommand;  
      
    public Control(Command fistCommand, Command kickCommand, Command moveCommand) {  
        super();  
        this.fistCommand = fistCommand;  
        this.kickCommand = kickCommand;  
        this.moveCommand = moveCommand;  
    }  
      
    public void fist(){  
        fistCommand.execute();  
    }  
      
    public void kick(){  
        kickCommand.execute();  
    }  
      
    public void move(){  
        moveCommand.execute();  
    }  
  
}  