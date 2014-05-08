package com.joy.json.model;

/**
 * 公告详细信息
 * @author daiye
 *
 */
public class PostDetailEntity extends TResult {
	
	private static final long serialVersionUID = 1L;
	
//	{"retobj":[{"PostTime":"\/Date(-62135596800000+0800)\/",
//		"Content":"<p>五一劳动节放假公告，5.1,5.2,5.3号放假，5.4正常上班<\/p>",
//		"Company":"DELPHI_SZ","PostId":23,"ExpireTime":"\/Date(1401379200000+0800)\/","Title":"五一劳动节放假公告"}
//	,{"PostTime":"\/Date(-62135596800000+0800)\/","Content":"<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 公司决定在1月25号在上海希尔顿酒店举办公司家庭日活动，希望员工带着你的父母孩子一起参加，让我们的大家庭更加祥和喜乐！<\/p>\n<p>易尔益科技<\/p>","Company":"DELPHI_SZ","PostId":12,"ExpireTime":"\/Date(1400515200000+0800)\/","Title":"家庭日活动"}],"msg":null,"flag":1}
	
	private String PostTime;
	
	private String Content;
	
	private String Company;
	
	private int PostId;
	
	private String ExpireTime;
	
	private String Title;

	public String getPostTime() {
		return PostTime;
	}

	public void setPostTime(String postTime) {
		PostTime = postTime;
	}

	public String getContent() {
		return Content;
	}

	public void setContent(String content) {
		Content = content;
	}

	public String getCompany() {
		return Company;
	}

	public void setCompany(String company) {
		Company = company;
	}

	public int getPostId() {
		return PostId;
	}

	public void setPostId(int postId) {
		PostId = postId;
	}

	public String getExpireTime() {
		return ExpireTime;
	}

	public void setExpireTime(String expireTime) {
		ExpireTime = expireTime;
	}

	public String getTitle() {
		return Title;
	}

	public void setTitle(String title) {
		Title = title;
	}
}
