package com.kxw.basic;

import java.io.*;
import java.awt.event.*;
import javax.swing.*;

public class TestTempFile implements ActionListener{
	private File tempPath;
	public static void main(String[] args){
		TestTempFile ttf = new TestTempFile();
		ttf.init();
		ttf.createUI();
	}

	public void createUI(){
		JFrame frame = new JFrame();
		JButton jb = new JButton("创建临时文件");
		jb.addActionListener(this);
		frame.add(jb,"North");
		frame.setSize(200,100);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

	public void init(){
		tempPath = new File("temp");
		if(!tempPath.exists() || !tempPath.isDirectory()){
			tempPath.mkdir();  //如果不存在，则创建该文件夹
		}
	}
	@Override
	public void actionPerformed(ActionEvent e){
		try	{
			//在tempPath路径下创建临时文件"mytempfileXXXX.tmp"
			//XXXX 是系统自动产生的随机数, tempPath对应的路径应事先存在
			File tempFile=File.createTempFile("mytempfile",".txt",null);
			System.out.println(tempFile.getAbsolutePath());

			FileWriter fout=new FileWriter(tempFile);
			PrintWriter out=new PrintWriter(fout);
			out.println("some info!" );
			out.close(); //注意：如无此关闭语句，文件将不能删除

			//tempFile.delete(); 
			tempFile.deleteOnExit();
		}
		catch(IOException e1){
			System.out.println(e1);
		}
	}
}

