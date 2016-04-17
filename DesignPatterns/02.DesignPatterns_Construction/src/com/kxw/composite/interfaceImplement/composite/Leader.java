package com.kxw.composite.interfaceImplement.composite;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import com.kxw.composite.interfaceImplement.component.Worker;

/**
 * 作者：alaric
 * 时间：2013-7-20下午5:14:50
 * 描述：领导类
 */
public class Leader implements Worker {
    private List<Worker> workers = new CopyOnWriteArrayList<Worker>();
    private String name;

    public Leader(String name) {
        super();
        this.name = name;
    }

    public void add(Worker worker) {
        workers.add(worker);
    }

    public void remove(Worker worker) {
        workers.remove(worker);
    }

    public Worker getChild(int i) {
        return workers.get(i);
    }

    @Override
    public void doSomething() {
        System.out.println(toString());
        Iterator<Worker> it = workers.iterator();
        while (it.hasNext()) {
            it.next().doSomething();
        }

    }


    @Override
    public String toString() {
        // TODO Auto-generated method stub  
        return "我叫" + getName() + ", 我是一个领导,有 " + workers.size() + "下属。";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}  