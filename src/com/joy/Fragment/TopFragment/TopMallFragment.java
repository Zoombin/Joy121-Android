package com.joy.Fragment.TopFragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.joy.Activity.MainActivity;
import com.joy.Fragment.MallFragment;


/****
 * 在线商城顶层Fragment
 * @author lsd
 *
 */
public class TopMallFragment extends BaseTopFragment {
	@Override
	protected void initTopView(View v) {
		// TODO Auto-generated method stub
		MainActivity.mActivity.replaceChildFragment("MallFragment"+"", new MallFragment(), true);
	}
}
