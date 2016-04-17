package com.kxw.visitor.test;

import java.util.ArrayList;
import java.util.List;

import com.kxw.visitor.concreteElement.ConcreteElementA;
import com.kxw.visitor.concreteElement.ConcreteElementB;
import com.kxw.visitor.concreteVisitor.ConcreteVisitorA;
import com.kxw.visitor.concreteVisitor.ConcreteVisitorB;
import com.kxw.visitor.visitable.Visitable;
import com.kxw.visitor.visitor.Visitor;

/**
 * 作者：alaric
 * 时间：2013-9-13下午11:34:22
 * 描述：客户端
 */
public class Client {

    /**
     * @param args
     */
    public static void main(String[] args) {

        Visitor v1 = new ConcreteVisitorA();
        List<Visitable> list = new ArrayList<>();
        list.add(new ConcreteElementA());
        list.add(new ConcreteElementB());

        for (Visitable able : list) {
            able.accept(v1);
        }


        Visitor v2 = new ConcreteVisitorB();
        List<Visitable> list2 = new ArrayList<>();
        list2.add(new ConcreteElementA());
        list2.add(new ConcreteElementB());

        for (Visitable able : list2) {
            able.accept(v2);
        }
    }

}  