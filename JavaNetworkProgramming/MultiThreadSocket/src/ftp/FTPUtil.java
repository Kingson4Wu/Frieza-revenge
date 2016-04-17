package ftp;


import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import sun.net.TelnetInputStream;
import sun.net.TelnetOutputStream;
import sun.net.ftp.FtpClient;

/**
 * <p>
 * Title: FTPUtil
 * </p>
 * <p>
 * Description: FTP操作的工具类，通过此类可以完成文件到指定FTP服务器的上传和下载。
 * </p>
 * <p>
 * Copyright: Copyright (c) 2007
 * </p>
 * <p>
 * Company: Routon
 * </p>
 * 
 * @author zhihui zhou
 * @version 1.0 InternalError-->Exception by jiangyl
 * @2007.12.21
 */
public class FTPUtil
{
 
	private FtpClient ftpClient=null;
	/** 
	    * connectServer 
	    * 连接ftp服务器 
	    * @throws java.io.IOException 
	    * @param path 文件夹，空代表根目录 
	    * @param password 密码 
	    * @param user   登陆用户 
	    * @param server 服务器地址 
	    */ 
	  public void connectServer(String server, String user, String password,  String path) 
	  throws IOException  
	  { 
	     // server：FTP服务器的IP地址；user:登录FTP服务器的用户名 
	     // password：登录FTP服务器的用户名的口令；path：FTP服务器上的路径 
	     ftpClient = new FtpClient(); 
	     ftpClient.openServer(server); 
	     ftpClient.login(user, password); 
	     //path是ftp服务下主目录的子目录 
	     if (path.length() != 0)  ftpClient.cd(path); 
	     //用2进制上传、下载 
	     ftpClient.binary();      
	 } 
	  
	 /** 
	    * upload 
	    * 上传文件 
	    * @throws java.lang.Exception 
	    * @return -1 文件不存在 
	    *          -2 文件内容为空  
	    *          >0 成功上传，返回文件的大小 
	    * @param newname 上传后的新文件名 
	    * @param filename 上传的文件 
	    */ 
	 public long upload(String filename,String newname) throws Exception  
	 { 
	     long result = 0; 
	     TelnetOutputStream os = null; //FTP就是用telnet
	     FileInputStream is = null; 
	     try {          
	         java.io.File file_in = new java.io.File(filename); 
	         if (!file_in.exists()) return -1; 
	         if (file_in.length()==0) return -2; 
	         os = ftpClient.put(newname); 
	         result = file_in.length(); 
	         is = new FileInputStream(file_in); 
	         byte[] bytes = new byte[1024]; 
	         int c; 
	         while ((c = is.read(bytes)) != -1) { 
	              os.write(bytes, 0, c); 
	         } 
	     } finally { 
	         if (is != null) { 
	             is.close(); 
	         } 
	         if (os != null) { 
	            os.close(); 
	         } 
	     } 
	    return result; 
	 } 
	 /** 
	    * upload 
	    * @throws java.lang.Exception 
	    * @return  
	    * @param filename 
	    */ 
	 public long upload(String filename) 
	 throws Exception  
	 { 
	    String newname = ""; 
	    if (filename.indexOf("/")>-1) 
	    { 
	       newname = filename.substring(filename.lastIndexOf("/")+1); 
	    }else 
	    { 
	       newname = filename; 
	    } 
	    return upload(filename,newname); //调用上面的重载函数
	 } 
	  
	 /** 
	    *  download 
	    *  从ftp下载文件到本地 
	    * @throws java.lang.Exception 
	    * @return  
	    * @param newfilename 本地生成的文件名 
	    * @param filename 服务器上的文件名 
	    */ 
	 public long download(String filename,String newfilename)  
	 throws Exception 
	 {   
	    long result = 0; 
	    TelnetInputStream is = null; 
	    FileOutputStream os = null; 
	    try  
	    { 
	       is = ftpClient.get(filename);        
	       java.io.File outfile = new java.io.File(newfilename); 
	       os = new FileOutputStream(outfile); 
	       byte[] bytes = new byte[1024]; 
	       int c; 
	       while ((c = is.read(bytes)) != -1) { 
	           os.write(bytes, 0, c); 
	           result = result + c; 
	       } 
	    } catch (IOException e)  
	    { 
	       e.printStackTrace(); 
	    } 
	    finally { 
	         if (is != null) { 
	             is.close(); 
	         } 
	         if (os != null) { 
	            os.close(); 
	         } 
	     } 
	     return result; 
	 } 
	 /** 
	  * 取得某个目录下的所有文件列表 
	  * 
	  */ 
	 public List getFileList(String path) 
	 { 
	    List list = new ArrayList(); 
	    try  
	    { 
	       DataInputStream dis = new  DataInputStream(ftpClient.nameList(path)); 
	       String filename = ""; 
	       while((filename=dis.readLine())!=null)   
	       {   
	         list.add(new String(filename.getBytes("iso8859-1"),"gbk"));         
	       }   
	    
	    } catch (Exception e)  
	    { 
	       e.printStackTrace(); 
	    } 
	    return list; 
	 } 
	  
	 /** 
	    * closeServer 
	    * 断开与ftp服务器的链接 
	    * @throws java.io.IOException 
	    */ 
	 public void closeServer() 
	 throws IOException  
	 {    
	   try  
	   { 
	      if (ftpClient != null)  
	      { 
	        ftpClient.closeServer();      
	      } 
	   } catch (IOException e) { 
	      e.printStackTrace(); 
	   } 
	 } 
	   
	  public static void main(String [] args) throws Exception  
	  { 
	    FTPUtil ftp = new FTPUtil(); 
	    try { 
	         //连接ftp服务器 
	         ftp.connectServer("172.16.42.54", "adup", "adup", "test"); 
	         /**  上传文件到 info2 文件夹下 */ 
	         System.out.println("filesize:"+ftp.upload("c:/样图.jpg")+"字节"); 
	         /** 取得info2文件夹下的所有文件列表,并下载到 E盘下 */ 
	         List list = ftp.getFileList("."); 
	         for (int i=0;i<list.size();i++) 
	         { 
	            String filename = (String)list.get(i); 
	            System.out.println(filename); 
	            ftp.download(filename,"d:/"+filename); 
	         } 
	    } catch (Exception e) { 
	       /// 
	    }finally 
	    { 
	       ftp.closeServer(); 
	    } 
	  }   

}
