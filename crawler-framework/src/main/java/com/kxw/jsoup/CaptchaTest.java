package com.kxw.jsoup;

import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

import java.io.File;

/**
 * <p/>
 * <br/>==========================
 * <br/> 创建时间：2016年12月21日 9:59
 * <br/>==========================
 */
public class CaptchaTest {


    public static void main(String[] args) {
        File imageFile = new File("E:\\gameopen\\captcha-image.jpg");
        Tesseract tessreact = new Tesseract();
        tessreact.setDatapath("D:\\Program Files (x86)\\Tesseract-OCR\\tessdata");
        try {
            String result = tessreact.doOCR(imageFile);
            System.out.println(result);

           /* String valCode = new OCR().recognizeText(new File(path), "jpg");
            System.out.println(valCode);*/
        } catch (TesseractException e) {
            System.err.println(e.getMessage());
        }
    }


}
