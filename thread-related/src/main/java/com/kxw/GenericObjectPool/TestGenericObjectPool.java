package com.kxw.GenericObjectPool;


import org.apache.commons.pool.impl.*;
import org.apache.commons.pool.*;

/**
 * <p>Title: 基本对象池(org.apache.commons.pool.impl.GenericObjectPool)的使用 </p>
 * <p>Description:
 * 测试  org.apache.commons.pool.impl.GenericObjectPool 和 org.apache.commons.pool.PoolableObjectFactory的使用.</p>
 * <p>基本对象池的使用,
 * <LI>class TestGenericObjectPool 表示一个测试使用对象池的具体例子
 * <LI>class  WdzPoolableObjectFactory  表示1个自己定义的生成对象的工厂
 * <LI>class  Student 表示 需要使用对象池来维护的类</p>
 * <p>
 * 引用了 common-collcetions-2.1 ，commons-pool-1.1
 * </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: netsky</p>
 * @author wdz( hotmail =wdz123@hotmail.com)
 * @version 1.0
 */

public class TestGenericObjectPool {
  private void testBorrowObject(GenericObjectPool pool, int kk) {
    try {
      Student[] student = new Student[10];
      for (int i = 0; i < 10; i++) {
        student[i] = (Student) (pool.borrowObject());
        student[i].setStudentid(i * 10000 + kk);
        student[i].setAge(i * 1000 + kk);
        student[i].setName("wdzname" + i * 100 + kk);
      }
      for (int i = 0; i < 10; i++) {
        System.out.println("student[" + i + "]=" + student[i]);
        pool.returnObject(student[i]);
        if (i == 9) {
          //察看对象回到对象池的状态
          System.out.println("****student[9]=" + student[9]);
        }
      }

    }
    catch (Exception ex) {
      ex.printStackTrace();
    }

  }

  public TestGenericObjectPool() {
    org.apache.commons.pool.PoolableObjectFactory factory = new
        WdzPoolableObjectFactory();
    GenericObjectPool pool = new GenericObjectPool(factory, 4000 * 10,
        GenericObjectPool.WHEN_EXHAUSTED_GROW, 3000 * 10, true, true);
    System.out.println("group1");
    testBorrowObject(pool, 1);
    System.out.println("group2");
    testBorrowObject(pool, 2);

  }

  public static void main(String[] args) {
    TestGenericObjectPool test = new TestGenericObjectPool();
  }
}
