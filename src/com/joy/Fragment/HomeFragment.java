package com.joy.Fragment;

import gejw.android.quickandroid.QFragment;

import java.util.ArrayList;

import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.joy.R;
import com.joy.Widget.GridViewAdapter;
import com.joy.Widget.GridViewEntity;
import com.joy.Widget.WelfareAdapter;

/**
 * 订单跟踪模块
 * 
 * @author daiye
 * 
 */
public class HomeFragment extends QFragment {

	private RelativeLayout layout_title;
	private TextView tv_title;
	private GridView grid_select;
	private ListView mListView;
	private WelfareAdapter mAdapter;
	private Resources resources;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_home, container, false);
		
		resources = getResources();
		
		initView(v);
		return v;
	}

	private void initView(View v) {
		layout_title = (RelativeLayout) v.findViewById(R.id.layout_title);
		uiAdapter.setMargin(layout_title, LayoutParams.MATCH_PARENT, 74, 0, 0, 0, 0);
		
		tv_title = (TextView) v.findViewById(R.id.tv_title);
		uiAdapter.setTextSize(tv_title, 20);
		
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
		holidaywelfare.setName(resources.getString(R.string.grid_holidaywelfare));
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
}
