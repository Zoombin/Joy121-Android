package com.joy.Utils;


import gejw.android.quickandroid.QActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import com.joy.R;
import com.joy.Dialog.EntryManagementDateDialog;
import com.joy.Dialog.ResetPasswordDialog;
import com.joy.Dialog.DialogUtil.MyDialogButtonClickCallback;
import com.joy.Dialog.DialogUtil.SingleDialogButtonClickCallback;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.widget.DatePicker;
import android.widget.DatePicker.OnDateChangedListener;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TimePicker;
import android.widget.TimePicker.OnTimeChangedListener;

/**
 * 日期时间选择控件 
 * @author rainbow  2015/8/15
 */
public class EntryDate extends QActivity {
	private DatePicker datePicker;
	private String initDateTime;
	private Activity activity;
	private EntryManagementDateDialog entrymangementDateDialog;

	/**
	 * 日期时间弹出选择框构造函数
	 * 
	 * @param activity：调用的父activity
	 * @param initDateTime初始日期时间值，作为日期时间初始值
	 */
	public EntryDate(Activity activity, String initDateTime) {
		this.activity = activity;
		this.initDateTime = initDateTime;

	}
	public void dateTimePicKDialog(final EditText inputDate) {
		entrymangementDateDialog=new EntryManagementDateDialog(activity,R.style.dialog);
		entrymangementDateDialog.show();
		entrymangementDateDialog.setButtonYes("设置");
		entrymangementDateDialog.setButtonNo("取消");
		entrymangementDateDialog.setONClickButton(new SingleDialogButtonClickCallback() {
			@Override
			public void positiveButtonClick() {}
			@Override
			public void negativeButtonClick() {}
			@Override
			public void getSingleDialogButtonClickCallback(String string) {
				inputDate.setText(string);
			}
		});
	   }
}
