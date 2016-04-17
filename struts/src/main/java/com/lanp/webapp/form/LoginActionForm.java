package com.lanp.webapp.form;  
  
import org.apache.struts.action.ActionForm;  
  
/** 
 * 封装登陆表单数据的FORM类 
 * @author LanP 
 * @since 2012年4月11日23:07:09 
 * @version v1.0 
 */  
@SuppressWarnings("serial")  
public class LoginActionForm extends ActionForm {  
    private String userName;  
      
    private String passWord;  
      
    public String getUserName() {  
        return userName;  
    }  
    public void setUserName(String userName) {  
        this.userName = userName;  
    }  
    public String getPassWord() {  
        return passWord;  
    }  
    public void setPassWord(String passWord) {  
        this.passWord = passWord;  
    }  
}  