package com.joy.json.model;

/**
 * 用户信息
 * @author ryan zhou 2014-11-27
 *
 */
public class Payroll extends TResult {

	private static final long serialVersionUID = 1L;

	private PayrollEntity retobj;
	
	private String msg;
	
	private int flag;

	public PayrollEntity getRetobj() {
		return retobj;
	}

	public void setRetobj(PayrollEntity retobj) {
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
