package com.joy.Activity;


import gejw.android.quickandroid.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import com.joy.JoyApplication;
import com.joy.R;
import com.joy.R.layout;
import com.joy.Utils.Constants;
import com.joy.Utils.EntryDate;
import com.joy.json.model.CompAppSet;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
/**
 * @rainbow  入职管理
 */
public class EntryManagementActiviy extends BaseActivity implements OnClickListener {
	private Bitmap bmp;
	private ImageView imgView;   
    private static int RESULT_LOAD_IMAGE = 1;

    private RelativeLayout layout_title;
    private ImageView iv_ret;
    private TextView  tv_title,tv_goBackEmployInfo,tv_goBackMyselfInfo,tv_goBackPapersInfo,
                      tv_goBackHistory,tv_goBackFamilyInfo;
  //应聘信息
    private String initDate="2015年8月14日";
    private List<String> list_department,list_position;
    private ArrayAdapter<String> department_adapter,position_adapter;
    private ImageView iv_department,iv_position,iv_entryDate,iv_nowAddress,iv_contactWay,
                      iv_emergencyPerson,iv_emergencyContact,iv_houschold;
    private TextView  tv_department,tv_position,tv_entryDate,tv_nowAddress,tv_contactWay,
                      tv_emergencyPerson,tv_emergencyContact,tv_houschold;
    private Spinner  sp_department,sp_position;
    private EditText et_entryDate,et_nowAddress,et_contactWay,et_emergencyPerson,
                     et_emergencyContact,et_houschold;
    private Button btn_saveEmployInfo,btn_employInfoNext;
    //个人信息
    private ImageView iv_chineseName,iv_englishName,iv_gender,iv_birthPlace,
                      iv_idNo,iv_degreeNo,iv_accumulationNo,iv_bankName,iv_bankNo;
    private TextView  tv_chineseName,tv_englishName,tv_gender,tv_birthPlace,
                      tv_idNo,tv_degreeNo,tv_accumulationNo,tv_bankName,tv_bankNo;
    private RadioGroup radiogender;
    private EditText  et_chineseName,et_englishName,et_birthPlace,
                      et_idNo,et_degreeNo,et_accumulationNo,et_bankName,et_bankNo;
    private Button btn_saveMyselfInfo,btn_myselfInfoNext;
    //证件信息
    private ImageView iv_myselfPhoto,iv_myselfVideo,iv_academicPhoto,iv_idPhoto,iv_repairOrder,iv_checkupReporting;
    private TextView tv_myselfPhoto,tv_myselfVideo,tv_academicPhoto,tv_idPhoto,tv_repairOrder,tv_checkupReporting;
    private Button btn_savePapersInfo,btn_papersInfoNext;
    //个人经历
    private LinearLayout layout_menu,layout_education,layout_workExperience;
    private TextView tv_education,tv_workExperience;
    private View line_education,line_workExperience;
    private Button btn_saveHistory,btn_historyNext;
    //家庭信息
    private TextView tv_childInfo,tv_parentsInfo;
    private EditText et_childName,et_childBirth,et_fatherName,et_fatherBirth,et_motherName,et_motherBirth;
    private View line_childInfo,line_parentsInfo;
    private Button btn_saveFamilyInfo,btn_familyInfoNext;
    //兴趣爱好
    private CheckBox basketball, football,badminton,table_tennis,mountains,sing,
                     book,cooking,drawing,dance,travel,photography;
    private Button btn_saveHobbies;
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
		initEmployInfo();
		initViewMyselfInfo();
		initViewPapersInfo();
		initViewHistory();
		initViewFamilyInfo();
		initViewHobbies();
		
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
			btn_saveEmployInfo.setBackgroundColor(color);
			btn_employInfoNext.setBackgroundColor(color);
			btn_saveMyselfInfo.setBackgroundColor(color);
			btn_myselfInfoNext.setBackgroundColor(color);
			btn_savePapersInfo.setBackgroundColor(color);
			btn_papersInfoNext.setBackgroundColor(color);
			btn_saveHistory.setBackgroundColor(color);
			btn_historyNext.setBackgroundColor(color);
			btn_saveFamilyInfo.setBackgroundColor(color);
			btn_familyInfoNext.setBackgroundColor(color);
			btn_saveHobbies.setBackgroundColor(color);
		}
		btn_saveEmployInfo.setOnClickListener(this);
		btn_employInfoNext.setOnClickListener(this);
		btn_saveMyselfInfo.setOnClickListener(this);
		btn_myselfInfoNext.setOnClickListener(this);
		btn_savePapersInfo.setOnClickListener(this);
		btn_papersInfoNext.setOnClickListener(this);
		btn_saveHistory.setOnClickListener(this);
		btn_historyNext.setOnClickListener(this);
		btn_saveFamilyInfo.setOnClickListener(this);
		btn_familyInfoNext.setOnClickListener(this);
		btn_saveHobbies.setOnClickListener(this);
		
		
		imgView.setOnClickListener(new View.OnClickListener() { 
			@Override
            public void onClick(View arg0) {
			  
            Intent i = new Intent(
                    Intent.ACTION_PICK,
                    android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

            startActivityForResult(i, RESULT_LOAD_IMAGE);
        }
    });
		return v;
	} 
	//应聘信息
    private void initEmployInfo()
		{
			layout_title = (RelativeLayout) findViewById(R.id.layout_title);
			uiAdapter.setMargin(layout_title, LayoutParams.MATCH_PARENT,
					Constants.SubTitleHeight, 0, 0, 0, 0);

			iv_ret = (ImageView) findViewById(R.id.iv_ret);
			iv_ret.setOnClickListener(this);
			
			tv_title = (TextView) findViewById(R.id.tv_title);
			uiAdapter.setTextSize(tv_title, Constants.TitleSize);
			//应聘部门	
			iv_department=(ImageView)findViewById(R.id.iv_department);
			uiAdapter.setMargin(iv_department, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 20, 30, 0, 10);
			tv_department= (TextView) findViewById(R.id.tv_department);
			uiAdapter.setTextSize(tv_department, 18);
			uiAdapter.setMargin(tv_department, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 10, 20, 0, 10);
			sp_department = (Spinner) findViewById(R.id.sp_department);
			uiAdapter.setMargin(sp_department, LayoutParams.MATCH_PARENT, 45, 5, 20,45, 0);
		    //数据
	        list_department = new ArrayList<String>();
	        list_department.add("开发部");
	        list_department.add("客服部");
	        list_department.add("软件部");
	        list_department.add("行政部");
	        
	        //适配器
	        department_adapter= new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list_department);
	        //设置样式
	        department_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	        //加载适配器
	        sp_department.setAdapter(department_adapter);
	        
			//应聘职位
	        iv_position=(ImageView)findViewById(R.id.iv_position);
			uiAdapter.setMargin(iv_position, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 20, 30, 0, 10);
			tv_position= (TextView) findViewById(R.id.tv_position);
			uiAdapter.setTextSize(tv_position, 18);
			uiAdapter.setMargin(tv_position, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT,10, 20, 0, 10);
			sp_position = (Spinner) findViewById(R.id.sp_position);
			uiAdapter.setMargin(sp_position, LayoutParams.MATCH_PARENT, 45, 5, 20,45, 0);
			//数据
	        list_position = new ArrayList<String>();
	        list_position.add("开发");
	        list_position.add("客服");
	        list_position.add("软件");
	        list_position.add("行政");
	        
	        //适配器
	        position_adapter= new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list_position);
	        //设置样式
	        position_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	        //加载适配器
	        sp_position.setAdapter(position_adapter);
			//入职日期
	        iv_entryDate=(ImageView)findViewById(R.id.iv_entryDate);
			uiAdapter.setMargin(iv_entryDate, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 20, 30, 0, 10);
			tv_entryDate= (TextView) findViewById(R.id.tv_entryDate);
			uiAdapter.setTextSize(tv_entryDate, 18);
			uiAdapter.setMargin(tv_entryDate, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 10, 20, 0, 10);
			et_entryDate = (EditText) findViewById(R.id.et_entryDate);
			et_entryDate.setText(initDate);
			uiAdapter.setMargin(et_entryDate, LayoutParams.MATCH_PARENT, 45, 5, 20,	45, 0);
			et_entryDate.setOnClickListener(new OnClickListener() {
				public void onClick(View v) {
					EntryDate dateTimePicKDialog = new EntryDate(
							EntryManagementActiviy.this, initDate);
					dateTimePicKDialog.dateTimePicKDialog(et_entryDate);

				}
			});
			//现居地址
			iv_nowAddress=(ImageView)findViewById(R.id.iv_nowAddress);
			uiAdapter.setMargin(iv_nowAddress, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 20, 33, 0, 10);
			tv_nowAddress= (TextView) findViewById(R.id.tv_nowAddress);
			uiAdapter.setTextSize(tv_nowAddress, 18);
			uiAdapter.setMargin(tv_nowAddress, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 10, 20, 0, 10);
			et_nowAddress = (EditText) findViewById(R.id.et_nowAddress);
			uiAdapter.setMargin(et_nowAddress, LayoutParams.MATCH_PARENT, 45, 5, 20,45, 0);
			//联系方式
			iv_contactWay=(ImageView)findViewById(R.id.iv_contactWay);
			uiAdapter.setMargin(iv_contactWay, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 20, 33, 0, 10);
			tv_contactWay= (TextView) findViewById(R.id.tv_contactWay);
			uiAdapter.setTextSize(tv_contactWay, 18);
			uiAdapter.setMargin(tv_contactWay, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 10, 20, 0, 10);
			et_contactWay = (EditText) findViewById(R.id.et_contactWay);
			uiAdapter.setMargin(et_contactWay, LayoutParams.MATCH_PARENT, 45, 5, 20,45, 0);
			//紧急联系人
			iv_emergencyPerson=(ImageView)findViewById(R.id.iv_emergencyPerson);
			uiAdapter.setMargin(iv_emergencyPerson, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 20, 33, 0, 10);
			tv_emergencyPerson= (TextView) findViewById(R.id.tv_emergencyPerson);
			uiAdapter.setTextSize(tv_emergencyPerson, 18);
			uiAdapter.setMargin(tv_emergencyPerson, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 10, 20, 0, 10);
			et_emergencyPerson = (EditText) findViewById(R.id.et_emergencyPerson);
			uiAdapter.setMargin(et_emergencyPerson, LayoutParams.MATCH_PARENT, 45, 5, 20,45, 0);
			//紧急联系方式
			iv_emergencyContact=(ImageView)findViewById(R.id.iv_emergencyContact);
			uiAdapter.setMargin(iv_emergencyContact, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 20, 33, 0, 10);
			tv_emergencyContact= (TextView) findViewById(R.id.tv_emergencyContact);
			uiAdapter.setTextSize(tv_emergencyContact, 18);
			uiAdapter.setMargin(tv_emergencyContact, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 10, 20, 0, 10);
			et_emergencyContact = (EditText) findViewById(R.id.et_emergencyContact);
			uiAdapter.setMargin(et_emergencyContact, LayoutParams.MATCH_PARENT, 45, 5, 20,45, 0);
			//户口所在地
			iv_houschold=(ImageView)findViewById(R.id.iv_houschold);
			uiAdapter.setMargin(iv_houschold, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 20, 33, 0, 10);
			tv_houschold= (TextView) findViewById(R.id.tv_houschold);
			uiAdapter.setTextSize(tv_houschold, 18);
			uiAdapter.setMargin(tv_houschold, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 10, 20, 0, 10);
			et_houschold = (EditText) findViewById(R.id.et_houschold);
			uiAdapter.setMargin(et_houschold, LayoutParams.MATCH_PARENT, 45, 5, 20,45, 0);
			//保存
			btn_saveEmployInfo = (Button) findViewById(R.id.btn_saveEmployInfo);
			uiAdapter.setMargin(btn_saveEmployInfo, LayoutParams.MATCH_PARENT, 45, 20,20,20,0);
			uiAdapter.setTextSize(btn_saveEmployInfo, 24);
			uiAdapter.setPadding(btn_saveEmployInfo, 10, 0, 0, 0);
			//下一步
			btn_employInfoNext = (Button) findViewById(R.id.btn_employInfoNext);
			uiAdapter.setMargin(btn_employInfoNext, LayoutParams.MATCH_PARENT, 45, 20,20,20,0);
			uiAdapter.setTextSize(btn_employInfoNext, 24);
			uiAdapter.setPadding(btn_employInfoNext, 10, 0, 0, 0);
			
		}
	//个人信息
	private void initViewMyselfInfo()
	{
		tv_goBackEmployInfo = (TextView) findViewById(R.id.tv_goBackEmployInfo);
		tv_goBackEmployInfo.setOnClickListener(this);
		//中文名
		iv_chineseName=(ImageView)findViewById(R.id.iv_chineseName);
		uiAdapter.setMargin(iv_chineseName, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 20, 30, 0, 10);
		tv_chineseName= (TextView) findViewById(R.id.tv_chineseName);
		uiAdapter.setTextSize(tv_chineseName, 18);
		uiAdapter.setMargin(tv_chineseName, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 10, 20, 0, 10);
		et_chineseName = (EditText) findViewById(R.id.et_chineseName);
		uiAdapter.setMargin(et_chineseName, LayoutParams.MATCH_PARENT,45, 5, 20,45, 0);
		//英文名
		iv_englishName=(ImageView)findViewById(R.id.iv_englishName);
		uiAdapter.setMargin(iv_englishName, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 20, 30, 0, 10);
		tv_englishName= (TextView) findViewById(R.id.tv_englishName);
		uiAdapter.setTextSize(tv_englishName, 18);
		uiAdapter.setMargin(tv_englishName, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 10, 20, 0, 10);
		et_englishName = (EditText) findViewById(R.id.et_englishName);
		uiAdapter.setMargin(et_englishName, LayoutParams.MATCH_PARENT, 45, 5, 20,45, 0);
		uiAdapter.setPadding(et_englishName, 10, 0, 0, 0);
		//性别
		iv_gender=(ImageView)findViewById(R.id.iv_gender);
		uiAdapter.setMargin(iv_gender, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 17, 20, 0, 10);
		tv_gender= (TextView) findViewById(R.id.tv_gender);
		uiAdapter.setTextSize(tv_gender, 18);
		uiAdapter.setMargin(tv_gender, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 10, 20, 0, 10);
		radiogender=(RadioGroup)findViewById(R.id.radiogender);
		uiAdapter.setMargin(radiogender, LayoutParams.MATCH_PARENT,40, 5, 20,45, 0);
		uiAdapter.setPadding(radiogender, 10, 0, 0, 0);
		//籍贯
		iv_birthPlace=(ImageView)findViewById(R.id.iv_birthPlace);
		uiAdapter.setMargin(iv_birthPlace, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 20, 30, 0, 10);
		tv_birthPlace= (TextView) findViewById(R.id.tv_birthPlace);
		uiAdapter.setTextSize(tv_birthPlace, 18);
		uiAdapter.setMargin(tv_birthPlace, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT,10, 20, 0, 10);
		et_birthPlace = (EditText) findViewById(R.id.et_birthPlace);
		uiAdapter.setMargin(et_birthPlace, LayoutParams.MATCH_PARENT,45, 5, 20,45, 0);
		uiAdapter.setPadding(et_birthPlace, 10, 0, 0, 0);
		//身份证号
		iv_idNo=(ImageView)findViewById(R.id.iv_idNo);
		uiAdapter.setMargin(iv_idNo, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 20, 30, 0, 10);
		tv_idNo= (TextView) findViewById(R.id.tv_idNo);
		uiAdapter.setTextSize(tv_idNo, 18);
		uiAdapter.setMargin(tv_idNo, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 10, 20, 0, 10);
		et_idNo = (EditText) findViewById(R.id.et_idNo);
		uiAdapter.setMargin(et_idNo, LayoutParams.MATCH_PARENT, 45, 5, 20,45, 0);
		uiAdapter.setPadding(et_idNo, 10, 0, 0, 0);
		//学历号
		iv_degreeNo=(ImageView)findViewById(R.id.iv_degreeNo);
		uiAdapter.setMargin(iv_degreeNo, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 20, 30, 0, 10);
		tv_degreeNo= (TextView) findViewById(R.id.tv_degreeNo);
		uiAdapter.setTextSize(tv_degreeNo, 18);
		uiAdapter.setMargin(tv_degreeNo, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT,10, 20, 0, 10);
		et_degreeNo = (EditText) findViewById(R.id.et_degreeNo);
		uiAdapter.setMargin(et_degreeNo, LayoutParams.MATCH_PARENT,45, 5, 20,45, 0);
		uiAdapter.setPadding(et_degreeNo, 10, 0, 0, 0);
		
		//开户银行
		iv_bankName=(ImageView)findViewById(R.id.iv_bankName);
		uiAdapter.setMargin(iv_bankName, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 20, 30, 0, 10);
		tv_bankName= (TextView) findViewById(R.id.tv_bankName);
		uiAdapter.setTextSize(tv_bankName, 18);
		uiAdapter.setMargin(tv_bankName, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT,10, 20, 0, 10);
		et_bankName = (EditText) findViewById(R.id.et_bankName);
		uiAdapter.setMargin(et_bankName, LayoutParams.MATCH_PARENT,45, 5, 20,45, 0);
		uiAdapter.setPadding(et_bankName, 10, 0, 0, 0);
		//银行账号
		iv_bankNo=(ImageView)findViewById(R.id.iv_bankNo);
		uiAdapter.setMargin(iv_bankNo, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 20, 30, 0, 10);
		tv_bankNo= (TextView) findViewById(R.id.tv_bankNo);
		uiAdapter.setTextSize(tv_bankNo, 18);
		uiAdapter.setMargin(tv_bankNo, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 10, 20, 0, 10);
		et_bankNo = (EditText) findViewById(R.id.et_bankNo);
		uiAdapter.setMargin(et_bankNo, LayoutParams.MATCH_PARENT, 45, 5, 20,45, 0);
		uiAdapter.setPadding(et_bankNo, 10, 0, 0, 0);
		//社会公积金编号
		iv_accumulationNo=(ImageView)findViewById(R.id.iv_accumulationNo);
		uiAdapter.setMargin(iv_accumulationNo, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 20, 30, 0, 10);
		tv_accumulationNo= (TextView) findViewById(R.id.tv_accumulationNo);
		uiAdapter.setTextSize(tv_accumulationNo, 18);
		uiAdapter.setMargin(tv_accumulationNo, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 10, 20, 0, 10);
		et_accumulationNo = (EditText) findViewById(R.id.et_accumulationNo);
		uiAdapter.setMargin(et_accumulationNo, LayoutParams.MATCH_PARENT,45, 5, 20,45, 0);
		uiAdapter.setPadding(et_accumulationNo, 10, 0, 0, 0);
		//保存
		btn_saveMyselfInfo = (Button) findViewById(R.id.btn_saveMyselfInfo);
	    uiAdapter.setMargin(btn_saveMyselfInfo, LayoutParams.MATCH_PARENT, 45, 20,20,20,0);
		uiAdapter.setTextSize(btn_saveMyselfInfo, 24);
	    uiAdapter.setPadding(btn_saveMyselfInfo, 10, 0, 0, 0);
		//下一步
	    btn_myselfInfoNext = (Button) findViewById(R.id.btn_myselfInfoNext);
		uiAdapter.setMargin(btn_myselfInfoNext, LayoutParams.MATCH_PARENT, 45, 20,20,20,0);
		uiAdapter.setTextSize(btn_myselfInfoNext, 24);
		uiAdapter.setPadding(btn_myselfInfoNext, 10, 0, 0, 0);
	}
	//证件信息
	private void initViewPapersInfo(){
		imgView = (ImageView) findViewById(R.id.imgView);
		//uiAdapter.setMargin(imgView, LayoutParams.MATCH_PARENT, 100, 5,10,80,0);
		//imgView.setOnClickListener(this);
		
		tv_goBackMyselfInfo = (TextView) findViewById(R.id.tv_goBackMyselfInfo);
		tv_goBackMyselfInfo.setOnClickListener(this);
		//个人照片
		iv_myselfPhoto=(ImageView)findViewById(R.id.iv_myselfPhoto);
		uiAdapter.setMargin(iv_myselfPhoto, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 20, 20, 0, 10);
		tv_myselfPhoto= (TextView) findViewById(R.id.tv_myselfPhoto);
		uiAdapter.setTextSize(tv_myselfPhoto, 18);
		uiAdapter.setMargin(tv_myselfPhoto, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 10, 20, 0, 10);
		//个人视频
		iv_myselfVideo=(ImageView)findViewById(R.id.iv_myselfVideo);
		uiAdapter.setMargin(iv_myselfVideo, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 20, 20, 0, 10);
		tv_myselfVideo= (TextView) findViewById(R.id.tv_myselfVideo);
		uiAdapter.setTextSize(tv_myselfVideo, 18);
		uiAdapter.setMargin(tv_myselfVideo, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 10, 20, 0, 10);
		//学历证书
		iv_academicPhoto=(ImageView)findViewById(R.id.iv_academicPhoto);
		uiAdapter.setMargin(iv_academicPhoto, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 20, 20, 0, 10);
		tv_academicPhoto= (TextView) findViewById(R.id.tv_academicPhoto);
		uiAdapter.setTextSize(tv_academicPhoto, 18);
		uiAdapter.setMargin(tv_academicPhoto, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 10, 20, 0, 10);
		//身份证
		iv_idPhoto=(ImageView)findViewById(R.id.iv_idPhoto);
		uiAdapter.setMargin(iv_idPhoto, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 20, 20, 0, 10);
		tv_idPhoto= (TextView) findViewById(R.id.tv_idPhoto);
		uiAdapter.setTextSize(tv_idPhoto, 18);
		uiAdapter.setMargin(tv_idPhoto, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 10, 20, 0, 10);
		//退工单
		iv_repairOrder=(ImageView)findViewById(R.id.iv_repairOrder);
		uiAdapter.setMargin(iv_repairOrder, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 20, 20, 0, 10);
		tv_repairOrder= (TextView) findViewById(R.id.tv_repairOrder);
		uiAdapter.setTextSize(tv_repairOrder, 18);
		uiAdapter.setMargin(tv_repairOrder, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 10, 20, 0, 10);
		//体检报告
		iv_checkupReporting=(ImageView)findViewById(R.id.iv_checkupReporting);
		uiAdapter.setMargin(iv_checkupReporting, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 20, 20, 0, 10);
		tv_checkupReporting= (TextView) findViewById(R.id.tv_checkupReporting);
		uiAdapter.setTextSize(tv_checkupReporting, 18);
		uiAdapter.setMargin(tv_checkupReporting, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 10, 20, 0, 10);
		//保存
		btn_savePapersInfo = (Button) findViewById(R.id.btn_savePapersInfo);
		uiAdapter.setMargin(btn_savePapersInfo, LayoutParams.MATCH_PARENT, 45, 20,20,20,0);
		uiAdapter.setTextSize(btn_savePapersInfo, 24);
		uiAdapter.setPadding(btn_savePapersInfo, 10, 0, 0, 0);
		//下一步
		btn_papersInfoNext = (Button) findViewById(R.id.btn_papersInfoNext);
		uiAdapter.setMargin(btn_papersInfoNext, LayoutParams.MATCH_PARENT, 45, 20,20,20,0);
		uiAdapter.setTextSize(btn_papersInfoNext, 24);
		uiAdapter.setPadding(btn_papersInfoNext, 10, 0, 0, 0);
	}
	//个人经历
    private void initViewHistory()
	{
		tv_goBackPapersInfo = (TextView) findViewById(R.id.tv_goBackPapersInfo);
		tv_goBackPapersInfo.setOnClickListener(this);
        layout_menu = (LinearLayout) findViewById(R.id.layout_menu);
      //学习经历
		layout_education = (LinearLayout) findViewById(R.id.layout_education);
		layout_education.setOnClickListener(this);
		tv_education = (TextView) findViewById(R.id.tv_education);
		line_education = (View) findViewById(R.id.line_education);
	  //工作经历
	    layout_workExperience = (LinearLayout) findViewById(R.id.layout_workExperience);
		layout_workExperience.setOnClickListener(this);
		tv_workExperience = (TextView) findViewById(R.id.tv_workExperience);
		line_workExperience = (View) findViewById(R.id.line_workExperience);
	    //保存
		btn_saveHistory = (Button) findViewById(R.id.btn_saveHistory);
		uiAdapter.setMargin(btn_saveHistory, LayoutParams.MATCH_PARENT, 45, 20,20,20,0);
		uiAdapter.setTextSize(btn_saveHistory, 24);
		uiAdapter.setPadding(btn_saveHistory, 10, 0, 0, 0);
		//下一步
		btn_historyNext = (Button) findViewById(R.id.btn_historyNext);
		uiAdapter.setMargin(btn_historyNext, LayoutParams.MATCH_PARENT, 45, 20,20,20,0);
		uiAdapter.setTextSize(btn_historyNext, 24);
		uiAdapter.setPadding(btn_historyNext, 10, 0, 0, 0);
		defaultColor();
	}
    private void defaultColor()
   	{
       	tv_education.setTextColor(color);
   		line_education.setBackgroundColor(color);
   		tv_workExperience.setTextColor(resources.getColor(R.color.gray));
   	}
    private void showMenu(int layout) {
		switch (layout) {
		case R.id.layout_education:
			tv_education.setTextColor(color);
			line_education.setBackgroundColor(color);
			tv_workExperience.setTextColor(resources.getColor(R.color.gray));
			line_workExperience.setBackgroundColor(resources.getColor(R.color.WHITE));
			break;
		case R.id.layout_workExperience:
			tv_education.setTextColor(resources.getColor(R.color.gray));
			line_education.setBackgroundColor(resources.getColor(R.color.WHITE));
			tv_workExperience.setTextColor(color);
			line_workExperience.setBackgroundColor(color);
			break;
		
		default:
			break;
		}
	}
    //家庭信息
    private void initViewFamilyInfo()
	{
		tv_goBackHistory = (TextView) findViewById(R.id.tv_goBackHistory);
		tv_goBackHistory.setOnClickListener(this);
		//子女信息
		tv_childInfo= (TextView) findViewById(R.id.tv_childInfo);
		uiAdapter.setTextSize(tv_childInfo, 22);
		uiAdapter.setMargin(tv_childInfo, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 40, 20, 0, 10);
		
		line_childInfo=(View)findViewById(R.id.line_childInfo);
		
		et_childName = (EditText) findViewById(R.id.et_chidName);
		uiAdapter.setMargin(et_childName, LayoutParams.MATCH_PARENT, 32, 5, 10,45, 0);
		et_childBirth = (EditText) findViewById(R.id.et_chidBirth);
		uiAdapter.setMargin(et_childBirth, LayoutParams.MATCH_PARENT, 32, 5, 10,45, 0);
		//父母信息
		tv_parentsInfo= (TextView) findViewById(R.id.tv_parentsInfo);
		uiAdapter.setTextSize(tv_parentsInfo, 22);
		uiAdapter.setMargin(tv_parentsInfo, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 40, 20, 0, 10);
		
		line_parentsInfo=(View)findViewById(R.id.line_parentsInfo);
		et_fatherName = (EditText) findViewById(R.id.et_fatherName);
		uiAdapter.setMargin(et_fatherName, LayoutParams.MATCH_PARENT, 32, 5, 10,45, 0);
		et_fatherBirth = (EditText) findViewById(R.id.et_fatherBirth);
		uiAdapter.setMargin(et_fatherBirth, LayoutParams.MATCH_PARENT, 32, 5, 10,45, 0);
		et_motherName = (EditText) findViewById(R.id.et_motherName);
		uiAdapter.setMargin(et_motherName, LayoutParams.MATCH_PARENT, 32, 5, 10,45, 0);
		et_motherBirth = (EditText) findViewById(R.id.et_motherBirth);
		uiAdapter.setMargin(et_motherBirth, LayoutParams.MATCH_PARENT, 32, 5, 10,45, 0);
		//保存
		btn_saveFamilyInfo = (Button) findViewById(R.id.btn_saveFamilyInfo);
		uiAdapter.setMargin(btn_saveFamilyInfo, LayoutParams.MATCH_PARENT, 45, 20,20,20,0);
		uiAdapter.setTextSize(btn_saveFamilyInfo, 24);
		uiAdapter.setPadding(btn_saveFamilyInfo, 10, 0, 0, 0);
		//下一步
		btn_familyInfoNext = (Button) findViewById(R.id.btn_familyInfoNext);
		uiAdapter.setMargin(btn_familyInfoNext, LayoutParams.MATCH_PARENT, 45, 20,20,20,0);
		uiAdapter.setTextSize(btn_familyInfoNext, 24);
		uiAdapter.setPadding(btn_familyInfoNext, 10, 0, 0, 0);
	}
	//兴趣爱好
	private void initViewHobbies()
	{
		tv_goBackFamilyInfo = (TextView) findViewById(R.id.tv_goBackFamilyInfo);
		tv_goBackFamilyInfo.setOnClickListener(this);
		
		basketball=(CheckBox)findViewById(R.id.basketball);
		//basketball.setButtonDrawable(R.drawable.check_hobbies);
		//basketball.getBackground().setAlpha(100);
		basketball.setBackgroundResource(R.drawable.check_hobbies);
		
		table_tennis=(CheckBox)findViewById(R.id.table_tennis);
		table_tennis.setBackgroundResource(R.drawable.check_hobbies);
		
		book=(CheckBox)findViewById(R.id.book);
		book.setBackgroundResource(R.drawable.check_hobbies);
		
		dance=(CheckBox)findViewById(R.id.dance);
		dance.setBackgroundResource(R.drawable.check_hobbies);
		
        football=(CheckBox)findViewById(R.id.football);
		football.setBackgroundResource(R.drawable.check_hobbies);
		
		mountains=(CheckBox)findViewById(R.id.mountains);
		mountains.setBackgroundResource(R.drawable.check_hobbies);
		
		cooking=(CheckBox)findViewById(R.id.cooking);
		cooking.setBackgroundResource(R.drawable.check_hobbies);
		
		travel=(CheckBox)findViewById(R.id.travel);
		travel.setBackgroundResource(R.drawable.check_hobbies);
		
		badminton=(CheckBox)findViewById(R.id.badminton);
		badminton.setBackgroundResource(R.drawable.check_hobbies);
		
		sing=(CheckBox)findViewById(R.id.sing);
		sing.setBackgroundResource(R.drawable.check_hobbies);
		
		drawing=(CheckBox)findViewById(R.id.drawing);
		drawing.setBackgroundResource(R.drawable.check_hobbies);
		
		photography=(CheckBox)findViewById(R.id.photography);
		photography.setBackgroundResource(R.drawable.check_hobbies);
		
		//保存
		btn_saveHobbies = (Button) findViewById(R.id.btn_saveHobbies);
		uiAdapter.setMargin(btn_saveHobbies, LayoutParams.MATCH_PARENT, 45, 20,20,20,0);
		uiAdapter.setTextSize(btn_saveHobbies, 24);
		uiAdapter.setPadding(btn_saveHobbies, 10, 0, 0, 0);
	}
	@Override
	public void onClick(View v) {
		LinearLayout employInfo = (LinearLayout)findViewById(R.id.employInfo);
		LinearLayout myselfInfo = (LinearLayout)findViewById(R.id.myselfInfo);
		LinearLayout papersInfo = (LinearLayout)findViewById(R.id.papersInfo);
		LinearLayout history = (LinearLayout)findViewById(R.id.history);
		LinearLayout familyInfo = (LinearLayout)findViewById(R.id.familyInfo);
		LinearLayout hobbies = (LinearLayout)findViewById(R.id.hobbies);
//		iv_ret=(ImageView) findViewById(R.id.iv_ret);
//		tv_goBackEmployInfo=(TextView) findViewById(R.id.tv_goBackEmployInfo);
//		tv_goBackMyselfInfo=(TextView) findViewById(R.id.tv_goBackMyselfInfo);
		switch (v.getId()) {
		case R.id.iv_ret:
			finish();
			break;
			//应聘信息
		case R.id.btn_saveEmployInfo:  //保存应聘信息
			break;
		case R.id.btn_employInfoNext:  //应聘信息上的下一步进入到个人信息的填写	
			String department=sp_department.getSelectedItem().toString();
			String position=sp_position.getSelectedItem().toString();
			String entryDate=et_entryDate.getText().toString();
			String nowAddress=et_nowAddress.getText().toString();
			String contactWay=et_contactWay.getText().toString();
			String emergencyPerson=et_emergencyPerson.getText().toString();
			String emergencyContact=et_emergencyContact.getText().toString();
			String houschold=et_houschold.getText().toString();
			if (TextUtils.isEmpty(currpwd) || TextUtils.isEmpty(newpwd)
					|| TextUtils.isEmpty(comfirmnewpwd)) {
				Toast.show(self, resources.getString(R.string.enterpwd));
				
				return;
			} else if (!newpwd.equals(comfirmnewpwd)) {
				Toast.show(self, resources.getString(R.string.diffpwderr));
				return;
			} else {
			employInfo.setVisibility(View.GONE);
			papersInfo.setVisibility(View.GONE);
			myselfInfo.setVisibility(View.VISIBLE);
		    iv_ret.setVisibility(View.GONE);
			tv_goBackEmployInfo.setVisibility(View.VISIBLE);
			tv_title.setText("个人信息");
			}
			break;
		
			//个人信息
		case R.id.tv_goBackEmployInfo:   //个人信息上的上一步返回到应聘信息页面
			myselfInfo.setVisibility(View.GONE);
			papersInfo.setVisibility(View.GONE);
			employInfo.setVisibility(View.VISIBLE);
			tv_goBackEmployInfo.setVisibility(View.GONE);
			iv_ret.setVisibility(View.VISIBLE);
			tv_title.setText("应聘信息");
			break;
		case R.id.btn_saveMyselfInfo:  //保存应聘信息
			
			break;
		case R.id.btn_myselfInfoNext:  //个人信息上的下一步进入到证件信息
			myselfInfo.setVisibility(View.GONE);
			papersInfo.setVisibility(View.VISIBLE);
		    iv_ret.setVisibility(View.GONE);
			tv_goBackEmployInfo.setVisibility(View.GONE);
			tv_goBackMyselfInfo.setVisibility(View.VISIBLE);
			tv_title.setText("证件信息");
			break;
			
			//证件信息
		case R.id.tv_goBackMyselfInfo:  //证件信息上的上一步返回到个人信息
			papersInfo.setVisibility(View.GONE);
			employInfo.setVisibility(View.GONE);
			myselfInfo.setVisibility(View.VISIBLE);
			iv_ret.setVisibility(View.GONE);
			tv_goBackEmployInfo.setVisibility(View.VISIBLE);
			tv_goBackMyselfInfo.setVisibility(View.GONE);
			tv_title.setText("个人信息");
			break;
		case R.id.btn_savePapersInfo:  //保存证件信息
			break;
		case R.id.btn_papersInfoNext:  //证件信息的下一步进入到个人经历
			myselfInfo.setVisibility(View.GONE);
			papersInfo.setVisibility(View.GONE);
			history.setVisibility(View.VISIBLE);
		    iv_ret.setVisibility(View.GONE);
			tv_goBackEmployInfo.setVisibility(View.GONE);
			tv_goBackMyselfInfo.setVisibility(View.GONE);
			tv_goBackPapersInfo.setVisibility(View.VISIBLE);
			tv_title.setText("个人经历");
			break;
			//个人经历
		case R.id.tv_goBackPapersInfo:  //个人经历中的上一步返回到证件信息
			papersInfo.setVisibility(View.VISIBLE);
			employInfo.setVisibility(View.GONE);
			myselfInfo.setVisibility(View.GONE);
			history.setVisibility(View.GONE);
			iv_ret.setVisibility(View.GONE);
			tv_goBackEmployInfo.setVisibility(View.GONE);
			tv_goBackPapersInfo.setVisibility(View.GONE);
			tv_goBackMyselfInfo.setVisibility(View.VISIBLE);
			tv_title.setText("证件信息");
			break;
		case R.id.btn_saveHistory:  //保存证件信息
			break;
		case R.id.btn_historyNext:  //个人经历的下一步进入到家庭信息
			myselfInfo.setVisibility(View.GONE);
			papersInfo.setVisibility(View.GONE);
			history.setVisibility(View.GONE);
			familyInfo.setVisibility(View.VISIBLE);
		    iv_ret.setVisibility(View.GONE);
			tv_goBackEmployInfo.setVisibility(View.GONE);
			tv_goBackMyselfInfo.setVisibility(View.GONE);
			tv_goBackPapersInfo.setVisibility(View.GONE);
			tv_goBackFamilyInfo.setVisibility(View.GONE);
			tv_goBackHistory.setVisibility(View.VISIBLE);
			tv_title.setText("家庭信息");
			break;
		case R.id.layout_education:
			showMenu(v.getId());
			break;
		case R.id.layout_workExperience:
			showMenu(v.getId());
			break;
			//家庭信息
		case R.id.tv_goBackHistory:  //家庭信息中的上一步返回到个人经历
			papersInfo.setVisibility(View.GONE);
			employInfo.setVisibility(View.GONE);
			familyInfo.setVisibility(View.GONE);
			myselfInfo.setVisibility(View.GONE);
			history.setVisibility(View.VISIBLE);
			iv_ret.setVisibility(View.GONE);
			tv_goBackEmployInfo.setVisibility(View.GONE);
			tv_goBackMyselfInfo.setVisibility(View.GONE);
			tv_goBackPapersInfo.setVisibility(View.VISIBLE);
			tv_goBackHistory.setVisibility(View.GONE);
			tv_goBackFamilyInfo.setVisibility(View.GONE);
			tv_title.setText("个人经历");
			break;
		case R.id.btn_saveFamilyInfo:  //保存证件信息
			break;
		case R.id.btn_familyInfoNext:  //家庭信息的下一步进入到兴趣爱好
			employInfo.setVisibility(View.GONE);
			myselfInfo.setVisibility(View.GONE);
			papersInfo.setVisibility(View.GONE);
			history.setVisibility(View.GONE);
			familyInfo.setVisibility(View.GONE);
			hobbies.setVisibility(View.VISIBLE);
		    iv_ret.setVisibility(View.GONE);
			tv_goBackEmployInfo.setVisibility(View.GONE);
			tv_goBackMyselfInfo.setVisibility(View.GONE);
			tv_goBackPapersInfo.setVisibility(View.GONE);
			tv_goBackHistory.setVisibility(View.GONE);
			tv_goBackFamilyInfo.setVisibility(View.VISIBLE);
			tv_title.setText("兴趣爱好");
			break;
			//兴趣爱好
		case R.id.tv_goBackFamilyInfo:  //家庭信息中的上一步返回到个人经历
			papersInfo.setVisibility(View.GONE);
			employInfo.setVisibility(View.GONE);
			familyInfo.setVisibility(View.VISIBLE);
			myselfInfo.setVisibility(View.GONE);
			history.setVisibility(View.GONE);
			hobbies.setVisibility(View.GONE);
			iv_ret.setVisibility(View.GONE);
			tv_goBackEmployInfo.setVisibility(View.GONE);
			tv_goBackMyselfInfo.setVisibility(View.GONE);
			tv_goBackPapersInfo.setVisibility(View.GONE);
			tv_goBackHistory.setVisibility(View.VISIBLE);
			tv_goBackFamilyInfo.setVisibility(View.GONE);
			tv_title.setText("个人经历");
			break;
			
		default:
			break;
	}
  }
	 @Override
	    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	        super.onActivityResult(requestCode, resultCode, data);
	  
	        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
	            Uri selectedImage = data.getData();
	            String[] filePathColumn = { MediaStore.Images.Media.DATA };
	  
	            Cursor cursor = getContentResolver().query(selectedImage,
	                    filePathColumn, null, null, null);
	            cursor.moveToFirst();
	  
	            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
	            String picturePath = cursor.getString(columnIndex);
	            cursor.close();
	  
	            ImageView imageView = (ImageView) findViewById(R.id.imgView);
	            imageView.setImageBitmap(BitmapFactory.decodeFile(picturePath));
	            
	            
	            
	            BitmapFactory.Options options = new BitmapFactory.Options();  
	            // options 设为true时，构造出的bitmap没有图片，只有一些长宽等配置信息，但比较快，设为false时，才有图片  
	            options.inJustDecodeBounds = true;  
	            Bitmap bitmap = BitmapFactory.decodeFile(picturePath, options);  
	           
	            int scale = (int)( options.outWidth / (float)100);  
	            if(scale <= 0)  
	                scale = 1;  
	            options.inSampleSize= scale; 
	            
	            options.inJustDecodeBounds = false;  
	            bitmap = BitmapFactory.decodeFile(picturePath, options);  
	            imgView.setImageBitmap(bitmap);  
	            imgView.setMaxHeight(250);  
	            imgView.setScaleType(ImageView.ScaleType.CENTER_CROP);
	          
	        }
	  
	    }  
	 private void saveEmployInfo(){}
}  

