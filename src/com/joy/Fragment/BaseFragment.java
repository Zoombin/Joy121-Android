package com.joy.Fragment;

import gejw.android.quickandroid.QFragment;
import gejw.android.quickandroid.ui.adapter.UIManager;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.joy.JoyApplication;
import com.joy.R;
import com.joy.json.model.CompAppSet;
import com.umeng.analytics.MobclickAgent;

/**
 * framgment基类
 * 
 * @author Robin
 * 
 */
public abstract class BaseFragment extends QFragment {
	protected View parentView = null;
	protected UIManager mUiManager;
	private RelativeLayout baseTitleLayou;
	private TextView baseTvTitle;
	private ImageView baseIvLogo;
	public CompAppSet appSet;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mUiManager = new UIManager(mActivity);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		//baseTitleLayou = find
		appSet = JoyApplication.getInstance().getCompAppSet();
		
		View v = initContentView(inflater, container, savedInstanceState);
		
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
		return v;
	}
	
	protected abstract View initContentView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState);
	

	@Override
	public void onDestroy() {
		mUiManager.Destroy();
		super.onDestroy();
	}

	public void onResume() {
		super.onResume();
		MobclickAgent.onPageStart(getClass().toString()); // 统计页面
	}

	public void onPause() {
		super.onPause();
		MobclickAgent.onPageEnd(getClass().toString());
	}
}
