package com.joy.json.model;
/**
 * 
 * @author rainbow 2015/8/19
 *  入离职管理的实体类
 *
 */
public class EntryManageEntity extends TResult{
	private static final long serialVersionUID = 1L;//为了在反序列化时，确保类版本的兼容性
	private String department;//应聘部门
	private String position;//应聘职位
	private String ComEntryDate;//到岗日期
	private String Residence;//现居地址
	private String Mobile;//联系方式
	private String UrgentContact;//紧急联系人
	private String UrgentMobile;//紧急联系方式
	private String Regions;//户口所在地
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getComEntryDate() {
		return ComEntryDate;
	}
	public void setComEntryDate(String comEntryDate) {
		ComEntryDate = comEntryDate;
	}
	public String getResidence() {
		return Residence;
	}
	public void setResidence(String residence) {
		Residence = residence;
	}
	public String getMobile() {
		return Mobile;
	}
	public void setMobile(String mobile) {
		Mobile = mobile;
	}
	public String getUrgentContact() {
		return UrgentContact;
	}
	public void setUrgentContact(String urgentContact) {
		UrgentContact = urgentContact;
	}
	public String getUrgentMobile() {
		return UrgentMobile;
	}
	public void setUrgentMobile(String urgentMobile) {
		UrgentMobile = urgentMobile;
	}
	public String getRegions() {
		return Regions;
	}
	public void setRegions(String regions) {
		Regions = regions;
	}
}
