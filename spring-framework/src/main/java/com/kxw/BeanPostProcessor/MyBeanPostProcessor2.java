package com.kxw.BeanPostProcessor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

public class MyBeanPostProcessor2 implements BeanPostProcessor {
	public Object postProcessBeforeInitialization(Object bean, String beanName)
			throws BeansException {
		System.out
				.println("************** MyBeanPostProcessor2 postProcessBeforeInitialization Bean '"
						+ beanName);
		return bean;
	}

	public Object postProcessAfterInitialization(Object bean, String beanName)
			throws BeansException {
		System.out
				.println("************** MyBeanPostProcessor2 postProcessAfterInitialization Bean '"
						+ beanName);
		return bean;
	}
}