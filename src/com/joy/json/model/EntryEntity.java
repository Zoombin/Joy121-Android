package com.joy.json.model;

/**
 * 入职管理信息对象
 * @author rainbow
 *
 */
public class EntryEntity extends TResult {


	private static final long serialVersionUID = 1L;

	private EntryManageEntity RetObj;
	
	private int RetCode;
	
	private int TotalRecord;

	public EntryManageEntity getRetObj() {
		return RetObj;
	}

	public void setRetObj(EntryManageEntity retObj) {
		RetObj = retObj;
	}

	public int getRetCode() {
		return RetCode;
	}

	public void setRetCode(int retCode) {
		RetCode = retCode;
	}

	public int getTotalRecord() {
		return TotalRecord;
	}

	public void setTotalRecord(int totalRecord) {
		TotalRecord = totalRecord;
	}

}
