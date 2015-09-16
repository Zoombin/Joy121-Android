package com.joy.json.model;

import java.util.List;

public class RelationContactListEntity extends TResult{
	private static final long serialVersionUID = 1L;
	private List<RelationContactEntity> RetObj;
    private String RetMsg;
	public List<RelationContactEntity> getRetObj() {
		return RetObj;
	}
	public void setRetObj(List<RelationContactEntity> retObj) {
		RetObj = retObj;
	}
	public String getRetMsg() {
		return RetMsg;
	}
	public void setRetMsg(String retMsg) {
		RetMsg = retMsg;
	}
}
