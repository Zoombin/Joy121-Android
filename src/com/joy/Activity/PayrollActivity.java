package com.joy.Activity;


/**
 * 工资单
 * @author ryan zhou 2014-11-12
 *
 */


import gejw.android.quickandroid.widget.Toast;

import java.util.List;

import com.joy.R;
import com.joy.Utils.Constants;
import com.joy.Widget.PayrollAdapter;
import com.joy.json.JsonCommon;
import com.joy.json.JsonCommon.OnOperationListener;
import com.joy.json.model.PayrollBriefEntity;
import com.joy.json.model.PayrollEntity;
import com.joy.json.model.PayrollListEntity;
import com.joy.json.operation.OperationBuilder;
import com.joy.json.operation.impl.PayrollOp;
import com.umeng.analytics.MobclickAgent;

import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.inputmethod.EditorInfo;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.TextView.OnEditorActionListener;

public class PayrollActivity extends BaseActivity implements OnClickListener {


	private RelativeLayout layout_title;
	private ImageView iv_ret;
	private TextView tv_title;
	private RelativeLayout layout_prompt;
	private ImageView iv_prompt;
	private ListView list_payrolls;
	private PayrollAdapter adapter;
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
		View v = inflater.inflate(R.layout.activity_payroll, null);
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
		
		layout_prompt = (RelativeLayout) findViewById(R.id.layout_prompt);
		layout_prompt.setBackgroundColor(Color.parseColor(appSet.getColor2()));
		iv_prompt = (ImageView) findViewById(R.id.iv_prompt);
		iv_prompt.setOnClickListener(this);

		tv_title = (TextView) findViewById(R.id.tv_title);
		uiAdapter.setTextSize(tv_title, Constants.TitleSize);
		tv_title.setText(R.string.payroll);//工资单

		list_payrolls = (ListView) findViewById(R.id.list_payrolls);
		uiAdapter.setMargin(list_payrolls, LayoutParams.MATCH_PARENT,
				LayoutParams.WRAP_CONTENT, 0, 0, 0, 0);
		adapter = new PayrollAdapter(self, self);
		list_payrolls.setAdapter(adapter);
	}
	
	private void initData() {
		/*PayrollEntity payroll1 = new PayrollEntity("4459.74", "2014/11", "3000.00", "", "", "", "378.06", "800.00", "5.00", "2816.89", "0.00", "-63.22", "-443.76", " -11.09", " -113.94", "-666.00", "4489.42", "-29.68", "0.00");
		PayrollEntity payroll2 = new PayrollEntity("4612.13", "2014/10", "3000.00", "", "", "", "441.07", "800.00", "5.00", "2796.99", "0.00", "0.00",  "-443.76", " -11.09", " -113.94", "-666.00", "4646.53", "	0", "0.00");
		PayrollEntity payroll3 = new PayrollEntity("3819.78", "2014/09", "3000.00", "", "", "", "340.25", "800.00", "5.00", "1781.74", "0.00", "-158.05",  "-443.76", " -11.09", " -113.94", "-666.00", "3829.67", "-9.89", "0.00");
		PayrollEntity payroll4 = new PayrollEntity("4756.54", "2014/08", "3000.00", "", "", "", "378.06", "800.00", "5.00", "2698.22", "0.00", "0.00",  "-443.76", " -11.09", " -113.94", "-666.00", "4795.40", "-38.86", "0.00");
		PayrollEntity payroll5 = new PayrollEntity("3617.66", "2014/07", "3000.00", "", "", "", "270.04", "800.00", "5.00", "1831.74", "0.00", "-147.41",  "-443.76", " -11.09", " -113.94", "-666.00", "3621.30", "-3.64", "0.00");
		adapter.addItem(payroll1);
		adapter.addItem(payroll2);
		adapter.addItem(payroll3);
		adapter.addItem(payroll4);
		adapter.addItem(payroll5);
		adapter.notifyDataSetChanged();*/
		
		
		
		OperationBuilder builder = new OperationBuilder().append(new PayrollOp(),
				null);
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
				PayrollListEntity entity = (PayrollListEntity) resList.get(0);
				List<PayrollBriefEntity> payrollEntityList = entity.getRetobj();
				if (payrollEntityList == null || payrollEntityList.size() == 0) {
					layout_prompt.setVisibility(View.VISIBLE);
					//finish();
					return;
				}
				layout_prompt.setVisibility(View.GONE);
				int position = 0;
				for (PayrollBriefEntity entity1 : payrollEntityList) {
					
					adapter.addItem(entity1);
					position++;
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
