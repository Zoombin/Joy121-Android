package com.joy.Fragment.TopFragment;

import android.util.Log;
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
		Log.e(TopPortalsFragment.class.getName()+"", "initTopView");
		MainActivity.mActivity.replaceChildFragment(TopPortalsFragment.class.getName()+"", new PortalsFragment(), true);
	}
}
