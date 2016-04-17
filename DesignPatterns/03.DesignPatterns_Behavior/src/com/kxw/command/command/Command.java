package com.kxw.command.command;

/** 
 *  
 *作者：alaric 
 *时间：2013-8-20下午10:20:35 
 *描述：命令接口 
 */  
public interface Command {  
  
    //执行方法  
    public void execute();  
      
    //这里还可以加入撤销方法，回滚方法等  
}  