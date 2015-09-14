package com.joy.json.model;

import java.util.List;

public class EntryManageExperiencesListEntity extends TResult{
	private static final long serialVersionUID = 1L;

	private List<EntryManageEducationInfoEntity> Learning;//学习经历
	private List<EntryManageWorkExperienceInfoEntity> Job;//工作经验

	public List<EntryManageEducationInfoEntity> getLearning() {
		return Learning;
	}

	public void setLearning(List<EntryManageEducationInfoEntity> learning) {
		Learning = learning;
	}

	public List<EntryManageWorkExperienceInfoEntity> getJob() {
		return Job;
	}

	public void setJob(List<EntryManageWorkExperienceInfoEntity> job) {
		Job = job;
	}
	
}
