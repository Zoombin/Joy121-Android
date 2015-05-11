package com.joy.Activity;

import gejw.android.quickandroid.widget.Toast;

import java.util.List;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.joy.JoyApplication;
import com.joy.R;
import com.joy.Utils.Constants;
import com.joy.Widget.ActivityAdapter;
import com.joy.json.JsonCommon;
import com.joy.json.JsonCommon.OnOperationListener;
import com.joy.json.model.ActivityDetailEntity;
import com.joy.json.model.ActivityEntity;
import com.joy.json.model.CompAppSet;
import com.joy.json.operation.OperationBuilder;
import com.joy.json.operation.impl.ActivityOp;
import com.umeng.analytics.MobclickAgent;

public class ActivityActivity extends BaseActivity implements OnClickListener {
	
	private RelativeLayout layout_title;
	private ImageView iv_ret;
	private TextView tv_title;
	private ListView list_activity;
	private ActivityAdapter adapter;
	
	private LinearLayout layout_menu;
	private LinearLayout layout_useful;
	private TextView tv_useful;
	private View line_useful;
	private LinearLayout layout_expired;
	private TextView tv_expired;
	private View line_expired;
	private Resources resources;
	
	public String acttype;
	CompAppSet appSet;
	int color;
	private String isSelect;//表示当时选中的时候哪个选项 onResume的时候刷新数据
	
	/*@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_activity);
		resources = getResources();
		Intent intent = getIntent();
	    acttype = intent.getStringExtra("acttype");
		
		initView();
		initData("1");
	}*/
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		isSelect = "1";
	}
	
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
		View v = inflater.inflate(R.layout.activity_activity, null);
		setContentView(v);
		resources = getResources();
		Intent intent = getIntent();
	    acttype = intent.getStringExtra("acttype");
		
		initView();
		return v;
	}
	
	public void onResume() {
		super.onResume();
		MobclickAgent.onResume(this);
		
		//刷新数据
		adapter.removeAll();
		adapter.notifyDataSetChanged();
		initData(isSelect,true);
	}
	
	public void reLoad() {
		// 刷新数据
		adapter.removeAll();
		initData(isSelect,true);
	}
	

	private void initView() {
		layout_title = (RelativeLayout) findViewById(R.id.layout_title);
		uiAdapter.setMargin(layout_title, LayoutParams.MATCH_PARENT, Constants.SubTitleHeight, 0, 0,
				0, 0);

		iv_ret = (ImageView) findViewById(R.id.iv_ret);
		iv_ret.setOnClickListener(this);

		tv_title = (TextView) findViewById(R.id.tv_title);
		uiAdapter.setTextSize(tv_title, Constants.TitleSize);
		if("1".equals(acttype)){
			tv_title.setText(R.string.row_activity);//活动
		}else if("2".equals(acttype)){
			tv_title.setText(R.string.row_train);//培训
		}

		list_activity = (ListView) findViewById(R.id.list_activity);
		uiAdapter.setMargin(list_activity, LayoutParams.MATCH_PARENT,
				LayoutParams.WRAP_CONTENT, 0, 0, 0, 0);
		adapter = new ActivityAdapter(self, self);
		list_activity.setAdapter(adapter);
		
		layout_menu = (LinearLayout) findViewById(R.id.layout_menu);
		
		layout_useful = (LinearLayout) findViewById(R.id.layout_useful);
		layout_useful.setOnClickListener(this);
		
		tv_useful = (TextView) findViewById(R.id.tv_useful);
		line_useful = (View) findViewById(R.id.line_useful);
		
		layout_expired = (LinearLayout) findViewById(R.id.layout_expired);
		layout_expired.setOnClickListener(this);
		
		tv_expired = (TextView) findViewById(R.id.tv_expired);
		line_expired = (View) findViewById(R.id.line_expired);
		
		defaultColor();
	}
	
	private void defaultColor()
	{
		//layout_useful.setBackgroundColor(color);
		//layout_expired.setBackgroundColor(getResources().getColor(R.color.btn_disable));
		tv_useful.setTextColor(color);
		line_useful.setBackgroundColor(color);
		tv_expired.setTextColor(resources.getColor(R.color.gray));
	}

	private void initData(final String isexpired,boolean pro) {
		ActivityEntity act = new ActivityEntity();
		
		act.isexpired = isexpired;
		act.acttype = acttype;
		OperationBuilder builder = new OperationBuilder().append(new ActivityOp(),
				act);
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
				ActivityEntity entity = (ActivityEntity) resList.get(0);
				List<ActivityDetailEntity> activitylist = entity.getRetobj();
				if (activitylist == null || activitylist.size() == 0) {
					if("1".equals(acttype)) {
						Toast.show(self, resources.getString(R.string.noactivityinfo));
					} else if ("2".equals(acttype)) {
						Toast.show(self, resources.getString(R.string.notraininginfo));
					}
					
					//finish();
					return;
				}
				for (ActivityDetailEntity entity1 : activitylist) {
					entity1.setIsexprired(isexpired);
					adapter.addItem(entity1);
				}
				adapter.setType(acttype);
				adapter.notifyDataSetChanged();
			}

			@Override
			public void onOperationError(Exception e) {
				e.printStackTrace();
			}
		};

		JsonCommon task  = null;
		if(pro){
			task = new JsonCommon(self, builder, listener,
					JsonCommon.PROGRESSQUERY);
		}else{
			task = new JsonCommon(self, builder, listener,
					false);
		}
		task.execute();
	}
	
	
	private void showMenu(int layout) {
		switch (layout) {
		case R.id.layout_useful:
			/*layout_useful.setBackgroundColor(color);
			layout_expired.setBackgroundColor(getResources().getColor(R.color.btn_disable));
			tv_useful.setTextColor(resources.getColor(R.color.WHITE));
			tv_expired.setTextColor(resources.getColor(R.color.WHITE));*/
			tv_useful.setTextColor(color);
			line_useful.setBackgroundColor(color);
			tv_expired.setTextColor(resources.getColor(R.color.gray));
			line_expired.setBackgroundColor(resources.getColor(R.color.WHITE));
			adapter.removeAll();
			adapter.notifyDataSetChanged();
			isSelect = "1";
			initData("1",true);
			break;
		case R.id.layout_expired:
			/*layout_expired.setBackgroundColor(color);
			layout_useful.setBackgroundColor(getResources().getColor(R.color.btn_disable));
			tv_expired.setTextColor(resources.getColor(R.color.WHITE));
			tv_useful.setTextColor(resources.getColor(R.color.WHITE));*/
			tv_useful.setTextColor(resources.getColor(R.color.gray));
			line_useful.setBackgroundColor(resources.getColor(R.color.WHITE));
			tv_expired.setTextColor(color);
			line_expired.setBackgroundColor(color);
			adapter.removeAll();
			adapter.notifyDataSetChanged();
			isSelect = "2";
			initData("2",true);
			break;
		default:
			break;
		}
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.layout_useful:
		case R.id.layout_expired:
			showMenu(v.getId());
			break;
		case R.id.iv_ret:
			finish();
			break;
		default:
			break;
		}
	}
	
	public void onPause() {
		super.onPause();
		MobclickAgent.onPause(this);
	}
}
