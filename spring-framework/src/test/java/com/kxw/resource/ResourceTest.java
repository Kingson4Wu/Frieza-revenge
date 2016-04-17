package com.kxw.resource;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.Executors;

import org.jboss.vfs.TempFileProvider;
import org.jboss.vfs.VFS;
import org.jboss.vfs.VirtualFile;
import org.jboss.vfs.spi.RealFileSystem;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.core.io.*;

/**
 * <a href='http://www.importnew.com/17644.html'>@link</a>
 */
public class ResourceTest {

    private void dumpStream(Resource resource) {
        InputStream is = null;
        try {
            //1.获取文件资源
            is = resource.getInputStream();
            //2.读取资源
            byte[] descBytes = new byte[is.available()];
            is.read(descBytes);
            System.out.println(new String(descBytes));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                //3.关闭资源
                is.close();
            } catch (IOException e) {
            }
        }
    }


    /**
     * ByteArrayResource代表byte[]数组资源，对于“getInputStream”操作将返回一个ByteArrayInputStream。
     */
    @Test
    public void testByteArrayResource() {
        Resource resource = new ByteArrayResource("Hello World!".getBytes());
        if (resource.exists()) {
            dumpStream(resource);
        }
    }


    /**
     * InputStreamResource代表java.io.InputStream字节流，对于“getInputStream ”操作将直接返回该字节流，
     * 因此只能读取一次该字节流，即“isOpen”永远返回true。
     */
    @Test
    public void testInputStreamResource() {
        ByteArrayInputStream bis = new ByteArrayInputStream("Hello World!".getBytes());
        Resource resource = new InputStreamResource(bis);
        if (resource.exists()) {
            dumpStream(resource);
        }
        Assert.assertEquals(true, resource.isOpen());
    }

    /**
     * FileSystemResource代表java.io.File资源，对于“getInputStream ”操作将返回底层文件的字节流，
     * “isOpen”将永远返回false，从而表示可多次读取底层文件的字节流。
     */
    @Test
    public void testFileResource() {
        File file = new File("d:/test.txt");
        Resource resource = new FileSystemResource(file);
        if (resource.exists()) {
            dumpStream(resource);
        }
        Assert.assertEquals(false, resource.isOpen());
    }


    //---------------------------------------------------------------------------
    //ClassPathResource

    /**
     * ClassPathResource代表classpath路径的资源，将使用ClassLoader进行加载资源。
     * classpath 资源存在于类路径中的文件系统中或jar包里，且“isOpen”永远返回false，表示可多次读取资源。
     * <p>
     * ClassPathResource加载资源替代了Class类和ClassLoader类的“getResource(String name)”
     * 和“getResourceAsStream(String name)”两个加载类路径资源方法，提供一致的访问方式。
     * <p>
     * ClassPathResource提供了三个构造器：
     * public ClassPathResource(String path)：使用默认的ClassLoader加载“path”类路径资源；
     * public ClassPathResource(String path, ClassLoader classLoader)：使用指定的ClassLoader加载“path”类路径资源；
     * public ClassPathResource(String path, Class<?> clazz)：使用指定的类加载“path”类路径资源，将加载相对于当前类的路径的资源；
     */

    /**
     * 使用默认的加载器加载资源，将加载当前ClassLoader类路径上相对于根路径的资源：
     */
    @Test
    public void testClasspathResourceByDefaultClassLoader() throws IOException {
        Resource resource = new ClassPathResource("cn/javass/spring/chapter4/test1.properties");
        if (resource.exists()) {
            dumpStream(resource);
        }
        System.out.println("path:" + resource.getFile().getAbsolutePath());
        Assert.assertEquals(false, resource.isOpen());
    }

    /**
     * 使用指定的ClassLoader进行加载资源，将加载指定的ClassLoader类路径上相对于根路径的资源
     */
    @Test
    public void testClasspathResourceByClassLoader() throws IOException {
        ClassLoader cl = this.getClass().getClassLoader();
        Resource resource = new ClassPathResource("cn/javass/spring/chapter4/test1.properties", cl);
        if (resource.exists()) {
            dumpStream(resource);
        }
        System.out.println("path:" + resource.getFile().getAbsolutePath());
        Assert.assertEquals(false, resource.isOpen());
    }

    /**
     * 使用指定的类进行加载资源，将尝试加载相对于当前类的路径的资源
     */
    @Test
    public void testClasspathResourceByClass() throws IOException {
        Class clazz = this.getClass();
        Resource resource1 = new ClassPathResource("cn/javass/spring/chapter4/test1.properties", clazz);
        if (resource1.exists()) {
            dumpStream(resource1);
        }
        System.out.println("path:" + resource1.getFile().getAbsolutePath());
        Assert.assertEquals(false, resource1.isOpen());

        Resource resource2 = new ClassPathResource("test1.properties", this.getClass());
        if (resource2.exists()) {
            dumpStream(resource2);
        }
        System.out.println("path:" + resource2.getFile().getAbsolutePath());
        Assert.assertEquals(false, resource2.isOpen());
    }

    /**
     * 加载jar包里的资源，首先在当前类路径下找不到，最后才到Jar包里找，而且在第一个Jar包里找到的将被返回
     */
    @Test
    public void classpathResourceTestFromJar() throws IOException {
        Resource resource = new ClassPathResource("overview.html");
        if (resource.exists()) {
            dumpStream(resource);
        }
        System.out.println("path:" + resource.getURL().getPath());
        Assert.assertEquals(false, resource.isOpen());
    }


    //------------------------------------------------------
    //UrlResource
    /**
     * UrlResource代表URL资源，用于简化URL资源访问。“isOpen”永远返回false，表示可多次读取资源。
     UrlResource一般支持如下资源访问：
     http：通过标准的http协议访问web资源，如new UrlResource(“http://地址”)；
     ftp：通过ftp协议访问资源，如new UrlResource(“ftp://地址”)；
     file：通过file协议访问本地文件系统资源，如new UrlResource(“file:d:/test.txt”)；
     */

    //------------------------------------------------------
    //ServletContextResource
    /**
     * ServletContextResource代表web应用资源，用于简化servlet容器的ServletContext接口的getResource操作和getResourceAsStream操作.
     */

    //------------------------------------------------------
    //VfsResource

    /**
     * VfsResource代表Jboss 虚拟文件系统资源。
     * Jboss VFS(Virtual File System)框架是一个文件系统资源访问的抽象层，它能一致的访问物理文件系统、jar资源、zip资源、war资源等，VFS能把这些资源一致的映射到一个目录上，访问它们就像访问物理文件资源一样，而其实这些资源不存在于物理文件系统。
     * 在示例之前需要准备一些jar包，在此我们使用的是Jboss VFS3版本
     */
    //通过VFS，对于jar里的资源和物理文件系统访问都具有一致性
    @Test
    public void testVfsResourceForRealFileSystem() throws IOException {
        //1.创建一个虚拟的文件目录
        VirtualFile home = VFS.getChild("/home");
        //2.将虚拟目录映射到物理的目录
        VFS.mount(home, new RealFileSystem(new File("d:")));
        //3.通过虚拟目录获取文件资源
        VirtualFile testFile = home.getChild("test.txt");
        //4.通过一致的接口访问
        Resource resource = new VfsResource(testFile);
        if (resource.exists()) {
            dumpStream(resource);
        }
        System.out.println("path:" + resource.getFile().getAbsolutePath());
        Assert.assertEquals(false, resource.isOpen());
    }

    @Test
    public void testVfsResourceForJar() throws IOException {
        //1.首先获取jar包路径
        File realFile = new File("lib/org.springframework.beans-3.0.5.RELEASE.jar");
        //2.创建一个虚拟的文件目录
        VirtualFile home = VFS.getChild("/home2");
        //3.将虚拟目录映射到物理的目录
        VFS.mountZipExpanded(realFile, home,
                TempFileProvider.create("tmp", Executors.newScheduledThreadPool(1)));
        //4.通过虚拟目录获取文件资源
        VirtualFile testFile = home.getChild("META-INF/spring.handlers");
        Resource resource = new VfsResource(testFile);
        if (resource.exists()) {
            dumpStream(resource);
        }
        System.out.println("path:" + resource.getFile().getAbsolutePath());
        Assert.assertEquals(false, resource.isOpen());
    }

    //------------------------------------------------------

    /**
     * ResourceLoader在进行加载资源时需要使用前缀来指定需要加载：“classpath:path”表示返回ClasspathResource，
     * “http://path”和“file:path”表示返回UrlResource资源，如果不加前缀则需要根据当前上下文来决定，
     * DefaultResourceLoader默认实现可以加载classpath资源
     */

    @Test
    public void testResourceLoad() {
        ResourceLoader loader = new DefaultResourceLoader();
        Resource resource = loader.getResource("classpath:cn/javass/spring/chapter4/test1.txt");
        //验证返回的是ClassPathResource
        Assert.assertEquals(ClassPathResource.class, resource.getClass());
        Resource resource2 = loader.getResource("file:cn/javass/spring/chapter4/test1.txt");
        //验证返回的是ClassPathResource
        Assert.assertEquals(UrlResource.class, resource2.getClass());
        Resource resource3 = loader.getResource("cn/javass/spring/chapter4/test1.txt");
        //验证返默认可以加载ClasspathResource
        Assert.assertTrue(resource3 instanceof ClassPathResource);
    }

    /**
     * 可以用配置文件代替
     * <bean id="resourceBean1" class="cn.javass.spring.chapter4.bean.ResourceBean3">
     <property name="resource" value="cn/javass/spring/chapter4/test1.properties"/>
     </bean>
     <bean id="resourceBean2" class="cn.javass.spring.chapter4.bean.ResourceBean3">
     <property name="resource"
     value="classpath:cn/javass/spring/chapter4/test1.properties"/>
     </bean>
     */

    /**
     * 注入Resource
     通过回调或注入方式注入“ResourceLoader”，然后再通过“ResourceLoader”再来加载需要的资源对于只需要加载某个固定的资源是不是很麻烦，有没有更好的方法类似于前边实例中注入“java.io.File”类似方式呢？
     Spring提供了一个PropertyEditor “ResourceEditor”用于在注入的字符串和Resource之间进行转换。因此可以使用注入方式注入Resource。
     ResourceEditor完全使用ApplicationContext根据注入的路径字符串获取相应的Resource，说白了还是自己做还是容器帮你做的问题。
     */



}

