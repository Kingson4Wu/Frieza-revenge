package com.kxw.interator.container;

import com.kxw.interator.iterator.Iterator;

public interface Aggregate<T> {  
	 public void add(T obj);  
	 public void remove(T obj);  
	 public Iterator<T> iterator();  
	}  