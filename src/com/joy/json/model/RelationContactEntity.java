package com.joy.json.model;

public class RelationContactEntity extends TResult{
	private static final long serialVersionUID = 1L;
	private int Id;
	private String LoginName;
	private String ReferLoginName;
	private int ReferRelationType;
	private String ReferRelationTypeName;
	private String ReferUserName;
	private String ReferMobile;
	private String ReferEmail;
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	public String getLoginName() {
		return LoginName;
	}
	public void setLoginName(String loginName) {
		LoginName = loginName;
	}
	public String getReferLoginName() {
		return ReferLoginName;
	}
	public void setReferLoginName(String referLoginName) {
		ReferLoginName = referLoginName;
	}
	public int getReferRelationType() {
		return ReferRelationType;
	}
	public void setReferRelationType(int referRelationType) {
		ReferRelationType = referRelationType;
	}
	public String getReferRelationTypeName() {
		return ReferRelationTypeName;
	}
	public void setReferRelationTypeName(String referRelationTypeName) {
		ReferRelationTypeName = referRelationTypeName;
	}
	public String getReferUserName() {
		return ReferUserName;
	}
	public void setReferUserName(String referUserName) {
		ReferUserName = referUserName;
	}
	public String getReferMobile() {
		return ReferMobile;
	}
	public void setReferMobile(String referMobile) {
		ReferMobile = referMobile;
	}
	public String getReferEmail() {
		return ReferEmail;
	}
	public void setReferEmail(String referEmail) {
		ReferEmail = referEmail;
	}

}
