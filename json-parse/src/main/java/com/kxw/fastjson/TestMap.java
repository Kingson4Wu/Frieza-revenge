package com.kxw.fastjson;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;

import static com.alibaba.fastjson.JSON.parseObject;

public class TestMap {

    public static void main(String[] args) {

        Map<Long,List<Long>> map = new HashMap();

        map.put(222L, Arrays.asList(2233L,34434L));
        map.put(224L, Arrays.asList(2233L,34434L));

        System.out.println(JSON.toJSONString(map));

        Kk kk =new Kk();
        kk.setMap(map);

        System.out.println(JSON.toJSONString(kk));

        Kk jj = JSON.parseObject("{\"map\":{224:[2233,34434],222:[2233,34434]}}",Kk.class);
        System.out.println();
    }
}

class Kk{
    Map<Long,List<Long>> map;

    public Map<Long, List<Long>> getMap() {
        return map;
    }

    public void setMap(Map<Long, List<Long>> map) {
        this.map = map;
    }
}