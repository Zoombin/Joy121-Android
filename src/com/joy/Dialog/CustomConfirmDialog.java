package com.joy.Dialog;


import com.joy.R;
import com.joy.Dialog.DialogUtil.DialogButtonClickCallback;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class CustomConfirmDialog extends Dialog {
	TextView tv_title, tv_msg;
	EditText et_msg;
	TextView buttonYes, buttonNo;
    
    public CustomConfirmDialog(Context context) {
        super(context);    
    }
    
	public CustomConfirmDialog(Context context, int theme) {
		super(context, theme);	
	}
	
	public void setTitle(String title) {
		tv_title.setText(title);
	}
	
	public void setEditMessage(String message) {
		et_msg.setText(message);
	}
	
	public String getEditMessage() {
		return et_msg.getText().toString();
	}
	
	public void setMessage(String message) {
		tv_msg.setText(message);
	}
	
	public void setHTMLMessage(String message) {
		tv_msg.setText(Html.fromHtml(message));
	}
	
	public void setMessageGravity(int position) {
		tv_msg.setGravity(position);
	}
	
	public void setInputVisibility() {
		et_msg.setVisibility(View.VISIBLE);
	}
	
	public void setButtonYes(String buttonName) {
		 buttonYes.setText(buttonName);
	}
	
	public TextView getButtonYes() {
		 return buttonYes;
	}
	
	public void setButtonNo(String buttonName) {
		 buttonNo.setText(buttonName);
	}
	
	public TextView getButtonNo() {
		 return buttonNo;
	}
	
	public void setONClickButton(final DialogButtonClickCallback callback) {
		buttonYes.setOnClickListener(new Button.OnClickListener(){    
            public void onClick(View v) {
            	dismiss();
                callback.positiveButtonClick();    
            }    
        });
		buttonNo.setOnClickListener(new Button.OnClickListener(){    
            public void onClick(View v) {
            	dismiss();
                callback.negativeButtonClick();    
            }    
        });
	}
	
	public void setYesButtonListener(OnClickListener onClicklistener) {
		
	}
	
	public void setNoButtonListener(OnClickListener onClicklistener) {
		
	}
        
     protected void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
         setContentView(R.layout.confirm_dialog);
         
         LinearLayout layout_dialog = (LinearLayout)findViewById(R.id.layout_dialog);
         
         tv_title= (TextView) findViewById(R.id.tv_title);
         tv_title.setText("提示");
         tv_title.setTextSize(18);
         
         tv_msg = (TextView) findViewById(R.id.tv_msg);
         tv_msg.setText("确定参加吗？");
         tv_msg.setTextSize(16);
         
         et_msg = (EditText) findViewById(R.id.et_msg);
         et_msg.setTextSize(16);
         

         buttonNo = (TextView) findViewById(R.id.tv_no);
         buttonNo.setTextSize(16);
         buttonNo.setSingleLine(true);
         
         buttonYes = (TextView) findViewById(R.id.tv_yes);
         buttonYes.setTextSize(16);
         buttonYes.setSingleLine(true);
     }    
         
     //called when this dialog is dismissed    
     protected void onStop() {    
         Log.d("TAG","+++++++++++++++++++++++++++");    
     }    
}  