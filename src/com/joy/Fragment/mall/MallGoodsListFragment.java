package com.joy.Fragment.mall;

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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.joy.R;
import com.joy.Activity.MainActivity;
import com.joy.Fragment.BaseFragment;
import com.joy.Utils.Constants;
import com.joy.Widget.ContactAdapter;
import com.joy.json.JsonCommon;
import com.joy.json.JsonCommon.OnOperationListener;
import com.joy.json.model.CategoriesGoodsDEntity.CategoriesGoods;
import com.joy.json.model.CategoriesGoodsDEntity;
import com.joy.json.model.CategoryEntity;
import com.joy.json.model.ContactEntity;
import com.joy.json.model.ContactListEntity;
import com.joy.json.operation.OperationBuilder;
import com.joy.json.operation.impl.CategoryGoodsListOp;
import com.joy.json.operation.impl.ContactOp;
import com.joy.json.operation.impl.MallGoodsListOp;
import com.nostra13.universalimageloader.core.ImageLoader;

/***
 * Logo商店全部商品
 * @author lsd
 *
 */
public class MallGoodsListFragment extends BaseFragment {
	
	public static final String EXTRA_CATEGROY1ID = "category1id";
	public static final String EXTRA_CATEGROY1NAME = "category1name";
	public static final String CLASS_NAME = "MallGoodsListFragment";
	private String category1id, category1name;
	private RelativeLayout layout_title;
	private TextView tv_title, tv_ret;
	private Resources resources;
	private Activity curActivity;
	protected UIManager mUiManager;

	// 列表
	private ListView listView;
	private CategoriseAllAdapter categoriseAllAdapter;
	
	CategoryEntity data;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		resources = this.getResources();
		curActivity = mActivity;
		mUiManager = new UIManager(curActivity);
		category1id = (String) getArguments().getSerializable(EXTRA_CATEGROY1ID);
		category1name = (String) getArguments().getSerializable(EXTRA_CATEGROY1NAME);
		
		
		//data = (CategoryEntity) getArguments().getSerializable("data");
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
		View v= inflater.inflate(R.layout.fragment_mallgoods_list, container, false);
		initView(v);
		return v;
	}
	
	private void initView(View v) {
		layout_title = (RelativeLayout) v.findViewById(R.id.layout_title);
		uiAdapter.setMargin(layout_title, LayoutParams.MATCH_PARENT, Constants.TitleHeight, 0, 0, 0, 0);

		tv_title = (TextView) v.findViewById(R.id.tv_title);
		uiAdapter.setTextSize(tv_title, Constants.TitleSize);
		
		tv_title.setText(category1name);

		tv_ret = (TextView) v.findViewById(R.id.tv_ret);
		tv_ret.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				MainActivity.mActivity.Back();
			}
		});
		uiAdapter.setTextSize(tv_ret, Constants.TitleRetSize);
		uiAdapter.setMargin(tv_ret, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 20, 0, 0, 0);
		listView = (ListView) v.findViewById(R.id.listview);
		categoriseAllAdapter = new CategoriseAllAdapter();
		listView.setAdapter(categoriseAllAdapter);
		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				// TODO Auto-generated method stub
				CategoriesGoods goods = (CategoriesGoods) parent.getAdapter().getItem(position);
				Log.d("0", "goods.getComName() = " + goods.getComName());
				//LogoStoreDetailFragment detailFragment = new LogoStoreDetailFragment();
				//Bundle bundle = new Bundle();  
                //bundle.putSerializable("detail", goods);
                //detailFragment.setArguments(bundle); 
                //MainActivity.mActivity.replaceChildFragment("StoreDetailFragment",detailFragment,true);
			}
		});
		getMallGoodsByCategorieId(category1id);
	}
	
	private void getMallGoodsByCategorieId(String id) {
		//CategoriesGoodsDEntity goods = new CategoriesGoodsDEntity();
		//请求参数

		OperationBuilder builder = new OperationBuilder().append(new MallGoodsListOp(), id);
		OnOperationListener listener = new OnOperationListener() {
			@Override
			public void onOperationFinished(List<Object> resList) {
				if (curActivity.isFinishing()) {
					return;
				}
				if (resList == null) {
					// Toast.show(self, "连接超时");
					return;
				}
				//ContactListEntity entity = (ContactListEntity) resList.get(0);
				//List<ContactEntity> contactEntityList = entity.getRetobj();
				
				
				CategoriesGoodsDEntity entity = (CategoriesGoodsDEntity) resList.get(0);
				if (entity == null || (entity != null && entity.getRetobj() == null)) {
					// Toast.show(self, "没有商品信息！");
					return;
				}
				List<CategoriesGoods> categoriesGoods = entity.getRetobj();
				categoriseAllAdapter.setData(categoriesGoods);
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

	class CategoriseAllAdapter extends BaseAdapter {
		private List<CategoriesGoods> datas;

		
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
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup arg2) {
			ViewHolder holder = null;
			if (convertView == null) {
				holder = new ViewHolder();
				convertView = LayoutInflater.from(mActivity).inflate(R.layout.layout_categories_all_item, null);
				mUiManager.matchingUIAllFromJson(convertView);
				
				holder.img = (ImageView) convertView.findViewById(R.id.img);
				holder.txt_content = (TextView) convertView.findViewById(R.id.txt_content);
				holder.txt_points = (TextView) convertView.findViewById(R.id.txt_points);
				
				convertView.setTag(holder);
			} else
				holder = (ViewHolder) convertView.getTag();

			CategoriesGoods data = (CategoriesGoods) getItem(position);
			if (data != null) {
				holder.txt_content.setText(data.getComName() + "");
				holder.txt_points.setText("所需积分：" + data.getPoints());
				if (TextUtils.isEmpty(data.getPicture())) {
					holder.img.setImageResource(R.drawable.img_default);
				} else {
					ImageLoader.getInstance().displayImage(Constants.IMGSURL + data.getPicture(), holder.img);
				}
			}
			return convertView;
		}

		class ViewHolder {
			ImageView img;
			TextView txt_content;
			TextView txt_points;
		}

	}

}
