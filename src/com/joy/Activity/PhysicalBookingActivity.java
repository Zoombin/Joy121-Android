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
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

public class PhysicalBookingActivity extends BaseActivity implements OnClickListener {
	private RelativeLayout layout_title;
	private ImageView iv_ret;
	private TextView tv_title;
	private ImageView iv_bookingDate,iv_bookingPlace,iv_bookingPlan,iv_isMarried,iv_addBooking;
	private TextView tv_bookingDate,tv_bookingPlace,tv_bookingPlan,tv_isMarried,tv_addBooking;
	private EditText et_bookingDate,et_bookingPlace,et_bookingPlan,et_addBooking;
	private Spinner sp_isMarried;
	private List<String> data_list;
	private ArrayAdapter<String> arr_adapter;
	private Button btn_sumbit;

	@Override
	protected View ceateView(LayoutInflater inflater, Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.activity_physical_booking, null);
		setContentView(v);
		initView();
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
        arr_adapter= new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, data_list);
        //设置样式
        arr_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //加载适配器
        sp_isMarried.setAdapter(arr_adapter);
        
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
		// 提交
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
		
		default:
			break;
		}
	}

}
