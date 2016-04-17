package com.kxw.basic;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class TestRandomAccessFile{
	private File file; 
	public static void main(String[] args){
		TestRandomAccessFile traf = new TestRandomAccessFile();
		traf.init();
		traf.record("Billy",22);
		traf.listAllRecords();
	}
	
	public void record(String record_breaker, int times){
		try{
			RandomAccessFile raf = new RandomAccessFile(file,"rw");
			boolean flag = false;
			while(raf.getFilePointer() < raf.length()){
				String name = raf.readUTF();
				if(record_breaker.equals(name)){
					raf.writeInt(times);	
					flag = true;
					break;
				}else{
					raf.skipBytes(4);	
				}
			}
			if(!flag){
				raf.writeUTF(record_breaker);
				raf.writeInt(times);	
			}
			raf.close();				
		}catch(Exception e){
			e.printStackTrace();	
		}
	}
	
	public void init(){
		if(file == null){
			file = new File("record.txt");
			try{
				file.createNewFile();
			}catch(IOException e){
				e.printStackTrace();	
			}	
		}	
	}
	
	public void listAllRecords(){
		try{
			RandomAccessFile raf = new RandomAccessFile(file,"r");
			while(raf.getFilePointer() < raf.length()){
				String name = raf.readUTF();
				int times = raf.readInt();
				System.out.println("name:" + name + "\trecord:" + times);
			}
			raf.close();				
		}catch(Exception e){
			e.printStackTrace();	
		}		
	}
}