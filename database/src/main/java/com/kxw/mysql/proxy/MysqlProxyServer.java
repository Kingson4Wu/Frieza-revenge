package com.kxw.mysql.proxy;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.core.net.NetClient;
import io.vertx.core.net.NetServer;
import io.vertx.core.net.NetSocket;


/**
 * 大部人都知道使用代理的好处，毕竟，随着互联网越来越普及，互联网系统越来越庞大、复杂，性能要求越来越高，为了让整个系统具有更好的扩展性、更高的性能、解藕等多种特性，在数据库层面引入代理层是目前互联网系统常见的架构设计方案。总的来说，在数据库层面引入代理会带来以下好处：
 将不同类型的请求分发的不同的server以此实现读写分离、负载均衡；
 来自不同客户端的请求分发到不同的server实现后端多租户数据库服务，当然，类似的原理还可以实现分库分表、一个请求写到多个server或者不同的源端如消息队列；
 监控统计客户端的请求情况，请求分布统计、请求类型等，以此来优化数据库的使用；
 总之，可以实现你想要的诸多功能。
 */
/**
 * vert.x是基于jvm、事件驱动、异步IO、响应式编程工具套件，底层网络通信使用netty4，是一个非常优秀的java开发框架（当然，严格意义上讲是工具套件），使用vert.x可以快速构建的各种应用，并且天生分布式，集群管理。
 另外，实现一个代理服务器远没有如此简单，根据需求的不同，复杂度也不同，这里仅仅是展示实现代理的核心代码，实现了最基本的代理功能，当然了，一切复杂的需求都可以基于上面的代码进行改造扩展.
 */

/**
 * @author sneaky
 * @since 1.0.0
 */
public class MysqlProxyServer {
    private static final Logger logger = LoggerFactory.getLogger(MysqlProxyServer.class);

    public static void main(String[] args) {
        Vertx.vertx().deployVerticle(new MysqlProxyServerVerticle());
    }

    public static class MysqlProxyServerVerticle extends AbstractVerticle {
        private final int port = 3306;
        private final String mysqlHost = "10.10.0.6";
        @Override
        public void start() throws Exception {
            NetServer netServer = vertx.createNetServer();//创建代理服务器
            NetClient netClient = vertx.createNetClient();//创建连接mysql客户端
            netServer.connectHandler(socket -> netClient.connect(port, mysqlHost, result -> {
                //响应来自客户端的连接请求，成功之后，在建立一个与目标mysql服务器的连接
                if (result.succeeded()) {
                    //与目标mysql服务器成功连接连接之后，创造一个MysqlProxyConnection对象,并执行代理方法
                    new MysqlProxyConnection(socket, result.result()).proxy();
                } else {
                    logger.error(result.cause().getMessage(), result.cause());
                    socket.close();
                }
            })).listen(port, listenResult -> {//代理服务器的监听端口
                if (listenResult.succeeded()) {
                    //成功启动代理服务器
                    logger.info("Mysql proxy server start up.");
                } else {
                    //启动代理服务器失败
                    logger.error("Mysql proxy exit. because: " + listenResult.cause().getMessage(), listenResult.cause());
                    System.exit(1);
                }
            });
        }
    }

    public static class MysqlProxyConnection {
        private final NetSocket clientSocket;
        private final NetSocket serverSocket;

        public MysqlProxyConnection(NetSocket clientSocket, NetSocket serverSocket) {
            this.clientSocket = clientSocket;
            this.serverSocket = serverSocket;
        }

        private void proxy() {
            //当代理与mysql服务器连接关闭时，关闭client与代理的连接
            serverSocket.closeHandler(v -> clientSocket.close());
            //反之亦然
            clientSocket.closeHandler(v -> serverSocket.close());
            //不管那端的连接出现异常时，关闭两端的连接
            serverSocket.exceptionHandler(e -> {
                logger.error(e.getMessage(), e);
                close();
            });
            clientSocket.exceptionHandler(e -> {
                logger.error(e.getMessage(), e);
                close();
            });
            //当收到来自客户端的数据包时，转发给mysql目标服务器
            clientSocket.handler(buffer -> serverSocket.write(buffer));
            //当收到来自mysql目标服务器的数据包时，转发给客户端
            serverSocket.handler(buffer -> clientSocket.write(buffer));
        }

        private void close() {
            clientSocket.close();
            serverSocket.close();
        }
    }
}
