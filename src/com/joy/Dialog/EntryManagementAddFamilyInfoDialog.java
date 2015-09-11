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

public class EntryManagementAddFamilyInfoDialog extends Dialog{
	TextView tv_title;
    public 	EditText et_familyName,et_familyBirthday,et_familyAddress,et_faimlyRelation;
	Button buttonYes,buttonNo;
	public EntryManagementAddFamilyInfoDialog(Context context) {
		super(context);
	}
	public EntryManagementAddFamilyInfoDialog(Context context, int theme)
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
	               callback.getAddInfoDialogButtonClickCallback(et_familyName.getText().toString(),
	            		   et_familyBirthday.getText().toString(), et_familyAddress.getText().toString(), 
	            		   et_faimlyRelation.getText().toString()); 
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
        tv_title.setText("增加家庭信息");
        tv_title.setTextSize(18);
        
        et_familyName=(EditText)findViewById(R.id.et_addInfo1);
        et_familyBirthday=(EditText)findViewById(R.id.et_addInfo2);
        et_familyAddress=(EditText)findViewById(R.id.et_addInfo3);
        et_faimlyRelation=(EditText)findViewById(R.id.et_addInfo4);
       //设置hint字体大小
          SpannableString familyName = new SpannableString("姓名");

		 AbsoluteSizeSpan size = new AbsoluteSizeSpan(15, true);// 新建一个属性对象,设置文字的大小
		 familyName.setSpan(size, 0, familyName.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE); // 附加属性到文本
		 et_familyName.setHint(new SpannedString(familyName)); 
		 
		 SpannableString familyBirthday = new SpannableString("生日");
		 familyBirthday.setSpan(size, 0, familyBirthday.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE); 
		 et_familyBirthday.setHint(new SpannedString(familyBirthday)); 
		 
		 SpannableString familyAddress = new SpannableString("地址");
		 familyAddress.setSpan(size, 0, familyAddress.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE); 
		 et_familyAddress.setHint(new SpannedString(familyAddress)); 
		 
		 SpannableString familyRelation = new SpannableString("关系(父亲，母亲，女儿，儿子)");
		 familyRelation.setSpan(size, 0, familyRelation.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE); 
		 et_faimlyRelation.setHint(new SpannedString(familyRelation));
	 
        buttonNo = (Button) findViewById(R.id.btn_no);
        buttonNo.setTextSize(16);
        buttonNo.setSingleLine(true);
        
        buttonYes = (Button) findViewById(R.id.btn_yes);
        buttonYes.setTextSize(16);
        buttonYes.setSingleLine(true);  
	}
}
