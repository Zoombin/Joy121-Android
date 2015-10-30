package com.joy.Activity;

import java.util.ArrayList;
import java.util.List;

import com.joy.R;
import com.joy.Utils.Constants;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

public class PhysicalBookingActivity extends BaseActivity implements OnClickListener {
	private RelativeLayout layout_title,bookingNext,bookingNext1,bookingSumbit;
	private LinearLayout layout_bookingInfo,layout_bookingInfo1,layout_personalInfo;
	private ImageView iv_ret,iv_retBack,iv_retBack1;
	private TextView tv_title,tv_goBack;
	private ImageView iv_name,iv_gender,iv_documentType,iv_idNo,iv_isMarried,iv_mobile;
	private TextView tv_name,tv_gender,tv_documentType,tv_idNo,tv_isMarried,tv_mobile;
	private EditText et_name,et_gender,et_idNo,et_mobile;
	private Spinner sp_isMarried,sp_documentType;
	
	private ImageView iv_bookingProductName,iv_bookingCity,iv_bookingCenter,iv_trafficRoutes,iv_bookingDate,iv_bookingPlace,iv_bookingPlan,iv_addBooking;
	private TextView tv_bookingProductName,tv_bookingCity,tv_bookingCenter,tv_trafficRoutes,tv_bookingDate,tv_bookingPlace,tv_bookingPlan,tv_addBooking;
	private EditText et_bookingProductName,et_bookingCity,et_bookingCenter,et_trafficRoutes,et_bookingDate,et_bookingPlace,et_bookingPlan,et_addBooking;
	private List<String> data_list,data_documentTypeList;
	private ArrayAdapter<String> isMarried_adapter,documentType_adapter;
	private Button btn_next,btn_next1,btn_sumbit;

	@Override
	protected View ceateView(LayoutInflater inflater, Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.activity_physical_booking, null);
		setContentView(v);
		initViewPersonalInfo();
		initViewBookingInfo();
		initViewBookingInfo1();
		btn_next.setOnClickListener(this);
		btn_next1.setOnClickListener(this);
		return v;
	}
	public void initViewPersonalInfo(){
		layout_title = (RelativeLayout) findViewById(R.id.layout_title);
		uiAdapter.setMargin(layout_title, LayoutParams.MATCH_PARENT, Constants.SubTitleHeight, 0, 0,
				0, 0);
		iv_ret = (ImageView) findViewById(R.id.iv_ret);
		iv_ret.setOnClickListener(this);
		
		iv_retBack = (ImageView) findViewById(R.id.iv_retBack);
		iv_retBack.setOnClickListener(this);
		tv_goBack=(TextView)findViewById(R.id.tv_goBack);
		
		tv_title = (TextView) findViewById(R.id.tv_title);
		uiAdapter.setTextSize(tv_title, Constants.TitleSize);
		layout_personalInfo=(LinearLayout)findViewById(R.id.layout_personalInfo);
		
		
		iv_name = (ImageView) findViewById(R.id.iv_name);
		uiAdapter.setMargin(iv_name, LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT, 20, 33, 0, 10);
		tv_name = (TextView) findViewById(R.id.tv_name);
		uiAdapter.setTextSize(tv_name, 18);
		uiAdapter.setMargin(tv_name, LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT, 10, 20, 0, 10);
		et_name = (EditText) findViewById(R.id.et_name);
		uiAdapter.setMargin(et_name, LayoutParams.MATCH_PARENT, 45, 5, 20,
				45, 0);
		
		iv_gender = (ImageView) findViewById(R.id.iv_gender);
		uiAdapter.setMargin(iv_gender, LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT, 20, 33, 0, 10);
		tv_gender = (TextView) findViewById(R.id.tv_gender);
		uiAdapter.setTextSize(tv_gender, 18);
		uiAdapter.setMargin(tv_gender, LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT, 10, 20, 0, 10);
		et_gender = (EditText) findViewById(R.id.et_gender);
		uiAdapter.setMargin(et_gender, LayoutParams.MATCH_PARENT, 45, 5, 20,
				45, 0);
		
		iv_documentType = (ImageView) findViewById(R.id.iv_documentType);
		uiAdapter.setMargin(iv_documentType, LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT, 20, 33, 0, 10);
		tv_documentType = (TextView) findViewById(R.id.tv_documentType);
		uiAdapter.setTextSize(tv_documentType, 18);
		uiAdapter.setMargin(tv_documentType, LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT, 10, 20, 0, 10);
		sp_documentType = (Spinner) findViewById(R.id.sp_documentType);
		uiAdapter.setMargin(sp_documentType, LayoutParams.MATCH_PARENT, 45, 5, 20,
				45, 0);

        //数据
		data_documentTypeList = new ArrayList<String>();
		data_documentTypeList.add("身份证");
		data_documentTypeList.add("居住证");
		data_documentTypeList.add("签证");
		data_documentTypeList.add("港澳通行证");
        
        //适配器
		documentType_adapter= new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, data_documentTypeList);
        //设置样式
		documentType_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //加载适配器
		sp_documentType.setAdapter(documentType_adapter);
		
		iv_idNo = (ImageView) findViewById(R.id.iv_idNo);
		uiAdapter.setMargin(iv_idNo, LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT, 20, 33, 0, 10);
		tv_idNo = (TextView) findViewById(R.id.tv_idNo);
		uiAdapter.setTextSize(tv_idNo, 18);
		uiAdapter.setMargin(tv_idNo, LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT, 10, 20, 0, 10);
		et_idNo = (EditText) findViewById(R.id.et_idNo);
		uiAdapter.setMargin(et_idNo, LayoutParams.MATCH_PARENT, 45, 5, 20,
				45, 0);
		
		
		
		iv_isMarried = (ImageView) findViewById(R.id.iv_isMarried);
		uiAdapter.setMargin(iv_isMarried, LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT, 20, 33, 0, 10);
		tv_isMarried = (TextView) findViewById(R.id.tv_isMarried);
		uiAdapter.setTextSize(tv_isMarried, 18);
		uiAdapter.setMargin(tv_isMarried, LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT, 10, 20, 0, 10);
		sp_isMarried = (Spinner) findViewById(R.id.sp_isMarried);
		uiAdapter.setMargin(sp_isMarried, LayoutParams.MATCH_PARENT, 45, 5, 20,
				45, 0);

        //数据
        data_list = new ArrayList<String>();
        data_list.add("已婚");
        data_list.add("未婚");
        
        //适配器
        isMarried_adapter= new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, data_list);
        //设置样式
        isMarried_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //加载适配器
        sp_isMarried.setAdapter(isMarried_adapter);
        
		iv_mobile = (ImageView) findViewById(R.id.iv_mobile);
		uiAdapter.setMargin(iv_mobile, LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT, 20, 33, 0, 10);
		tv_mobile = (TextView) findViewById(R.id.tv_mobile);
		uiAdapter.setTextSize(tv_mobile, 18);
		uiAdapter.setMargin(tv_mobile, LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT, 10, 20, 0, 10);
		et_mobile = (EditText) findViewById(R.id.et_mobile);
		uiAdapter.setMargin(et_mobile, LayoutParams.MATCH_PARENT, 45, 5, 20,
				45, 0);
		//下一步
		bookingNext=(RelativeLayout) findViewById(R.id.bookingNext);
		btn_next = (Button) findViewById(R.id.btn_next);
	    uiAdapter.setTextSize(btn_next, 24);
	}
  
    public void initViewBookingInfo(){
    	layout_bookingInfo=(LinearLayout)findViewById(R.id.layout_bookingInfo);
		iv_bookingProductName = (ImageView) findViewById(R.id.iv_bookingProductName);
		uiAdapter.setMargin(iv_bookingProductName, LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT, 20, 33, 0, 10);
		tv_bookingProductName = (TextView) findViewById(R.id.tv_bookingProductName);
		uiAdapter.setTextSize(tv_bookingProductName, 18);
		uiAdapter.setMargin(tv_bookingProductName, LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT, 10, 20, 0, 10);
		et_bookingProductName = (EditText) findViewById(R.id.et_bookingProductName);
		uiAdapter.setMargin(et_bookingProductName, LayoutParams.MATCH_PARENT, 45, 5, 20,
				45, 0);
		
		
		iv_bookingCity = (ImageView) findViewById(R.id.iv_bookingCity);
		uiAdapter.setMargin(iv_bookingCity, LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT, 20, 33, 0, 10);
		tv_bookingCity = (TextView) findViewById(R.id.tv_bookingCity);
		uiAdapter.setTextSize(tv_bookingCity, 18);
		uiAdapter.setMargin(tv_bookingCity, LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT, 10, 20, 0, 10);
		et_bookingCity = (EditText) findViewById(R.id.et_bookingCity);
		uiAdapter.setMargin(et_bookingCity, LayoutParams.MATCH_PARENT, 45, 5, 20,
				45, 0);
		
		iv_bookingCenter = (ImageView) findViewById(R.id.iv_bookingCenter);
		uiAdapter.setMargin(iv_bookingCenter, LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT, 20, 33, 0, 10);
		tv_bookingCenter = (TextView) findViewById(R.id.tv_bookingCenter);
		uiAdapter.setTextSize(tv_bookingCenter, 18);
		uiAdapter.setMargin(tv_bookingCenter, LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT, 10, 20, 0, 10);
		et_bookingCenter = (EditText) findViewById(R.id.et_bookingCenter);
		uiAdapter.setMargin(et_bookingCenter, LayoutParams.MATCH_PARENT, 45, 5, 20,
				45, 0);
		
		iv_trafficRoutes = (ImageView) findViewById(R.id.iv_trafficRoutes);
		uiAdapter.setMargin(iv_trafficRoutes, LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT, 20, 33, 0, 10);
		tv_trafficRoutes = (TextView) findViewById(R.id.tv_trafficRoutes);
		uiAdapter.setTextSize(tv_trafficRoutes, 18);
		uiAdapter.setMargin(tv_trafficRoutes, LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT, 10, 20, 0, 10);
		et_trafficRoutes = (EditText) findViewById(R.id.et_trafficRoutes);
		uiAdapter.setMargin(et_trafficRoutes, LayoutParams.MATCH_PARENT, 45, 5, 20,
				45, 0);
	
  	
		iv_bookingDate = (ImageView) findViewById(R.id.iv_bookingDate);
		uiAdapter.setMargin(iv_bookingDate, LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT, 20, 33, 0, 10);
		tv_bookingDate = (TextView) findViewById(R.id.tv_bookingDate);
		uiAdapter.setTextSize(tv_bookingDate, 18);
		uiAdapter.setMargin(tv_bookingDate, LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT, 10, 20, 0, 10);
		et_bookingDate = (EditText) findViewById(R.id.et_bookingDate);
		uiAdapter.setMargin(et_bookingDate, LayoutParams.MATCH_PARENT, 45, 5, 20,
				45, 0);
		
		iv_bookingPlace = (ImageView) findViewById(R.id.iv_bookingPlace);
		uiAdapter.setMargin(iv_bookingPlace, LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT, 20, 33, 0, 10);
		tv_bookingPlace = (TextView) findViewById(R.id.tv_bookingPlace);
		uiAdapter.setTextSize(tv_bookingPlace, 18);
		uiAdapter.setMargin(tv_bookingPlace, LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT, 10, 20, 0, 10);
		et_bookingPlace = (EditText) findViewById(R.id.et_bookingPlace);
		uiAdapter.setMargin(et_bookingPlace, LayoutParams.MATCH_PARENT, 45, 5, 20,
				45, 0);
		
		iv_bookingPlan = (ImageView) findViewById(R.id.iv_bookingPlan);
		uiAdapter.setMargin(iv_bookingPlan, LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT, 20, 33, 0, 10);
		tv_bookingPlan = (TextView) findViewById(R.id.tv_bookingPlan);
		uiAdapter.setTextSize(tv_bookingPlan, 18);
		uiAdapter.setMargin(tv_bookingPlan, LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT, 10, 20, 0, 10);
		et_bookingPlan = (EditText) findViewById(R.id.et_bookingPlan);
		uiAdapter.setMargin(et_bookingPlan, LayoutParams.MATCH_PARENT, 45, 5, 20,
				45, 0);
        iv_addBooking = (ImageView) findViewById(R.id.iv_addBooking);
		uiAdapter.setMargin(iv_addBooking, LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT, 20, 33, 0, 10);
		tv_addBooking = (TextView) findViewById(R.id.tv_addBooking);
		uiAdapter.setTextSize(tv_addBooking, 18);
		uiAdapter.setMargin(tv_addBooking, LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT, 10, 20, 0, 10);
		et_addBooking = (EditText) findViewById(R.id.et_addBooking);
		uiAdapter.setMargin(et_addBooking, LayoutParams.MATCH_PARENT, 45, 5, 20,
				45, 0);
		//下一步
	    bookingNext1=(RelativeLayout) findViewById(R.id.bookingNext1);
		btn_next1 = (Button) findViewById(R.id.btn_next1);
	    uiAdapter.setTextSize(btn_next1, 24);
    }
    public void initViewBookingInfo1(){   	
    	layout_bookingInfo1=(LinearLayout)findViewById(R.id.layout_bookingInfo1);
    	iv_retBack1 = (ImageView) findViewById(R.id.iv_retBack1);
		iv_retBack1.setOnClickListener(this);
    	iv_bookingPlan = (ImageView) findViewById(R.id.iv_bookingPlan);
		uiAdapter.setMargin(iv_bookingPlan, LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT, 20, 33, 0, 10);
		tv_bookingPlan = (TextView) findViewById(R.id.tv_bookingPlan);
		uiAdapter.setTextSize(tv_bookingPlan, 18);
		uiAdapter.setMargin(tv_bookingPlan, LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT, 10, 20, 0, 10);
		et_bookingPlan = (EditText) findViewById(R.id.et_bookingPlan);
		uiAdapter.setMargin(et_bookingPlan, LayoutParams.MATCH_PARENT, 45, 5, 20,
				45, 0);
        iv_addBooking = (ImageView) findViewById(R.id.iv_addBooking);
		uiAdapter.setMargin(iv_addBooking, LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT, 20, 33, 0, 10);
		tv_addBooking = (TextView) findViewById(R.id.tv_addBooking);
		uiAdapter.setTextSize(tv_addBooking, 18);
		uiAdapter.setMargin(tv_addBooking, LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT, 10, 20, 0, 10);
		et_addBooking = (EditText) findViewById(R.id.et_addBooking);
		uiAdapter.setMargin(et_addBooking, LayoutParams.MATCH_PARENT, 45, 5, 20,
				45, 0);
		//提交
		bookingSumbit=(RelativeLayout) findViewById(R.id.bookingSumbit);
		btn_sumbit = (Button) findViewById(R.id.btn_sumbit);
		
		uiAdapter.setTextSize(btn_sumbit, 24);
    }
    
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.iv_ret:
			finish();
			break;
		case R.id.btn_next:
			layout_personalInfo.setVisibility(View.GONE);
			layout_bookingInfo.setVisibility(View.VISIBLE);
			layout_bookingInfo1.setVisibility(View.GONE);
			bookingNext.setVisibility(View.GONE);
			bookingNext1.setVisibility(View.VISIBLE);
			bookingSumbit.setVisibility(View.GONE);
			iv_ret.setVisibility(View.GONE);
			iv_retBack.setVisibility(View.VISIBLE);
			tv_goBack.setVisibility(View.VISIBLE);
			tv_title.setText("体检信息");
			break;
		case R.id.btn_next1:
			layout_personalInfo.setVisibility(View.GONE);
			layout_bookingInfo.setVisibility(View.GONE);
			layout_bookingInfo1.setVisibility(View.VISIBLE);
			bookingNext.setVisibility(View.GONE);
			bookingNext1.setVisibility(View.GONE);
			bookingSumbit.setVisibility(View.VISIBLE);
			iv_ret.setVisibility(View.GONE);
			iv_retBack.setVisibility(View.GONE);
			iv_retBack1.setVisibility(View.VISIBLE);
			tv_goBack.setVisibility(View.VISIBLE);
			tv_title.setText("体检信息");
			break;
		case R.id.iv_retBack:
			layout_personalInfo.setVisibility(View.VISIBLE);
			layout_bookingInfo.setVisibility(View.GONE);
			layout_bookingInfo1.setVisibility(View.GONE);
			bookingNext.setVisibility(View.VISIBLE);
			bookingNext1.setVisibility(View.GONE);
			bookingSumbit.setVisibility(View.GONE);
			iv_ret.setVisibility(View.VISIBLE);
			iv_retBack.setVisibility(View.GONE);
			iv_retBack1.setVisibility(View.GONE);
			tv_goBack.setVisibility(View.GONE);
			tv_title.setText("个人信息");
			break;
		case R.id.iv_retBack1:
			layout_personalInfo.setVisibility(View.GONE);
			layout_bookingInfo.setVisibility(View.VISIBLE);
			layout_bookingInfo1.setVisibility(View.GONE);
			bookingNext.setVisibility(View.GONE);
			bookingNext1.setVisibility(View.VISIBLE);
			bookingSumbit.setVisibility(View.GONE);
			iv_ret.setVisibility(View.GONE);
			iv_retBack.setVisibility(View.VISIBLE);
			iv_retBack1.setVisibility(View.GONE);
			tv_goBack.setVisibility(View.VISIBLE);
			tv_title.setText("体检信息");
			break;
		default:
			break;
		}
	}

}
