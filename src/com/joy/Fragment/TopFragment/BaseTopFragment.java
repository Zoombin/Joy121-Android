package com.joy.Fragment.TopFragment;

import com.joy.R;
import com.joy.Activity.MainActivity;

import gejw.android.quickandroid.QFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public abstract class BaseTopFragment extends QFragment {
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View v = inflater.inflate(R.layout.fragment_empty, container, false);
		if (MainActivity.mActivity.getCurChildFragmentCount() == 0){
			View chirdView = v.findViewById(R.id.child_fragment);
			
			initTopView(chirdView);
		}
		return v;
	}
	
	/***
	 * 初始化顶层View
	 */
	protected abstract void initTopView(View v);
}
