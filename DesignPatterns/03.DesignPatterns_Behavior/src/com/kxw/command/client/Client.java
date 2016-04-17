package com.kxw.command.client;

import com.kxw.command.command.Command;
import com.kxw.command.concreteCommand.FistCommand;
import com.kxw.command.concreteCommand.KickCommand;
import com.kxw.command.concreteCommand.MoveCommand;
import com.kxw.command.invoke.Control;
import com.kxw.command.receiver.GameMachine;

/**
 * 作者：alaric
 * 时间：2013-8-20下午9:26:42
 * 描述：客户端角色
 */
public class Client {

    /**
     * 作者：alaric
     * 时间：2013-8-20下午9:26:36
     * 描述：测试
     */
    public static void main(String[] args) {

        GameMachine machine = new GameMachine();
        Command fistCommand = new FistCommand(machine);
        Command kickCommand = new KickCommand(machine);
        Command moveCommand = new MoveCommand(machine, "左");

        Control control = new Control(fistCommand, kickCommand, moveCommand);
        control.fist();
        control.kick();
        control.move();

        //其实在不同命令模式的情况下就是下面这样直接调用，  
        //就会让调用者和实际命令执行者紧紧耦合在一起，还有一个好处  
        //就是可以在  
        //machine.fist();  
        //machine.kick();  
        //machine.move("左");  
    }

}  