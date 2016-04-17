package P2P;


import javax.swing.*;
import java.util.*;
import java.io.*;

public class TransFileManager extends Thread {
    public TransFileManager() {
        try {
            jbInit();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private JTabbedPane jtp;

    private boolean running = false;

    private ArrayList paneList = new ArrayList();

    private ArrayList threadList = new ArrayList();

    private Server s;

    int maxThreadNum = 5;

    int port=5000;

    //构造函数
    public TransFileManager(JTabbedPane jtp) {
        this.jtp = jtp;
        s = new Server(threadList);
        SocketThread st;
        s.start();
    }

    public void run() {
        running = true;
        SocketThread st;
        TransFilePanel tfp;
        int threadListSize;
        int paneListSize;
        while (running) {
            threadListSize = threadList.size();
            paneListSize = paneList.size();
            for (int i = 0; i < threadListSize; i++) {
                st = (SocketThread) threadList.get(i);
                if (paneListSize <= i) {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException ex2) {
                    }
                    tfp = new TransFilePanel(st.isFileSender());
                    jtp.addTab(st.getFileName(), tfp);
                    paneList.add(tfp);
                    tfp.jtaFileTransStatus.setText("文件发送  目标IP："+st.getIP()+"  端口："+st.getPort()+"\n\n正在与对方建立连接...");
                } else {
                    tfp = (TransFilePanel) paneList.get(i);
                }
                tfp.setStatus(st.getStatus());
                if (tfp.isCanneled) {
                    st.cancelTrans();
                }
                switch (st.getStatus()) {
                case SocketThread.FILE_TRANS_STATUS_WAITFORCONFIRM: {
                    if (st.isFileSender()) {
                        tfp.jtaFileTransStatus.setText(st.getFileTransMessage()+"\n\n连接成功！  等待对方回应...");
                    } else {
                        tfp.jtaFileTransStatus.setText(st.getFileName() + " (" +
                                st.fileSize + " B)  是否接收？\n"+"来自："+st.getIP()+"\n简短附言："+st.getMessage());
                    }
                    if (tfp.isConfirm) {
                        if(tfp.fileName!=null){
                            File aFile = new File(tfp.fileName);
                            long off=0,size;
                            if (aFile.exists()) {
                                File temFile=new File(aFile.getAbsolutePath()+".tmp");
                                if(temFile!=null){
                                    try {
                                        DataInputStream dis=new DataInputStream(new
                                                FileInputStream(temFile));
                                        off = dis.readLong();
                                        System.out.println(off);
                                        size=dis.readLong();
                                        if(off!=new File(tfp.fileName).length()||size!=st.fileSize){
                                            off=0;
                                        }
                                    } catch (IOException ex1) {
                                        off=0;
                                    }
                                }
                            }
                            st.confirmReceiveFile(true,tfp.fileName,off);
                        }
                        else{
                            st.confirmReceiveFile(false,"",0);
                        }
                    }
                    break;
                }
                case SocketThread.FILE_TRANS_STATUS_CONTEXT: {
                    double progress = st.getTransFileLength() * 1.0 /
                                      st.fileSize * 100;
                    double transSpeed = (st.getTransFileLength() -
                                         tfp.transFileLength) / 1024 * 2;
                    tfp.transFileLength = st.getTransFileLength();
                    tfp.jpgbFileTrans.setValue((int) progress);
                    tfp.jtaFileTransStatus.setText(st.getFileTransMessage()+"\n\n");
                    tfp.jtaFileTransStatus.setText(tfp.jtaFileTransStatus.getText()+"传输速度：" + (int) transSpeed + "k/s"+"  已完成：" + (int) progress +
                            "%");
                    break;
                }
                case SocketThread.FILE_TRANS_STATUS_SUCCESS: {
                    tfp.jtaFileTransStatus.setText(st.getFileTransMessage()+"\n\n传输成功！");
                    if(!st.isFileSender()){
                        new File(tfp.fileName+".tmp").delete();
                    }
                    if (tfp.isClosed) {
                        threadList.remove(i);
                        paneList.remove(i);
                        jtp.remove(i);
                        i--;
                        threadListSize--;
                        paneListSize--;
                    }
                    break;
                }
                case SocketThread.FILE_TRANS_STATUS_FAIL: {
                    tfp.jtaFileTransStatus.setText(st.getFileTransMessage()+"\n\n传输失败！" + st.getErrorMessage());
                    if (tfp.isClosed) {
                        threadList.remove(i);
                        paneList.remove(i);
                        jtp.remove(i);
                        i--;
                        threadListSize--;
                        paneListSize--;
                    }
                    break;
                }
                }
            }
            try {
                Thread.sleep(500);
            } catch (InterruptedException ex) {
            }
        }
    }

    public void sendFile(String serverName, int port, String fileName,String message) {
        if (threadList.size() < maxThreadNum) {
            SocketThread st = new SocketThread(serverName, port, fileName,message);
            st.start();
            threadList.add(st);
        }
    }

    public void setPort(int port) {
        s.stopThread();
        s = new Server(threadList);
        s.port = port;
        this.port=port;
        s.start();
    }

    public void setMaxThreadNum(int num) {
        this.maxThreadNum = num;
        s.maxThreadNum=num;
    }

    public void close(){
        s.stopThread();
        SocketThread st;
        for(int i=0;i<threadList.size();i++){
            st=(SocketThread)threadList.get(i);
            st.stopThread();
        }
    }

    private void jbInit() throws Exception {
    }
}
