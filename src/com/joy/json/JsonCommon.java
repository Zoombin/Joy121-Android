package com.joy.json;

import java.util.List;

import android.content.Context;
import android.os.AsyncTask;

import com.joy.Dialog.CustomProgressDialog;
import com.joy.json.operation.OperationBuilder;

public class JsonCommon extends AsyncTask<Void, Void, List<Object>> {

	private Context mContext;
	private OperationBuilder mBuilder;
	private OnOperationListener mListener;
	private CustomProgressDialog customProgressDialog;
	private boolean mDisableprogress = true;
	private String mProgresscontent;
	public static final String PROGRESSQUERY = "正在查询，请稍等......";
	public static final String PROGRESSLOGIN = "正在登录，请稍等......";
	public static final String PROGRESSCOMMIT = "正在提交，请稍等......";
	public static final String PROGRESSUPDATE = "正在检查，请稍等......";
	
	public interface OnOperationListener {
		void onOperationFinished(List<Object> resList);
		void onOperationError(Exception e);
	}

	public JsonCommon(Context context, OperationBuilder builder,
			OnOperationListener listener, String progresscontent) {
		mContext = context;
		mBuilder = builder;
		mListener = listener;
		mProgresscontent = progresscontent;
	}

	public JsonCommon(Context context, OperationBuilder builder,
			OnOperationListener listener, boolean disableprogress) {
		mContext = context;
		mBuilder = builder;
		mListener = listener;
		mDisableprogress = disableprogress;
	}
	
	@Override
	protected void onPreExecute() {
		if (mDisableprogress && mContext!=null && mProgresscontent != null) {
			customProgressDialog = CustomProgressDialog.createDialog(mContext);
			customProgressDialog.setMessage(mProgresscontent);
			customProgressDialog.setCanceledOnTouchOutside(false);
			customProgressDialog.show();
			
		}
		super.onPreExecute();
	}

	@Override
	protected List<Object> doInBackground(Void... params) {
		try {
			List<Object> result = mBuilder.exec();
			return result;
		} catch (Exception e) {
			if (mListener != null) {
				mListener.onOperationError(e);
			}
		}
		return null;
	}

	@Override
	protected void onPostExecute(List<Object> result) {
		if (mDisableprogress) {
			try {
				if (customProgressDialog != null && customProgressDialog.isShowing()) {
					customProgressDialog.dismiss();
					customProgressDialog = null;
				}
			} catch (Exception e) {
				// nothing
			}
		}
		if (mListener == null) {
			return;
		}
		mListener.onOperationFinished(result);
	}
}
