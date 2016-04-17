package com.kxw.fastjson.bean;

public class Person {
	private String id ;
	private String name;
	private int age ;

	public Person(){

	}
	public Person(String id,String name,int age){
		this.id=id;
		this.name=name;
		this.age=age;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	@Override
	public String toString() {
		return "解析json字符串后获取的person对象：id的值是" + id + ", name的值是" + name + ", age的值是" + age + "";
	}

}
