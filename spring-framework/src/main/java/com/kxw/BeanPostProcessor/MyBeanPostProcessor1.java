package com.kxw.BeanPostProcessor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

public class MyBeanPostProcessor1 implements BeanPostProcessor {
	public Object postProcessBeforeInitialization(Object bean, String beanName)
			throws BeansException {
		System.out
				.println("************** MyBeanPostProcessor1 postProcessBeforeInitialization Bean '"
						+ beanName);
		return bean;
	}

	public Object postProcessAfterInitialization(Object bean, String beanName)
			throws BeansException {
		System.out
				.println("************** MyBeanPostProcessor1 postProcessAfterInitialization Bean '"
						+ beanName);
		return bean;
	}
}