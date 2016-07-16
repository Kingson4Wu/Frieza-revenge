package com.kxw.classLoader.diffClassloader;


public class Factory {
    private Car b;

    public Car getCar() {
        return b;
    }

    public void setCar(Car b) {
        this.b = b;
    }
}


