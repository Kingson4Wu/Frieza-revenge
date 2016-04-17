package com.kxw.memento.black.DataBaseExample;

import java.util.Map;  

/** 
 *  
 *作者：alaric 
 *时间：2013-8-25下午2:03:49 
 *描述：测试类 
 */  
public class Client {  
  
      
    private static DatabaseServer dbServer = new DatabaseServer();  
    private static BackupsServer backupServer = new BackupsServer(dbServer);  
      
    /** 
     *作者：alaric 
     *时间：2013-8-25下午2:03:43 
     *描述： 
     * @throws InterruptedException  
     */  
    public static void main(String[] args) throws InterruptedException {  
        //数据库系统设置可用状态  
        dbServer.setUseable(true);  
        //备份  
        backupServer.createMemento();  
          
        //1秒钟备份一次  
        Thread.sleep(1000);  
        dbServer.setUseable(true);  
        backupServer.createMemento();  
          
        Thread.sleep(1000);  
        dbServer.setUseable(true);  
        backupServer.createMemento();  
          
        Thread.sleep(1000);  
        //设置系统故障  
        dbServer.setUseable(false);  
        //系统故障立即还原到最近一次可用状态  
        System.out.println("------系统还原-----");  
        backupServer.retrieveMemento();  
    }  
  
}  