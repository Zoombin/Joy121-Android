package gejw.android.quickandroid;

import gejw.android.quickandroid.encryption.QRsa;
import gejw.android.quickandroid.encryption.QRsa.RsaKey;
import gejw.android.quickandroid.ui.adapter.UIAdapter;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.util.DisplayMetrics;

import com.nostra13.universalimageloader.core.ImageLoader;

public class QActivity extends FragmentActivity {

	protected QActivity self;
	protected UIAdapter uiAdapter;
	protected int width ,height;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		self = this;
		uiAdapter = UIAdapter.getInstance(self);
		
		DisplayMetrics dm = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dm);
		width = dm.widthPixels;
		height = dm.heightPixels;
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		ImageLoader.getInstance().clearMemoryCache();
	}


	private Fragment curFragment;

	public void replaceFragment(String key, Fragment fragment, Bundle bundle) {
		FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
		Fragment f = getSupportFragmentManager().findFragmentByTag(key);
		if (f == null) {
			f = fragment;

			if (f != null) {
				if (bundle != null)
					f.setArguments(bundle);
				ft.add(R.id.layout_fragment, f, key);
				ft.setTransition(FragmentTransaction.TRANSIT_ENTER_MASK);
			}
		}
		if (curFragment != null) {
			ft.hide(curFragment);
			ft.show(f);
		}
		try {
			ft.commitAllowingStateLoss();
		} catch (Exception e) {

		}
		curFragment = f;
	}

	public UIAdapter getUiAdapter() {
		return uiAdapter;
	}

	public void setUiAdapter(UIAdapter uiAdapter) {
		this.uiAdapter = uiAdapter;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

}
