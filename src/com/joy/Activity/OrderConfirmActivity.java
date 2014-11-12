package com.joy.Activity;

import gejw.android.quickandroid.QActivity;
import gejw.android.quickandroid.ui.adapter.UIManager;
import gejw.android.quickandroid.widget.Toast;

import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.joy.JoyApplication;
import com.joy.R;
import com.joy.Utils.Constants;
import com.joy.json.JsonCommon;
import com.joy.json.JsonCommon.OnOperationListener;
import com.joy.json.model.CommoditySet;
import com.joy.json.model.OrderSubmitEntity;
import com.joy.json.model.OrderSubmitEntity.SubmitRet;
import com.joy.json.model.UserInfoEntity;
import com.joy.json.operation.OperationBuilder;
import com.joy.json.operation.impl.OrderSubmitOp;
import com.umeng.analytics.MobclickAgent;

/**
 * 提交订单
 * @author daiye
 *
 */
public class OrderConfirmActivity extends BaseActivity implements OnClickListener {
	
	private RelativeLayout layout_title;
	private TextView tv_ret;
	private TextView tv_title;
	private TextView tv_receiverinfo;
	private TextView tv_receiver_title;
	private TextView tv_recAdd_title;
	private TextView tv_recPhone_title;
	private TextView tv_receiver;
	private TextView tv_recAdd;
	private TextView tv_recPhone;
	private ImageView iv_arrowright;
	private TextView tv_beizhu_title;
	private EditText et_beizhu;
	private TextView tv_product_title;
	private LinearLayout layout_points;
	private TextView tv_request_points_title;
	private TextView tv_total_points_title;
	private TextView tv_request_points;
	private TextView tv_total_points;
	private Button btn_orderconfirm;
	private UserInfoEntity userinfo;
	private CommoditySet commoditySet;
	
	/*@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_orderconfirm);
		
		Intent intent = getIntent();
		commoditySet = (CommoditySet) intent.getSerializableExtra(OrderDetailActivity.EXTRA_COMMODITYSET);
		userinfo = JoyApplication.getInstance().getUserinfo();
		
		initView();
		initData();
	}*/
	
	@Override
	protected View ceateView(LayoutInflater inflater, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View v = inflater.inflate(R.layout.activity_orderconfirm, null);
		setContentView(v);
		Intent intent = getIntent();
		commoditySet = (CommoditySet) intent.getSerializableExtra(OrderDetailActivity.EXTRA_COMMODITYSET);
		userinfo = JoyApplication.getInstance().getUserinfo();
		
		initView();
		initData();
		return v;
	}

	private void initView() {
		layout_title = (RelativeLayout) findViewById(R.id.layout_title);
		uiAdapter.setMargin(layout_title, LayoutParams.MATCH_PARENT, Constants.TitleHeight, 0, 0, 0, 0);

		tv_ret = (TextView) findViewById(R.id.tv_ret);
		tv_ret.setOnClickListener(this);
		uiAdapter.setTextSize(tv_ret, Constants.TitleRetSize);
		uiAdapter.setMargin(tv_ret, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 20, 0, 0, 0);
		
		tv_title = (TextView) findViewById(R.id.tv_title);
		uiAdapter.setTextSize(tv_title, Constants.TitleSize);
		
		tv_receiverinfo = (TextView) findViewById(R.id.tv_receiverinfo);
		uiAdapter.setTextSize(tv_receiverinfo, 18);
		uiAdapter.setPadding(tv_receiverinfo, 20, 10, 0, 5);
		
		tv_receiver_title = (TextView) findViewById(R.id.tv_receiver_title);
		uiAdapter.setTextSize(tv_receiver_title, 18);
		uiAdapter.setMargin(tv_receiver_title, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 30, 10, 0, 0);
		
		tv_recAdd_title = (TextView) findViewById(R.id.tv_recAdd_title);
		uiAdapter.setTextSize(tv_recAdd_title, 18);
		uiAdapter.setMargin(tv_recAdd_title, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 30, 0, 0, 0);
		
		tv_recPhone_title = (TextView) findViewById(R.id.tv_recPhone_title);
		uiAdapter.setTextSize(tv_recPhone_title, 18);
		uiAdapter.setMargin(tv_recPhone_title, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 30, 0, 0, 10);
		
		tv_receiver = (TextView) findViewById(R.id.tv_receiver);
		uiAdapter.setTextSize(tv_receiver, 18);
		uiAdapter.setMargin(tv_receiver, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 0, 10, 0, 0);
		
		tv_recAdd = (TextView) findViewById(R.id.tv_recAdd);
		uiAdapter.setTextSize(tv_recAdd, 18);
		
		tv_recPhone = (TextView) findViewById(R.id.tv_recPhone);
		uiAdapter.setTextSize(tv_recPhone, 18);
		
		iv_arrowright = (ImageView) findViewById(R.id.iv_arrowright);
		uiAdapter.setMargin(iv_arrowright, 15, uiAdapter.CalcHeight(15, 16, 28), 0, 0, 10, 0);
		
		tv_beizhu_title = (TextView) findViewById(R.id.tv_beizhu_title);
		uiAdapter.setTextSize(tv_beizhu_title, 18);
		uiAdapter.setPadding(tv_beizhu_title, 20, 10, 0, 5);
		
		et_beizhu = (EditText) findViewById(R.id.et_beizhu);
		uiAdapter.setTextSize(et_beizhu, 18);
		uiAdapter.setMargin(et_beizhu, LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT, 60, 10, 10, 10);
		uiAdapter.setPadding(et_beizhu, 10, 0, 10, 0);
		
		tv_product_title = (TextView) findViewById(R.id.tv_product_title);
		uiAdapter.setTextSize(tv_product_title, 18);
		uiAdapter.setPadding(tv_product_title, 20, 10, 0, 5);
		
		layout_points = (LinearLayout) findViewById(R.id.layout_points);
		uiAdapter.setMargin(layout_points, LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT, 60, 10, 10, 10);
		uiAdapter.setPadding(layout_points, 10, 10, 10, 40);
		
		RelativeLayout l = new RelativeLayout(self);

		TextView tv_setname = new TextView(self);
		tv_setname.setText(commoditySet.getSetName());
		uiAdapter.setTextSize(tv_setname, 18);
		RelativeLayout.LayoutParams layoutParams1 = new RelativeLayout.LayoutParams(  
		        LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		layoutParams1.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
		tv_setname.setLayoutParams(layoutParams1);
		l.addView(tv_setname);
		
		TextView tv_point = new TextView(self);
		tv_point.setText(commoditySet.getPoints() + "×1");
		uiAdapter.setTextSize(tv_point, 18);
		RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(  
		        LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		layoutParams2.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
		tv_point.setLayoutParams(layoutParams2);
		l.addView(tv_point);
		
		layout_points.addView(l);
		
		tv_request_points_title = (TextView) findViewById(R.id.tv_request_points_title);
		uiAdapter.setTextSize(tv_request_points_title, 18);
		uiAdapter.setMargin(tv_request_points_title, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 0, 10, 0, 0);
		
		tv_total_points_title = (TextView) findViewById(R.id.tv_total_points_title);
		uiAdapter.setTextSize(tv_total_points_title, 18);
		uiAdapter.setMargin(tv_total_points_title, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 0, 10, 0, 10);
		
		tv_request_points = (TextView) findViewById(R.id.tv_request_points);
		uiAdapter.setTextSize(tv_request_points, 18);
		uiAdapter.setMargin(tv_request_points, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 0, 10, 20, 0);
		
		tv_total_points = (TextView) findViewById(R.id.tv_total_points);
		uiAdapter.setTextSize(tv_total_points, 18);
		uiAdapter.setMargin(tv_total_points, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 0, 10, 20, 10);
		
		btn_orderconfirm = (Button) findViewById(R.id.btn_orderconfirm);
		uiAdapter.setMargin(btn_orderconfirm, LayoutParams.MATCH_PARENT, 46, 10,
				50, 10, 20);
		uiAdapter.setTextSize(btn_orderconfirm, 24);
		btn_orderconfirm.setOnClickListener(this);
	}
	
	private void initData() {
		if (userinfo != null) { 
			tv_receiver.setText(userinfo.getUserName());
			
			tv_recAdd.setText(userinfo.getCompanyInfo().getCompAddr());
			
			tv_recPhone.setText(userinfo.getCellNumber());
			
			tv_request_points.setText(commoditySet.getPoints() + "");
			
			tv_total_points.setText(userinfo.getPoints() + "");
		}
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.tv_ret:
			finish();
			break;
		case R.id.btn_orderconfirm:
			orderSubmit();
			break;
		default:
			break;
		}
	}
	
	private void orderSubmit() {
		OrderSubmitEntity entity = new OrderSubmitEntity();
		entity.setpId(Integer.toString(commoditySet.getId()));
		entity.setpType("2");
		entity.setReceiver(tv_receiver.getText().toString());
		entity.setRecAdd(tv_recAdd.getText().toString());
		entity.setRecPhone(tv_recPhone.getText().toString());
		String beizhu = et_beizhu.getText().toString();
		if (beizhu == null) { 
			entity.setpRemark(" ");
		} else {
			entity.setpRemark(beizhu);
		}
		
		OperationBuilder builder = new OperationBuilder().append(
				new OrderSubmitOp(), entity);
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
				OrderSubmitEntity entity = (OrderSubmitEntity) resList.get(0);
				SubmitRet result = entity.getRetobj();
				if (result != null && result.getStatusFlag().equals("1")) {
					Toast.show(self, getResources().getString(R.string.commitordersuccess));
					finish();
				} else {
					Toast.show(self, result.getStatusRemark());
				}
			}

			@Override
			public void onOperationError(Exception e) {
				e.printStackTrace();
			}
		};

		JsonCommon task = new JsonCommon(self, builder, listener,
				JsonCommon.PROGRESSCOMMIT);
		task.execute();
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
