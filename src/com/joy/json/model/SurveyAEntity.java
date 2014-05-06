package com.joy.json.model;

import java.util.List;

/**
 * 调查投票
 * @author daiye
 *
 */
public class SurveyAEntity extends TResult {

	private static final long serialVersionUID = 1L;

	private List<String> retobj;
	
	private String msg;
	
	private int flag;

	public List<String> getRetobj() {
		return retobj;
	}

	public void setRetobj(List<String> retobj) {
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
