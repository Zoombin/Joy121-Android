package com.joy.Fragment;

import gejw.android.quickandroid.QFragment;
import gejw.android.quickandroid.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.joy.R;
import com.joy.Activity.OrderqueryActivity;
import com.joy.Activity.WelfareActivity;
import com.joy.Utils.Constants;
import com.joy.Widget.GridViewAdapter;
import com.joy.Widget.GridViewEntity;
import com.joy.Widget.HomeWelfareGridViewAdapter;
import com.joy.Widget.PagerviewAdapter;
import com.joy.json.JsonCommon;
import com.joy.json.JsonCommon.OnOperationListener;
import com.joy.json.model.CommoditySet;
import com.joy.json.model.PicEntity;
import com.joy.json.model.WelfareEntity;
import com.joy.json.operation.OperationBuilder;
import com.joy.json.operation.impl.Fp_benefitOp;
import com.joy.json.operation.impl.PicOp;

/**
 * 首页模块
 * 
 * @author daiye
 * 
 */
public class HomeFragment extends BaseFragment implements OnClickListener, OnItemClickListener {

	private RelativeLayout layout_title;
	private ImageView iv_title;
	private TextView tv_title;
	private ImageView iv_phone;
	private ViewPager viewpager;
	private LinearLayout mNumLayout;
	private GridView grid_select;
	private TextView tv_mywelfare;
	private GridView grid_welfare;
	private Resources resources;
	Button mPreSelectedBt;

	/*@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_home, container, false);

		resources = getResources();
		
		initView(v);

		getPicList();
		getWelfareList();
		return v;
	}*/
	
	@Override
	protected View initContentView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View v = inflater.inflate(R.layout.fragment_home, container, false);

		resources = getResources();
		
		initView(v);

		getPicList();
		getWelfareList();
		return v;
	}

	private void initView(View v) {
		layout_title = (RelativeLayout) v.findViewById(R.id.layout_title);
		uiAdapter.setMargin(layout_title, LayoutParams.MATCH_PARENT, Constants.TitleHeight, 0, 0,
				0, 0);

		iv_title = (ImageView) v.findViewById(R.id.iv_title);
		uiAdapter.setMargin(iv_title, Constants.TitleIvWidth, Constants.TitleIvWidth, 10, 0, 10, 0);
		
		tv_title = (TextView) v.findViewById(R.id.tv_title);
		uiAdapter.setTextSize(tv_title, Constants.TitleSize);

		iv_phone = (ImageView) v.findViewById(R.id.iv_phone);
		uiAdapter.setMargin(iv_phone, 28, 28, 0, 0, 30, 0);
		iv_phone.setOnClickListener(this);
		
		viewpager = (ViewPager) v.findViewById(R.id.viewpager);
		uiAdapter.setMargin(viewpager, LayoutParams.MATCH_PARENT, 228, 0, 0, 0,
				0);

		mNumLayout = (LinearLayout) v.findViewById(R.id.ll_pager_num);
		uiAdapter.setMargin(mNumLayout, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 0, 190, 0, 0);
		
		grid_select = (GridView) v.findViewById(R.id.grid_select);
		uiAdapter.setMargin(grid_select, LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT, 0, 10, 0, 0);
		ArrayList<GridViewEntity> data = new ArrayList<GridViewEntity>();

		// onlineinquery
		GridViewEntity onlineinquery = new GridViewEntity();
		onlineinquery.setIcon(R.drawable.onlineinquery);
		onlineinquery.setName(resources.getString(R.string.grid_onlineinquery));
		data.add(onlineinquery);

		// holidaywelfare
		GridViewEntity holidaywelfare = new GridViewEntity();
		holidaywelfare.setIcon(R.drawable.holidaywelfare);
		holidaywelfare.setName(resources
				.getString(R.string.grid_holidaywelfare));
		data.add(holidaywelfare);

		// sale
		GridViewEntity sale = new GridViewEntity();
		sale.setIcon(R.drawable.sale);
		sale.setName(resources.getString(R.string.grid_sale));
		data.add(sale);

		// orderquery
		GridViewEntity orderquery = new GridViewEntity();
		orderquery.setIcon(R.drawable.orderquery);
		orderquery.setName(resources.getString(R.string.grid_orderquery));
		data.add(orderquery);

		grid_select.setNumColumns(4);
		grid_select.setAdapter(new GridViewAdapter(mActivity, data));
		grid_select.setOnItemClickListener(this);
		
		tv_mywelfare = (TextView) v.findViewById(R.id.tv_mywelfare);
		uiAdapter.setMargin(tv_mywelfare, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 0, 10, 0, 10);
		uiAdapter.setPadding(tv_mywelfare, 40, 5, 40, 5);
		
		grid_welfare = (GridView) v.findViewById(R.id.grid_welfare);
		grid_welfare.setNumColumns(2);
	}
	

	private void getWelfareList() {
		OperationBuilder builder = new OperationBuilder().append(
				new Fp_benefitOp(), null);
		OnOperationListener listener = new OnOperationListener() {
			@Override
			public void onOperationFinished(List<Object> resList) {
				if (mActivity.isFinishing()) {
					return;
				}
				if (resList == null) {
					Toast.show(mActivity, getResources().getString(R.string.timeout));
					return;
				}
				WelfareEntity entity = (WelfareEntity) resList.get(0);
				List<CommoditySet> commoditySetlist = entity.getRetobj();
				if (commoditySetlist == null || commoditySetlist.size() == 0) {
					Toast.show(mActivity, getResources().getString(R.string.nodatagetted));
					return;
				} else {
					grid_welfare.setAdapter(new HomeWelfareGridViewAdapter(mActivity, commoditySetlist));
				}
			}

			@Override
			public void onOperationError(Exception e) {
				e.printStackTrace();
			}
		};

		JsonCommon task = new JsonCommon(mActivity, builder, listener,
				false);
		task.execute();
	}
	
	private void getPicList() {
		OperationBuilder builder = new OperationBuilder().append(new PicOp(),
				null);
		OnOperationListener listener = new OnOperationListener() {
			@Override
			public void onOperationFinished(List<Object> resList) {
				if (mActivity.isFinishing()) {
					return;
				}
				if (resList == null) {
					// Toast.show(mActivity, "连接超时");
					return;
				}
				PicEntity entity = (PicEntity) resList.get(0);
				List<String> piclist = entity.getRetobj();
				if (piclist != null) {
					setviewpager(piclist);
				}
			}

			@Override
			public void onOperationError(Exception e) {
				e.printStackTrace();
			}
		};
		JsonCommon task = new JsonCommon(mActivity, builder, listener, false);
		task.execute();
	}

	private void setviewpager(List<String> piclist) {
		PagerviewAdapter pagerviewAdapter = new PagerviewAdapter(mActivity,
				piclist);
		for (int i = 0; i < piclist.size(); i++) {
			Button bt = new Button(mActivity);
			bt.setLayoutParams(new ViewGroup.LayoutParams(uiAdapter.CalcWidth(10), uiAdapter.CalcWidth(10)));
			bt.setBackgroundResource(R.drawable.point);
			mNumLayout.addView(bt);
		}
		
		viewpager.setAdapter(pagerviewAdapter);
		viewpager.setOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageSelected(int position) {
				if (mPreSelectedBt != null) {
					mPreSelectedBt
							.setBackgroundResource(R.drawable.point);
				}

				Button currentBt = (Button) mNumLayout.getChildAt(position);
				currentBt
						.setBackgroundResource(R.drawable.point_press);
				uiAdapter.setMargin(currentBt, 12, 12, 5, 0, 5, 0);
				mPreSelectedBt = currentBt;
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onPageScrollStateChanged(int arg0) {
				// TODO Auto-generated method stub

			}
		});
		new Handler().post(r);
	}

	Runnable r = new Runnable() {
		int i = 0;

		@Override
		public void run() {
			if (i > viewpager.getChildCount()) {
				i = 0;
			}
			viewpager.setCurrentItem(i);
			i++;
			new Handler().postDelayed(r, 5000);
		}
	};

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.iv_phone:
			// 跳转到拨号面板
			Uri uri = Uri.parse("tel:4008558121");
			Intent intent = new Intent(Intent.ACTION_DIAL, uri);     
			startActivity(intent);
			break;
		default:
			break;
		}
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		Intent intent = new Intent();
		switch (arg2) {
		case 0:

			break;
		case 1:
			intent.setClass(mActivity, WelfareActivity.class);
			startActivity(intent);
			break;
		case 2:
			
			break;
		case 3:
			intent.setClass(mActivity, OrderqueryActivity.class);
			startActivity(intent);
			break;
		default:
			break;
		}
	}
}
