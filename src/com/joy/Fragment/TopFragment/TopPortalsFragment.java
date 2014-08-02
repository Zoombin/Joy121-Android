package com.joy.Fragment.TopFragment;

import android.view.View;

import com.joy.Activity.MainActivity;
import com.joy.Fragment.PortalsFragment;


/****
 * 公司门户顶层Fragment
 * @author lsd
 *
 */
public class TopPortalsFragment extends BaseTopFragment {
	@Override
	protected void initTopView(View v) {
		// TODO Auto-generated method stub
		MainActivity.mActivity.replaceChildFragment("PortalsFragment"+"", new PortalsFragment(), true);
	}
}
