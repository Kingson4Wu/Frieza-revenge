package com.kxw.basic.type;

/**
 * Created by kxw on 2016/1/5.
 * Int和byte数组之间的转换
 * 有时候和C的程序通信的时候，我们在封装协议时，可能需要将Java里的int值，转换成byte[]后用socket发送。所以我们需要将32位的int值放到4字节的byte[]里。
 */
public class IntByteConverter {

    /**
     * int值转成4字节的byte数组
     *
     * @param num
     * @return
     */
    public static byte[] int2byteArray(int num) {
        byte[] result = new byte[4];
        result[0] = (byte) (num >>> 24);//取最高8位放到0下标
        result[1] = (byte) (num >>> 16);//取次高8为放到1下标
        result[2] = (byte) (num >>> 8); //取次低8位放到2下标
        result[3] = (byte) (num);      //取最低8位放到3下标
        return result;
    }


    /**
     * 将4字节的byte数组转成int值
     *
     * @param b
     * @return
     */
    public static int byteArray2int(byte[] b) {
        byte[] a = new byte[4];
        int i = a.length - 1, j = b.length - 1;
        for (; i >= 0; i--, j--) {//从b的尾部(即int值的低位)开始copy数据
            if (j >= 0)
                a[i] = b[j];
            else
                a[i] = 0;//如果b.length不足4,则将高位补0
        }
        int v0 = (a[0] & 0xff) << 24;//&0xff将byte值无差异转成int,避免Java自动类型提升后,会保留高位的符号位
        int v1 = (a[1] & 0xff) << 16;
        int v2 = (a[2] & 0xff) << 8;
        int v3 = (a[3] & 0xff);
        return v0 + v1 + v2 + v3;
    }


    //byte 数组长度分为大于等于4或等于2 的情况.
    public static int byte2int(byte b[]) {
        if (b != null && b.length > 0) {

            if (b.length >= 4) {
                return b[3] & 0xff | (b[2] & 0xff) << 8 | (b[1] & 0xff) << 16
                        | (b[0] & 0xff) << 24;
            } else if (b.length == 2) {
                return byte2int2(b);
            } else {
                return 0;
            }

        } else {
            return 0;
        }

    }

    public static int byte2int2(byte b[]) {
        return b[1] & 0xff | (b[0] & 0xff) << 8;
    }
}
