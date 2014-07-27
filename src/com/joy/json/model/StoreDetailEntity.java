package com.joy.json.model;

import java.util.List;

public class StoreDetailEntity extends TResult {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	int flag;
	String msg;
	List<StoreDetail> retobj;

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public List<StoreDetail> getRetobj() {
		return retobj;
	}

	public void setRetobj(List<StoreDetail> retobj) {
		this.retobj = retobj;
	}
	
	public class StoreDetail{
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

}
