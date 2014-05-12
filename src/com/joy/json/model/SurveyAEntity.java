package com.joy.json.model;

import java.util.List;

/**
 * 调查投票
 * 
 * @author daiye
 * 
 */
public class SurveyAEntity extends TResult {

	private static final long serialVersionUID = 1L;

	private int retobj;

	private String msg;

	private int flag;

	public int getRetobj() {
		return retobj;
	}

	public void setRetobj(int retobj) {
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
