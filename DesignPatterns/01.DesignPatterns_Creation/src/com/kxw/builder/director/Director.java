package com.kxw.builder.director;

import com.kxw.builder.abstractBuilder.Builder;
import com.kxw.builder.product.TrafficMachine;
import com.kxw.builder.realBuilder.CoachBuilder;

/**
 * 【描述】：导演者
 * 【作者】：alaric
 * 【时间】：Jul 14, 2012
 * 【文件】：com.pattern.builder.Director.java
 */
public class Director {

    private Builder builder;

    public TrafficMachine construct() {
        builder = new CoachBuilder();
        builder.buildTire();//创建轮胎  
        builder.buildChassis();//创建底盘  
        builder.buildEngine();//创建发动机  
        builder.buildSeat();//创建座椅  
        builder.buildShell();//创建外壳  
        return builder.retrieveResult();

    }

} 