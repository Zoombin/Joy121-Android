package com.joy.Activity;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.joy.R;
import com.joy.Fragment.PersonalFragment;
import com.joy.Utils.Constants;
import com.joy.json.model.UserInfoEntity;
import com.nostra13.universalimageloader.core.ImageLoader;

public class PersonalInfoActivity extends BaseActivity implements OnClickListener {

	private RelativeLayout layout_title;
	private ImageView iv_ret;
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
	private TextView tv_companyname_title;
	private TextView tv_companyname;
	private TextView tv_companyaddress_title;
	private TextView tv_companyaddress;
	private TextView tv_companyphone_title;
	private TextView tv_companyphone;
	
	private TextView tv_mail_title;
	private TextView tv_mail;
	private TextView tv_cellnumber_title;
	private TextView tv_cellnumber;
	private TextView tv_createtime_title;
	private TextView tv_createtime;
	private UserInfoEntity userinfo;
	
	private LinearLayout layout_personinfo;
	private TextView tv_name;
	private ImageView img_company;
	private TextView tv_company;
	private TextView tv_points_title;
	private TextView tv_points;
	
	/*@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_personal_info);
		
		userinfo = (UserInfoEntity) getIntent().getSerializableExtra(PersonalFragment.EXTRA_USERINFO);
		initView();
		initData();
	}*/
	
	@Override
	protected View ceateView(LayoutInflater inflater, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View v = inflater.inflate(R.layout.activity_personal_info, null);
		setContentView(v);
		userinfo = (UserInfoEntity) getIntent().getSerializableExtra(PersonalFragment.EXTRA_USERINFO);
		initView();
		initData();
		return v;
	}
	
	private void initView() {
		layout_title = (RelativeLayout) findViewById(R.id.layout_title);
		uiAdapter.setMargin(layout_title, LayoutParams.MATCH_PARENT, Constants.SubTitleHeight, 0, 0, 0, 0);

		iv_ret = (ImageView) findViewById(R.id.iv_ret);
		iv_ret.setOnClickListener(this);
		
		tv_title = (TextView) findViewById(R.id.tv_title);
		uiAdapter.setTextSize(tv_title, Constants.TitleSize);
		
		layout_personinfo = (LinearLayout) findViewById(R.id.layout_personinfo);
		uiAdapter.setMargin(layout_personinfo, LayoutParams.MATCH_PARENT, 113,
				0, 0, 0, 0);

		img_company = (ImageView) findViewById(R.id.img_company);
		uiAdapter.setMargin(img_company, 76, 76, 30, 10, 30, 20);

		tv_name = (TextView) findViewById(R.id.tv_name);
		uiAdapter.setTextSize(tv_name, 20);
		uiAdapter.setMargin(tv_name, LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT, 0, 10, 0, 0);

		tv_company = (TextView) findViewById(R.id.tv_company);
		uiAdapter.setTextSize(tv_company, 16);
		uiAdapter.setMargin(tv_company, LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT, 0, 5, 0, 0);

		tv_points_title = (TextView) findViewById(R.id.tv_points_title);
		uiAdapter.setTextSize(tv_points_title, 16);
		uiAdapter.setMargin(tv_points_title, LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT, 0, 5, 0, 20);

		tv_points = (TextView) findViewById(R.id.tv_points);
		uiAdapter.setTextSize(tv_points, 16);
		uiAdapter.setMargin(tv_points, LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT, 0, 5, 0, 0);
		
		tv_loginname_title = (TextView) findViewById(R.id.tv_loginname_title);
		uiAdapter.setTextSize(tv_loginname_title, 18);
		uiAdapter.setMargin(tv_loginname_title, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 50, 10, 20, 10);
		
		tv_loginname = (TextView) findViewById(R.id.tv_loginname);
		uiAdapter.setTextSize(tv_loginname, 18);
		
		tv_username_title = (TextView) findViewById(R.id.tv_username_title);
		uiAdapter.setTextSize(tv_username_title, 18);
		uiAdapter.setMargin(tv_username_title, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 50, 10, 20, 10);
		
		tv_username = (TextView) findViewById(R.id.tv_username);
		uiAdapter.setTextSize(tv_username, 18);
		
		tv_idno_title = (TextView) findViewById(R.id.tv_idno_title);
		uiAdapter.setTextSize(tv_idno_title, 18);
		uiAdapter.setMargin(tv_idno_title, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 50, 10, 20, 10);
		
		tv_idno = (TextView) findViewById(R.id.tv_idno);
		uiAdapter.setTextSize(tv_idno, 18);
		
		tv_gender_title = (TextView) findViewById(R.id.tv_gender_title);
		uiAdapter.setTextSize(tv_gender_title, 18);
		uiAdapter.setMargin(tv_gender_title, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 50, 10, 20, 10);
		
		tv_gender = (TextView) findViewById(R.id.tv_gender);
		uiAdapter.setTextSize(tv_gender, 18);
		
		tv_birthday_title = (TextView) findViewById(R.id.tv_birthday_title);
		uiAdapter.setTextSize(tv_birthday_title, 18);
		uiAdapter.setMargin(tv_birthday_title, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 50, 10, 20, 10);
		
		tv_birthday = (TextView) findViewById(R.id.tv_birthday);
		uiAdapter.setTextSize(tv_birthday, 18);
		
		tv_companyname_title = (TextView) findViewById(R.id.tv_companyname_title);
		uiAdapter.setTextSize(tv_companyname_title, 18);
		uiAdapter.setMargin(tv_companyname_title, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 50, 10, 20, 10);
		
		tv_companyname = (TextView) findViewById(R.id.tv_companyname);
		uiAdapter.setTextSize(tv_companyname, 18);
		
		tv_companyaddress_title = (TextView) findViewById(R.id.tv_companyaddress_title);
		uiAdapter.setTextSize(tv_companyaddress_title, 18);
		uiAdapter.setMargin(tv_companyaddress_title, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 50, 10, 20, 10);
		
		tv_companyaddress = (TextView) findViewById(R.id.tv_companyaddress);
		uiAdapter.setTextSize(tv_companyaddress, 18);
		
		tv_companyphone_title = (TextView) findViewById(R.id.tv_companyphone_title);
		uiAdapter.setTextSize(tv_companyphone_title, 18);
		uiAdapter.setMargin(tv_companyphone_title, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 50, 10, 20, 10);
		
		tv_companyphone = (TextView) findViewById(R.id.tv_companyphone);
		uiAdapter.setTextSize(tv_companyphone, 18);
		
		tv_mail_title = (TextView) findViewById(R.id.tv_mail_title);
		uiAdapter.setTextSize(tv_mail_title, 18);
		uiAdapter.setMargin(tv_mail_title, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 50, 10, 20, 10);
		
		tv_mail = (TextView) findViewById(R.id.tv_mail);
		uiAdapter.setTextSize(tv_mail, 18);
		
		tv_cellnumber_title = (TextView) findViewById(R.id.tv_cellnumber_title);
		uiAdapter.setTextSize(tv_cellnumber_title, 18);
		uiAdapter.setMargin(tv_cellnumber_title, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 50, 10, 20, 10);
		
		tv_cellnumber = (TextView) findViewById(R.id.tv_cellnumber);
		uiAdapter.setTextSize(tv_cellnumber, 18);
		
		tv_createtime_title = (TextView) findViewById(R.id.tv_createtime_title);
		uiAdapter.setTextSize(tv_createtime_title, 18);
		uiAdapter.setMargin(tv_createtime_title, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 50, 10, 20, 10);
		
		tv_createtime = (TextView) findViewById(R.id.tv_createtime);
		uiAdapter.setTextSize(tv_createtime, 18);
	}
	
	private void initData() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		tv_loginname.setText(userinfo.getLoginName());
		tv_username.setText(userinfo.getUserName());
		tv_idno.setText(userinfo.getIdNo());
		tv_gender.setText(userinfo.getGender().equals("0") ? getResources().getString(R.string.male) : getResources().getString(R.string.female));
		tv_birthday.setText(sdf.format(new Date(Long.parseLong(userinfo.getBirthDay().substring(6, 18)))));
		tv_mail.setText(userinfo.getMail());
		tv_cellnumber.setText(userinfo.getPhoneNumber());
		tv_companyname.setText(userinfo.getCompanyName());
		tv_companyaddress.setText(userinfo.getCompanyInfo().getCompAddr());
		tv_companyphone.setText(userinfo.getCompanyInfo().getCompPhone());
		ImageLoader.getInstance().displayImage(
				Constants.IMGLOGO
						+ userinfo.getCompanyInfo().getCompLogo(),
				img_company);
		tv_name.setText(userinfo.getUserName());
		tv_company.setText(userinfo.getCompanyName());
		tv_points.setText(userinfo.getPoints() + "");
		tv_createtime.setText(sdf.format(new Date(Long.parseLong(userinfo.getCreateTime().substring(6, 19)))));
	}
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.iv_ret:
			finish();
			break;
		default:
			break;
		}
	}
}
