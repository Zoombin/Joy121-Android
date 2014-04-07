package com.joy.json.model;

/**
 * 积分历史信息
 * @author daiye
 *
 */
public class UserPointsHisEntity extends TResult {
	
	private static final long serialVersionUID = 1L;
	
//	{"flag":1,"msg":null,
//		"retobj":[{"LoginName":"steven","Company":"","Action":"0","Points":80,"ActionTime":"\/Date(1396433988000+0800)\/","Remark":"订单20140402061855406的费用",
//			"COMPNAME":null,"UserName":null,"IDNo":null},
			
//			{"LoginName":"steven","Company":"","Action":"0","Points":80,"ActionTime":"\/Date(1395559924000+0800)\/","Remark":"订单20140323033158864的费用","COMPNAME":null,"UserName":null,"IDNo":null},{"LoginName":"steven","Company":"","Action":"0","Points":60,"ActionTime":"\/Date(1391844131000+0800)\/","Remark":"订单20140208032124466的费用","COMPNAME":null,"UserName":null,"IDNo":null},{"LoginName":"steven","Company":"YEY","Action":"0","Points":200,"ActionTime":"\/Date(1389089950000+0800)\/","Remark":"订单20140107061900339的费用","COMPNAME":"虚拟公司","UserName":null,"IDNo":null},{"LoginName":"steven","Company":"YEY","Action":"1","Points":200,"ActionTime":"\/Date(1389024000000+0800)\/","Remark":"2014年春节员工福利","COMPNAME":"虚拟公司","UserName":null,"IDNo":null},{"LoginName":"steven","Company":"YEY","Action":"1","Points":200,"ActionTime":"\/Date(1377964800000+0800)\/","Remark":"2013年中秋节福利","COMPNAME":"虚拟公司","UserName":null,"IDNo":null},{"LoginName":"steven","Company":"YEY","Action":"1","Points":200,"ActionTime":"\/Date(1369843200000+0800)\/","Remark":"2013年端午节福","COMPNAME":"虚拟公司","UserName":null,"IDNo":null},{"LoginName":"steven","Company":"YEY","Action":"1","Points":200,"ActionTime":"\/Date(1356969600000+0800)\/","Remark":"2013年员工生日福利","COMPNAME":"虚拟公司","UserName":null,"IDNo":null}]}
	
	private String LoginName;
	
	private String Company;
	
	private String Action;
	
	private String Points;
	
	private String ActionTime;
	
	private String Remark;
	
	private String COMPNAME;
	
	private String UserName;
	
	private int IDNo;

	public String getLoginName() {
		return LoginName;
	}

	public void setLoginName(String loginName) {
		LoginName = loginName;
	}

	public String getCompany() {
		return Company;
	}

	public void setCompany(String company) {
		Company = company;
	}

	public String getAction() {
		return Action;
	}

	public void setAction(String action) {
		Action = action;
	}

	public String getPoints() {
		return Points;
	}

	public void setPoints(String points) {
		Points = points;
	}

	public String getActionTime() {
		return ActionTime;
	}

	public void setActionTime(String actionTime) {
		ActionTime = actionTime;
	}

	public String getRemark() {
		return Remark;
	}

	public void setRemark(String remark) {
		Remark = remark;
	}

	public String getCOMPNAME() {
		return COMPNAME;
	}

	public void setCOMPNAME(String cOMPNAME) {
		COMPNAME = cOMPNAME;
	}

	public String getUserName() {
		return UserName;
	}

	public void setUserName(String userName) {
		UserName = userName;
	}

	public int getIDNo() {
		return IDNo;
	}

	public void setIDNo(int iDNo) {
		IDNo = iDNo;
	}
}
