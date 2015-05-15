package com.joy.Dialog;

import com.joy.R;
import com.joy.Dialog.DialogUtil.DialogButtonClickCallback;
import com.joy.Dialog.DialogUtil.MyDialogButtonClickCallback;
import com.joy.json.model.UserInfoEntity;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**已经绑定手机号显示手机号
 * @author rainbow
 */
public class BindMobileInfoDialog extends Dialog{
    TextView tv_title,tv_msg;
    TextView buttonYes;
    UserInfoEntity userinfo;
    
    public BindMobileInfoDialog(Context context) {
		super(context);
	}
	public BindMobileInfoDialog(Context context, int theme){
	    	super(context, theme);	
	}
	
	
    public void setTitle(String title) {
		tv_title.setText(title);
	}
    public void setMessage(String message) {
		tv_msg.setText(message);
	}
    public void setButtonYes(String buttonName) {
		 buttonYes.setText(buttonName);
	}
	
	public TextView getButtonYes() {
		 return buttonYes;
	}
	//确定响应按钮
	public void setONClickButton(final DialogButtonClickCallback callback){
		buttonYes.setOnClickListener(new Button.OnClickListener(){    
            public void onClick(View v) {
            	dismiss();
                callback.positiveButtonClick();    
            }    
        });
	}
     public void setYesButtonListener(OnClickListener onClicklistener) {
		
	}
	
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.bindmobileinfo_dialog);
		 tv_title= (TextView) findViewById(R.id.tv_title);
	     tv_title.setText("提示");
	     tv_title.setTextSize(18);
	   //已经绑定手机号显示手机号码
	     tv_msg= (TextView) findViewById(R.id.tv_msg);
	    // tv_msg.setText("绑定的手机号码："+userinfo.getBindMobile());
	     tv_msg.setTextSize(18);
	     
	     buttonYes = (TextView) findViewById(R.id.tv_yes);
         buttonYes.setTextSize(16);
         buttonYes.setSingleLine(true);
	}

}
