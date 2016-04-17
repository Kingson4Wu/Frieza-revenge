package model;

/**
 * 总的服务器监听
 * 监听到则单开一个线程
 */

//import httpserver.HttpConnection;

import java.io.IOException;
import java.net.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
public class MyWebServer {

	public MyWebServer(String path,int port)
	{
		try {
			System.out.println("我是服务器，在"+port+"监听");
			ServerSocket ss=new ServerSocket(port);
			startServer(path,ss);         //调用启动服务器
			/*while(true)
			{
				Socket s=ss.accept();
				//单开一个线程
				new WebServerThread(s).start();
			}*/
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	@SuppressWarnings("unchecked")
	public void startServer(String httpPath,ServerSocket ss) {                //启动线程池
		@SuppressWarnings("rawtypes")
		ThreadLocal localSocket=new ThreadLocal();
		/*线程池，参数意思依次为：
		 * 线程池中所保存的线程数，包括空闲线程
		 * 线程池中允许的最大线程数
		 * 当线程数大于核心时，此为终止前多余的空闲线程等待新任务的最长时间。以秒为单位
		 * 执行前用于保持任务的队列。此队列仅由保持 execute 方法提交的 Runnable 任务
		 * 执行线程的服务
		 * */
		@SuppressWarnings({ "rawtypes" })
		ThreadPoolExecutor threadPool = new ThreadPoolExecutor(10, 50, 3,TimeUnit.SECONDS, new ArrayBlockingQueue(10),new ThreadPoolExecutor.DiscardOldestPolicy());
		for (;;){
			try {
				System.out.println("haha");
				localSocket.set(ss.accept());          //得到客户端线程
				threadPool.execute(new WebServerThread((Socket)localSocket.get(),httpPath));
			} catch (IOException ioexception) {
				System.out.println(ioexception);
			}
		}
	}
}
