package com.joy.json.model;
/**
 * 
 * @author rainbow 2015/8/19
 *  入离职管理的实体类
 *
 */
public class EntryManageEntity extends TResult{
	private static final long serialVersionUID = 1L;//为了在反序列化时，确保类版本的兼容性
	private String LoginName;//登录名
	public String getLoginName() {
		return LoginName;
	}
	public void setLoginName(String loginName) {
		LoginName = loginName;
	}
	private String ComDep;//应聘部门
	private String ComPos;//应聘职位
	private String ComEntryDate;//到岗日期
	private String Residence;//现居地址
	private String Mobile;//联系方式
	private String UrgentContact;//紧急联系人
	private String UrgentMobile;//紧急联系方式
	private String Regions;//户口所在地
	private String PersonName;//中文名
	private String EnglishName;//英文名
	private String Gender;//性别
	private String Address;//籍贯
	private String IdNo;//身份证号
	private String EducationNo;//学历证号
	private String DepositBank;//开户银行
	private String DepositCardNo;//银行账号
	private String DegreeNo;//公积金编号
	public String getPersonName() {
		return PersonName;
	}
	public void setPersonName(String personName) {
		PersonName = personName;
	}
	public String getEnglishName() {
		return EnglishName;
	}
	public void setEnglishName(String englishName) {
		EnglishName = englishName;
	}
	public String getGender() {
		return Gender;
	}
	public void setGender(String gender) {
		Gender = gender;
	}
	public String getAddress() {
		return Address;
	}
	public void setAddress(String address) {
		Address = address;
	}
	public String getIdNo() {
		return IdNo;
	}
	public void setIdNo(String idNo) {
		IdNo = idNo;
	}
	public String getEducationNo() {
		return EducationNo;
	}
	public void setEducationNo(String educationNo) {
		EducationNo = educationNo;
	}
	public String getDepositBank() {
		return DepositBank;
	}
	public void setDepositBank(String depositBank) {
		DepositBank = depositBank;
	}
	public String getDepositCardNo() {
		return DepositCardNo;
	}
	public void setDepositCardNo(String depositCardNo) {
		DepositCardNo = depositCardNo;
	}
	public String getDegreeNo() {
		return DegreeNo;
	}
	public void setDegreeNo(String degreeNo) {
		DegreeNo = degreeNo;
	}
	public String getComDep() {
		return ComDep;
	}
	public void setComDep(String comDep) {
		ComDep = comDep;
	}
	public String getComPos() {
		return ComPos;
	}
	public void setComPos(String comPos) {
		ComPos = comPos;
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
