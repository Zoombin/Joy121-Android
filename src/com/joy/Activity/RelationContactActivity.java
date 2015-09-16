package com.joy.Activity;
/******
 * 通讯录中重要联系人详细页面
 * 
 * @author rainbow  2015-9-16
 * 
 */
import com.joy.R;
import com.joy.Utils.Constants;
import com.joy.json.model.ContactEntity;
import com.joy.json.model.RelationContactEntity;

import android.app.ActionBar.LayoutParams;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextPaint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class RelationContactActivity extends BaseActivity implements OnClickListener {
    public static final String relationContactDetails="relationcontactdetails";
    private RelativeLayout layout_title;
    private ImageView iv_contactpic;
	private ImageView iv_ret;
	private TextView tv_title, tv_referUserName, tv_referRelationTypeName,tv_label_phone, tv_label_mobile,tv_mobile,
	                 tv_label_mail,tv_label_referEmail,tv_referEmail;
	private Button btn_addcontact;
	private RelationContactEntity entity;
	private final String RELATIONCONTACT_PREFS = "relationcontact";


	@Override
	protected View ceateView(LayoutInflater inflater, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View v = inflater.inflate(R.layout.activity_relationcontactdetail, null);
		setContentView(v);
		initView();
		Intent intent = getIntent();
		if (intent.hasExtra(relationContactDetails)) {
			entity = (RelationContactEntity) intent.getSerializableExtra(relationContactDetails);
			setData(entity);
		}
		return v;
	}
	
	private void initView(){
		layout_title = (RelativeLayout) findViewById(R.id.layout_title);
		uiAdapter.setMargin(layout_title, LayoutParams.MATCH_PARENT, Constants.SubTitleHeight, 0, 0,
				0, 0);

		iv_ret = (ImageView) findViewById(R.id.iv_ret);
		iv_ret.setOnClickListener(this);

		tv_title = (TextView) findViewById(R.id.tv_title);
		uiAdapter.setTextSize(tv_title, Constants.TitleSize);
		tv_title.setText(R.string.contactdetail);//联系人详细
		
		iv_contactpic = (ImageView) findViewById(R.id.iv_contactpic);
		uiAdapter.setMargin(iv_contactpic, 76, 76, 30, 20, 30, 0);
		
		tv_referUserName = (TextView) findViewById(R.id.tv_referUserName);
		uiAdapter.setMargin(tv_referUserName, LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT, 0, 20, 0, 0);
		uiAdapter.setTextSize(tv_referUserName, 22);
		TextPaint tp = tv_referUserName.getPaint(); 
		tp.setFakeBoldText(true); 
		
		tv_referRelationTypeName = (TextView) findViewById(R.id.tv_referRelationTypeName);
		uiAdapter.setMargin(tv_referRelationTypeName, LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT, 10, 5, 0, 0);
		uiAdapter.setTextSize(tv_referRelationTypeName, 16);
		
		tv_label_phone = (TextView) findViewById(R.id.tv_label_phone);
		uiAdapter.setMargin(tv_label_phone, LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT, 30, 50, 0, 0);
		uiAdapter.setTextSize(tv_label_phone, 24);
		
		tv_label_mobile = (TextView) findViewById(R.id.tv_label_mobile);
		uiAdapter.setMargin(tv_label_mobile, LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT, 80, 10, 0, 0);
		uiAdapter.setTextSize(tv_label_mobile, 20);
		
		tv_mobile = (TextView) findViewById(R.id.tv_mobile);
		uiAdapter.setMargin(tv_mobile, LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT, 15, 10, 0, 0);
		uiAdapter.setTextSize(tv_mobile, 20);
		tv_mobile.setTextColor(Color.rgb(51, 165, 235));
		tv_mobile.setOnClickListener(this);
		
		tv_label_mail = (TextView) findViewById(R.id.tv_label_mail);
		uiAdapter.setMargin(tv_label_mail, LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT, 30, 50, 0, 0);
		uiAdapter.setTextSize(tv_label_mail, 24);
		
		tv_label_referEmail = (TextView) findViewById(R.id.tv_label_referEmail);
		uiAdapter.setMargin(tv_label_referEmail, LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT, 80, 10, 0, 0);
		uiAdapter.setTextSize(tv_label_referEmail, 20);
		
		tv_referEmail = (TextView) findViewById(R.id.tv_referEmail);
		uiAdapter.setMargin(tv_referEmail, LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT, 15, 10, 0, 0);
		uiAdapter.setTextSize(tv_referEmail, 20);
		tv_referEmail.setTextColor(Color.rgb(51, 165, 235));
		tv_referEmail.setOnClickListener(this);
		
		btn_addcontact = (Button) findViewById(R.id.btn_addcontact);
		uiAdapter.setMargin(btn_addcontact, LayoutParams.MATCH_PARENT,
				46, 10, 50, 10, 50);
		uiAdapter.setTextSize(btn_addcontact, 24);
		int color = 0;
		if (appSet != null) {
			try {
				color = Color.parseColor(appSet.getColor2());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (color != 0) {
			// 设置颜色
			btn_addcontact.setBackgroundColor(color);
		}
		btn_addcontact.setOnClickListener(this);
	}
	/*****
	 * 设置数据
	 * 
	 * @param storelist
	 */
	private void setData(RelationContactEntity entity) {
        tv_referUserName.setText(entity.getReferUserName()!=null ? entity.getReferUserName() : "");
		tv_referRelationTypeName.setText(entity.getReferRelationTypeName()!=null ? entity.getReferRelationTypeName() : "");
		tv_mobile.setText(entity.getReferMobile()!=null ? entity.getReferMobile() : "");
		tv_referEmail.setText(entity.getReferEmail()!=null ? entity.getReferEmail() : "");
		
	}
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.iv_ret:
			finish();
			break;
		case R.id.btn_addcontact:
			Intent contactIntent = new Intent(Intent.ACTION_INSERT, Uri.withAppendedPath(
					Uri.parse("content://com.android.contacts"), "contacts"));
			contactIntent.setType("vnd.android.cursor.dir/person");
			contactIntent.putExtra(android.provider.ContactsContract.Intents.Insert.NAME, entity.getReferUserName());
			contactIntent.putExtra(android.provider.ContactsContract.Intents.Insert.COMPANY, entity.getReferRelationTypeName());
			contactIntent.putExtra(android.provider.ContactsContract.Intents.Insert.PHONE,entity.getReferMobile());
			contactIntent.putExtra(android.provider.ContactsContract.Intents.Insert.EMAIL, entity.getReferEmail());
		    startActivity(contactIntent);
			break;
		case R.id.tv_mobile:
			Intent phoneIntent = new Intent(android.content.Intent.ACTION_DIAL, Uri.parse("tel:+"+entity.getReferMobile()));
			startActivity(phoneIntent);
			break;
		case R.id.tv_referEmail:
			Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
			String[] recipients = new String[]{entity.getReferEmail(), "",};
			emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, recipients);
			emailIntent.setType("text/plain");
			startActivity(Intent.createChooser(emailIntent, "Send mail..."));
			break;
		default:
			break;
		}
	}
}
