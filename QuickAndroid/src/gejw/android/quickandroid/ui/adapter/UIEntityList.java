package gejw.android.quickandroid.ui.adapter;

import java.util.List;

public class UIEntityList {

	private List<UIEntity> list;

	public List<UIEntity> getList() {
		return list;
	}

	public void setList(List<UIEntity> list) {
		this.list = list;
	}

	public class UIEntity {
		// Viewid
		private String viewKey;
		// 宽度
		private float viewWidth;
		// 高度
		private float viewHeight;
		// 比重
		private int viewWeight;
		// 外间距
		private float MarginL;
		private float MarginT;
		private float MarginR;
		private float MarginB;

		// 内间距
		private int PaddingL;
		private int PaddingT;
		private int PaddingR;
		private int PaddingB;
		
		//字体大小
		private int textSize;
		
		private boolean isClick;
		
		private String img;

		public String getViewKey() {
			return viewKey;
		}

		public void setViewKey(String viewKey) {
			this.viewKey = viewKey;
		}

		public float getViewWidth() {
			return viewWidth;
		}

		public void setViewWidth(float viewWidth) {
			this.viewWidth = viewWidth;
		}

		public float getViewHeight() {
			return viewHeight;
		}

		public void setViewHeight(float viewHeight) {
			this.viewHeight = viewHeight;
		}

		public int getViewWeight() {
			return viewWeight;
		}

		public void setViewWeight(int viewWeight) {
			this.viewWeight = viewWeight;
		}

		public float getMarginL() {
			return MarginL;
		}

		public void setMarginL(float marginL) {
			MarginL = marginL;
		}

		public float getMarginT() {
			return MarginT;
		}

		public void setMarginT(float marginT) {
			MarginT = marginT;
		}

		public float getMarginR() {
			return MarginR;
		}

		public void setMarginR(float marginR) {
			MarginR = marginR;
		}

		public float getMarginB() {
			return MarginB;
		}

		public void setMarginB(float marginB) {
			MarginB = marginB;
		}


		public int getPaddingL() {
			return PaddingL;
		}

		public void setPaddingL(int paddingL) {
			PaddingL = paddingL;
		}

		public int getPaddingT() {
			return PaddingT;
		}

		public void setPaddingT(int paddingT) {
			PaddingT = paddingT;
		}

		public int getPaddingR() {
			return PaddingR;
		}

		public void setPaddingR(int paddingR) {
			PaddingR = paddingR;
		}

		public int getPaddingB() {
			return PaddingB;
		}

		public void setPaddingB(int paddingB) {
			PaddingB = paddingB;
		}

		public int getTextSize() {
			return textSize;
		}

		public void setTextSize(int textSize) {
			this.textSize = textSize;
		}

		public boolean isClick() {
			return isClick;
		}

		public void setClick(boolean isClick) {
			this.isClick = isClick;
		}

		public String getImg() {
			return img;
		}

		public void setImg(String img) {
			this.img = img;
		}

	}
}
