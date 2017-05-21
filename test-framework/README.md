+ JUnitParams

<pre>

参数化测试
@Test
@Parameters({"17, false", 
             "22, true" })
public void personIsAdult(int age, boolean valid) throws Exception {
  assertThat(new Person(age).isAdult(), is(valid));
}

与标准 JUnit Parametrised 运行器的主要区别：
● 更明确 - 参数是测试方法的参数，而不是类中定义的字段

● 更少代码 - 不需要通过构造器来设置参数

● 可以在一个类里混合参数化或非参数化的方法
● 参数可以由 CSV 文本提供，也可以由专门的参数提供类提供

● 参数提供类可以有任意多个参数提供方法，所以你可以对不同的情况分组

● 可以由测试方法提供参数（而不是其它类或静态方法）

● 可以在 IDE 中看到实际的参数值（JUnit 的 Parametrised 中只有连续编号的参数）
</pre>

<<http://mp.weixin.qq.com/s/P_mD8pTEk91BKevfZaawkw>

+ Jukito

<pre>

结合了 JUnit、Guice 和 Mockito 的力量，听起来很有技术含量。
● 大大降低了自动 mock 的古板，使阅读测试变得容易

● 在测试对象的 API 变化时更有弹性

● 通过 @Inject 注解的字段可以自动注入

● 更容易将对象绑在一起，因此可以将单元测试扩展到部分集成测试

@RunWith(JukitoRunner.class)
public class EmailSystemTest {

  @Inject EmailSystemImpl emailSystem;
  Email dummyEmail;

  @Before
  public void setupMocks(
      IncomingEmails incomingEmails,
      EmailFactory factory) {
    dummyEmail = factory.createDummy();
    when(incomingEmails.count()).thenReturn(1);
    when(incomingEmails.get(0)).thenReturn(dummyEmail);
  }

  @Test
  public void shouldFetchEmailWhenStarting(
      EmailView emailView) {
    // WHEN
    emailSystem.start();

    // THEN
    verify(emailView).addEmail(dummyEmail);
  }
}
</pre>

+ WireMock

<pre>

模拟 HTTP 服务的工具
● HTTP 响应头，匹配 URL、标题和正文模式
● 验证请求
● 在单元测试中运行、独立运行或作为 WAR 应用运行
● 通过 Java API、JSON 文件或者通过 HTTP 获取的 JSON 来进行配置
● 录制/回放存根
● 故障注入
● 针对每个请求的条件代理
● 浏览器代码用于注入请求或更换请求
● 有状态的行为
● 可配置响应延迟

{
    "request": {
        "method": "GET",
        "url": "/some/thing"
    },
    "response": {
        "status": 200,
        "statusMessage": "Everything was just fine!"
    }
}
</pre>

---


Cobertura 是一种开源工具，它通过检测基本的代码，并观察在测试包运行时执行了哪些代码和没有执行哪些代码，来测量测试覆盖率。
除了找出未测试到的代码并发现 bug 外，Cobertura 还可以通过标记无用的、执行不到的代码来优化代码，
还可以提供 API 实际操作的内部信息。Elliotte Rusty Harold 将与您分享如何利用代码覆盖率的最佳实践来使用 Cobertura。



