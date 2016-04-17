package apache.commons.io;

import org.apache.commons.io.FileSystemUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import java.io.*;
import java.net.URL;
import java.util.List;

/**
 * Created by kingsonwu on 15/12/26.
 */
public class TestIO {

    public static void main(String[] args) throws IOException {
        //1．读取Stream

//标准代码：
        InputStream in = new URL("http://jakarta.apache.org").openStream();
        try {
            InputStreamReader inR = new InputStreamReader(in);
            BufferedReader buf = new BufferedReader(inR);
            String line;
            while ((line = buf.readLine()) != null) {
                System.out.println(line);
            }
        } finally {
            in.close();
        }

//使用IOUtils

        InputStream in2 = new URL("http://jakarta.apache.org").openStream();
        try {
            System.out.println(IOUtils.toString(in2));
        } finally {
            IOUtils.closeQuietly(in2);
        }

//2．读取文件
        File file = new File("/commons/io/project.properties");
        List lines = FileUtils.readLines(file, "UTF-8");
//3．察看剩余空间
        long freeSpace = FileSystemUtils.freeSpace("C:/");
    }
}
