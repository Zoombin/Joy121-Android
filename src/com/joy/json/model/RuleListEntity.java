package com.joy.json.model;

import java.util.List;

public class RuleListEntity extends TResult {
	private static final long serialVersionUID = 1L;
	int flag;
	String msg;
	List<RuleEntity> retobj;

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public List<RuleEntity> getRetobj() {
		return retobj;
	}

	public void setRetobj(List<RuleEntity> retobj) {
		this.retobj = retobj;
	}

}

