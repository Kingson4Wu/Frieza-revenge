so_timeout这个必须设置，否则pool就可能呆死在这里了


这个是控制并发量的，两个都要设置
cm.setDefaultMaxPerRoute(100);
cm.setMaxTotal(200);

不知道楼主那么大的并发不是针对相同的机器么，相同的话，keepalive能提升不少性能，减少了连接次数
time_wait 需要设置reuseaddr

下面是官方默认给的
httpClient.setHttpRequestRetryHandler(new DefaultHttpRequestRetryHandler());
httpClient.setReuseStrategy(new DefaultConnectionReuseStrategy());
httpClient.setKeepAliveStrategy(new DefaultConnectionKeepAliveStrategy());

官方的例子还给了一个定期清理pool中connection的例子

```java
class IdleConnectionMonitorThread extends Thread {  
private final ClientConnectionManager connMgr;  
private volatile boolean shutdown;  
  
public IdleConnectionMonitorThread(ClientConnectionManager connMgr) {  
    super();  
    this.setName("idle-connection-monitor");  
    this.setDaemon(true);  
    this.connMgr = connMgr;  
    this.start();  
}  
  
@Override  
public void run() {  
    try {  
        while (!shutdown) {  
            synchronized (this) {  
                wait(5000);  
                // Close expired connections  
                connMgr.closeExpiredConnections();  
                // Optionally, close connections  
                // that have been idle longer than 30 sec  
                connMgr.closeIdleConnections(60, TimeUnit.SECONDS);  
            }  
        }  
    } catch (InterruptedException ex) {  
        // terminate  
    }  
}  
  
public void shutdown() {  
    synchronized (this) {  
        shutdown = true;  
        notifyAll();  
    }  
}  
```

reference:<http://jinnianshilongnian.iteye.com/blog/2089792>

---

vips-common-http-client

---

从原生的 HttpUrlConnection 到经典的 Apache 的 HttpClient，再到对前面这些网络基础框架的封装，比如 Volley、Async Http Client，如今 Retrofit

