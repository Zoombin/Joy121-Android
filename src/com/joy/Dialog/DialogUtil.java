package com.joy.Dialog;

import com.joy.R;

import android.R.integer;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.widget.Toast;

public class DialogUtil {
	private Context context;
	private ProgressDialog progressDialog;

	public DialogUtil(Context context) {
		this.context = context;
	}

	/** 显示等待对话框 */
	public void showProgressDialog() {
		showProgressDialog("数据获取中");
	}

	/** 显示等待对话框 */
	public void showProgressDialog(String str) {
		showProgressDialog(str, "请等待……");
	}

	/** 显示等待对话框 */
	public void showProgressDialog(String title, String message) {
		if (progressDialog == null) {
			progressDialog = new ProgressDialog(context);
		}
		progressDialog.setTitle(title);
		progressDialog.setMessage(message);
		progressDialog.show();
	}

	/** 可取消进度框 */
	public void setProgressDialogCancelable(boolean b) {
		progressDialog.setCancelable(b);
	}

	/** 取消显示进度框 */
	public void hideProgressDialog() {
		if (progressDialog != null && progressDialog.isShowing()) {
			progressDialog.dismiss();
			progressDialog.cancel();
		}
	}

	/**
	 * showDialog
	 * 
	 * @param title
	 *            标题
	 * @param incoId
	 *            LogoId
	 * @param message
	 *            信息
	 * @param pStr
	 *            积极按钮
	 * @param nStr
	 *            消极按钮
	 * @param callback
	 *            回调
	 */
	public void showDialog(String message, final DialogButtonClickCallback callback) {
		showDialog("提示", R.drawable.app_icon, message, "确定", "取消", callback);
	}

	public void showDialog(String message, String pStr, String nStr, final DialogButtonClickCallback callback) {
		showDialog("提示", R.drawable.app_icon, message, pStr, nStr, callback);
	}
	
	public void showDialog(String message, int icon ,String pStr, String nStr, final DialogButtonClickCallback callback) {
		showDialog("提示", icon, message, pStr, nStr, callback);
	}

	public void showDialog(String title, String message, String pStr, String nStr, final DialogButtonClickCallback callback) {
		showDialog(title, R.drawable.app_icon, message, pStr, nStr, callback);
	}

	public void showDialog(String title, int incoId, String message, String pStr, String nStr, final DialogButtonClickCallback callback) {
		AlertDialog.Builder builder = new Builder(context);
		builder.setTitle(title);
		builder.setIcon(incoId);
		builder.setMessage(message);
		builder.setPositiveButton(pStr, new OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				callback.positiveButtonClick();
				dialog.dismiss();
			}
		});
		builder.setNegativeButton(nStr, new OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				callback.negativeButtonClick();
				dialog.dismiss();
			}
		});
		builder.create().show();
	}

	/** 长显示 Toast */
	public void showToastLong(String msg) {
		Toast.makeText(context, "" + msg, Toast.LENGTH_LONG).show();
	}

	/** 短显示 Toast */
	public void showToastShort(String msg) {
		Toast.makeText(context, "" + msg, Toast.LENGTH_SHORT).show();
	}

	public interface DialogButtonClickCallback {
		// 按钮点击
		public void positiveButtonClick();

		// 按钮点击
		public void negativeButtonClick();
	}
}
