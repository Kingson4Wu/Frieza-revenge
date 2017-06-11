package model;

/**
 * 处理各种请求的线程
 */

import java.io.*;

import java.net.*;
import java.util.StringTokenizer;

import view.MainWin;


@SuppressWarnings("unused")
public class WebServerThread extends Thread{

	Socket s=null;
	String path=null;
	PrintStream pout;
	boolean isHttp1;
	public WebServerThread(Socket s,String path)
	{
		this.s=s;
		this.path=path;
		setPriority(4);
	}

	@Override
	public void run()
	{

		try {
			BufferedReader br = (new BufferedReader(new InputStreamReader(s.getInputStream())));//得到客户端的输入流
			MainWin.jp2_jl3.setText(s.getSendBufferSize()+"");//得到socket的缓冲区大小为8192
			OutputStream out = s.getOutputStream();//得到客户端socket的输出流
			pout = new PrintStream(out);
			/*客户端通过浏览器发送的请求信息*/
			String requestCmds="";
			/*读出第一行请求信息，其中有请求的类型、请求路径、HTTP版本三个参数，如:"GET / HTTP/1.1"*/
			String requestLine = br.readLine();
			System.out.print("------"+requestLine);
			requestCmds+=requestLine;

			if (requestLine == null)
			{
				error(400, "Empty Request");
				MainWin.jta.append("Bad Request");
			}

			//System.out.println("--------------"+requestLine+"-----------------------");
			//浏览器传入http服务器的字符串GET /index.html HTTP/1.1


			if (requestLine.toLowerCase().indexOf("http/1.") != -1) {
				String string_0_;
				while (!"".equals(string_0_ = br.readLine())//请求不为空
						&& string_0_ != null) {
					requestCmds+="\n"+string_0_;
				}
				isHttp1 = true;
			}
			/*如果想查看HTTP请求，可以将其打印出来*/
			System.out.println("requestString:"+requestLine);
			String[] request=requestLine.split(" ");//有空格分为3个数据存到数组
			if (request.length< 2)
			{
				error(400, "Bad Request");
				MainWin.jta.append("Bad Request");
			}
			else {
				String string_1_ = request[0];//如"GET"
				/*只允许GET请求类型*/
				System.out.println("请求方法为："+string_1_);
				if ("GET".equals(string_1_))
				{
					serveFile(request[1]);//request[1]为请求路径，如"/"
					System.out.println("请求路径为"+request[1]);
					MainWin.jta.append("请求资源为"+request[1]+"\n");
				}
				else
				{
					error(400, "Bad Request");
					MainWin.jta.append("Bad Request");
				}
			}
			/*与客户端断开连接*/
			s.close();
		} catch (IOException ioexception) {
			System.out.println("I/O error " + ioexception);
			try {
				s.close();
			} catch (Exception exception) {
				/* empty */
			}
		}
	}


	private void serveFile(String requestPath)  {
		if ("/".equals(requestPath)){
			/**
			 * 取首页文件，首页文件可以为index.html或index.htm
			 */
			requestPath = "/index.html";
			if(path==null){
				path=new File("").getAbsolutePath();
				//得到程序运行的工作目录
				//D:\Workspaces\MyEclipse 8.5\HttpWebServer
			}
			if(!new File(path+requestPath).exists()){
				requestPath="/index.htm";
			}
		}
		try {
			sendFileData(requestPath);

		} catch (Exception e) {
			error(404, "访问出错！");
			MainWin.jta.append("Object Not Found"+"\n");
			e.printStackTrace();
		}
	}

	private void sendFileData(String requestPath) throws IOException,FileNotFoundException {
		InputStream inputstream = new FileInputStream(path+requestPath);
		if (inputstream == null) {
			throw new FileNotFoundException(requestPath);
		}
		if (isHttp1) {
			pout.println("HTTP/1.0 200 Document follows");
			pout.println("Content-length: " + inputstream.available());
			if (requestPath.endsWith(".gif")) {
				pout.println("Content-type: image/gif");
			} else if (requestPath.endsWith(".jpg")) {
				pout.println("Content-type: image/jpeg");
			} else if (requestPath.endsWith(".html") || requestPath.endsWith(".htm")) {
				pout.println("Content-Type: text/html");
			} else {
				pout.println("Content-Type: application/octet-stream");
			}
			pout.println();
		}
		/*缓冲区设为8K*/
		byte[] is = new byte[8*1024];
		int length=0;
		while((length=inputstream.read(is))!=-1){//把文件读入，返回访问的浏览器
			MainWin.jp2_jl4.setText(length+"");
			pout.write(is, 0, length);
		}
		pout.flush();
	}
	private void error(int erorcd, String erortx) {
		erortx = "<html><h1>" + erortx + "</h1></html>";
		if (isHttp1) {
			pout.println("HTTP/1.0 " + erorcd + " " + erortx);
			pout.println("Content-type: text/html");
			pout.println("Content-length: " + erortx.length() + "\n");
		}
		pout.println(erortx);
	}
}
