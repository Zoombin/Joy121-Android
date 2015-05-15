package com.joy.json.model;
/**
 * 绑定手机
 * @author rainbow
 */
public class BindMobileEntity extends TResult{
  
	private static final long serialVersionUID = 1L;
	private String loginName;//登录名
	private String BindMobile;//手机号
	private String securitycode;//验证码
	private String nloginpwd;//重置密码后的新密码
	private String retobj;
	private int flag;
	private String msg;
	//登录名
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	//手机号
	public String getBindMobile()
	{
		return BindMobile;
	}
	public void setBindMobile(String BindMobile)
	{
		this.BindMobile=BindMobile;
	}
	//验证码
	public String getsecuritycode()
	{
		return securitycode;
	}
	public void setsecuritycode(String securitycode)
	{
		this.securitycode=securitycode;
	}
	//重置密码后的新密码
	public String getNewPwd() {
		return nloginpwd;
	}
	public void setNewPwd(String nloginpwd) {
		this.nloginpwd = nloginpwd;
	}
	
	
	public String getRetobj() {
		return retobj;
	}

	public void setRetobj(String retobj) {
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

