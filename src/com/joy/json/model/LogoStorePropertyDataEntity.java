package com.joy.json.model;

import java.util.List;

public class LogoStorePropertyDataEntity extends TResult{
	private static final long serialVersionUID = 1L;
	private int flag;
	private String msg;
	private List<LogoStorePropertyDataDetailEntity> retobj;
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
	public List<LogoStorePropertyDataDetailEntity> getRetobj() {
		return retobj;
	}
	public void setRetobj(List<LogoStorePropertyDataDetailEntity> retobj) {
		this.retobj = retobj;
	}

}
