package com.joy.Activity;

import gejw.android.quickandroid.QActivity;
import gejw.android.quickandroid.log.PLog;
import gejw.android.quickandroid.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import android.app.ActionBar.LayoutParams;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.joy.R;
import com.joy.Utils.Constants;
import com.joy.json.JsonCommon;
import com.joy.json.JsonCommon.OnOperationListener;
import com.joy.json.model.CategoriesGoods;
import com.joy.json.model.CategoriesStore;
import com.joy.json.model.CategoriesStoreEntity;
import com.joy.json.model.CategoryEntity;
import com.joy.json.operation.OperationBuilder;
import com.joy.json.operation.impl.CategoryStoreOp;
import com.nostra13.universalimageloader.core.ImageLoader;

public class CategorieStoreActivity extends QActivity {
	private RelativeLayout layout_title;
	private TextView tv_title;
	private ViewPager picViewPager;
	private LinearLayout colorLayout,sizeLayout;
	private CategoriesGoods goods;
	private List<CategoriesStore> templist;
	private List<String> tempColor;
	private List<String> tempSize;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_goods);

		Intent intent = getIntent();
		if (intent.hasExtra("detail")) {
			goods = (CategoriesGoods) intent.getSerializableExtra("detail");
			Log.e("LSD", goods.getAppPicture());
		}
		
		
		initViews();
		initViewPager();
		if(goods != null){
			getCategorieStore(goods.getId());
		}
	}

	private void initViews() {
		layout_title = (RelativeLayout) findViewById(R.id.layout_title);
		uiAdapter.setMargin(layout_title, LayoutParams.MATCH_PARENT, Constants.TitleHeight, 0, 0,
				0, 0);

		tv_title = (TextView) findViewById(R.id.tv_title);
		uiAdapter.setTextSize(tv_title, Constants.TitleSize);
		picViewPager = (ViewPager) findViewById(R.id.pic_viewpager);
		
		colorLayout = (LinearLayout) findViewById(R.id.color_layout);
		sizeLayout = (LinearLayout) findViewById(R.id.size_layout);
	}

	private void initViewPager() {
		if (goods != null) {
			ViewPagerAdapter adapter = new ViewPagerAdapter();
			picViewPager.setAdapter(adapter);
			String pics = goods.getAppPicture();
			if (pics.contains(";")) {
				String[] picUrls = pics.split(";");
				int len = picUrls.length;
				if (len > 0) {
					List<View> views = new ArrayList<View>();
					for (int i = 0; i < len; i++) {
						View view = LayoutInflater.from(CategorieStoreActivity.this).inflate(R.layout.goods_item_image, null);
						view.setTag(picUrls[i]);
						views.add(view);
					}
					adapter.setViews(views);
				}
			}
		}
	}
	
	
	private void getCategorieStore(int commodityid) {
		CategoriesStoreEntity storeE = new CategoriesStoreEntity();
		OperationBuilder builder = new OperationBuilder().append(new CategoryStoreOp(commodityid),
				storeE);
		OnOperationListener listener = new OnOperationListener() {
			@Override
			public void onOperationFinished(List<Object> resList) {
				if (self.isFinishing()) {
					return;
				}
				if (resList == null) {
					//Toast.show(self, "连接超时");
					return;
				}
				CategoriesStoreEntity entity = (CategoriesStoreEntity) resList.get(0);
				List<CategoriesStore> storelist = entity.getRetobj();
				if (storelist == null) {
					//Toast.show(self, "没有分类信息！");
					return;
				}
				//PLog.e("返回结果--->%s", storelist.size());
				setData(storelist);
			}
			@Override
			public void onOperationError(Exception e) {
				e.printStackTrace();
			}
		};

		JsonCommon task = new JsonCommon(self, builder, listener,
				JsonCommon.PROGRESSQUERY);
		task.execute();
	}
	
	private void setData(List<CategoriesStore> storelist){
		if(storelist != null && storelist.size()>0){
			//查找存在的颜色和尺寸
			for(CategoriesStore store :storelist){
				String CAndS = store.getPropertyValues();
				String[] sp = CAndS.split(";");
				if(sp.length == 2){
					addColorAndSize(sp[0], sp[1]);
					store.setColor(sp[0]);
					store.setSize(sp[1]);
					templist.add(store);
				}
			}
			
			
			
		}
	}
	private void addColorAndSize(String color,String size){
		if(!TextUtils.isEmpty(color)){
			boolean colorExist = false;
			for(String str : tempColor){
				if(str.equals(color)){
					colorExist = true;
				}
			}
			if(!colorExist){
				tempColor.add(color);
			}
		}
		if(!TextUtils.isEmpty(size)){
			boolean sizeExist = false;
			for(String str : tempSize){
				if(str.equals(color)){
					sizeExist = true;
				}
			}
			if(!sizeExist){
				tempSize.add(color);
			}
		}
	}
	
	private void setColorAndSzieToView(){
		
	}
	

	public class ViewPagerAdapter extends PagerAdapter {
		// 界面列表
		private List<View> views;

		public void setViews(List<View> views2) {
			this.views = views2;
			this.notifyDataSetChanged();
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			if (views != null) {
				return views.size();
			}
			return 0;
		}

		/**
		 * 判断是否由对象生成界面
		 */
		@Override
		public boolean isViewFromObject(View view, Object object) {
			// TODO Auto-generated method stub
			return view == object;
		}

		/**
		 * 初始化position位置的界面
		 */
		@Override
		public Object instantiateItem(View container, int position) {
			View view = views.get(position);
			ImageView imageView = (ImageView) view.findViewById(R.id.image);
			String imgUrl = (String) view.getTag();
			if(!TextUtils.isEmpty(imgUrl)){
				ImageLoader.getInstance().displayImage(Constants.IMGURL+imgUrl, imageView);
			}
			((ViewPager) container).addView(view, 0);
			return views.get(position);
		}

		/**
		 * 销毁position位置的界面
		 */
		@Override
		public void destroyItem(View container, int position, Object object) {
			((ViewPager) container).removeView(views.get(position));
		}
	}
}
