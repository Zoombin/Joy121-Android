package com.joy.json.model;

import java.util.List;

/**
 * 积分信息
 * @author ryan zhou 2014-11-25
 *
 */
public class RentGoodsListEntity extends TResult {

	private static final long serialVersionUID = 1L;

	private List<RentGoodsEntity> retobj;
	
	private String msg;
	
	private int flag;

	public List<RentGoodsEntity> getRetobj() {
		return retobj;
	}

	public void setRetobj(List<RentGoodsEntity> retobj) {
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
