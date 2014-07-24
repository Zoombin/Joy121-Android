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

	public class PopularGoods {
		private String goods_id;
		// 积分
		private String cost_integral;
		private String goods_name;
		private String name;
		private String shop_price;
		private String market_price;
		private String goods_img;
		private String goods_thumb;
		private String thumb;
		private String bought_count;

		public String getGoods_id() {
			return goods_id;
		}

		public void setGoods_id(String goods_id) {
			this.goods_id = goods_id;
		}

		public String getCost_integral() {
			return cost_integral;
		}

		public void setCost_integral(String cost_integral) {
			this.cost_integral = cost_integral;
		}

		public String getGoods_name() {
			return goods_name;
		}

		public void setGoods_name(String goods_name) {
			this.goods_name = goods_name;
		}

		public String getShop_price() {
			return shop_price;
		}

		public void setShop_price(String shop_price) {
			this.shop_price = shop_price;
		}

		public String getMarket_price() {
			return market_price;
		}

		public void setMarket_price(String market_price) {
			this.market_price = market_price;
		}

		public String getGoods_img() {
			return goods_img;
		}

		public void setGoods_img(String goods_img) {
			this.goods_img = goods_img;
		}

		public String getGoods_thumb() {
			return goods_thumb;
		}

		public void setGoods_thumb(String goods_thumb) {
			this.goods_thumb = goods_thumb;
		}

		public String getBought_count() {
			return bought_count;
		}

		public void setBought_count(String bought_count) {
			this.bought_count = bought_count;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getThumb() {
			return thumb;
		}

		public void setThumb(String thumb) {
			this.thumb = thumb;
		}
	}

	public int getMax_page() {
		return max_page;
	}

	public void setMax_page(int max_page) {
		this.max_page = max_page;
	}
}
