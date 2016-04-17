package com.kxw.builder.realBuilder;

import com.kxw.builder.abstractBuilder.Builder;
import com.kxw.builder.parts.Chassis;
import com.kxw.builder.parts.Engine;
import com.kxw.builder.parts.Seat;
import com.kxw.builder.parts.Shell;
import com.kxw.builder.parts.Tire;
import com.kxw.builder.product.Coach;
import com.kxw.builder.product.TrafficMachine;

/**
 * 【描述】：客车建造者
 */
public class CoachBuilder implements Builder {

    private Coach coach = new Coach();

    @Override
    public void buildTire() {
// 此处根据实际业务写相关逻辑  
        coach.setTire(new Tire());
    }

    @Override
    public void buildChassis() {
// 此处根据实际业务写相关逻辑  
        coach.setChassis(new Chassis());
    }

    @Override
    public void buildEngine() {
// 此处根据实际业务写相关逻辑  
        coach.setEngine(new Engine());
    }

    @Override
    public void buildSeat() {
// 此处根据实际业务写相关逻辑  
        coach.setSeat(new Seat());
    }

    @Override
    public void buildShell() {
// 此处根据实际业务写相关逻辑  
        coach.setShell(new Shell());
    }

    public TrafficMachine retrieveResult() {
// 此处根据实际业务写相关逻辑  
        return coach;
    }

} 
