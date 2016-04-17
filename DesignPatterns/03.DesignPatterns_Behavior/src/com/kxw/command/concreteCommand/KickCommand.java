package com.kxw.command.concreteCommand;

import com.kxw.command.command.Command;
import com.kxw.command.receiver.GameMachine;

/** 
 *  
 *作者：alaric 
 *时间：2013-8-21上午7:42:21 
 *描述：脚踢命令 
 */  
public class KickCommand implements Command {  
    private GameMachine machine;  
      
    public KickCommand(GameMachine machine) {  
        super();  
        this.machine = machine;  
    }  
  
    @Override  
    public void execute() {  
        machine.kick();  
    }  
  
} 