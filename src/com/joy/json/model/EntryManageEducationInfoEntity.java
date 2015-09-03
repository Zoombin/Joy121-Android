package com.joy.json.model;

public class EntryManageEducationInfoEntity extends TResult{
/*
 * rainbow  入职管理中的学习经历实体
 */
	private static final long serialVersionUID = 1L;
	private String Date;//日期
	private String School;//学校
	private String Profession;//专业
	private String Achievement;//收获
	public String getDate() {
		return Date;
	}
	public void setDate(String date) {
		Date = date;
	}
	public String getSchool() {
		return School;
	}
	public void setSchool(String school) {
		School = school;
	}
	public String getProfession() {
		return Profession;
	}
	public void setProfession(String profession) {
		Profession = profession;
	}
	public String getAchievement() {
		return Achievement;
	}
	public void setAchievement(String achievement) {
		Achievement = achievement;
	}
	
	
}
