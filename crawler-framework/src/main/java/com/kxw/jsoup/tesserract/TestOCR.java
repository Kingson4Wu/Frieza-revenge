package com.kxw.jsoup.tesserract;

import java.io.File;
import java.io.IOException;

/**
 * <p/>
 * <br/>==========================
 * <br/> 创建时间：2016年12月21日 11:45
 * <br/>==========================
 */
public class TestOCR {

    /**
     * @param args
     */
    public static void main(String[] args) {
        String path = "E:\\gameopen\\captcha-image.jpg";
        try {
            String valCode = new OCR().recognizeText(new File(path), "jpg");
            System.out.println(valCode);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
