package com.joy.json.model;

import java.util.List;

public class CategoriesStoreEntity extends TResult {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	int flag;
	String msg;
	List<CategoriesStore> retobj;

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

	public List<CategoriesStore> getRetobj() {
		return retobj;
	}

	public void setRetobj(List<CategoriesStore> retobj) {
		this.retobj = retobj;
	}

}
