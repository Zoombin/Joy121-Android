package com.joy.json.model;

import java.util.List;

/**
 * 积分信息
 * @author ryan zhou 2014-12-11
 *
 */
public class AttendanceListEntity extends TResult {

	private static final long serialVersionUID = 1L;

	private List<AttendanceEntity> retobj;
	
	private String msg;
	
	private int flag;

	public List<AttendanceEntity> getRetobj() {
		return retobj;
	}

	public void setRetobj(List<AttendanceEntity> retobj) {
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
}
