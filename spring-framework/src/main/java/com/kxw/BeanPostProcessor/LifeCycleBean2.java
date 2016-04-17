package com.kxw.BeanPostProcessor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class LifeCycleBean2 implements InitializingBean, DisposableBean,
		BeanNameAware, BeanFactoryAware, ApplicationContextAware {
	private String str = "default2";

	public LifeCycleBean2() {
		System.out.println("construct LifecycleBean2 @@@@@@@@");
	}

	public LifeCycleBean2(String str) {
		this.str = str;
	}

	public String getStr() {
		return str;
	}

	public void setStr(String str) {
		System.out.println("setStr2 @@@@@@@@");
		this.str = str;
	}

	public void init() {
		System.out.println("init mtd2 @@@@@@@@");
	}

	public void cleanup() {
		System.out.println("cleanup mtd2 @@@@@@@@");
	}

	public void afterPropertiesSet() throws Exception {
		System.out.println("afterPropertiesSet2 @@@@@@@@");
	}

	public void destroy() throws Exception {
		System.out.println("destroy2 @@@@@@@@");
	}

	public void setApplicationContext(ApplicationContext arg0)
			throws BeansException {
		System.out.println("setApplicationContext2@@@@@@@@");
	}

	public void setBeanFactory(BeanFactory arg0) throws BeansException {
		System.out.println("setBeanFactory2@@@@@@@@");
	}

	public void setBeanName(String arg0) {
		System.out.println("setBeanName2@@@@@@@@" + arg0);
	}
}
