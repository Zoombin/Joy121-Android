package com.joy.Fragment;

import gejw.android.quickandroid.QFragment;
import gejw.android.quickandroid.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.joy.R;
import com.joy.Activity.OrderDetailActivity;
import com.joy.Utils.Constants;
import com.joy.Widget.WelfareAdapter;
import com.joy.json.JsonCommon;
import com.joy.json.JsonCommon.OnOperationListener;
import com.joy.json.model.CommoditySet;
import com.joy.json.model.WelfareEntity;
import com.joy.json.operation.OperationBuilder;
import com.joy.json.operation.impl.Fp_benefitOp;

/**
 * 公司福利
 * 
 * @author daiye
 * 
 */
public class WelfareFragment extends QFragment {

	private RelativeLayout layout_title;
	private ImageView iv_title;
	private TextView tv_title;
	private ListView mListView;
	private WelfareAdapter mAdapter;
	WelfareFragment self;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		self = this;
		View v = inflater.inflate(R.layout.fragment_welfare, container, false);
		initView(v);
		
		getWelfareList();
		return v;
	}

	private void initView(View v) {
		layout_title = (RelativeLayout) v.findViewById(R.id.layout_title);
		uiAdapter.setMargin(layout_title, LayoutParams.MATCH_PARENT, Constants.TitleHeight, 0, 0, 0, 0);
		
		iv_title = (ImageView) v.findViewById(R.id.iv_title);
		uiAdapter.setMargin(iv_title, Constants.TitleIvWidth, Constants.TitleIvWidth, 10, 0, 10, 0);
		
		tv_title = (TextView) v.findViewById(R.id.tv_title);
		uiAdapter.setTextSize(tv_title, Constants.TitleSize);
		
		mListView = (ListView) v.findViewById(R.id.list_welfare);
		mAdapter = new WelfareAdapter(mActivity);
		
		mListView.setAdapter(mAdapter);
	}
	
	/**
	 * 获取接口数据
	 */
	private void getWelfareList() {
		OperationBuilder builder = new OperationBuilder().append(
				new Fp_benefitOp(), null);
		OnOperationListener listener = new OnOperationListener() {
			@Override
			public void onOperationFinished(List<Object> resList) {
				if (!self.isResumed()) {
					return;
				}
				if (resList == null) {
					Toast.show(mActivity, "连接超时");
					return;
				}
				WelfareEntity entity = (WelfareEntity) resList.get(0);
				List<CommoditySet> commoditySetlist = entity.getRetobj();
				if (commoditySetlist == null) {
					Toast.show(mActivity, "无法取得数据！");
					return;
				} else {
					commoditySetlist = getCommoditySetList(commoditySetlist);
					for (CommoditySet commoditySet : commoditySetlist) {
						mAdapter.addItem(commoditySet);
					}
					mAdapter.notifyDataSetChanged();
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
	
	private List<CommoditySet> getCommoditySetList(List<CommoditySet> commoditySetlist) {
		List<CommoditySet> commoditysetlist = new ArrayList<CommoditySet>();
		String type = "-1";
		for (CommoditySet commoditySet : commoditySetlist) {
			String setType = commoditySet.getSetType();
			if (!setType.equals(type)) {
				CommoditySet tempcommoditySet = new CommoditySet();
				tempcommoditySet.setSetName(commoditySet.getTypeName());
				tempcommoditySet.setStartDate(commoditySet.getStartDate().substring(6, 19));
				tempcommoditySet.setEXPIREDDATE(commoditySet.getEXPIREDDATE().substring(6, 19));
				tempcommoditySet.setSetType(null);
				commoditysetlist.add(tempcommoditySet);
				type = setType;
			}
			commoditysetlist.add(commoditySet);
		}
		return commoditysetlist;
	}
}
