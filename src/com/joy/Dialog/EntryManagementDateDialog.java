package com.joy.Dialog;



import com.joy.R;
import com.joy.Dialog.DialogUtil.SingleDialogButtonClickCallback;


import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.TextView;
/**
 * 弹出日期提示框
 * @author rainbow
 *
 */
public class EntryManagementDateDialog extends Dialog{
	private TextView tv_title;
	private DatePicker datePicker;
    private Button buttonYes,buttonNo;
	
	
	public EntryManagementDateDialog(Context context) {
		super(context);
	}
    public EntryManagementDateDialog(Context context, int theme)
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
	public void setONClickButton(final SingleDialogButtonClickCallback callback) {
		buttonNo.setOnClickListener(new Button.OnClickListener(){    
           public void onClick(View v) {
           	dismiss();
               callback.negativeButtonClick();    
           }    
       });
		buttonYes.setOnClickListener(new Button.OnClickListener(){    
	           public void onClick(View v) {
	           	callback.getSingleDialogButtonClickCallback(datePicker.getYear()+"-"+
	                                                       (datePicker.getMonth()+1)+"-"+
	           			                                   datePicker.getDayOfMonth()); 
                  dismiss();
	           }    
	       });
	}
	
	public void setYesButtonListener(OnClickListener onClicklistener) {	
	}
	
	public void setNoButtonListener(OnClickListener onClicklistener) {	
	}
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entry_date);
        LinearLayout layout_dialog = (LinearLayout)findViewById(R.id.layout_dialog);
        
        
        tv_title= (TextView) findViewById(R.id.tv_title);
        tv_title.setText("请选择到岗日期");
        tv_title.setTextSize(18);
        datePicker=(DatePicker)findViewById(R.id.datepicker);
        
        buttonNo = (Button) findViewById(R.id.btn_no);
        buttonYes = (Button) findViewById(R.id.btn_yes);
	}
}
	
