package com.kxw.copy;

import org.apache.commons.io.FileUtils;

import java.io.*;

/**
 * Created by kxw on 2015/9/18.
 * {<a href='http://www.jobbole.com/members/57845349'>@link</a>}
 */
public class DirectoryCopy {
    /**
     * 复制一个目录及其子目录、文件到另外一个目录
     *
     * @param src
     * @param dest
     * @throws IOException
     */
    public static void copyFolder(File src, File dest) throws IOException {
        if (src.isDirectory()) {
            if (!dest.exists()) {
                dest.mkdir();
            }
            String files[] = src.list();
            for (String file : files) {
                File srcFile = new File(src, file);
                File destFile = new File(dest, file);
                // 递归复制
                copyFolder(srcFile, destFile);
            }
        } else {
            InputStream in = new FileInputStream(src);
            OutputStream out = new FileOutputStream(dest);

            byte[] buffer = new byte[1024];

            int length;

            while ((length = in.read(buffer)) > 0) {
                out.write(buffer, 0, length);
            }
            in.close();
            out.close();
        }
    }

    public static void copyFolderUtils(File src, File dest) throws IOException {
        FileUtils.copyDirectory(src, dest);
    }
}
/**
 * PS： apache commons-io包，FileUtils有相关的方法，IOUtils一般是拷贝文件。
 * <p/>
 * 删除目录结构                    FileUtils.deleteDirectory(dest);
 * 递归复制目录及文件        FileUtils.copyDirectory(src, dest);
 */