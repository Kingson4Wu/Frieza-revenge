package com.kxw.encryption.base64;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * http://blog.csdn.net/phinecos/article/details/4612071
 **/
public class MyTestForCocos2dJs {
	public static void main(String[] args) {
	String strImg = GetImageStr();
		//System.out.print(strImg);
		System.out.println("=======================================================================");
	//	strImg=strImg.replace("\n", "\""+"\n"+"+"+"\"");
//		strImg=strImg.replace("\n", "\""+"\n");
		//strImg=strImg.replace("\n", "\"");
		//strImg=strImg.replace("\n", "\"");
		
		StringBuilder sb=new StringBuilder();
		
		
		
		//convert for cocos2d source file CCLoader.js
		// open by sublime
		for(int i=0;i<strImg.length();i++){ 
			char ch = strImg.charAt(i); 
			if(ch==10||ch==13){
				sb.append("\""+"+"+ch+"\"");
				i++;
			}else{
				sb.append(ch);
			}
			}
		
		strImg=sb.toString();
		FileWriter writer;
		try {
			writer = new FileWriter("E://code.txt");
			writer.write(strImg);
			System.out.println("write file success...");
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	//	GenerateImage(strImg);
	}

	public static String GetImageStr() {// 将图片文件转化为字节数组字符串，并对其进行Base64编码处理
		String imgFile = "e://torres.jpg";// 待处理的图片
		InputStream in = null;
		byte[] data = null;
		// 读取图片字节数组
		try {
			in = new FileInputStream(imgFile);
			data = new byte[in.available()];
			in.read(data);
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		// 对字节数组Base64编码
		BASE64Encoder encoder = new BASE64Encoder();
		return encoder.encode(data);// 返回Base64编码过的字节数组字符串
	}

	public static boolean GenerateImage(String imgStr) {// 对字节数组字符串进行Base64解码并生成图片
		if (imgStr == null) // 图像数据为空
			return false;
		BASE64Decoder decoder = new BASE64Decoder();
		try {
			// Base64解码
			byte[] b = decoder.decodeBuffer(imgStr);
			for (int i = 0; i < b.length; ++i) {
				if (b[i] < 0) {// 调整异常数据
					b[i] += 256;
				}
			}
			// 生成jpeg图片
			String imgFilePath = "e://222.jpg";// 新生成的图片
			OutputStream out = new FileOutputStream(imgFilePath);
			out.write(b);
			out.flush();
			out.close();
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
