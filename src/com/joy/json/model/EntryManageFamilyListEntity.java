package com.joy.json.model;

import java.util.List;

public class EntryManageFamilyListEntity extends TResult{
	private static final long serialVersionUID = 1L;
	private List<EntryManageFamilyInfoEntity> Relatives;
	public List<EntryManageFamilyInfoEntity> getRelatives() {
		return Relatives;
	}
	public void setRelatives(List<EntryManageFamilyInfoEntity> relatives) {
		Relatives = relatives;
	}

}
