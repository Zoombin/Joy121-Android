package com.joy.json.model;

public class EntryDepartmentDetailEntity extends TResult{

	/**
	 * rainbow
	 */
	private static final long serialVersionUID = 1L;
	private String Id;
	private String ComGroup;
	private String SysKey;
	private String SysValue;
	private String SysKeyName;
	private int ParentId;
	private String CreateTime;
	private int Flag;
	public String getId() {
		return Id;
	}
	public void setId(String id) {
		Id = id;
	}
	public String getComGroup() {
		return ComGroup;
	}
	public void setComGroup(String comGroup) {
		ComGroup = comGroup;
	}
	public String getSysKey() {
		return SysKey;
	}
	public void setSysKey(String sysKey) {
		SysKey = sysKey;
	}
	public String getSysValue() {
		return SysValue;
	}
	public void setSysValue(String sysValue) {
		SysValue = sysValue;
	}
	public String getSysKeyName() {
		return SysKeyName;
	}
	public void setSysKeyName(String sysKeyName) {
		SysKeyName = sysKeyName;
	}
	public int getParentId() {
		return ParentId;
	}
	public void setParentId(int parentId) {
		ParentId = parentId;
	}
	public String getCreateTime() {
		return CreateTime;
	}
	public void setCreateTime(String createTime) {
		CreateTime = createTime;
	}
	public int getFlag() {
		return Flag;
	}
	public void setFlag(int flag) {
		Flag = flag;
	}
}