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
	
	private String devicetype;
	
	private String versionname;

	private UserInfoEntity retobj;
	
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

	public String getDevicetype() {
		return devicetype;
	}

	public void setDevicetype(String devicetype) {
		this.devicetype = devicetype;
	}

	public String getVersionname() {
		return versionname;
	}

	public void setVersionname(String versionname) {
		this.versionname = versionname;
	}

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
