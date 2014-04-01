package com.joy.Activity;

import gejw.android.quickandroid.QActivity;
import android.content.res.Resources;
import android.os.Bundle;

import com.joy.R;

/**
 * 订单详情
 * @author daiye
 *
 */
public class OrderDetailActivity extends QActivity {

	private Resources resources;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_orderdetail);

		resources = getResources();
		initView();
	}

	private void initView() {

	}

}
