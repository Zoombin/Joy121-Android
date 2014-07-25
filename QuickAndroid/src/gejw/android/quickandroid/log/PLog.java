package gejw.android.quickandroid.log;

import android.util.Log;

public class PLog {

	private static String TAG = "Reader";
	private static boolean isDebug = true;

	public static void e(String format, Object... msg) {
		if (isDebug)
			Log.e(TAG, String.format(format, msg));
	}

	public static void d(String format, Object... msg) {
		if (isDebug)
			Log.d(TAG, String.format(format, msg));
	}

	public static void v(String format, Object... msg) {
		if (isDebug)
			Log.v(TAG, String.format(format, msg));
	}

	public static void i(String format, Object... msg) {
		if (isDebug)
			Log.i(TAG, String.format(format, msg));
	}

	public static void w(String format, Object... msg) {
		if (isDebug)
			Log.w(TAG, String.format(format, msg));
	}

	public static boolean isDebug() {
		return isDebug;
	}

	public static void setDebug(boolean isDebug) {
		PLog.isDebug = isDebug;
	}

	public static String getTAG() {
		return TAG;
	}

	public static void setTAG(String tAG) {
		TAG = tAG;
	}
}
