package com.joy.Activity;

import gejw.android.quickandroid.QActivity;
import gejw.android.quickandroid.widget.Toast;

import java.util.List;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.joy121.R;
import com.joy.Dialog.DialogUtil;
import com.joy.Utils.Constants;
import com.joy.Widget.HispointsAdapter;
import com.joy.json.JsonCommon;
import com.joy.json.JsonCommon.OnOperationListener;
import com.joy.json.model.PointEntity;
import com.joy.json.model.UserPointsHisEntity;
import com.joy.json.operation.OperationBuilder;
import com.joy.json.operation.impl.Point_hisOp;
import com.umeng.analytics.MobclickAgent;

/**
 * 积分历史
 * @author daiye
 *
 */
public class HisPointsActivity extends BaseActivity implements OnClickListener {

	private RelativeLayout layout_title;
	private TextView tv_ret;
	private TextView tv_title;
	private ListView list_hispoints;
	private HispointsAdapter adapter;

	/*@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_hispoints);
		
		initView();
		initData();
	}*/
	
	@Override
	protected View ceateView(LayoutInflater inflater, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View v = inflater.inflate(R.layout.activity_hispoints, null);
		setContentView(v);
		initView();
		initData();
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

		list_hispoints = (ListView) findViewById(R.id.list_hispoints);
		uiAdapter.setMargin(list_hispoints, LayoutParams.MATCH_PARENT,
				LayoutParams.WRAP_CONTENT, 10, 10, 10, 0);
		adapter = new HispointsAdapter(self);
		list_hispoints.setAdapter(adapter);
	}

	private void initData() {
		OperationBuilder builder = new OperationBuilder().append(new Point_hisOp(),
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
				PointEntity entity = (PointEntity) resList.get(0);
				List<UserPointsHisEntity> userpointshislist = entity.getRetobj();
				if (userpointshislist == null) {
					Toast.show(self, "没有积分历史！");
					finish();
					return;
				}
				for (UserPointsHisEntity entity1 : userpointshislist) {
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
