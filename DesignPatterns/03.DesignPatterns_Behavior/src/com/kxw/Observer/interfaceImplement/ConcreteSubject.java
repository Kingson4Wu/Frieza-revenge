package com.kxw.Observer.interfaceImplement;

import java.util.Enumeration;  
import java.util.Vector;  
/** 
 *  
 *作者：alaric 
 *时间：2013-8-13下午8:09:21 
 *描述：具体主题类 
 */  
public class ConcreteSubject implements Subject {  
    private Vector<Observer>observersVector = new Vector<Observer>();  
    public void attach(Observer observer) {  
        observersVector.addElement(observer);  
    }  
  
    public void detach(Observer observer) {  
        observersVector.removeElement(observer);  
    }  
  
    public void notifyObservers() {  
        Enumeration<Observer>enumeration = observers();  
        while (enumeration.hasMoreElements()) {  
            ((Observer) enumeration.nextElement()).update();  
        }  
    }  
  
    @SuppressWarnings("unchecked")  
    public Enumeration<Observer> observers() {  
        return ((Vector<Observer>) observersVector.clone()).elements();  
    }  
  
}   