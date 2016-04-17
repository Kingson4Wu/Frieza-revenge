package com.kxw.dwr.model;

public class Group {

	private int id;
	private String name;
	
	
	
	public Group(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	
	
	public Group() {
		super();
	}


	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}


	@Override
	public String toString() {
		return "Group [id=" + id + ", name=" + name + "]";
	}

	
}
