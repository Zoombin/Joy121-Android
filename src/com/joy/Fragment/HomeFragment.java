package com.joy.Fragment;

import gejw.android.quickandroid.QFragment;

import java.util.ArrayList;
import java.util.List;

import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.joy.R;
import com.joy.Utils.Constants;
import com.joy.Widget.GridViewAdapter;
import com.joy.Widget.GridViewEntity;
import com.joy.Widget.PagerviewAdapter;
import com.joy.Widget.WelfareAdapter;
import com.joy.json.JsonCommon;
import com.joy.json.JsonCommon.OnOperationListener;
import com.joy.json.model.PicEntity;
import com.joy.json.operation.OperationBuilder;
import com.joy.json.operation.impl.PicOp;

/**
 * 订单跟踪模块
 * 
 * @author daiye
 * 
 */
public class HomeFragment extends QFragment {

	private RelativeLayout layout_title;
	private TextView tv_title;
	private ViewPager viewpager;
	private LinearLayout mNumLayout;
	private GridView grid_select;
	private ListView mListView;
	private WelfareAdapter mAdapter;
	private Resources resources;
	Button mPreSelectedBt;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_home, container, false);

		resources = getResources();

		initView(v);

		getPicList();
		getWelfareList();
		return v;
	}

	private void initView(View v) {
		layout_title = (RelativeLayout) v.findViewById(R.id.layout_title);
		uiAdapter.setMargin(layout_title, LayoutParams.MATCH_PARENT, 74, 0, 0,
				0, 0);

		tv_title = (TextView) v.findViewById(R.id.tv_title);
		uiAdapter.setTextSize(tv_title, Constants.TitleSize);

		viewpager = (ViewPager) v.findViewById(R.id.viewpager);
		uiAdapter.setMargin(viewpager, LayoutParams.MATCH_PARENT, 280, 0, 0, 0,
				0);

		mNumLayout = (LinearLayout) v.findViewById(R.id.ll_pager_num);
		uiAdapter.setMargin(mNumLayout, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 0, 250, 0, 0);
		
		grid_select = (GridView) v.findViewById(R.id.grid_select);

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
		sale.setIcon(R.drawable.orderquery);
		sale.setName(resources.getString(R.string.grid_sale));
		data.add(sale);

		// orderquery
		GridViewEntity orderquery = new GridViewEntity();
		orderquery.setIcon(R.drawable.orderquery);
		orderquery.setName(resources.getString(R.string.grid_orderquery));
		data.add(orderquery);

		grid_select.setNumColumns(4);
		grid_select.setAdapter(new GridViewAdapter(mActivity, data));

		mListView = (ListView) v.findViewById(R.id.list_welfare);
		mAdapter = new WelfareAdapter(mActivity);

		mListView.setAdapter(mAdapter);

		mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

			}
		});
	}

	private void getWelfareList() {
		
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
			bt.setLayoutParams(new ViewGroup.LayoutParams(uiAdapter.CalcWidth(20), uiAdapter.CalcWidth(20)));
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
}
