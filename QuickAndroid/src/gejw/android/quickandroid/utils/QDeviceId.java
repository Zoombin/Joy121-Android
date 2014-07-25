package gejw.android.quickandroid.utils;

import gejw.android.quickandroid.encryption.QMD5;

import java.util.Random;
import java.util.UUID;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.telephony.TelephonyManager;

public class QDeviceId {
	public static String getDeviceId(Context context) {
		SharedPreferences preferences = context.getSharedPreferences(
				"systeminfo", 0);
		if (!preferences.getString("deviceid", "").equals("")) {
			return preferences.getString("deviceid", "");
		}
		StringBuilder deviceId = new StringBuilder();
		// 渠道标志

		try {

			// wifi mac地址
			WifiManager wifi = (WifiManager) context
					.getSystemService(Context.WIFI_SERVICE);
			WifiInfo info = wifi.getConnectionInfo();
			String wifiMac = info.getMacAddress();
			if (!isEmpty(wifiMac)) {
				deviceId.append(wifiMac);
			}

			// IMEI（imei）
			TelephonyManager tm = (TelephonyManager) context
					.getSystemService(Context.TELEPHONY_SERVICE);
			String imei = tm.getDeviceId();
			if (!isEmpty(imei)) {
				deviceId.append(imei);
			}

			// 序列号（sn）
			String sn = tm.getSimSerialNumber();
			if (!isEmpty(sn)) {
				deviceId.append(sn);
			}

			// 如果上面都没有， 则生成一个id：随机码
//			String uuid = UUID.randomUUID().toString();
//			if (!isEmpty(uuid)) {
//				deviceId.append(uuid);
//
//			}
		} catch (Exception e) {
			e.printStackTrace();
		}

//		if (deviceId.equals("")) {
//			deviceId.append(getRandomString(20));
//		}

		String deviceid_md5 = QMD5.GetMD5Code(deviceId.toString());

		preferences.edit().putString("deviceid", deviceid_md5).commit();
		return deviceid_md5;

	}

	private static String getRandomString(int length) { // length表示生成字符串的长度
		String base = "abcdefghijklmnopqrstuvwxyz0123456789";
		Random random = new Random();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < length; i++) {
			int number = random.nextInt(base.length());
			sb.append(base.charAt(number));
		}
		return sb.toString();
	}

	private static boolean isEmpty(String str) {
		return str == null || str.equals("");
	}

}
