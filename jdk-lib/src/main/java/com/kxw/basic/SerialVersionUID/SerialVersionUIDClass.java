package com.kxw.basic.SerialVersionUID;

import java.io.Serializable;

/**
 * Created by kingsonwu on 17/3/12.
 */
public class SerialVersionUIDClass implements Serializable{

    private static final long serialVersionUID = 555555555;

    private int id;

    private String name;

    private String email;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
