package com.kxw.Observer.interfaceImplement;

/** 
 *  
 *作者：alaric 
 *时间：2013-8-13下午8:05:55 
 *描述：抽象主题 
 */  
public interface Subject {  
  
    public void attach(Observer observer);  
  
    public void detach(Observer observer);  
  
    void notifyObservers();  
}  