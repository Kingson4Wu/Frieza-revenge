package com.kxw.stream;


import java.io.*;

public class ByteArrayOutputStreamTest {

    public static void main(String[] args) throws IOException {
        //字节数组流：
        //ByteArrayOutputStream:    可以捕获内存缓冲区的数据，转换成字节数组。
        ByteArrayOutputStream bout = new ByteArrayOutputStream();
        /**
         *
        bout.write(int a);
        bout.write(int b);
        bout.write(int c);
         */
        byte[] buf=bout.toByteArray();//获取内存缓冲中的数据
        for(int i=0;i<=buf.length;i++)
        {
            System.out.println(buf);
        }
        bout.close();
        //注：通过调用reset（）方法可以重新定位。
        //ByteArrayInputStream: 可以将字节数组转化为输入流
        ByteArrayInputStream bin=new ByteArrayInputStream(buf);
        int data=0;
        int b;
        while( (b=bin.read())!=-1)
        {
            System.out.println(b);
        }
        bin.close();


        //-----
        //与DataOutputStream&DataInputStream联合使用：

        DataOutputStream dos=new DataOutputStream(bout);
        String name="suntao";
        int age=19;
        dos.writeUTF(name);
        dos.writeInt(age);
        dos.close();
        bout.close();

        DataInputStream dis=new DataInputStream(bin);
        dis.close();
        bin.close();

        /**
         * 注:  DataInputStream&DataOutputStream还可以与FileInputStream&FileOutputStream联合使用。
        其中：
         DataInputStream&DataOutputStream关心如何将数据从高层次的形式转化成低层次的形式.
         FileInputStream&FileOutputStream关心如何操作存储单元以接受和产生数据。*/

    }
}
