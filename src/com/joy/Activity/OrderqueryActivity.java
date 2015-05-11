package com.joy.Activity;

import gejw.android.quickandroid.widget.Toast;

import java.util.List;

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
import com.joy.Widget.OrderQueryAdapter;
import com.joy.json.JsonCommon;
import com.joy.json.JsonCommon.OnOperationListener;
import com.joy.json.model.OrderEntity;
import com.joy.json.model.UserOrderEntity;
import com.joy.json.operation.OperationBuilder;
import com.joy.json.operation.impl.OrderOp;
import com.umeng.analytics.MobclickAgent;

/***
 * 我的订单
 * @author lsd
 *
 */
public class OrderqueryActivity extends BaseActivity implements OnClickListener {
	
	private RelativeLayout layout_title;
	private ImageView iv_ret;
	private TextView tv_title;
	private ListView list_order;
	private OrderQueryAdapter adapter;
	
	/*@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_orderquery);
		
		initView();
		initData();
	}*/
	
	@Override
	protected View ceateView(LayoutInflater inflater, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View v = inflater.inflate(R.layout.activity_orderquery, null);
		setContentView(v);
		initView();
		initData();
		return v;
	}

	private void initView() {
		layout_title = (RelativeLayout) findViewById(R.id.layout_title);
		uiAdapter.setMargin(layout_title, LayoutParams.MATCH_PARENT, Constants.SubTitleHeight, 0, 0,
				0, 0);

		iv_ret = (ImageView) findViewById(R.id.iv_ret);
		iv_ret.setOnClickListener(this);

		tv_title = (TextView) findViewById(R.id.tv_title);
		uiAdapter.setTextSize(tv_title, Constants.TitleSize);

		list_order = (ListView) findViewById(R.id.list_order);
		uiAdapter.setMargin(list_order, LayoutParams.MATCH_PARENT,
				LayoutParams.WRAP_CONTENT, 0, 0, 0, 0);
		adapter = new OrderQueryAdapter(self);
		list_order.setAdapter(adapter);
	}

	private void initData() {
		OperationBuilder builder = new OperationBuilder().append(new OrderOp(),
				null);
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
				OrderEntity entity = (OrderEntity) resList.get(0);
				List<UserOrderEntity> userorderlist = entity.getRetobj();
				if (userorderlist == null || userorderlist.size() == 0) {
					Toast.show(self, getResources().getString(R.string.noorderinfo));
					//finish();
					return;
				}
				for (UserOrderEntity entity1 : userorderlist) {
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
		case R.id.iv_ret:
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
