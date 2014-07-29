package com.joy.Fragment.TopFragment;

import android.util.Log;
import android.view.View;

import com.joy.Activity.MainActivity;
import com.joy.Fragment.WelfareFragment;

public class TopWelfareFragment extends BaseTopFragment {
	@Override
	protected void initTopView(View v) {
		// TODO Auto-generated method stub
		Log.e("TopWelfareFragment", "initTopView");
		MainActivity.mActivity.replaceChildFragment("TopWelfareFragment", new WelfareFragment(), true);
	}
}
