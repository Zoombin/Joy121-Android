package com.joy.json.model;

/**
 * 积分信息
 * @author daiye
 *
 */
public class UserEntity extends TResult {

	private static final long serialVersionUID = 1L;

	private UserInfoEntity retobj;
	
	private String msg;
	
	private int flag;

	public UserInfoEntity getRetobj() {
		return retobj;
	}

	public void setRetobj(UserInfoEntity retobj) {
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
