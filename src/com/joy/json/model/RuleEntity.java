package com.joy.json.model;
/**
 * 规章制度信息
 * @author ryan zhou 2014-12-31
 *
 */

public class RuleEntity extends TResult {
	private static final long serialVersionUID = 1L;
	
	String Id;
	String TypeId;
	String Title;
	String Content;
	String PublishDate;
	String NewsUrl;
	String Level;
	String Pic;
	String ActtachmentUrl;
	String Company;
	
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getId() {
		return Id;
	}
	public void setId(String id) {
		Id = id;
	}
	public String getTypeId() {
		return TypeId;
	}
	public void setTypeId(String typeId) {
		TypeId = typeId;
	}
	public String getTitle() {
		return Title;
	}
	public void setTitle(String title) {
		Title = title;
	}
	public String getContent() {
		return Content;
	}
	public void setContent(String content) {
		Content = content;
	}
	public String getPublishDate() {
		return PublishDate;
	}
	public void setPublishDate(String publishDate) {
		PublishDate = publishDate;
	}
	public String getNewsUrl() {
		return NewsUrl;
	}
	public void setNewsUrl(String newsUrl) {
		NewsUrl = newsUrl;
	}
	public String getLevel() {
		return Level;
	}
	public void setLevel(String level) {
		Level = level;
	}
	public String getPic() {
		return Pic;
	}
	public void setPic(String pic) {
		Pic = pic;
	}
	public String getActtachmentUrl() {
		return ActtachmentUrl;
	}
	public void setActtachmentUrl(String acttachmentUrl) {
		ActtachmentUrl = acttachmentUrl;
	}
	public String getCompany() {
		return Company;
	}
	public void setCompany(String company) {
		Company = company;
	}
}