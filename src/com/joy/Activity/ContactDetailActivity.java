package com.joy.Activity;

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

import com.joy.R;
import com.joy.Utils.Constants;
import com.joy.json.model.ContactEntity;

/******
 * 通讯录详细页面
 * 
 * @author Ryan Zhou 2014-11-06
 * 
 */
public class ContactDetailActivity extends BaseActivity implements OnClickListener {
	public static final String ContactDetails = "contactdetails";
	private RelativeLayout layout_title;
	private ImageView iv_contactpic;
	private ImageView iv_ret;
	private TextView tv_title, tv_personname, tv_englishname, tv_comppos, tv_compdep, tv_company
	, tv_label_phone, tv_label_mobile, tv_mobile, tv_label_work, tv_work, tv_label_fax, tv_fax, tv_label_mail, tv_label_workmail, tv_workmail;
	private Button btn_addcontact;
	private ContactEntity entity;
	private final String CONTACT_PREFS = "contact";
	
	
/*
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_goods);

		Intent intent = getIntent();
		if (intent.hasExtra("detail")) {
			goods = (CategoriesGoods) intent.getSerializableExtra("detail");
			//Log.e("LSD", goods.getAppPicture());
		}
		templist = new ArrayList<StoreDetail>();
		tempColor = new ArrayList<SelectionModel>();
		tempSize = new ArrayList<SelectionModel>();

		initViews();
		initViewPager();
		if (goods != null) {
			getCategorieStore(goods.getId());
		}
	}*/
	
	@Override
	protected View ceateView(LayoutInflater inflater, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View v = inflater.inflate(R.layout.activity_contactdetail, null);
		setContentView(v);
		initView();
		Intent intent = getIntent();
		if (intent.hasExtra(ContactDetails)) {
			entity = (ContactEntity) intent.getSerializableExtra(ContactDetails);
			setData(entity);
		}
		return v;
	}

	private void initView() {
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
		
		tv_personname = (TextView) findViewById(R.id.tv_personname);
		uiAdapter.setMargin(tv_personname, LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT, 0, 20, 0, 0);
		uiAdapter.setTextSize(tv_personname, 22);
		TextPaint tp = tv_personname.getPaint(); 
		tp.setFakeBoldText(true); 
		
		tv_englishname = (TextView) findViewById(R.id.tv_englishname);
		uiAdapter.setMargin(tv_englishname, LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT, 10, 20, 0, 0);
		uiAdapter.setTextSize(tv_englishname, 20);
		
		tv_comppos = (TextView) findViewById(R.id.tv_comppos);
		uiAdapter.setMargin(tv_comppos, LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT, 0, 5, 0, 0);
		uiAdapter.setTextSize(tv_comppos, 16);
		
		tv_compdep = (TextView) findViewById(R.id.tv_compdep);
		uiAdapter.setMargin(tv_compdep, LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT, 10, 5, 0, 0);
		uiAdapter.setTextSize(tv_compdep, 16);
		
		tv_company = (TextView) findViewById(R.id.tv_company);
		uiAdapter.setMargin(tv_company, LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT, 0, 5, 0, 0);
		uiAdapter.setTextSize(tv_company, 16);
		
		
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
		
		tv_label_work = (TextView) findViewById(R.id.tv_label_work);
		uiAdapter.setMargin(tv_label_work, LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT, 80, 10, 0, 0);
		uiAdapter.setTextSize(tv_label_work, 20);
		
		tv_work = (TextView) findViewById(R.id.tv_work);
		uiAdapter.setMargin(tv_work, LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT, 15, 10, 0, 0);
		uiAdapter.setTextSize(tv_work, 20);
		tv_work.setTextColor(Color.rgb(51, 165, 235));
		tv_work.setOnClickListener(this);
		
		tv_label_fax = (TextView) findViewById(R.id.tv_label_fax);
		uiAdapter.setMargin(tv_label_fax, LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT, 80, 10, 0, 0);
		uiAdapter.setTextSize(tv_label_fax, 20);
		
		tv_fax = (TextView) findViewById(R.id.tv_fax);
		uiAdapter.setMargin(tv_fax, LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT, 15, 10, 0, 0);
		uiAdapter.setTextSize(tv_fax, 20);
		tv_fax.setTextColor(Color.rgb(51, 165, 235));
		tv_fax.setOnClickListener(this);
		
		tv_label_mail = (TextView) findViewById(R.id.tv_label_mail);
		uiAdapter.setMargin(tv_label_mail, LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT, 30, 50, 0, 0);
		uiAdapter.setTextSize(tv_label_mail, 24);
		
		tv_label_workmail = (TextView) findViewById(R.id.tv_label_workmail);
		uiAdapter.setMargin(tv_label_workmail, LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT, 80, 10, 0, 0);
		uiAdapter.setTextSize(tv_label_workmail, 20);
		
		tv_workmail = (TextView) findViewById(R.id.tv_workmail);
		uiAdapter.setMargin(tv_workmail, LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT, 15, 10, 0, 0);
		uiAdapter.setTextSize(tv_workmail, 20);
		tv_workmail.setTextColor(Color.rgb(51, 165, 235));
		tv_workmail.setOnClickListener(this);
		
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
	private void setData(ContactEntity entity) {
		tv_personname.setText(entity.getPersonName()!=null ? entity.getPersonName() : "");
		
		tv_englishname.setText(entity.getEnglishName()!=null ? entity.getEnglishName() : "");
		tv_comppos.setText(entity.getComPos()!=null ? entity.getComPos() : "");
		tv_compdep.setText(entity.getComDep()!=null ? entity.getComDep() : "");
		tv_company.setText(entity.getCompanyName()!=null ? entity.getCompanyName() : "");
		tv_mobile.setText(entity.getMobile()!=null ? entity.getMobile() : "");
		tv_work.setText(entity.getPhone()!=null ? entity.getPhone() : "");
		tv_fax.setText(entity.getFax()!=null ? entity.getFax() : "");
		tv_workmail.setText(entity.getEmail()!=null ? entity.getEmail() : "");
		
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
			contactIntent.putExtra(android.provider.ContactsContract.Intents.Insert.NAME, entity.getPersonName());
			contactIntent.putExtra(android.provider.ContactsContract.Intents.Insert.COMPANY, entity.getCompanyName());
			contactIntent.putExtra(android.provider.ContactsContract.Intents.Insert.PHONE,entity.getMobile());
			contactIntent.putExtra(android.provider.ContactsContract.Intents.Insert.SECONDARY_PHONE,entity.getPhone());
			contactIntent.putExtra(android.provider.ContactsContract.Intents.Insert.EMAIL, entity.getEmail());
		    startActivity(contactIntent);
			break;
		case R.id.tv_mobile:
			Intent phoneIntent = new Intent(android.content.Intent.ACTION_DIAL, Uri.parse("tel:+"+entity.getMobile()));
			startActivity(phoneIntent);
			break;
		case R.id.tv_workmail:
			Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
			String[] recipients = new String[]{entity.getEmail(), "",};
			emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, recipients);
			//emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Test");
			//emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, "This is email's message");
			emailIntent.setType("text/plain");
			startActivity(Intent.createChooser(emailIntent, "Send mail..."));
			break;
		default:
			break;
		}
	}
	
}
