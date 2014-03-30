package com.joy.json.model;

/**
 * 福利套餐
 * @author daiye
 *
 */
public class CommoditySet extends TResult {
	
	private static final long serialVersionUID = 1L;
//	{"flag":1,"msg":null,"retobj":[{"Id":18,"SetType":"15","SetNo":"delphi_03","SetName":"坚果炒货端午节礼盒一号","Picture":"set 003.jpg","Description":"坚果炒货礼盒一号",
//		"Points":50,"MarketPrice":60,"Flag":"1","CreateTime":"\/Date(1395817450000+0800)\/","IsDefault":null,"CommSetOrderSx":0,
//		"EXPIREDDATE":"\/Date(1398873600000+0800)\/","Commodities":null},{"Id":17,"SetType":"15","SetNo":"delphi_02","SetName":"生态草鸡蛋端午节二号礼盒",
//			"Picture":"delphi_02.png","Description":"生态草鸡蛋50枚","Points":80,"MarketPrice":100,"Flag":"1","CreateTime":"\/Date(1392282955000+0800)\/",
//			"IsDefault":null,"CommSetOrderSx":0,"EXPIREDDATE":"\/Date(1398873600000+0800)\/","Commodities":null},{"Id":16,"SetType":"15","SetNo":"delphi_01",
//				"SetName":"有机蔬菜端午节一号礼盒","Picture":"delphi_01.png","Description":"有机绿色蔬菜礼盒 3kg<br />","Points":60,"MarketPrice":100,"Flag":"1",
//				"CreateTime":"\/Date(1392281517000+0800)\/","IsDefault":null,"CommSetOrderSx":0,"EXPIREDDATE":"\/Date(1398873600000+0800)\/","Commodities":null},
//				{"Id":19,"SetType":"22","SetNo":"delphi_04","SetName":"员工周年福利一号礼盒","Picture":"delphi_04.jpg","Description":"员工周年福利一号礼盒","Points":180,"MarketPrice":200,"Flag":"1","CreateTime":"\/Date(1395829051000+0800)\/","IsDefault":null,"CommSetOrderSx":0,"EXPIREDDATE":"\/Date(1398787200000+0800)\/","Commodities":null},{"Id":20,"SetType":"22","SetNo":"delphi_05","SetName":"员工周年福利二号礼盒","Picture":"delphi_05.jpg","Description":"员工周年福利二号礼盒","Points":180,"MarketPrice":220,"Flag":"1","CreateTime":"\/Date(1395829221000+0800)\/","IsDefault":null,"CommSetOrderSx":0,"EXPIREDDATE":"\/Date(1398787200000+0800)\/","Commodities":null}]}
	
	private int id;
	
	private String SetType;
	
	private String SetNo;
	
	private String SetName;
	
	private String Picture;
	
	private String Description;
	
	private int Points;
	
	private int MarketPrice;
	
	private String Flag;
	
	private String CreateTime;
	
	private boolean IsDefault;
	
	private int CommSetOrderSx;
	
	private String EXPIREDDATE;
	
	private String Commodities;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSetType() {
		return SetType;
	}

	public void setSetType(String setType) {
		SetType = setType;
	}

	public String getSetNo() {
		return SetNo;
	}

	public void setSetNo(String setNo) {
		SetNo = setNo;
	}

	public String getSetName() {
		return SetName;
	}

	public void setSetName(String setName) {
		SetName = setName;
	}

	public String getPicture() {
		return Picture;
	}

	public void setPicture(String picture) {
		Picture = picture;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public int getPoints() {
		return Points;
	}

	public void setPoints(int points) {
		Points = points;
	}

	public int getMarketPrice() {
		return MarketPrice;
	}

	public void setMarketPrice(int marketPrice) {
		MarketPrice = marketPrice;
	}

	public String getFlag() {
		return Flag;
	}

	public void setFlag(String flag) {
		Flag = flag;
	}

	public String getCreateTime() {
		return CreateTime;
	}

	public void setCreateTime(String createTime) {
		CreateTime = createTime;
	}

	public boolean isIsDefault() {
		return IsDefault;
	}

	public void setIsDefault(boolean isDefault) {
		IsDefault = isDefault;
	}

	public int getCommSetOrderSx() {
		return CommSetOrderSx;
	}

	public void setCommSetOrderSx(int commSetOrderSx) {
		CommSetOrderSx = commSetOrderSx;
	}

	public String getEXPIREDDATE() {
		return EXPIREDDATE;
	}

	public void setEXPIREDDATE(String eXPIREDDATE) {
		EXPIREDDATE = eXPIREDDATE;
	}

	public String getCommodities() {
		return Commodities;
	}

	public void setCommodities(String commodities) {
		Commodities = commodities;
	}
}
