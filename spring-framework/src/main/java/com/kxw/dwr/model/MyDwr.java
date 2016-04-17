package com.kxw.dwr.model;


import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.directwebremoting.WebContext;
import org.directwebremoting.WebContextFactory;
import org.directwebremoting.guice.spring.WebApplicationContextLoader;

public class MyDwr {

	public String hello(String world){
		
		System.out.println("hello"+world);
		
		return "hello"+world;
	}
	
	
	public User load(){
		
		User user=new User(10,"Messi",new Group(1,"Bacelona FC"));
		
		return user;
		
	}
	
	
	public List<User> list(){
		
		List<User> users=new ArrayList<User>();
		users.add(new User(7,"Ronaldo",new Group(3,"Real Madrid")));
		users.add(new User(11,"Ozil",new Group(8,"Asenal")));
		users.add(new User(20,"Van Persie",new Group(2,"Manchester United")));
		users.add(new User(9,"Torress",new Group(5,"Chelsea")));
		
		return users;
	}
	
	public void add(User user){
		
		System.out.println(user);
	}
	
	public void deleteUser(){
		throw new MyException("删除用户！！");
	}
	
	public int add(int a,int b){
		
		return a+b;
	}
	
	public String upload(InputStream is,String filename){
	
		/*String fn=FilenameUtils.getName(filename);
	File file=	new File("");
	System.out.println(file.getAbsolutePath()+"\\"+fn);
	try {
			FileUtils.copyInputStreamToFile(is, new File(file.getAbsolutePath()+"\\"+fn));
		} catch (IOException e) {

			e.printStackTrace();
		}
		return fn;*/
		
		WebContext wc=WebContextFactory.get();
		HttpServletRequest request=wc.getHttpServletRequest();
		String realPath=request.getSession().getServletContext().getRealPath("upload");
		String fn=FilenameUtils.getName(filename);
		String filePath=realPath+"/"+fn;
		try {
			FileUtils.copyInputStreamToFile(is, new File(filePath));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return filePath;
	}
	
}
