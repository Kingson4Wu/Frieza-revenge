package com.kxw;

import com.kxw.jackson.JacksonUtils;

import java.util.Date;

/**
 * Created by Kingson.wu on 2015/10/20.
 */
public class Test {

     public static void main( String[] args ) throws Exception {

         Switch swi = new Switch();
         swi.setDate(new Date());
         swi.setName("kxw");

         String str = JacksonUtils.obj2json(swi);
        String str2 = "{\"startTime\":1436371200000,\"endTime\":1563984000000,\"status\":1}";

         System.out.println(str);
         System.out.println(str2);
         swi = JacksonUtils.json2pojo(str2,Switch.class);
        MobileSwitch swie =JacksonUtils.json2pojo(str2,MobileSwitch.class);

         System.out.println(swie.getStartTime());

     }


}
class Switch{
    private Date date;
    private String name;
    private Short status;
    /** 开始时间 **/
    private Date startTime;
    /** 结束时间 **/
    private Date endTime;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Short getStatus() {
        return status;
    }

    public void setStatus(Short status) {
        this.status = status;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
}