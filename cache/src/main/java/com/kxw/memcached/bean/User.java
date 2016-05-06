package com.kxw.memcached.bean;
import java.io.Serializable;
public class User implements Serializable{
 
    /**
     * 序列号
     */
    private static final long serialVersionUID = -3896605600471191953L;
    private int uid;
    private String uname;
    private String upass;


    public User(){
        super();
    }

    public User(int uid, String uname, String upass) {
        super();
        this.uid = uid;
        this.uname = uname;
        this.upass = upass;
    }

    public int getUid() {
       return uid;
    }
    public void setUid(int uid) {
       this.uid = uid;
    }
    public String getUname() {
       return uname;
    }
    public void setUname(String uname) {
       this.uname = uname;
    }
    public String getUpass() {
       return upass;
    }
    public void setUpass(String upass) {
       this.upass = upass;
    }

}