package com.joy.Activity;

import com.joy.JoyApplication;
import com.joy.R;
import com.joy.json.model.CompAppSet;

import gejw.android.quickandroid.QActivity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public abstract class BaseActivity extends QActivity {
	private RelativeLayout baseTitleLayou;
	private TextView baseTvTitle;
	private ImageView baseIvLogo;
	CompAppSet appSet;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		LayoutInflater inflater = getLayoutInflater();
		
		appSet = JoyApplication.getInstance().getCompAppSet();
		
		View v = ceateView(inflater,savedInstanceState);
		/***
		 * 设置Nav背景
		 */
		baseTitleLayou = (RelativeLayout) v.findViewById(R.id.layout_title);
		if(appSet != null){
			int navColor = 0;
			try {
				navColor = Color.parseColor(appSet.getColor1());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(navColor !=0){
				baseTitleLayou.setBackgroundColor(navColor);
			}
		}
	}
	
	protected abstract View ceateView(LayoutInflater inflater, Bundle savedInstanceState);

}
