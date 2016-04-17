package com.kxw.resource.ResourceLoaderAware;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ResourceLoader;

public class ResoureLoaderAwareTest {
    @Test
    public void test() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("resourceLoaderAware.xml");
        ResourceBean resourceBean = ctx.getBean(ResourceBean.class);
        ResourceLoader loader;
        loader = resourceBean.getResourceLoader();
        Assert.assertTrue(loader instanceof ApplicationContext);
    }
}

/**
 由于上述实现回调接口注入ResourceLoader的方式属于侵入式，所以不推荐上述方法，
 可以采用更好的自动注入方式，如“byType”和“constructor”
 */