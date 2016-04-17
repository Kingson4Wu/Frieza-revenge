package apache.commons.compress;

import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipArchiveOutputStream;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * Created by kingsonwu on 15/12/26.
 */
public class TestCompress {

    public static void main(String[] args) throws Exception {
        //创建压缩对象
        ZipArchiveEntry entry = new ZipArchiveEntry("CompressTest");
        //要压缩的文件
        File f = new File("e:\\test.pdf");
        FileInputStream fis = new FileInputStream(f);
        //输出的对象 压缩的文件
        ZipArchiveOutputStream zipOutput = new ZipArchiveOutputStream(new File("e:\\test.zip"));
        zipOutput.putArchiveEntry(entry);
        int i = 0, j;
        while ((j = fis.read()) != -1) {
            zipOutput.write(j);
            i++;
            System.out.println(i);
        }
        zipOutput.closeArchiveEntry();
        zipOutput.close();
        fis.close();
    }
}
