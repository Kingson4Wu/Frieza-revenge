package com.kxw.encryption.md5;

import java.security.MessageDigest;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;

/**
 * Created by kingsonwu on 18/1/10.
 */
public class Md5Test {

    public static void main(String[] args) {

        System.out.println(DigestUtils.md5Hex("kxw123kingson"));

        /**
         * MessageDigest 对象开始被初始化。该对象通过使用 update（）方法处理数据。任何时候都可以调用 reset（）方法重置摘要。
         * 一旦所有需要更新的数据都已经被更新了，应该调用digest() 方法之一完成哈希计算。
         */
        MessageDigest messageDigest = DigestUtils.getMd5Digest();

        byte[] buffer1 = "kxw".getBytes();
        messageDigest.update(buffer1);

        byte[] buffer2 = "123".getBytes();
        messageDigest.update(buffer2);
        byte[] buffer3 = "kingson".getBytes();
        messageDigest.update(buffer3);

        System.out.println(Hex.encodeHexString(messageDigest.digest()));


        //System.out.println(Arrays.toString(messageDigest.digest()));


    }
}
