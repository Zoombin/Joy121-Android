package gejw.android.quickandroid.utils;

import java.util.List;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.Context;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.os.Handler;
import android.view.Gravity;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.widget.TextView;

/**
 * 内存监听测试工具类 负责在屏幕左上角显示当前app占用的内存
 * 
 * @author Robin
 * 
 */
public class QMemoryMonitorUtils {
	private WindowManager wm = null;
	private WindowManager.LayoutParams wmParams = null;

	private static QMemoryMonitorUtils mListener = null;
	private boolean isShow = false;
	private TextView myFV;
	private Context mContext;
	private long mDelayMillis;
	private boolean isListening = false;

	private java.text.DecimalFormat decimalFormat = new java.text.DecimalFormat(
			"#.0000");

	public static QMemoryMonitorUtils getInstance() {
		if (mListener == null)
			mListener = new QMemoryMonitorUtils();
		return mListener;
	}

	/**
	 * 开始监听程序
	 * 
	 * @param delayMillis
	 */
	public void start(Context context, long delayMillis) {
		if (isListening)
			return;
		isListening = true;
		mContext = context;
		mDelayMillis = delayMillis;
		show();
		handler.postDelayed(runnable, 0);
	}

	/**
	 * 停止监听
	 */
	public void stop() {
		isListening = false;
		try {
			handler.removeCallbacks(runnable);
			dismiss();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	Handler handler = new Handler();
	Runnable runnable = new Runnable() {

		@Override
		public void run() {
			getMemory();
			// LemoteToast.show(mContext, "--->开始监听内存");
			handler.postDelayed(runnable, mDelayMillis);
		}
	};

	/**
	 * 获取内存使用情况
	 */
	private void getMemory() {
		ActivityManager activityManager = (ActivityManager) mContext
				.getSystemService(mContext.ACTIVITY_SERVICE);

		List<RunningAppProcessInfo> runningAppProcesses = activityManager
				.getRunningAppProcesses();

		int pid = 0;
		for (RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses) {
			if (mContext.getApplicationInfo().packageName
					.equals(runningAppProcessInfo.processName)) {
				pid = runningAppProcessInfo.pid;
				int pids[] = new int[] { pid };
				if (myFV != null)
					myFV.setText(String.format("占用内存:%sMB", decimalFormat
							.format((activityManager.getProcessMemoryInfo(pids)[0]
									.getTotalPss() / 1024f))));
				return;
			}

		}
		

	}

	/**
	 * 显示悬浮窗体
	 */
	private void show() {
		myFV = new TextView(mContext);
		myFV.setTextSize(20);
		myFV.setGravity(Gravity.CENTER);
		myFV.setBackgroundColor(Color.parseColor("#88000000"));
		myFV.setTextColor(Color.WHITE);
		myFV.setGravity(Gravity.CENTER);
		// 获取WindowManager
		wm = (WindowManager) mContext.getSystemService("window");
		// 设置LayoutParams(全局变量）相关参数
		wmParams = new WindowManager.LayoutParams();

		/**
		 * 以下都是WindowManager.LayoutParams的相关属性 具体用途可参考SDK文档
		 */
		wmParams.type = LayoutParams.TYPE_PHONE; // 设置window type
		// wmParams.token = token;
		wmParams.format = PixelFormat.RGBA_8888; // 设置图片格式，效果为背景透明

		// 设置Window flag
		wmParams.flags = LayoutParams.FLAG_NOT_TOUCH_MODAL
				| LayoutParams.FLAG_NOT_FOCUSABLE;
		/*
		 * 下面的flags属性的效果形同“锁定”。 悬浮窗不可触摸，不接受任何事件,同时不影响后面的事件响应。
		 * wmParams.flags=LayoutParams.FLAG_NOT_TOUCH_MODAL |
		 * LayoutParams.FLAG_NOT_FOCUSABLE | LayoutParams.FLAG_NOT_TOUCHABLE;
		 */

		wmParams.gravity = Gravity.LEFT | Gravity.TOP; // 调整悬浮窗口至左上角
		// 设置悬浮窗口长宽数据
		wmParams.width = -2;
		wmParams.height = -2;

		// 显示myFloatView图像
		wm.addView(myFV, wmParams);

		isShow = true;
	}

	/**
	 * 让界面消失
	 */
	private void dismiss() {
		try {
			if (isShow && myFV != null) {
				wm.removeView(myFV);
				isShow = false;
				return;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
