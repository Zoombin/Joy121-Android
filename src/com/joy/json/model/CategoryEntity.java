package com.joy.json.model;

import java.util.List;

import com.joy.json.model.CategoriesGoodsDEntity.CategoriesGoods;

public class CategoryEntity extends TResult{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String CategoryName;
	private String Id;
	private String categorytype;
	private String CompAppSetting;
	private List<CategoryEntity> retobj;
	private List<CategoriesGoods> goodsList;//类别下的商品列表
	
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
	public String getId() {
		return Id;
	}
	public void setId(String id) {
		Id = id;
	}
	public List<CategoriesGoods> getGoodsList() {
		return goodsList;
	}
	public void setGoodsList(List<CategoriesGoods> goodsList) {
		this.goodsList = goodsList;
	}
	public String getCategorytype() {
		return categorytype;
	}
	public void setCategorytype(String categorytype) {
		this.categorytype = categorytype;
	}
	public String getCompAppSetting() {
		return CompAppSetting;
	}
	public void setCompAppSetting(String compAppSetting) {
		CompAppSetting = compAppSetting;
	}
}
