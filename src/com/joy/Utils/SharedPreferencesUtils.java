package com.joy.Utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * 变量存储
 * @author daiye
 *
 */
public class SharedPreferencesUtils {

	private static final String PREFERENCES_USERINFO = "USER_INFO";
	private static final String KEY_LOGINNAME = "LOGIN_NAME";
	private static final String KEY_LOGINPWD = "LOGIN_PWD";
	private static final String KEY_COMPANY = "COMPANY";
	private static final String KEY_APPSET = "APPSET";
	
	public static void setLoginName(Context context, String loginname) {
		SharedPreferences userInfo = context.getSharedPreferences(PREFERENCES_USERINFO,
				0);
		userInfo.edit().putString(KEY_LOGINNAME, loginname).commit();
	}

	public static String getLoginName(Context context) {
		SharedPreferences userInfo = context.getSharedPreferences(PREFERENCES_USERINFO,
				0);
		return userInfo.getString(KEY_LOGINNAME, "");
	}
	
	public static void setLoginPwd(Context context, String loginpwd) {
		SharedPreferences userInfo = context.getSharedPreferences(PREFERENCES_USERINFO,
				0);
		userInfo.edit().putString(KEY_LOGINPWD, loginpwd).commit();
	}

	public static String getLoginPwd(Context context) {
		SharedPreferences userInfo = context.getSharedPreferences(PREFERENCES_USERINFO,
				0);
		return userInfo.getString(KEY_LOGINPWD, "");
	}
	
	public static void setCompany(Context context, String Company) {
		SharedPreferences userInfo = context.getSharedPreferences(PREFERENCES_USERINFO,
				0);
		userInfo.edit().putString(KEY_COMPANY, Company).commit();
	}

	public static String getCompany(Context context) {
		SharedPreferences userInfo = context.getSharedPreferences(PREFERENCES_USERINFO,
				0);
		return userInfo.getString(KEY_COMPANY, "");
	}
	public static void setAppSet(Context context, String appset) {
		SharedPreferences userInfo = context.getSharedPreferences(PREFERENCES_USERINFO,
				0);
		userInfo.edit().putString(KEY_APPSET, appset).commit();
	}

	public static String getAppSet(Context context) {
		SharedPreferences userInfo = context.getSharedPreferences(PREFERENCES_USERINFO,
				0);
		return userInfo.getString(KEY_APPSET, "");
	}
}
