package com.joy.json.model;

import java.util.List;

/**
 * 工资单列表
 * @author ryan zhou 2014-11-2
 *
 */
public class PayrollListEntity extends TResult {

	private static final long serialVersionUID = 1L;

	private List<PayrollBriefEntity> retobj;
	
	private String msg;
	
	private int flag;

	public List<PayrollBriefEntity> getRetobj() {
		return retobj;
	}

	public void setRetobj(List<PayrollBriefEntity> retobj) {
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
