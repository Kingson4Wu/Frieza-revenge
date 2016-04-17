package com.kxw;

import java.io.*;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class Rename {

	private static ArrayList<String> fileList = new ArrayList<String>();




	private ExecutorService executorService;


	private void getFilePath(String dirPath){  //获得目录下的有文件
		File dir = new File(dirPath);
		File[] files = dir.listFiles();
		if(files == null){
			return;
		}
		for(int i = 0; i < files.length; i++){
			if(files[i].isDirectory()){
				getFilePath(files[i].getAbsolutePath());  //如果是目录，递归调用
			}
			else {
				fileList.add(files[i].getAbsolutePath());
			}
		}
	}




	//--------------------------------------------------------------------
	public void prefixRename(String directory,String prefix) {


		getFilePath(directory);
		executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() * 4);

		System.out.println("---------------");

		for(String  filePath : fileList){
			System.out.println(filePath.toString());

			executorService.execute(prefixRenameFile(filePath,prefix));



		}
		executorService.shutdown();

	}

	public void postfixRename(String directory,String postfix) {


		getFilePath(directory);
		executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() * 4);

		System.out.println("---------------");

		for(String  filePath : fileList){
			//System.out.println(filePath.toString());

			executorService.execute(postfixRenameFile(filePath,postfix));



		}
		executorService.shutdown();

	}

	public void replaceRename(String directory,String oldString ,String newString ) {


		getFilePath(directory);
		executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() * 4);

		System.out.println("---------------");

		for(String  filePath : fileList){
			//System.out.println(filePath.toString());

			executorService.execute(replaceRenameFile(filePath,oldString ,newString));



		}
		executorService.shutdown();

	}



	//---------------------------------------------------------------------------------------






	private static Runnable prefixRenameFile(final String filePath,final String prefix){

		return new Runnable(){

			public void run() {

				File file=new File(filePath);

				String filename=file.getName();

				String replaceName=prefix+filename;

				File tempfile=new File(file.getParent()+"\\"+replaceName);

				file.renameTo(tempfile);

			}



		};
	}

	private static Runnable postfixRenameFile(final String filePath,final String postfix){

		return new Runnable(){

			public void run() {

				File file=new File(filePath);

				String filename=file.getName().substring(0, file.getName().lastIndexOf('.'));

				String fileType=file.getName().substring(file.getName().lastIndexOf('.'));

				String replaceName=filename+postfix+fileType;

				System.out.println(replaceName+"***********");

				File tempfile=new File(file.getParent()+"\\"+replaceName);

				file.renameTo(tempfile);

			}



		};
	}


	private static Runnable replaceRenameFile( final String filePath,final String oldString,final String newString){

		return new Runnable(){

			public void run() {

				File file=new File(filePath);

				String filename=file.getName();

				//String replaceString=filename.replace(oldString, newString);
				String replaceString=filename.replaceFirst(oldString, newString);




				System.out.println( replaceString);

				File tempfile=new File(file.getParent()+"\\"+replaceString);

				file.renameTo(tempfile);

			}



		};
	}


	//--------------------------------------------------------------------------------------------------------------

	public static void main(String[] args){
		// new Rename().prefixRename("E:\\rename\\", "miao");//给文件加前缀
		// new Rename().postfixRename("E:\\rename\\", "miao");//给文件加后缀
		new Rename().replaceRename("E:\\rename\\", "kxw","");//替换


	}



}
