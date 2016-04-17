package com.kxw.jsp.constants;

/**
 * Created by kxw on 2016/1/5.
 */
public enum SalePlatformConstants {
   abc("8") ;

    private String value;

    SalePlatformConstants(String value) {
        this.value = value;
    }

    public String getName() {
        return value;
    }

}
