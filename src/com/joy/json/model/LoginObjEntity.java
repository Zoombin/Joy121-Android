package com.joy.json.model;


public class LoginObjEntity extends TResult {
	
	private static final long serialVersionUID = 1L;
	
//	{"retobj":{"LoginName":"steven","UserName":"刘想柱","UserPortalMenus":null,"LastLoginTime":"\/Date(1396079508377+0800)\/",
//	"Mail":"steven@joy121.com","Company":"DELPHI_SZ","EndInsured":"\/Date(1401465600000+0800)\/","IsManager":"1","CellNumber":"18662652121",
//	"Flag":"1","InsCompanyNO":"DELPHI_SZ2013","BirthDay":"\/Date(315936000000+0800)\/","PhoneNumber":"0512-88852121-121",
//	"LoginPasswd":"4c56ff4ce4aaf9573aa5dff913df997a","CompanyName":"德尔福电子（苏州）有限公司","Points":460,"CompanyInfo":null,"IdNo":"320511198001062561",
//	"UserCompanyBenefittype":null,"CreateTime":"\/Date(1303439971000+0800)\/","Gender":"0","StartInsured":"\/Date(1370016000000+0800)\/",
//	"ExpiredTime":"\/Date(-62135596800000+0800)\/","IsQuitUser":false,"ClaimPrint":"~\/UControls\/Insurance\/ClaimApply\/WSI.ascx","EmployeeId":null},
//	"msg":null,"flag":1}
	
	private String LoginName;
	
	private String UserName;
	
	private String UserPortalMenus;
	
	private String LastLoginTime;
	
	private String Mail;
	
	private String Company;
	
	private String EndInsured;
	
	private String IsManager;
	
	private String CellNumber;
	
	private String Flag;
	
	private String InsCompanyNO;
	
	private String BirthDay;
	
	private String PhoneNumber;
	
	private String LoginPasswd;
	
	private String CompanyName;
	
	private int Points;
	
	private String CompanyInfo;
	
	private String IdNo;
	
	private String UserCompanyBenefittype;
	
	private String CreateTime;
	
	private String Gender;
	
	private String StartInsured;
	
	private String ExpiredTime;
	
	private boolean IsQuitUser;
	
	private String ClaimPrint;
	
	private String EmployeeId;

	public String getLoginName() {
		return LoginName;
	}

	public void setLoginName(String loginName) {
		LoginName = loginName;
	}

	public String getUserName() {
		return UserName;
	}

	public void setUserName(String userName) {
		UserName = userName;
	}

	public String getUserPortalMenus() {
		return UserPortalMenus;
	}

	public void setUserPortalMenus(String userPortalMenus) {
		UserPortalMenus = userPortalMenus;
	}

	public String getLastLoginTime() {
		return LastLoginTime;
	}

	public void setLastLoginTime(String lastLoginTime) {
		LastLoginTime = lastLoginTime;
	}

	public String getMail() {
		return Mail;
	}

	public void setMail(String mail) {
		Mail = mail;
	}

	public String getCompany() {
		return Company;
	}

	public void setCompany(String company) {
		Company = company;
	}

	public String getEndInsured() {
		return EndInsured;
	}

	public void setEndInsured(String endInsured) {
		EndInsured = endInsured;
	}

	public String getIsManager() {
		return IsManager;
	}

	public void setIsManager(String isManager) {
		IsManager = isManager;
	}

	public String getCellNumber() {
		return CellNumber;
	}

	public void setCellNumber(String cellNumber) {
		CellNumber = cellNumber;
	}

	public String getFlag() {
		return Flag;
	}

	public void setFlag(String flag) {
		Flag = flag;
	}

	public String getInsCompanyNO() {
		return InsCompanyNO;
	}

	public void setInsCompanyNO(String insCompanyNO) {
		InsCompanyNO = insCompanyNO;
	}

	public String getBirthDay() {
		return BirthDay;
	}

	public void setBirthDay(String birthDay) {
		BirthDay = birthDay;
	}

	public String getPhoneNumber() {
		return PhoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		PhoneNumber = phoneNumber;
	}

	public String getLoginPasswd() {
		return LoginPasswd;
	}

	public void setLoginPasswd(String loginPasswd) {
		LoginPasswd = loginPasswd;
	}

	public String getCompanyName() {
		return CompanyName;
	}

	public void setCompanyName(String companyName) {
		CompanyName = companyName;
	}

	public int getPoints() {
		return Points;
	}

	public void setPoints(int points) {
		Points = points;
	}

	public String getCompanyInfo() {
		return CompanyInfo;
	}

	public void setCompanyInfo(String companyInfo) {
		CompanyInfo = companyInfo;
	}

	public String getIdNo() {
		return IdNo;
	}

	public void setIdNo(String idNo) {
		IdNo = idNo;
	}

	public String getUserCompanyBenefittype() {
		return UserCompanyBenefittype;
	}

	public void setUserCompanyBenefittype(String userCompanyBenefittype) {
		UserCompanyBenefittype = userCompanyBenefittype;
	}

	public String getCreateTime() {
		return CreateTime;
	}

	public void setCreateTime(String createTime) {
		CreateTime = createTime;
	}

	public String getGender() {
		return Gender;
	}

	public void setGender(String gender) {
		Gender = gender;
	}

	public String getStartInsured() {
		return StartInsured;
	}

	public void setStartInsured(String startInsured) {
		StartInsured = startInsured;
	}

	public String getExpiredTime() {
		return ExpiredTime;
	}

	public void setExpiredTime(String expiredTime) {
		ExpiredTime = expiredTime;
	}

	public boolean isIsQuitUser() {
		return IsQuitUser;
	}

	public void setIsQuitUser(boolean isQuitUser) {
		IsQuitUser = isQuitUser;
	}

	public String getClaimPrint() {
		return ClaimPrint;
	}

	public void setClaimPrint(String claimPrint) {
		ClaimPrint = claimPrint;
	}

	public String getEmployeeId() {
		return EmployeeId;
	}

	public void setEmployeeId(String employeeId) {
		EmployeeId = employeeId;
	}
}
