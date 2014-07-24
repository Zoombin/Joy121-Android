package com.joy.json.model;

import java.util.List;

public class CategoryEntity extends TResult{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String CategoryName;
	private String ID;
	private List<CategoryEntity> retobj;
	
	public String getCategoryName() {
		return CategoryName;
	}
	public List<CategoryEntity> getRetobj() {
		return retobj;
	}
	public void setRetobj(List<CategoryEntity> retobj) {
		this.retobj = retobj;
	}
	public void setCategoryName(String categoryName) {
		CategoryName = categoryName;
	}
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	
	
}
