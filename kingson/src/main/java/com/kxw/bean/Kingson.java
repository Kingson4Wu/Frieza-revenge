package com.kxw.bean;

import java.util.Date;

/**
 * Created by kingsonwu on 15/12/26.
 */
public class Kingson {


    private String name ;
    private int id;
    private String email;
    private String company;
    private int age;
    private String gameOpen;
    private Date birthDay;

    private Boolean deleted;

    public Kingson(){
        super();
        this.id = 10;
        this.name = "Kingson Wu";
        this.company = "abc";
        this.email = "kingson4wu@gmail.com";
        this.age = 24;
        this.birthDay= new Date();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getGameOpen() {
        return gameOpen;
    }

    public void setGameOpen(String gameOpen) {
        this.gameOpen = gameOpen;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Date getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public void run() {
        System.out.println("i am king !");

    }

    public boolean next() {
        return false;
    }
}
