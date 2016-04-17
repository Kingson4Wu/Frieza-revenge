具体代码看项目test case
<http://www.importnew.com/17673.html>

资源之基础知识

在日常程序开发中，处理外部资源是很繁琐的事情，我们可能需要处理URL资源、File资源资源、ClassPath相关资源、服务器相关资源（JBoss AS 5.x上的VFS资源）等等很多资源。因此处理这些资源需要使用不同的接口，这就增加了我们系统的复杂性；而且处理这些资源步骤都是类似的（打开资源、读取资源、关闭资源），因此如果能抽象出一个统一的接口来对这些底层资源进行统一访问，是不是很方便，而且使我们系统更加简洁，都是对不同的底层资源使用同一个接口进行访问。

Spring 提供一个Resource接口来统一这些底层资源一致的访问，而且提供了一些便利的接口，从而能提供我们的生产力。

Spring的Resource接口代表底层外部资源，提供了对底层外部资源的一致性访问接口。

<code>
public interface InputStreamSource {
    InputStream getInputStream() throws IOException;
}
 
public interface Resource extends InputStreamSource {
       boolean exists();
       boolean isReadable();
       boolean isOpen();
       URL getURL() throws IOException;
       URI getURI() throws IOException;
       File getFile() throws IOException;
       long contentLength() throws IOException;
       long lastModified() throws IOException;
       Resource createRelative(String relativePath) throws IOException;
       String getFilename();
       String getDescription();
}
</code>


内置Resource实现

ByteArrayResource
InputStreamResource
FileSystemResource
ClassPathResource
UrlResource
ServletContextResource
VfsResource

---
ResourceLoader接口

ResourceLoader接口用于返回Resource对象；其实现可以看作是一个生产Resource的工厂类。

<code>
public interface ResourceLoader {
       Resource getResource(String location);
       ClassLoader getClassLoader();
}
</code>

getResource接口用于根据提供的location参数返回相应的Resource对象；而getClassLoader则返回加载这些Resource的ClassLoader。

Spring提供了一个适用于所有环境的DefaultResourceLoader实现，可以返回ClassPathResource、UrlResource；
还提供一个用于web环境的ServletContextResourceLoader，它继承了DefaultResourceLoader的所有功能，
又额外提供了获取ServletContextResource的支持。


ResourceLoader在进行加载资源时需要使用前缀来指定需要加载：“classpath:path”表示返回ClasspathResource，
“http://path”和“file:path”表示返回UrlResource资源，如果不加前缀则需要根据当前上下文来决定，
DefaultResourceLoader默认实现可以加载classpath资源


对于目前所有ApplicationContext都实现了ResourceLoader，因此可以使用其来加载资源。

ClassPathXmlApplicationContext：不指定前缀将返回默认的ClassPathResource资源，否则将根据前缀来加载资源；
FileSystemXmlApplicationContext：不指定前缀将返回FileSystemResource，否则将根据前缀来加载资源；
WebApplicationContext：不指定前缀将返回ServletContextResource，否则将根据前缀来加载资源；
其他：不指定前缀根据当前上下文返回Resource实现，否则将根据前缀来加载资源。

---
ResourceLoaderAware接口
ResourceLoaderAware是一个标记接口，用于通过ApplicationContext上下文注入ResourceLoader。

<code>
public interface ResourceLoaderAware {
   void setResourceLoader(ResourceLoader resourceLoader);
}
</code>

---
使用路径通配符加载Resource


前面介绍的资源路径都是非常简单的一个路径匹配一个资源，Spring还提供了一种更强大的Ant模式通配符匹配，从能一个路径匹配一批资源。

Ant路径通配符支持“？”、“*”、“**”，注意通配符匹配不包括目录分隔符“/”：

“?”：匹配一个字符，如“config?.xml”将匹配“config1.xml”；
“*”：匹配零个或多个字符串，如“cn/*/config.xml”将匹配“cn/javass/config.xml”，
但不匹配匹配“cn/config.xml”；而“cn/config-*.xml”将匹配“cn/config-dao.xml”；
 “**”：匹配路径中的零个或多个目录，如“cn/**/config.xml”将匹配“cn /config.xml”，
 也匹配“cn/javass/spring/config.xml”；而“cn/javass/config-**.xml”将匹配“cn/javass/config-dao.xml”，
 即把“**”当做两个“*”处理。
 
Spring提供AntPathMatcher来进行Ant风格的路径匹配。

Spring在加载类路径资源时除了提供前缀“classpath:”的来支持加载一个Resource，还提供一个前缀“classpath*:”来支持加载所有匹配的类路径Resource。

Spring提供ResourcePatternResolver接口来加载多个Resource，该接口继承了ResourceLoader并添加了“Resource[] getResources(String locationPattern)”用来加载多个Resource：

<code>
public interface ResourcePatternResolver extends ResourceLoader {
       String CLASSPATH_ALL_URL_PREFIX = "classpath*:";
       Resource[] getResources(String locationPattern) throws IOException;
}
</code>

Spring提供了一个ResourcePatternResolver实现PathMatchingResourcePatternResolver，它是基于模式匹配的，默认使用AntPathMatcher进行路径匹配，
它除了支持ResourceLoader支持的前缀外，还额外支持“classpath*:”用于加载所有匹配的类路径Resource，ResourceLoader不支持前缀“classpath*:”：

AppliacationContext提供的getResources方法将获取资源委托给ResourcePatternResolver实现，默认使用PathMatchingResourcePatternResolver。
---

注入Resource数组
<code>
<bean id="resourceBean1" class="cn.javass.spring.chapter4.bean.ResourceBean4">
<property name="resources">
        <array>
            <value>cn/javass/spring/chapter4/test1.properties</value>
            <value>log4j.xml</value>
        </array>
    </property>
</bean>
<bean id="resourceBean2" class="cn.javass.spring.chapter4.bean.ResourceBean4">
<property name="resources" value="classpath*:META-INF/INDEX.LIST"/>
</bean>
<bean id="resourceBean3" class="cn.javass.spring.chapter4.bean.ResourceBean4">
<property name="resources">
        <array>
            <value>cn/javass/spring/chapter4/test1.properties</value>
            <value>classpath*:META-INF/INDEX.LIST</value>
        </array>
    </property>
</bean>
</code>

“resourceBean1”就不用多介绍了，传统实现方式；对于“resourceBean2”则使用前缀“classpath*”，看到这大家应该懂的，
加载匹配多个资源；“resourceBean3”是混合使用的；
测试代码在“cn.javass.spring.chapter4.ResourceInjectTest.testResourceArrayInject”。

Spring通过ResourceArrayPropertyEditor来进行类型转换的，
而它又默认使用“PathMatchingResourcePatternResolver”来进行把路径解析为Resource对象。
所有大家只要会使用“PathMatchingResourcePatternResolver”，其它一些实现都是委托给它的，
比如AppliacationContext的“getResources”方法等

---
AppliacationContext实现对各种Resource的支持

+ ClassPathXmlApplicationContext
默认将通过classpath进行加载返回ClassPathResource，提供两类构造器方法：

第一类构造器是根据提供的配置文件路径使用“ResourcePatternResolver ”的“getResources()”接口通过匹配获取资源；即如“classpath:config.xml”

第二类构造器则是根据提供的路径和clazz来构造ClassResource资源。即采用“public ClassPathResource(String path, Class<?> clazz)”构造器获取资源。

+ FileSystemXmlApplicationContext
将加载相对于当前工作目录的“configLocation”位置的资源，注意在linux系统上不管“configLocation”是否带“/”，都作为相对路径；
而在window系统上如“D:/resourceInject.xml”是绝对路径。因此在除非很必要的情况下，不建议使用该ApplicationContext。

<pre>
public class FileSystemXmlApplicationContext{
       public FileSystemXmlApplicationContext(String configLocation);
       public FileSystemXmlApplicationContext(String... configLocations,……);
       
//linux系统，以下全是相对于当前vm路径进行加载
new FileSystemXmlApplicationContext("chapter4/config.xml");
new FileSystemXmlApplicationContext("/chapter4/confg.xml");
//windows系统，第一个将相对于当前vm路径进行加载；
//第二个则是绝对路径方式加载
new FileSystemXmlApplicationContext("chapter4/config.xml");
new FileSystemXmlApplicationContext("d:/chapter4/confg.xml");
 

此处还需要注意：在linux系统上，构造器使用的是相对路径，而ctx.getResource()方法如果以“/”开头则表示获取绝对路径资源，而不带前导“/”将返回相对路径资源。如下：

//linux系统，第一个将相对于当前vm路径进行加载；
//第二个则是绝对路径方式加载
ctx.getResource ("chapter4/config.xml");
ctx.getResource ("/root/confg.xml");
//windows系统，第一个将相对于当前vm路径进行加载；
//第二个则是绝对路径方式加载
ctx.getResource ("chapter4/config.xml");
ctx.getResource ("d:/chapter4/confg.xml");

因此如果需要加载绝对路径资源最好选择前缀“file”方式，将全部根据绝对路径加载。如在linux系统“ctx.getResource (“file:/root/confg.xml”);”。
</pre>