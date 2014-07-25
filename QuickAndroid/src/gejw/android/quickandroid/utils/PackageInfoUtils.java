package gejw.android.quickandroid.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

/**
 * 程序包工具类
 * 
 * @author gejw(gejw0623@yeah.net)
 * 
 */
public class PackageInfoUtils {
	/**
	 * 获取版本号
	 * 
	 * @param context
	 * @return
	 */
	public static String getVersionName(Context context) {
		try {
			// 获取packagemanager的实例
			PackageManager packageManager = context.getPackageManager();
			// getPackageName()是你当前类的包名，0代表是获取版本信息
			PackageInfo packInfo = packageManager.getPackageInfo(
					context.getPackageName(), 0);
			String version = packInfo.versionName;
			return version;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "1.0";

	}
}
