package com.joy.json.model;

/**
 * 活动详情
 * @author daiye
 *
 */
public class ActivityDetailEntity extends TResult {

	private static final long serialVersionUID = 1L;

//	{"retobj":[{"LocationAddr":"高教区白鹭园","ActLocationId":0,"LoginName":"steven",
//		"ActPicturePath":"http:\/\/www.tech-ex.com\/article_images3\/9\/514449\/195670_2.jpg","ActName":"复活节慈善义卖活动","ActFee":0,
//		"CurrentCount":44,"ActTypeName":null,"DeadLine":"\/Date(1400083200000+0800)\/","ScopeLevel":"DELPHI_SZ","LimitCount":100,
//		"EndTime":"\/Date(1400727600000+0800)\/","StartTime":"\/Date(1400720400000+0800)\/","ActId":1,
//		"Content":"<p>复活节慈善义卖活动<\/p>\n<p>时间：2014年5月22日 上午9:00-11:00<\/p>\n<p>地点：苏州工业园区高教区白鹭园<\/p>","ActTypeId":1}],"msg":null,"flag":1}
	
	private String LocationAddr;
	
	private int ActLocationId;
	
	private String LoginName;
	
	private String ActPicturePath;
	
	private String ActName;
	
	private int ActFee;
	
	private String CurrentCount;
	
	private String ActTypeName;
	
	private String DeadLine;
	
	private String ScopeLevel;
	
	private int LimitCount;
	
	private String EndTime;
	
	private String StartTime;

	private int ActId;
	
	private String Content;
	
	private int ActTypeId;
	
	private String isexprired;
	
	private String IsJoin;
	
	public Boolean getIsEnabled(String loginname) {
		if (this.getIsexprired()) {
			return false;
		} else {
			if (loginname == null) {
				return true;
			}
			if (this.getLoginName().equals(loginname)) {
				return false;
			} else {
				return true;
			}
		}
	}
	
	public String getStatus(String loginname) {
		if (this.getIsexprired()) {
			if (this.getIsJoin()) {
				return "已参与";
			} else {
				return "未参与";
			}
		} else {
			if (loginname == null) {
				return "未报名";
			}
			if (this.getLoginName().equals(loginname)) {
				return "已报名";
			} else {
				return "未报名";
			}
		}
	}
	
	public Boolean getIsJoin() {
		return IsJoin.equals("1");
	}

	public void setIsJoin(String isJoin) {
		this.IsJoin = isJoin;
	}

	public Boolean getIsexprired() {
		return isexprired.equals("2");
	}

	public void setIsexprired(String isexprired) {
		this.isexprired = isexprired;
	}

	public String getLocationAddr() {
		return LocationAddr;
	}

	public void setLocationAddr(String locationAddr) {
		LocationAddr = locationAddr;
	}

	public int getActLocationId() {
		return ActLocationId;
	}

	public void setActLocationId(int actLocationId) {
		ActLocationId = actLocationId;
	}

	public String getLoginName() {
		return LoginName;
	}

	public void setLoginName(String loginName) {
		LoginName = loginName;
	}

	public String getActPicturePath() {
		return ActPicturePath;
	}

	public void setActPicturePath(String actPicturePath) {
		ActPicturePath = actPicturePath;
	}

	public String getActName() {
		return ActName;
	}

	public void setActName(String actName) {
		ActName = actName;
	}

	public int getActFee() {
		return ActFee;
	}

	public void setActFee(int actFee) {
		ActFee = actFee;
	}

	public String getCurrentCount() {
		return CurrentCount;
	}

	public void setCurrentCount(String currentCount) {
		CurrentCount = currentCount;
	}

	public String getActTypeName() {
		return ActTypeName;
	}

	public void setActTypeName(String actTypeName) {
		ActTypeName = actTypeName;
	}

	public String getDeadLine() {
		return DeadLine;
	}

	public void setDeadLine(String deadLine) {
		DeadLine = deadLine;
	}

	public String getScopeLevel() {
		return ScopeLevel;
	}

	public void setScopeLevel(String scopeLevel) {
		ScopeLevel = scopeLevel;
	}

	public int getLimitCount() {
		return LimitCount;
	}

	public void setLimitCount(int limitCount) {
		LimitCount = limitCount;
	}

	public String getEndTime() {
		return EndTime;
	}

	public void setEndTime(String endTime) {
		EndTime = endTime;
	}

	public String getStartTime() {
		return StartTime;
	}

	public void setStartTime(String startTime) {
		StartTime = startTime;
	}

	public int getActId() {
		return ActId;
	}

	public void setActId(int actId) {
		ActId = actId;
	}

	public String getContent() {
		return Content;
	}

	public void setContent(String content) {
		Content = content;
	}

	public int getActTypeId() {
		return ActTypeId;
	}

	public void setActTypeId(int actTypeId) {
		ActTypeId = actTypeId;
	}
}
