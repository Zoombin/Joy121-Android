package com.joy.Fragment.portals.welfare;

import gejw.android.quickandroid.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.joy.R;
import com.joy.Activity.MainActivity;
import com.joy.Fragment.BaseFragment;
import com.joy.Utils.Constants;
import com.joy.Widget.WelfareAdapter;
import com.joy.json.JsonCommon;
import com.joy.json.JsonCommon.OnOperationListener;
import com.joy.json.model.CommoditySet;
import com.joy.json.model.WelfareEntity;
import com.joy.json.operation.OperationBuilder;
import com.joy.json.operation.impl.Fp_benefitOp;
import com.umeng.analytics.MobclickAgent;


/***
 * 公司福利
 * @author lsd
 *
 */
public class WelfareFragment extends BaseFragment implements OnClickListener{
	private RelativeLayout layout_title;
	private TextView tv_ret;
	private TextView tv_title;
	private ListView mListView;
	private WelfareAdapter mAdapter;
	List<CommoditySet> tempList ;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View v= inflater.inflate(R.layout.activity_welfare, container,false);
		initView(v);
		return v;
	}
	
	
	private void initView(View v) {
		layout_title = (RelativeLayout) v.findViewById(R.id.layout_title);
		uiAdapter.setMargin(layout_title, LayoutParams.MATCH_PARENT, Constants.TitleHeight, 0, 0,
				0, 0);

		tv_ret = (TextView) v.findViewById(R.id.tv_ret);
		tv_ret.setOnClickListener(this);
		uiAdapter.setTextSize(tv_ret, Constants.TitleRetSize);
		uiAdapter.setMargin(tv_ret, LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT, 20, 0, 0, 0);

		tv_title = (TextView) v.findViewById(R.id.tv_title);
		uiAdapter.setTextSize(tv_title, Constants.TitleSize);
		
		mListView = (ListView) v.findViewById(R.id.list_welfare);
		mAdapter = new WelfareAdapter(mActivity);
		
		mListView.setAdapter(mAdapter);
		
		getWelfareList();
	}
	
	/**
	 * 获取接口数据
	 */
	private void getWelfareList() {
		if(tempList != null){
			mAdapter.setData(tempList);
			return;
		}
		OperationBuilder builder = new OperationBuilder().append(
				new Fp_benefitOp(), null);
		OnOperationListener listener = new OnOperationListener() {
			@Override
			public void onOperationFinished(List<Object> resList) {
				if (mActivity.isFinishing()) {
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
					/*for (CommoditySet commoditySet : commoditySetlist) {
						mAdapter.addItem(commoditySet);
					}
					mAdapter.notifyDataSetChanged();*/
					tempList = commoditySetlist;
					mAdapter.setData(commoditySetlist);
				}
			}

			@Override
			public void onOperationError(Exception e) {
				e.printStackTrace();
			}
		};

		JsonCommon task = new JsonCommon(mActivity, builder, listener, JsonCommon.PROGRESSQUERY);
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

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.tv_ret:
			MainActivity.mActivity.Back();
			break;

		default:
			break;
		}
	}
	
	public void onResume() {
		super.onResume();
		///MobclickAgent.onResume(this);
	}

	public void onPause() {
		super.onPause();
		///MobclickAgent.onPause(this);
	}
}
