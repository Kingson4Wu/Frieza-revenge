package com.kxw.interator.concreteIterator;

import java.util.ArrayList;
import java.util.List;

import com.kxw.interator.iterator.Iterator;
  
public class ConcreteIterator<T> implements Iterator<T> {  
  
  private List<T> list = new ArrayList<T> ();  
  private int cursor =0;  
  public ConcreteIterator(List<T> list){  
   this.list = list;  
  }  
  @Override
  public boolean hasNext() {
   if(cursor==list.size()){  
    return false;  
   }  
   return true;  
  }  
  @Override
  public T next() {
   T obj = null;  
   if(this.hasNext()){  
    obj = this.list.get(cursor++);  
   }  
   return obj;  
  }  
}  