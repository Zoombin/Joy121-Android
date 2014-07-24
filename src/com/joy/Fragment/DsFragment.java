package com.joy.Fragment;

import gejw.android.quickandroid.QFragment;
import gejw.android.quickandroid.ui.adapter.UIManager;
import android.os.Bundle;
import android.view.View;

import com.umeng.analytics.MobclickAgent;

/**
 * framgment基类
 * @author Robin
 *
 */
public class DsFragment extends QFragment{
	protected View parentView = null;
	protected UIManager mUiManager;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mUiManager = new UIManager(mActivity);
	}
	
	@Override
	public void onDestroy() {
		mUiManager.Destroy();
		super.onDestroy();
	}
	
	public void onResume() {
	    super.onResume();
	    MobclickAgent.onPageStart(getClass().toString()); //统计页面
	}
	public void onPause() {
	    super.onPause();
	    MobclickAgent.onPageEnd(getClass().toString()); 
	}
	
}
