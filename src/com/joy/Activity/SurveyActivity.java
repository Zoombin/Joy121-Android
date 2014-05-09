package com.joy.Activity;

import gejw.android.quickandroid.QActivity;
import gejw.android.quickandroid.widget.Toast;

import java.util.List;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.joy.R;
import com.joy.Utils.Constants;
import com.joy.Widget.SurveyAdapter;
import com.joy.json.JsonCommon;
import com.joy.json.JsonCommon.OnOperationListener;
import com.joy.json.model.SurveyDetailEntity;
import com.joy.json.model.SurveyEntity;
import com.joy.json.operation.OperationBuilder;
import com.joy.json.operation.impl.SurveyOp;
import com.umeng.analytics.MobclickAgent;

/**
 * 调查列表
 * @author daiye
 *
 */
public class SurveyActivity extends QActivity implements OnClickListener {
	
	private RelativeLayout layout_title;
	private TextView tv_ret;
	private TextView tv_title;
	private ListView list_survey;
	private SurveyAdapter adapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_survey);
		
		initView();
		initData();
	}

	private void initView() {
		layout_title = (RelativeLayout) findViewById(R.id.layout_title);
		uiAdapter.setMargin(layout_title, LayoutParams.MATCH_PARENT, Constants.TitleHeight, 0, 0,
				0, 0);

		tv_ret = (TextView) findViewById(R.id.tv_ret);
		tv_ret.setOnClickListener(this);
		uiAdapter.setTextSize(tv_ret, Constants.TitleRetSize);
		uiAdapter.setMargin(tv_ret, LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT, 20, 0, 0, 0);

		tv_title = (TextView) findViewById(R.id.tv_title);
		uiAdapter.setTextSize(tv_title, Constants.TitleSize);

		list_survey = (ListView) findViewById(R.id.list_survey);
		uiAdapter.setMargin(list_survey, LayoutParams.MATCH_PARENT,
				LayoutParams.WRAP_CONTENT, 0, 0, 0, 0);
		adapter = new SurveyAdapter(self, self);
		list_survey.setAdapter(adapter);
	}

	private void initData() {
		OperationBuilder builder = new OperationBuilder().append(new SurveyOp(),
				null);
		OnOperationListener listener = new OnOperationListener() {
			@Override
			public void onOperationFinished(List<Object> resList) {
				if (self.isFinishing()) {
					return;
				}
				if (resList == null) {
					Toast.show(self, "连接超时");
					return;
				}
				SurveyEntity entity = (SurveyEntity) resList.get(0);
				List<SurveyDetailEntity> surveylist = entity.getRetobj();
				if (surveylist == null) {
					Toast.show(self, "没有调查信息！");
					finish();
					return;
				}
				for (SurveyDetailEntity entity1 : surveylist) {
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

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.tv_ret:
			finish();
			break;

		default:
			break;
		}
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
