package com.joy.Fragment.rent;

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
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.joy.R;
import com.joy.Activity.MainActivity;
import com.joy.Dialog.DialogUtil;
import com.joy.Dialog.DialogUtil.DialogButtonClickCallback;
import com.joy.Fragment.BaseFragment;
import com.joy.Utils.Constants;
import com.joy.Widget.ContactAdapter;
import com.joy.json.JsonCommon;
import com.joy.json.JsonCommon.OnOperationListener;
import com.joy.json.model.CategoriesGoodsDEntity.CategoriesGoods;
import com.joy.json.model.ActivityDetailEntity;
import com.joy.json.model.CategoriesGoodsDEntity;
import com.joy.json.model.CategoryEntity;
import com.joy.json.model.ContactEntity;
import com.joy.json.model.ContactListEntity;
import com.joy.json.model.RentGoodsEntity;
import com.joy.json.model.RentGoodsListEntity;
import com.joy.json.operation.OperationBuilder;
import com.joy.json.operation.impl.CategoryGoodsListOp;
import com.joy.json.operation.impl.ContactOp;
import com.joy.json.operation.impl.MallGoodsListOp;
import com.joy.json.operation.impl.RentGoodsListOp;
import com.nostra13.universalimageloader.core.ImageLoader;

/***
 *  现实领用物品列表
 * @author Ryan Zhou 2014-11-25
 *
 */
public class RentGoodsListFragment extends BaseFragment {
	private RelativeLayout layout_title;
	private TextView tv_title, tv_ret;
	private Resources resources;
	private Activity curActivity;
	protected UIManager mUiManager;
	protected DialogUtil dialogUtil;

	// 列表
	private ListView listView;
	private RentGoodsAdapter rentGoodsAdapter;
	
	CategoryEntity data;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		resources = this.getResources();
		curActivity = mActivity;
		mUiManager = new UIManager(curActivity);
		dialogUtil = new DialogUtil(curActivity);
	}
	
	/*@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View v= inflater.inflate(R.layout.activity_logostore_all, container,false);
		initView(v);
		return v;
	}*/
	
	@Override
	protected View initContentView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View v= inflater.inflate(R.layout.fragment_rentgoods_list, container, false);
		initView(v);
		getRentGoodsList();
		return v;
	}
	
	private void initView(View v) {
		layout_title = (RelativeLayout) v.findViewById(R.id.layout_title);
		uiAdapter.setMargin(layout_title, LayoutParams.MATCH_PARENT, Constants.TitleHeight, 0, 0, 0, 0);

		tv_title = (TextView) v.findViewById(R.id.tv_title);
		uiAdapter.setTextSize(tv_title, Constants.TitleSize);
		
		tv_title.setText("物品领用");

		tv_ret = (TextView) v.findViewById(R.id.tv_ret);
		tv_ret.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				 FragmentManager fragmentManager = getFragmentManager();
				if (fragmentManager.getBackStackEntryCount() > 0) {
					fragmentManager.popBackStack();
				}
			}
		});
		uiAdapter.setTextSize(tv_ret, Constants.TitleRetSize);
		uiAdapter.setMargin(tv_ret, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 20, 0, 0, 0);
		listView = (ListView) v.findViewById(R.id.listview);
		rentGoodsAdapter = new RentGoodsAdapter();
		listView.setAdapter(rentGoodsAdapter);
		/*listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				// TODO Auto-generated method stub
				RentGoodsEntity rentGoodsEntity = (RentGoodsEntity) parent.getAdapter().getItem(position);
				LogoStoreDetailFragment detailFragment = new LogoStoreDetailFragment();
				Bundle bundle = new Bundle();  
                bundle.putSerializable("detail", goods);
                detailFragment.setArguments(bundle); 
                MainActivity.mActivity.replaceChildFragment("StoreDetailFragment",detailFragment,true);
			}
		});*/
	}
	
	private void getRentGoodsList() {
		OperationBuilder builder = new OperationBuilder().append(new RentGoodsListOp(), null);
		OnOperationListener listener = new OnOperationListener() {
			@Override
			public void onOperationFinished(List<Object> resList) {
				if (curActivity.isFinishing()) {
					return;
				}
				if (resList == null) {
					Toast.show(curActivity, "连接超时");
					return;
				}
				RentGoodsListEntity entity = (RentGoodsListEntity) resList.get(0);
				if (entity == null || (entity != null && entity.getRetobj() == null)) {
					Toast.show(curActivity, "没有物品信息！");
					return;
				}
				List<RentGoodsEntity> rentGoodsList = entity.getRetobj();
				int color = 0;
				if(appSet != null){
					try {
						color = Color.parseColor(appSet.getColor2());
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				rentGoodsAdapter.setBtColor(color);
				rentGoodsAdapter.setData(rentGoodsList);
			}

			@Override
			public void onOperationError(Exception e) {
				e.printStackTrace();
			}
		};
		JsonCommon task = new JsonCommon(curActivity, builder, listener,
				JsonCommon.PROGRESSQUERY);
		task.execute();
	}

	class RentGoodsAdapter extends BaseAdapter {
		private List<RentGoodsEntity> datas;
		private int color;
		
		public void setBtColor(int color){
			if(color !=0 ){
				this.color = color;
			}
		}
		
		public void setData(List<RentGoodsEntity> datas) {
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
			final RentGoodsEntity data;
			
			if (convertView == null) {
				holder = new ViewHolder();
				convertView = LayoutInflater.from(mActivity).inflate(R.layout.rentgoods_list_item, null);
				mUiManager.matchingUIAllFromJson(convertView);
				
				holder.iv_pictures = (ImageView) convertView.findViewById(R.id.iv_pictures);
				uiAdapter.setMargin(holder.iv_pictures, 80, 80, 12, 6, 0, 6);
				
				holder.tv_name = (TextView) convertView.findViewById(R.id.tv_name);
				uiAdapter.setMargin(holder.tv_name, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 10, 12, 0, 0);
				uiAdapter.setTextSize(holder.tv_name, 24);
				TextPaint tp = holder.tv_name.getPaint(); 
				tp.setFakeBoldText(true); 
				
				holder.tv_model = (TextView) convertView.findViewById(R.id.tv_model);
				uiAdapter.setMargin(holder.tv_model, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 10, 0, 0, 12);
				uiAdapter.setTextSize(holder.tv_model, 20);
				
				//holder.tv_currentnum = (TextView) convertView.findViewById(R.id.tv_currentnum);

				holder.bt_rent = (Button) convertView.findViewById(R.id.bt_rent);
				uiAdapter.setTextSize(holder.bt_rent, 24);
				uiAdapter.setMargin(holder.bt_rent, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 0, 0, 12, 0);
				
				holder.bt_rent.setBackgroundColor(color);
				uiAdapter.setTextSize(holder.bt_rent, 16);
				uiAdapter.setMargin(holder.bt_rent, 120, 35, 0, 0, 10, 2);
				
				
				convertView.setTag(holder);
			} else {
				holder = (ViewHolder) convertView.getTag();
			}
			data = (RentGoodsEntity) getItem(position);
			if (data != null) {
				holder.tv_name.setText(data.getName() + "");
				holder.tv_model.setText(data.getModel() + "");
				//holder.tv_currentnum.setText("当前库存数量：" + data.getCurrentNum());
				if (TextUtils.isEmpty(data.getPictures())) {
					holder.iv_pictures.setImageResource(R.drawable.img_default);
				} else {
					ImageLoader.getInstance().displayImage(data.getPictures(), holder.iv_pictures);
				}
				//show dialog
				holder.bt_rent.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						dialogUtil.showDialog("物品领用", 0, "请问您是否要领用"+data.getName()+"？", resources.getString(R.string.confirm), resources.getString(R.string.cancel)
								, new DialogButtonClickCallback() {
							@Override
							public void positiveButtonClick() {
								// TODO Auto-generated method stub
								
								Log.d("0", "------positiveButtonClick()");
							}
							@Override
							public void negativeButtonClick() {
								// TODO Auto-generated method stub
								Log.d("0", "------negativeButtonClick()");
							}
							
						});
					}
				});
			}
			return convertView;
		}

		class ViewHolder {
			ImageView iv_pictures;
			TextView tv_name;
			TextView tv_model;
			TextView tv_currentnum;
			Button bt_rent;
		}

	}

}
