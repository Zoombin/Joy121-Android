package com.joy.json.model;

/**
 * 用户信息
 * @author daiye
 *
 */
public class LoginEntity extends TResult {

	private static final long serialVersionUID = 1L;
	
	private String loginname;
	
	private String loginpwd;

	private UserInfo retobj;
	
	private String msg;
	
	private int flag;
	
	public String getLoginname() {
		return loginname;
	}

	public void setLoginname(String loginname) {
		this.loginname = loginname;
	}

	public String getLoginpwd() {
		return loginpwd;
	}

	public void setLoginpwd(String loginpwd) {
		this.loginpwd = loginpwd;
	}

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