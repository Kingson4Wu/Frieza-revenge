//: net/mindview/util/Stack.java
// Making a stack from a LinkedList.
package com.kxw.thinkinjava;
import java.util.LinkedList;

public class Stack<T> {
  private LinkedList<T> storage = new LinkedList<T>();
  public void push(T v) { storage.addFirst(v); }
  public T peek() { return storage.getFirst(); }
  public T pop() { return storage.removeFirst(); }
  public boolean empty() { return storage.isEmpty(); }
  @Override
  public String toString() { return storage.toString(); }
} ///:~
