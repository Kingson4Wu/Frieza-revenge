package P2P;

//服务器端


import java.io.*;
import java.net.*;
import java.util.List;

public class Server extends Thread {
    public Server() {
        try {
            jbInit();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public int port=5000;
    public int maxThreadNum=5;
    private ServerSocket checkServer = null;
    private SocketThread socketThread = null;
    private Socket aSocket = null;
    private List socketPool; //socket连接池
    private boolean serverRunning = false; //服务器是否运行

    //构造函数
    public Server(List socketPool) {
        this.socketPool = socketPool;
    }

    @Override
    public void run() {
        serverRunning = true;
        try {
            checkServer = new ServerSocket(port);
            checkServer.setSoTimeout(60000);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        while (serverRunning) {
                try {
                    System.out.println("Started...");
                    aSocket = checkServer.accept();//开始监听
                    System.out.println("client connected");
                    socketThread = new SocketThread(aSocket);
                    socketThread.start();
                    if(socketPool.size()>=maxThreadNum){
                        socketThread.stopThread();
                    }
                    else{
                        socketPool.add(socketThread);
                    }
                }
                catch (Exception ex) {
                    ex.printStackTrace();
                }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
        try {
            checkServer.close();
        } catch (IOException ex1) {
        }
    }

    public void stopThread(){
        serverRunning=false;
    }

    private void jbInit() throws Exception {
    }
}
