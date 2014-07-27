package com.joy.json.model;

import java.io.Serializable;

public class PopularGoodsEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private PopularGoods[] data;
	private int max_page;

	public PopularGoods[] getData() {
		return data;
	}

	public void setData(PopularGoods[] data) {
		this.data = data;
	}
	public int getMax_page() {
		return max_page;
	}

	public void setMax_page(int max_page) {
		this.max_page = max_page;
	}
}
