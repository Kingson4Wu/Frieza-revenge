package com.kxw.mongodb;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

import java.util.Arrays;

/**
 * <a href='http://www.runoob.com/mongodb/mongodb-java.html'>@link</a>
 */
public class MongoDBJDBC{
    public static void main( String args[] ){
        try{
            // 连接到 mongodb 服务
            MongoClient mongoClient = new MongoClient( "localhost" , 27017 );
            // 连接到数据库
            MongoDatabase db = mongoClient.getDatabase( "test" );
            System.out.println("Connect to database successfully");
            //boolean auth = db.authenticate(myUserName, myPassword);
            //System.out.println("Authentication: "+auth);
        }catch(Exception e){
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        }
    }
}
/**
 * <http://www.runoob.com/mongodb/working-with-rockmongo.html>
 * <http://www.runoob.com/mongodb/mongodb-map-reduce.html>
 *
 */
