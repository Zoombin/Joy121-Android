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

public class EntryManagementAddWorkExperienceInfoDialog extends Dialog{
	TextView tv_title;
    public 	EditText et_workDate,et_workCompany,et_workPosition,et_workAchievement;
	Button buttonYes,buttonNo;
	public EntryManagementAddWorkExperienceInfoDialog(Context context) {
		super(context);
	}
	public EntryManagementAddWorkExperienceInfoDialog(Context context, int theme)
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
	               callback.getAddInfoDialogButtonClickCallback(et_workDate.getText().toString(),
	            		   et_workCompany.getText().toString(), et_workPosition.getText().toString(), 
	            		   et_workAchievement.getText().toString()); 
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
        tv_title.setText("增加工作经验");
        tv_title.setTextSize(18);

        et_workDate=(EditText)findViewById(R.id.et_addInfo1);
        et_workCompany=(EditText)findViewById(R.id.et_addInfo2);
        et_workPosition=(EditText)findViewById(R.id.et_addInfo3);
        et_workAchievement=(EditText)findViewById(R.id.et_addInfo4);
       //设置hint字体大小
          SpannableString workDate = new SpannableString("时间");
       
		 AbsoluteSizeSpan size = new AbsoluteSizeSpan(15, true);// 新建一个属性对象,设置文字的大小
		 workDate.setSpan(size, 0, workDate.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE); // 附加属性到文本
		 et_workDate.setHint(new SpannedString(workDate)); 
		 
		 SpannableString workCompany = new SpannableString("公司");
		 workCompany.setSpan(size, 0, workCompany.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE); 
		 et_workCompany.setHint(new SpannedString(workCompany)); 
		 
		 SpannableString workPosition = new SpannableString("职位");
		 workPosition.setSpan(size, 0, workPosition.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE); 
		 et_workPosition.setHint(new SpannedString(workPosition)); 
		 
		 SpannableString workAchievement = new SpannableString("收获");
		 workAchievement.setSpan(size, 0, workAchievement.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE); 
		 et_workAchievement.setHint(new SpannedString(workAchievement));
	 
        buttonNo = (Button) findViewById(R.id.btn_no);
        buttonNo.setTextSize(16);
        buttonNo.setSingleLine(true);
        
        buttonYes = (Button) findViewById(R.id.btn_yes);
        buttonYes.setTextSize(16);
        buttonYes.setSingleLine(true);  
	}
}
