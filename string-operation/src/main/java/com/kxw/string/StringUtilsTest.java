package com.kxw.string;

import org.apache.commons.lang3.StringUtils;

/**
 * <p/>
 * <br/>==========================
 * <br/> 创建时间：2017年02月04日 17:05
 * <br/>==========================
 */
public class StringUtilsTest {

    public static void main(String[] args) {
        /** Left padding a String with Zeros */
        //http://stackoverflow.com/questions/4469717/left-padding-a-string-with-zeros
        System.out.println(String.format("%010d", Integer.parseInt("123")));
        System.out.println(StringUtils.leftPad("123", 10, "0"));
    }
}
