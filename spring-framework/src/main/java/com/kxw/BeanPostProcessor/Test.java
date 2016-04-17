package com.kxw.BeanPostProcessor;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {

	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:bfactoryCtx1.xml");  
		System.out.println("^^^^^^^^^^^^^^^^^^1");
		((ClassPathXmlApplicationContext)ctx).registerShutdownHook();  
		System.out.println("^^^^^^^^^^^^^^^^^^2");
		LifeCycleBean bean1 = ctx.getBean("lifeCycleBean",LifeCycleBean.class); 
		LifeCycleBean2 bean2 = ctx.getBean("lifeCycleBean2",LifeCycleBean2.class); 
		System.out.println("^^^^^^^^^^^^^^^^^^3");
		System.out.println("#############" + bean1 +  "   " + bean1.getStr());  
		System.out.println("#############" + bean2 +  "   " + bean2.getStr());
	}

}
