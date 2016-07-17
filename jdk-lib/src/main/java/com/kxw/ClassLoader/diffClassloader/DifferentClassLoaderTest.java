package com.kxw.classLoader.diffClassloader;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

public class DifferentClassLoaderTest {

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, MalformedURLException, NoSuchMethodException, InvocationTargetException {

        String dir = Thread.currentThread().getContextClassLoader().getResource("").getPath();

        // Getting the jar URL which contains target class
        URL[] classLoaderUrls = new URL[]{new URL("file:" + dir)};

        // Create a new URLClassLoader
        //parent class Loader 为extClassLoader!!!!!!
        URLClassLoader cl = new KingsonClassLoader(classLoaderUrls, DifferentClassLoaderTest.class.getClassLoader().getParent());


        //Thread.currentThread().setContextClassLoader(cl);

        System.out.println(Thread.currentThread().getContextClassLoader());


        Class<?> bClazz = cl.loadClass("com.kxw.classLoader.diffClassloader.BMW");
        Class<?> bClazz2 = Class.forName("com.kxw.classLoader.diffClassloader.BMW", true, cl);

        System.out.println(bClazz.getClassLoader());
        System.out.println(bClazz2.getClassLoader());

        System.out.println("class classloader: " + BMW.class.getClassLoader());

        /** new 的原理 */
        System.out.println("new classloader: " + new BMW().getClass().getClassLoader());

        Object o = bClazz.newInstance();

        System.out.println(o);

        // Getting a method from the loaded class and invoke it
        Method method = bClazz.getMethod("sayHello");
        method.invoke(o);

        /** 只能反射调???! */


        /** class.forName 以及new,以及Car.class 都是取目前所处的类的加载器作为自身的加载器(当然加载自定义加载器无法加载的时候可能取父加载器,这个取决于自定义加载器里面的定义)
         * 跟设置setContextClassLoader 无关,设置这个只是方便在需要使用的时候通过getContextClassLoader()取出,
         * 所以在这个类(使用KingClassLoader加载)在这里只能使用反射来调用,但是在BMW类则可以不使用反射
         * 类的ContextClassLoader默认为AppClassLoader,这是固定的!!!
         * Thread和String都是rt类的包,它们的classLoader为null
         * */

        /*Factory factory = new Factory();
        Car car = (Car) bClazz.newInstance();
        car.sayHello();*/

        //factory.setCar(car);/////dddd!!

       /* BMW b = (BMW) bClazz.newInstance();

        Factory a = new Factory();
        a.setCar(b);*/


        //----------

       /* B b = (B) Class.forName("com.kxw.classLoader.diffClassloader.B").newInstance();
        b.sayHello();*/

        //如何改变B的默认classloader???

        /**
         * <a href='http://stevex.blog.51cto.com/4300375/1579612'>@link</a>
         * 每个class loader 都有自己唯一的命名空间，每个class loader 只能访问自己命名空间中的类，
         * 一个class可以被不同的class loader重复加载，但同一个class只能被同一个class loader加载一次，
         * 如果一个类是委托parent加载的，那么加载后，这个类就类似共享的，parent和child都可以访问到这个类，
         * 因为parent是不会委托child加载类的，所以child加载的类parent访问不到），子加载器的命名空间包含了parent加载的所有类，
         * 反过来则不成立
         */

        /**
         * http://www.cnblogs.com/shosky/archive/2011/07/22/2114290.html
         */

        System.out.println("cl parent: " + cl.getParent());
        Class<?> factoryClass = cl.loadClass("com.kxw.classLoader.diffClassloader.Factory");
        //Class<?> factoryClass = Class.forName("com.kxw.classLoader.diffClassloader.Factory");
        Object factoryObject = factoryClass.newInstance();

        Method factoryMethod = factoryClass.getMethod("setCar", cl.loadClass("com.kxw.classLoader.diffClassloader.Car"));
        //Method factoryMethod = factoryClass.getMethod("setCar", Car.class);

        Car car = new BMW();
        factoryMethod.invoke(factoryObject,car);

        /**
         * 虽然这里的car是使用AppClassLoader加载,而Factory使用的是KingsonClassLoader,
         * AppClassLoader 和KingsonClassLoader 为同级的加载器,父加载器都是ExtClassLoader
         * 因此两个car之间不能转化
         * java.lang.IllegalArgumentException: argument type mismatch
         *
         * 即使AppClassLoader是KingsonClassLoader的父加载器,也不能set,详见AppClassLoaderAsParentTest!!
         */
        //System.out.println();

    }
}

