package com.joy.json.model;

public class EntryManageWorkExperienceInfoEntity extends TResult{
	/*
	 * rainbow  工作经历
	 */
	private static final long serialVersionUID = 1L; 
	private String Date;//时间
	private String Company;//公司
	private String Position;//职位
	private String Achievenment;//收获
	public String getDate() {
		return Date;
	}
	public void setDate(String date) {
		Date = date;
	}
	public String getCompany() {
		return Company;
	}
	public void setCompany(String company) {
		Company = company;
	}
	public String getPosition() {
		return Position;
	}
	public void setPosition(String position) {
		Position = position;
	}
	public String getAchievenment() {
		return Achievenment;
	}
	public void setAchievenment(String achievenment) {
		Achievenment = achievenment;
	}
}
