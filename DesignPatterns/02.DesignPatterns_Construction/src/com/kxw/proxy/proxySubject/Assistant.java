package com.kxw.proxy.proxySubject;

import com.kxw.proxy.subject.Leader;

/** 
 *  
 *作者：alaric 
 *时间：2013-7-24下午10:45:25 
 *描述：代理主题 
 */  
public class Assistant implements Leader{  
  
    private Leader leader ;  
      
      
    public Assistant(Leader leader) {  
        super();  
        this.leader = leader;  
    }  
  
  
    @Override  
    public void sign() {  
        System.out.println("递给领导");  
        leader.sign();  
        System.out.println("装入袋子，送出");  
    }  
      
  
}