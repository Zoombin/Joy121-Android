package com.joy.json.model;

import java.util.List;

public class EntryManageExperiencesListEntity {
	private static final long serialVersionUID = 1L;

	private List<EntryManageEducationInfoEntity> Learning;//学习经历

	public List<EntryManageEducationInfoEntity> getLearning() {
		return Learning;
	}

	public void setLearning(List<EntryManageEducationInfoEntity> learning) {
		Learning = learning;
	}
	
}
