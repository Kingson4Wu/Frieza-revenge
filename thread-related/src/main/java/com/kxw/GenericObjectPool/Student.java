package com.kxw.GenericObjectPool;

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
* @see {@link TestGenericObjectPool},
* org.apache.commons.pool.impl.GenericObjectPool,org.apache.commons.pool.PoolableObjectFactory
*/

public class Student {
  private String sex;
  private String name;
  private int studentid;
  private int age;
  public Student() {
  }

  public static void main(String[] args) {
    Student student1 = new Student();

  }

  public String getSex() {
    return sex;
  }

  public void setSex(String sex) {
    this.sex = sex;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getStudentid() {
    return studentid;
  }

  public void setStudentid(int studentid) {
    this.studentid = studentid;
  }

  public void clear() {
    studentid = 0;
    name = "";
    age = 0;
    sex = "";

  }

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }

  public String toString() {
    return "id =" + studentid + ",name =" + name + ",age=" + age + ",sex=" +
        sex;
  }
}