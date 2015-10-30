package com.joy.Activity;


import com.joy.R;
import com.joy.Utils.Constants;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class PhysicalBookingResultsActivity  extends BaseActivity implements OnClickListener {
	private RelativeLayout layout_title;
	private ImageView iv_ret;
	private TextView tv_title;
	private ImageView iv_bookingResultsDate,iv_bookingResultsPlace,iv_trafficRoutes,iv_medicalInformation;
	private TextView tv_bookingResultsDate,tv_bookingResultsPlace,tv_trafficRoutes,tv_medicalInformation;
	private EditText et_bookingResultsDate,et_bookingResultsPlace,et_trafficRoutes,et_medicalInformation;
	@Override
	protected View ceateView(LayoutInflater inflater, Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.activity_physical_booking_results, null);
		setContentView(v);
		initView();
		setData();
		return v;
	}
	  public void initView(){
	    	layout_title = (RelativeLayout) findViewById(R.id.layout_title);
			uiAdapter.setMargin(layout_title, LayoutParams.MATCH_PARENT, Constants.SubTitleHeight, 0, 0,
					0, 0);
			iv_ret = (ImageView) findViewById(R.id.iv_ret);
			iv_ret.setOnClickListener(this);

			tv_title = (TextView) findViewById(R.id.tv_title);
			uiAdapter.setTextSize(tv_title, Constants.TitleSize);
			
			iv_bookingResultsDate = (ImageView) findViewById(R.id.iv_bookingResultsDate);
			uiAdapter.setMargin(iv_bookingResultsDate, LayoutParams.WRAP_CONTENT,
					LayoutParams.WRAP_CONTENT, 20, 33, 0, 10);
			tv_bookingResultsDate = (TextView) findViewById(R.id.tv_bookingResultsDate);
			uiAdapter.setTextSize(tv_bookingResultsDate, 18);
			uiAdapter.setMargin(tv_bookingResultsDate, LayoutParams.WRAP_CONTENT,
					LayoutParams.WRAP_CONTENT, 10, 20, 0, 10);
			et_bookingResultsDate = (EditText) findViewById(R.id.et_bookingResultsDate);
			uiAdapter.setMargin(et_bookingResultsDate, LayoutParams.MATCH_PARENT, 45, 5, 20,
					45, 0);
			
			iv_bookingResultsPlace = (ImageView) findViewById(R.id.iv_bookingResultsPlace);
			uiAdapter.setMargin(iv_bookingResultsPlace, LayoutParams.WRAP_CONTENT,
					LayoutParams.WRAP_CONTENT, 20, 33, 0, 10);
			tv_bookingResultsPlace = (TextView) findViewById(R.id.tv_bookingResultsPlace);
			uiAdapter.setTextSize(tv_bookingResultsPlace, 18);
			uiAdapter.setMargin(tv_bookingResultsPlace, LayoutParams.WRAP_CONTENT,
					LayoutParams.WRAP_CONTENT, 10, 20, 0, 10);
			et_bookingResultsPlace = (EditText) findViewById(R.id.et_bookingResultsPlace);
			uiAdapter.setMargin(et_bookingResultsPlace, LayoutParams.MATCH_PARENT, 45, 5, 20,
					45, 0);
			
			iv_trafficRoutes = (ImageView) findViewById(R.id.iv_trafficRoutes);
			uiAdapter.setMargin(iv_trafficRoutes, LayoutParams.WRAP_CONTENT,
					LayoutParams.WRAP_CONTENT, 20, 33, 0, 10);
			tv_trafficRoutes = (TextView) findViewById(R.id.tv_trafficRoutes);
			uiAdapter.setTextSize(tv_trafficRoutes, 18);
			uiAdapter.setMargin(tv_trafficRoutes, LayoutParams.WRAP_CONTENT,
					LayoutParams.WRAP_CONTENT, 10, 20, 0, 10);
			et_trafficRoutes = (EditText) findViewById(R.id.et_trafficRoutes);
			
			
			iv_medicalInformation = (ImageView) findViewById(R.id.iv_medicalInformation);
			uiAdapter.setMargin(iv_medicalInformation, LayoutParams.WRAP_CONTENT,
					LayoutParams.WRAP_CONTENT, 20, 33, 0, 10);
			tv_medicalInformation = (TextView) findViewById(R.id.tv_medicalInformation);
			uiAdapter.setTextSize(tv_medicalInformation, 18);
			uiAdapter.setMargin(tv_medicalInformation, LayoutParams.WRAP_CONTENT,
					LayoutParams.WRAP_CONTENT, 10, 20, 0, 10);
			et_medicalInformation = (EditText) findViewById(R.id.et_medicalInformation);
			
	    }
	  public void setData(){
		  et_bookingResultsDate.setText("2015-10-29");
		  et_bookingResultsPlace.setText("苏州大学附属总医院");
		  et_trafficRoutes.setText("乘坐1路;12路;25路;308路;811路到第一人民医院站,下车向北步行500米。");
		  et_medicalInformation.setText("1. 体检前一天不要大吃大喝，特别不要喝酒及浓茶、咖啡等刺激食物，不要进食太甜太咸及油腻的食物，以免影响次日的化验结果，晚上8点后一般要求禁食。\n" +
		  		                       " 2. 晚上早点休息，保证充足的睡眠，以确保体检结果的准确。\n" +
		  		                       " 3. 检查当日早上不能进食及饮水，若感到很口渴，只能喝少量（一、二口）白开水。");
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

}
