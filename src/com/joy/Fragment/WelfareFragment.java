package com.joy.Fragment;

import gejw.android.quickandroid.QFragment;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
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
import com.joy.Activity.LogoStoreActivity;
import com.joy.Activity.PostActivity;
import com.joy.Activity.StoreDetailActivity;
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
	
	private LinearLayout layout_logostore;
	private TextView tv_logostore;
	private ImageView iv_logostore;
	private LinearLayout layout_activity;
	private TextView tv_activity;
	private ImageView iv_activity;
	private LinearLayout layout_train;
	private TextView tv_train;
	private ImageView iv_train;
	private LinearLayout layout_notice;
	private TextView tv_notice;
	private ImageView iv_notice;
	private LinearLayout layout_suivey;
	private TextView tv_suivey;
	private ImageView iv_suivey;
	private LinearLayout layout_contact;
	private TextView tv_contact;
	private ImageView iv_contact;
	private LinearLayout layout_groupbuy;
	private TextView tv_groupbuy;
	private ImageView iv_groupbuy;
	private LinearLayout layout_shop;
	private TextView tv_shop;
	private ImageView iv_shop;
	
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
		
		// 公司福利
		layout_welfare = (LinearLayout) v.findViewById(R.id.layout_welfare);
		layout_welfare.setOnClickListener(this);
		
		tv_welfare = (TextView) v.findViewById(R.id.tv_welfare);
		uiAdapter.setTextSize(tv_welfare, TEXTSIZE);
		uiAdapter.setPadding(tv_welfare, 5, 5, 0, 0);
		
		iv_welfare = (ImageView) v.findViewById(R.id.iv_welfare);
		uiAdapter.setMargin(iv_welfare, IMAGEVIEWWH, IMAGEVIEWWH, 0, 10, 0, 20);
		
		// Logo商店
		layout_logostore = (LinearLayout) v.findViewById(R.id.layout_logostore);
		layout_logostore.setOnClickListener(this);
				
		tv_logostore = (TextView) v.findViewById(R.id.tv_logostore);
		uiAdapter.setTextSize(tv_logostore, TEXTSIZE);
		uiAdapter.setPadding(tv_logostore, 5, 5, 0, 0);
				
		iv_logostore = (ImageView) v.findViewById(R.id.iv_logostore);
		uiAdapter.setMargin(iv_logostore, IMAGEVIEWWH, IMAGEVIEWWH, 0, 10, 0, 20);
		
		// 活动
		layout_activity = (LinearLayout) v.findViewById(R.id.layout_activity);
		layout_activity.setOnClickListener(this);
						
		tv_activity = (TextView) v.findViewById(R.id.tv_activity);
		uiAdapter.setTextSize(tv_activity , TEXTSIZE);
		uiAdapter.setPadding(tv_activity , 5, 5, 0, 0);
						
		iv_activity = (ImageView) v.findViewById(R.id.iv_activity);
		uiAdapter.setMargin(iv_activity, IMAGEVIEWWH, IMAGEVIEWWH, 0, 10, 0, 20);
		
		// 培训
		layout_train = (LinearLayout) v.findViewById(R.id.layout_train);
		layout_train.setOnClickListener(this);
								
		tv_train = (TextView) v.findViewById(R.id.tv_train);
		uiAdapter.setTextSize(tv_train, TEXTSIZE);
		uiAdapter.setPadding(tv_train, 5, 5, 0, 0);
								
		iv_train = (ImageView) v.findViewById(R.id.iv_train);
		uiAdapter.setMargin(iv_train, IMAGEVIEWWH, IMAGEVIEWWH, 0, 10, 0, 20);	
				
		// 通知
		layout_notice = (LinearLayout) v.findViewById(R.id.layout_notice);
		layout_notice.setOnClickListener(this);
								
		tv_notice = (TextView) v.findViewById(R.id.tv_notice);
		uiAdapter.setTextSize(tv_notice, TEXTSIZE);
		uiAdapter.setPadding(tv_notice, 5, 5, 0, 0);
								
		iv_notice = (ImageView) v.findViewById(R.id.iv_notice);
		uiAdapter.setMargin(iv_notice, IMAGEVIEWWH, IMAGEVIEWWH, 0, 10, 0, 20);
		
		// 调查
		layout_suivey = (LinearLayout) v.findViewById(R.id.layout_suivey);
		layout_suivey.setOnClickListener(this);
										
		tv_suivey = (TextView) v.findViewById(R.id.tv_suivey);
		uiAdapter.setTextSize(tv_suivey, TEXTSIZE);
		uiAdapter.setPadding(tv_suivey, 5, 5, 0, 0);
										
		iv_suivey = (ImageView) v.findViewById(R.id.iv_suivey);
		uiAdapter.setMargin(iv_suivey, IMAGEVIEWWH, IMAGEVIEWWH, 0, 10, 0, 20);

		// 通讯录
		layout_contact = (LinearLayout) v.findViewById(R.id.layout_contact);
		layout_contact.setOnClickListener(this);
												
		tv_contact = (TextView) v.findViewById(R.id.tv_contact);
		uiAdapter.setTextSize(tv_contact, TEXTSIZE);
		uiAdapter.setPadding(tv_contact, 5, 5, 0, 0);
												
		iv_contact = (ImageView) v.findViewById(R.id.iv_contact);
		uiAdapter.setMargin(iv_contact, IMAGEVIEWWH, IMAGEVIEWWH, 0, 10, 0, 20);
		
		// 限时团购
		layout_groupbuy = (LinearLayout) v.findViewById(R.id.layout_groupbuy);
		layout_groupbuy.setOnClickListener(this);
														
		tv_groupbuy = (TextView) v.findViewById(R.id.tv_groupbuy);
		uiAdapter.setTextSize(tv_groupbuy, TEXTSIZE);
		uiAdapter.setPadding(tv_groupbuy, 5, 5, 0, 0);
														
		iv_groupbuy = (ImageView) v.findViewById(R.id.iv_groupbuy);
		uiAdapter.setMargin(iv_groupbuy, IMAGEVIEWWH, IMAGEVIEWWH, 0, 10, 0, 20);
				
		// 特约商户
		layout_shop = (LinearLayout) v.findViewById(R.id.layout_shop);
		layout_shop.setOnClickListener(this);
														
		tv_shop = (TextView) v.findViewById(R.id.tv_shop);
		uiAdapter.setTextSize(tv_shop, TEXTSIZE);
		uiAdapter.setPadding(tv_shop, 5, 5, 0, 0);
														
		iv_shop = (ImageView) v.findViewById(R.id.iv_shop);
		uiAdapter.setMargin(iv_shop, IMAGEVIEWWH, IMAGEVIEWWH, 0, 10, 0, 20);
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
			intent.putExtra("acttype", "1");
			startActivity(intent);
			break;
		case R.id.layout_suivey:
			intent.setClass(mActivity, SurveyActivity.class);
			startActivity(intent);
			break;
		case R.id.layout_train:
			intent.setClass(mActivity, ActivityActivity.class);
			intent.putExtra("acttype", "2");
			startActivity(intent);
			break;
		case R.id.layout_logostore:
			intent.setClass(mActivity, LogoStoreActivity.class);
			startActivity(intent);
			break;
		default:
			break;
		}
	}
}
