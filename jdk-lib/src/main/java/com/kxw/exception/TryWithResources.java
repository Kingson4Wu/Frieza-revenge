package com.kxw.exception;

import java.io.*;

/**
 * Created by kingsonwu on 16/1/16.
 * <a href='http://www.oschina.net/question/12_10706'>@link</a>
 */
public class TryWithResources {

    /**
     * 从 Java 7 build 105 版本开始，Java 7 的编译器和运行环境支持新的 try-with-resources 语句，
     * 称为 ARM 块(Automatic Resource Management) ，自动资源管理。
     * 新的语句支持包括流以及任何可关闭的资源，例如，一般我们会编写如下代码来释放资源
     * 代码挺复杂的，异常的管理很麻烦。
     */
    private static void customBufferStreamCopy(File source, File target) {
        InputStream fis = null;
        OutputStream fos = null;
        try {
            fis = new FileInputStream(source);
            fos = new FileOutputStream(target);

            byte[] buf = new byte[8192];

            int i;
            while ((i = fis.read(buf)) != -1) {
                fos.write(buf, 0, i);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(fis);
            close(fos);
        }
    }

    private static void close(Closeable closable) {
        if (closable != null) {
            try {
                closable.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 而使用 try-with-resources 语句来简化代码如下
     * 在这个例子中，数据流会在 try 执行完毕后自动被关闭，前提是，这些可关闭的资源必须实现 java.lang.AutoCloseable 接口。
     */
    private static void customBufferStreamCopy2(File source, File target) {
        try (InputStream fis = new FileInputStream(source);
             OutputStream fos = new FileOutputStream(target)) {

            byte[] buf = new byte[8192];

            int i;
            while ((i = fis.read(buf)) != -1) {
                fos.write(buf, 0, i);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
