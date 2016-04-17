package com.kxw.servlet3;

/**
 * 此工具类只适用于Servlet 3.0
 * 为了弥补 Servlet 3.0 文件上传时获取文件类型的困难问题
 *
 * @author xiazdong
 */
import javax.servlet.http.*;
public class UploadUtil{
    public static String getFileType(Part p){
        String name = p.getHeader("content-disposition");
        String fileNameTmp = name.substring(name.indexOf("filename=")+10);
        String type = fileNameTmp.substring(fileNameTmp.indexOf(".")+1,fileNameTmp.indexOf("\""));
        return type;
    }
    public static String getFileName(Part p){
        String name = p.getHeader("content-disposition");
        String fileNameTmp = name.substring(name.indexOf("filename=")+10);
        String fileName = fileNameTmp.substring(0,fileNameTmp.indexOf("\""));
        return fileName;
    }
}

