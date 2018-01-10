package com.kxw.disruptor.bigfilemd5;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by kingsonwu on 18/1/10.
 */
public class FileMd5Info {

    private String groupId = UUID.randomUUID().toString();

    private List<String> md5List = new ArrayList<>();

    public String getGroupId() {
        return groupId;
    }


}
