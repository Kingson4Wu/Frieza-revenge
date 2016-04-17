package com.kxw.selenium;

import com.thoughtworks.selenium.DefaultSelenium;  

public class SeleniumDemo  
{  
  public static void main(String[] args)  
  {  
	  
		//System.setProperty("webdriver.firefox.bin", "F:\\Program Files (x86)\\Mozilla Firefox\\firefox.exe");
      String host = "localhost";  
      int port = 4444;  
      String url = "http://www.baidu.com/";  
      String browserType = "*firefox";  
   
      String keyWordsLocator = "document.getElementById('kw1')";     
      String search = "document.getElementById('su1')";  
      DefaultSelenium selenium = new DefaultSelenium(host,port,browserType,url);  
      selenium.start();  
      selenium.open(url);  
      selenium.type(keyWordsLocator,"java selenium");  
      selenium.click(search);  
      selenium.waitForPageToLoad("50000");  
      selenium.stop();  
       
  }  
}  
/*
 * 运行结果：  
    1.firefox浏览器期待  
    2.打开百度主页  
    3.在搜索框中键入了"java selenium"  
    4.点击"百度一下"按钮  
    5.等等页面加载50s  
    6.关闭浏览器  
    */
