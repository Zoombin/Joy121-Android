package com.joy.Activity;



import gejw.android.quickandroid.QActivity;
import gejw.android.quickandroid.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.joy.JoyApplication;
import com.joy.R;
import com.joy.Utils.Constants;
import com.joy.Utils.EntryDate;
import com.joy.Utils.SharedPreferencesUtils;
import com.joy.json.JsonCommon;
import com.joy.json.JsonCommon.OnOperationListener;
import com.joy.json.model.ActivityDetailEntity;
import com.joy.json.model.ActivityEntity;
import com.joy.json.model.CompAppSet;
import com.joy.json.model.EntryDepartmentDetailEntity;
import com.joy.json.model.EntryDepartmentEntity;
import com.joy.json.model.EntryEntity;
import com.joy.json.model.EntryManageEntity;
import com.joy.json.operation.OperationBuilder;
import com.joy.json.operation.impl.ActivityOp;
import com.joy.json.operation.impl.EntryDepartmentOp;
import com.joy.json.operation.impl.EntryManageOp;
import com.joy.json.operation.impl.EntrySaveOp;

import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
/**
 * @rainbow  入职管理
 */
public class EntryManagementActiviy extends BaseActivity implements OnClickListener {
	private Bitmap bmp;
	private ImageView imgView;   
	protected QActivity mActivity;
    private static int RESULT_LOAD_IMAGE = 1;

    private RelativeLayout layout_title;
    private ImageView iv_ret;
    private TextView  tv_title,tv_goBackEmployInfo,tv_goBackMyselfInfo,tv_goBackPapersInfo,
                      tv_goBackHistory,tv_goBackFamilyInfo;
    EntryManageEntity entryManageEntity;
    private String type;
    private String gender;
    //应聘信息
    private String initDate="2015-08-14";
    private List<String> list_comDep,list_comPos;
    private ArrayAdapter<String> comDep_adapter,comPos_adapter;
    private ImageView iv_comDep,iv_comPos,iv_comEntryDate,iv_residence,iv_mobile,
                      iv_urgentContact,iv_urgentMobile,iv_regions;
    private TextView  tv_comDep,tv_comPos,tv_comEntryDate,tv_residence,tv_mobile,
                      tv_urgentContact,tv_urgentMobile,tv_regions;
    private Spinner  sp_comDep,sp_comPos;
    private EditText et_comEntryDate,et_residence,et_mobile,et_urgentContact,
                     et_urgentMobile,et_regions;
    private Button btn_saveEmployInfo,btn_employInfoNext;
    //个人信息
    private ImageView iv_personName,iv_englishName,iv_gender,iv_address,
                      iv_idNo,iv_educationNo,iv_accumFund,iv_depositBank,iv_depositCardNo;
    private TextView  tv_personName,tv_englishName,tv_gender,tv_address,
                      tv_idNo,tv_educationNo,tv_accumFund,tv_depositBank,tv_depositCardNo;
    private RadioGroup radiogender;
    private RadioButton maleButton,femaleButton;
    private EditText  et_personName,et_englishName,et_address,
                      et_idNo,et_educationNo,et_accumFund,et_depositBank,et_depositCardNo;
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
		bindDepartmentOrPos("CostCenterno",-1);
		bindDepartmentOrPos("comgrade",-1);
		initViewMyselfInfo();
		initViewPapersInfo();
		initViewHistory();
		initViewFamilyInfo();
		initViewHobbies();
		initData();
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
			iv_comDep=(ImageView)findViewById(R.id.iv_comDep);
			uiAdapter.setMargin(iv_comDep, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 20, 30, 0, 10);
			tv_comDep= (TextView) findViewById(R.id.tv_comDep);
			uiAdapter.setTextSize(tv_comDep, 18);
			uiAdapter.setMargin(tv_comDep, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 10, 20, 0, 10);
			sp_comDep = (Spinner) findViewById(R.id.sp_comDep);
			uiAdapter.setMargin(sp_comDep, LayoutParams.MATCH_PARENT, 45, 5, 20,45, 0);
	        
			//应聘职位
	        iv_comPos=(ImageView)findViewById(R.id.iv_comPos);
			uiAdapter.setMargin(iv_comPos, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 20, 30, 0, 10);
			tv_comPos= (TextView) findViewById(R.id.tv_comPos);
			uiAdapter.setTextSize(tv_comPos, 18);
			uiAdapter.setMargin(tv_comPos, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT,10, 20, 0, 10);
			sp_comPos = (Spinner) findViewById(R.id.sp_comPos);
			uiAdapter.setMargin(sp_comPos, LayoutParams.MATCH_PARENT, 45, 5, 20,45, 0);
			//入职日期
	        iv_comEntryDate=(ImageView)findViewById(R.id.iv_comEntryDate);
			uiAdapter.setMargin(iv_comEntryDate, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 20, 30, 0, 10);
			tv_comEntryDate= (TextView) findViewById(R.id.tv_comEntryDate);
			uiAdapter.setTextSize(tv_comEntryDate, 18);
			uiAdapter.setMargin(tv_comEntryDate, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 10, 20, 0, 10);
			et_comEntryDate = (EditText) findViewById(R.id.et_comEntryDate);
			et_comEntryDate.setText(initDate);
			uiAdapter.setMargin(et_comEntryDate, LayoutParams.MATCH_PARENT, 45, 5, 20,	45, 0);
			et_comEntryDate.setOnClickListener(new OnClickListener() {
				public void onClick(View v) {
					EntryDate dateTimePicKDialog = new EntryDate(
							EntryManagementActiviy.this, initDate);
					dateTimePicKDialog.dateTimePicKDialog(et_comEntryDate);

				}
			});
			//现居地址
			iv_residence=(ImageView)findViewById(R.id.iv_residence);
			uiAdapter.setMargin(iv_residence, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 20, 33, 0, 10);
			tv_residence= (TextView) findViewById(R.id.tv_residence);
			uiAdapter.setTextSize(tv_residence, 18);
			uiAdapter.setMargin(tv_residence, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 10, 20, 0, 10);
			et_residence = (EditText) findViewById(R.id.et_residence);
			uiAdapter.setMargin(et_residence, LayoutParams.MATCH_PARENT, 45, 5, 20,45, 0);
			//联系方式
			iv_mobile=(ImageView)findViewById(R.id.iv_mobile);
			uiAdapter.setMargin(iv_mobile, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 20, 33, 0, 10);
			tv_mobile= (TextView) findViewById(R.id.tv_mobile);
			uiAdapter.setTextSize(tv_mobile, 18);
			uiAdapter.setMargin(tv_mobile, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 10, 20, 0, 10);
			et_mobile = (EditText) findViewById(R.id.et_mobile);
			uiAdapter.setMargin(et_mobile, LayoutParams.MATCH_PARENT, 45, 5, 20,45, 0);
			//紧急联系人
			iv_urgentContact=(ImageView)findViewById(R.id.iv_urgentContact);
			uiAdapter.setMargin(iv_urgentContact, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 20, 33, 0, 10);
			tv_urgentContact= (TextView) findViewById(R.id.tv_urgentContact);
			uiAdapter.setTextSize(tv_urgentContact, 18);
			uiAdapter.setMargin(tv_urgentContact, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 10, 20, 0, 10);
			et_urgentContact = (EditText) findViewById(R.id.et_urgentContact);
			uiAdapter.setMargin(et_urgentContact, LayoutParams.MATCH_PARENT, 45, 5, 20,45, 0);
			//紧急联系方式
			iv_urgentMobile=(ImageView)findViewById(R.id.iv_urgentMobile);
			uiAdapter.setMargin(iv_urgentMobile, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 20, 33, 0, 10);
			tv_urgentMobile= (TextView) findViewById(R.id.tv_urgentMobile);
			uiAdapter.setTextSize(tv_urgentMobile, 18);
			uiAdapter.setMargin(tv_urgentMobile, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 10, 20, 0, 10);
			et_urgentMobile = (EditText) findViewById(R.id.et_urgentMobile);
			uiAdapter.setMargin(et_urgentMobile, LayoutParams.MATCH_PARENT, 45, 5, 20,45, 0);
			//户口所在地
			iv_regions=(ImageView)findViewById(R.id.iv_regions);
			uiAdapter.setMargin(iv_regions, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 20, 33, 0, 10);
			tv_regions= (TextView) findViewById(R.id.tv_regions);
			uiAdapter.setTextSize(tv_regions, 18);
			uiAdapter.setMargin(tv_regions, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 10, 20, 0, 10);
			et_regions = (EditText) findViewById(R.id.et_regions);
			uiAdapter.setMargin(et_regions, LayoutParams.MATCH_PARENT, 45, 5, 20,45, 0);
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
		iv_personName=(ImageView)findViewById(R.id.iv_personName);
		uiAdapter.setMargin(iv_personName, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 20, 30, 0, 10);
		tv_personName= (TextView) findViewById(R.id.tv_personName);
		uiAdapter.setTextSize(tv_personName, 18);
		uiAdapter.setMargin(tv_personName, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 10, 20, 0, 10);
		et_personName = (EditText) findViewById(R.id.et_personName);
		uiAdapter.setMargin(et_personName, LayoutParams.MATCH_PARENT,45, 5, 20,45, 0);
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
		maleButton=(RadioButton)findViewById(R.id.male);
		femaleButton=(RadioButton)findViewById(R.id.female);
		uiAdapter.setMargin(radiogender, LayoutParams.MATCH_PARENT,40, 5, 20,45, 0);
		uiAdapter.setPadding(radiogender, 10, 0, 0, 0);
		//籍贯
		iv_address=(ImageView)findViewById(R.id.iv_address);
		uiAdapter.setMargin(iv_address, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 20, 30, 0, 10);
		tv_address= (TextView) findViewById(R.id.tv_address);
		uiAdapter.setTextSize(tv_address, 18);
		uiAdapter.setMargin(tv_address, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT,10, 20, 0, 10);
		et_address = (EditText) findViewById(R.id.et_address);
		uiAdapter.setMargin(et_address, LayoutParams.MATCH_PARENT,45, 5, 20,45, 0);
		uiAdapter.setPadding(et_address, 10, 0, 0, 0);
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
		iv_educationNo=(ImageView)findViewById(R.id.iv_educationNo);
		uiAdapter.setMargin(iv_educationNo, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 20, 30, 0, 10);
		tv_educationNo= (TextView) findViewById(R.id.tv_educationNo);
		uiAdapter.setTextSize(tv_educationNo, 18);
		uiAdapter.setMargin(tv_educationNo, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT,10, 20, 0, 10);
		et_educationNo = (EditText) findViewById(R.id.et_educationNo);
		uiAdapter.setMargin(et_educationNo, LayoutParams.MATCH_PARENT,45, 5, 20,45, 0);
		uiAdapter.setPadding(et_educationNo, 10, 0, 0, 0);
		
		//开户银行
		iv_depositBank=(ImageView)findViewById(R.id.iv_depositBank);
		uiAdapter.setMargin(iv_depositBank, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 20, 30, 0, 10);
		tv_depositBank= (TextView) findViewById(R.id.tv_depositBank);
		uiAdapter.setTextSize(tv_depositBank, 18);
		uiAdapter.setMargin(tv_depositBank, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT,10, 20, 0, 10);
		et_depositBank = (EditText) findViewById(R.id.et_depositBank);
		uiAdapter.setMargin(et_depositBank, LayoutParams.MATCH_PARENT,45, 5, 20,45, 0);
		uiAdapter.setPadding(et_depositBank, 10, 0, 0, 0);
		//银行账号
		iv_depositCardNo=(ImageView)findViewById(R.id.iv_depositCardNo);
		uiAdapter.setMargin(iv_depositCardNo, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 20, 30, 0, 10);
		tv_depositCardNo= (TextView) findViewById(R.id.tv_depositCardNo);
		uiAdapter.setTextSize(tv_depositCardNo, 18);
		uiAdapter.setMargin(tv_depositCardNo, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 10, 20, 0, 10);
		et_depositCardNo = (EditText) findViewById(R.id.et_depositCardNo);
		uiAdapter.setMargin(et_depositCardNo, LayoutParams.MATCH_PARENT, 45, 5, 20,45, 0);
		uiAdapter.setPadding(et_depositCardNo, 10, 0, 0, 0);
		//社会公积金编号
		iv_accumFund=(ImageView)findViewById(R.id.iv_accumFund);
		uiAdapter.setMargin(iv_accumFund, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 20, 30, 0, 10);
		tv_accumFund= (TextView) findViewById(R.id.tv_accumFund);
		uiAdapter.setTextSize(tv_accumFund, 18);
		uiAdapter.setMargin(tv_accumFund, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 10, 20, 0, 10);
		et_accumFund = (EditText) findViewById(R.id.et_accumFund);
		uiAdapter.setMargin(et_accumFund, LayoutParams.MATCH_PARENT,45, 5, 20,45, 0);
		uiAdapter.setPadding(et_accumFund, 10, 0, 0, 0);
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
			saveEmployInfoData();
			break;
		case R.id.btn_employInfoNext:  //应聘信息上的下一步进入到个人信息的填写	
			String residence=et_residence.getText().toString();
			String mobile=et_mobile.getText().toString();
			String urgentContact=et_urgentContact.getText().toString();
			String urgentMobile=et_urgentMobile.getText().toString();
			String regions=et_regions.getText().toString();
			if (TextUtils.isEmpty(residence)) {
				Toast.show(self, resources.getString(R.string.entryResidence));
				return;
			} else if (TextUtils.isEmpty(mobile)) {
				Toast.show(self, resources.getString(R.string.entryMobile));
				return;
			} else if (TextUtils.isEmpty(urgentContact)) {
				Toast.show(self, resources.getString(R.string.entryUrgentContact));
				return;
			} else if (TextUtils.isEmpty(urgentMobile)) {
				Toast.show(self, resources.getString(R.string.urgentMobile));
				return;
			} else if (TextUtils.isEmpty(regions)) {
				Toast.show(self, resources.getString(R.string.entryRegions));
				return;
			} else if (!isMobile(et_mobile.getText().toString())) {
				Toast.show(self, resources.getString(R.string.mobileFormat));
				return;
			} else if (!isMobile(et_urgentMobile.getText().toString())) {
				Toast.show(self, resources.getString(R.string.mobileFormat));
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
		case R.id.btn_saveMyselfInfo:  //个人信息
			saveMyselfInfoData();
			break;
		case R.id.btn_myselfInfoNext:  //个人信息上的下一步进入到证件信息
			String personName=et_personName.getText().toString();
			String address=et_address.getText().toString();
			String idNo=et_idNo.getText().toString();
			String enducationNo=et_educationNo.getText().toString();
			String depositBank=et_depositBank.getText().toString();
			String depositCardNo=et_depositCardNo.getText().toString();
			if (TextUtils.isEmpty(personName)) {
				Toast.show(self, resources.getString(R.string.entryPersonName));
				return;
			} else if (TextUtils.isEmpty(address)) {
				Toast.show(self, resources.getString(R.string.entryAddress));
				return;
			} else if (TextUtils.isEmpty(idNo)) {
				Toast.show(self, resources.getString(R.string.entryIdNo));
				return;
			} else if (TextUtils.isEmpty(enducationNo)) {
				Toast.show(self, resources.getString(R.string.entryEducationNo));
				return;
			} else if (TextUtils.isEmpty(depositBank)) {
				Toast.show(self, resources.getString(R.string.entryDepositBank));
				return;
			} else if (TextUtils.isEmpty(depositCardNo)) {
				Toast.show(self, resources.getString(R.string.entryDepositCardNo));
				return;
			} else if (!isIdNo(et_idNo.getText().toString())) {
				Toast.show(self, resources.getString(R.string.idNoFormat));
				return;
			} else {
				myselfInfo.setVisibility(View.GONE);
				papersInfo.setVisibility(View.VISIBLE);
			    iv_ret.setVisibility(View.GONE);
				tv_goBackEmployInfo.setVisibility(View.GONE);
				tv_goBackMyselfInfo.setVisibility(View.VISIBLE);
				tv_title.setText("证件信息");
			}
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
	 private void initData()
	 {
		 OperationBuilder builder = new OperationBuilder().append(
					new EntryManageOp(), null);
			OnOperationListener listener = new OnOperationListener() {
				@Override
				public void onOperationFinished(List<Object> resList) {
					EntryEntity entity = (EntryEntity) resList.get(0);
					entryManageEntity = entity.getRetObj();
					if (entryManageEntity != null) {
						//应聘信息					
					   SpinnerAdapter comDepAdater=sp_comDep.getAdapter();
					   int comDepCount=comDepAdater.getCount();
					   for(int i=0;i<comDepCount;i++)
					    {
					      if(entryManageEntity.getComDep().equals(comDepAdater.getItem(i).toString()))
					        {
					          sp_comDep.setSelection(i,true);
					    	 }
					     }
					   SpinnerAdapter comPosAdater=sp_comPos.getAdapter();
					   int comPosCount=comPosAdater.getCount();
					   for(int i=0;i<comPosCount;i++)
					    {
					      if(entryManageEntity.getComPos().equals(comPosAdater.getItem(i).toString()))
					        {
					          sp_comPos.setSelection(i,true);
					    	 }
					     }
						//entity.setComPos((String) sp_comPos.getSelectedItem());
						SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
					    et_comEntryDate.setText(date.format(new Date(Long.parseLong(entryManageEntity.getComEntryDate().substring(6, 19)))));
					    et_residence.setText(entryManageEntity.getRegions());
					    et_mobile.setText(entryManageEntity.getMobile());
					    et_urgentContact.setText(entryManageEntity.getUrgentContact());
					    et_urgentMobile.setText(entryManageEntity.getUrgentMobile());
					    et_regions.setText(entryManageEntity.getResidence());
					    //个人信息
					    et_personName.setText(entryManageEntity.getPersonName());
					    et_englishName.setText(entryManageEntity.getEnglishName());
					    gender=entryManageEntity.getGender();
					    Log.e("++++++++++++++++++",gender);
					    if(gender.equals("1")){
					    	femaleButton.setChecked(true);
					    }else{
					    	maleButton.setChecked(true);
					    }
					    et_address.setText(entryManageEntity.getAddress());
					    et_idNo.setText(entryManageEntity.getIdNo());
					    et_educationNo.setText(entryManageEntity.getEducationNo());
					    et_depositBank.setText(entryManageEntity.getDepositBank());
					    et_depositCardNo.setText(entryManageEntity.getDepositCardNo());
					    et_accumFund.setText(entryManageEntity.getAccumFund());
					}
				}

				@Override
				public void onOperationError(Exception e) {
					e.printStackTrace();
				}
			};
			JsonCommon task = new JsonCommon(mActivity, builder, listener, false);
			task.execute();
	 }
	 private void bindDepartmentOrPos(final String type,final int parentId){
		 EntryDepartmentDetailEntity entry=new EntryDepartmentDetailEntity();
		 entry.setSysKey(type);
		 entry.setParentId(parentId);
			OperationBuilder builder = new OperationBuilder().append(new EntryDepartmentOp(),
					entry);
			OnOperationListener listener = new OnOperationListener() {
				@Override
				public void onOperationFinished(List<Object> resList) {
					if (self.isFinishing()) {
						return;
					}
					if (resList == null) {
						Toast.show(self, resources.getString(R.string.timeout));
						return;
					}
					EntryDepartmentEntity entity = (EntryDepartmentEntity) resList.get(0);
					List<EntryDepartmentDetailEntity> departmentList = entity.getRetObj();
					if(type=="CostCenterno"&&parentId==-1){
						  //数据
				        list_comDep = new ArrayList<String>();
				        for(int i=0;i<departmentList.size();i++)
				        {
				        	list_comDep.add(departmentList.get(i).getSysKeyName());
				        }
				        //适配器
				        comDep_adapter= new ArrayAdapter<String>(EntryManagementActiviy.this, android.R.layout.simple_spinner_item, list_comDep);
				        //设置样式
				        comDep_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item );
				        //加载适配器
				        sp_comDep.setAdapter(comDep_adapter);
//				        
					}else if(type=="comgrade"&&parentId==-1){
						//数据
				        list_comPos = new ArrayList<String>();
				        for(int i=0;i<departmentList.size();i++)
				        {
				        	list_comPos.add(departmentList.get(i).getSysKeyName());
				        }
				        
				        //适配器
				        comPos_adapter= new ArrayAdapter<String>(EntryManagementActiviy.this, android.R.layout.simple_spinner_item, list_comPos);
				        //设置样式
				        comPos_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
				        //加载适配器
				        sp_comPos.setAdapter(comPos_adapter);
					}
					
				}
					@Override
					public void onOperationError(Exception e) {
						e.printStackTrace();
					}
		    	};
		    	JsonCommon task = new JsonCommon(self, builder, listener,
						JsonCommon.PROGRESSCOMMIT);
				task.execute();
	 }
	 private void saveEmployInfoData()
	 {
		 EntryManageEntity entity=new EntryManageEntity();
		 entity.setLoginName(SharedPreferencesUtils.getLoginName(JoyApplication.getSelf()));
		 entity.setComDep((String) sp_comDep.getSelectedItem());
		 entity.setComPos((String) sp_comPos.getSelectedItem());
		 entity.setComEntryDate(et_comEntryDate.getText().toString());
		 entity.setResidence(et_residence.getText().toString());
		 if (!isMobile(et_mobile.getText().toString())) {
				Toast.show(self, resources.getString(R.string.mobileFormat));
				return;
			} else if (!isMobile(et_urgentMobile.getText().toString())) {
				Toast.show(self, resources.getString(R.string.mobileFormat));
				return;
			} else {
				entity.setMobile(et_mobile.getText().toString());
			}
		 entity.setUrgentContact(et_urgentContact.getText().toString());
		 entity.setUrgentMobile(et_urgentMobile.getText().toString());
		 entity.setRegions(et_regions.getText().toString());
		 OperationBuilder builder = new OperationBuilder().append(
					new EntrySaveOp(), entity);
	    	OnOperationListener listener = new OnOperationListener() {
				@Override
				public void onOperationFinished(List<Object> resList) {
					if (self.isFinishing()) {
						return;
					}else if(resList==null){
						Toast.show(self,"连接超时");
						return;
					}else{
						Toast.show(self,"保存成功");
					}
				}
				@Override
				public void onOperationError(Exception e) {
					e.printStackTrace();
				}
	    	};
	    	JsonCommon task = new JsonCommon(self, builder, listener,
					JsonCommon.PROGRESSCOMMIT);
			task.execute();
	 }
	 private void saveMyselfInfoData(){
		 EntryManageEntity entity=new EntryManageEntity();
		 entity.setLoginName(SharedPreferencesUtils.getLoginName(JoyApplication.getSelf()));
		 //应聘信息
		 entity.setComDep((String) sp_comDep.getSelectedItem());
		 entity.setComPos((String) sp_comPos.getSelectedItem());
		 entity.setComEntryDate(et_comEntryDate.getText().toString());
		 entity.setResidence(et_residence.getText().toString());
		 entity.setMobile(et_mobile.getText().toString());
		 entity.setUrgentContact(et_urgentContact.getText().toString());
		 entity.setUrgentMobile(et_urgentMobile.getText().toString());
		 entity.setRegions(et_regions.getText().toString());
		 //个人信息
		 entity.setPersonName(et_personName.getText().toString());
		 entity.setEnglishName(et_englishName.getText().toString());
		 if(femaleButton.isChecked())
		 {
			 entity.setGender("1");
		 }else{
			 entity.setGender("0");
		 }
		 entity.setAddress(et_address.getText().toString());
		 entity.setIdNo(et_idNo.getText().toString());
		 entity.setEducationNo(et_educationNo.getText().toString());
		 entity.setAccumFund(et_accumFund.getText().toString());
		 entity.setDepositBank(et_depositBank.getText().toString());
		 entity.setDepositCardNo(et_depositCardNo.getText().toString());
		 OperationBuilder builder = new OperationBuilder().append(
					new EntrySaveOp(), entity);
	    	OnOperationListener listener = new OnOperationListener() {
				@Override
				public void onOperationFinished(List<Object> resList) {
					if (self.isFinishing()) {
						return;
					}else if(resList==null){
						Toast.show(self,"连接超时");
						return;
					}else{
						Toast.show(self,"保存成功");
					}
				}
				@Override
				public void onOperationError(Exception e) {
					e.printStackTrace();
				}
	    	};
	    	JsonCommon task = new JsonCommon(self, builder, listener,
					JsonCommon.PROGRESSCOMMIT);
			task.execute();
	 }
	 /**
	   * 手机号的形式判断
	  */
		public boolean isMobile(String mobile)
		{
			Pattern p=Pattern.compile("^(13[0-9]|14[0-9]|15[0-9]|17[0-9]|18[0-9])\\d{8}$");//正则表达式验证手机的正确性
			Matcher m=p.matcher(mobile);
			return m.matches();
		}
		 /**
		   * 身份证号的形式判断
		  */
		public boolean isIdNo(String idNo)
		{
		     Pattern idNoPattern = Pattern.compile("(\\d{14}[0-9a-zA-Z])|(\\d{17}[0-9a-zA-Z])");  
	            //通过Pattern获得Matcher  
	         Matcher idNoMatcher = idNoPattern.matcher(idNo);
	         return idNoMatcher.matches();
		}

}  

