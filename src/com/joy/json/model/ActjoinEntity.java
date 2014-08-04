package com.joy.json.model;

import java.util.List;

/**
 * 活动报名
 * @author daiye
 *
 */
public class ActjoinEntity extends TResult {

	private static final long serialVersionUID = 1L;

	private Result retobj;
	
	private String msg;
	
	private int flag;

	public Result getRetobj() {
		return retobj;
	}

	public void setRetobj(Result retobj) {
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
	
	public class Result{
		String respcode;
		String result;
		public String getRespcode() {
			return respcode;
		}
		public void setRespcode(String respcode) {
			this.respcode = respcode;
		}
		public String getResult() {
			return result;
		}
		public void setResult(String result) {
			this.result = result;
		}
	}
}
