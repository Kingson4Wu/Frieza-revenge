package com.kxw.sync;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collection;

/**
 * Created by kingsonwu on 17/9/16.
 */
public class LockSelfObjectTest {

    public static synchronized void one() throws IOException {
        write(System.currentTimeMillis() + " one ...", ".kxw1.txt");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        write(System.currentTimeMillis() + " one end ...", ".kxw1.txt");
    }

    public static synchronized  void two() throws IOException {
        write(System.currentTimeMillis() + " two ...", ".kxw2.txt");
    }

    public static void main(String[] args) throws IOException {

        /*new Thread(() -> {
            try {
                one();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            try {
                two();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();*/
        /*
        List<String> list = new ArrayList<>();
        read(list, ".kxw2.txt");
        list.forEach(v -> System.out.println(v));*/

        //public static void two() throws IOException
        //1505577430721 one ...
        //1505577430721 two ...
        //1505577435725 one end ...

        //public static synchronized void two() throws IOException
        //1505577518892 one ...
        //1505577523893 one end ...
        //1505577523893 two ...

        new Thread(() -> {
            try {
                new LockObject().fuckone();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            try {
                new LockObject2().fucktwo();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();

        ////public static synchronized void two() throws IOException
        //1505578101268 one ...
        //1505578106272 one end ...
        //1505578106273 two ...

        //public static  void two() throws IOException
        //1505578196698 one ...
        //1505578201702 one end ...
        //1505578196699 two ...

        //1505578312161 one ...
        //1505578317164 one end ...
        //1505578317164 two ...



    }

    private synchronized static void write(String content, String filename) throws IOException {
        FileWriter fw = new FileWriter(filename, true);
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(content);
        bw.newLine();

        bw.close();
        fw.close();
    }

    private static void read(Collection<String> collection, String filename) {
        try {
            String encoding = "GBK"; // 字符编码(可解决中文乱码问题 )
            File file = new File(filename);
            if (file.isFile() && file.exists()) {
                InputStreamReader read = new InputStreamReader(
                    new FileInputStream(file), encoding);
                BufferedReader bufferedReader = new BufferedReader(read);
                String lineTXT = null;
                while ((lineTXT = bufferedReader.readLine()) != null) {
                    collection.add(lineTXT.toString().trim());
                }
                read.close();
            } else {
                System.out.println("找不到指定的文件！");
            }
        } catch (Exception e) {
            System.out.println("读取文件内容操作出错");
            e.printStackTrace();
        }
    }
}
