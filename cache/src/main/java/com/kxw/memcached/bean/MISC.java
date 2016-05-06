package com.kxw.memcached.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by win7 on 2016/5/5.
 */
public class MISC implements Serializable{

    private static final long serialVersionUID = 7094216348612966098L;
    private String name;
    private User user;
    private List<String> list;
    private List<User> usrlist;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }

    public List<User> getUsrlist() {
        return usrlist;
    }

    public void setUsrlist(List<User> usrlist) {
        this.usrlist = usrlist;
    }
}
