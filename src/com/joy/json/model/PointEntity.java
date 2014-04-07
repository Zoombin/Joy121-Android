package com.joy.json.model;

import java.util.List;

/**
 * 积分信息
 * @author daiye
 *
 */
public class PointEntity extends TResult {

	private static final long serialVersionUID = 1L;

	private List<UserPointsHisEntity> retobj;
	
	private String msg;
	
	private int flag;

	public List<UserPointsHisEntity> getRetobj() {
		return retobj;
	}

	public void setRetobj(List<UserPointsHisEntity> retobj) {
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
