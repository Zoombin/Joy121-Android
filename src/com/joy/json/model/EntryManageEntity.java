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
	private String entryDate;//到岗日期
	private String nowAddress;//现居地址
	private String contactWay;//联系方式
	private String emergencyPerson;//紧急联系人
	private String emergencyContact;//紧急联系方式
	private String houschold;//户口所在地
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
	
	public String getEntryDate() {
		return entryDate;
	}
	public void setEntryDate(String entryDate) {
		this.entryDate = entryDate;
	}
	
	public String getNowAddress() {
		return nowAddress;
	}
	public void setNowAddress(String nowAddress) {
		this.nowAddress = nowAddress;
	}
	
	public String getContactWay() {
		return contactWay;
	}
	public void setContactWay(String contactWay) {
		this.contactWay = contactWay;
	}
	
	public String getEmergencyPerson() {
		return emergencyPerson;
	}
	public void setEmergencyPerson(String emergencyPerson) {
		this.emergencyPerson = emergencyPerson;
	}
	
	public String getEmergencyContact() {
		return emergencyContact;
	}
	public void setEmergencyContact(String emergencyContact) {
		this.emergencyContact = emergencyContact;
	}
	
	public String getHouschold() {
		return houschold;
	}
	public void setHouschold(String houschold) {
		this.houschold = houschold;
	}
}
