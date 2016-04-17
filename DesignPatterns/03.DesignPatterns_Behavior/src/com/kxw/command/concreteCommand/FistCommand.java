package com.kxw.command.concreteCommand;

import com.kxw.command.command.Command;
import com.kxw.command.receiver.GameMachine;

/** 
 *  
 *作者：alaric 
 *时间：2013-8-21上午7:17:02 
 *描述：拳打 
 */  
public class FistCommand implements Command {  
    private GameMachine machine;  
      
      
      
    public FistCommand(GameMachine machine) {  
        super();  
        this.machine = machine;  
    }  
  
  
  
    @Override  
    public void execute() {  
        machine.fist();  
    }  
  
  
}