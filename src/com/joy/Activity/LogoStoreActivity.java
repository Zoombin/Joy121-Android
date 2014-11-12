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

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
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
import com.joy.json.model.CategoriesGoodsDEntity.CategoriesGoods;
import com.joy.json.model.CategoryEntity;
import com.joy.json.operation.OperationBuilder;
import com.joy.json.operation.impl.CategoryGoodsListOp;
import com.joy.json.operation.impl.CategoryOp;
import com.nostra13.universalimageloader.core.ImageLoader;

/****
 * Logo商店
 * 
 * @author ADMIN
 * 
 */
public class LogoStoreActivity extends BaseActivity implements OnClickListener {
	private RelativeLayout layout_title;
	private TextView tv_title, tv_ret;
	private Resources resources;
	private Activity mActivity;
	protected UIManager mUiManager;

	// 滚动view
	LinearLayout layout_viewPager;

	// 列表
	private PullToRefreshListView listView;
	private CategoriseAdapter categoriseAdapter;
	
	private List<CategoryEntity> tempList;
	private int index  = 0;

	/*@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_logostore);
		resources = this.getResources();
		mActivity = this;
		mUiManager = new UIManager(self);
		initView();
	}*/
	
	@Override
	protected View ceateView(LayoutInflater inflater, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View v = inflater.inflate(R.layout.activity_logostore, null);
		setContentView(v);
		resources = this.getResources();
		mActivity = this;
		mUiManager = new UIManager(self);
		initView();
		return v;
	}

	private void initView() {
		PLog.e("进入--->%s", "LogoStoreActivity");
		layout_title = (RelativeLayout) findViewById(R.id.layout_title);
		uiAdapter.setMargin(layout_title, LayoutParams.MATCH_PARENT, Constants.TitleHeight, 0, 0, 0, 0);

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
		uiAdapter.setMargin(tv_ret, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 20, 0, 0, 0);
		listView = (PullToRefreshListView) findViewById(R.id.listview);
		listView.setMode(Mode.PULL_FROM_START);
		listView.setAdapter(categoriseAdapter = new CategoriseAdapter());
		listView.setOnRefreshListener(new OnRefreshListener<ListView>() {

			@Override
			public void onRefresh(PullToRefreshBase<ListView> refreshView) {
				getCategories();
			}
		});

		getCategories();
	}

	private void getCategories() {
		CategoryEntity sur = new CategoryEntity();

		OperationBuilder builder = new OperationBuilder().append(new CategoryOp(), sur);
		OnOperationListener listener = new OnOperationListener() {
			@Override
			public void onOperationFinished(List<Object> resList) {
				listView.onRefreshComplete();
				if (self.isFinishing()) {
					return;
				}
				if (resList == null) {
					Toast.show(self, resources.getString(R.string.timeout));
					return;
				}
				CategoryEntity entity = (CategoryEntity) resList.get(0);
				List<CategoryEntity> surveylist = entity.getRetobj();
				if (surveylist == null || surveylist.size() == 0) {
					Toast.show(self, resources.getString(R.string.toast_nologocommo));
					//finish();
					return;
				}

				categoriseAdapter.setData(surveylist);
				
				tempList = surveylist;
				orderRequestGoodsData();
				/*
				 * 连续调用会出问题
				 * 
				 * for(CategoryEntity cEntity :surveylist){
					getGoodsByCategorieId(cEntity.getId());
				}*/
			}

			@Override
			public void onOperationError(Exception e) {
				listView.onRefreshComplete();
				e.printStackTrace();
			}
		};

		JsonCommon task = new JsonCommon(self, builder, listener, JsonCommon.PROGRESSQUERY);
		task.execute();
	}
	
	/***
	 * 顺序请求goods数据
	 */
	private void orderRequestGoodsData(){
		if(tempList != null && tempList.size()>0){
			if(index < tempList.size()){
				getGoodsByCategorieId(tempList.get(index).getId());
				index ++;
			}else{
				index = 0;
			}
		}
	}

	class CategoriseAdapter extends BaseAdapter {
		private List<CategoryEntity> datas;
		int i =0;

		public void addData(CategoryEntity data) {
			if (datas == null) {
				datas = new ArrayList<CategoryEntity>();
			}
			datas.add(data);
			this.notifyDataSetChanged();
		}
		
		public void refrashData(CategoriesGoodsDEntity gDEntity) {
			if(gDEntity != null){
				String categoryId = gDEntity.getCategoryId();
				if(!TextUtils.isEmpty(categoryId) && datas != null){
					for(CategoryEntity cEntity :datas){
						if(categoryId.equals(cEntity.getId())){
							cEntity.setGoodsList(gDEntity.getRetobj());
						}
					}
				}
				this.notifyDataSetChanged();
			}
		}

		public void setData(List<CategoryEntity> datas) {
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
				convertView = LayoutInflater.from(mActivity).inflate(R.layout.layout_categories_item, null);
				//mUiManager.matchingUIAllFromJson(convertView);

				holder.txt_categoriesName = (RectangleTextView) convertView.findViewById(R.id.txt_categoriesName);
				holder.txt_showall = (TextView) convertView.findViewById(R.id.txt_showall);
				holder.horizontalListView = (HorizontalListView) convertView.findViewById(R.id.horizontalListView);
				convertView.setTag(holder);
			} else
				holder = (ViewHolder) convertView.getTag();

			CategoryEntity data = (CategoryEntity) getItem(position);
			if (data != null) {
				holder.txt_categoriesName.setText(data.getCategoryName());
				holder.txt_categoriesName.setTag(data);
				holder.txt_categoriesName.setOnClickListener(LogoStoreActivity.this);

				holder.txt_showall.setText(resources.getString(R.string.displayall) + " >");
				holder.txt_showall.setTag(data);

				List<CategoriesGoods> goodsList = data.getGoodsList();
				HorizontalCategoriseAdapter hAdapter = new HorizontalCategoriseAdapter();
				holder.horizontalListView.setAdapter(hAdapter);
				if(goodsList != null && goodsList.size()>0){
					hAdapter.setData(data.getGoodsList());
					holder.horizontalListView.setOnItemClickListener(new OnItemClickListener() {
						@Override
						public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
							// TODO Auto-generated method stub
							CategoriesGoods goods = (CategoriesGoods) parent.getAdapter().getItem(position);
							Intent intent = new Intent();
							intent.setClass(mActivity, StoreDetailActivity.class);
							Bundle bundle = new Bundle();
							bundle.putSerializable("detail", goods);
							intent.putExtras(bundle);
							startActivity(intent);
						}
					});
				}else{
					hAdapter.setData(null);
				}
			}
			return convertView;
		}

		class ViewHolder {
			RectangleTextView txt_categoriesName;
			TextView txt_showall;
			HorizontalListView horizontalListView;
		}

	}

	/***
	 * 根据id获取商品列表
	 * 
	 * @param id
	 * @param hAdapter
	 */
	private void getGoodsByCategorieId(String id) {
		//CategoriesGoodsDEntity goods = new CategoriesGoodsDEntity();
		//请求参数

		OperationBuilder builder = new OperationBuilder().append(new CategoryGoodsListOp(), id);
		OnOperationListener listener = new OnOperationListener() {
			@Override
			public void onOperationFinished(List<Object> resList) {
				orderRequestGoodsData();
				if (self.isFinishing()) {
					return;
				}
				if (resList == null) {
					// Toast.show(self, "连接超时");
					return;
				}
				CategoriesGoodsDEntity entity = (CategoriesGoodsDEntity) resList.get(0);
				if (entity == null || (entity != null && entity.getRetobj() == null)) {
					// Toast.show(self, "没有商品信息！");
					return;
				}
				categoriseAdapter.refrashData(entity);
			}

			@Override
			public void onOperationError(Exception e) {
				orderRequestGoodsData();
				e.printStackTrace();
			}
		};

		final JsonCommon task = new JsonCommon(self, builder, listener, false);
		task.execute();
	}

	class HorizontalCategoriseAdapter extends BaseAdapter {
		List<CategoriesGoods> datas;
		
		public void setData(List<CategoriesGoods> datas) {
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
				convertView = LayoutInflater.from(mActivity).inflate(R.layout.logo_categories_goods_item, null);
				//mUiManager.matchingUIAllFromJson(convertView);
				convertView.setTag(holder);

				holder.img_icon = (ImageView) convertView.findViewById(R.id.img_icon);
				holder.txt_price = (TextView) convertView.findViewById(R.id.txt_goods_price);
				holder.txt_goodsname = (TextView) convertView.findViewById(R.id.txt_goods_name);
			} else {
				holder = (ViewHolder) convertView.getTag();
			}

			CategoriesGoods data = (CategoriesGoods) getItem(position);
			if (data != null) {
				holder.txt_price.setText(data.getMarketPrice() + "");
				holder.txt_goodsname.setText(data.getComName());
				holder.img_icon.setImageResource(R.drawable.img_default);
				if (TextUtils.isEmpty(data.getPicture())) {
					holder.img_icon.setImageResource(R.drawable.img_default);
				} else {
					ImageLoader.getInstance().displayImage(Constants.IMGSURL + data.getPicture(), holder.img_icon);
				}
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
}
