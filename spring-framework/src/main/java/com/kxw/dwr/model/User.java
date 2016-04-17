package com.kxw.dwr.model;

public class User {

	private int id;
	private String username;
	private Group group;
	
	
	
	public User(int id, String username, Group group) {
		super();
		this.id = id;
		this.username = username;
		this.group = group;
	}
	
	
	public User() {
		super();
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Group getGroup() {
		return group;
	}
	public void setGroup(Group group) {
		this.group = group;
	}


	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", group=" + group
				+ "]";
	}


	
	
	
	
}
