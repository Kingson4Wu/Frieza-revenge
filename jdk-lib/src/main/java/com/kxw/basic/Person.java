package com.kxw.basic;

public class Person implements Comparable{
	private final int id;
	private String name;
	private int age;
	
	public Person(int id,String name,int age){
		this.id = id;
		this.name = name;
		this.age = age;	
	}
	public int getId(){
		return id;	
	}
	public void setName(String name){
		this.name = name;
	}
	public String getName(){
		return name;	
	}
	public void setAge(int age){
		this.age = age;	
	} 
	public int getAge(){
		return age;	
	}
	public String toString(){
		return "Id: " + id + "\tName: " + name + "\tAge: " + age; 	
	}	
	@Override
	public int compareTo(Object o){
		Person p = (Person)o;
		return this.id - p.id;		
	}
	@Override
	public boolean equals(Object o){
		boolean flag = false;
		if(o instanceof Person){
			if(this.id == ((Person)o).id)
				flag = true;
		}
		return false;		
	}	
}