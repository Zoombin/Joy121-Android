package com.joy.Fragment.TopFragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.joy.R;
import com.joy.Activity.MainActivity;
import com.joy.Fragment.BaseFragment;

public abstract class BaseTopFragment extends BaseFragment {
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
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
