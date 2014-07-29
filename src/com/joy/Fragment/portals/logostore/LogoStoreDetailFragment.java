package com.joy.Fragment.portals.logostore;

import gejw.android.quickandroid.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import android.app.ActionBar.LayoutParams;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.joy.R;
import com.joy.Activity.MainActivity;
import com.joy.Fragment.BaseFragment;
import com.joy.Utils.Constants;
import com.joy.json.JsonCommon;
import com.joy.json.JsonCommon.OnOperationListener;
import com.joy.json.model.CategoriesGoodsDEntity.CategoriesGoods;
import com.joy.json.model.GoodsDetail;
import com.joy.json.model.SelectionModel;
import com.joy.json.model.StoreDetailEntity;
import com.joy.json.model.StoreDetailEntity.StoreDetail;
import com.joy.json.operation.OperationBuilder;
import com.joy.json.operation.impl.CategoryStoreOp;
import com.nostra13.universalimageloader.core.ImageLoader;

/****
 * 商店详情
 * @author lsd
 *
 */
public class LogoStoreDetailFragment extends BaseFragment {

	private RelativeLayout layout_title;
	private TextView addToStore, storeNum;
	private TextView tv_ret, tv_title;
	private ViewPager picViewPager;
	private LinearLayout colorLayout, sizeLayout;
	private CategoriesGoods goods;
	private List<StoreDetail> templist;
	private List<SelectionModel> tempColor;
	private List<SelectionModel> tempSize;
	private String colorSelect;
	private String sizeSelect;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		templist = new ArrayList<StoreDetail>();
		tempColor = new ArrayList<SelectionModel>();
		tempSize = new ArrayList<SelectionModel>();
		
		goods = (CategoriesGoods) getArguments().getSerializable("detail");
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View v = inflater.inflate(R.layout.activity_goods, container, false);
		initViews(v);
		return v;
	}

	private void initViews(View v) {
		layout_title = (RelativeLayout) v.findViewById(R.id.layout_title);
		uiAdapter.setMargin(layout_title, LayoutParams.MATCH_PARENT, Constants.TitleHeight, 0, 0, 0, 0);
		tv_title = (TextView) v.findViewById(R.id.tv_title);
		uiAdapter.setTextSize(tv_title, Constants.TitleSize);
		layout_title = (RelativeLayout) v.findViewById(R.id.layout_title);
		uiAdapter.setMargin(layout_title, LayoutParams.MATCH_PARENT, Constants.TitleHeight, 0, 0, 0, 0);
		tv_ret = (TextView) v.findViewById(R.id.tv_ret);
		tv_ret.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				// finish();
				MainActivity.mActivity.Back();
			}
		});
		uiAdapter.setTextSize(tv_ret, Constants.TitleRetSize);
		uiAdapter.setMargin(tv_ret, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 20, 0, 0, 0);

		storeNum = (TextView) v.findViewById(R.id.store_num);
		addToStore = (TextView) v.findViewById(R.id.add_to_store);
		addToStore.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String numStr = storeNum.getText().toString();
				int num = Integer.parseInt(numStr);
				if (num == 0) {
					Toast.show(mActivity, "库存不足");
					return;
				}

				GoodsDetail detail = new GoodsDetail();
				detail.setGoods_id(goods.getId() + "");
				detail.setGoods_img(goods.getPicture());
				detail.setGoods_name(goods.getComName());
				detail.setColor(colorSelect);
				detail.setSize_cloth(sizeSelect);
				detail.setIsLogoStore(true);
				MainActivity.Add2ShopCar(mActivity, detail, 1);
				Toast.show(mActivity, "商品已加入购物车");
				// StoreDetailActivity.this.finish();
			}
		});
		picViewPager = (ViewPager) v.findViewById(R.id.pic_viewpager);
		colorLayout = (LinearLayout) v.findViewById(R.id.color_layout);
		sizeLayout = (LinearLayout) v.findViewById(R.id.size_layout);
		
		initViewPager();
		if(goods != null){
			if(templist.size() ==0){
				getCategorieStore(goods.getId());
			}else{
				setColorAndSzieToView();
				calculateStore();
			}
		}
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
						View view = LayoutInflater.from(mActivity).inflate(R.layout.store_item_image, null);
						view.setTag(picUrls[i]);
						views.add(view);
					}
					adapter.setViews(views);
				}
			}
		}
	}

	private void getCategorieStore(int commodityid) {
		OperationBuilder builder = new OperationBuilder().append(new CategoryStoreOp(), commodityid);
		OnOperationListener listener = new OnOperationListener() {
			@Override
			public void onOperationFinished(List<Object> resList) {
				if (mActivity.isFinishing()) {
					return;
				}
				if (resList == null) {
					// Toast.show(self, "连接超时");
					return;
				}
				StoreDetailEntity entity = (StoreDetailEntity) resList.get(0);
				List<StoreDetail> storelist = entity.getRetobj();
				if (storelist == null) {
					// Toast.show(self, "没有分类信息！");
					return;
				}
				// PLog.e("返回结果--->%s", storelist.size());
				setData(storelist);
			}

			@Override
			public void onOperationError(Exception e) {
				e.printStackTrace();
			}
		};

		JsonCommon task = new JsonCommon(mActivity, builder, listener, JsonCommon.PROGRESSQUERY);
		task.execute();
	}

	/*****
	 * 设置数据
	 * 
	 * @param storelist
	 */
	private void setData(List<StoreDetail> storelist) {
		if (storelist != null && storelist.size() > 0) {
			// 查找存在的颜色和尺寸
			for (StoreDetail store : storelist) {
				String CAndS = store.getPropertyValues();
				if (CAndS.contains(";")) {
					String[] sp = CAndS.split(";");
					if (sp.length == 2) {
						addColorAndSize(sp[0].substring(sp[0].indexOf(":") + 1), sp[1].substring(sp[1].indexOf(":") + 1));
						store.setColor(sp[0].substring(sp[0].indexOf(":") + 1));
						store.setSize(sp[1].substring(sp[1].indexOf(":") + 1));
					}
					templist.add(store);
				} else {
					if (CAndS.contains("color")) {
						addColorAndSize(CAndS.substring(CAndS.indexOf(":") + 1), "");
						store.setColor(CAndS.substring(CAndS.indexOf(":") + 1));
						store.setSize("");
					} else if (CAndS.contains("size_cloth")) {
						addColorAndSize("", CAndS.substring(CAndS.indexOf(":") + 1));
						store.setColor("");
						store.setSize(CAndS.substring(CAndS.indexOf(":") + 1));
					}
				}
				templist.add(store);
			}
			setColorAndSzieToView();
		}
	}

	/*****
	 * 保存临时的颜色和尺寸
	 * 
	 * @param color
	 * @param size
	 */
	private void addColorAndSize(String color, String size) {
		if (!TextUtils.isEmpty(color)) {
			boolean colorExist = false;
			for (SelectionModel model : tempColor) {
				if (color.equals(model.getName())) {
					colorExist = true;
				}
			}
			if (!colorExist) {
				SelectionModel model = new SelectionModel();
				model.setName(color);
				model.setSelected(false);
				tempColor.add(model);
			}
		}
		if (!TextUtils.isEmpty(size)) {
			boolean sizeExist = false;
			for (SelectionModel model : tempSize) {
				if (size.equals(model.getName())) {
					sizeExist = true;
				}
			}
			if (!sizeExist) {
				SelectionModel model = new SelectionModel();
				model.setName(size);
				model.setSelected(false);
				tempSize.add(model);
			}
		}
	}

	/****
	 * 设置颜色和尺寸
	 */
	private void setColorAndSzieToView() {
		if (tempColor.size() > 0) {
			GridView colorGridView = new GridView(mActivity);
			colorGridView.setNumColumns(tempColor.size());
			colorGridView.setHorizontalSpacing(10);
			colorGridView.setCacheColorHint(Color.parseColor("#00000000"));
			colorGridView.setSelector(new ColorDrawable(Color.TRANSPARENT));
			colorGridView.setVerticalScrollBarEnabled(false);
			colorGridView.setHorizontalFadingEdgeEnabled(false);
			colorGridView.setOnItemClickListener(new OnItemClickListener() {
				@Override
				public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
					// TODO Auto-generated method stub
					GridAdapter adapter = (GridAdapter) parent.getAdapter();
					List<SelectionModel> datas = adapter.getData();
					for (SelectionModel data : datas) {
						data.setSelected(false);
					}
					SelectionModel model = (SelectionModel) adapter.getItem(position);
					model.setSelected(true);
					adapter.notifyDataSetChanged();

					colorSelect = model.getName();

					calculateStore();
				}
			});

			GridAdapter colorAdapter = new GridAdapter();
			colorGridView.setAdapter(colorAdapter);
			colorAdapter.setData(tempColor);
			colorLayout.addView(colorGridView);
		}
		if (tempSize.size() > 0) {
			GridView sizeGridView = new GridView(mActivity);
			sizeGridView.setNumColumns(tempSize.size());
			sizeGridView.setHorizontalSpacing(10);
			sizeGridView.setCacheColorHint(Color.parseColor("#00000000"));
			sizeGridView.setSelector(new ColorDrawable(Color.TRANSPARENT));
			sizeGridView.setVerticalScrollBarEnabled(false);
			sizeGridView.setHorizontalFadingEdgeEnabled(false);
			sizeGridView.setOnItemClickListener(new OnItemClickListener() {
				@Override
				public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
					// TODO Auto-generated method stub
					GridAdapter adapter = (GridAdapter) parent.getAdapter();
					List<SelectionModel> datas = adapter.getData();
					for (SelectionModel data : datas) {
						data.setSelected(false);
					}
					SelectionModel model = (SelectionModel) adapter.getItem(position);
					model.setSelected(true);
					adapter.notifyDataSetChanged();

					sizeSelect = model.getName();

					calculateStore();
				}
			});

			GridAdapter sizeAdapter = new GridAdapter();
			sizeGridView.setAdapter(sizeAdapter);
			sizeAdapter.setData(tempSize);
			sizeLayout.addView(sizeGridView);
		}
	}

	/***
	 * 计算库存
	 */
	private void calculateStore() {
		if (!TextUtils.isEmpty(colorSelect) && !TextUtils.isEmpty(sizeSelect)) {
			for (StoreDetail cStore : templist) {
				if (colorSelect.equals(cStore.getColor()) && sizeSelect.equals(cStore.getSize())) {
					storeNum.setText(cStore.getAmount() + "");
				}
			}
		} else if (!TextUtils.isEmpty(colorSelect) && TextUtils.isEmpty(sizeSelect)) {
			for (StoreDetail cStore : templist) {
				if (colorSelect.equals(cStore.getColor()) && TextUtils.isEmpty(cStore.getSize())) {
					storeNum.setText(cStore.getAmount() + "");
				}
			}
		} else if (TextUtils.isEmpty(colorSelect) && !TextUtils.isEmpty(sizeSelect)) {
			for (StoreDetail cStore : templist) {
				if (TextUtils.isEmpty(cStore.getColor()) && sizeSelect.equals(cStore.getSize())) {
					storeNum.setText(cStore.getAmount() + "");
				}
			}
		}
	}

	/***
	 * 颜色、尺寸的适配器
	 * 
	 * @author ADMIN
	 * 
	 */
	public class GridAdapter extends BaseAdapter {
		List<SelectionModel> datas;

		public void setData(List<SelectionModel> datas) {
			this.datas = datas;
			this.notifyDataSetChanged();
		}

		public List<SelectionModel> getData() {
			return datas == null ? null : datas;
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return datas == null ? 0 : datas.size();
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return datas == null ? null : datas.get(position);
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return datas == null ? 0 : position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			Tag tag;
			if (convertView == null) {
				tag = new Tag();
				convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.store_item_txt, null);
				tag.txt = (TextView) convertView.findViewById(R.id.txt);
				convertView.setTag(tag);
			} else {
				tag = (Tag) convertView.getTag();
			}

			SelectionModel model = (SelectionModel) getItem(position);
			if (model != null) {
				tag.txt.setText(model.getName());
				if (model.isSelected()) {
					tag.txt.setBackgroundResource(R.drawable.txt_gray_selector);
					tag.txt.setTextColor(Color.parseColor("#ffffff"));
				} else {
					tag.txt.setBackgroundResource(R.drawable.layout_selector_all);
					tag.txt.setTextColor(Color.parseColor("#000000"));
				}
			}

			return convertView;
		}

		class Tag {
			TextView txt;
		}
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
			if (!TextUtils.isEmpty(imgUrl)) {
				ImageLoader.getInstance().displayImage(Constants.IMGURL + imgUrl, imageView);
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
