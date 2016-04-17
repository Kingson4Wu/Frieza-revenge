package com.kxw.SecurityManager.proxy;

/**
 * Created by Kingson.wu on 2015/8/26.
 * http://blog.csdn.net/yfqnihao/article/details/8271407
 *
 */
import java.io.FileWriter;
import java.io.IOException;

public class TestPolicy {
    /**
     * -Djava.security.manager -Djava.security.policy=F:/IdeaProject/Test/apache-tomcat-8.0.24-src/test/kxw/SecurityManager/proxy/myPolicy.txt
     * @param args
     */

    public static void main(String[] args) {
        FileWriter writer;
        try {
            writer = new FileWriter("d:/testPolicy.txt");
            writer.write("hello1");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
