package com.kxw.proxy.realSubject;

import com.kxw.proxy.subject.Leader;

/** 
 *  
 *作者：alaric 
 *时间：2013-7-24下午10:45:05 
 *描述：ceo  真实主题 
 */  
public class CEO implements Leader {  
  
    @Override  
    public void sign() {  
        System.out.println("CEO签文件");  
    }  
  
}  