package com.joy.json.model;

import java.util.List;

public class EntryDepartmentEntity extends TResult{

	/**
	 * rainbow
	 */
	private static final long serialVersionUID = 1L;
    private List<EntryDepartmentDetailEntity> Retobj;
    private int CurentPage;
    private int TotalRecord;
    private int TotalPage;
    private int RetCode;
    private String  RetMsg;
	public List<EntryDepartmentDetailEntity> getRetobj() {
		return Retobj;
	}
	public void setRetobj(List<EntryDepartmentDetailEntity> retobj) {
		Retobj = retobj;
	}
	public int getCurentPage() {
		return CurentPage;
	}
	public void setCurentPage(int curentPage) {
		CurentPage = curentPage;
	}
	public int getTotalRecord() {
		return TotalRecord;
	}
	public void setTotalRecord(int totalRecord) {
		TotalRecord = totalRecord;
	}
	public int getTotalPage() {
		return TotalPage;
	}
	public void setTotalPage(int totalPage) {
		TotalPage = totalPage;
	}
	public int getRetCode() {
		return RetCode;
	}
	public void setRetCode(int retCode) {
		RetCode = retCode;
	}
	public String getRetMsg() {
		return RetMsg;
	}
	public void setRetMsg(String retMsg) {
		RetMsg = retMsg;
	}
}
