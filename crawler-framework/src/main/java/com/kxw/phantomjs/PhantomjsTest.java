package com.kxw.phantomjs;


import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;

/**
 * <a href='https://blog.ishell.me/a/python-selenium-phantomjs.html'>@link</a>
 * <a href='http://cuiqingcai.com/2577.html'>@link</a>
 * <a href='http://www.programcreek.com/java-api-examples/index.php?api=org.openqa.selenium.phantomjs.PhantomJSDriver'>@link</a>
 */

public class PhantomjsTest {
    public static void main(String[] args) {

        //File phantomjs = Phanbedder.unpack();
        Capabilities caps = new DesiredCapabilities();
        ((DesiredCapabilities) caps).setJavascriptEnabled(true);
        ((DesiredCapabilities) caps).setCapability("takesScreenshot", true);
        ((DesiredCapabilities) caps).setCapability(
                PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY,
                "D:\\phantomjs-2.1.1-windows\\bin\\phantomjs.exe"
        );
        WebDriver driver = new PhantomJSDriver(caps);
        driver.get("http://www.bilibili.com/video/dance.html");
     /*   (new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return d.getTitle().toLowerCase().endsWith("ztree");
            }
        });*/
        System.out.println(driver.getTitle());
        System.out.println(driver.getPageSource());
        driver.quit();
        //Runtime.getRuntime().exit(0);
    }
}
