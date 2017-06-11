package UDP;

import java.io.*;
import java.net.*;

public class ServerUDP {
    public static void main(String[] args) {
        DatagramSocket ds = null;
        DatagramPacket dpOut = null;
        DatagramPacket dpIn = null;
        byte[] buffer = new byte[128];
        try {
            //从客户端读取数据
            ds = new DatagramSocket(8888);
            while (true) {
                dpIn = new DatagramPacket(buffer, buffer.length);
                ds.receive(dpIn);
                String str = new String(buffer);
                str = str.toUpperCase();//变成大写
                //将读到的数据写回
                dpOut = new DatagramPacket(str.getBytes(), str.getBytes().length, dpIn.getAddress(), dpIn.getPort());
                ds.send(dpOut);
            }

        } catch (IOException e) {
            System.out.println("系统出错");
        } finally {
            ds.close();
        }
    }
}