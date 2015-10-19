package com.joy.Activity;

import gejw.android.quickandroid.QActivity;
import android.content.Intent;
import android.content.Intent.ShortcutIconResource;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.Handler;
import cn.jpush.android.api.JPushInterface;

import com.joy.R;

public class SplashActivity extends QActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash);
		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				startActivity(new Intent(getApplication(),
						LoginActivity.class));
				SplashActivity.this.finish();
			}
		}, 2000);// 1秒后跳转到首页
		
		// 创建快捷方式
		createShortcut();
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		
		JPushInterface.onResume(self);
	}
	
	@Override
	protected void onPause() {
		super.onPause();
		
		JPushInterface.onPause(self);
	}
	
	/** 
	* 创建桌面快捷方式 
	*/ 
	private void createShortcut() {
		SharedPreferences setting = getSharedPreferences("user_info", 0);
		// 判断是否第一次启动应用程序（默认为true）
		boolean firstStart = setting.getBoolean("FIRST_START", true);
		// 第一次启动时创建桌面快捷方式
		if (firstStart) {			
			Intent shortcut = new Intent("com.android.launcher.action.INSTALL_SHORTCUT");
			// 快捷方式的名称
			shortcut.putExtra(Intent.EXTRA_SHORTCUT_NAME, getString(R.string.app_name));
			// 不允许重复创建
			shortcut.putExtra("duplicate", false);
			// 指定快捷方式的启动对象 
			Intent shortcutIntent = new Intent(Intent.ACTION_MAIN);
			shortcutIntent.setClassName(this.getPackageName(), this.getClass().getName());
			shortcutIntent.addCategory(Intent.CATEGORY_LAUNCHER); 
			shortcut.putExtra(Intent.EXTRA_SHORTCUT_INTENT, shortcutIntent);
			// 快捷方式的图标
			ShortcutIconResource iconRes = Intent.ShortcutIconResource.fromContext(this, R.drawable.app_icon1);
			shortcut.putExtra(Intent.EXTRA_SHORTCUT_ICON_RESOURCE, iconRes);
			// 发出广播
			sendBroadcast(shortcut);
			// 将第一次启动的标识设置为false 
			Editor editor = setting.edit();
			editor.putBoolean("FIRST_START", false);
			// 提交设置
			editor.commit();
		}
	}
}