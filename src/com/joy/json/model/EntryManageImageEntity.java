package com.joy.json.model;

public class EntryManageImageEntity extends TResult{
	/*
	 * rainbow  入职管理中证件图片
	 */
	private static final long serialVersionUID = 1L;
    private String Certificates;//证件照
    private String Video;//我的视频
    private String LearningCertificate;//学习证书
    private String IDImage ;//身份证
	private String Retirement;//退工单
    private String Physical;//体检报告
    private String BankCard;//银行卡
    public String getBankCard() {
		return BankCard;
	}
	public void setBankCard(String bankCard) {
		BankCard = bankCard;
	}
	public String getIDImage() {
		return IDImage;
	}
	public void setIDImage(String iDImage) {
		IDImage = iDImage;
	}
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
