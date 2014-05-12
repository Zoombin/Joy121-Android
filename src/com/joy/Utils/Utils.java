package com.joy.Utils;

import android.content.Context;
import android.telephony.TelephonyManager;

/**
 * 工具类
 * @author daiye
 *
 */
public class Utils {

	/**
	 * 获取设备号
	 * @param context
	 * @return
	 */
	public static String getDeviceImei(Context context) {
		String imei = ((TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE))
				.getDeviceId();
		if (imei == null) {
			return "";
		} else {
			return imei;
		}
	}
}
