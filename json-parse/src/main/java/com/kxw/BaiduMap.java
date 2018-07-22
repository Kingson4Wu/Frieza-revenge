package com.kxw;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

/**
 * Created by kingsonwu on 18/4/30.
 */
public class BaiduMap {

    public static void main(String[] args) throws IOException {

        String content = FileUtils.readFileToString(new File(BaiduMap.class.getResource("/data.json").getPath()));

        String[] array = content.split("\n");

        System.out.println();

        System.out.println(Double.parseDouble("28.1667298415775"));

        double xmin = 10000D,ymin=1000D,xmax=0,ymax=0;

        StringBuilder point = new StringBuilder();
        for(int i=0; i <array.length;i++){
            String[] ele = array[i].split(",");
            if(Double.parseDouble(ele[0])<xmin){
                xmin = Double.parseDouble(ele[0]);
            }
        }

        System.out.println(point);

    }
}
