package com.joy.Dialog;
/*
 * rainbow 2015/9/11
 * */
import com.joy.R;
import com.joy.Dialog.DialogUtil.AddFamilyInfoDialogButtonClickCallback;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.SpannedString;
import android.text.method.NumberKeyListener;
import android.text.style.AbsoluteSizeSpan;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class EntryManagementAddFamilyInfoDialog extends Dialog{
	TextView tv_title;
    public 	EditText et_faimlyRelation,et_familyName,et_familyBirthday,et_familyAddress,et_familyMobile,et_unit;
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
	public void setOnClickButton(final AddFamilyInfoDialogButtonClickCallback callback){//增加信息提交按钮
		buttonYes.setOnClickListener(new Button.OnClickListener(){    
	           public void onClick(View v) {
	               //dismiss();
	               callback.getAddFamilyInfoDialogButtonClickCallback(et_faimlyRelation.getText().toString(),
	            		   et_familyName.getText().toString(),et_familyBirthday.getText().toString(), 
	            		   et_familyAddress.getText().toString(), 
	            		   et_familyMobile.getText().toString(),
	            		   et_unit.getText().toString()); 
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
		setContentView(R.layout.activity_entry_addfamilyinfo);
		tv_title= (TextView) findViewById(R.id.tv_title);
        tv_title.setText("增加家庭信息");
        tv_title.setTextSize(18);
        
        et_faimlyRelation=(EditText)findViewById(R.id.et_addRelationShip);
        et_familyName=(EditText)findViewById(R.id.et_addName);
        et_familyBirthday=(EditText)findViewById(R.id.et_addBirthday);
        et_familyAddress=(EditText)findViewById(R.id.et_addAddress);
        et_familyMobile=(EditText)findViewById(R.id.et_addMobile);
        et_unit=(EditText)findViewById(R.id.et_addUnit);
        //限制日期只能输入0-9的数字和'-'
        et_familyBirthday.setKeyListener(new NumberKeyListener(){

			@Override
			public int getInputType() {
				// TODO Auto-generated method stub
			    return android.text.InputType.TYPE_CLASS_PHONE;  
			}

			@Override
			protected char[] getAcceptedChars() {
				// TODO Auto-generated method stub
				char[] myChar={'-','1','2','3','4','5','6','7','8','9','0'};
				return myChar;
			}
        	
        });
//        int newHeight = 90;
//        //用ViewGroup还是用LinearLayout或者是FrameLayout，主要是看EditTex控件所在的父控件布局，如果是LinearLayout，那么这里就要改成LinearLayout.LayoutParams
//        ViewGroup.LayoutParams lp = et_faimlyRelation.getLayoutParams();
//        lp.height = newHeight;
//        et_faimlyRelation.setLayoutParams(lp);
       //设置hint字体大小
        AbsoluteSizeSpan size = new AbsoluteSizeSpan(15, true);// 新建一个属性对象,设置文字的大小
        
         SpannableString familyRelation = new SpannableString("关系(父亲，母亲，女儿，儿子)");
		 familyRelation.setSpan(size, 0, familyRelation.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE); 
		 et_faimlyRelation.setHint(new SpannedString(familyRelation));
        
         SpannableString familyName = new SpannableString("姓名");
		 familyName.setSpan(size, 0, familyName.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE); // 附加属性到文本
		 et_familyName.setHint(new SpannedString(familyName)); 
		 
		 SpannableString familyBirthday = new SpannableString("生日(格式：1990-01-01)");
		 familyBirthday.setSpan(size, 0, familyBirthday.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE); 
		 et_familyBirthday.setHint(new SpannedString(familyBirthday)); 
		 
		 SpannableString familyAddress = new SpannableString("地址");
		 familyAddress.setSpan(size, 0, familyAddress.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE); 
		 et_familyAddress.setHint(new SpannedString(familyAddress)); 
		 
		
		 SpannableString familyMobile = new SpannableString("电话");
		 familyMobile.setSpan(size, 0, familyMobile.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE); 
		 et_familyMobile.setHint(new SpannedString(familyMobile));
		 
		 SpannableString unit = new SpannableString("学习或者工作单位");
		 unit.setSpan(size, 0, unit.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE); 
		 et_unit.setHint(new SpannedString(unit));
	 
        buttonNo = (Button) findViewById(R.id.btn_no);
        buttonNo.setTextSize(16);
        buttonNo.setSingleLine(true);
        
        buttonYes = (Button) findViewById(R.id.btn_yes);
        buttonYes.setTextSize(16);
        buttonYes.setSingleLine(true);  
	}
}
