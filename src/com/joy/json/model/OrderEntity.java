package com.joy.json.model;

import java.util.List;

/**
 * 订单列表
 * @author daiye
 *
 */
public class OrderEntity extends TResult {

	private static final long serialVersionUID = 1L;

	private List<UserOrderEntity> retobj;
	
	private String msg;
	
	private int flag;

	public List<UserOrderEntity> getRetobj() {
		return retobj;
	}

	public void setRetobj(List<UserOrderEntity> retobj) {
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
