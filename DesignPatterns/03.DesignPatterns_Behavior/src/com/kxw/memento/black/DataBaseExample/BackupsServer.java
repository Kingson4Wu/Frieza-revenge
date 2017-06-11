package com.kxw.memento.black.DataBaseExample;


import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;  
 
/** 
*  
*作者：alaric 
*时间：2013-8-25下午2:48:05 
*描述：备份服务器（管理者） 
*/  
public class BackupsServer {  
   private DatabaseServer dbServer ;//增强管理者的功能，把发起人的操作放在这里  
   private Map<Long,IMemento> mementos ;//用一个map来对数据库服务多点备份  
     
   public BackupsServer(DatabaseServer dbServer) {  
       super();  
       this.dbServer = dbServer;  
       mementos = new ConcurrentHashMap<>();  
   }  
     
   /** 
    *  
    *作者：alaric 
    *时间：2013-8-25下午3:47:18 
    *描述：还原 
    */  
   public void retrieveMemento(){  
       Iterator<Long> it = mementos.keySet().iterator();  
       //还原到最近一个可用的状态  
       while(it.hasNext()){  
           Long key = it.next();  
           IMemento val = mementos.get(key);  
           boolean isUseable = dbServer.restoreMemento(val);  
           if(isUseable){  
               break;  
           }  
       }  
   }  
   /** 
    *  
    *作者：alaric 
    *时间：2013-8-25下午4:05:01 
    *描述：备份 
    */  
   public void createMemento(){  
       IMemento memento = dbServer.createMemento();  
       this.mementos.put(System.currentTimeMillis(), memento);
   }  
 
 
}