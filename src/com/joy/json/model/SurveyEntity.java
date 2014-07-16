package com.joy.json.model;

import java.util.List;

/**
 * 调查
 * @author daiye
 *
 */
public class SurveyEntity extends TResult {

	private static final long serialVersionUID = 1L;

	private List<SurveyDetailEntity> retobj;
	
	private String msg;
	
	private int flag;
	
	public String isexpired;
	
	public List<SurveyDetailEntity> getRetobj() {
		return retobj;
	}

	public void setRetobj(List<SurveyDetailEntity> retobj) {
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
	
	public String getIsExpired() {
		return isexpired;
	}
	
	public void setIsExpired(String expired) {
		this.isexpired = expired;
	}
}
