package com.joy;

import gejw.android.quickandroid.QApplication;
import gejw.android.quickandroid.log.PLog;
import gejw.android.quickandroid.ui.adapter.UIAdapter;

import com.joy.json.model.UserInfoEntity;
import com.lidroid.xutils.HttpUtils;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;

public class JoyApplication extends QApplication {

	public static boolean isDebug = true;

	private static JoyApplication self;

	public static JoyApplication getSelf() {
		return self;
	}

	public static void setSelf(JoyApplication self) {
		JoyApplication.self = self;
	}

	public static JoyApplication getInstance() {
		return self;
	}

	@Override
	public void onCreate() {
		super.onCreate();
		self = this;

		createCache();
	}

	private void createCache() {
		DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
				.cacheInMemory(true)
				.imageScaleType(ImageScaleType.EXACTLY_STRETCHED).build();

		ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
				getApplicationContext())
				.defaultDisplayImageOptions(defaultOptions).threadPoolSize(10)
				.build();

		// default
		ImageLoader.getInstance().init(config);
		// 设置屏幕基准分辨率
		UIAdapter.setSize(480, 800);
		PLog.setDebug(true);

		new HttpUtils().configTimeout(5000);
	}
}
