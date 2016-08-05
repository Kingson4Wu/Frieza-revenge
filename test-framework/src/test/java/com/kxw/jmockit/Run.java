package com.kxw.jmockit;


import com.kxw.bean.Kingson;
import mockit.Mock;
import mockit.MockUp;
import org.junit.Assert;
import org.junit.Test;

import static junit.framework.TestCase.assertTrue;


/**
 * <a href='http://blog.csdn.net/aitangyong/article/details/20065547'>@link</a>
 */
public class Run {

    @Test
    public void t1() {
        MockUp<IStudentService> proxyStub = new MockUp<IStudentService>() {
            // 需要使用@Mock标记,否则jmockit不会处理;
            // 而且方法的签名必须与接口中方法签名一致，否则jmockit会报错
            @Mock
            public String getName(int id) {
                return "00" + id;
            }
        };

        IStudentService mockInstance = proxyStub.getMockInstance();

        Assert.assertEquals("001", mockInstance.getName(1));

        Assert.assertEquals(0, mockInstance.getAge(1));

        proxyStub.tearDown();
    }

    @Test
    public void t2() {
        MockUp<AbstractStudentService> proxyStub = new MockUp<AbstractStudentService>() {
            @Mock
            public int getAge(int id) {
                return 10 * id;
            }
        };

        AbstractStudentService mockInstance = proxyStub.getMockInstance();

        Assert.assertEquals("ATY", mockInstance.getName(1));

        Assert.assertEquals(10, mockInstance.getAge(1));

        proxyStub.tearDown();
    }


    /**
     * jmockit的工作原理是通过asm修改原有class的字节码，然后通过jdk的instrument机制替换现有class的内容，达到mock方法或者属性的目的。在测试完成后再恢复原有class。
     * <a href='http://zldeng1984.iteye.com/blog/1756904'>@link</a>
     */
    @Test
    public void t3() {

        Kingson mock = new MockUp<Kingson>() {
            @Mock
            void run() {
                System.out.println("i am mock !");
            }

            @Mock
            boolean next() {
                return true;
            }
        }.getMockInstance();


        System.out.println(new Kingson().getClass());
        System.out.println(mock.getClass());
        System.out.println(mock.getClass().getClassLoader());
        mock.run();
        assertTrue(mock.next());
    }
}
