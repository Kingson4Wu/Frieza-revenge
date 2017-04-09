package com.kxw.bean;

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

    public Kingson(){
        super();
        this.id = 10;
        this.name = "Kingson Wu";
        this.company = "abc";
        this.email = "kingson4wu@gmail.com";
        this.age = 24;
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

    public void run() {
        System.out.println("i am king !");

    }

    public boolean next() {
        return false;
    }
}
