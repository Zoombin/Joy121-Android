package gejw.android.quickandroid;

import com.nostra13.universalimageloader.core.ImageLoader;

import gejw.android.quickandroid.ui.adapter.UIAdapter;
import android.os.Bundle;
import android.support.v4.app.Fragment;

public class QFragment extends Fragment {
	protected UIAdapter uiAdapter;
	protected QActivity mActivity;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		mActivity = (QActivity) getActivity();
		uiAdapter = UIAdapter.getInstance(mActivity);
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		ImageLoader.getInstance().clearMemoryCache();
		System.gc();
		System.gc();
	}

}
