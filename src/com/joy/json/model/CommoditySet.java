package com.joy.json.model;

import java.util.List;

/**
 * 福利套餐
 * 
 * @author daiye
 * 
 */
public class CommoditySet extends TResult {

	private static final long serialVersionUID = 1L;
	// [{"Picture":"set 003.jpg","MarketPrice":60,"Description":"坚果炒货礼盒一号","Flag":"1","CommSetOrderSx":0,"IsDefault":"0","TypeName":"端午节","Points":50,"Commodities":null,
	// "SetType":"15","AppPicture":"","ExpireDate":"\/Date(-62135596800000+0800)\/","StartDate":"\/Date(1393603200000+0800)\/","EXPIREDDATE":"\/Date(1398873600000+0800)\/",
	// "CreateTime":"\/Date(1395817450000+0800)\/","SetNo":"delphi_03","SetName":"坚果炒货端午节礼盒一号","AppDescription":null,"Id":18},
	// {"Picture":"delphi_02.png","MarketPrice":100,"Description":"生态草鸡蛋50枚","Flag":"1","CommSetOrderSx":0,"IsDefault":"0","TypeName":"端午节","Points":80,"Commodities":null,"SetType":"15","AppPicture":"","ExpireDate":"\/Date(-62135596800000+0800)\/","StartDate":"\/Date(1393603200000+0800)\/","EXPIREDDATE":"\/Date(1398873600000+0800)\/","CreateTime":"\/Date(1392282955000+0800)\/","SetNo":"delphi_02","SetName":"生态草鸡蛋端午节二号礼盒","AppDescription":null,"Id":17},{"Picture":"delphi_01.png","MarketPrice":100,"Description":"有机绿色蔬菜礼盒
	// 3kg<br
	// \/>","Flag":"1","CommSetOrderSx":0,"IsDefault":"0","TypeName":"端午节","Points":60,"Commodities":null,"SetType":"15","AppPicture":"","ExpireDate":"\/Date(-62135596800000+0800)\/","StartDate":"\/Date(1393603200000+0800)\/","EXPIREDDATE":"\/Date(1398873600000+0800)\/","CreateTime":"\/Date(1392281517000+0800)\/","SetNo":"delphi_01","SetName":"有机蔬菜端午节一号礼盒","AppDescription":null,"Id":16},{"Picture":"delphi_04.jpg","MarketPrice":200,"Description":"员工周年福利一号礼盒","Flag":"1","CommSetOrderSx":0,"IsDefault":"0","TypeName":"员工周年","Points":180,"Commodities":null,"SetType":"22","AppPicture":"","ExpireDate":"\/Date(-62135596800000+0800)\/","StartDate":"\/Date(1393603200000+0800)\/","EXPIREDDATE":"\/Date(1398787200000+0800)\/","CreateTime":"\/Date(1395829051000+0800)\/","SetNo":"delphi_04","SetName":"员工周年福利一号礼盒","AppDescription":null,"Id":19},{"Picture":"delphi_05.jpg","MarketPrice":220,"Description":"员工周年福利二号礼盒","Flag":"1","CommSetOrderSx":0,"IsDefault":"0","TypeName":"员工周年","Points":180,"Commodities":null,"SetType":"22","AppPicture":"","ExpireDate":"\/Date(-62135596800000+0800)\/","StartDate":"\/Date(1393603200000+0800)\/","EXPIREDDATE":"\/Date(1398787200000+0800)\/","CreateTime":"\/Date(1395829221000+0800)\/","SetNo":"delphi_05","SetName":"员工周年福利二号礼盒","AppDescription":null,"Id":20}]
	private int Id;

	private String SetType;

	private String SetNo;

	private String SetName;

	private String Picture;

	private String Description;

	private String AppDescription;

	private int Points;

	private int MarketPrice;

	private String Flag;

	private String CreateTime;

	private String IsDefault;

	private int CommSetOrderSx;

	private String ExpireDate;

	private List<Commodities> Commodities;

	private String TypeName;

	private String AppPicture;

	private String StartDate;

	private String EXPIREDDATE;
	
	private String ProductName;
	private String Amount;
	private String Property;
	private String productPicture;
	private String OrderId;

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getAppDescription() {
		return AppDescription;
	}

	public void setAppDescription(String appDescription) {
		AppDescription = appDescription;
	}

	public String getIsDefault() {
		return IsDefault;
	}

	public void setIsDefault(String isDefault) {
		IsDefault = isDefault;
	}

	public String getExpireDate() {
		return ExpireDate;
	}

	public void setExpireDate(String expireDate) {
		ExpireDate = expireDate;
	}

	public String getTypeName() {
		return TypeName;
	}

	public void setTypeName(String typeName) {
		TypeName = typeName;
	}

	public String getAppPicture() {
		return AppPicture;
	}

	public void setAppPicture(String appPicture) {
		AppPicture = appPicture;
	}

	public String getStartDate() {
		return StartDate;
	}

	public void setStartDate(String startDate) {
		StartDate = startDate;
	}

	public String getEXPIREDDATE() {
		return EXPIREDDATE;
	}

	public void setEXPIREDDATE(String eXPIREDDATE) {
		EXPIREDDATE = eXPIREDDATE;
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

	public int getCommSetOrderSx() {
		return CommSetOrderSx;
	}

	public void setCommSetOrderSx(int commSetOrderSx) {
		CommSetOrderSx = commSetOrderSx;
	}

	public List<Commodities> getCommodities() {
		return Commodities;
	}

	public void setCommodities(List<Commodities> commodities) {
		Commodities = commodities;
	}

	public String getProductName() {
		return ProductName;
	}

	public void setProductName(String productName) {
		ProductName = productName;
	}

	public String getAmount() {
		return Amount;
	}

	public void setAmount(String amount) {
		Amount = amount;
	}

	public String getProperty() {
		return Property;
	}

	public void setProperty(String property) {
		Property = property;
	}

	public String getProductPicture() {
		return productPicture;
	}

	public void setProductPicture(String productPicture) {
		this.productPicture = productPicture;
	}

	public String getOrderId() {
		return OrderId;
	}

	public void setOrderId(String orderId) {
		OrderId = orderId;
	}
	
	

}
