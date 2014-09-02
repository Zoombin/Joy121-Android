package com.joy.Fragment.portals.activity;

import gejw.android.quickandroid.widget.Toast;

import java.util.List;

import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.joy.R;
import com.joy.Activity.MainActivity;
import com.joy.Fragment.BaseFragment;
import com.joy.Utils.Constants;
import com.joy.Widget.ActivityAdapter;
import com.joy.json.JsonCommon;
import com.joy.json.JsonCommon.OnOperationListener;
import com.joy.json.model.ActivityDetailEntity;
import com.joy.json.model.ActivityEntity;
import com.joy.json.operation.OperationBuilder;
import com.joy.json.operation.impl.ActivityOp;

/****
 * 公司活动
 * @author LSD
 *
 */
public class ActivityFragment extends BaseFragment implements OnClickListener{
	private RelativeLayout layout_title;
	private TextView tv_ret;
	private TextView tv_title;
	private ListView list_activity;
	private ActivityAdapter adapter;
	
	private LinearLayout layout_menu;
	private LinearLayout layout_useful;
	private TextView tv_useful;
	private LinearLayout layout_expired;
	private TextView tv_expired;
	private Resources resources;
	
	String acttype;
	List<ActivityDetailEntity> tempList;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		resources = getResources();
		
		Bundle bundle = getArguments();
		if(bundle != null){
			acttype = bundle.getString("acttype");
		}
	}
	
	/*@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View v= inflater.inflate(R.layout.activity_activity, container,false);
		initView(v);
		return v;
	}*/
	
	@Override
	protected View initContentView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View v= inflater.inflate(R.layout.activity_activity, container,false);
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

		list_activity = (ListView) v.findViewById(R.id.list_activity);
		uiAdapter.setMargin(list_activity, LayoutParams.MATCH_PARENT,
				LayoutParams.WRAP_CONTENT, 0, 0, 0, 0);
		adapter = new ActivityAdapter(mActivity, mActivity);
		list_activity.setAdapter(adapter);
		
		layout_menu = (LinearLayout) v.findViewById(R.id.layout_menu);
		
		layout_useful = (LinearLayout) v.findViewById(R.id.layout_useful);
		layout_useful.setOnClickListener(this);
		
		tv_useful = (TextView) v.findViewById(R.id.tv_useful);
		
		layout_expired = (LinearLayout) v.findViewById(R.id.layout_expired);
		layout_expired.setOnClickListener(this);
		
		tv_expired = (TextView) v.findViewById(R.id.tv_expired);
		
		
		initData("1",false);
	}

	private void initData(final String isexpired,boolean isMenuSwitch) {
		if(tempList != null && !isMenuSwitch){
			adapter.setData(tempList);
			return;
		}
		ActivityEntity act = new ActivityEntity();
		
		//Intent intent = getIntent();
		//String acttype = intent.getStringExtra("acttype");
		//act.isexpired = isexpired;
		//act.acttype = acttype;
		act.setIsExpired(isexpired);
		act.setActType(acttype);
		OperationBuilder builder = new OperationBuilder().append(new ActivityOp(),
				act);
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
				ActivityEntity entity = (ActivityEntity) resList.get(0);
				List<ActivityDetailEntity> activitylist = entity.getRetobj();
				if (activitylist == null) {
					Toast.show(mActivity, "没有活动信息！");
					//finish();
					return;
				}
				/*for (ActivityDetailEntity entity1 : activitylist) {
					entity1.setIsexprired(isexpired);
					adapter.addItem(entity1);
				}
				adapter.notifyDataSetChanged();*/
				
				tempList = activitylist;
				adapter.setData(activitylist);
			}

			@Override
			public void onOperationError(Exception e) {
				e.printStackTrace();
			}
		};

		JsonCommon task = new JsonCommon(mActivity, builder, listener,
				JsonCommon.PROGRESSQUERY);
		task.execute();
	}
	
	
	private void showMenu(int layout) {
		switch (layout) {
		case R.id.layout_useful:
			tv_useful.setTextColor(resources.getColor(R.color.title_bg));
			tv_expired.setTextColor(resources.getColor(R.color.BLACK));
			adapter.removeAll();
			adapter.notifyDataSetChanged();
			initData("1",true);
			break;
		case R.id.layout_expired:
			tv_expired.setTextColor(resources.getColor(R.color.title_bg));
			tv_useful.setTextColor(resources.getColor(R.color.BLACK));
			adapter.removeAll();
			adapter.notifyDataSetChanged();
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
		case R.id.tv_ret:
			//finish();
			MainActivity.mActivity.Back();
			break;
		default:
			break;
		}
	}
	
	public void onResume() {
		super.onResume();
		//MobclickAgent.onResume(this);
	}

	public void onPause() {
		super.onPause();
		//MobclickAgent.onPause(this);
	}
}
