package com.joy.Activity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.joy.R;
import com.joy.Dialog.CalendarView;
import com.joy.Dialog.CalendarView.OnItemClickListener;
import com.joy.Utils.Constants;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class PhysicalBookingActivity extends BaseActivity implements
		OnClickListener {
	private RelativeLayout layout_title, bookingNext, bookingNext1,
			bookingSumbit;
	private LinearLayout layout_bookingInfo, layout_bookingInfo1,
			layout_personalInfo;
	private ImageView iv_ret, iv_retBack, iv_retBack1;
	private TextView tv_title, tv_goBack;
	private ImageView iv_name, iv_gender, iv_documentType, iv_idNo,
			iv_isMarried, iv_mobile;
	private TextView tv_name, tv_gender, tv_documentType, tv_idNo,
			tv_isMarried, tv_mobile;
	private EditText et_name, et_gender, et_idNo, et_mobile;
	private Spinner sp_isMarried, sp_documentType,sp_bookingCity,sp_bookingCenter;

	private ImageView iv_bookingProductName, iv_bookingCity, iv_bookingCenter,
			iv_trafficRoutes, iv_bookingDate, iv_bookingPlace, iv_bookingPlan,
			iv_addBooking;
	private TextView tv_bookingProductName, tv_bookingCity, tv_bookingCenter,
			tv_trafficRoutes, tv_bookingDate, tv_bookingPlace, tv_bookingPlan,
			tv_addBooking;
	private EditText et_bookingProductName,et_trafficRoutes, et_bookingDate, et_bookingPlace, et_bookingPlan;
	private List<String> data_list, data_documentTypeList,data_bookingCityList,data_bookingCenterList;
	private ArrayAdapter<String> isMarried_adapter, documentType_adapter,bookingCity_adapter,bookingCenter_adapter;
	private Button btn_next, btn_next1, btn_sumbit;
	// 增加体检项
	private CheckBox addBooking1, addBooking2, addBooking3, addBooking4,
			addBooking5, addBooking6, addBooking7, addBooking8, addBooking9,
			addBooking10, addBooking11, addBooking12;

	private CalendarView calendar;
	private ImageButton calendarLeft;
	private TextView calendarCenter;
	private ImageButton calendarRight;
	private SimpleDateFormat format;

	@Override
	protected View ceateView(LayoutInflater inflater, Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.activity_physical_booking, null);
		setContentView(v);
		format = new SimpleDateFormat("yyyy-MM-dd");
		// 获取日历控件对象
		calendar = (CalendarView) findViewById(R.id.calendar);
		calendar.setSelectMore(false); // 单选

		calendarLeft = (ImageButton) findViewById(R.id.calendarLeft);
		calendarCenter = (TextView) findViewById(R.id.calendarCenter);
		calendarRight = (ImageButton) findViewById(R.id.calendarRight);
		try {
			// 设置日历日期
			Date date = format.parse("2015-01-01");
			calendar.setCalendarData(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		// 获取日历中年月 ya[0]为年，ya[1]为月
		String[] ya = calendar.getYearAndmonth().split("-");
		calendarCenter.setText(ya[0] + "年" + ya[1] + "月");
		calendarLeft.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// 点击上一月 同样返回年月
				String leftYearAndmonth = calendar.clickLeftMonth();
				String[] ya = leftYearAndmonth.split("-");
				calendarCenter.setText(ya[0] + "年" + ya[1] + "月");
			}
		});

		calendarRight.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// 点击下一月
				String rightYearAndmonth = calendar.clickRightMonth();
				String[] ya = rightYearAndmonth.split("-");
				calendarCenter.setText(ya[0] + "年" + ya[1] + "月");
			}
		});
		// 设置控件监听，可以监听到点击的每一天
		calendar.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void OnItemClick(Date selectedStartDate,
					Date selectedEndDate, Date downDate) {
				if (calendar.isSelectMore()) {
					Toast.makeText(
							getApplicationContext(),
							format.format(selectedStartDate) + "到"
									+ format.format(selectedEndDate),
							Toast.LENGTH_SHORT).show();
				} else {
					et_bookingDate.setText(format.format(downDate));
					Toast.makeText(getApplicationContext(),
							format.format(downDate), Toast.LENGTH_SHORT).show();
				}
			}
		});
		initViewPersonalInfo();
		initViewBookingInfo();
		initViewBookingInfo1();
		btn_next.setOnClickListener(this);
		btn_next1.setOnClickListener(this);
		return v;
	}

	public void initViewPersonalInfo() {
		layout_title = (RelativeLayout) findViewById(R.id.layout_title);
		uiAdapter.setMargin(layout_title, LayoutParams.MATCH_PARENT,
				Constants.SubTitleHeight, 0, 0, 0, 0);
		iv_ret = (ImageView) findViewById(R.id.iv_ret);
		iv_ret.setOnClickListener(this);

		iv_retBack = (ImageView) findViewById(R.id.iv_retBack);
		iv_retBack.setOnClickListener(this);
		tv_goBack = (TextView) findViewById(R.id.tv_goBack);

		tv_title = (TextView) findViewById(R.id.tv_title);
		uiAdapter.setTextSize(tv_title, Constants.TitleSize);
		layout_personalInfo = (LinearLayout) findViewById(R.id.layout_personalInfo);

		iv_name = (ImageView) findViewById(R.id.iv_name);
		uiAdapter.setMargin(iv_name, LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT, 20, 33, 0, 10);
		tv_name = (TextView) findViewById(R.id.tv_name);
		uiAdapter.setTextSize(tv_name, 18);
		uiAdapter.setMargin(tv_name, LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT, 10, 20, 0, 10);
		et_name = (EditText) findViewById(R.id.et_name);
		uiAdapter.setMargin(et_name, LayoutParams.MATCH_PARENT, 45, 5, 20, 45,
				0);

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
		uiAdapter.setMargin(sp_documentType, LayoutParams.MATCH_PARENT, 45, 5,
				20, 45, 0);

		// 数据
		data_documentTypeList = new ArrayList<String>();
		data_documentTypeList.add("身份证");
		data_documentTypeList.add("居住证");
		data_documentTypeList.add("签证");
		data_documentTypeList.add("港澳通行证");

		// 适配器
		documentType_adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, data_documentTypeList);
		// 设置样式
		documentType_adapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// 加载适配器
		sp_documentType.setAdapter(documentType_adapter);

		iv_idNo = (ImageView) findViewById(R.id.iv_idNo);
		uiAdapter.setMargin(iv_idNo, LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT, 20, 33, 0, 10);
		tv_idNo = (TextView) findViewById(R.id.tv_idNo);
		uiAdapter.setTextSize(tv_idNo, 18);
		uiAdapter.setMargin(tv_idNo, LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT, 10, 20, 0, 10);
		et_idNo = (EditText) findViewById(R.id.et_idNo);
		uiAdapter.setMargin(et_idNo, LayoutParams.MATCH_PARENT, 45, 5, 20, 45,
				0);

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

		// 数据
		data_list = new ArrayList<String>();
		data_list.add("已婚");
		data_list.add("未婚");

		// 适配器
		isMarried_adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, data_list);
		// 设置样式
		isMarried_adapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// 加载适配器
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
		// 下一步
		bookingNext = (RelativeLayout) findViewById(R.id.bookingNext);
		btn_next = (Button) findViewById(R.id.btn_next);
		uiAdapter.setTextSize(btn_next, 24);
	}

	public void initViewBookingInfo() {
		layout_bookingInfo = (LinearLayout) findViewById(R.id.layout_bookingInfo);
		iv_bookingProductName = (ImageView) findViewById(R.id.iv_bookingProductName);
		uiAdapter.setMargin(iv_bookingProductName, LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT, 20, 23, 0, 10);
		tv_bookingProductName = (TextView) findViewById(R.id.tv_bookingProductName);
		uiAdapter.setTextSize(tv_bookingProductName, 18);
		uiAdapter.setMargin(tv_bookingProductName, LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT, 10, 15, 0, 10);
		et_bookingProductName = (EditText) findViewById(R.id.et_bookingProductName);
		uiAdapter.setMargin(et_bookingProductName, LayoutParams.MATCH_PARENT,
				35, 5, 20, 45, 0);

		iv_bookingCity = (ImageView) findViewById(R.id.iv_bookingCity);
		uiAdapter.setMargin(iv_bookingCity, LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT, 20, 23, 0, 10);
		tv_bookingCity = (TextView) findViewById(R.id.tv_bookingCity);
		uiAdapter.setTextSize(tv_bookingCity, 18);
		uiAdapter.setMargin(tv_bookingCity, LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT, 10, 15, 0, 10);
		sp_bookingCity = (Spinner) findViewById(R.id.sp_bookingCity);
		uiAdapter.setMargin(sp_bookingCity, LayoutParams.MATCH_PARENT, 35, 5,
				20, 45, 0);
		
		// 数据
		data_bookingCityList = new ArrayList<String>();
		data_bookingCityList.add("北京");
		data_bookingCityList.add("上海");
		data_bookingCityList.add("南京");
		data_bookingCityList.add("苏州");
		// 适配器
		bookingCity_adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, data_bookingCityList);
		// 设置样式
		bookingCity_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// 加载适配器
		sp_bookingCity.setAdapter(bookingCity_adapter);

		iv_bookingCenter = (ImageView) findViewById(R.id.iv_bookingCenter);
		uiAdapter.setMargin(iv_bookingCenter, LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT, 20, 23, 0, 10);
		tv_bookingCenter = (TextView) findViewById(R.id.tv_bookingCenter);
		uiAdapter.setTextSize(tv_bookingCenter, 18);
		uiAdapter.setMargin(tv_bookingCenter, LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT, 10, 15, 0, 10);
		sp_bookingCenter = (Spinner) findViewById(R.id.sp_bookingCenter);
		uiAdapter.setMargin(sp_bookingCenter, LayoutParams.MATCH_PARENT, 35, 5,
				20, 45, 0);
		
		// 数据
		data_bookingCenterList = new ArrayList<String>();
		data_bookingCenterList.add("北京大学人民医院");
		data_bookingCenterList.add("北京大学第一医院 ");
		data_bookingCenterList.add("北京协和医院西院");
		data_bookingCenterList.add("北京军区总医院");
				// 适配器
		bookingCenter_adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, data_bookingCenterList);
				// 设置样式
		bookingCenter_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
				// 加载适配器
		sp_bookingCenter.setAdapter(bookingCenter_adapter);

		iv_trafficRoutes = (ImageView) findViewById(R.id.iv_trafficRoutes);
		uiAdapter.setMargin(iv_trafficRoutes, LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT, 20, 23, 0, 10);
		tv_trafficRoutes = (TextView) findViewById(R.id.tv_trafficRoutes);
		uiAdapter.setTextSize(tv_trafficRoutes, 18);
		uiAdapter.setMargin(tv_trafficRoutes, LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT, 10, 15, 0, 10);
		et_trafficRoutes = (EditText) findViewById(R.id.et_trafficRoutes);
		uiAdapter.setMargin(et_trafficRoutes, LayoutParams.MATCH_PARENT, 30, 5,
				20, 45, 0);

		iv_bookingDate = (ImageView) findViewById(R.id.iv_bookingDate);
		uiAdapter.setMargin(iv_bookingDate, LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT, 20, 23, 0, 10);
		tv_bookingDate = (TextView) findViewById(R.id.tv_bookingDate);
		uiAdapter.setTextSize(tv_bookingDate, 18);
		uiAdapter.setMargin(tv_bookingDate, LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT, 10, 15, 0, 10);
		et_bookingDate = (EditText) findViewById(R.id.et_bookingDate);
		uiAdapter.setMargin(et_bookingDate, LayoutParams.MATCH_PARENT, 35, 5,
				20, 45, 0);

		iv_bookingPlace = (ImageView) findViewById(R.id.iv_bookingPlace);
		uiAdapter.setMargin(iv_bookingPlace, LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT, 20, 23, 0, 10);
		tv_bookingPlace = (TextView) findViewById(R.id.tv_bookingPlace);
		uiAdapter.setTextSize(tv_bookingPlace, 18);
		uiAdapter.setMargin(tv_bookingPlace, LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT, 10, 15, 0, 10);
		et_bookingPlace = (EditText) findViewById(R.id.et_bookingPlace);
		uiAdapter.setMargin(et_bookingPlace, LayoutParams.MATCH_PARENT, 35, 5,
				20, 45, 0);
		// 下一步
		bookingNext1 = (RelativeLayout) findViewById(R.id.bookingNext1);
		btn_next1 = (Button) findViewById(R.id.btn_next1);
		uiAdapter.setTextSize(btn_next1, 24);
	}

	public void initViewBookingInfo1() {
		layout_bookingInfo1 = (LinearLayout) findViewById(R.id.layout_bookingInfo1);
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
		uiAdapter.setMargin(et_bookingPlan, LayoutParams.MATCH_PARENT, 45, 5,
				20, 45, 0);
		iv_addBooking = (ImageView) findViewById(R.id.iv_addBooking);
		uiAdapter.setMargin(iv_addBooking, LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT, 20, 33, 0, 10);
		tv_addBooking = (TextView) findViewById(R.id.tv_addBooking);
		uiAdapter.setTextSize(tv_addBooking, 18);
		uiAdapter.setMargin(tv_addBooking, LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT, 10, 33, 0, 10);

		addBooking1 = (CheckBox) findViewById(R.id.addBooking1);
		addBooking1.setBackgroundResource(R.drawable.check_physical);

		addBooking2 = (CheckBox) findViewById(R.id.addBooking2);
		addBooking2.setBackgroundResource(R.drawable.check_physical);

		addBooking3 = (CheckBox) findViewById(R.id.addBooking3);
		addBooking3.setBackgroundResource(R.drawable.check_physical);

		addBooking4 = (CheckBox) findViewById(R.id.addBooking4);
		addBooking4.setBackgroundResource(R.drawable.check_physical);

		addBooking5 = (CheckBox) findViewById(R.id.addBooking5);
		addBooking5.setBackgroundResource(R.drawable.check_physical);

		addBooking6 = (CheckBox) findViewById(R.id.addBooking6);
		addBooking6.setBackgroundResource(R.drawable.check_physical);

		addBooking7 = (CheckBox) findViewById(R.id.addBooking7);
		addBooking7.setBackgroundResource(R.drawable.check_physical);

		addBooking8 = (CheckBox) findViewById(R.id.addBooking8);
		addBooking8.setBackgroundResource(R.drawable.check_physical);

		addBooking9 = (CheckBox) findViewById(R.id.addBooking9);
		addBooking9.setBackgroundResource(R.drawable.check_physical);

		addBooking10 = (CheckBox) findViewById(R.id.addBooking10);
		addBooking10.setBackgroundResource(R.drawable.check_physical);

		addBooking11 = (CheckBox) findViewById(R.id.addBooking11);
		addBooking11.setBackgroundResource(R.drawable.check_physical);

		addBooking12 = (CheckBox) findViewById(R.id.addBooking12);
		addBooking12.setBackgroundResource(R.drawable.check_physical);
		// 提交
		bookingSumbit = (RelativeLayout) findViewById(R.id.bookingSumbit);
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
