package com.joy.Activity;

import com.joy.R;
import com.joy.Utils.Constants;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class PhysicalActivity extends BaseActivity implements OnClickListener {
	private RelativeLayout layout, layout_title;
	private ImageView iv_ret;
	private TextView tv_title;
	private ImageButton imageBooking, imageBookingResults, imageResults;
	final Intent intent = new Intent();
	@Override
	protected View ceateView(LayoutInflater inflater, Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.activity_physical, null);
		setContentView(v);
		initView();
		return v;
	}

	// 初始化
	private void initView() {
		layout = (RelativeLayout) findViewById(R.id.layout);
		layout.getBackground().setAlpha(175);
		layout_title = (RelativeLayout) findViewById(R.id.layout_title);
		uiAdapter.setMargin(layout_title, LayoutParams.MATCH_PARENT,
				Constants.SubTitleHeight, 0, 0, 0, 0);
		iv_ret = (ImageView) findViewById(R.id.iv_ret);
		iv_ret.setOnClickListener(this);
		tv_title = (TextView) findViewById(R.id.tv_title);
		uiAdapter.setTextSize(tv_title, Constants.TitleSize);
		imageBooking = (ImageButton) findViewById(R.id.imageBooking);
		imageBooking
				.setBackgroundResource(R.drawable.activity_physicalbooking_selector);
		imageBooking.setOnClickListener(this);
		imageBookingResults = (ImageButton) findViewById(R.id.imageBookingResults);
		imageBookingResults
				.setBackgroundResource(R.drawable.activity_physicalbookingresults_selector);
		imageBookingResults.setOnClickListener(this);
		imageResults = (ImageButton) findViewById(R.id.imageResults);
		imageResults
				.setBackgroundResource(R.drawable.activity_physicalresults_selector);
		imageResults.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.iv_ret:
			finish();
			break;
		case R.id.imageBooking:
			intent.setClass(self, PhysicalBookingActivity.class);
			startActivity(intent);
			break;
		case R.id.imageBookingResults:
			intent.setClass(self, PhysicalBookingResultsActivity.class);
			startActivity(intent);
			break;
		case R.id.imageResults:
			intent.setClass(self, PhyscialResultsActivity.class);
			startActivity(intent);
			break;
		default:
			break;
		}

	}
}