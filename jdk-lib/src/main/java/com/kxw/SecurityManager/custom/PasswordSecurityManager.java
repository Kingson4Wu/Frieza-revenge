package com.kxw.SecurityManager.custom;

/**
 * Created by Kingson.wu on 2015/8/22.
 */

import java.io.BufferedReader;
import java.io.FileDescriptor;
import java.io.IOException;
import java.io.InputStreamReader;

class PasswordSecurityManager extends SecurityManager {
    private String password;
    PasswordSecurityManager(String password) {
        super();
        this.password = password;
    }
    private boolean accessOK() {
        int c;
        //DataInputStream dis = new DataInputStream(System.in);
        BufferedReader dis = new BufferedReader(new InputStreamReader(System.in));
        String response;
        System.out.println("What's the secret password?");
        try {
            response = dis.readLine();
            if (response.equals(password)) {
                return true;
            } else {
                return false;
            }
        } catch (IOException e) {
            return false;
        }
    }
    @Override
    public void checkRead(FileDescriptor filedescriptor) {
        if (!accessOK()) {
            throw new SecurityException("Not a Chance!");
        }
    }
    @Override
    public void checkRead(String filename) {
        if (!accessOK()) {
            throw new SecurityException("No Way!");
        }
    }
    @Override
    public void checkRead(String filename, Object executionContext) {
        if (!accessOK()) {
            throw new SecurityException("Forget It!");
        }
    }
    @Override
    public void checkWrite(FileDescriptor filedescriptor) {
        if (!accessOK()) {
            throw new SecurityException("Not!");
        }
    }
    @Override
    public void checkWrite(String filename) {
        if (!accessOK()) {
            throw new SecurityException("Not Even!");
        }
    }
}