package com.kxw.gson.bean;

/**
 * Created by kingsonwu on 15/12/25.
 */
import java.util.Date;
import java.util.List;


public class SendByUserIds {

    private List<String> userIds;
    private String content;
    private int messageType;
    private Args args;
    private Date expiredTime;
    private String pwd;
    private String groupId;
    private String appName;
    private String title;
    private String titleColor;
    private String bgColor;
    private String contentColor;
    private String bgPic;



    public List<String> getUserIds() {
        return userIds;
    }
    public void setUserIds(List<String> userIds) {
        this.userIds = userIds;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public int getMessageType() {
        return messageType;
    }
    public void setMessageType(int messageType) {
        this.messageType = messageType;
    }
    public Args getArgs() {
        return args;
    }
    public void setArgs(Args args) {
        this.args = args;
    }
    public Date getExpiredTime() {
        return expiredTime;
    }
    public void setExpiredTime(Date expiredTime) {
        this.expiredTime = expiredTime;
    }
    public String getPwd() {
        return pwd;
    }
    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
    public String getGroupId() {
        return groupId;
    }
    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }
    public String getAppName() {
        return appName;
    }
    public void setAppName(String appName) {
        this.appName = appName;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getTitleColor() {
        return titleColor;
    }
    public void setTitleColor(String titleColor) {
        this.titleColor = titleColor;
    }
    public String getBgColor() {
        return bgColor;
    }
    public void setBgColor(String bgColor) {
        this.bgColor = bgColor;
    }
    public String getContentColor() {
        return contentColor;
    }
    public void setContentColor(String contentColor) {
        this.contentColor = contentColor;
    }
    public String getBgPic() {
        return bgPic;
    }
    public void setBgPic(String bgPic) {
        this.bgPic = bgPic;
    }

}