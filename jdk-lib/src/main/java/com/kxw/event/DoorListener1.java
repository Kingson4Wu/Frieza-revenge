package com.kxw.event;

public class DoorListener1 implements DoorListener {
    @Override
    public void doorEvent(DoorEvent event) {
        // TODO Auto-generated method stub
        if (event.getDoorState() != null && "open".equals(event.getDoorState())) {
            System.out.println("门1打开");
        } else {
            System.out.println("门1关闭");
        }
    }

}