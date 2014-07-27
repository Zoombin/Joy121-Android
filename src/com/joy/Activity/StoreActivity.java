package com.joy.Activity;

import gejw.android.quickandroid.QActivity;
import gejw.android.quickandroid.log.PLog;
import gejw.android.quickandroid.ui.adapter.UIManager;
import gejw.android.quickandroid.widget.HorizontalListView;
import gejw.android.quickandroid.widget.Toast;
import gejw.android.quickandroid.widget.PullToRefresh.PullToRefreshBase;
import gejw.android.quickandroid.widget.PullToRefresh.PullToRefreshBase.Mode;
import gejw.android.quickandroid.widget.PullToRefresh.PullToRefreshBase.OnRefreshListener;
import gejw.android.quickandroid.widget.PullToRefresh.PullToRefreshListView;

import java.util.ArrayList;
import java.util.List;

import android.R.integer;
import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.joy.R;
import com.joy.Utils.Constants;
import com.joy.Widget.RectangleTextView;
import com.joy.json.JsonCommon;
import com.joy.json.JsonCommon.OnOperationListener;
import com.joy.json.model.CategoriesGoodsDEntity;
import com.joy.json.model.CategoryEntity;
import com.joy.json.model.CategoriesGoods;
import com.joy.json.operation.OperationBuilder;
import com.joy.json.operation.impl.CategoryGoodsListOp;
import com.joy.json.operation.impl.CategoryListOp;
import com.nostra13.universalimageloader.core.ImageLoader;

public class StoreActivity extends QActivity implements OnClickListener{
	private Handler mHandler;
	private RelativeLayout layout_title;
	private TextView tv_title,tv_ret;
	private Resources resources;
	private Activity mActivity;
	protected UIManager mUiManager;
	
	// 滚动view
	private List<View> views = new ArrayList<View>();
//	ViewPager viewPager;
//	ViewPagerAdapter viewPagerAdapter;
	LinearLayout layout_viewPager;

	// 列表
	private PullToRefreshListView pullListView;
	private CategoriseAdapter categoriseAdapter;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_store);
		mHandler = new Handler();
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
		
		tv_ret = (TextView) findViewById(R.id.tv_ret);
		tv_ret.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
		uiAdapter.setTextSize(tv_ret, Constants.TitleRetSize);
		uiAdapter.setMargin(tv_ret, LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT, 20, 0, 0, 0);
		pullListView = (PullToRefreshListView) findViewById(R.id.listview);
		pullListView.setMode(Mode.PULL_FROM_START);
		//pullListView.mRefreshableView.addHeaderView(layout_viewPager);
		pullListView.setAdapter(categoriseAdapter = new CategoriseAdapter());
		pullListView.setOnRefreshListener(new OnRefreshListener<ListView>() {

			@Override
			public void onRefresh(PullToRefreshBase<ListView> refreshView) {
					getCategories();
			}
		});

			getCategories();
	}


	private void getCategories() {
		CategoryEntity sur = new CategoryEntity();

		OperationBuilder builder = new OperationBuilder().append(new CategoryListOp(),
				sur);
		OnOperationListener listener = new OnOperationListener() {
			@Override
			public void onOperationFinished(List<Object> resList) {
				pullListView.onRefreshComplete();
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
				
				PLog.e("返回结果--->%s", surveylist.size());
				for(CategoryEntity data:surveylist){
					PLog.e("id %s", data.getId());
					PLog.e("name %s", data.getCategoryName());
				}

				categoriseAdapter.addData(surveylist);
			}
			@Override
			public void onOperationError(Exception e) {
				pullListView.onRefreshComplete();
				e.printStackTrace();
			}
		};

		JsonCommon task = new JsonCommon(self, builder, listener,
				JsonCommon.PROGRESSQUERY);
		task.execute();
	}

	class CategoriseAdapter extends BaseAdapter {
		private List<CategoryEntity> datas;
		public void addData(List<CategoryEntity> datas){
			this.datas = datas;
			this.notifyDataSetChanged();
		}

		@Override
		public int getCount() {
			return datas == null ? 0 : datas.size();
		}

		@Override
		public Object getItem(int position) {
			return datas == null ? null : datas.get(position);
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

				holder.txt_categoriesName = (RectangleTextView) convertView.findViewById(R.id.txt_categoriesName);
				holder.txt_showall = (TextView) convertView
						.findViewById(R.id.txt_showall);
				holder.horizontalListView = (HorizontalListView) convertView
						.findViewById(R.id.horizontalListView);
				convertView.setTag(holder);
			} else
				holder = (ViewHolder) convertView.getTag();

			CategoryEntity data = (CategoryEntity) getItem(position);
			if(data != null){
				holder.txt_categoriesName.setText(data.getCategoryName());
				holder.txt_categoriesName.setTag(data);
				holder.txt_categoriesName
						.setOnClickListener(StoreActivity.this);

				holder.txt_showall.setText("显示全部 >");
				holder.txt_showall.setTag(data);
				holder.txt_showall.setVisibility(4);
				
				String id = data.getId();
				HorizontalCategoriseAdapter hAdapter = new HorizontalCategoriseAdapter();
				holder.horizontalListView.setAdapter(hAdapter);
				holder.horizontalListView.setOnItemClickListener(new OnItemClickListener() {
					@Override
					public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
						// TODO Auto-generated method stub
						CategoriesGoods goods = (CategoriesGoods) parent.getAdapter().getItem(position);
						Intent intent = new Intent();
						intent.setClass(mActivity, CategorieStoreActivity.class);
						Bundle bundle = new Bundle();
						bundle.putSerializable("detail", goods);
						intent.putExtras(bundle);
						startActivity(intent);
					}
				});
				getCategorieGoods(id,hAdapter);
			}
			return convertView;
		}

		class ViewHolder {
			RectangleTextView txt_categoriesName;
			TextView txt_showall;
			HorizontalListView horizontalListView;
		}

	}
	
	private void getCategorieGoods(String id,final HorizontalCategoriseAdapter hAdapter ) {
		PLog.e("id--->%s", id);
		CategoriesGoodsDEntity goods = new CategoriesGoodsDEntity();

		OperationBuilder builder = new OperationBuilder().append(new CategoryGoodsListOp(id),
				goods);
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
				CategoriesGoodsDEntity entity = (CategoriesGoodsDEntity) resList.get(0);
				if (entity == null || (entity != null&&entity.getGoods() == null)|| (entity != null&&entity.getGoods().size()==0)) {
					//Toast.show(self, "没有商品信息！");
					return;
				}
				PLog.e("dfsfd fd -->%s", entity.getGoods().size());
				
				//HorizontalCategoriseAdapter hAdapter = new HorizontalCategoriseAdapter();
				//hListView.setAdapter(hAdapter);
				hAdapter.setData(entity.getGoods());
			}
			@Override
			public void onOperationError(Exception e) {
				e.printStackTrace();
			}
		};

		final JsonCommon task = new JsonCommon(self, builder, listener,false);
		task.execute();
		
		/*int time = 60;
		int sir = 0;
		try {
			sir = Integer.parseInt(id);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		mHandler.postDelayed(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				
			}
		}, time + sir*2);*/
	}

	class HorizontalCategoriseAdapter extends BaseAdapter {
		List<CategoriesGoods> datas;
		/*public HorizontalCategoriseAdapter(List<Goods> datas) {
			// TODO Auto-generated constructor stub
			this.datas  = datas;
		}*/
		
		public void setData(List<CategoriesGoods> datas){
			this.datas = datas;
			this.notifyDataSetChanged();
		}

		@Override
		public int getCount() {
			return datas == null ? 0 : datas.size();
		}

		@Override
		public Object getItem(int position) {
			return datas == null ? null : datas.get(position);
		}

		@Override
		public long getItemId(int position) {
			return datas == null ? 0 : position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup arg2) {
			ViewHolder holder = null;
			if (convertView == null) {
				holder = new ViewHolder();
				convertView = LayoutInflater.from(mActivity).inflate(
						R.layout.logo_categories_goods_item, null);
				convertView.setTag(holder);
				mUiManager.matchingUIAllFromJson(convertView);

				holder.img_icon = (ImageView) convertView
						.findViewById(R.id.img_icon);
				holder.txt_price = (TextView) convertView
						.findViewById(R.id.txt_goods_price);
				holder.txt_goodsname = (TextView) convertView
						.findViewById(R.id.txt_goods_name);
				//convertView.setTag(holder);
			} else{
				holder = (ViewHolder) convertView.getTag();
			}
			
			CategoriesGoods data = (CategoriesGoods) getItem(position);
			if(data != null){
				PLog.e("dfsfd fd -->%s", data.getComName());
				holder.txt_price.setText(data.getMarketPrice()+"");
				holder.txt_goodsname.setText(data.getComName());
				//holder.img_icon.setImageResource(R.drawable.app_icon);
				ImageLoader.getInstance().displayImage(Constants.IMGSURL+data.getPicture(), holder.img_icon);
			}
			return convertView;
		}

		class ViewHolder {
			ImageView img_icon;
			TextView txt_price;
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
