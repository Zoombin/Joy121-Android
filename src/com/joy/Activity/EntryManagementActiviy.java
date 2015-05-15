package com.joy.Activity;

import gejw.android.quickandroid.widget.Toast;

import com.joy.JoyApplication;
import com.joy.R;
import com.joy.R.layout;
import com.joy.Utils.Constants;
import com.joy.json.model.CompAppSet;

import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
/**
 * @rainbow  入职管理
 */
public class EntryManagementActiviy extends BaseActivity implements OnClickListener {
    private RelativeLayout layout_title;
    private ImageView iv_ret;
    private TextView  tv_ret;
    private TextView  tv_goback;
    private TextView  tv_title;
    private TextView  tv_name,tv_gender,tv_nation,tv_marital,tv_basicinfoidno,
                      tv_basicinfodegreeno,tv_department,tv_position,tv_entrydate;
    private EditText et_name,et_nation,
                     et_marital,et_basicinfoidno,et_basicinfodegreeno,
                     et_department,et_position,et_entrydate;
    private RadioGroup radiogender;
    private TextView tv_contactway,tv_contactaddress,tv_residentcity,tv_emergencyperson,tv_emergencycontact;
    private EditText et_contactway,et_contactaddress,
                     et_residentcity,et_emergencyperson,et_emergencycontact;
    private TextView tv_accessoryidno;
    private ImageView accessoryimgidno;
    private Button btn_accessoryidno;
    private Button btn_confirmbasicinfo,btn_confirmcontact,btn_confirmaccessory;
    CompAppSet appSet;
	int color;
	private Resources resources;

	@Override
	protected View ceateView(LayoutInflater inflater, Bundle savedInstanceState) {

		resources = getResources();
		color = Color.parseColor("#ffa800");
		appSet = JoyApplication.getInstance().getCompAppSet();
		if (appSet != null) {
			try {
				color = Color.parseColor(appSet.getColor2());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		View v = inflater.inflate(R.layout.activity_entry_basic_info, null);
		setContentView(v);
		initViewBasicInfo();
		initViewContactWay();
		initViewAccessory();
		
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
			btn_confirmbasicinfo.setBackgroundColor(color);
			btn_confirmcontact.setBackgroundColor(color);
			btn_confirmaccessory.setBackgroundColor(color);
		}

		btn_confirmbasicinfo.setOnClickListener(this);
		btn_confirmcontact.setOnClickListener(this);
		btn_confirmaccessory.setOnClickListener(this);
		return v;
	}
	private void initViewBasicInfo()
	{
		//基本信息
		layout_title = (RelativeLayout) findViewById(R.id.layout_title);
		uiAdapter.setMargin(layout_title, LayoutParams.MATCH_PARENT,
				Constants.SubTitleHeight, 0, 0, 0, 0);

		iv_ret = (ImageView) findViewById(R.id.iv_ret);
		iv_ret.setOnClickListener(this);
		
		tv_ret = (TextView) findViewById(R.id.tv_ret);
		tv_ret.setOnClickListener(this);
		
		tv_goback = (TextView) findViewById(R.id.tv_goback);
		tv_goback.setOnClickListener(this);
     
		tv_title = (TextView) findViewById(R.id.tv_title);
		uiAdapter.setTextSize(tv_title, Constants.TitleSize);
		//姓名
		tv_name= (TextView) findViewById(R.id.tv_name);
		uiAdapter.setTextSize(tv_name, 18);
		uiAdapter.setMargin(tv_name, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 45, 20, 0, 10);
		et_name = (EditText) findViewById(R.id.et_name);
		uiAdapter.setMargin(et_name, LayoutParams.MATCH_PARENT, 45, 0, 20,
				10, 0);
		uiAdapter.setPadding(et_name, 10, 0, 0, 0);
		
		//性别
		tv_gender= (TextView) findViewById(R.id.tv_gender);
		uiAdapter.setTextSize(tv_gender, 18);
		uiAdapter.setMargin(tv_gender, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 45, 30, 0, 10);
		radiogender=(RadioGroup)findViewById(R.id.radiogender);
		uiAdapter.setMargin(radiogender, LayoutParams.MATCH_PARENT, 45, 0, 20,
				10, 0);
		uiAdapter.setPadding(radiogender, 10, 0, 0, 0);
		//民族
		tv_nation= (TextView) findViewById(R.id.tv_nation);
		uiAdapter.setTextSize(tv_nation, 18);
		uiAdapter.setMargin(tv_nation, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT,45, 20, 0, 10);
		et_nation = (EditText) findViewById(R.id.et_nation);
		uiAdapter.setMargin(et_nation, LayoutParams.MATCH_PARENT, 45, 0, 20,
				20, 0);
		uiAdapter.setPadding(et_nation, 10, 0, 0, 0);
		
		//婚否
		tv_marital= (TextView) findViewById(R.id.tv_marital);
		uiAdapter.setTextSize(tv_marital, 18);
		uiAdapter.setMargin(tv_marital, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 45, 20, 0, 10);
		et_marital = (EditText) findViewById(R.id.et_marital);
		uiAdapter.setMargin(et_marital, LayoutParams.MATCH_PARENT,45, 0, 20,
				20, 0);
		uiAdapter.setPadding(et_marital, 10, 0, 0, 0);
		
		//身份证号
		tv_basicinfoidno= (TextView) findViewById(R.id.tv_basicifoidno);
		uiAdapter.setTextSize(tv_basicinfoidno, 18);
		uiAdapter.setMargin(tv_basicinfoidno, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 10, 20, 0, 10);
		et_basicinfoidno = (EditText) findViewById(R.id.et_basicicfoidno);
		uiAdapter.setMargin(et_basicinfoidno, LayoutParams.MATCH_PARENT, 45, 0, 20,
				20, 0);
		uiAdapter.setPadding(et_basicinfoidno, 10, 0, 0, 0);
		
		//学历号
		tv_basicinfodegreeno= (TextView) findViewById(R.id.tv_basicinfodegreeno);
		uiAdapter.setTextSize(tv_basicinfodegreeno, 18);
		uiAdapter.setMargin(tv_basicinfodegreeno, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 10, 20, 0, 10);
		et_basicinfodegreeno = (EditText) findViewById(R.id.et_basicinfodegreeno);
		uiAdapter.setMargin(et_basicinfodegreeno, LayoutParams.MATCH_PARENT, 45, 0, 20,
				20, 0);
		uiAdapter.setPadding(et_basicinfodegreeno, 10, 0, 0, 0);
		
		//应聘部门
		tv_department= (TextView) findViewById(R.id.tv_department);
		uiAdapter.setTextSize(tv_department, 18);
		uiAdapter.setMargin(tv_department, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 10, 20, 0, 10);
		et_department = (EditText) findViewById(R.id.et_department);
		uiAdapter.setMargin(et_department, LayoutParams.MATCH_PARENT, 45, 0, 20,
				20, 0);
		uiAdapter.setPadding(et_department, 10, 0, 0, 0);
		
		//应聘职位
		tv_position= (TextView) findViewById(R.id.tv_position);
		uiAdapter.setTextSize(tv_position, 18);
		uiAdapter.setMargin(tv_position, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT,10, 20, 0, 10);
		et_position = (EditText) findViewById(R.id.et_position);
		uiAdapter.setMargin(et_position, LayoutParams.MATCH_PARENT, 45, 0, 20,
				20, 0);
		uiAdapter.setPadding(et_position, 10, 0, 0, 0);
		
		//入职日期
		tv_entrydate= (TextView) findViewById(R.id.tv_entrydate);
		uiAdapter.setTextSize(tv_entrydate, 18);
		uiAdapter.setMargin(tv_entrydate, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 10, 20, 0, 10);
		et_entrydate = (EditText) findViewById(R.id.et_entrydate);
		uiAdapter.setMargin(et_entrydate, LayoutParams.MATCH_PARENT, 45, 0, 20,
				20, 0);
		uiAdapter.setPadding(et_entrydate, 10, 0, 0, 0);
		
        //下一步
		btn_confirmbasicinfo = (Button) findViewById(R.id.btn_confirmbasicinfo);
		uiAdapter.setMargin(btn_confirmbasicinfo, LayoutParams.MATCH_PARENT, 45, 20,20,
				20,0);
		uiAdapter.setTextSize(btn_confirmbasicinfo, 24);
		uiAdapter.setPadding(btn_confirmbasicinfo, 10, 0, 0, 0);
		
		
		
		
		
		
	}
	private void initViewContactWay()
	{
		//联系方式  
		//联系方式
		tv_contactway= (TextView) findViewById(R.id.tv_contactway);
		uiAdapter.setTextSize(tv_contactway, 18);
		uiAdapter.setMargin(tv_contactway, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 40, 20, 0, 10);
        et_contactway = (EditText) findViewById(R.id.et_contactway);
		uiAdapter.setMargin(et_contactway, LayoutParams.MATCH_PARENT, 45, 5, 20,
				20, 0);
		uiAdapter.setPadding(et_contactway, 10, 0, 0, 0);
		
		//通讯地址
		tv_contactaddress= (TextView) findViewById(R.id.tv_contactaddress);
		uiAdapter.setTextSize(tv_contactaddress, 18);
		uiAdapter.setMargin(tv_contactaddress, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 40, 20, 0, 10);
	    et_contactaddress = (EditText) findViewById(R.id.et_contactaddress);
	    uiAdapter.setMargin(et_contactaddress, LayoutParams.MATCH_PARENT, 45, 5, 20,
				20, 0);
	    uiAdapter.setPadding(et_contactaddress, 10, 0, 0, 0);
	    
	    //户口所在地
	    tv_residentcity= (TextView) findViewById(R.id.tv_residentcity);
		uiAdapter.setTextSize(tv_residentcity, 18);
		uiAdapter.setMargin(tv_residentcity, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 20, 20, 0, 10);
	    et_residentcity = (EditText) findViewById(R.id.et_residentcity);
	    uiAdapter.setMargin(et_residentcity, LayoutParams.MATCH_PARENT, 45, 5, 20,
				20, 0);
	    uiAdapter.setPadding(et_residentcity, 10, 0, 0, 0);
	    
	    //紧急联系人
	    tv_emergencyperson= (TextView) findViewById(R.id.tv_emergencyperson);
		uiAdapter.setTextSize(tv_emergencyperson, 18);
		uiAdapter.setMargin(tv_emergencyperson, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 20, 20, 5, 10);
	    et_emergencyperson = (EditText) findViewById(R.id.et_emergencyperson);
	    uiAdapter.setMargin(et_emergencyperson, LayoutParams.MATCH_PARENT, 45, 5, 20,
				20, 0);
	    uiAdapter.setPadding(et_emergencyperson, 10, 0, 0, 0);
	    
	    //紧急联系方式
	    tv_emergencycontact= (TextView) findViewById(R.id.tv_emergencycontact);
		uiAdapter.setTextSize(tv_emergencycontact, 18);
		uiAdapter.setMargin(tv_emergencycontact, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 10, 20, 0, 10);
	    et_emergencycontact = (EditText) findViewById(R.id.et_emergencycontact);
	    uiAdapter.setMargin(et_emergencycontact, LayoutParams.MATCH_PARENT, 45, 5, 20,
				20, 0);
	    uiAdapter.setPadding(et_emergencycontact, 10, 0, 0, 0);
	    //下一步
	    btn_confirmcontact = (Button) findViewById(R.id.btn_confirmcontact);
		uiAdapter.setMargin(btn_confirmcontact, LayoutParams.MATCH_PARENT, 45, 20,20,
				20,0);
		uiAdapter.setTextSize(btn_confirmcontact, 24);
		uiAdapter.setPadding(btn_confirmcontact, 10, 0, 0, 0);
	    
	    
	}
	private void initViewAccessory()
	{
		//上传附件
		tv_accessoryidno = (TextView) findViewById(R.id.tv_accessoryidno);
		uiAdapter.setTextSize(tv_accessoryidno, 20);
		//uiAdapter.setMargin(tv_idno, LayoutParams.MATCH_PARENT, 20,0, 0,
		//		0, 0);
		
		accessoryimgidno = (ImageView) findViewById(R.id.accessoryimgidno);
		
		
		btn_accessoryidno=(Button)findViewById(R.id.btn_accessoryidno);
		uiAdapter.setTextSize(tv_accessoryidno, 20);
		uiAdapter.setMargin(tv_accessoryidno, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 0, 0, 0, 0);
		
		btn_confirmaccessory = (Button) findViewById(R.id.btn_confirmaccessory);
		uiAdapter.setMargin(btn_confirmaccessory, LayoutParams.MATCH_PARENT, 45, 20,20,
				20,0);
		uiAdapter.setTextSize(btn_confirmaccessory, 24);
		uiAdapter.setPadding(btn_confirmaccessory, 10, 0, 0, 0);
	}
	@Override
	public void onClick(View v) {
		LinearLayout basicinfo = (LinearLayout)findViewById(R.id.basic_info);
		LinearLayout contactway = (LinearLayout)findViewById(R.id.contact_way);
		LinearLayout accessory = (LinearLayout)findViewById(R.id.accessory);
		iv_ret=(ImageView) findViewById(R.id.iv_ret);
		tv_ret=(TextView) findViewById(R.id.tv_ret);
		switch (v.getId()) {
		case R.id.iv_ret:
			finish();
			break;
		case R.id.tv_ret:
			contactway.setVisibility(View.GONE);
			basicinfo.setVisibility(View.VISIBLE);
			iv_ret.setVisibility(View.VISIBLE);
			tv_ret.setVisibility(View.GONE);
			tv_title.setText("基本信息");
			break;
		case R.id.tv_goback:
			contactway.setVisibility(View.VISIBLE);
			accessory.setVisibility(View.GONE);
			tv_goback.setVisibility(View.GONE);
			tv_ret.setVisibility(View.VISIBLE);
			tv_title.setText("联系方式");
			break;
		case R.id.btn_confirmbasicinfo:
			String name = et_name.getText().toString();
			String idno = et_basicinfoidno.getText().toString();
			String degreeno = et_basicinfodegreeno.getText().toString();
			String department = et_department.getText().toString();
			String position= et_position.getText().toString();
			String entrydate = et_entrydate.getText().toString();
			if (TextUtils.isEmpty(name)){
				Toast.show(self, resources.getString(R.string.entryname));
				return;
			}else if (TextUtils.isEmpty(idno)){
				Toast.show(self, resources.getString(R.string.entryidno));
				return;
			}else if (TextUtils.isEmpty(degreeno)){
				Toast.show(self, resources.getString(R.string.entrydegreeno));
				return;
			}else  if (TextUtils.isEmpty(department)){
				Toast.show(self, resources.getString(R.string.entrydepartment));
				return;
			}else if (TextUtils.isEmpty(position)){
				Toast.show(self, resources.getString(R.string.entryposition));
				return;
			}else if (TextUtils.isEmpty(entrydate)){
				Toast.show(self, resources.getString(R.string.please_entrydate));
				return;
			}else{
				basicinfo.setVisibility(View.GONE);
				contactway.setVisibility(View.VISIBLE);
				iv_ret.setVisibility(View.GONE);
				tv_ret.setVisibility(View.VISIBLE);
				tv_title.setText("联系方式");
			}
			break;
		case R.id.btn_confirmcontact:
			String strcontactway = et_contactway.getText().toString();
			String strcontactaddress = et_contactaddress.getText().toString();
			if (TextUtils.isEmpty(strcontactway)){
				Toast.show(self, resources.getString(R.string.entry_contactway));
				return;
			}else if (TextUtils.isEmpty(strcontactaddress)){
				Toast.show(self, resources.getString(R.string.entry_contactaddress));
				return;
			}else{
				contactway.setVisibility(View.GONE);
				accessory.setVisibility(View.VISIBLE);
				tv_ret.setVisibility(View.GONE);
				tv_goback.setVisibility(View.VISIBLE);
				tv_title.setText("上传附件");
			}
			break;
		case R.id.btn_confirmaccessory:
			finish();
			break;
		default:
			break;
		}
	}

}
