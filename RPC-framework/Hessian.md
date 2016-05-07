<http://www.tuicool.com/articles/u2eIvym>
hessian是基于http协议的，使用自己的序列化机制，这里和RMI不同，RMI是使用java的序列化机制，包路径不能改变。 
hessian是一套用于建立web service的简单的二进制协议，用于替代基于XML的web service，是建立在rpc上的，hessian有一套自己的序列化格式将数据序列化成流，然后通过http协议发送给服务器，看源码发现其实是使用
HttpURLConnection和servlet建立连接，然后发送流

Http是一种应用层网络协议,而hession是一种远程调用协议。
