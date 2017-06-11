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


public class WdzPoolableObjectFactory
    implements PoolableObjectFactory {

  /**
   * 创建对象实例。同时可以分配这个对象适用的资源。
   * **/
  @Override
  public Object makeObject() throws Exception {
    return new Student();
  };

  /**
   * 销毁对象，实际上提供了释放对象占用资源的接口。
   * destroyObject is invoked on every instance when it is being "dropped"
   *  from the pool (whether due to the response from validateObject,
   *  or for reasons specific to the pool implementation.)
   * */
  @Override
  public void destroyObject(Object obj) throws Exception {
  }

  /***
   * 这个方法一般在 activateObject 方法执行后调用
   * 检查对象的有效性
   * validateObject is invoked in an implementation-specific fashion to
   * determine if an instance is still valid to be returned by the pool.
   * It will only be invoked on an "activated" instance.
   * **/
  @Override
  public boolean validateObject(Object obj) {
    return true;
  }

  /**
   * 激活一个对象。
   * activateObject is invoked on every instance before it is returned from the pool.
   * **/
  @Override
  public void activateObject(Object obj) throws Exception {
  }

  /**
   * 挂起一个对象
       * passivateObject is invoked on every instance when it is returned to the pool
   * **/
  @Override
  public void passivateObject(Object obj) throws Exception {
    if (obj instanceof Student) {
      ( (Student) obj).clear();
    }
  }
}