package com.joy.Fragment.portals.logostore;

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
 * Logo商店全部商品
 * @author lsd
 *
 */
public class LogoStoreAllFragment extends BaseFragment {
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
		
		data = (CategoryEntity) getArguments().getSerializable("data");
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View v= inflater.inflate(R.layout.activity_logostore_all, container,false);
		initView(v);
		return v;
	}
	
	private void initView(View v) {
		layout_title = (RelativeLayout) v.findViewById(R.id.layout_title);
		uiAdapter.setMargin(layout_title, LayoutParams.MATCH_PARENT, Constants.TitleHeight, 0, 0, 0, 0);

		tv_title = (TextView) v.findViewById(R.id.tv_title);
		uiAdapter.setTextSize(tv_title, Constants.TitleSize);
		if(data!= null){
			tv_title.setText(data.getCategoryName());
		}else{
			tv_title.setText("全部商品");
		}

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
		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				// TODO Auto-generated method stub
				CategoriesGoods goods = (CategoriesGoods) parent.getAdapter().getItem(position);
				LogoStoreDetailFragment detailFragment = new LogoStoreDetailFragment();
				Bundle bundle = new Bundle();  
                bundle.putSerializable("detail", goods);
                detailFragment.setArguments(bundle); 
                MainActivity.mActivity.replaceChildFragment("StoreDetailFragment",detailFragment,true);
			}
		});
		listView.setAdapter(categoriseAllAdapter = new CategoriseAllAdapter());
		if(data != null && data.getGoodsList() != null){
			categoriseAllAdapter.setData(data.getGoodsList());
		}
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
				holder.txt_content = (TextView) convertView.findViewById(R.id.txt_content);
				holder.img = (ImageView) convertView.findViewById(R.id.img);
				
				convertView.setTag(holder);
			} else
				holder = (ViewHolder) convertView.getTag();

			CategoriesGoods data = (CategoriesGoods) getItem(position);
			if (data != null) {
				
				holder.txt_content.setText(data.getComName()+ "");
				if (TextUtils.isEmpty(data.getPicture())) {
					holder.img.setImageResource(R.drawable.img_default);
				} else {
					ImageLoader.getInstance().displayImage(Constants.IMGSURL + data.getPicture(), holder.img);
				}
			}
			return convertView;
		}

		class ViewHolder {
			TextView txt_content;
			ImageView img;
		}

	}

}
