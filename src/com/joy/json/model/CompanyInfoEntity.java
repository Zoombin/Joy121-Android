package com.joy.json.model;

/**
 * 公司信息
 * 
 * @author daiye
 * 
 */
public class CompanyInfoEntity extends TResult {

	private static final long serialVersionUID = 1L;

	// {"retobj":{"LoginName":"steven","UserName":"刘想柱","UserPortalMenus":null,"LastLoginTime":"\/Date(1396919646686+0800)\/",
	// "Mail":"steven@joy121.com","Company":"DELPHI_SZ","EndInsured":"\/Date(1401465600000+0800)\/","IsManager":"1",
	// "CellNumber":"18662652121","Flag":"1","InsCompanyNO":"DELPHI_SZ2013","BirthDay":"\/Date(315936000000+0800)\/",
	// "PhoneNumber":"0512-88852121","LoginPasswd":"4c56ff4ce4aaf9573aa5dff913df997a","CompanyName":"德尔福电子（苏州）有限公司",
	// "Points":380,"CompanyInfo":{"PersonCount":0,"Flag":"1","YAccess":"","Contact":"德尔福","CompPhone":"051262831888",
	// "CompAddr":"苏州工业园区长安街123号","CompLogo":"delphi.jpg","CompName":"德尔福电子（苏州）有限公司","Points":0,"CompType":"",
	// "CompNo":null,"CreateTime":"\/Date(1392279404000+0800)\/","NAccess":"","Company":"DELPHI_SZ","AllowCustom":"1"},
	// "IdNo":"320511198001062561","UserCompanyBenefittype":null,"CreateTime":"\/Date(1303439971000+0800)\/","Gender":"0",
	// "StartInsured":"\/Date(1370016000000+0800)\/","ExpiredTime":"\/Date(-62135596800000+0800)\/","IsQuitUser":false,
	// "ClaimPrint":"~\/UControls\/Insurance\/ClaimApply\/WSI.ascx","EmployeeId":null}
	// }
	private int PersonCount;

	private String Flag;

	private String YAccess;

	private String Contact;

	private String CompPhone;

	private String CompLogo;

	private String CompAddr;

	private String CreateTime;

	private String NAccess;

	private String Company;

	private String AllowCustom;

	public int getPersonCount() {
		return PersonCount;
	}

	public void setPersonCount(int personCount) {
		PersonCount = personCount;
	}

	public String getFlag() {
		return Flag;
	}

	public void setFlag(String flag) {
		Flag = flag;
	}

	public String getYAccess() {
		return YAccess;
	}

	public void setYAccess(String yAccess) {
		YAccess = yAccess;
	}

	public String getContact() {
		return Contact;
	}

	public void setContact(String contact) {
		Contact = contact;
	}

	public String getCompPhone() {
		return CompPhone;
	}

	public void setCompPhone(String compPhone) {
		CompPhone = compPhone;
	}

	public String getCompAddr() {
		return CompAddr;
	}

	public void setCompAddr(String compAddr) {
		CompAddr = compAddr;
	}

	public String getCreateTime() {
		return CreateTime;
	}

	public void setCreateTime(String createTime) {
		CreateTime = createTime;
	}

	public String getNAccess() {
		return NAccess;
	}

	public void setNAccess(String nAccess) {
		NAccess = nAccess;
	}

	public String getCompany() {
		return Company;
	}

	public void setCompany(String company) {
		Company = company;
	}

	public String getAllowCustom() {
		return AllowCustom;
	}

	public void setAllowCustom(String allowCustom) {
		AllowCustom = allowCustom;
	}

	public String getCompLogo() {
		return CompLogo;
	}

	public void setCompLogo(String compLogo) {
		CompLogo = compLogo;
	}
}
