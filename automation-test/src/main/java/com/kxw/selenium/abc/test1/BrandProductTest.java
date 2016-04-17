package com.kxw.selenium.abc.test1;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class BrandProductTest {

	public static void main(String[] args) {
		BrandProductTest instance = new BrandProductTest();
		instance.testWeb();
	}

	
	public void testWeb(){
		System.setProperty("webdriver.firefox.bin", "F:\\Program Files (x86)\\Mozilla Firefox\\firefox.exe");
	    WebDriver driver = new FirefoxDriver();
	    driver.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);
	    
	    /*
	     * 
	     * Thread.sleep() 是线程休眠若干秒，JAVA去实现。等待的时间需要预估的比较准确，但实际上这是很难做到。而且系统一直再等待，预估的长了，时间就白白的浪费了，预估短了，不起作用。
implicitlyWait() 不是休眠，是设置超时时间，是每个driver自己去实现的。以IEDriverServer为例，implicitlyWait()会将一个超时的时间阀值传递给IEDriverServer,在findelement或者findelements的时候，首先去找web元素，如果没有找到，判断时间否超超过implicitlyWait()传递进来的阀值，如果没有超过，则再次找这个元素，直到找到元素或者时间超过最大阀值。那我们就可以设定一个比较长的超时时间，但同时也不会让程序白白的等待。当然，在没有找到元素之后，IEDriverServer也是会休眠的，默认是250ms。
	     */
	    
	    
	    
	    
	    // go to homepage
	    driver.get("http://www.abc.com");
	    
	    WebElement firstBlock = driver.findElement(By.id("J_warehouse_wrap"));
	    
	    WebElement address = firstBlock.findElement(By.linkText("北京"));
	    // select address
	    address.click();
	    
	    WebElement secondBlock = driver.findElement(By.id("J_firstPart_wrap"));
	    //get brand list
	    List<WebElement> rows = secondBlock.findElements(By.className("s_mod"));
	    int size = rows.size();
	    System.out.println("total size:" + size);
	    Iterator it = rows.iterator();
	    List brandUrls = new ArrayList();
	    while(it.hasNext()){
	    	WebElement item = (WebElement) it.next();
	    	WebElement link = item.findElement(By.className("J_to_list"));
	    	//get brand url
	    	String href = link.getAttribute("href");
	    	System.out.println(href);
	    	brandUrls.add(href);
	    }
	    
	    if(brandUrls.size() > 0){
	    	// iterator of brandurl list
	    	Iterator it2 = brandUrls.iterator();
	    	while(it2.hasNext()){
	    		String brandUrl = (String)it2.next();
	    		if(brandUrl.contains("show-")){
	    			//goto a specific brand page
			    	driver.get(brandUrl);
			    	
			    	//scroll to the bottom of the page, otherwise you can only get a part of products
			    	WebElement footer = driver.findElement(By.id("footer"));
			    	((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", footer);
			    	
			    	// find all product items' element 
			    	List<WebElement> products = driver.findElements(By.className("J_pro_items"));
			    	System.out.println("total products:" + products.size());
			    	
			    	Iterator it3 = products.iterator();
			    	// list contain all items id of current brand
			    	List<Integer> productIds = new ArrayList();
			    	while(it3.hasNext()){
			    		WebElement item = (WebElement)it3.next();
			    		String idstr = item.getAttribute("id");
			    		String id = idstr.substring(idstr.lastIndexOf("_")+1);
			    		System.out.println("item id:" + id);
			    		productIds.add(Integer.parseInt(id));
			    	}
	    		}
	    	}
	    }
	    
	    driver.quit();  
	}

}
