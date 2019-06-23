<https://mp.weixin.qq.com/s?__biz=MjM5MDE0Mjc4MA==&mid=2651014354&idx=2&sn=9a356f184842908ab5004bdcfef1caac&scene=21#wechat_redirect>

1. 基础层代码：本层主要包括两类适配代码：主动适配和被动适配。主动适配代码主要面向前端应用提供 API 网关服务，进行简单的前端数据校验、协议以及格式转换适配等工作。被动适配主要面向后端基础资源（如数据库、缓存等），通过依赖反转为应用层和领域层提供数据持久化和数据访问支持，实现资源层的解耦。

2. 应用层代码：本层代码主要通过调用领域层服务或其他中台应用层服务，完成服务组合和编排形成粗粒度的服务，为前台提供 API 服务。本层代码可进行业务逻辑数据的校验、权限认证、服务组合和编排、分布式事务管理等工作。

3. 领域层代码：本层代码主要实现核心的业务领域逻辑，需要做好领域代码的分层以及聚合之间代码的逻辑隔离。相关的开发方法请查阅 DDD 战术设计相关资料，并遵循相关设计和开发规范。

对代码进行逻辑隔离和分层的主要意义在于：

1、避免各层代码的交叉，保持领域代码的纯洁，保证中台领域层业务逻辑的稳定。

2、业务和代码模型的逻辑保持一致，有利于微服务的拆分和组合。

+ 基于DDD的微服务设计和开发实战:<https://mp.weixin.qq.com/s?__biz=MjM5MDE0Mjc4MA==&mid=2651017086&idx=2&sn=5349a6e8fb4f3ddb326b6b2d8949f23d&chksm=bdbeb72d8ac93e3beab1e729118f7fb8f5fa3bea1d17252be644eee4472599e8e2d9ee0b6f27&xtrack=1&scene=90&subscene=93&sessionid=1561197800&clicktime=1561197863&ascene=56&devicetype=android-28&version=27000435&nettype=WIFI&abtest_cookie=BQABAAoACwASABMAFQAGACOXHgBWmR4AyJkeANyZHgDzmR4ACZoeAAAA&lang=zh_CN&pass_ticket=j2AOFlmgPrXhYh2eFU4bBFogw1p8J6se6aSyA60APG1Dy94StO3sZOvkE%2F2eEyFN&wx_header=1>