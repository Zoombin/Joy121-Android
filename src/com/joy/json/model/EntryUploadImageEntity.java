package com.joy.json.model;

public class EntryUploadImageEntity extends TResult {

	private static final long serialVersionUID = 1L;
	private int RetCode;
	private String RetFilePath;
	private String RetMsg;
	public int getRetCode() {
		return RetCode;
	}
	public void setRetCode(int retCode) {
		RetCode = retCode;
	}
	public String getRetFilePath() {
		return RetFilePath;
	}
	public void setRetFilePath(String retFilePath) {
		RetFilePath = retFilePath;
	}
	public String getRetMsg() {
		return RetMsg;
	}
	public void setRetMsg(String retMsg) {
		RetMsg = retMsg;
	}
}
