package com.kxw.selenium.abc.test2;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class BrandProductTest {

	
	private static Log log = LogFactory.getLog(BrandProductTest.class.getName());
	
	
	public static void main(String[] args) {
		BrandProductTest instance = new BrandProductTest();
		System.setProperty("webdriver.firefox.bin", "F:\\Program Files (x86)\\Mozilla Firefox\\firefox.exe");
		//WebDriver driver = new HtmlUnitDriver(true);
		WebDriver driver = new FirefoxDriver();

		/*
		List<Brand> webResult;
		try {
			webResult = instance.testabcWeb(driver);
			System.out.println("brand num of b2c: " + webResult.size());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		
		List<Brand> wapResult;
		List<Brand> webResult;
		try {
			wapResult = instance.testabcWap(driver);
			System.out.println("brand num of wap: " + wapResult.size());
			//webResult = instance.testabcWeb(driver);
			//System.out.println("brand num of web: " + webResult.size());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		driver.quit();
		 
	}

	
	public  List<Brand> testabcWeb(WebDriver driver) throws InterruptedException{
	    
	    //driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	    
	    // go to homepage
	    driver.get("http://www.abc.com");
	    Thread.sleep(5000);
	    
	    WebElement firstBlock = driver.findElement(By.id("J_warehouse_wrap"));
	    
	    WebElement address = firstBlock.findElement(By.linkText("北京"));
	    // select address
	    address.click();
	    
	    Thread.sleep(5000);
	    
	    WebElement secondBlock = driver.findElement(By.id("J_firstPart_wrap"));
	    //get brand list
	    List<WebElement> rows = secondBlock.findElements(By.className("s_mod"));
	    int size = rows.size();
	    System.out.println("total brand size:" + size);
	    log.info("total brand size:" + size);
	    
	    Iterator it = rows.iterator();
	    List<String> brandUrls = new ArrayList();
	    while(it.hasNext()){
	    	WebElement item = (WebElement) it.next();
	    	WebElement link = item.findElement(By.className("J_to_list"));
	    	//get brand url
	    	String href = link.getAttribute("href");
	    	System.out.println(href);
	    	brandUrls.add(href);
	    }
	    
	    List<Brand> brandList = new ArrayList();
	    
	    if(brandUrls.size() > 0){
	    	// iterator of brandurl list
	    	for(int i=0; i<brandUrls.size(); i++){
	    		Brand aBrand = new Brand();
	    		String brandUrl = brandUrls.get(i);
	    		if(brandUrl.contains("show-")){
	    			
	    			int start = brandUrl.indexOf("show-") + 5;
		    		String brandId = brandUrl.substring(start,  start+6);
		    		
	    			aBrand.setId(Integer.parseInt(brandId));
		    		
	    			//goto a specific brand page
			    	driver.get(brandUrl);
			    	
			    	//scroll to the bottom of the page, otherwise you can only get a part of products
			    	WebElement footer = driver.findElement(By.id("footer"));
			    	((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", footer);
			    	
			    	// find all product items' element 
			    	List<WebElement> products = driver.findElements(By.className("J_pro_items"));
			    	System.out.println("total product num of brand " + brandId + " is " + products.size());
			    	
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
			    	
			    	aBrand.setProducts(productIds);
			    	brandList.add(aBrand);
	    		}
	    		
	    		/*if(i > 3){
	    			break;
	    		}*/
	    	}
	    }
	    
	    
	    return brandList;
	}
	
	public List<Brand> testabcWap(WebDriver driver) throws InterruptedException{
	    //driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	    
	    // go to homepage
	    driver.get("http://m.abc.com");
	    Thread.sleep(5000);
	    
	    
	    WebElement firstBlock = driver.findElement(By.className("modal-message"));
	    
	    WebElement address = firstBlock.findElement(By.linkText("北京"));
	    // select address
	    address.click();
	    
	    //scroll to the bottom of the page, otherwise you can only get a part of products
    	WebElement footer = driver.findElement(By.className("footer"));
    	((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", footer);
    	
	    //WebElement brandBox = driver.findElement(By.id("channel_img"));
	    List<WebElement> brandLinks = driver.findElements(By.className("itemList"));
	    System.out.println("total brand found:" + brandLinks.size());
	    //log.debug("total brand found:" + brandLinks.size());
	    
	    
	    
		List<String> brandUrls = new ArrayList();
	    for(int i=0; i<brandLinks.size(); i++){
	    	//List<WebElement> brandLinks2 = driver.findElements(By.className("itemList"));
	    	brandLinks = driver.findElements(By.className("itemList"));
	    	WebElement brandLink = brandLinks.get(i);
	    	//(new WebDriverWait(driver, 5)).until(ExpectedConditions.stalenessOf(brandLink)); 
	    	String brandUrl = brandLink.getAttribute("href");
	    	System.out.println(brandUrl);
	    	brandUrls.add(brandUrl);
	    }
	    
	    List<Brand> brandList = new ArrayList();
	    for(int i=0; i<brandUrls.size(); i++){
	    	String brandUrl = brandUrls.get(i);
	    		
	    	if(brandUrl.contains("brand-")){
	    		int start = brandUrl.indexOf("brand-") + 6;
	    		String brandId = brandUrl.substring(start,  start+6);
	    		System.out.println(brandId);
	    		Brand item = new Brand();
	    		item.setId(Integer.parseInt(brandId));
	    		
	    		List<Integer> productIds = this.fetchProductIdsFromWap(driver, brandUrl);
	    		System.out.println("total product:" + productIds.size());
	    		item.setProducts(productIds);
	    		 
	    		brandList.add(item);
	    		
	    	}
	    	
	    	/*if(i > 3){
	    		break;
	    	}
	    	*/
	    }
	
	    
	   
	    return brandList;
	}
	
	private List<Integer> fetchProductIdsFromWap(WebDriver driver, String brandUrl){
		List<Integer> productIds = new ArrayList();
		// go to homepage
	    driver.get(brandUrl);
	    
	    //scroll to the bottom of the page, otherwise you can only get a part of products
    	WebElement footer = driver.findElement(By.className("footer"));
    	((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", footer);
	    
    	
    	
        	WebElement loadingMoreBtn= driver.findElement(By.className("more_btn"));
        		System.out.println(loadingMoreBtn.getText());
        	
        		while(!"^_^，亲，没有更多了".equals(loadingMoreBtn.getText())){
        			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", footer);
        			loadingMoreBtn= driver.findElement(By.className("more_btn"));
        			System.out.println(loadingMoreBtn.getText());
        		}
        		//if(loadingMoreBtn.getText()!="查看更多商品")
        		//	break;
        	//((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", footer);
        	
    	
    	
    	
    	
    	//System.out.println(loadingMoreBtn.getText());
    	
    	
    	
    	
    	
    	
	    WebElement box = driver.findElement(By.className("gridItem"));
	    List<WebElement> productLinks = box.findElements(By.className("rs-item"));
	    for(int i=0; i<productLinks.size(); i++){
	    	WebElement link = productLinks.get(i);
	    	String pid = link.getAttribute("data-mid");
	    	System.out.println(pid);
	    	productIds.add(Integer.parseInt(pid));
	    }
	    
		return productIds;
	}
	

}
