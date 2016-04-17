package com.kxw.selenium.abc.test3;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class BrandProductTest {
	
	private static final int MAXIMUM_BRAND = 30;
	private static Logger logger = LogManager.getLogger(BrandProductTest.class.getName());
	  

	public static void main(String[] args) {
		BrandProductTest instance = new BrandProductTest();
		System.setProperty("webdriver.firefox.bin", "F:\\Program Files (x86)\\Mozilla Firefox\\firefox.exe");
		//WebDriver driver = new HtmlUnitDriver(true);
		WebDriver driver = new FirefoxDriver();
				
		List<Brand> webResult;
		List<Brand> wapResult;
		try {
			webResult = instance.testabcWeb(driver);
			 
			wapResult = instance.testabcWap(driver);
			
			compareResult(webResult, wapResult);
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		
		driver.quit();
		 
	}


	public void testSpecificBrand(){
		//WebDriver driver = new HtmlUnitDriver(true);
		WebDriver driver = new FirefoxDriver();

		//String brandUrl = "http://www.abc.com/show-179800.html";
		String brandUrl = "http://m.abc.com/brand-177536-0-0-0-1-0-1-40.html";
		List<Integer> products;
		try {
			//products = instance.fetchProductIdsFromWeb(driver, brandUrl, 1);
			products = fetchProductIdsFromWap(driver, brandUrl);
			if(products.size() > 0){
				logger.info("total product number is " + products.size());
				driver.quit();
				return;
			}
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		
	}
	
	private static void compareResult(List<Brand> webResult, List<Brand> wapResult) {
		logger.info("the result of web and wap are equal ? " + webResult.equals(wapResult));
		logger.info("brand num of b2c: " + webResult.size());
		logger.info("brand num of wap: " + wapResult.size());
		for(int i=0; i<webResult.size(); i++){
			Brand brandItemWeb = webResult.get(i);
			boolean isFound = false;
			for(int j=0; j<wapResult.size(); j++){
				Brand brandItemWap = wapResult.get(j);
				if(brandItemWeb.getId() == brandItemWap.getId()){
					isFound =  true;
					logger.info("the brand " + brandItemWeb.getId() + " is found in wap result");
					logger.info("the product size compare: b2c has " + brandItemWeb.getProducts().size() + ", wap has " + brandItemWap.getProducts().size());
					logger.info("the products are equal ? " + brandItemWeb.getProducts().equals(brandItemWap.getProducts()));
					logger.info("the product list of b2c is " + brandItemWeb.getProducts().toString());
					logger.info("the product list of wap is " + brandItemWap.getProducts().toString());
				}
			}
			if(!isFound){
				logger.info("the brand " + brandItemWeb.getId() + " is not found in wap result");
			}
		}
		
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
	    
	    WebElement secondBlock = driver.findElement(By.id("J_firstPart_wrap"));
	    //get brand list
	    List<WebElement> rows = secondBlock.findElements(By.className("s_mod"));
	    int size = rows.size();
	    logger.info("total brand size:" + size);
	    Iterator it = rows.iterator();
	    List<String> brandUrls = new ArrayList();
	    while(it.hasNext()){
	    	WebElement item = (WebElement) it.next();
	    	WebElement link = item.findElement(By.className("J_to_list"));
	    	//get brand url
	    	String href = link.getAttribute("href");
	    	//logger.info(href);
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
		    		
	    			logger.info("current brand id is " + brandId );
	    			List<Integer> productIds = fetchProductIdsFromWeb(driver,	brandUrl, 1);
			    	
			    	aBrand.setProducts(productIds);
			    	brandList.add(aBrand);
	    		}
	    		
	    		if(brandList.size() > MAXIMUM_BRAND){
	    			break;
	    		}
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
	    logger.info("total brand found:" + brandLinks.size());
		List<String> brandUrls = new ArrayList();
	    for(int i=0; i<brandLinks.size(); i++){
	    	//List<WebElement> brandLinks2 = driver.findElements(By.className("itemList"));
	    	brandLinks = driver.findElements(By.className("itemList"));
	    	WebElement brandLink = brandLinks.get(i);
	    	//(new WebDriverWait(driver, 5)).until(ExpectedConditions.stalenessOf(brandLink)); 
	    	String brandUrl = brandLink.getAttribute("href");
	    	//logger.info(brandUrl);
	    	brandUrls.add(brandUrl);
	    }
	    
	    List<Brand> brandList = new ArrayList();
	    for(int i=0; i<brandUrls.size(); i++){
	    	String brandUrl = brandUrls.get(i);
	    		
	    	if(brandUrl.contains("brand-")){
	    		int start = brandUrl.indexOf("brand-") + 6;
	    		String brandId = brandUrl.substring(start,  start+6);
	    		logger.info("current brand id is:" + brandId);
	    		Brand item = new Brand();
	    		item.setId(Integer.parseInt(brandId));
	    		
	    		List<Integer> productIds = fetchProductIdsFromWap(driver, brandUrl);
	    		item.setProducts(productIds);
	    		 
	    		brandList.add(item);
	    		
	    	}
	    	
	    	if(brandList.size() > MAXIMUM_BRAND){
	    		break;
	    	}
	    	
	    }
	
	    
	   
	    return brandList;
	}
	
	private List<Integer> fetchProductIdsFromWeb(WebDriver driver, String brandUrl, int pageNo) throws InterruptedException {
		logger.info("pageNo=" + pageNo);
		//goto a specific brand page
		driver.get(brandUrl);
		//Thread.sleep(6000);
		
		//scroll to the bottom of the page, otherwise you can only get a part of products
		WebElement footer = driver.findElement(By.id("footer"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", footer);
		
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", footer);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", footer);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", footer);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", footer);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", footer);
		
		//action.moveByOffset(0, 3000);
		//.moveToElement(driver.findElement(By.id("hidden_element"))).click().build().perform()
		
		List<String> otherPageUrls = new ArrayList();
		try{
			WebElement pager = driver.findElement(By.id("J_page_special"));
			// just read pager infomation in the first page
			if(pageNo == 1 && pager.isDisplayed()){
				logger.info("pager is found");
				List<WebElement> pageLinks = pager.findElements(By.tagName("a"));
				logger.info("pager link size:" + pageLinks.size());
				for(int i=0; i<pageLinks.size(); i++){
					WebElement aLink = pageLinks.get(i);
					logger.info(aLink.getText());
					if(!aLink.getText().equals(">")){
						otherPageUrls.add(aLink.getAttribute("href"));
					}
				}
			}
		}catch(NoSuchElementException e){
			logger.info("no pager found in this page");
		}
		logger.info(otherPageUrls.toString());
		
		// find all product items' element 
		List<WebElement> products = driver.findElements(By.className("J_pro_items"));
		logger.info("total product num of brand is " + products.size());
		
		Iterator it = products.iterator();
		// list contain all items id of current brand
		List<Integer> productIds = new ArrayList();
		while(it.hasNext()){
			WebElement item = (WebElement)it.next();
			String idstr = item.getAttribute("id");
			String id = idstr.substring(idstr.lastIndexOf("_")+1);
			//logger.info("product id:" + id);
			productIds.add(Integer.parseInt(id));
		}
		
		//fetch other pages data and merge those data into first page data.
		if(pageNo == 1 && otherPageUrls.size() > 0){
			for(int j=0; j<otherPageUrls.size(); j++){
				List<Integer> pageProducts = fetchProductIdsFromWeb(driver, otherPageUrls.get(j), j+2);
				productIds.addAll(pageProducts);
			}
		}
		
		return productIds;
	}
	
	private List<Integer> fetchProductIdsFromWap(WebDriver driver, String brandUrl) throws InterruptedException{
		List<Integer> productIds = new ArrayList();
		// go to homepage
	    driver.get(brandUrl);
	    Thread.sleep(3000);
	    
	    //scroll to the bottom of the page, otherwise you can only get a part of products
    	WebElement footer = driver.findElement(By.className("footer"));
    	
    	
    	((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", footer);
	    
	    WebElement box = driver.findElement(By.className("gridItem"));
	    List<WebElement> productLinks = box.findElements(By.className("rs-item"));
	    List<WebElement> lastProductLinks = new ArrayList();
	    logger.info("total product num of brand is " + productLinks.size());
	    while(productLinks.size() != lastProductLinks.size()){
	    	lastProductLinks = productLinks;
	    	((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", footer);
	    	Thread.sleep(1000);
	    	productLinks = box.findElements(By.className("rs-item"));
	    	logger.info("total product num of brand is " + productLinks.size());
	    }
	    
	    for(int i=0; i<productLinks.size(); i++){
	    	WebElement link = productLinks.get(i);
	    	String pid = link.getAttribute("data-mid");
	    	//logger.info("product id:" + pid);
	    	productIds.add(Integer.parseInt(pid));
	    }
	    
		return productIds;
	}
	

}
