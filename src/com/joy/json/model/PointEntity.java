package com.joy.json.model;

/**
 * 积分信息
 * @author daiye
 *
 */
public class PointEntity extends TResult {

	private static final long serialVersionUID = 1L;

	private UserInfo retobj;
	
	private String msg;
	
	private int flag;

	public UserInfo getRetobj() {
		return retobj;
	}

	public void setRetobj(UserInfo retobj) {
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
