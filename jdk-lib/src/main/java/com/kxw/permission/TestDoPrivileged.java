package com.kxw.permission;

import java.security.AccessController;
import java.security.PrivilegedAction;

/**
 * Created by kingsonwu on 16/4/17.
 */
public class TestDoPrivileged {

    /**
     * AccessController.doPrivileged意思是这个是特别的,不用做权限检查.
     */
    public static void main(String[] args) {

        if (System.getSecurityManager() != null) {

            AccessController.doPrivileged(new PrivilegedAction() {
                public Object run() {
                    //check();
                    return null;
                }
            });
        }
    }
}

/**
 public T newInstance()  throws InstantiationException, IllegalAccessException { if (System.getSecurityManager() != null) {  checkMemberAccess(Member.PUBLIC, ClassLoader.getCallerClassLoader()); } return newInstance0(); }
 */