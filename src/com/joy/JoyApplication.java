package com.joy;

import gejw.android.quickandroid.QApplication;
import gejw.android.quickandroid.log.PLog;
import gejw.android.quickandroid.ui.adapter.UIAdapter;
import android.text.TextUtils;
import android.util.Log;
import cn.jpush.android.api.JPushInterface;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.joy.Utils.MD5;
import com.joy.json.model.CompAppSet;
import com.joy.json.model.CompanyInfoEntity;
import com.joy.json.model.LoginEntity;
import com.joy.json.model.UserInfoEntity;
//import com.lidroid.xutils.HttpUtils;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;

public class JoyApplication extends QApplication {

	public static boolean isDebug = true;

	private static JoyApplication self;

	private UserInfoEntity userinfo;

	/***
	 * APP设置
	 * @return
	 */
	public CompAppSet  getCompAppSet (){
		UserInfoEntity userinfo = getUserinfo();
		if(userinfo != null){
			CompanyInfoEntity cEntity = userinfo.getCompanyInfo();
			if(cEntity != null){
				String appSet = cEntity.getCompAppSetting();
				if(!TextUtils.isEmpty(appSet)){
					try {
						CompAppSet set = new Gson().fromJson(
								appSet, CompAppSet.class);
						return set;
					} catch (JsonSyntaxException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
		return null;
	}
	
	
	
	public UserInfoEntity getUserinfo() {
		return userinfo;
	}

	public void setUserinfo(UserInfoEntity userinfo) {
		this.userinfo = userinfo;
	}

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
		
		JPushInterface.setDebugMode(false);
		JPushInterface.init(this);
		
		createCache();
		UIAdapter.setSize(480, 800);
		PLog.setDebug(true);

		//new HttpUtils().configTimeout(5000);
		
//		try {
//			DesUtil.run();
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		MD5 md5 = new MD5();
//		MD5.run();
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
	}
}
