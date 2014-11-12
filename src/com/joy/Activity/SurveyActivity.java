package com.joy.Activity;

import gejw.android.quickandroid.QActivity;
import gejw.android.quickandroid.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.joy.JoyApplication;
import com.joy.R;
import com.joy.Utils.Constants;
import com.joy.Widget.SurveyAdapter;
import com.joy.json.JsonCommon;
import com.joy.json.JsonCommon.OnOperationListener;
import com.joy.json.model.ActivityEntity;
import com.joy.json.model.CompAppSet;
import com.joy.json.model.SelectionModel;
import com.joy.json.model.SurveyDetailEntity;
import com.joy.json.model.SurveyEntity;
import com.joy.json.model.CategoriesGoodsDEntity.CategoriesGoods;
import com.joy.json.model.StoreDetailEntity.StoreDetail;
import com.joy.json.operation.OperationBuilder;
import com.joy.json.operation.impl.SurveyOp;
import com.umeng.analytics.MobclickAgent;

/**
 * 调查列表
 * @author daiye
 *
 */
public class SurveyActivity extends BaseActivity implements OnClickListener {
	
	private RelativeLayout layout_title;
	private TextView tv_ret;
	private TextView tv_title;
	private ListView list_survey;
	private SurveyAdapter adapter;
	
	private LinearLayout layout_menu;
	private LinearLayout layout_useful;
	private TextView tv_useful;
	private LinearLayout layout_expired;
	private TextView tv_expired;
	private Resources resources;
	CompAppSet appSet;
	int color;
	
	
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
		color = Color.parseColor("#ffa800");
		appSet = JoyApplication.getInstance().getCompAppSet();
		if (appSet != null) {
			try {
				color = Color.parseColor(appSet.getColor2());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		View v = inflater.inflate(R.layout.activity_survey, null);
		setContentView(v);
		resources = getResources();
		initView();
		initData("1",true);
		return v;
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
		tv_title.setText(R.string.row_suivey);//调查

		list_survey = (ListView) findViewById(R.id.list_survey);
		uiAdapter.setMargin(list_survey, LayoutParams.MATCH_PARENT,
				LayoutParams.WRAP_CONTENT, 0, 0, 0, 0);
		adapter = new SurveyAdapter(self, self);
		list_survey.setAdapter(adapter);
		
		layout_menu = (LinearLayout) findViewById(R.id.layout_menu);
		
		layout_useful = (LinearLayout) findViewById(R.id.layout_useful);
		layout_useful.setOnClickListener(this);
		
		tv_useful = (TextView) findViewById(R.id.tv_useful);
		
		layout_expired = (LinearLayout) findViewById(R.id.layout_expired);
		layout_expired.setOnClickListener(this);
		
		tv_expired = (TextView) findViewById(R.id.tv_expired);
		
		defaultColor();
	}
	
	private void defaultColor()
	{
		layout_useful.setBackgroundColor(color);
		layout_expired.setBackgroundColor(getResources().getColor(R.color.btn_disable));
		tv_useful.setTextColor(resources.getColor(R.color.WHITE));
		tv_expired.setTextColor(resources.getColor(R.color.WHITE));
	}


	public void initData(final String isexpired,boolean pro) {
		
		SurveyEntity sur = new SurveyEntity();
		sur.isexpired = isexpired;

		OperationBuilder builder = new OperationBuilder().append(new SurveyOp(),
				sur);
		OnOperationListener listener = new OnOperationListener() {
			@Override
			public void onOperationFinished(List<Object> resList) {
				if (self.isFinishing()) {
					return;
				}
				if (resList == null) {
					Toast.show(self, getResources().getString(R.string.timeout));
					return;
				}
				SurveyEntity entity = (SurveyEntity) resList.get(0);
				List<SurveyDetailEntity> surveylist = entity.getRetobj();
				if (surveylist == null || surveylist.size() == 0) {
					Toast.show(self, getResources().getString(R.string.nosurveyinfo));
					finish();
					return;
				}
				for (SurveyDetailEntity entity1 : surveylist) {
					adapter.addItem(entity1);
				}
				adapter.setType(isexpired);
				adapter.notifyDataSetChanged();
			}

			@Override
			public void onOperationError(Exception e) {
				e.printStackTrace();
			}
		};

		JsonCommon task = null;
		if(pro){
			task = new JsonCommon(self, builder, listener,
					JsonCommon.PROGRESSQUERY);
		}else{
			task = new JsonCommon(self, builder, listener,
					false);
		}
		task.execute();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.layout_useful:
		case R.id.layout_expired:
			showMenu(v.getId());
			break;
		case R.id.tv_ret:
			finish();
			break;
		default:
			break;
		}
	}
	
	private void showMenu(int layout) {
		switch (layout) {
		case R.id.layout_useful:
			layout_useful.setBackgroundColor(color);
			layout_expired.setBackgroundColor(getResources().getColor(R.color.btn_disable));
			tv_useful.setTextColor(resources.getColor(R.color.WHITE));
			tv_expired.setTextColor(resources.getColor(R.color.WHITE));
			adapter.removeAll();
			initData("1",true);
			break;
		case R.id.layout_expired:
			layout_expired.setBackgroundColor(color);
			layout_useful.setBackgroundColor(getResources().getColor(R.color.btn_disable));
			tv_expired.setTextColor(resources.getColor(R.color.WHITE));
			tv_useful.setTextColor(resources.getColor(R.color.WHITE));
			adapter.removeAll();
			initData("2",true);
			break;
		default:
			break;
		}
	}
	
	//重调接口刷新数据
	public void reLoad(){
		layout_useful.setBackgroundColor(color);
		layout_expired.setBackgroundColor(getResources().getColor(R.color.btn_disable));
		tv_useful.setTextColor(resources.getColor(R.color.WHITE));
		tv_expired.setTextColor(resources.getColor(R.color.WHITE));
		adapter.removeAll();
		initData("1",false);
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
