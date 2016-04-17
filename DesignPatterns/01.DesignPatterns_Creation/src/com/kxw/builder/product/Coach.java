package com.kxw.builder.product;

import com.kxw.builder.parts.Chassis;
import com.kxw.builder.parts.Engine;
import com.kxw.builder.parts.Seat;
import com.kxw.builder.parts.Shell;
import com.kxw.builder.parts.Tire;

/**
 * 【描述】：客车
 */
public class Coach implements TrafficMachine {


    private Chassis chassis;
    private Tire tire;
    private Engine engine;
    private Seat seat;
    private Shell shell;

    public Chassis getChassis() {
        return chassis;
    }

    public void setChassis(Chassis chassis) {
        this.chassis = chassis;
    }

    public Tire getTire() {
        return tire;
    }

    public void setTire(Tire tire) {
        this.tire = tire;
    }

    public Engine getEngine() {
        return engine;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    public Seat getSeat() {
        return seat;
    }

    public void setSeat(Seat seat) {
        this.seat = seat;
    }

    public Shell getShell() {
        return shell;
    }

    public void setShell(Shell shell) {
        this.shell = shell;
    }

}