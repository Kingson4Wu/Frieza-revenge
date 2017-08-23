package com.kxw.java7;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

/**
 * <a href='https://mp.weixin.qq.com/s/KpubikzBKe5htTFSEoUm0A'>@link</a>
 * Java文件IO操作应该抛弃File拥抱Paths和Files
 <http://www.cnblogs.com/digdeep/p/4478734.html>
 * Created by kingsonwu on 17/8/23.
 */
public class FileOperateWithPath {


    public static void main(String[] args) throws IOException {
        //从一个路径字符串来构造Path对象
        Path path1   = Paths.get("/home/biezhi", "a.txt");
        Path path2   = Paths.get("/home/biezhi/a.txt");
        URI  u       = URI.create("file:////home/biezhi/a.txt");
        Path pathURI = Paths.get(u);

        //通过FileSystems构造
        Path filePath = FileSystems.getDefault().getPath("/home/biezhi", "a.txt");

        //Path、URI、File之间的转换
        File file  = new File("/home/biezhi/a.txt");
        Path p1    = file.toPath();
        p1.toFile();
        file.toURI();

        //读写文件
        byte[] data    = Files.readAllBytes(Paths.get("/home/biezhi/a.txt"));
        String content = new String(data, StandardCharsets.UTF_8);

        //按照行读取文件，可以调用
        List<String> lines = Files.readAllLines(Paths.get("/home/biezhi/a.txt"));

        //将字符串写入到文件
        Files.write(Paths.get("/home/biezhi/b.txt"), "Hello JDK7!".getBytes());

        //按照行写入文件，Files.write方法的参数中支持传递一个实现Iterable接口的类实例。 将内容追加到指定文件可以使用write方法的第三个参数OpenOption
        Files.write(Paths.get("/home/biezhi/b.txt"), "Hello JDK7!".getBytes(),
            StandardOpenOption.APPEND);

        //默认情况Files类中的所有方法都会使用UTF-8编码进行操作，当你不愿意这么干的时候可以传递Charset参数进去变更。


        Path path = new File("/home/biezhi/a.txt").toPath();
        //Files还有一些其他的常用方法
        InputStream ins = Files.newInputStream(path);
        OutputStream ops = Files.newOutputStream(path);
        Reader reader = Files.newBufferedReader(path);
        Writer writer = Files.newBufferedWriter(path);

        //创建、移动、删除
        // 创建文件、目录
        if (!Files.exists(path)) {
            Files.createFile(path);
            Files.createDirectory(path);
        }
        //Files还提供了一些方法让我们创建临时文件/临时目录:
        /*Files.createTempFile(dir, prefix, suffix);
        Files.createTempFile(prefix, suffix);
        Files.createTempDirectory(dir, prefix);
        Files.createTempDirectory(prefix);*/

        //读取一个目录下的文件请使用Files.list和Files.walk方法

        //复制、移动一个文件内容到某个路径

        /*Files.copy(in, path);
        Files.move(path, path);*/

        //删除一个文件

        Files.delete(path);



    }
}
