package com.joy.Dialog;
/*
 * rainbow 2015/9/11
 * */
import com.joy.R;
import com.joy.Dialog.DialogUtil.AddInfoDialogButtonClickCallback;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.SpannedString;
import android.text.style.AbsoluteSizeSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class EntryManagementAddEductionInfoDialog extends Dialog{
	TextView tv_title;
    public 	EditText et_date,et_school,et_profession,et_achievement;
	Button buttonYes,buttonNo;
	public EntryManagementAddEductionInfoDialog(Context context) {
		super(context);
	}
	public EntryManagementAddEductionInfoDialog(Context context, int theme)
    {
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
	//按钮响应事件
	public void setOnClickButton(final AddInfoDialogButtonClickCallback callback){//增加信息提交按钮
		buttonYes.setOnClickListener(new Button.OnClickListener(){    
	           public void onClick(View v) {
	               //dismiss();
	               callback.getAddInfoDialogButtonClickCallback(et_date.getText().toString(),
	            		   et_school.getText().toString(), et_profession.getText().toString(), 
	            		   et_achievement.getText().toString()); 
	           }    
	       });
			buttonNo.setOnClickListener(new Button.OnClickListener(){    
	           public void onClick(View v) {
	           	dismiss();
	               callback.negativeButtonClick();    
	           }    
	       });
		}
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_entry_addinfo);
		tv_title= (TextView) findViewById(R.id.tv_title);
        tv_title.setText("增加学习经历");
        tv_title.setTextSize(18);
        
        et_date=(EditText)findViewById(R.id.et_addInfo1);
        et_school=(EditText)findViewById(R.id.et_addInfo2);
        et_profession=(EditText)findViewById(R.id.et_addInfo3);
        et_achievement=(EditText)findViewById(R.id.et_addInfo4);
       //设置hint字体大小
          SpannableString date = new SpannableString("时间(格式：2014-05-05)");

		 AbsoluteSizeSpan size = new AbsoluteSizeSpan(15, true);// 新建一个属性对象,设置文字的大小
		 date.setSpan(size, 0, date.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE); // 附加属性到文本
		 et_date.setHint(new SpannedString(date)); 
		 
		 SpannableString school = new SpannableString("学校");
		 school.setSpan(size, 0, school.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE); 
		 et_school.setHint(new SpannedString(school)); 
		 
		 SpannableString profession = new SpannableString("专业");
		 profession.setSpan(size, 0, profession.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE); 
		 et_profession.setHint(new SpannedString(profession)); 
		 
		 SpannableString achievement = new SpannableString("收获");
		 achievement.setSpan(size, 0, achievement.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE); 
		 et_achievement.setHint(new SpannedString(achievement));
	 
        buttonNo = (Button) findViewById(R.id.btn_no);
        buttonNo.setTextSize(16);
        buttonNo.setSingleLine(true);
        
        buttonYes = (Button) findViewById(R.id.btn_yes);
        buttonYes.setTextSize(16);
        buttonYes.setSingleLine(true);  
	}
}
