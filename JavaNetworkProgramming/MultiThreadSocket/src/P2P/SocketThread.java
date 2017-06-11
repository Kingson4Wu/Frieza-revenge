package P2P;



import java.net.Socket;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.*;
import java.net.*;

/**
 * Socket线程
 */
public class SocketThread extends Thread {
    public SocketThread() {
        try {
            jbInit();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public final static int CACHE_SIZE = 10240; //缓存大小

    public final static int FILE_TRANS_STATUS_FILENAME = 0x01; //文件名传输状态

    public final static int FILE_TRANS_STATUS_CONTEXT = 0x02; //文件内容传输状态

    public final static int FILE_TRANS_STATUS_WAITFORCONFIRM = 0x03; //等待确认接收文件

    public final static int FILE_TRANS_STATUS_SUCCESS = 0x04; //文件传输成功

    public final static int FILE_TRANS_STATUS_FAIL = 0x05; //文件传输失败

    public final static int FILE_TRANS_STATUS_CANCELTRANS = 0x06; //取消文件传输

    public final static int PACKAGE_TYPE_FILENAME = 0x01; //文件名包

    public final static int PACKAGE_TYPE_CONTEXT = 0x02; //文件内容包

    public final static int PACKAGE_TYPE_CONFIRMRECEIVE = 0x03; //是否接收文件

    private Socket aSocket; //套接字

    private String serverName; //服务器名称

    private DataInputStream dis; //输入流

    private DataOutputStream dos; //输出流

    private DataInputStream fDis; //文件输入流

    private RandomAccessFile raf; //文件输出流

    private boolean fileSender = false; //文件发送者

    private boolean running = false; //线程运行

    public int fileTransStatus = 0x0; //文件传输状态

    private File aFile; //传输的文件

    public long fileSize; //文件大小

    private String fileName; //文件名称

    private String errorMessage; //错误消息

    private long transFileLength = 0; //已传输字节数

    private byte [] dataBuf;

    private String message; //验证消息

    private String IP; //目标IP

    private int port; //目标端口

    private boolean fileTransed=false; //文件是否已经开始传输

    int count=0;

    //接收者构造函数
    public SocketThread(Socket aSocket) {
        this.aSocket = aSocket;
        try {
            aSocket.setSoTimeout(300000);
        } catch (SocketException ex) {
        }
        byte[] address = aSocket.getInetAddress().getAddress();
        IP = (address[0] & 0xff) + "." + (address[1] & 0xff) + "." +
             (address[2] & 0xff) + "." + (address[3] & 0xff);
        try {
            dis = new DataInputStream(aSocket.getInputStream());
            dos = new DataOutputStream(aSocket.getOutputStream());
            fileTransStatus = FILE_TRANS_STATUS_FILENAME;
        } catch (IOException ex) {
            setError("创建连接错误！");
        }

        try {
            aSocket.setReceiveBufferSize(CACHE_SIZE*2);
        } catch (SocketException ex1) {
            ex1.printStackTrace();
        }
        dataBuf=new byte[CACHE_SIZE+100];
    }

    //发送者构造函数
    public SocketThread(String serverName, int portNo, String fileName,
                        String message) {
            aFile = new File(fileName);
            this.fileName = aFile.getName();
            this.fileSize = fileSize;
            fileSender = true;
            if (message != null) {
                this.message = message;
            }
            this.IP = serverName;
            this.port = portNo;
            dataBuf=new byte[CACHE_SIZE];
    }

    //线程执行函数
    @Override
    public void run() {
        running = true;
        if (fileSender) {
            try {
                aSocket = new Socket(IP, port);
                aSocket.setSoTimeout(300000);
                aSocket.setSendBufferSize(CACHE_SIZE*2);
                dos = new DataOutputStream(aSocket.getOutputStream());
                dis = new DataInputStream(aSocket.getInputStream());
                fDis = new DataInputStream(new FileInputStream(aFile));
                fileTransStatus = FILE_TRANS_STATUS_FILENAME;
            } catch (UnknownHostException ex1) {
                ex1.printStackTrace();
                setError("连接服务器错误！");
            } catch (IOException ex1) {
                ex1.printStackTrace();
                setError("创建连接错误！");
            }

        } while (running) {
            if (fileSender) {
                sendFile();
            } else {
                receiveFile();
            }
            try {
                Thread.sleep(6);
            } catch (InterruptedException ex) {
            }
        }
    }

    //从socket读
    private int readFromSocket(byte[] data) throws IOException {
        int length = 0;
        length = fDis.read(data);
        return length;
    }

    //从socket读
    private int readFromSocket() throws IOException {
        int buf = 0;
        buf = dis.readInt();
        return buf;
    }

    //从文件读
    private int readFromFile(byte[] data,int off,int length) {
        int len=0;
        try {
            len = fDis.read(data,off,length);
        } catch (IOException ex) {
            setError("文件读取错误！");
        }
        return len;
    }

    //写入socket
    private void writeToSocket(byte[] data) throws IOException {
        dos.write(data);
    }

    //写入文件
    private void writeToFile(byte[] data,int off,int length) throws IOException {
        raf.write(data,off,length);
    }

    //写入socket
    private void writeToSocket(int data) throws IOException {
        dos.writeInt(data);
    }

    private void writeToSocket(long data) throws IOException {
        dos.writeLong(data);
    }

    private long readLongFromSocket() throws IOException {
        return dis.readLong();
    }

    //打包
    private byte[] doPackage(byte[] data, int length) throws IOException {
        ByteArrayOutputStream buf = new ByteArrayOutputStream();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        DataOutputStream bufDos = new DataOutputStream(buf);
        DataOutputStream baosDos = new DataOutputStream(baos);
        switch (fileTransStatus) {
        case FILE_TRANS_STATUS_FILENAME: {
            bufDos.writeInt(PACKAGE_TYPE_FILENAME);
            bufDos.writeInt(fileName.getBytes().length);
            bufDos.write(fileName.getBytes());
            bufDos.writeLong(fileSize);
            if (message!=null) {
                bufDos.writeInt(message.getBytes().length);
                bufDos.write(message.getBytes());
            } else {
                bufDos.writeInt(-1);
            }
            baosDos.writeInt(buf.toByteArray().length);
            baosDos.write(buf.toByteArray());
            break;
        }
        case FILE_TRANS_STATUS_CONTEXT: {
            bufDos.writeInt(PACKAGE_TYPE_CONTEXT);
            if ((transFileLength + length) >= fileSize) {
                bufDos.writeInt(0);
            } else {
                bufDos.writeInt(1);
            }
            bufDos.writeInt(length);
            bufDos.write(data, 0, length);
            baosDos.writeInt(buf.toByteArray().length);
            baosDos.write(buf.toByteArray());
            break;
        }
        }
        return baos.toByteArray();
    }

    //停止线程
    public void stopThread() {
        running = false;
        try {
            if (dis != null) {
                dis.close();
            }
            if (dos != null) {
                dos.close();
            }
            if (fDis != null) {
                fDis.close();
            }
            if (raf != null) {
                raf.close();
            }
        } catch (Exception ex) {
        }
    }

    //解包
    private void upPackage(byte[] data) {
        ByteArrayInputStream bias = new ByteArrayInputStream(data);
        DataInputStream biasDis = new DataInputStream(bias);
        int type = 0;
        try {
            type = biasDis.readInt();
        } catch (SocketTimeoutException ex) {
            setError("网络超时！");
        } catch (IOException ex1) {
            setError("对方取消了文件传输或网络错误！");
        }
        switch (type) {
        case PACKAGE_TYPE_FILENAME: {
            try {
                int length = biasDis.readInt();
                bias.read(dataBuf,0,length);
                fileName = new String(dataBuf,0,length);
                fileSize = biasDis.readLong();
                length = biasDis.readInt();
                if (length !=-1) {
                    bias.read(dataBuf,0,length);
                    message = new String(dataBuf,0,length);
                }
                fileTransStatus = FILE_TRANS_STATUS_WAITFORCONFIRM;
            } catch (SocketTimeoutException ex) {
                setError("网络超时！");
            } catch (IOException ex) {
                setError("对方取消了文件传输或网络错误！");
            }
            break ;
        }
        case PACKAGE_TYPE_CONTEXT: {
            try {
                int flag = biasDis.readInt();
                int length = biasDis.readInt();
                bias.read(dataBuf,0,length);
                writeToFile(dataBuf,0,length);
                transFileLength += length;
                if (flag == 0) {
                    fileTransStatus = FILE_TRANS_STATUS_SUCCESS;
                    stopThread();
                }
            } catch (SocketTimeoutException ex) {
                setError("网络超时！");
            } catch (IOException ex) {
                setError("对方取消了文件传输或网络错误！");
            }
            break ;
        }
        }
    }

    //发送文件
    private void sendFile() {
        int length;
        switch (fileTransStatus) {
        case FILE_TRANS_STATUS_FILENAME: {
            try {
                byte [] buf;
                fileName = aFile.getName();
                fileSize = aFile.length();
                buf = doPackage(null, 0);
                writeToSocket(buf);
                fileTransStatus = FILE_TRANS_STATUS_WAITFORCONFIRM;
            } catch (IOException ex) {
                setError("对方取消了文件传输或网络错误！");
            }
            break ;
        }
        case FILE_TRANS_STATUS_WAITFORCONFIRM: {
            int flag;
            try {
                flag = readFromSocket();
                if (flag == 0) {
                    setError("对方拒绝了文件传输！");
                } else {
                    fileTransStatus = FILE_TRANS_STATUS_CONTEXT;
                    transFileLength = readLongFromSocket();
                    fDis.skip(transFileLength);
                    aSocket.setSoTimeout(30000);
                }
            } catch (SocketTimeoutException ex) {
                setError("网络超时！");
            } catch (IOException ex) {
                setError("对方取消了文件传输或网络错误！");
            }
            break ;
        }
        case FILE_TRANS_STATUS_CONTEXT: {
            length = readFromFile(dataBuf,0,CACHE_SIZE);
            try {
                writeToSocket(doPackage(dataBuf, length));
                transFileLength += length;
                if (transFileLength >= fileSize) {
                    fileTransStatus = FILE_TRANS_STATUS_SUCCESS;
                    Thread.sleep(1000);
                    stopThread();
                }
            } catch (IOException ex) {
                setError("对方取消了文件传输或网络错误！");
            } catch (InterruptedException ex1) {

            }
            count++;
            if(count==2){
                //stopThread();
            }
            break ;
        }
        }
    }

    //接收文件
    private void receiveFile() {
        if (fileTransStatus == FILE_TRANS_STATUS_CONTEXT ||
            fileTransStatus == FILE_TRANS_STATUS_FILENAME) {
            try {
                int length = dis.readInt();
                int len=dis.read(dataBuf,0,length);
                while(len<length){
                    len=len+dis.read(dataBuf,len,length-len);
                }
                    upPackage(dataBuf);
            } catch (SocketTimeoutException ex) {
                setError("网络超时！");
                ex.printStackTrace();
            } catch (IOException ex) {
                setError("对方取消了文件传输或网络错误！");
            }
        } else if (fileTransStatus == FILE_TRANS_STATUS_CANCELTRANS) {
            try {
                doPackage(null, 0);
            } catch (IOException ex1) {
            }
            setError("已取消文件传输！");
        }
    }

    //确认是否接收文件
    public void confirmReceiveFile(boolean flag, String fileName, long off) {
        if (flag) {
            try {

                writeToSocket(1);
                if (off >0) {
                    writeToSocket(off);
                } else {
                    writeToSocket(0L);
                    File aFile=new File(fileName);
                    if(aFile.exists()){
                        System.out.println("*");
                        aFile.delete();
                    }
                }
                raf = new RandomAccessFile(fileName, "rws");
                this.fileName = fileName;
                fileTransStatus = FILE_TRANS_STATUS_CONTEXT;
                fileTransed=true;
                raf.seek(off);
            } catch (FileNotFoundException ex) {
                setError("创建文件错误！");
            } catch (IOException ex) {
                setError("对方取消了文件传输或网络错误！");
            }
            transFileLength = off;
        } else {
            try {
                writeToSocket(0);
                writeToSocket(0L);
            } catch (IOException ex) {
                setError("对方取消了文件传输或网络错误！");
            }
        }
    }


//返回已传输字节数
    public long getTransFileLength() {
        return transFileLength;
    }

//设置错误消息
    public void setError(String errorMessage) {
        fileTransStatus = FILE_TRANS_STATUS_FAIL;
        this.errorMessage = errorMessage;
        if (!fileSender&&fileTransed) {
            File tmpFile = new File(fileName + ".tmp");
            try {
                DataOutputStream dos = new DataOutputStream(new
                        FileOutputStream(
                                tmpFile));
                dos.writeLong(transFileLength);
                dos.writeLong(fileSize);
                dos.close();
            } catch (IOException ex) {
            }
        }
        stopThread();
    }


//返回错误消息
    public String getErrorMessage() {
        return errorMessage;
    }

//返回传输状态
    public int getStatus() {
        return fileTransStatus;
    }

//是否是文件发送者
    public boolean isFileSender() {
        return fileSender;
    }

    public void cancelTrans() {
        if (fileTransStatus == FILE_TRANS_STATUS_WAITFORCONFIRM) {
            confirmReceiveFile(false, null, 0);
        }
        setError("已取消文件传输！");
    }

    public String getMessage() {
        return message;
    }

    public String getIP() {
        return IP;
    }

    public int getPort() {
        return port;
    }

    public String getFileName() {
        if (fileName.length() > 10) {
            return fileName.substring(0, 10) + "..." +
                    fileName.substring(fileName.lastIndexOf('.'),
                                       fileName.length());
        } else {
            return fileName;
        }
    }

    public String getFileTransMessage(){
        if(fileSender){
            return "文件发送 目标IP: "+getIP()+" 端口:"+getPort();
        }
        else{
            return "文件接收 来自: "+getIP();
        }
    }

    private void jbInit() throws Exception {
    }
}
