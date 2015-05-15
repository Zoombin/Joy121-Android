package com.joy.Fragment.portals.logostore;

import gejw.android.quickandroid.log.PLog;
import gejw.android.quickandroid.ui.adapter.UIManager;
import gejw.android.quickandroid.utils.ResName2ID;
import gejw.android.quickandroid.widget.HorizontalListView;
import gejw.android.quickandroid.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
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
import com.joy.Activity.MainActivity;
import com.joy.Activity.StoreDetailActivity;
import com.joy.Fragment.BaseFragment;
import com.joy.Fragment.TopFragment.TopPortalsFragment;
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

/***
 * Logo商店
 * @author lsd
 *
 */
public class LogoStoreFragment extends BaseFragment {
	private RelativeLayout layout_title;
	private TextView tv_title;
	private ImageView iv_ret;
	private RelativeLayout layout_prompt;
	private TextView tv_prompt;
	private ImageView iv_prompt;
	private Resources resources;
	private Activity curActivity;
	protected UIManager mUiManager;

	// 滚动view
	LinearLayout layout_viewPager;

	// 列表
	private ListView listView;
	private CategoriseAdapter categoriseAdapter;
	
	private List<CategoryEntity> tempList;
	private int index  = 0;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		resources = this.getResources();
		curActivity = mActivity;
		mUiManager = new UIManager(curActivity);
	}
	
	/*@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View v= inflater.inflate(R.layout.activity_logostore, container,false);
		initView(v);
		return v;
	}*/
	
	@Override
	protected View initContentView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View v= inflater.inflate(R.layout.activity_logostore, container,false);
		initView(v);
		return v;
	}
	
	private void initView(View v) {
		PLog.e("进入--->%s", "LogoStoreActivity");
		initTitleLayout(v);
		initPromptInfo(v);
		listView = (ListView) v.findViewById(R.id.listview);
		//listView.setMode(Mode.PULL_FROM_START);
		listView.setAdapter(categoriseAdapter = new CategoriseAdapter());
		/*listView.setOnRefreshListener(new OnRefreshListener<ListView>() {

			@Override
			public void onRefresh(PullToRefreshBase<ListView> refreshView) {
				getCategories();
			}
		});*/
        if(tempList == null){
        	getCategories();
        }else{
        	categoriseAdapter.setData(tempList);
        }
	}
	
	
	private void initTitleLayout(View v){
		layout_title = (RelativeLayout) v.findViewById(R.id.layout_title);
		uiAdapter.setMargin(layout_title, LayoutParams.MATCH_PARENT, Constants.TitleHeight, 0, 0, 0, 0);

		tv_title = (TextView) v.findViewById(R.id.tv_title);
		uiAdapter.setTextSize(tv_title, Constants.TitleSize);

		iv_ret = (ImageView) v.findViewById(R.id.iv_ret);
		iv_ret.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				MainActivity.mActivity.Back();
			}
		});
	}
	
	private void initPromptInfo(View v) {
		layout_prompt = (RelativeLayout) v.findViewById(R.id.layout_prompt);
		layout_prompt.setBackgroundColor(Color.parseColor(appSet.getColor2()));
		iv_prompt = (ImageView) v.findViewById(R.id.iv_prompt);
		iv_prompt.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				layout_prompt.setVisibility(View.GONE);
			}
		});
	}
	
	private void getCategories() {
		CategoryEntity sur = new CategoryEntity();

		OperationBuilder builder = new OperationBuilder().append(new CategoryOp(), sur);
		OnOperationListener listener = new OnOperationListener() {
			@Override
			public void onOperationFinished(List<Object> resList) {
				//listView.onRefreshComplete();
				if (curActivity.isFinishing()) {
					return;
				}
				if (resList == null) {
					Toast.show(curActivity, "连接超时");
					return;
				}
				CategoryEntity entity = (CategoryEntity) resList.get(0);
				List<CategoryEntity> surveylist = entity.getRetobj();
				if (surveylist == null || surveylist.size() == 0) {
					layout_prompt.setVisibility(View.VISIBLE);
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
				//listView.onRefreshComplete();
				e.printStackTrace();
			}
		};

		JsonCommon task = new JsonCommon(curActivity, builder, listener, JsonCommon.PROGRESSQUERY);
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

				holder.txt_showall.setText("显示全部 >");

				List<CategoriesGoods> goodsList = data.getGoodsList();
				HorizontalCategoriseAdapter hAdapter = new HorizontalCategoriseAdapter();
				holder.horizontalListView.setAdapter(hAdapter);
				if(goodsList != null && goodsList.size()>0){
					//传递数据
					holder.txt_showall.setTag(data);
					holder.txt_showall.setOnClickListener(new OnClickListener() {
						@Override
						public void onClick(View v) {
							// TODO Auto-generated method stub
							CategoryEntity data =  (CategoryEntity) v.getTag();
							LogoStoreAllFragment allFragment = new LogoStoreAllFragment();
							Bundle bundle = new Bundle();  
			                bundle.putSerializable("data", data);
			                allFragment.setArguments(bundle); 
			                MainActivity.mActivity.replaceChildFragment("LogoStoreAllFragment",allFragment,true);
						}
					});
					hAdapter.setData(data.getGoodsList());
					holder.horizontalListView.setOnItemClickListener(new OnItemClickListener() {
						@Override
						public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
							// TODO Auto-generated method stub
							/*CategoriesGoods goods = (CategoriesGoods) parent.getAdapter().getItem(position);
							Intent intent = new Intent();
							intent.setClass(mActivity, StoreDetailActivity.class);
							Bundle bundle = new Bundle();
							bundle.putSerializable("detail", goods);
							intent.putExtras(bundle);
							startActivity(intent);*/
							
							CategoriesGoods goods = (CategoriesGoods) parent.getAdapter().getItem(position);
							LogoStoreDetailFragment detailFragment = new LogoStoreDetailFragment();
							Bundle bundle = new Bundle();  
			                bundle.putSerializable("detail", goods);
			                detailFragment.setArguments(bundle); 
			                MainActivity.mActivity.replaceChildFragment("StoreDetailFragment",detailFragment,true);
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
				if (curActivity.isFinishing()) {
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

		final JsonCommon task = new JsonCommon(curActivity, builder, listener, false);
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

}
