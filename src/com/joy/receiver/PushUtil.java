package com.joy.receiver;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.joy.Activity.ActivityActivity;
import com.joy.Activity.MainActivity;
import com.joy.Activity.SurveyActivity;
import com.joy.Fragment.portals.logostore.LogoStoreFragment;
import com.joy.Fragment.portals.welfare.WelfareFragment;
/***
 * 推送处理类
 * @author LSD
 *
 */
public class PushUtil {
	private Context context;
	public PushUtil(Context context) {
		// TODO Auto-generated constructor stub
		this.context = context;
	}
	
	public class CommondModel {
		String msgtype;
		String objid;

		public String getMsgtype() {
			return msgtype;
		}

		public void setMsgtype(String msgtype) {
			this.msgtype = msgtype;
		}

		public String getObjid() {
			return objid;
		}

		public void setObjid(String objid) {
			this.objid = objid;
		}
	}

	public CommondModel getCommond(String msg) {
		if (TextUtils.isEmpty(msg)) {
			return null;
		}

		CommondModel entity = new Gson().fromJson(msg, CommondModel.class);
		if (entity != null) {
			return entity;
		}
		return null;
	}

	public void dispatch(CommondModel model) {// separate
		if (model == null) {
			return;
		}
		String key = model.getMsgtype();
		if (TextUtils.isEmpty(key)) {
			return;
		}
		if (key.equals("activity")) {
			// 活动
			Intent intent = new Intent();
			intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			intent.setClass(context, ActivityActivity.class);
			intent.putExtra("acttype", "1");
			context.startActivity(intent);
		} else if (key.equals("benefit")) {
			// 福利
			MainActivity.mActivity.replaceChildFragment(
					"WelfareFragment", new WelfareFragment(), true);
		} else if (key.equals("logostore")) {
			// logostore
			MainActivity.mActivity.replaceChildFragment(
					"LogoStoreFragment", new LogoStoreFragment(), true);
		} else if (key.equals("post")) {
			// 公告
			Intent intent = new Intent();
			intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			intent.setClass(context, ActivityActivity.class);
			intent.putExtra("acttype", "1");
			context.startActivity(intent);
		} else if (key.equals("survey")) {
			// 调查
			Intent intent = new Intent();
			intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			intent.setClass(context, SurveyActivity.class);
			context.startActivity(intent);
		} else if (key.equals("training")) {
			// 培训
			Intent intent = new Intent();
			intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			intent.setClass(context, ActivityActivity.class);
			intent.putExtra("acttype", "2");
			context.startActivity(intent);
		}
	}

}
