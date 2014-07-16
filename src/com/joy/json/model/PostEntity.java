package com.joy.json.model;

import java.util.List;

/**
 * 公告
 * @author daiye
 *
 */
public class PostEntity extends TResult {

	private static final long serialVersionUID = 1L;

	private List<PostDetailEntity> retobj;
	
	private String msg;
	
	private int flag;
	
	public String isexpired;

	public List<PostDetailEntity> getRetobj() {
		return retobj;
	}

	public void setRetobj(List<PostDetailEntity> retobj) {
		this.retobj = retobj;
	}

	public String getMsg() {
		return msg;
	}

	public void setIsExpired(String expired) {
		this.isexpired = expired;
	}
	
	public String getIsExpired() {
		return isexpired;
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
