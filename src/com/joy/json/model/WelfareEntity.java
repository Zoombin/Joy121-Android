package com.joy.json.model;

import java.util.List;

/**
 * 福利列表
 * 
 * @author daiye
 * 
 */
public class WelfareEntity extends TResult {

	private static final long serialVersionUID = 1L;

	private List<CommoditySet> retobj;

	private String msg;

	private int flag;

	public List<CommoditySet> getRetobj() {
		return retobj;
	}

	public void setRetobj(List<CommoditySet> retobj) {
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
