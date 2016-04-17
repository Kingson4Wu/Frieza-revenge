package com.kxw.builder.abstractBuilder;

import com.kxw.builder.product.TrafficMachine;

/**
 * 【描述】：抽象建造者
 * 【作者】：alaric
 * 【时间】：Jul 14, 2012
 * 【文件】：com.pattern.builder.Builder.java
 */
public interface Builder {
    /**
     * 【描述】：建造轮胎
     */
    public void buildTire();

    /**
     * 【描述】：建造底盘
     */
    public void buildChassis();

    /**
     * 【描述】：建造引擎
     */
    public void buildEngine();

    /**
     * 【描述】：建造座椅
     */
    public void buildSeat();

    /**
     * 【描述】：建造外壳
     */
    public void buildShell();

    /**
     * 【描述】：返回产品
     */
    public TrafficMachine retrieveResult();

}  
