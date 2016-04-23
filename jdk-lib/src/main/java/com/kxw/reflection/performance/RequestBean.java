package com.kxw.reflection.performance;

/**
 * Created by kingsonwu on 16/4/23.
 */
public class RequestBean {

    private long id;

    private String name;

    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

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

    @Override
    public String toString() {
        return this.getId() + "," + this.getName() + "," + this.getEmail();
    }
}
