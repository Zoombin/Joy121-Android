package com.joy.json.model;

import java.util.List;

public class CategoriesGoodsDEntity extends TResult{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String flag;
	private String msg;
	private List<CategoriesGoods> retobj;
	
	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public List<CategoriesGoods> getGoods() {
		return retobj;
	}


	public void setGoods(List<CategoriesGoods> retobj) {
		this.retobj = retobj;
	}
}
