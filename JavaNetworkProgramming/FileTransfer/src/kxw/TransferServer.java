package kxw;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TransferServer {

    private int defaultBindPort = Constants.DEFAULT_BIND_PORT;    //默认监听端口号为10000  
    private int tryBindTimes = 0;           //初始的绑定端口的次数设定为0  

    private ServerSocket serverSocket;      //服务套接字等待对方的连接和文件发送  

    private ExecutorService executorService;    //线程池  
    private final int POOL_SIZE = 4;            //单个CPU的线程池大小   

    /**
     * 不带参数的构造器，选用默认的端口号 
     * @throws Exception
     */
    public TransferServer() throws Exception{
        try {
            this.bingToServerPort(defaultBindPort);  //来自下面的函数
            executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() * POOL_SIZE);
            System.out.println("开辟线程数 ： " + Runtime.getRuntime().availableProcessors() * POOL_SIZE);
        } catch (Exception e) {
            throw new Exception("绑定端口不成功!");
        }
    }

    /**
     * 带参数的构造器，选用用户指定的端口号 
     * @param port
     * @throws Exception
     */
    public TransferServer(int port) throws Exception{
        try {
            this.bingToServerPort(port);
            executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() * POOL_SIZE);
            //Runtime的availableProcessors()方法返回当前系统的CPU的数目
            //系统的CPU越多，线程池中工作线程的数目也越多
        } catch (Exception e) {
            throw new Exception("绑定端口不成功!");
        }
    }

    private void bingToServerPort(int port) throws Exception{
        try {
            serverSocket = new ServerSocket(port);
            System.out.println(port);
            System.out.println("服务启动!");
        } catch (Exception e) {
            this.tryBindTimes = this.tryBindTimes + 1;
            port = port + this.tryBindTimes;  //修改绑定端口
            if(this.tryBindTimes >= 20){
                throw new Exception("您已经尝试很多次了，但是仍无法绑定到指定的端口!请重新选择绑定的默认端口号");
            }
            //递归绑定端口  
            this.bingToServerPort(port); //递归 调用
        }
    }

    public void service(){
        Socket socket = null;
        while (true) {
            try {
                socket = serverSocket.accept(); //等待客户端连接 
                executorService.execute(new Handler(socket));  //Handler是下面定义的线程类
                //没接收到一个请求开启一个线程
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    class Handler implements Runnable{
        private Socket socket;

        public Handler(Socket socket){
            this.socket = socket;
        }

        public void run() {

            System.out.println("New connection accepted " + socket.getInetAddress() + ":" + socket.getPort());

            DataInputStream dis = null;
            DataOutputStream dos = null;

            int bufferSize = 8192;
            byte[] buf = new byte[bufferSize];

            try {
                dis = new DataInputStream(new BufferedInputStream(socket.getInputStream())); // 
                String savePath = Constants.RECEIVE_FILE_PATH + dis.readUTF();  //接受文件后的保存路径，readUTF()文件名字
                long length = dis.readLong();  //文件大小
                dos = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(savePath)));  //

                int read = 0;
                long passedlen = 0;
                while ((read = dis.read(buf)) != -1) {
                    passedlen += read;
                    dos.write(buf, 0, read);
                    System.out.println("文件[" + savePath + "]已经接收: " + passedlen * 100L/ length + "%"); //已经接受百分比 
                }
                System.out.println("文件: " + savePath + "接收完成!");

            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("接收文件失败!");
            }finally{
                try {
                    if(dos != null){
                        dos.close();
                    }
                    if(dis != null){
                        dis.close();
                    }
                    if(socket != null){
                        socket.close();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) throws Exception{
        new TransferServer().service();
    }
}  