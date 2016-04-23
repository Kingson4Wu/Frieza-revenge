package com.kxw.reflection.performance;

import org.junit.Before;
import org.junit.Test;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by kingsonwu on 16/4/23.
 */
public class MethodPerformanceTest {

    //private Class<?> classType = BizService.class;
    private BizService service;
    private RequestBean requestBean;

    Map<String, Method> methodMap = new HashMap<>();

    Map<String, MethodHandle> methodHandleMap = new HashMap<>();

    @Before
    public void setup() {
        service = new BizService();

        requestBean = new RequestBean();
        requestBean.setId(100);
        requestBean.setName("kxw");
        requestBean.setEmail("kingson_wu@163.com");

        try {
            Method method = BizService.class.getMethod("list", new Class[]{RequestBean.class});
            methodMap.put("list", method);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }


        try {
            MethodType mt = MethodType.methodType(List.class, RequestBean.class);
            MethodHandle handle = MethodHandles.lookup().findVirtual(BizService.class, "list", mt);
            methodHandleMap.put("list", handle);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

    }


    //@Test
    public void testReflect(String name, Class<?> classType, Class<?>... args) {

        try {

            long start = System.currentTimeMillis();
            for (int i = 0; i < 10_000_000; i++) {

                Method listMethod = classType.getMethod(name, args);
                listMethod.invoke(service, requestBean);
            }
            long end = System.currentTimeMillis();
            System.out.println("reflect cost time : " + (end - start));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    //@Test
    public void testReflect2(String name, int count) {

        try {

            long start = System.nanoTime();
            for (int i = 0; i < count; i++) {
                Method listMethod = methodMap.get(name);
                listMethod.invoke(service, requestBean);
            }
            long end = System.nanoTime();
            System.out.println(count + "  reflect2 cost time : " + (end - start));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void testMethodHandle(String name, int count) {

        try {
            long start = System.nanoTime();
            for (int i = 0; i < count; i++) {
                MethodHandle handle = methodHandleMap.get(name);
                handle.invoke(service, requestBean);
            }
            long end = System.nanoTime();
            System.out.println(count + "  MethodHandle cost time : " + (end - start));
        } catch (Exception e) {
            e.printStackTrace();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }

    @Test
    public void testList() {

        long start = System.currentTimeMillis();
        for (int i = 0; i < 10_000_000; i++) {
            service.list(requestBean);
        }
        long end = System.currentTimeMillis();
        System.out.println("list cost time : " + (end - start));
    }

    @Test
    public void testtest() {
        //testList();
        testReflect("list", BizService.class, new Class[]{RequestBean.class});
        //testReflect2("list");
    }

    @Test
    public void testtest2() {
        //testList();
        //testReflect("list", BizService.class, new Class[]{RequestBean.class});
        testReflect2("list", 10_000_000);
        testReflect2("list", 2);
    }


    @Test
    public void testtest3() {
        testMethodHandle("list", 10_000_000);
        testMethodHandle("list", 2);
    }
}

/**
 * Class.forName方法的调用会执行Class类文件在整个类路径下的搜索，频繁调用比较影响性能。
 * Class对象上的getDeclaredMethod (String, Class<?>...)或getMethod(String, Class<?>...)方法的调用
 * 会执行Method对象在Class对象上的搜索。有些同事还使用getMethods()方法获取Method数组，然后执行搜索任务，
 * 实际上getMethods()还会执行方法对象的集体Copy比直接使用
 * (String, Class<?>...)或getMethod(String, Class<?>...)方法还要消耗时间及空间。
 * <http://blog.csdn.net/huoyunshen88/article/details/12059903>
 */

/*
method.invoke()性能不算差，反而查找这个method可能比较慢。
reflect2 cost time : 70
list cost time : 8
reflect cost time : 595
 */

/*
Method.invoke()本身要用数组包装参数；而且每次调用都必须检查方法的可见性（在Method.invoke()里），
也必须检查每个实际参数与形式参数的类型匹配性（在NativeMethodAccessorImpl.invoke0()
里或者生成的Java版MethodAccessor.invoke()里）；而且Method.invoke()就像是个独木桥一样，各处的反射调用都要挤过去，
在调用点上收集到的类型信息就会很乱，影响内联程序的判断，使得Method.invoke()自身难以被内联到调用方。

作者：郭无心
链接：http://www.zhihu.com/question/30097357/answer/76924443
来源：知乎
著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。

http://rednaxelafx.iteye.com/blog/548536
就反射调用而言，这个invoke()方法非常干净（然而就“正常调用”而言这额外开销还是明显的）

相比之下JDK 7里新的MethodHandle则更有潜力，在其功能完全实现后能达到比普通反射调用方法更高的性能。
在使用MethodHandle来做反射调用时，MethodHandle.invoke()的形式参数与返回值类型都是准确的，
所以只需要在链接方法的时候才需要检查类型的匹配性，而不必在每次调用时都检查。而且MethodHandle是不可变值，
在创建后其内部状态就不会再改变了；JVM可以利用这个知识而放心的对它做激进优化，例如将实际的调用目标内联到做反射调用的一侧。
 */

/**
 *
 * Java 反射到底慢在哪里
 * Because reflection involves types that are dynamically resolved,
 * certain Java virtual machine optimizations can not be performed. Consequently,
 * reflective operations have slower performance than their non-reflective counterparts,
 * and should be avoided in sections of code which are called frequently in performance-sensitive
 * applications.
 * http://stackoverflow.com/questions/435553/java-reflection-performance
 *
 * java反射之所以慢，最主要的就是就是编译器没法对反射相关的代码做优化。
 */