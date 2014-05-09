package com.joy.json.model;

/**
 * 调查详情
 * @author daiye
 *
 */
public class SurveyDetailEntity extends TResult {

	private static final long serialVersionUID = 1L;
	
//	{"retobj":[{"Description":"为了根据员工实际需求和公司财务的双赢，做此次调查。",
//		"ScopeLevel":"DELPHI_SZ","LoginName":"steven",
//		"Questions":"2000以下^2000-3000^3000-5000^5000-10000^10000-20000^20000-30000^30000-50000^50000以上",
//		"ExpireTime":"\/Date(1399690800000+0800)\/","SurveyId":10,"OptionType":0,"ResultShow":1,"Title":"公司员工四金缴纳"}
//	,{"Description":"根据公司家庭日安排，为了活动参与的广泛性，特发起这次活动投票征询。","ScopeLevel":"DELPHI_SZ","LoginName":"steven","Questions":"公司组织看电影。^公司组织吃饭。^公司组织游戏比赛。^公司组织旅游。^公司组织球类比赛。^公司组织趣味游戏。","ExpireTime":"\/Date(1399716000000+0800)\/","SurveyId":8,"OptionType":1,"ResultShow":0,"Title":"家庭日活动投票"},{"Description":"春节来临之际，公司为了表达对员工辛勤劳动的回报，准备发一批过节礼品，征询大家的需求，特举行这次投票。","ScopeLevel":"DELPHI_SZ","LoginName":"steven","Questions":"格兰仕微波炉^爱仕达电饭煲^步步高跑步机^爱玛电动车^海信电视机^联想电脑^苹果平板电脑^家用橱柜","ExpireTime":"\/Date(1399716000000+0800)\/","SurveyId":9,"OptionType":1,"ResultShow":1,"Title":"春节来临，礼品不断"},{"Description":"为了方便员工，满足每个人不同的需要，通过这次调查选择元旦礼品。","ScopeLevel":"DELPHI_SZ","LoginName":null,"Questions":"弹性自选^超市卡^发现金^买各种礼券^买电影票，健身卡^随便什么都行","ExpireTime":"\/Date(1400918400000+0800)\/","SurveyId":11,"OptionType":1,"ResultShow":1,"Title":"元旦节日福利调查"}],"msg":null,"flag":1}

	private String Description;
	
	private String ScopeLevel;
	
	private String LoginName;
	
	private String Questions;
	
	private String ExpireTime;
	
	private int SurveyId;
	
	private int OptionType;
	
	private int ResultShow;
	
	private String Title;

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public String getScopeLevel() {
		return ScopeLevel;
	}

	public void setScopeLevel(String scopeLevel) {
		ScopeLevel = scopeLevel;
	}

	public String getLoginName() {
		return LoginName;
	}

	public void setLoginName(String loginName) {
		LoginName = loginName;
	}

	public String getQuestions() {
		return Questions;
	}

	public void setQuestions(String questions) {
		Questions = questions;
	}

	public String getExpireTime() {
		return ExpireTime;
	}

	public void setExpireTime(String expireTime) {
		ExpireTime = expireTime;
	}

	public int getSurveyId() {
		return SurveyId;
	}

	public void setSurveyId(int surveyId) {
		SurveyId = surveyId;
	}

	public int getOptionType() {
		return OptionType;
	}

	public void setOptionType(int optionType) {
		OptionType = optionType;
	}

	public int getResultShow() {
		return ResultShow;
	}

	public void setResultShow(int resultShow) {
		ResultShow = resultShow;
	}

	public String getTitle() {
		return Title;
	}

	public void setTitle(String title) {
		Title = title;
	}
}
