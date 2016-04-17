package com.kxw.scope.prototype;

/**
 * Created by kingsonwu on 16/3/10.
 */
public class PrototypeTest {

    public static void main(String[] args) {
//1.创建Bean工厂
        DefaultBeanFactory bf = new DefaultBeanFactory();
//2.创建原型 Bean定义
        BeanDefinition bd = new BeanDefinition();
        bd.setId("bean");
        bd.setScope(BeanDefinition.SCOPE_PROTOTYPE);
        //bd.setClazz(HelloImpl2.class.getName());
        bf.registerBeanDefinition(bd);
//对于原型Bean每次应该返回一个全新的Bean
        System.out.println(bf.getBean("bean") != bf.getBean("bean"));
    }
}
