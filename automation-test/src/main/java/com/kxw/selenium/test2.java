package com.kxw.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
public class test2 {
public static void main(String[] args) {
// 创建一个FirefoxDriver实例
// 这个类依赖于接口而不是接口的实现
	System.setProperty("webdriver.firefox.bin", "F:\\Program Files (x86)\\Mozilla Firefox\\firefox.exe");
WebDriver driver = new FirefoxDriver();
// 使用get方法访问Google
driver.get("http://www.google.com");
// 使用下面这个方法也能够达到访问Google的目的
// driver.navigate().to("http://www.google.com");
// 找到html输入框的name
WebElement element = driver.findElement(By.name("q"));
// 输入要查找的内容
element.sendKeys("Cheese!");
// 提交表单，WebDriver会自动找到我们需要提交的元素所在的表单
element.submit();
// 打印网页的标题
System.out.println("Page title is: " + driver.getTitle());
// Google的搜索网页会通过JS动态渲染
// 等待页面加载完毕，超时时间为10秒
(new WebDriverWait(driver, 10)).until(new ExpectedCondition() {
/*	
public Boolean apply(WebDriver d) {
return d.getTitle().toLowerCase().startsWith("cheese!");
}
*/
@Override
public Object apply(Object d) {
	return ((WebDriver) d).getTitle().toLowerCase().startsWith("cheese!");
}


});
// 控制台上将打印如下信息: "cheese! - Google Search"
System.out.println("Page title is: " + driver.getTitle());
// 关闭浏览器
driver.quit();
}
}