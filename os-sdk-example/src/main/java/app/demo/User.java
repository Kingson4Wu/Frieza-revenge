package app.demo;

import java.io.Serializable;

public class User implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 153519254199840035L;
    
    String userName = "snoopy";
    String password = "showme";

    public User(String user, String pwd) {
        this.userName = user;
        this.password = pwd;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }
}