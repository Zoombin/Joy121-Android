package com.joy.Dialog;


import com.joy.R;
import com.joy.Dialog.DialogUtil.MyDialogButtonClickCallback;
import com.joy.R.color;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.provider.CalendarContract.Colors;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.SpannedString;
import android.text.style.AbsoluteSizeSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
/**
 * 忘记密码
 * @author rainbow
 */

public class ResetPasswordDialog extends Dialog {
	TextView tv_title;
	Button buttonYes, buttonNo;
	EditText et_nloginpwd,et_securitycode,
	         et_forgetloginname,et_confrimnloginpwd;
	Button buttongetcode;
	
	private TimeCount time;
	
	public ResetPasswordDialog(Context context) {
		super(context);
	}
	public ResetPasswordDialog(Context context, int theme) {
		super(context, theme);	
	}
	public void setTitle(String title) {
		tv_title.setText(title);
	}
	//确定按钮
	 public void setButtonYes(String buttonName) {
			buttonYes.setText(buttonName);
		}
		
		public Button getButtonYes() {
			 return buttonYes;
		}
	//取消按钮
		public void setButtonNo(String buttonName) {
			 buttonNo.setText(buttonName);
		}
		
		public Button getButtonNo() {
			 return buttonNo;
		}
	//获取验证码按钮
		public void setBtnGetCode(String buttonName) {
			buttongetcode.setText(buttonName);
		}
		public Button getBtnGetCode() {
			 return buttongetcode;
		}
	//按钮响应事件
	public void setONClickButton(final MyDialogButtonClickCallback callback) {//忘记密码提交按钮
		buttonYes.setOnClickListener(new Button.OnClickListener(){    
           public void onClick(View v) {
               //dismiss();
               callback.forgetPwdDialogButtonClickCallback(et_forgetloginname.getText().toString(),et_nloginpwd.getText().toString(),
            		   et_securitycode.getText().toString(),et_confrimnloginpwd.getText().toString()) ;  
           }    
       });
		buttonNo.setOnClickListener(new Button.OnClickListener(){    
           public void onClick(View v) {
           	dismiss();
               callback.negativeButtonClick();    
           }    
       });
		buttongetcode.setOnClickListener(new Button.OnClickListener(){//获取验证码响应按钮
			 public void onClick(View v) {
		           	//dismiss();//点击后，dialog页面消失
		            callback.getMycodeButtonClick(et_forgetloginname.getText().toString());
		           }    
		});
	}
    public void setYesButtonListener(OnClickListener onClicklistener) {
		
	}
	
	public void setNoButtonListener(OnClickListener onClicklistener) {	
	}
    public void setBtnGetCodeListener(OnClickListener onClicklistener) {
	}
	 protected void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
         setContentView(R.layout.reset_password_dialog);
         LinearLayout layout_dialog = (LinearLayout)findViewById(R.id.layout_dialog);
        
         tv_title= (TextView) findViewById(R.id.tv_title);
         tv_title.setText("提示");
         tv_title.setTextSize(18);
         
         
         et_forgetloginname=(EditText)findViewById(R.id.et_forgetloginname);
         et_securitycode=(EditText)findViewById(R.id.et_securitycode);
         et_nloginpwd=(EditText)findViewById(R.id.et_newpwd);
         et_confrimnloginpwd=(EditText)findViewById(R.id.et_comfirmnewpwd);
        //设置hint字体大小
         SpannableString loginname = new SpannableString("登录名");
		 AbsoluteSizeSpan size = new AbsoluteSizeSpan(15, true);// 新建一个属性对象,设置文字的大小
		 loginname.setSpan(size, 0, loginname.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE); // 附加属性到文本
		 et_forgetloginname.setHint(new SpannedString(loginname)); 
		 
		 SpannableString code = new SpannableString("验证码");
		 code.setSpan(size, 0, loginname.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE); 
		 et_securitycode.setHint(new SpannedString(code)); 
		 
		 SpannableString nloginpwd = new SpannableString("新密码");
		 nloginpwd.setSpan(size, 0, nloginpwd.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE); 
		 et_nloginpwd.setHint(new SpannedString(nloginpwd)); 
         
		 
		 SpannableString confirmnloginpwd = new SpannableString("确认新密码");
		 confirmnloginpwd.setSpan(size, 0, confirmnloginpwd.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE); 
		 et_confrimnloginpwd.setHint(new SpannedString(confirmnloginpwd)); 
         
         buttonNo = (Button) findViewById(R.id.btn_no);
         buttonNo.setTextSize(16);
         buttonNo.setSingleLine(true);
         
         buttonYes = (Button) findViewById(R.id.btn_yes);
         buttonYes.setTextSize(16);
         buttonYes.setSingleLine(true);  
         
         
         buttongetcode = (Button) findViewById(R.id.btn_getcode);
         buttongetcode.setTextSize(10);
 		time = new TimeCount(180000, 1000);//构造CountDownTimer对象
     }    
	 /* 定义一个倒计时的内部类 */
	 class TimeCount extends CountDownTimer {
	 public TimeCount(long millisInFuture, long countDownInterval) {
	 super(millisInFuture, countDownInterval);//参数依次为总时长,和计时的时间间隔
	 }
	 @Override
	 public void onFinish() {//计时完毕时触发
		 buttongetcode.setClickable(true);
		 buttongetcode.setEnabled(true);
		 buttongetcode.setText("重新获取验证码");
	 }
	 @Override
	 public void onTick(long millisUntilFinished){//计时过程显示
		 buttongetcode.setClickable(false);
		 buttongetcode.setEnabled(false);
		 buttongetcode.setText(millisUntilFinished /1000+"秒后重新发送") ;
	 }
	 }
	 public void downTimer()
	 {
		 time.start();//开始计时
	 }

}
