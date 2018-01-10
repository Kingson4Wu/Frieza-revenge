package com.kxw.disruptor.bigfilemd5;

/**
 * Created by kingsonwu on 18/1/10.
 */
public class FileMd5Event {

    private String groupId;

    private int sequence;

    private int md5String;

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public int getSequence() {
        return sequence;
    }

    public void setSequence(int sequence) {
        this.sequence = sequence;
    }

    public int getMd5String() {
        return md5String;
    }

    public void setMd5String(int md5String) {
        this.md5String = md5String;
    }
}
