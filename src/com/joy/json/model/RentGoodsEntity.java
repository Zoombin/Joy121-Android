package com.joy.json.model;

/**
 * 积分信息
 * @author ryan zhou 2014-11-25
 *
 */

public class RentGoodsEntity extends TResult {
	private static final long serialVersionUID = 1L;
	
	private String id;
	private String Name;
	private String CurrentNum;
	private String totalNum;
	private String subCategory;
	private String Pictures;
	private String Model;
	private String state;
	private String supplierId;
	private String companyId;
	private String categoryType;
	
	


	public String getId() {
		return id;
	}




	public void setId(String id) {
		this.id = id;
	}




	public String getName() {
		return Name;
	}




	public void setName(String Name) {
		this.Name = Name;
	}




	public String getCurrentNum() {
		return CurrentNum;
	}




	public void setCurrentNum(String CurrentNum) {
		this.CurrentNum = CurrentNum;
	}




	public String getTotalNum() {
		return totalNum;
	}




	public void setTotalNum(String totalNum) {
		this.totalNum = totalNum;
	}




	public String getSubCategory() {
		return subCategory;
	}




	public void setSubCategory(String subCategory) {
		this.subCategory = subCategory;
	}




	public String getPictures() {
		return Pictures;
	}




	public void setPictures(String Pictures) {
		this.Pictures = Pictures;
	}




	public String getModel() {
		return Model;
	}




	public void setModel(String Model) {
		this.Model = Model;
	}




	public String getState() {
		return state;
	}




	public void setState(String state) {
		this.state = state;
	}




	public String getSupplierId() {
		return supplierId;
	}




	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId;
	}




	public String getCompanyId() {
		return companyId;
	}




	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}




	public String getCategoryType() {
		return categoryType;
	}




	public void setCategoryType(String categoryType) {
		this.categoryType = categoryType;
	}




	public static long getSerialversionuid() {
		return serialVersionUID;
	}




	public RentGoodsEntity() {
		// TODO Auto-generated constructor stub
	}

}
