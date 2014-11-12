package com.joy.json.model;

/**
 * 积分信息
 * @author ryan zhou 2014-11-2
 *
 */

public class ContactEntity extends TResult {
	private static final long serialVersionUID = 1L;
	
	private String PersonName;
	private String Gender;
	private String Phone;
	private String Mobile;
	private String Fax;
	private String ComDep;
	private String ComPos;
	private String Email;
	private String EnglishName;
	private String CompanyName;
	
	public String getCompanyName() {
		return CompanyName;
	}


	public void setCompanyName(String companyName) {
		CompanyName = companyName;
	}


	private int background;
	

	public int getBackground() {
		return background;
	}


	public void setBackground(int background) {
		this.background = background;
	}


	public String getPersonName() {
		return PersonName;
	}


	public void setPersonName(String personName) {
		PersonName = personName;
	}


	public String getGender() {
		return Gender;
	}


	public void setGender(String gender) {
		Gender = gender;
	}


	public String getPhone() {
		return Phone;
	}


	public void setPhone(String phone) {
		Phone = phone;
	}


	public String getMobile() {
		return Mobile;
	}


	public void setMobile(String mobile) {
		Mobile = mobile;
	}


	public String getFax() {
		return Fax;
	}


	public void setFax(String fax) {
		Fax = fax;
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


	public String getEmail() {
		return Email;
	}


	public void setEmail(String email) {
		Email = email;
	}


	public String getEnglishName() {
		return EnglishName;
	}


	public void setEnglishName(String englishName) {
		EnglishName = englishName;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	public ContactEntity() {
		// TODO Auto-generated constructor stub
	}

}
