package com.joy.json.model;

/**
 * 订单提交
 * 
 * @author daiye
 * 
 */
public class OrderSubmitEntity extends TResult {

	private static final long serialVersionUID = 1L;

	private String pId;

	private String pType;

	private String receiver;

	private String recAdd;

	private String recPhone;

	private String retobj;

	private String msg;

	private String pRemark;

	private int flag;

	public String getpRemark() {
		return pRemark;
	}

	public void setpRemark(String pRemark) {
		this.pRemark = pRemark;
	}

	public String getpId() {
		return pId;
	}

	public void setpId(String pId) {
		this.pId = pId;
	}

	public String getpType() {
		return pType;
	}

	public void setpType(String pType) {
		this.pType = pType;
	}

	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	public String getRecAdd() {
		return recAdd;
	}

	public void setRecAdd(String recAdd) {
		this.recAdd = recAdd;
	}

	public String getRecPhone() {
		return recPhone;
	}

	public void setRecPhone(String recPhone) {
		this.recPhone = recPhone;
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
