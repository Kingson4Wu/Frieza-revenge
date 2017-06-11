package com.kxw.BeanPostProcessor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class LifeCycleBean implements InitializingBean, DisposableBean,
		BeanNameAware, BeanFactoryAware, ApplicationContextAware {
	private String str = "default";

	public LifeCycleBean() {
		System.out.println("construct LifecycleBean ***************");
	}

	public LifeCycleBean(String str) {
		this.str = str;
	}

	public String getStr() {
		return str;
	}

	public void setStr(String str) {
		System.out.println("setStr ***************");
		this.str = str;
	}

	public void init() {
		System.out.println("init mtd ***************");
	}

	public void cleanup() {
		System.out.println("cleanup mtd ***************");
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		System.out.println("afterPropertiesSet ***************");
	}

	@Override
	public void destroy() throws Exception {
		System.out.println("destroy ***************");
	}

	@Override
	public void setApplicationContext(ApplicationContext arg0)
			throws BeansException {
		System.out.println("setApplicationContext***************");
	}

	@Override
	public void setBeanFactory(BeanFactory arg0) throws BeansException {
		System.out.println("setBeanFactory***************");
	}

	@Override
	public void setBeanName(String arg0) {
		System.out.println("setBeanName***************" + arg0);
	}
}
