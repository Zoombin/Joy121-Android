package com.joy.Fragment.portals.welfare;

import gejw.android.quickandroid.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
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
	private ImageView iv_ret;
	private TextView tv_title;
	private RelativeLayout layout_prompt;
	private ImageView iv_prompt;
	private ListView mListView;
	private WelfareAdapter mAdapter;
	private 
	List<CommoditySet> tempList ;
	
	/*@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View v= inflater.inflate(R.layout.activity_welfare, container,false);
		initView(v);
		return v;
	}*/
	
	@Override
	protected View initContentView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View v= inflater.inflate(R.layout.activity_welfare, container,false);
		initView(v);
		return v;
	}
	
	
	//显示标题，如果没有内容，显示tips条
	private void initView(View v) {
		layout_title = (RelativeLayout) v.findViewById(R.id.layout_title);
		uiAdapter.setMargin(layout_title, LayoutParams.MATCH_PARENT, Constants.TitleHeight, 0, 0,
				0, 0);  //标题的位置

		iv_ret = (ImageView) v.findViewById(R.id.iv_ret);
		iv_ret.setOnClickListener(this);

		tv_title = (TextView) v.findViewById(R.id.tv_title);
		uiAdapter.setTextSize(tv_title, Constants.TitleSize);
<<<<<<< HEAD
=======
<<<<<<< HEAD
>>>>>>> bfc1dd98ae18f2af35989a913a31b9cdaa58e226
		tv_title.setText(R.string.row_welfare);//显示标题“公司福利”
		
		layout_prompt = (RelativeLayout) v.findViewById(R.id.layout_prompt);
		layout_prompt.setBackgroundColor(Color.parseColor(appSet.getColor2()));//显示tips提示背景
		iv_prompt = (ImageView) v.findViewById(R.id.iv_prompt);//响应tips的叉号
<<<<<<< HEAD
=======
=======
		tv_title.setText(R.string.row_welfare);//工资单
		
		layout_prompt = (RelativeLayout) v.findViewById(R.id.layout_prompt);
		layout_prompt.setBackgroundColor(Color.parseColor(appSet.getColor2()));
		iv_prompt = (ImageView) v.findViewById(R.id.iv_prompt);
>>>>>>> 34acdd014449076be19c67258f14caec9568e50d
>>>>>>> bfc1dd98ae18f2af35989a913a31b9cdaa58e226
		iv_prompt.setOnClickListener(this);
		
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
			setAdapterData(tempList);
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
				if (commoditySetlist == null || commoditySetlist.size() == 0) {
					layout_prompt.setVisibility(View.VISIBLE);
					return;
				} else {
					commoditySetlist = getCommoditySetList(commoditySetlist);
					/*for (CommoditySet commoditySet : commoditySetlist) {
						mAdapter.addItem(commoditySet);
					}
					mAdapter.notifyDataSetChanged();*/
					tempList = commoditySetlist;
					setAdapterData(commoditySetlist);
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
	
	private void setAdapterData(List<CommoditySet> list){
		int color = 0;
		if(appSet != null){
			try {
				color = Color.parseColor(appSet.getColor2());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		mAdapter.setBtColor(color);
		mAdapter.setData(list);
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
		case R.id.iv_ret:
			MainActivity.mActivity.Back();
			break;
		case R.id.iv_prompt:
			layout_prompt.setVisibility(View.GONE);
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
