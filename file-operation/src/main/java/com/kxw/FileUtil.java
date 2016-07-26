package com.kxw;

import org.apache.commons.io.IOUtils;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.util.Iterator;

public class FileUtil {

    /**
     * 根据地址获得数据的字节流
     *
     * @param strUrl 网络连接地址
     * @return
     */
    public static byte[] getImageFromNetByUrl(String strUrl) {

        try {
            java.net.URL networkUrl = new java.net.URL(strUrl);

            HttpURLConnection conn = (HttpURLConnection) networkUrl.openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(5 * 1000);
            InputStream inStream = conn.getInputStream();//通过输入流获取图片数据
            byte[] btImg = IOUtils.toByteArray(inStream);//得到图片的二进制数据
            inStream.close();
            return btImg;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 根据inStream获得数据的字节流
     *
     * @param inStream
     * @return
     */
    public static byte[] getImageFromNetByUrl(InputStream inStream) {
        try {
            byte[] btImg = IOUtils.toByteArray(inStream);//得到图片的二进制数据
            inStream.close();
            return btImg;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 根据地址获得数据的InputStream
     *
     * @param strUrl 网络连接地址
     * @return
     */
    public static InputStream getInputStreamFromNetByUrl(String strUrl) {
        try {
            java.net.URL networkUrl = new java.net.URL(strUrl);

            HttpURLConnection conn = (HttpURLConnection) networkUrl.openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(5 * 1000);
            InputStream inStream = conn.getInputStream();//通过输入流获取图片数据
            return inStream;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getImageSuffix(InputStream inStream) {

        String suffix = "JPG";
        try {
            ImageInputStream imageInputStream = ImageIO.createImageInputStream(inStream);

            Iterator<ImageReader> iter = ImageIO.getImageReaders(imageInputStream);

            if (null != iter && iter.hasNext()) {
                ImageReader reader = iter.next();
                suffix = reader.getFormatName(); //获得图片的类型

                //reader.setInput(imageInputStream, true);
                //reader.getWidth(0);  //获得图片的宽
                //reader.getHeight(0); //获得图片的高
            }
        } catch (IOException e) {
            suffix = "JPG";
        }
        return suffix;
    }
}
