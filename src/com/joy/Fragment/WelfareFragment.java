package com.joy.Fragment;

import gejw.android.quickandroid.QFragment;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.joy.R;
import com.joy.Activity.ActivityActivity;
import com.joy.Activity.PostActivity;
import com.joy.Activity.SurveyActivity;
import com.joy.Activity.WelfareActivity;
import com.joy.Utils.Constants;

/**
 * 公司福利
 * 
 * @author daiye
 * 
 */
public class WelfareFragment extends QFragment implements OnClickListener {

	private final int IMAGEVIEWWH = 80;
	private final int TEXTSIZE = 20;
	private Resources resources;
	private RelativeLayout layout_title;
	private ImageView iv_title;
	private TextView tv_title;
	private LinearLayout layout_welfare;
	private TextView tv_welfare;
	private ImageView iv_welfare;
	private LinearLayout layout_shop;
	private TextView tv_shop;
	private ImageView iv_shop;
	private LinearLayout layout_logoshop;
	private TextView tv_logoshop;
	private ImageView iv_logoshop;
	private LinearLayout layout_week;
	private TextView tv_week;
	private ImageView iv_week;
	private LinearLayout layout_notice;
	private TextView tv_notice;
	private ImageView iv_notice;
	private LinearLayout layout_activity;
	private TextView tv_activity;
	private ImageView iv_activity;
	private LinearLayout layout_suivey;
	private TextView tv_suivey;
	private ImageView iv_suivey;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_welfare, container, false);
		
		resources = getResources();
		initView(v);

		return v;
	}

	private void initView(View v) {
		layout_title = (RelativeLayout) v.findViewById(R.id.layout_title);
		uiAdapter.setMargin(layout_title, LayoutParams.MATCH_PARENT, Constants.TitleHeight, 0, 0, 0, 0);
		
		iv_title = (ImageView) v.findViewById(R.id.iv_title);
		uiAdapter.setMargin(iv_title, Constants.TitleIvWidth, Constants.TitleIvWidth, 10, 0, 10, 0);
		
		tv_title = (TextView) v.findViewById(R.id.tv_title);
		uiAdapter.setTextSize(tv_title, Constants.TitleSize);
		
		// 节日福利
		layout_welfare = (LinearLayout) v.findViewById(R.id.layout_welfare);
		layout_welfare.setOnClickListener(this);
		
		tv_welfare = (TextView) v.findViewById(R.id.tv_welfare);
		uiAdapter.setTextSize(tv_welfare, TEXTSIZE);
		uiAdapter.setPadding(tv_welfare, 5, 5, 0, 0);
		
		iv_welfare = (ImageView) v.findViewById(R.id.iv_welfare);
		uiAdapter.setMargin(iv_welfare, IMAGEVIEWWH, IMAGEVIEWWH, 0, 10, 0, 20);
		
		// 特约商户
		layout_shop = (LinearLayout) v.findViewById(R.id.layout_shop);
		layout_shop.setOnClickListener(this);
		
		tv_shop = (TextView) v.findViewById(R.id.tv_shop);
		uiAdapter.setTextSize(tv_shop, TEXTSIZE);
		uiAdapter.setPadding(tv_shop, 5, 5, 0, 0);
		
		iv_shop = (ImageView) v.findViewById(R.id.iv_shop);
		uiAdapter.setMargin(iv_shop, IMAGEVIEWWH, IMAGEVIEWWH, 0, 10, 0, 20);
		
		// logo商店
		layout_logoshop = (LinearLayout) v.findViewById(R.id.layout_logoshop);
		layout_logoshop.setOnClickListener(this);
		
		tv_logoshop = (TextView) v.findViewById(R.id.tv_logoshop);
		uiAdapter.setTextSize(tv_logoshop, TEXTSIZE);
		uiAdapter.setPadding(tv_logoshop, 5, 5, 0, 0);
		
		iv_logoshop = (ImageView) v.findViewById(R.id.iv_logoshop);
		uiAdapter.setMargin(iv_logoshop, IMAGEVIEWWH, IMAGEVIEWWH, 0, 10, 0, 20);
		
		// 每周一团
		layout_week = (LinearLayout) v.findViewById(R.id.layout_week);
		layout_week.setOnClickListener(this);
		
		tv_week = (TextView) v.findViewById(R.id.tv_week);
		uiAdapter.setTextSize(tv_week, TEXTSIZE);
		uiAdapter.setPadding(tv_week, 5, 5, 0, 0);
		
		iv_week = (ImageView) v.findViewById(R.id.iv_week);
		uiAdapter.setMargin(iv_week, IMAGEVIEWWH, IMAGEVIEWWH, 0, 10, 0, 20);
		
		// 公告
		layout_notice = (LinearLayout) v.findViewById(R.id.layout_notice);
		layout_notice.setOnClickListener(this);
		
		tv_notice = (TextView) v.findViewById(R.id.tv_notice);
		uiAdapter.setTextSize(tv_notice, TEXTSIZE);
		uiAdapter.setPadding(tv_notice, 5, 5, 0, 0);
		
		iv_notice = (ImageView) v.findViewById(R.id.iv_notice);
		uiAdapter.setMargin(iv_notice, IMAGEVIEWWH, IMAGEVIEWWH, 0, 30, 0, 40);
		
		// 活动
		layout_activity = (LinearLayout) v.findViewById(R.id.layout_activity);
		layout_activity.setOnClickListener(this);
		
		tv_activity = (TextView) v.findViewById(R.id.tv_activity);
		uiAdapter.setTextSize(tv_activity, TEXTSIZE);
		uiAdapter.setPadding(tv_activity, 5, 5, 0, 0);
		
		iv_activity = (ImageView) v.findViewById(R.id.iv_activity);
		uiAdapter.setMargin(iv_activity, IMAGEVIEWWH, IMAGEVIEWWH, 0, 30, 0, 40);
		
		// 调查
		layout_suivey = (LinearLayout) v.findViewById(R.id.layout_suivey);
		layout_suivey.setOnClickListener(this);
		
		tv_suivey = (TextView) v.findViewById(R.id.tv_suivey);
		uiAdapter.setTextSize(tv_suivey, TEXTSIZE);
		uiAdapter.setPadding(tv_suivey, 5, 5, 0, 0);
		
		iv_suivey = (ImageView) v.findViewById(R.id.iv_suivey);
		uiAdapter.setMargin(iv_suivey, IMAGEVIEWWH, IMAGEVIEWWH, 0, 30, 0, 40);
	}

	@Override
	public void onClick(View v) {
		Intent intent = new Intent();
		switch (v.getId()) {
		case R.id.layout_welfare:
			intent.setClass(mActivity, WelfareActivity.class);
			startActivity(intent);
			break;
		case R.id.layout_notice:
			intent.setClass(mActivity, PostActivity.class);
			startActivity(intent);
			break;
		case R.id.layout_activity:
			intent.setClass(mActivity, ActivityActivity.class);
			startActivity(intent);
			break;
		case R.id.layout_suivey:
			intent.setClass(mActivity, SurveyActivity.class);
			startActivity(intent);
			break;
		default:
			break;
		}
	}
}
