package com.joy.Activity;

import gejw.android.quickandroid.QActivity;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.joy.R;
import com.joy.Fragment.PersonalFragment;
import com.joy.Utils.Constants;
import com.joy.json.model.UserInfoEntity;

public class PersonalInfoActivity extends QActivity implements OnClickListener {

	private RelativeLayout layout_title;
	private TextView tv_ret;
	private TextView tv_title;
	private TextView tv_loginname_title;
	private TextView tv_loginname;
	private TextView tv_username_title;
	private TextView tv_username;
	private TextView tv_idno_title;
	private TextView tv_idno;
	private TextView tv_gender_title;
	private TextView tv_gender;
	private TextView tv_birthday_title;
	private TextView tv_birthday;
	private TextView tv_mail_title;
	private TextView tv_mail;
	private TextView tv_cellnumber_title;
	private TextView tv_cellnumber;
	private TextView tv_createtime_title;
	private TextView tv_createtime;
	private UserInfoEntity userinfo;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_personal_info);
		
		userinfo = (UserInfoEntity) getIntent().getSerializableExtra(PersonalFragment.EXTRA_USERINFO);
		initView();
		initData();
	}
	
	private void initView() {
		layout_title = (RelativeLayout) findViewById(R.id.layout_title);
		uiAdapter.setMargin(layout_title, LayoutParams.MATCH_PARENT, Constants.TitleHeight, 0, 0, 0, 0);

		tv_ret = (TextView) findViewById(R.id.tv_ret);
		tv_ret.setOnClickListener(this);
		uiAdapter.setTextSize(tv_ret, Constants.TitleRetSize);
		uiAdapter.setMargin(tv_ret, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 20, 0, 0, 0);
		
		tv_title = (TextView) findViewById(R.id.tv_title);
		uiAdapter.setTextSize(tv_title, Constants.TitleSize);
		
		tv_loginname_title = (TextView) findViewById(R.id.tv_loginname_title);
		uiAdapter.setTextSize(tv_loginname_title, 20);
		uiAdapter.setMargin(tv_loginname_title, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 60, 10, 20, 10);
		
		tv_loginname = (TextView) findViewById(R.id.tv_loginname);
		uiAdapter.setTextSize(tv_loginname, 20);
		
		tv_username_title = (TextView) findViewById(R.id.tv_username_title);
		uiAdapter.setTextSize(tv_username_title, 20);
		uiAdapter.setMargin(tv_username_title, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 60, 10, 20, 10);
		
		tv_username = (TextView) findViewById(R.id.tv_username);
		uiAdapter.setTextSize(tv_username, 20);
		
		tv_idno_title = (TextView) findViewById(R.id.tv_idno_title);
		uiAdapter.setTextSize(tv_idno_title, 20);
		uiAdapter.setMargin(tv_idno_title, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 60, 10, 20, 10);
		
		tv_idno = (TextView) findViewById(R.id.tv_idno);
		uiAdapter.setTextSize(tv_idno, 20);
		
		tv_gender_title = (TextView) findViewById(R.id.tv_gender_title);
		uiAdapter.setTextSize(tv_gender_title, 20);
		uiAdapter.setMargin(tv_gender_title, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 60, 10, 20, 10);
		
		tv_gender = (TextView) findViewById(R.id.tv_gender);
		uiAdapter.setTextSize(tv_gender, 20);
		
		tv_birthday_title = (TextView) findViewById(R.id.tv_birthday_title);
		uiAdapter.setTextSize(tv_birthday_title, 20);
		uiAdapter.setMargin(tv_birthday_title, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 60, 10, 20, 10);
		
		tv_birthday = (TextView) findViewById(R.id.tv_birthday);
		uiAdapter.setTextSize(tv_birthday, 20);
		
		tv_mail_title = (TextView) findViewById(R.id.tv_mail_title);
		uiAdapter.setTextSize(tv_mail_title, 20);
		uiAdapter.setMargin(tv_mail_title, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 60, 10, 20, 10);
		
		tv_mail = (TextView) findViewById(R.id.tv_mail);
		uiAdapter.setTextSize(tv_mail, 20);
		
		tv_cellnumber_title = (TextView) findViewById(R.id.tv_cellnumber_title);
		uiAdapter.setTextSize(tv_cellnumber_title, 20);
		uiAdapter.setMargin(tv_cellnumber_title, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 60, 10, 20, 10);
		
		tv_cellnumber = (TextView) findViewById(R.id.tv_cellnumber);
		uiAdapter.setTextSize(tv_cellnumber, 20);
		
		tv_createtime_title = (TextView) findViewById(R.id.tv_createtime_title);
		uiAdapter.setTextSize(tv_createtime_title, 20);
		uiAdapter.setMargin(tv_createtime_title, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 60, 10, 20, 10);
		
		tv_createtime = (TextView) findViewById(R.id.tv_createtime);
		uiAdapter.setTextSize(tv_createtime, 20);
	}
	
	private void initData() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		tv_loginname.setText(userinfo.getLoginName());
		tv_username.setText(userinfo.getUserName());
		tv_idno.setText(userinfo.getIdNo());
		tv_gender.setText(userinfo.getGender().equals("0") ? "男" : "女");
		tv_birthday.setText(sdf.format(new Date(Long.parseLong(userinfo.getBirthDay().substring(6, 18)))));
		tv_mail.setText(userinfo.getMail());
		tv_cellnumber.setText(userinfo.getCellNumber());
		
		tv_createtime.setText(sdf.format(new Date(Long.parseLong(userinfo.getCreateTime().substring(6, 19)))));
	}
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.tv_ret:
			finish();
			break;
		default:
			break;
		}
	}
}
