package com.joy.receiver;

import com.joy.Activity.MainActivity;
import com.joy.Utils.SharedPreferencesUtils;
import com.joy.receiver.PushUtil.CommondModel;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import cn.jpush.android.api.JPushInterface;

public class PushReceiver extends BroadcastReceiver {
	String TAG = "PushReceiver";

	@Override
	public void onReceive(Context context, Intent intent) {
		Bundle bundle = intent.getExtras();
		// Log.d(TAG, "[MyReceiver] onReceive - " + intent.getAction() + ", extras: " + printBundle(bundle));

		if (JPushInterface.ACTION_REGISTRATION_ID.equals(intent.getAction())) {
			String regId = bundle.getString(JPushInterface.EXTRA_REGISTRATION_ID);
			// Log.d(TAG, "[MyReceiver] 接收Registration Id : " + regId);

		} else if (JPushInterface.ACTION_MESSAGE_RECEIVED.equals(intent.getAction())) {
			// Log.d(TAG, "[MyReceiver] 接收到推送下来的自定义消息: " + bundle.getString(JPushInterface.EXTRA_MESSAGE));

		} else if (JPushInterface.ACTION_NOTIFICATION_RECEIVED.equals(intent.getAction())) {
			// Log.d(TAG, "[MyReceiver] 接收到推送下来的通知");
			//int notifactionId = bundle.getInt(JPushInterface.EXTRA_NOTIFICATION_ID);

		} else if (JPushInterface.ACTION_NOTIFICATION_OPENED.equals(intent.getAction())) {
			Log.d(TAG, "[MyReceiver] 用户点击打开了通知");
			JPushInterface.reportNotificationOpened(context, bundle.getString(JPushInterface.EXTRA_MSG_ID));
			String pushConten = bundle.getString(JPushInterface.EXTRA_EXTRA);
			Log.e(TAG, "[MyReceiver] 内容是:/" + pushConten);

			String loginname = SharedPreferencesUtils.getLoginName(context);
			if (TextUtils.isEmpty(loginname)) {
				//如果未登录，不进行操作
				return;
			}
			
			PushUtil pUtil = new PushUtil(context);
			CommondModel model = pUtil.getCommond(pushConten);
			//pUtil.dispatch(model);
			
			Bundle pushBundle = new Bundle();
			pushBundle.putSerializable("push", model);
			Intent i = new Intent(context, MainActivity.class);
			i.putExtras(pushBundle);
			i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			context.startActivity(i);

		} else if (JPushInterface.ACTION_RICHPUSH_CALLBACK.equals(intent.getAction())) {
			// Log.d(TAG, "[MyReceiver] 用户收到到RICH PUSH CALLBACK: " + bundle.getString(JPushInterface.EXTRA_EXTRA));
			// 在这里根据 JPushInterface.EXTRA_EXTRA 的内容处理代码，比如打开新的Activity，打开一个网页等..

		} else if (JPushInterface.ACTION_CONNECTION_CHANGE.equals(intent.getAction())) {
			// boolean connected = intent.getBooleanExtra(JPushInterface.EXTRA_CONNECTION_CHANGE,false);
			// Log.e(TAG, "[MyReceiver]" + intent.getAction() +" connected state change to "+connected);
		} else {
			// Log.d(TAG, "[MyReceiver] Unhandled intent - " +  intent.getAction());
		}
	}

	// 打印所有的 intent extra 数据
	private static String printBundle(Bundle bundle) {
		StringBuilder sb = new StringBuilder();
		for (String key : bundle.keySet()) {
			if (key.equals(JPushInterface.EXTRA_NOTIFICATION_ID)) {
				sb.append("key:" + key + ", value:" + bundle.getInt(key));
			} else if (key.equals(JPushInterface.EXTRA_CONNECTION_CHANGE)) {
				sb.append("key:" + key + ", value:" + bundle.getBoolean(key));
			} else if (key.equals(JPushInterface.EXTRA_EXTRA)) {
				sb.append("/推送内容：" + bundle.getString(key));
			} else {
				sb.append("key:" + key + ", value:" + bundle.getString(key));
			}
		}
		return sb.toString();
	}
}
