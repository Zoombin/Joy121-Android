package com.joy.Dialog;



import com.joy.R;
import com.joy.R.color;
import com.joy.Dialog.DialogUtil.MyDialogButtonClickCallback;


import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.os.CountDownTimer;

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
 * 未绑定手机号弹出的对话框
 * @author rainbow
 *
 */
public class BindMobileDialog extends Dialog{
	TextView tv_title;
	EditText et_bindMobile,et_securitycode;
	Button buttongetcode,buttonYes,buttonNo;
	
	private TimeCount time;
	
	
	public BindMobileDialog(Context context) {
		super(context);
	}
    public BindMobileDialog(Context context, int theme)
    {
    	super(context, theme);	
    }
    public void setButtonYes(String buttonName) {
		buttonYes.setText(buttonName);
	}
	
	public Button getButtonYes() {
		 return buttonYes;
	}
	
	public void setButtonNo(String buttonName) {
		 buttonNo.setText(buttonName);
	}
	
	public Button getButtonNo() {
		 return buttonNo;
	}
	//获取验证码
	public void setBtnGetCode(String buttonName) {
		buttongetcode.setText(buttonName);
	}
	public Button getBtnGetCode() {
		 return buttongetcode;
	}
	public void setONClickButton(final MyDialogButtonClickCallback callback) {
		buttonNo.setOnClickListener(new Button.OnClickListener(){    
           public void onClick(View v) {
           	dismiss();
               callback.negativeButtonClick();    
           }    
       });
		buttongetcode.setOnClickListener(new Button.OnClickListener(){
			 public void onClick(View v) {
		           	//dismiss();//点击后，dialog页面消失
					  callback.getMycodeButtonClick(et_bindMobile.getText().toString());
					 
					  
		      }    
		});
		buttonYes.setOnClickListener(new Button.OnClickListener(){    
	           public void onClick(View v) {
	           	//dismiss();
	           	callback.getBindMobileDialogButtonClickCallback(et_securitycode.getText().toString()); 
                  
	           }    
	       });
	}
	
	public void setYesButtonListener(OnClickListener onClicklistener) {	
	}
	
	public void setNoButtonListener(OnClickListener onClicklistener) {	
	}
    public void setBtnGetCodeListener(OnClickListener onClicklistener) {
	}
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
        setContentView(R.layout.bindmobile_dialog);
        LinearLayout layout_dialog = (LinearLayout)findViewById(R.id.layout_dialog);
        
        
        tv_title= (TextView) findViewById(R.id.tv_title);
        tv_title.setText("绑定手机");
        tv_title.setTextSize(18);
        
        
        et_bindMobile = (EditText) findViewById(R.id.et_bindMobile);
        et_securitycode=(EditText) findViewById(R.id.et_securitycode);
         //设置hint字体大小
		 SpannableString mobile = new SpannableString("手机号");
		 AbsoluteSizeSpan mobieSize = new AbsoluteSizeSpan(15, true);// 新建一个属性对象,设置文字的大小
		 mobile.setSpan(mobieSize, 0, mobile.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE); // 附加属性到文本
		 et_bindMobile.setHint(new SpannedString(mobile)); 
        
         SpannableString code = new SpannableString("验证码");
         code.setSpan(mobieSize, 0, code.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
         et_securitycode.setHint(new SpannedString(code)); 
        
        buttonNo = (Button) findViewById(R.id.btn_no);
        buttonYes = (Button) findViewById(R.id.btn_yes);
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
		 buttongetcode.setText("重新获取验证码");
		 buttongetcode.setEnabled(true);
		 buttongetcode.setClickable(true);
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
	
