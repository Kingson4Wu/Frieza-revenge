package com.kxw.path;

import java.io.File;
import java.net.URL;

/**
 * Created by kxw on 2015/10/4.
 */
public class ProjectPath {

    public static void main(String[] args){

       // this.getClass().getClassLoader().getResource("/").getPath();
       // this.getClass().getClassLoader().getResource("").getPath();
        System.out.println(System.getProperty("user.dir"));

        //this.getClass().getClassLoader().getResource(".").getPath();

     /*   ClassLoader cl = Thread.currentThread().getContextClassLoader();
        URL defaultPropertyURL = cl.getResource("./META-INF/configcenter.properties");
        if (defaultPropertyURL != null) {
            addProeprties(new File(defaultPropertyURL.getFile()));
        }*/
        //----------------------



    }

}
