package com.kxw.http.retrofit;

import java.io.Serializable;

/**
 * Created by kingsonwu on 16/7/23.
 */
public class Repo implements Serializable{

    private long id;
    private String name;
    private String full_name;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }
}
