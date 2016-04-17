package kxw;
import java.io.*;
import com.opensymphony.xwork2.ActionContext;
import org.apache.struts2.ServletActionContext;
import com.opensymphony.xwork2.ActionSupport;
public class UploadAction extends ActionSupport {
 /**
  *
  */
 private static final long serialVersionUID = 1L;
 private String title;
 private File upload;
 private String uploadContentType;
 private String uploadFileName;
 private String allowTypes;
 // 接受依赖注入的属性
 private String savePath;
 // 接受依赖注入的方法
 public void setSavePath(String value) {
  this.savePath = value;
 }
 private String getSavePath() throws Exception {
  return ServletActionContext.getServletContext().getRealPath(savePath);
 }
 public void setTitle(String title) {
  this.title = title;
 }
 public void setUpload(File upload) {
  this.upload = upload;
 }
 public void setUploadContentType(String uploadContentType) {
  this.uploadContentType = uploadContentType;
 }
 public void setUploadFileName(String uploadFileName) {
  this.uploadFileName = uploadFileName;
 }
 public String getTitle() {
  return (this.title);
 }
 public File getUpload() {
  return (this.upload);
 }
 public String getUploadContentType() {
  return (this.uploadContentType);
 }
 public String getUploadFileName() {
  return (this.uploadFileName);
 }
 @Override
 public String execute() throws Exception {
  System.out.println("开始上传单个文件---");
  System.out.println(getSavePath());
  System.out.println("==========" + getUploadFileName());
  System.out.println("==========" + getUploadContentType());
  System.out.println("==========" + getUpload());
  // 判断是否允许上传
  String filterResult = filterType(this.getAllowTypes().split(","));
  if (filterResult != null) {
   ActionContext.getContext().put("typeError","您要上传的文件类型不正确");
   return filterResult;
  }
  // 以服务器的文件保存地址和原文件名建立上传文件输出流
  FileOutputStream fos = new FileOutputStream(getSavePath() + "\\"
          + getUploadFileName());
  FileInputStream fis = new FileInputStream(getUpload());
  byte[] buffer = new byte[1024];
  int len = 0;
  while ((len = fis.read(buffer)) > 0) {
   fos.write(buffer, 0, len);
  }
  return SUCCESS;
 }
 public String filterType(String[] types) {
  String fileType = this.getUploadContentType();
  for (String type : types) {
   if (type.equals(fileType)) {
    return null;
   }
  }
  return INPUT;
 }
 public String getAllowTypes() {
  return allowTypes;
 }
 public void setAllowTypes(String allowTypes) {
  this.allowTypes = allowTypes;
 }
}