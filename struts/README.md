1、配置文件格式不同
2、struts1有from类和action类，属性封装from类里，在struts2里只有action类，属性封装action类里
3、页面调用是struts1 是*.do  struts2 是*.action
我认为struts2要比struts1，简单、方便、快捷。就是用着比struts1好
以上是我开发的体会，下面是我找的资料
主要表现在如下几个方面：
　　在Action的实现方面：Struts1要求必须统一扩展自Action类，而Struts2中可以是一个普通的POJO。
　　线程模型方面：Struts1的Action工作在单例模式，一个Action的实例处理所有的请求。Struts2的Action是一个请求对应一个实例。没有线程安全方面的问题。
　　Servlet依赖方面：Struts1的Action依赖于Servlet API，比如Action的execute方法的参数就包括request和response对象。这使程序难于测试。Struts2中的Action不再依赖于Servlet API，有利于测试，并且实现TDD。
　　封装请求参数：Struts1中强制使用ActionForm对象封装请求的参数。Struts2可以选择使用POJO类来封装请求的参数，或者直接使用Action的属性。
　　表达式语言方面：Struts1中整合了EL，但是EL对集合和索引的支持不强，Struts2整合了OGNL（Object Graph NavigationLanguage）。
　　绑定值到视图技术：Struts1使用标准的JSP，Struts2使用“ValueStack”技术。
　　类型转换：Struts1中的ActionForm基本使用String类型的属性。Struts2中使用OGNL进行转换，可以更方便的使用。
　　数据校验：Struts1中支持覆盖validate方法或者使用Validator框架。Struts2支持重写validate方法或者使用XWork的验证框架。
　　Action执行控制的对比：Struts1支持每一个模块对应一个请求处理，但是模块中的所有Action必须共享相同的生命周期。Struts2支持通过拦截器堆栈为每一个Action创建不同的生命周期。
