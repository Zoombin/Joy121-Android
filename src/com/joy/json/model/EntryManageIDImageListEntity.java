package com.joy.json.model;

public class EntryManageIDImageListEntity  extends TResult{
	/*
	 * rainbow  证件信息中的身份证
	 */
	private static final long serialVersionUID = 1L;
	private String Positive;
	private String Reverse;
	public String getPositive() {
		return Positive;
	}
	public void setPositive(String positive) {
		Positive = positive;
	}
	public String getReverse() {
		return Reverse;
	}
	public void setReverse(String reverse) {
		Reverse = reverse;
	}

}
