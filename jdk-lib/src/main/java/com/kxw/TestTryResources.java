package com.kxw;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by kingsonwu on 16/3/24.
 */
public class TestTryResources {

    //在Java 6之前，打开一个文件然后读取内容需要通过try/finally来完成
    //但是readLine和close都有可能抛出异常。在这种情况下，readLine抛出的异常被忽略，我们事实上并不知道readLine执行失败。
    static String readFirstLineFromFileWithFinallyBlock(String path)
            throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(path));
        try {
            return br.readLine();
        } finally {
            if (br != null) {
                br.close();
            }
        }
    }

    //Java 7引入了 Try-With-Resources结构来克服这种缺陷
    //无论在何种失败情况下，BufferedReader都会自动关闭文件流。你可以通过用逗号分隔的方式，用一个try语句来打开多个资源。
    static String readFirstLineFromFile(String path) throws IOException {
        try (BufferedReader br =
                     new BufferedReader(new FileReader(path))) {
            return br.readLine();
        }
    }

    //多重catch
    //从Java 7开始，你可以在一个代码块内捕捉多个异常，从而减少了代码冗余
    /*catch (IOException|SQLException ex) {
        logger.log(ex);
        throw ex;
    }*/


}
