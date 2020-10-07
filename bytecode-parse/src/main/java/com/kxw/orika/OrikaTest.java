package com.kxw.orika;

import com.kxw.bean.Kingson;

public class OrikaTest {

    public static void main(String[] args) {
        Kingson user1 = new Kingson();
        user1.setAge(1);
        user1.setName("user1");
        Kingson user2 = OrikaBeanMapper.map(user1, Kingson.class);
        System.out.println(user2.getName());
    }
}
