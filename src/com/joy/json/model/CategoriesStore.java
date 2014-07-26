package com.joy.json.model;

public class CategoriesStore extends TResult {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int CommodityId;
	String PropertyValues;
	int Amount;
	int StatusFlag;
	String color;
	String size;

	public int getCommodityId() {
		return CommodityId;
	}

	public void setCommodityId(int commodityId) {
		CommodityId = commodityId;
	}

	public String getPropertyValues() {
		return PropertyValues;
	}

	public void setPropertyValues(String propertyValues) {
		PropertyValues = propertyValues;
	}

	public int getAmount() {
		return Amount;
	}

	public void setAmount(int amount) {
		Amount = amount;
	}

	public int getStatusFlag() {
		return StatusFlag;
	}

	public void setStatusFlag(int statusFlag) {
		StatusFlag = statusFlag;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

}
