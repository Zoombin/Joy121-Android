package com.joy.Dialog;

import com.joy.R;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
<<<<<<< HEAD
import android.util.Log;
=======
>>>>>>> 34acdd014449076be19c67258f14caec9568e50d
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class DialogUtil {
	private Context context;
	private View view;
	private ProgressDialog progressDialog;

	public DialogUtil(Context context) {
		this.context = context;
	}
	
	public DialogUtil(Context context, View view) {
		this.context = context;
		this.view = view;
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
		CustomConfirmDialog customConfrimDialog = new CustomConfirmDialog(context, R.style.CustomConfirmDialog);
		customConfrimDialog.show();
		customConfrimDialog.setTitle(title);
		customConfrimDialog.setMessage(message);
		customConfrimDialog.setMessageGravity(Gravity.CENTER);
		customConfrimDialog.setButtonYes(pStr);
		customConfrimDialog.setButtonNo(nStr);
		customConfrimDialog.setONClickButton(callback);
		/*AlertDialog.Builder builder = new Builder(context);
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
		builder.create().show();*/
	}
	
	public void showPwdDialog(String title, int incoId, String message, final String pwd, String pStr, String nStr, final DialogButtonClickCallback callback) {
		final CustomConfirmDialog customConfirmDialog = new CustomConfirmDialog(context, R.style.CustomConfirmDialog);
		customConfirmDialog.show();
		customConfirmDialog.setTitle(title);
		customConfirmDialog.setMessage(message);
		customConfirmDialog.setInputVisibility();
		customConfirmDialog.setButtonYes(pStr);
		customConfirmDialog.setButtonNo(nStr);
		//customConfirmDialog.setONClickButton(callback);
		customConfirmDialog.getButtonYes().setOnClickListener(new Button.OnClickListener(){    
            public void onClick(View v) {
            	
            	if (pwd != null && customConfirmDialog.getEditMessage() != null && pwd.equals(customConfirmDialog.getEditMessage())) {
					callback.positiveButtonClick();
				} else {
					gejw.android.quickandroid.widget.Toast.show(context, "密码错误！");
				}
            	customConfirmDialog.dismiss();
            }    
        });
		customConfirmDialog.getButtonNo().setOnClickListener(new Button.OnClickListener(){    
            public void onClick(View v) {
            	customConfirmDialog.dismiss();
            }    
        });
		/*AlertDialog.Builder builder = new Builder(context);
		builder.setTitle(title);
		builder.setIcon(incoId);
		builder.setMessage(message);
		final EditText pwdEditText = new EditText(context);
		pwdEditText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
		Drawable drawable=context.getResources().getDrawable(R.drawable.searchtext_background);//id为R.drawable.图片名称  
		//pwdEditText.setBackground(drawable);
		builder.setView(pwdEditText);
		builder.setPositiveButton(pStr, new OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				if (pwd != null && pwd.equalsIgnoreCase(pwdEditText.getText().toString())) {
					callback.positiveButtonClick();
				} else {
					gejw.android.quickandroid.widget.Toast.show(context, "密码错误！");
				}
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
		builder.create().show();*/
	}
	
	public void showDownloadDialog(String title, String message, String buttonYes, String buttonNo, String forceUpdate, final DialogButtonClickCallback callback){
		CustomConfirmDialog customConfrimDialog = new CustomConfirmDialog(context, R.style.CustomConfirmDialog);
		customConfrimDialog.show();
		customConfrimDialog.setTitle(title);
		customConfrimDialog.setHTMLMessage(message);
		customConfrimDialog.setButtonYes(buttonYes);
		customConfrimDialog.setButtonNo(buttonNo);
		customConfrimDialog.setONClickButton(callback);
		customConfrimDialog.setForceUpdate(forceUpdate);
		customConfrimDialog.setOnKeyListener(new DialogInterface.OnKeyListener(){
	        public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
	            if (keyCode==KeyEvent.KEYCODE_BACK&&event.getRepeatCount()==0) {
	             return true;
	            } else {
	             return false;
	            }
	        }
	    });
		customConfrimDialog.setCanceledOnTouchOutside(false);
<<<<<<< HEAD
	}
=======
		
		
	}
	
>>>>>>> 34acdd014449076be19c67258f14caec9568e50d
	public void showSingleChoiceDialog(String title, int incoId, String message, String[] items, String pStr, String nStr, final DialogButtonClickCallback callback) {
		AlertDialog.Builder builder = new Builder(context);
		builder.setTitle(title);
		//builder.setIcon(incoId);
		builder.setMessage(message);
		/*builder.setSingleChoiceItems(items, -1, new OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				Log.d("0", "------" + which);
				//dialog.dismiss();
			}
		});*/
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
		builder.show();
	}
/*	
//忘记密码
 public void forgetpwdShowDialog(String title, String pStr, String nStr,String cStr, final MyDialogButtonClickCallback callback)
 {
	    SMSDialog smsdialog=new SMSDialog(context,R.style.dialog);
		smsdialog.show();
		smsdialog.setTitle(title);
	    smsdialog.setButtonYes(pStr);
	    smsdialog.setButtonNo(nStr);
	    smsdialog.setBtnGetCode(cStr);
	    smsdialog.setONClickButton(callback);  
	   // smsdialog.setCanceledOnTouchOutside(false);
 }

 //未绑定手机
 public void bindMobileShowDialog(String title, String pStr, String nStr,String cStr, final MyDialogButtonClickCallback callback)
 {
	    BindMobileDialog bindmobiledialog=new BindMobileDialog(context,R.style.dialog);
	    bindmobiledialog.show();
	  //  bindmobiledialog.setCancelable(false);
	    bindmobiledialog.setTitle(title);
	    bindmobiledialog.setButtonYes(pStr);
	    bindmobiledialog.setButtonNo(nStr);
	    bindmobiledialog.setBtnGetCode(cStr);
	    bindmobiledialog.setONClickButton(callback);   
	   
 }
 */
 //已经绑定手机
 public void bindMobileInfoShowDialog(String title,String msg,String pStr,final DialogButtonClickCallback callback)
 {
	 BindMobileInfoDialog bindmobileinfo=new BindMobileInfoDialog(context,R.style.dialog);//去掉黑色边框
	 bindmobileinfo.show();
	 bindmobileinfo.setTitle(title);
	 bindmobileinfo.setMessage(msg);
	 bindmobileinfo.setButtonYes(pStr);
	 bindmobileinfo.setONClickButton(callback);   
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
	
	public interface MyDialogButtonClickCallback extends DialogButtonClickCallback{
		//获取验证码按钮点击
		public void getMycodeButtonClick(String BindMobile);
		//绑定手机中确定按钮
		public void getBindMobileDialogButtonClickCallback(String MobileCode);
		//忘记密码中确认按钮
		public void forgetPwdDialogButtonClickCallback(String loginname,String nLoginPwd,String confirmCode,String comfirmnewpwd);
	}
}
