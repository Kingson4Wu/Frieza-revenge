package app.demo2;

import java.io.Serializable;

public class User2 implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 153519254199840035L;

    String userName = "snoopy";
    String password = "showme";

    public User2(String user, String pwd) {
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