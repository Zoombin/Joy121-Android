package com.joy.json.model;

/**
 * 修改密码
 * 
 * @author daiye
 * 
 */
public class ChangePwdEntity extends TResult {

	private static final long serialVersionUID = 1L;

	private String ologinpwd;

	private String nloginpwd;

	private boolean retobj;

	private String msg;

	private int flag;

	public String getOloginpwd() {
		return ologinpwd;
	}

	public void setOloginpwd(String ologinpwd) {
		this.ologinpwd = ologinpwd;
	}

	public String getNloginpwd() {
		return nloginpwd;
	}

	public void setNloginpwd(String nloginpwd) {
		this.nloginpwd = nloginpwd;
	}

	public boolean isRetobj() {
		return retobj;
	}

	public void setRetobj(boolean retobj) {
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
