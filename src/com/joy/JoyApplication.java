package com.joy;

import gejw.android.quickandroid.QApplication;
import gejw.android.quickandroid.io.FileUtil;
import gejw.android.quickandroid.log.PLog;
import gejw.android.quickandroid.ui.adapter.UIAdapter;
import gejw.android.quickandroid.utils.MD5;

import java.io.File;

import android.os.Environment;

import com.lidroid.xutils.HttpUtils;
import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiscCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;

public class JoyApplication extends QApplication {
	
	public static boolean isDebug = true;
	
	private static JoyApplication self;

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
		File cacheFile = getCacheDir();
		if (FileUtil.sdacrdExist()) {
			String dirPath = String.format("%s/joy/%s/",
					Environment.getExternalStorageDirectory(),
					MD5.GetMD5Code("cache"));
			if (!Environment.getExternalStorageState().equals(
					Environment.MEDIA_MOUNTED)) {
				dirPath = String.format("%s/joy/%s/",
						self.getFilesDir().getPath() + "data/com.joy", MD5.GetMD5Code("cache"));
			}
			cacheFile = new File(dirPath);
			if (!cacheFile.exists())
				FileUtil.getInstance().createDirectory(dirPath);
		}
		DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
				.cacheInMemory(true).cacheOnDisc(true)
				.imageScaleType(ImageScaleType.EXACTLY_STRETCHED).build();

		ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
				getApplicationContext())
				.defaultDisplayImageOptions(defaultOptions)
				.discCache(new UnlimitedDiscCache(cacheFile))
				.discCacheFileCount(10000).threadPoolSize(10).build();
		// default
		ImageLoader.getInstance().init(config);
		//	设置屏幕基准分辨率	
		UIAdapter.setSize(480, 800);
		PLog.setDebug(true);
		
		new HttpUtils().configTimeout(5000);
	}
}
