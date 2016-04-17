package com.kxw.command.concreteCommand;

import com.kxw.command.command.Command;
import com.kxw.command.receiver.GameMachine;

/** 
 *  
 *作者：alaric 
 *时间：2013-8-21上午7:17:02 
 *描述：移动命令 
 */  
public class MoveCommand implements Command {  
    private GameMachine machine;  
    private String direction;  
      
      
    public MoveCommand(GameMachine machine,String direction) {  
        super();  
        this.machine = machine;  
        this.direction = direction;  
    }  
  
  
  
    @Override  
    public void execute() {  
        machine.move(direction);  
    }  
  
      
  
}  