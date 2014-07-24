package com.joy.json.model;

public class CategoriesGoodsEntity {
	private CategoriesData[] data;

	public CategoriesData[] getData() {
		return data;
	}

	public void setData(CategoriesData[] data) {
		this.data = data;
	}

	public class CategoriesData {
		private String category_id;
		private String category_url;
		private String category_name;
		private String category_color;
		private CategoriesGood[] category_goods;

		public String getCategory_id() {
			return category_id;
		}

		public void setCategory_id(String category_id) {
			this.category_id = category_id;
		}

		public String getCategory_url() {
			return category_url;
		}

		public void setCategory_url(String category_url) {
			this.category_url = category_url;
		}

		public String getCategory_name() {
			return category_name;
		}

		public void setCategory_name(String category_name) {
			this.category_name = category_name;
		}

		public CategoriesGood[] getCategory_goods() {
			return category_goods;
		}

		public void setCategory_goods(CategoriesGood[] category_goods) {
			this.category_goods = category_goods;
		}

		public String getCategory_color() {
			return category_color.startsWith("#") ? category_color : "#"
					+ category_color;
		}

		public void setCategory_color(String category_color) {
			this.category_color = category_color;
		}
	}

	public class CategoriesGood {
		private String promote_price;
		private String id;
		private String goods_id;
		private String name;
		private String brief;
		private String cost_integral;
		private String limit_number;
		private String brand_name;
		private String goods_style_name;
		private String short_name;
		private String short_style_name;
		private String market_price;
		private String shop_price;
		private String shop_price_format;
		private String thumb;
		private String goods_img;
		private String url;
		private String goods_name;
		private String goods_thumb;

		public String getPromote_price() {
			return promote_price;
		}

		public void setPromote_price(String promote_price) {
			this.promote_price = promote_price;
		}

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getGoods_id() {
			return goods_id;
		}

		public void setGoods_id(String goods_id) {
			this.goods_id = goods_id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getBrief() {
			return brief;
		}

		public void setBrief(String brief) {
			this.brief = brief;
		}

		public String getCost_integral() {
			return cost_integral;
		}

		public void setCost_integral(String cost_integral) {
			this.cost_integral = cost_integral;
		}

		public String getLimit_number() {
			return limit_number;
		}

		public void setLimit_number(String limit_number) {
			this.limit_number = limit_number;
		}

		public String getBrand_name() {
			return brand_name;
		}

		public void setBrand_name(String brand_name) {
			this.brand_name = brand_name;
		}

		public String getGoods_style_name() {
			return goods_style_name;
		}

		public void setGoods_style_name(String goods_style_name) {
			this.goods_style_name = goods_style_name;
		}

		public String getShort_name() {
			return short_name;
		}

		public void setShort_name(String short_name) {
			this.short_name = short_name;
		}

		public String getShort_style_name() {
			return short_style_name;
		}

		public void setShort_style_name(String short_style_name) {
			this.short_style_name = short_style_name;
		}

		public String getMarket_price() {
			return market_price;
		}

		public void setMarket_price(String market_price) {
			this.market_price = market_price;
		}

		public String getShop_price() {
			return shop_price;
		}

		public void setShop_price(String shop_price) {
			this.shop_price = shop_price;
		}

		public String getShop_price_format() {
			return shop_price_format;
		}

		public void setShop_price_format(String shop_price_format) {
			this.shop_price_format = shop_price_format;
		}

		public String getThumb() {
			return thumb;
		}

		public void setThumb(String thumb) {
			this.thumb = thumb;
		}

		public String getGoods_img() {
			return goods_img;
		}

		public void setGoods_img(String goods_img) {
			this.goods_img = goods_img;
		}

		public String getUrl() {
			return url;
		}

		public void setUrl(String url) {
			this.url = url;
		}

		public String getGoods_name() {
			return goods_name;
		}

		public void setGoods_name(String goods_name) {
			this.goods_name = goods_name;
		}

		public String getGoods_thumb() {
			return goods_thumb;
		}

		public void setGoods_thumb(String goods_thumb) {
			this.goods_thumb = goods_thumb;
		}
	}
}