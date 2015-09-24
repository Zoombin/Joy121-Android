package com.joy.json.model;

import java.util.List;

public class EntryManageMaterialsInfoEntity extends TResult{
	/*
	 * rainbow  证件信息
	 */
	private static final long serialVersionUID = 1L; 
	private String Certificates;
	private String Video;
	private String LearningCertificate;
	private List<EntryManageIDImageListEntity> IDImage;
	private String Retirement;
	private String Physical;
	public String getCertificates() {
		return Certificates;
	}
	public void setCertificates(String certificates) {
		Certificates = certificates;
	}
	public String getVideo() {
		return Video;
	}
	public void setVideo(String video) {
		Video = video;
	}
	public String getLearningCertificate() {
		return LearningCertificate;
	}
	public void setLearningCertificate(String learningCertificate) {
		LearningCertificate = learningCertificate;
	}
	public List<EntryManageIDImageListEntity> getIDImage() {
		return IDImage;
	}
	public void setIDImage(List<EntryManageIDImageListEntity> iDImage) {
		IDImage = iDImage;
	}
	public String getRetirement() {
		return Retirement;
	}
	public void setRetirement(String retirement) {
		Retirement = retirement;
	}
	public String getPhysical() {
		return Physical;
	}
	public void setPhysical(String physical) {
		Physical = physical;
	}
	

}
