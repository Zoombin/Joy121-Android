package com.joy.json.model;

import java.util.List;

/**
 * 激励列表
 * @author ryan zhou 2015-01-22
 *
 */
public class RosterListEntity extends TResult {

	private static final long serialVersionUID = 1L;

	private List<RosterEntity> retobj;
	
	private String msg;
	
	private int flag;

	public List<RosterEntity> getRetobj() {
		return retobj;
	}

	public void setRetobj(List<RosterEntity> retobj) {
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
