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
	private String Residence;//户口所在地
	private String Mobile;//联系方式
	private String UrgentContact;//紧急联系人
	private String UrgentMobile;//紧急联系方式
	private String Regions;//籍贯
	private String PersonName;//中文名
	private String EnglishName;//英文名
	private int Gender;//性别
	private String Address;//现居地
	private String Nation;//民族
	private String MaritalStatus;//婚姻状况
	private String PoliticalStatus;//政治面貌
	private String HealthCondition;//健康状况
	private String CulturalDegree;//文化程度
	private String UrgentAddr;//紧急联系人地址
	private String SocialSecurityNo;//社保账号
	private String Major;//专业
	private String IdNo;//身份证号
	private String EducationNo;//学历证号
	private String DepositBank;//开户银行
	private String DepositCardNo;//银行账号
	private String AccumFund;//公积金编号
	private String Materials;//证件
	private String Experiences;//学习经历
	private String Family;//家庭信息
	private String Interesting;//兴趣爱好
	private int Submited;//提交状态位
	private int CurrentStep;//当前保存到的页面数
	public int getCurrentStep() {
		return CurrentStep;
	}
	public void setCurrentStep(int currentStep) {
		CurrentStep = currentStep;
	}
	public int getSubmited() {
		return Submited;
	}
	public void setSubmited(int submited) {
		Submited = submited;
	}
	public String getInteresting() {
		return Interesting;
	}
	public void setInteresting(String interesting) {
		Interesting = interesting;
	}
	public String getFamily() {
		return Family;
	}
	public void setFamily(String family) {
		Family = family;
	}
	public String getAccumFund() {
		return AccumFund;
	}
	public void setAccumFund(String accumFund) {
		AccumFund = accumFund;
	}
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
	public int getGender() {
		return Gender;
	}
	public void setGender(int gender) {
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
	public String getMaterials() {
		return Materials;
	}
	public void setMaterials(String materials) {
		Materials = materials;
	}
	public String getExperiences() {
		return Experiences;
	}
	public void setExperiences(String experiences) {
		Experiences = experiences;
	}
	public String getNation() {
		return Nation;
	}
	public void setNation(String nation) {
		Nation = nation;
	}
	public String getMaritalStatus() {
		return MaritalStatus;
	}
	public void setMaritalStatus(String maritalStatus) {
		MaritalStatus = maritalStatus;
	}
	public String getPoliticalStatus() {
		return PoliticalStatus;
	}
	public void setPoliticalStatus(String politicalStatus) {
		PoliticalStatus = politicalStatus;
	}
	public String getHealthCondition() {
		return HealthCondition;
	}
	public void setHealthCondition(String healthCondition) {
		HealthCondition = healthCondition;
	}
	public String getCulturalDegree() {
		return CulturalDegree;
	}
	public void setCulturalDegree(String culturalDegree) {
		CulturalDegree = culturalDegree;
	}
	public String getUrgentAddr() {
		return UrgentAddr;
	}
	public void setUrgentAddr(String urgentAddr) {
		UrgentAddr = urgentAddr;
	}
	public String getSocialSecurityNo() {
		return SocialSecurityNo;
	}
	public void setSocialSecurityNo(String socialSecurityNo) {
		SocialSecurityNo = socialSecurityNo;
	}
	public String getMajor() {
		return Major;
	}
	public void setMajor(String major) {
		Major = major;
	}
	
	
}
