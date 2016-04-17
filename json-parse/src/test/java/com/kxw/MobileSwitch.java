package com.kxw;

import java.io.Serializable;
import java.util.Date;

public class MobileSwitch implements Serializable {

	private static final long serialVersionUID = 729113350097261592L;

	/** 序列 **/
	private Long id;
	/** 编码 **/
	private String code;
	/** 状态 0 关 1 开 **/
	private Short status;
	/** 开始时间 **/
	private Date startTime;
	/** 结束时间 **/
	private Date endTime;
	/**说明描述**/
	private String instruction;
	
	public MobileSwitch() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	private static final Short OPEN_STATUS = 1;

	public static boolean isSwitchOpened(MobileSwitch mobileSwitch) {
		return mobileSwitch != null && OPEN_STATUS.equals(mobileSwitch.getStatus());
	}

	public String getInstruction() {
		return instruction;
	}

	public void setInstruction(String instruction) {
		this.instruction = instruction;
	}

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("id").append(":").append(id);
        sb.append("-code").append(":").append(code);
        sb.append("-status").append(":").append(status);
        sb.append("-startTime").append(":").append(startTime);
        sb.append("-endTime").append(":").append(endTime);
        return sb.toString();
    }

}
