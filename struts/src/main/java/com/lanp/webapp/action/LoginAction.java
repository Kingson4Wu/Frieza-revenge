package com.lanp.webapp.action;  
  
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;  
  
import org.apache.struts.action.Action;  
import org.apache.struts.action.ActionForm;  
import org.apache.struts.action.ActionForward;  
import org.apache.struts.action.ActionMapping;  
  
import com.lanp.webapp.form.LoginActionForm;  
/** 
 * 处理登陆的Action类 
 * @author LanP 
 * @since 2012年4月11日23:07:09 
 * @version v1.0 
 */  
public class LoginAction extends Action {  
  
    @Override  
    public ActionForward execute(ActionMapping mapping, ActionForm form,  
            HttpServletRequest request, HttpServletResponse response)  
            throws Exception {  
        String path = "error";  
        LoginActionForm loginActionForm = (LoginActionForm)form;  
        String userName = loginActionForm.getUserName();  
        String passWord = loginActionForm.getPassWord();  
          
        if(null != userName && "admin".equals(userName) && null != passWord && "admin".equals(passWord)) {  
            path = "success";  
            request.setAttribute("userName", userName);  
        } else {  
            path = "error";  
        }  
        return mapping.findForward(path);  
    }  
      
}  