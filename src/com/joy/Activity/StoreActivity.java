package com.joy.Activity;

//import me.dushuhu.android.Config.NetConfig;
import java.util.ArrayList;
import java.util.List;


import com.joy.Utils.Constants;
import com.joy.json.JsonCommon;
import com.joy.json.JsonCommon.OnOperationListener;
import com.joy.json.model.CategoryEntity;
//import me.dushuhu.android.Entity.CategoriesGoodsEntity.CategoriesData;
import com.joy.json.model.CategoriesGoodsEntity.CategoriesGood;
import com.joy.json.operation.OperationBuilder;
import com.joy.json.operation.impl.CategoryListOp;
import gejw.android.quickandroid.QActivity;
import gejw.android.quickandroid.log.PLog;
import gejw.android.quickandroid.ui.adapter.UIManager;
import gejw.android.quickandroid.widget.HorizontalListView;
import gejw.android.quickandroid.widget.Toast;
import gejw.android.quickandroid.widget.PullToRefresh.PullToRefreshBase;
import gejw.android.quickandroid.widget.PullToRefresh.PullToRefreshListView;
import gejw.android.quickandroid.widget.PullToRefresh.PullToRefreshBase.Mode;
import gejw.android.quickandroid.widget.PullToRefresh.PullToRefreshBase.OnRefreshListener;

import com.joy.R;

import android.os.Bundle;
import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.content.res.Resources;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class StoreActivity extends QActivity implements OnClickListener{
	
	private RelativeLayout layout_title;
	private TextView tv_title;
	private Resources resources;
	private Activity mActivity;
	protected UIManager mUiManager;
	
	// 滚动view
	private List<View> views = new ArrayList<View>();
//	ViewPager viewPager;
//	ViewPagerAdapter viewPagerAdapter;
	LinearLayout layout_viewPager;

	// 列表
	private PullToRefreshListView listView;
	private CategoriseAdapter categoriseAdapter;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_store);
		resources = this.getResources();
		mActivity = this;
		mUiManager = new UIManager(self);;
		initView();
	}
	
	private void initView() {
		PLog.e("进入--->%s", "TypeFragment");
		layout_title = (RelativeLayout) findViewById(R.id.layout_title);
		uiAdapter.setMargin(layout_title, LayoutParams.MATCH_PARENT, Constants.TitleHeight, 0, 0,
				0, 0);

		tv_title = (TextView) findViewById(R.id.tv_title);
		uiAdapter.setTextSize(tv_title, Constants.TitleSize);

		listView = (PullToRefreshListView) findViewById(R.id.listview);
		listView.setMode(Mode.PULL_FROM_START);
		listView.mRefreshableView.addHeaderView(layout_viewPager);
		listView.setAdapter(categoriseAdapter = new CategoriseAdapter());
		listView.setOnRefreshListener(new OnRefreshListener<ListView>() {

			@Override
			public void onRefresh(PullToRefreshBase<ListView> refreshView) {
					getCategories();
			}
		});

		if (datas == null || datas.length == 0)
			getCategories();
	}


	private CategoryEntity[] datas = new CategoryEntity[0];

	private void getCategories() {
		CategoryEntity sur = new CategoryEntity();

		OperationBuilder builder = new OperationBuilder().append(new CategoryListOp(),
				sur);
		OnOperationListener listener = new OnOperationListener() {
			@Override
			public void onOperationFinished(List<Object> resList) {
				if (self.isFinishing()) {
					return;
				}
				if (resList == null) {
					Toast.show(self, "连接超时");
					return;
				}
				CategoryEntity entity = (CategoryEntity) resList.get(0);
				List<CategoryEntity> surveylist = entity.getRetobj();
				
				if (surveylist == null) {
					Toast.show(self, "没有分类信息！");
					finish();
					return;
				}
				
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

	class CategoriseAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			return datas.length;
		}

		@Override
		public Object getItem(int position) {
			return datas[position];
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup arg2) {
			ViewHolder holder = null;
			if (convertView == null) {
				holder = new ViewHolder();
				convertView = LayoutInflater.from(mActivity).inflate(
						R.layout.layout_categories_item, null);
				mUiManager.matchingUIAllFromJson(convertView);

//				holder.txt_categoriesName = (RectangleTextView) convertView
//						.findViewById(R.id.txt_categoriesName);
				holder.txt_showall = (TextView) convertView
						.findViewById(R.id.txt_showall);
				holder.horizontalListView = (HorizontalListView) convertView
						.findViewById(R.id.horizontalListView);
				convertView.setTag(holder);
			} else
				holder = (ViewHolder) convertView.getTag();

			CategoryEntity data = datas[position];
			holder.txt_categoriesName.setText(data.getCategoryName());
			holder.txt_categoriesName.setTag(data);
			holder.txt_categoriesName
					.setOnClickListener(StoreActivity.this);

			holder.txt_showall.setTag(data);
//			holder.txt_showall.setOnClickListener(CategoriesFragment.this);
//			final CategoriesGood[] goods = data.getCategory_goods();
//			holder.horizontalListView
//					.setAdapter(new HorizontalCategoriseAdapter(goods));
//			holder.horizontalListView
//					.setOnItemClickListener(new OnItemClickListener() {
//
//						@Override
//						public void onItemClick(AdapterView<?> arg0, View view,
//								int position, long arg3) {
//							Bundle bundle = new Bundle();
//							bundle.putString("goodsid",
//									goods[position].getGoods_id());
//
//							GoodsDetails goodsDetails = new GoodsDetails();
//							goodsDetails.setArguments(bundle);
//							MainActivity.mActivity.replaceChildFragment("goods"
//									+ goods[position].getGoods_id(),
//									goodsDetails, true);
//						}
//					});

			return convertView;
		}

		class ViewHolder {
			TextView txt_categoriesName;
			TextView txt_showall;
			HorizontalListView horizontalListView;
		}

	}

	class HorizontalCategoriseAdapter extends BaseAdapter {

		CategoriesGood[] categoriesGoods;

		public HorizontalCategoriseAdapter(CategoriesGood[] categoriesGoods) {
			this.categoriesGoods = categoriesGoods;
		}

		@Override
		public int getCount() {
			return categoriesGoods.length;
		}

		@Override
		public Object getItem(int position) {
			return categoriesGoods[position];
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup arg2) {
			ViewHolder holder = null;
			if (convertView == null) {
				holder = new ViewHolder();
				convertView = LayoutInflater.from(mActivity).inflate(
						R.layout.layout_categories_goods_item, null);
				convertView.setTag(holder);
				mUiManager.matchingUIAllFromJson(convertView);

				holder.img_icon = (ImageView) convertView
						.findViewById(R.id.img_icon);
				holder.txt_price_now = (TextView) convertView
						.findViewById(R.id.txt_price_now);
				holder.txt_price_old = (TextView) convertView
						.findViewById(R.id.txt_price_old);
				holder.txt_price_old.getPaint().setFlags(
						Paint.STRIKE_THRU_TEXT_FLAG);
				holder.txt_goodsname = (TextView) convertView
						.findViewById(R.id.txt_goodsname);
			} else
				holder = (ViewHolder) convertView.getTag();

			CategoriesGood good = categoriesGoods[position];
//			String icon_url = NetConfig.URL() + good.getGoods_img();
//			ImageLoader.getInstance().displayImage(icon_url, holder.img_icon);

			
				// 普通商品
				holder.txt_price_now.setVisibility(View.VISIBLE);
				holder.txt_price_old.setVisibility(View.VISIBLE);
				holder.txt_price_now.setText(good.getShop_price());
				holder.txt_price_old.setText(good.getMarket_price());

			holder.txt_goodsname.setText(good.getGoods_name());
			return convertView;
		}

		class ViewHolder {
			ImageView img_icon;
			TextView txt_price_now;
			TextView txt_price_old;
			TextView txt_goodsname;
		}
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		
	}

//	private FlashData[] imgUrls;

	/**
	 * 获取滚轮图
	 */
//	private void getViewPagerData() {
//		if (imgUrls != null && imgUrls.length > 0) {
//			updateViewpager(imgUrls);
//			return;
//		}
//		String url = NetConfig.URL() + "index.php?action=flashimages";
//		NetUtils.getValue(mActivity, url, getString(R.string.loading),
//				new NetCallBack() {
//
//					@Override
//					public void success(String json) {
//						imgUrls = new Gson().fromJson(json,
//								FlashImageEntity.class).getData();
//						updateViewpager(imgUrls);
//					}
//
//					@Override
//					public void failed(String msg) {
//						Toast.show(mActivity, msg);
//					}
//				});
//	}

//	/***** 滚动布局 ******/
//	private void updateViewpager(FlashData[] imgUrls) {
//		views.clear();
//		for (int i = 0; i < imgUrls.length; i++) {
//			String url = String.format("%s/%s", NetConfig.URL(),
//					imgUrls[i].getSrc());
//			PLog.e("image---->%s", url);
//			ImageView imageView = new ImageView(mActivity);
//			imageView.setScaleType(ScaleType.FIT_XY);
//			uiAdapter.setMargin(viewPager, 480,
//					uiAdapter.CalcHeight(480, 710, 275), 0, 0, 0, 0);
//			ImageLoader.getInstance().displayImage(url, imageView);
//			views.add(imageView);
//		}
//
//		viewPagerAdapter.notifyDataSetChanged();
//	}
//
//	private void initViewPager() {
//		layout_viewPager = new LinearLayout(mActivity);
//		viewPager = new ViewPager(mActivity);
//		viewPagerAdapter = new ViewPagerAdapter();
//		viewPager.setAdapter(viewPagerAdapter);
//		layout_viewPager.addView(viewPager);
//		uiAdapter.setMargin(viewPager, 480,
//				uiAdapter.CalcHeight(480, 710, 275), 0, 0, 0, 0);
//	}
//
//	public class ViewPagerAdapter extends PagerAdapter {
//
//		@Override
//		public int getItemPosition(Object object) {
//			return POSITION_NONE;
//		}
//
//		@Override
//		public void destroyItem(ViewGroup container, int position, Object object) {
//			container.removeView(views.get(position));// 删除页卡
//		}
//
//		@Override
//		public Object instantiateItem(ViewGroup container, int position) { // 这个方法用来实例化页卡
//			container.addView(views.get(position), 0);// 添加页卡
//			return views.get(position);
//		}
//
//		@Override
//		public int getCount() {
//			return views.size();// 返回页卡的数量
//		}
//
//		@Override
//		public boolean isViewFromObject(View view, Object object) {
//			return view == object;// 官方提示这样写
//		}
//	}
//
//	class ViewItem {
//		public ViewItem(LinearLayout layout, CategoriesGood good) {
//			super();
//			this.layout = layout;
//			this.good = good;
//		}
//
//		LinearLayout layout;
//		CategoriesGood good;
//	}


}
