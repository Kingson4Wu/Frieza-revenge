package com.kxw.interator.concreteContainer;

import java.util.ArrayList;
import java.util.List;

import com.kxw.interator.concreteIterator.ConcreteIterator;
import com.kxw.interator.container.Aggregate;
import com.kxw.interator.iterator.Iterator;
  
public class ConcreteAggregate <T>implements Aggregate<T>{  
 private List <T>list = new ArrayList<T>();  
 @Override
 public void add(T obj) {
   list.add(obj);  
 }  
  
 @Override
 public Iterator<T> iterator() {
     return new ConcreteIterator<T>(list);  
 }  
  
 @Override
 public void remove(Object obj) {
     list.remove(obj);  
 }  
}  