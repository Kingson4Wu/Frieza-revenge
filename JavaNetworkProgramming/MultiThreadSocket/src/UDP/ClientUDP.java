package UDP;

import java.net.*;
import java.io.*;

public class ClientUDP {
    /**
     * 客户端UDP演示
     */
    public static void main(String[] args) {
        //连接服务器,发送数据
        byte[] buffer = null;
        DatagramSocket ds = null;
        DatagramPacket dpOut = null;
        try {
            ds = new DatagramSocket(9999);
            while (true) {
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                String str = br.readLine();
                buffer = str.getBytes();
                dpOut = new DatagramPacket(buffer, buffer.length, InetAddress.getByName("localhost"), 8888);
                //客户端主动访问服务端
                ds.send(dpOut);
                //处理收信
                DatagramPacket dpIn = null;
                byte[] bufferIn = new byte[128];
                dpIn = new DatagramPacket(bufferIn, bufferIn.length);
                ds.receive(dpIn);
                System.out.println(new String(bufferIn, 0, buffer.length));
            }
        } catch (Exception e) {
            System.out.println("出现错误");
            e.printStackTrace();
        } finally {
            ds.close();
        }

    }
}