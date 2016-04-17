package com.kxw.builder2.abstactBuilder;

import java.util.Map;

/**
 * 抽象建造者类
 *
 * @author king
 */

public abstract class Builder {

    //设置产品  
    abstract void setProduct(Class clazz, Map setMap);

    //返回产品  
    abstract Object getProduct();

}  