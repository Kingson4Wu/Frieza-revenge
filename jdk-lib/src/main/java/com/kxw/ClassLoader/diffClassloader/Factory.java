package com.kxw.classLoader.diffClassloader;


public class Factory {
    private Car car;

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public ClassLoader getCarClassLoader(){
        return Car.class.getClassLoader();
    }
}


