package com.joy.json.model;

public class EntryManageIDImageEntity extends TResult{
	/*
	 * rainbow  入职管理中身份证件图片反正面
	 */
	private static final long serialVersionUID = 1L;
    private String Positive;//正面照
    private String Reverse;//反面照
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
