package com.joy.json.model;

import java.util.List;

/**
 * 活动详情
 * @author daiye
 *
 */
public class ActivityEntity extends TResult {

	private static final long serialVersionUID = 1L;

	private List<ActivityDetailEntity> retobj;
	
	private String msg;
	
	private int flag;
	
	public String isexpired;
	
	public String acttype;

	public List<ActivityDetailEntity> getRetobj() {
		return retobj;
	}

	public void setRetobj(List<ActivityDetailEntity> retobj) {
		this.retobj = retobj;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}
	
	public String getIsExpired() {
		return isexpired;
	}
	
	public void setIsExpired(String expired) {
		this.isexpired = expired;
	}
	
	public String getActType() {
		return acttype;
	}
	
	public void setActType(String actType) {
		this.acttype = actType;
	}
}
