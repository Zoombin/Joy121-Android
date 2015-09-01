package com.joy.json.model;

public class EntryManageFamilyInfoEntity extends TResult{

	/**
	 * rainbow
	 */
	private static final long serialVersionUID = 1L;
	private String Name;//家人姓名
	private String Birthday;//家人生日
	private String Address;//家人住址
	private String RelationShip;//家人关系
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getBirthday() {
		return Birthday;
	}
	public void setBirthday(String birthday) {
		Birthday = birthday;
	}
	public String getAddress() {
		return Address;
	}
	public void setAddress(String address) {
		Address = address;
	}
	public String getRelationShip() {
		return RelationShip;
	}
	public void setRelationShip(String relationShip) {
		RelationShip = relationShip;
	}
}