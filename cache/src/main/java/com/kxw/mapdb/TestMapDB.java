package com.kxw.mapdb;
import org.mapdb.*;

import java.util.concurrent.ConcurrentNavigableMap;

/**
 * Created by kingsonwu on 15/12/13.
 * {<a href='http://www.mapdb.org/doc/getting-started.html'>@link</a>}
 */
public class TestMapDB {

    public static void main(String[] args) {


        DB db = DBMaker.newMemoryDB().make();

        ConcurrentNavigableMap treeMap = db.getTreeMap("map");
        treeMap.put("something","here");

        db.commit();
        db.close();
    }
}
