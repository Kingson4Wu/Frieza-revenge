Spring测试框架JUnit4.4

TestContext 可以运行在 JUnit 3.8、JUnit 4.4、TestNG 等测试框架下。
Spring的版本2.5+JUnit4.4+log4j1.2.12



@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"http://www.cnblogs.com/../applicationContext.xml","http://www.cnblogs.com/../daoContext.xml"})
@TransactionConfiguration(transactionManager="transactionManager")
@Transactional
public class KindDaoTest
{
    @Autowired
    KindDao kindDao;

    @Test
    @Rollback(false)
    public void findAll()
    {

    }

}



该单元测试的特点：运用注释，使得编写测试更加简单，以及可以设置是否回滚。

@RunWith(SpringJUnit4ClassRunner.class)

表示该测试用例是运用junit4进行测试，也可以换成其他测试框架

@TransactionConfiguration(transactionManager="transactionManager")为可选项，该项不会影响回滚的设置。

@ContextConfiguration(locations={"http://www.cnblogs.com/../applicationContext.xml","http://www.cnblogs.com/../daoContext.xml"})

该路径的设置时相当于该单元测试所在的路径，也可以用“classpath：xxx.xml”进行设置，该设置还有一个inheritLocations的属性，默认为true,表示子类可以继承该设置。

@Autowired

表示bean自动加载，而不用像之前的两个类要添加一个set的方法。

@Test

表示该方法是测试用例

@Rollback(false)

表示该测试用例不回滚



问题：
1.报错log4j:WARN No appenders could be found for logger (org.springframework.test.context.junit4.SpringJUnit4ClassRunner).
log4j:WARN Please initialize the log4j system properly.

解答：将log4j.properties文件放在/resource下面

2.log4j的版本必须在1.2.12以后



注意：
操作数据库时，数据并没有真正插入到数据库中。

这是TestContext起的作用，调用事务返回了并没有真正插入数据库。


来源： <http://www.cnblogs.com/shipengzhi/articles/2361307.html>



使用Spring+Junit4.4进行测试(使用注解)
http://nottiansyf.iteye.com/blog/345819
使用Junit4.4测试
在类上的配置Annotation
@RunWith(SpringJUnit4ClassRunner.class) 用于配置spring中测试的环境
@ContextConfiguration(Locations="../applicationContext.xml") 用于指定配置文件所在的位置
@Test标注在方法前，表示其是一个测试的方法 无需在其配置文件中额外设置属性.

多个配置文件时{"/applic","/asas"} 可以导入多个配置文件

测试中的事务配置 ，
AbstractTransactionalJUnit38SpringContextTests、 AbstractTransactionalJUnit4SpringContextTests
AbstractTransactionalTestNGSpringContextTests
已经在类级别预先配置了好了事物支持

在普通spring的junit环境中配置事务
在类之前加入注解
@TransactionConfiguration(transactionManagert="txMgr",defaultRollback=false)
@Transactional
在方法中主要使用的Annotation包括
@TestExecutionListeners({})---用于禁用默认的监听器 否着需要通过@contextconfiguration配置一个ApplicationContext；

@BeforeTransaction
@Before
@Rollback(true)
@AfterTransaction
@NotTransactional

Junit4.4下支持类，方便基于junit4.4的测试
AbstractJUnit4SpringContextTests：

AbstractTransactionalJUnit4SpringContextTests：
需要在applicationContext中定义一个datasource

2009年3月9日
目前Spring2.5只支持4.4的Junit进行测试
下面是一个简单的测试Demo

 1 package com.gameplus.service.webService;
 2
 3 import javax.annotation.Resource;
 4
 5 import org.junit.Test;
 6 import org.junit.runner.RunWith;
 7 import org.springframework.test.context.ContextConfiguration;
 8 import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
 9
10 @RunWith(SpringJUnit4ClassRunner.class)
11 @ContextConfiguration(locations={"../../../../applicationContext.xml","../../../../applicationDatasource.xml"})
12 public class UserServiceTest  {
13     @Resource
14     private IUserService userService;
15
16     @Test
17     public void testAddOpinion1() {
18         userService.downloadCount(1);
19         System.out.println(1);
20     }
21     @Test
22     public void testAddOpinion2() {
23         userService.downloadCount(2);
24         System.out.println(2);
25     }
26 }
27

注意需要新的Jar包如下
javassist-3.4.GA.jar
hibernate3.jar
hibernate-annotations.jar
尤其注意用新版的,旧版会出现类未找到的异常

来源： <http://www.blogjava.net/titanaly/archive/2011/11/30/365230.html>



例子：
package brand;
import com.abc.service.rankserver.IGetBrandListFromRankServerService;
import com.abc.service.rankserver.domain.SortBrandInfo;
import com.abc.service.rankserver.domain.SortBrandList2RankServerParam;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;
/**
 * Created by peikun.wang on 2014/5/30.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:config/spring/applicationContext.xml")
public class GetBrandListFromRankServerServiceTest {
    @Autowired
    private IGetBrandListFromRankServerService rankServerService;
    @Test
    public void testGetBrandList() {
        SortBrandList2RankServerParam param = new SortBrandList2RankServerParam("0", null, null, null, "abc_NH", new ArrayList<Integer>(1), null, "0", "0", "127.0.0.1", "android", "wifi", null, "1", "C10");
        System.out.println("request params: " + param.toString());
        Map<String, SortBrandInfo> brandSort = rankServerService.getBrandList(param);
        if (brandSort != null) {
            Set<Map.Entry<String, SortBrandInfo>> entrySet = brandSort.entrySet();
            for (Map.Entry<String, SortBrandInfo> e : entrySet) {
                System.out.println(e.toString());
            }
        }
    }
}
