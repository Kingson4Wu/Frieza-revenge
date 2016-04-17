### JAX-RS
<http://segmentfault.com/q/1010000002484009/a-1020000002484552>
java.ws.rs 是 jax-rs 规范中定义的包名。
jax-rs 全程 Java API for RESTful Services
jax-rs 规范 目前版本是 2.0。 规范文档
jax-rs 中定义了
一组启动方式 (以jee作为http容器，还是配合servlet作为http容器)
一组注解 @GET, @POST, @DELETE, @PUT, @Consumes ... 通过 POJO Resource类, 提供Rest服务
其实这不难理解， 就像 JSR 规范中定义了 Servlet 是 以继承 HttpServlet 并重写 doGet, doPost, do... 方法 一样.只要遵循 这套标准的 我们我们都可以称之为 Servlet 程序.
而 Spring MVC 是以 Servlet 为http容器，并自己构建了一套Api，没有遵循 jax-rs 规范。
更直白一点说吧。你写的 Servlet 程序，可以不经过任何修改，放到任何实现 Servlet 容器中运行。
你写的 jax-rs 程序，可以不经任何修改，和任何 jax-rs 框架配合使用。
目前实现 jax-rs 标准的框架有很多
Apache CXF，开源的Web服务框架。
Jersey， 由Sun提供的JAX-RS的参考实现。
RESTEasy，JBoss的实现。
Restlet，由Jerome Louvel和Dave Pawson开发，是最早的REST框架，先于JAX-RS出现。
Apache Wink，一个Apache软件基金会孵化器中的项目，其服务模块实现JAX-RS规范
我就是一个 Jersey 的使用者。
而如果你使用 Spring MVC 开发 Rest 服务，你的代码就不能实现移植。
其实没有好坏之分，Spring拥有一套成熟稳定且强大的解决方案。 所以看你喜欢那种方案。
个人推荐 Spring 。 Spring 强大的依赖注入是很大的吸引力，虽然其他框架也有 依赖注入，但是还是 Spring 的好用。
---
JAX-RS入门 一 ：基础<http://liugang594.iteye.com/blog/1491434>


