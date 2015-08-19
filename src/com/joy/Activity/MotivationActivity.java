package com.joy.Activity;


/**
<<<<<<< HEAD
 * 员工激励
=======
 * 绩效考核
>>>>>>> origin/master
 * @author ryan zhou 2015-01-12
 *
 */


import gejw.android.quickandroid.widget.Toast;

import java.util.List;

import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.joy.R;
import com.joy.Utils.Constants;
import com.joy.Widget.MotivationAdapter;
import com.joy.json.JsonCommon;
import com.joy.json.JsonCommon.OnOperationListener;
import com.joy.json.model.PerformanceEntity;
import com.joy.json.model.PerformanceListEntity;
import com.joy.json.operation.OperationBuilder;
import com.joy.json.operation.impl.PerformanceOp;
import com.umeng.analytics.MobclickAgent;

public class MotivationActivity extends BaseActivity implements OnClickListener {


	private RelativeLayout layout_title;
	private ImageView iv_ret;
	private TextView tv_title;
	private RelativeLayout layout_prompt;
	private ImageView iv_prompt;
	private ListView list_motivation;
	private MotivationAdapter adapter;
	private Resources resources;
	
	/*@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_survey);
		resources = getResources();
		initView();
		initData("1");
	}*/
	
	@Override
	protected View ceateView(LayoutInflater inflater, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View v = inflater.inflate(R.layout.activity_motivation, null);
		resources = getResources();
		setContentView(v);
		initView();
		initData();
		return v;
	}
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.iv_ret:
			finish();
			break;
		case R.id.iv_prompt:
			layout_prompt.setVisibility(View.GONE);
			break;
		default:
			break;
		}
	}
	
	private void initView() {
		//设置页眉
		layout_title = (RelativeLayout) findViewById(R.id.layout_title);
		uiAdapter.setMargin(layout_title, LayoutParams.MATCH_PARENT, Constants.SubTitleHeight, 0, 0,
				0, 0);
		iv_ret = (ImageView) findViewById(R.id.iv_ret);
		iv_ret.setOnClickListener(this);

		tv_title = (TextView) findViewById(R.id.tv_title);
		uiAdapter.setTextSize(tv_title, Constants.TitleSize);
		tv_title.setText(R.string.motivation);//绩效考核&员工激励
		
		layout_prompt = (RelativeLayout) findViewById(R.id.layout_prompt);
		layout_prompt.setBackgroundColor(Color.parseColor(appSet.getColor2()));
		iv_prompt = (ImageView) findViewById(R.id.iv_prompt);
		iv_prompt.setOnClickListener(this);

		list_motivation = (ListView) findViewById(R.id.list_motivation);
		uiAdapter.setMargin(list_motivation, LayoutParams.MATCH_PARENT,
				LayoutParams.WRAP_CONTENT, 0, 0, 0, 0);
		adapter = new MotivationAdapter(self, self);
		list_motivation.setAdapter(adapter);
	}
	
	private void initData() {
		String reportType = "1";//类型：激励
		OperationBuilder builder = new OperationBuilder().append(new PerformanceOp(),
				reportType);
		OnOperationListener listener = new OnOperationListener() {
			@Override
			public void onOperationFinished(List<Object> resList) {
				if (self.isFinishing()) {
					return;
				}
				if (resList == null) {
					Toast.show(self, resources.getString(R.string.timeout));
					return;
				}
				PerformanceListEntity entity = (PerformanceListEntity) resList.get(0);
				List<PerformanceEntity> performanceEntityList = entity.getRetobj();
				if (performanceEntityList == null || performanceEntityList.size() == 0) {
					layout_prompt.setVisibility(View.VISIBLE);
					//finish();
					return;
				}
				layout_prompt.setVisibility(View.GONE);
				for (PerformanceEntity entity1 : performanceEntityList) {
					adapter.addItem(entity1);
				}
				adapter.notifyDataSetChanged();
			}

			@Override
			public void onOperationError(Exception e) {
				e.printStackTrace();
			}
		};
		JsonCommon task = new JsonCommon(self, builder, listener,
				JsonCommon.PROGRESSQUERY);
		task.execute();
	}
	
	//重调接口刷新数据
	public void reLoad(){

	}
	
	public void onResume() {
		super.onResume();
		MobclickAgent.onResume(this);
	}

	public void onPause() {
		super.onPause();
		MobclickAgent.onPause(this);
	}

}
