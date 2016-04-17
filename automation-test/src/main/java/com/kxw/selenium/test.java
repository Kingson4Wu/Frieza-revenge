package com.kxw.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.reporters.ExitCodeListener;

public class test {

	// 定位UI元素
	// 　　WebElement element = driver.findElement(By.id("coolestWidgetEvah"));
	// 　List cheeses = driver.findElements(By.className("cheese"));
	// WebElement frame = driver.findElement(By.tagName("iframe"));
	// WebElement cheese = driver.findElement(By.name("cheese"));
	// WebElement cheese =
	// driver.findElement(By.linkText("cheese"));//　找到与Text属性精确匹配的超链接。
	// WebElement cheese =
	// driver.findElement(By.partialLinkText("cheese"));//　找到与Text属性模糊匹配的超链接。
	//WebElement cheese = driver.findElement(By.cssSelector("#food span.dairy.aged"));
	
	
	
	public static void main(String[] args) {
		// 设置chromedriver地址
		System.setProperty("webdriver.chrome.driver",
				"C:\\Program Files (x86)\\Google\\Chrome\\Application\\chromedriver.exe");

		WebDriver driver = new ChromeDriver();

		driver.get("http://www.baidu.com");
		System.out.println("Page title is: " + driver.getTitle());

		WebElement input = driver.findElement(By.xpath("//input[@id='kw1']"));

		CharSequence[] cs = new CharSequence[1];
		cs[0] = "托雷斯";
		input.sendKeys(cs);

		WebElement btn = driver.findElement(By.xpath("//input[@id='su1']"));

		btn.click();

		// 关闭浏览器
		// driver.quit();

		// 设置chromedriver地址
		/*
		 * System.setProperty ( "webdriver.chrome.driver" ,
		 * "C:\\Program Files (x86)\\Google\\Chrome\\Application\\chromedriver.exe"
		 * );
		 * 
		 * WebDriver driver = new ChromeDriver();
		 * 
		 * driver.get("http://shanghai.anjuke.com");
		 * 
		 * WebElement input =
		 * driver.findElement(By.xpath("//input[@id='glb_search0']"));
		 * 
		 * CharSequence[] cs= new CharSequence[1]; cs[0] = "广州";
		 * input.sendKeys(cs);
		 * 
		 * WebElement btn =
		 * driver.findElement(By.xpath("//input[@id='btnSubmit']"));
		 * 
		 * btn.click();
		 * 
		 * System.out.println("Page title is: " + driver.getTitle());
		 */

	}
}