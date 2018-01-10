package com.kxw.disruptor.bigfilemd5;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.security.MessageDigest;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

/**
 * Created by kingsonwu on 18/1/10.
 */
public class GenerareBigFileMd5 {

    public static void main(String[] args) throws IOException {

        URL url = GenerareBigFileMd5.class.getClassLoader().getResource("bigfile.txt");
        File file = new File(url.getFile());

        String content = FileUtils.readFileToString(file);

        System.out.println(file.length());

        System.out.println(DigestUtils.md5Hex(content));

        InputStream inputStream = GenerareBigFileMd5.class.getClassLoader().getResourceAsStream("bigfile.txt");

        int size = (int)(file.length() / 4);
        int remainder = (int)(file.length() % 4);

        System.out.println(size);

        MessageDigest messageDigest = DigestUtils.getMd5Digest();
        byte[] buffer = new byte[size];
        IOUtils.read(inputStream, buffer);
        //String md51 = DigestUtils.md5Hex(buffer);
        //messageDigest.reset();
        messageDigest.update(buffer);
        //byte[] md51 = messageDigest.digest();
        //System.out.println("md1: " + md1.length);

        IOUtils.read(inputStream, buffer);
        //messageDigest.update(buffer);
        //String md52 = DigestUtils.md5Hex(buffer);
        //messageDigest.reset();
        messageDigest.update(buffer);
        //byte[] md52 = messageDigest.digest();

        IOUtils.read(inputStream, buffer);
        //messageDigest.update(buffer);
        //String md53 = DigestUtils.md5Hex(buffer);
        //messageDigest.reset();
        messageDigest.update(buffer);
        //byte[] md53 = messageDigest.digest();

        buffer = new byte[size + remainder];
        IOUtils.read(inputStream, buffer);
        //messageDigest.update(buffer);
        //String md54 = DigestUtils.md5Hex(buffer);
        //messageDigest.reset();
        messageDigest.update(buffer);
        //byte[] md54 = messageDigest.digest();

      /*  messageDigest.update(md51.getBytes());
        messageDigest.update(md52.getBytes());
        messageDigest.update(md53.getBytes());
        messageDigest.update(md54.getBytes());*/

        /*messageDigest.reset();
        messageDigest.update(md51);
        messageDigest.update(md52);
        messageDigest.update(md53);
        messageDigest.update(md54);*/

        System.out.println(Hex.encodeHexString(messageDigest.digest()));

        inputStream.close();
    }

}
