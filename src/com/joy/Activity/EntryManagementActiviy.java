package com.joy.Activity;
import gejw.android.quickandroid.QActivity;
import gejw.android.quickandroid.widget.Toast;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.joy.JoyApplication;
import com.joy.R;
import com.joy.Utils.Constants;
import com.joy.Utils.EntryAddInfoManager;
import com.joy.Utils.EntryDate;
import com.joy.Utils.SharedPreferencesUtils;
import com.joy.Widget.EntryEducationDetailAdapter;
import com.joy.Widget.EntryFamilyDetailAdapter;
import com.joy.Widget.EntryWrokExperienceDetailAdapter;
import com.joy.json.JsonCommon;
import com.joy.json.JsonCommon.OnOperationListener;
import com.joy.json.model.CompAppSet;
import com.joy.json.model.EntryDepartmentDetailEntity;
import com.joy.json.model.EntryDepartmentEntity;
import com.joy.json.model.EntryEntity;
import com.joy.json.model.EntryManageEducationInfoEntity;
import com.joy.json.model.EntryManageEntity;
import com.joy.json.model.EntryManageExperiencesListEntity;
import com.joy.json.model.EntryManageFamilyInfoEntity;
import com.joy.json.model.EntryManageFamilyListEntity;
import com.joy.json.model.EntryManageWorkExperienceInfoEntity;
import com.joy.json.model.SpinnerData;
import com.joy.json.operation.OperationBuilder;
import com.joy.json.operation.impl.EntryDepartmentOp;
import com.joy.json.operation.impl.EntryManageOp;
import com.joy.json.operation.impl.EntrySaveOp;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
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
	protected QActivity mActivity;
    private static int RESULT_LOAD_IMAGE = 1;
    private  String picturePath;
    

    private RelativeLayout layout_title;
    private LinearLayout layout_ret,layout_goBackEmployInfo,layout_goBackMyselfInfo,layout_goBackPapersInfo,
                         layout_goBackHistory,layout_goBackFamilyInfo;
    private ImageView iv_step1,iv_step2,iv_step3,iv_step4,iv_step5,iv_step6;
    private TextView  tv_title;
    EntryManageEntity entryManageEntity;
    private int gender;
    //应聘信息
    private String initDate="2015-08-14";
    private List<SpinnerData> list_comDep,list_comPos;
    private ArrayAdapter<SpinnerData> comDep_adapter,comPos_adapter;
    private ImageView iv_comDep,iv_comPos,iv_comEntryDate,iv_residence,iv_mobile,
                      iv_urgentContact,iv_urgentMobile,iv_regions;
    private TextView  tv_comDep,tv_comPos,tv_comEntryDate,tv_residence,tv_mobile,
                      tv_urgentContact,tv_urgentMobile,tv_regions;
    private EditText et_comEntryDate,et_residence,et_mobile,et_urgentContact,
                     et_urgentMobile,et_regions;
    private Spinner  sp_comDep,sp_comPos;
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
    private ImageView imgViewPhoto,imgViewVedio,imgViewAcademic,imgViewIdPhoto1,imgViewIdPhoto2,imgViewRepairOrder,imgViewCheckupReporting;
    private TextView tv_myselfPhoto,tv_myselfVideo,tv_academicPhoto,tv_idPhoto,tv_repairOrder,tv_checkupReporting;
    private Button btn_savePapersInfo,btn_papersInfoNext;
    //个人经历
    private LinearLayout layout_menu,layout_education,layout_workExperience;
    private TextView tv_education,tv_workExperience;
    public ListView list_educationInfo,list_workExperienceInfo;
    private EntryEducationDetailAdapter educationAdapter;
    private EntryWrokExperienceDetailAdapter workExperienceAdapter;
    private Button btn_addEducation,btn_addWorkExperience,btn_saveHistory,btn_historyNext;
    //家庭信息
    private TextView tv_familyInfo;
    private View line_familyInfo;
    private EntryFamilyDetailAdapter adapterFamily;
    private ListView list_familyInfo;
    private Button btn_saveFamilyInfo,btn_familyInfoNext,btn_addFamilyInfo;
    //兴趣爱好
    private CheckBox basketball, football,badminton,table_tennis,mountains,sing,
                     book,cooking,drawing,dance,travel,photography;
    private List<String> checkBoxHobbiesList;  
    private Button btn_saveHobbies;
    private Button btn_sumbit;
    CompAppSet appSet;
	int color;
	private Resources resources;
	
	private LinearLayout employInfo;
	private LinearLayout myselfInfo;
	private LinearLayout papersInfo;
	private LinearLayout history;
	private LinearLayout familyInfo;
	private LinearLayout hobbies;
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
		int id;
		View v = inflater.inflate(R.layout.activity_entry_basic_info, null);
		setContentView(v);
		initEmployInfo();
		bindDepartmentOrPos("CostCenterno",-1);//传入-1显示全部部门
		bindDepartmentOrPos("Compos",-1);
		initViewMyselfInfo();
		initViewPapersInfo();
		initViewHistory();
		initViewFamilyInfo();
		initViewHobbies();
		initData();
		if (appSet != null) {
			try {
				color = Color.parseColor(appSet.getColor2());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		btn_saveEmployInfo.setOnClickListener(this);
		btn_employInfoNext.setOnClickListener(this);
		btn_saveMyselfInfo.setOnClickListener(this);
		btn_myselfInfoNext.setOnClickListener(this);
		btn_savePapersInfo.setOnClickListener(this);
		btn_papersInfoNext.setOnClickListener(this);
		btn_addEducation.setOnClickListener(this);
		btn_addWorkExperience.setOnClickListener(this);
		btn_saveHistory.setOnClickListener(this);
		btn_historyNext.setOnClickListener(this);
		btn_addFamilyInfo.setOnClickListener(this);
		btn_saveFamilyInfo.setOnClickListener(this);
		btn_familyInfoNext.setOnClickListener(this);
		btn_saveHobbies.setOnClickListener(this);
		btn_sumbit.setOnClickListener(this);
		//响应img图片选择图库相片
		imgViewPhoto.setOnClickListener(new View.OnClickListener() { 
			@Override
            public void onClick(View arg0) { 
            Intent i = new Intent(
                    Intent.ACTION_PICK,
                    android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(i, RESULT_LOAD_IMAGE);
        }
    });
		imgViewAcademic.setOnClickListener(new View.OnClickListener() { 
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
	/**
	 * 应聘信息
	 */
    private void initEmployInfo()
		{
			layout_title = (RelativeLayout) findViewById(R.id.layout_title);
			uiAdapter.setMargin(layout_title, LayoutParams.MATCH_PARENT,
					Constants.SubTitleHeight, 0, 0, 0, 0);
			layout_ret = (LinearLayout) findViewById(R.id.layout_ret);
			layout_ret.setOnClickListener(this);
			layout_goBackEmployInfo=(LinearLayout)findViewById(R.id.layout_goBackEmployInfo);
			layout_goBackEmployInfo.setOnClickListener(this);
			layout_goBackMyselfInfo=(LinearLayout)findViewById(R.id.layout_goBackMyselfInfo);
			layout_goBackMyselfInfo.setOnClickListener(this);
			
			layout_goBackPapersInfo=(LinearLayout)findViewById(R.id.layout_goBackPapersInfo);
			layout_goBackPapersInfo.setOnClickListener(this);
			
			layout_goBackHistory=(LinearLayout)findViewById(R.id.layout_goBackHistory);
			layout_goBackHistory.setOnClickListener(this);
			
			layout_goBackFamilyInfo=(LinearLayout)findViewById(R.id.layout_goBackFamilyInfo);
			layout_goBackFamilyInfo.setOnClickListener(this);
			iv_step1= (ImageView) findViewById(R.id.iv_step1);
			iv_step2= (ImageView) findViewById(R.id.iv_step2);
			iv_step3= (ImageView) findViewById(R.id.iv_step3);
			iv_step4= (ImageView) findViewById(R.id.iv_step4);
			iv_step5= (ImageView) findViewById(R.id.iv_step5);
			iv_step6= (ImageView) findViewById(R.id.iv_step6);
			
			
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
			et_address = (EditText) findViewById(R.id.et_address);
			uiAdapter.setMargin(et_address, LayoutParams.MATCH_PARENT, 45, 5, 20,45, 0);
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
			et_residence = (EditText) findViewById(R.id.et_residence);
			uiAdapter.setMargin(et_residence, LayoutParams.MATCH_PARENT, 45, 5, 20,45, 0);
			//保存
			btn_saveEmployInfo = (Button) findViewById(R.id.btn_saveEmployInfo);
			uiAdapter.setMargin(btn_saveEmployInfo, LayoutParams.MATCH_PARENT, 45, 30,20,30,0);
			uiAdapter.setTextSize(btn_saveEmployInfo, 24);
			
			//下一步
			btn_employInfoNext = (Button) findViewById(R.id.btn_employInfoNext);
			uiAdapter.setMargin(btn_employInfoNext, LayoutParams.MATCH_PARENT, 45, 30,20,30,0);
			uiAdapter.setTextSize(btn_employInfoNext, 24);
			
			
		}
	/**
	 * 个人信息
	 */
	private void initViewMyselfInfo()
	{
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
		et_regions = (EditText) findViewById(R.id.et_regions);
		uiAdapter.setMargin(et_regions, LayoutParams.MATCH_PARENT,45, 5, 20,45, 0);
		uiAdapter.setPadding(et_regions, 10, 0, 0, 0);
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
	    uiAdapter.setMargin(btn_saveMyselfInfo, LayoutParams.MATCH_PARENT, 45, 30,20,30,0);
		uiAdapter.setTextSize(btn_saveMyselfInfo, 24);
		//下一步
	    btn_myselfInfoNext = (Button) findViewById(R.id.btn_myselfInfoNext);
		uiAdapter.setMargin(btn_myselfInfoNext, LayoutParams.MATCH_PARENT, 45, 30,20,30,0);
		uiAdapter.setTextSize(btn_myselfInfoNext, 24);
	}
	/**
	 * 证件信息
	 */
	private void initViewPapersInfo(){
		imgViewPhoto = (ImageView) findViewById(R.id.imgViewPhoto);
		imgViewVedio=(ImageView) findViewById(R.id.imgViewVideo);
		imgViewAcademic=(ImageView) findViewById(R.id.imgViewAcademic);
		imgViewIdPhoto1= (ImageView) findViewById(R.id.imgViewIdPhoto1);//身份证正面
		imgViewIdPhoto2=(ImageView) findViewById(R.id.imgViewIdPhoto2);//身份证反面
		imgViewRepairOrder= (ImageView) findViewById(R.id.imgViewRepairOrder);
		imgViewCheckupReporting= (ImageView) findViewById(R.id.imgViewCheckupReporting);
		//个人照片
		iv_myselfPhoto=(ImageView)findViewById(R.id.iv_myselfPhoto);
		uiAdapter.setMargin(iv_myselfPhoto, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 20, 40, 0, 10);
		tv_myselfPhoto= (TextView) findViewById(R.id.tv_myselfPhoto);
		uiAdapter.setTextSize(tv_myselfPhoto, 18);
		uiAdapter.setMargin(tv_myselfPhoto, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 10, 40, 0, 10);
		//个人视频
		iv_myselfVideo=(ImageView)findViewById(R.id.iv_myselfVideo);
		uiAdapter.setMargin(iv_myselfVideo, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 20, 40, 0, 10);
		tv_myselfVideo= (TextView) findViewById(R.id.tv_myselfVideo);
		uiAdapter.setTextSize(tv_myselfVideo, 18);
		uiAdapter.setMargin(tv_myselfVideo, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 10, 40, 0, 10);
		//学历证书
		iv_academicPhoto=(ImageView)findViewById(R.id.iv_academicPhoto);
		uiAdapter.setMargin(iv_academicPhoto, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 20, 40, 0, 10);
		tv_academicPhoto= (TextView) findViewById(R.id.tv_academicPhoto);
		uiAdapter.setTextSize(tv_academicPhoto, 18);
		uiAdapter.setMargin(tv_academicPhoto, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 10, 40, 0, 10);
		//身份证
		iv_idPhoto=(ImageView)findViewById(R.id.iv_idPhoto);
		uiAdapter.setMargin(iv_idPhoto, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 20, 40, 0, 10);
		tv_idPhoto= (TextView) findViewById(R.id.tv_idPhoto);
		uiAdapter.setTextSize(tv_idPhoto, 18);
		uiAdapter.setMargin(tv_idPhoto, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 10, 40, 0, 10);
		//退工单
		iv_repairOrder=(ImageView)findViewById(R.id.iv_repairOrder);
		uiAdapter.setMargin(iv_repairOrder, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 20, 40, 0, 10);
		tv_repairOrder= (TextView) findViewById(R.id.tv_repairOrder);
		uiAdapter.setTextSize(tv_repairOrder, 18);
		uiAdapter.setMargin(tv_repairOrder, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 10, 40, 0, 10);
		//体检报告
		iv_checkupReporting=(ImageView)findViewById(R.id.iv_checkupReporting);
		uiAdapter.setMargin(iv_checkupReporting, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 20, 40, 0, 10);
		tv_checkupReporting= (TextView) findViewById(R.id.tv_checkupReporting);
		uiAdapter.setTextSize(tv_checkupReporting, 18);
		uiAdapter.setMargin(tv_checkupReporting, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 10, 40, 0, 10);
		//保存
		btn_savePapersInfo = (Button) findViewById(R.id.btn_savePapersInfo);
		uiAdapter.setMargin(btn_savePapersInfo, LayoutParams.MATCH_PARENT, 45, 30,20,30,0);
		uiAdapter.setTextSize(btn_savePapersInfo, 24);
		//下一步
		btn_papersInfoNext = (Button) findViewById(R.id.btn_papersInfoNext);
		uiAdapter.setMargin(btn_papersInfoNext, LayoutParams.MATCH_PARENT, 45, 30,20,30,0);
		uiAdapter.setTextSize(btn_papersInfoNext, 24);
	}
	/**
	 * 个人经历
	 */
    private void initViewHistory()
	{
        layout_menu = (LinearLayout) findViewById(R.id.layout_menu);
       //学习经历
		layout_education = (LinearLayout) findViewById(R.id.layout_education);
		layout_education.setOnClickListener(this);
		tv_education = (TextView) findViewById(R.id.tv_education);
		btn_addEducation= (Button) findViewById(R.id.btn_addEducation);
		uiAdapter.setMargin(btn_addEducation, LayoutParams.MATCH_PARENT, 45, 10,20,10,0);
		uiAdapter.setTextSize(btn_addEducation, 20);
		list_educationInfo = (ListView) findViewById(R.id.list_educationInfo);
		uiAdapter.setMargin(list_educationInfo, LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT, 0, 0, 0, 0);
		educationAdapter=new EntryEducationDetailAdapter(self,self);
		list_educationInfo.setAdapter(educationAdapter);//给listView设置适配器
		
	   //工作经历
	    layout_workExperience = (LinearLayout) findViewById(R.id.layout_workExperience);
		layout_workExperience.setOnClickListener(this);
		tv_workExperience = (TextView) findViewById(R.id.tv_workExperience);
		btn_addWorkExperience= (Button) findViewById(R.id.btn_addWorkExperience);
		uiAdapter.setMargin(btn_addWorkExperience, LayoutParams.MATCH_PARENT, 45, 10,20,10,0);
		uiAdapter.setTextSize(btn_addWorkExperience, 20);
		
		list_workExperienceInfo = (ListView) findViewById(R.id.list_workExperienceInfo);
		uiAdapter.setMargin(list_workExperienceInfo, LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT, 0, 0, 0, 0);
		workExperienceAdapter=new EntryWrokExperienceDetailAdapter(self,self);
		list_workExperienceInfo.setAdapter(workExperienceAdapter);
	    //保存
		btn_saveHistory = (Button) findViewById(R.id.btn_saveHistory);
		uiAdapter.setMargin(btn_saveHistory, LayoutParams.MATCH_PARENT, 45, 30,20,30,0);
		uiAdapter.setTextSize(btn_saveHistory, 24);
		//下一步
		btn_historyNext = (Button) findViewById(R.id.btn_historyNext);
		uiAdapter.setMargin(btn_historyNext, LayoutParams.MATCH_PARENT, 45, 30,20,30,0);
		uiAdapter.setTextSize(btn_historyNext, 24);
		defaultShow();//初始化显示经历的后背景框
	}
    @SuppressWarnings("deprecation")
    private void defaultShow()
   	{
    	layout_education.setBackgroundDrawable(this.getResources().getDrawable(R.drawable.entry_educationclick));
   		layout_workExperience.setBackgroundDrawable(this.getResources().getDrawable(R.drawable.entry_workexperience));
   		list_educationInfo.setVisibility(View.VISIBLE);
   		list_workExperienceInfo.setVisibility(View.GONE);
   		btn_addEducation.setVisibility(View.VISIBLE);
		btn_addWorkExperience.setVisibility(View.GONE);
   	}
    @SuppressWarnings("deprecation")
    private void showMenu(int layout) {
		switch (layout) {
		case R.id.layout_education:
			layout_education.setBackgroundDrawable(this.getResources().getDrawable(R.drawable.entry_educationclick));
			layout_workExperience.setBackgroundDrawable(this.getResources().getDrawable(R.drawable.entry_workexperience));	
			break;
		case R.id.layout_workExperience:
			layout_education.setBackgroundDrawable(this.getResources().getDrawable(R.drawable.entry_education));
			layout_workExperience.setBackgroundDrawable(this.getResources().getDrawable(R.drawable.entry_workexperienceclick));	
		default:
			break;
		}
	}

    /**
     * 家庭信息
     */
    private void initViewFamilyInfo()
	{
    	//亲属信息
		list_familyInfo = (ListView) findViewById(R.id.list_familyInfo);
		uiAdapter.setMargin(list_familyInfo, LayoutParams.MATCH_PARENT,
				LayoutParams.WRAP_CONTENT, 0, 0, 0, 0);
		adapterFamily=new EntryFamilyDetailAdapter(self,self);
		list_familyInfo.setAdapter(adapterFamily);
		
		btn_addFamilyInfo= (Button) findViewById(R.id.btn_addFamilyInfo);
		uiAdapter.setMargin(btn_addFamilyInfo, LayoutParams.MATCH_PARENT, 45, 10,20,10,0);
		uiAdapter.setTextSize(btn_addFamilyInfo, 20);
		
		//保存
		btn_saveFamilyInfo = (Button) findViewById(R.id.btn_saveFamilyInfo);
		uiAdapter.setMargin(btn_saveFamilyInfo, LayoutParams.MATCH_PARENT, 45, 30,20,30,0);
		uiAdapter.setTextSize(btn_saveFamilyInfo, 24);
		//下一步
		btn_familyInfoNext = (Button) findViewById(R.id.btn_familyInfoNext);
		uiAdapter.setMargin(btn_familyInfoNext, LayoutParams.MATCH_PARENT, 45, 30,20,30,0);
		uiAdapter.setTextSize(btn_familyInfoNext, 24);
	}
	/**
	 * 兴趣爱好
	 */
	private void initViewHobbies()
	{
		basketball=(CheckBox)findViewById(R.id.basketball);
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
		uiAdapter.setMargin(btn_saveHobbies, LayoutParams.MATCH_PARENT, 45, 30,20,30,0);
		uiAdapter.setTextSize(btn_saveHobbies, 24);
		//上传
		btn_sumbit=(Button)findViewById(R.id.btn_sumbit);
		uiAdapter.setMargin(btn_sumbit, LayoutParams.MATCH_PARENT, 45, 30,20,30,0);
		uiAdapter.setTextSize(btn_sumbit, 24);
	}

	/**
	 * 响应按钮显示和隐藏的页面及控件
	 */
	private void step1(){
		employInfo.setVisibility(View.VISIBLE);
		myselfInfo.setVisibility(View.GONE);
		papersInfo.setVisibility(View.GONE);
		history.setVisibility(View.GONE);
		familyInfo.setVisibility(View.GONE);
		hobbies.setVisibility(View.GONE);
		layout_ret.setVisibility(View.VISIBLE);
		layout_goBackEmployInfo.setVisibility(View.GONE);
	    layout_goBackMyselfInfo.setVisibility(View.GONE);
	    layout_goBackPapersInfo.setVisibility(View.GONE);
	    layout_goBackHistory.setVisibility(View.GONE);
	    layout_goBackFamilyInfo.setVisibility(View.GONE);
	    iv_step1.setVisibility(View.VISIBLE);
	    iv_step2.setVisibility(View.GONE);
	    iv_step3.setVisibility(View.GONE);
	    iv_step4.setVisibility(View.GONE);
	    iv_step5.setVisibility(View.GONE);
	    iv_step6.setVisibility(View.GONE);
		tv_title.setText("应聘信息");
	}
	private void step2(){
		employInfo.setVisibility(View.GONE);
		myselfInfo.setVisibility(View.VISIBLE);
		papersInfo.setVisibility(View.GONE);
		history.setVisibility(View.GONE);
		familyInfo.setVisibility(View.GONE);
		hobbies.setVisibility(View.GONE);
		layout_ret.setVisibility(View.GONE);
		layout_goBackEmployInfo.setVisibility(View.VISIBLE);
		layout_goBackMyselfInfo.setVisibility(View.GONE);
		layout_goBackPapersInfo.setVisibility(View.GONE);
		layout_goBackHistory.setVisibility(View.GONE);
		layout_goBackFamilyInfo.setVisibility(View.GONE);
		tv_title.setText("个人信息");
		iv_step1.setVisibility(View.GONE);
	    iv_step2.setVisibility(View.VISIBLE);
		iv_step3.setVisibility(View.GONE);
		iv_step4.setVisibility(View.GONE);
		iv_step5.setVisibility(View.GONE);
		iv_step6.setVisibility(View.GONE);
	}
	private void step3(){
		employInfo.setVisibility(View.GONE);
		myselfInfo.setVisibility(View.GONE);
		papersInfo.setVisibility(View.VISIBLE);
		history.setVisibility(View.GONE);
		familyInfo.setVisibility(View.GONE);
		hobbies.setVisibility(View.GONE);
		layout_ret.setVisibility(View.GONE);
		layout_goBackEmployInfo.setVisibility(View.GONE);
		layout_goBackMyselfInfo.setVisibility(View.VISIBLE);
		layout_goBackPapersInfo.setVisibility(View.GONE);
		layout_goBackHistory.setVisibility(View.GONE);
		layout_goBackFamilyInfo.setVisibility(View.GONE);
		tv_title.setText("证件信息");
		iv_step1.setVisibility(View.GONE);
		iv_step2.setVisibility(View.GONE);
		iv_step3.setVisibility(View.VISIBLE);
		iv_step4.setVisibility(View.GONE);
	    iv_step5.setVisibility(View.GONE);
		iv_step6.setVisibility(View.GONE);
	}
	private void step4(){
		employInfo.setVisibility(View.GONE);
		myselfInfo.setVisibility(View.GONE);
		papersInfo.setVisibility(View.GONE);
		history.setVisibility(View.VISIBLE);
		familyInfo.setVisibility(View.GONE);
		hobbies.setVisibility(View.GONE);
		layout_ret.setVisibility(View.GONE);
		layout_goBackEmployInfo.setVisibility(View.GONE);
		layout_goBackMyselfInfo.setVisibility(View.GONE);
		layout_goBackPapersInfo.setVisibility(View.VISIBLE);
		layout_goBackHistory.setVisibility(View.GONE);
		layout_goBackFamilyInfo.setVisibility(View.GONE);
		tv_title.setText("个人经历");
	    iv_step1.setVisibility(View.GONE);
	    iv_step2.setVisibility(View.GONE);
	    iv_step3.setVisibility(View.GONE);
	    iv_step4.setVisibility(View.VISIBLE);
	    iv_step5.setVisibility(View.GONE);
	    iv_step6.setVisibility(View.GONE);
	}
	private void step5(){
		employInfo.setVisibility(View.GONE);
		myselfInfo.setVisibility(View.GONE);
		papersInfo.setVisibility(View.GONE);
		history.setVisibility(View.GONE);
		familyInfo.setVisibility(View.VISIBLE);
		hobbies.setVisibility(View.GONE);
		layout_ret.setVisibility(View.GONE);
		layout_goBackEmployInfo.setVisibility(View.GONE);
		layout_goBackMyselfInfo.setVisibility(View.GONE);
		layout_goBackPapersInfo.setVisibility(View.GONE);
		layout_goBackHistory.setVisibility(View.VISIBLE);
		layout_goBackFamilyInfo.setVisibility(View.GONE);
		tv_title.setText("家庭信息");
		iv_step1.setVisibility(View.GONE);
	    iv_step2.setVisibility(View.GONE);
	    iv_step3.setVisibility(View.GONE);
	    iv_step4.setVisibility(View.GONE);
	    iv_step5.setVisibility(View.VISIBLE);
	    iv_step6.setVisibility(View.GONE);
	}
	private void step6(){
		employInfo.setVisibility(View.GONE);
		myselfInfo.setVisibility(View.GONE);
		papersInfo.setVisibility(View.GONE);
		history.setVisibility(View.GONE);
		familyInfo.setVisibility(View.GONE);
		hobbies.setVisibility(View.VISIBLE);
		layout_ret.setVisibility(View.GONE);
		layout_goBackEmployInfo.setVisibility(View.GONE);
		layout_goBackMyselfInfo.setVisibility(View.GONE);
		layout_goBackPapersInfo.setVisibility(View.GONE);
		layout_goBackHistory.setVisibility(View.GONE);
		layout_goBackFamilyInfo.setVisibility(View.VISIBLE);
		tv_title.setText("兴趣爱好");
		iv_step1.setVisibility(View.GONE);
	    iv_step2.setVisibility(View.GONE);
	    iv_step3.setVisibility(View.GONE);
	    iv_step4.setVisibility(View.GONE);
	    iv_step5.setVisibility(View.GONE);
	    iv_step6.setVisibility(View.VISIBLE);
	}
	@Override
	public void onClick(View v) {
		 String saveSuccess="保存成功";
		 String upLoadSuccess="上传成功";
		 employInfo = (LinearLayout)findViewById(R.id.employInfo);
		 myselfInfo = (LinearLayout)findViewById(R.id.myselfInfo);
		 papersInfo = (LinearLayout)findViewById(R.id.papersInfo);
		 history = (LinearLayout)findViewById(R.id.history);
		 familyInfo = (LinearLayout)findViewById(R.id.layout_familyInfo);
		 hobbies = (LinearLayout)findViewById(R.id.hobbies);
		switch (v.getId()) {
		case R.id.layout_ret:
			finish();
			break;
			//应聘信息
		case R.id.btn_saveEmployInfo:  //保存应聘信息
			saveAll(1,saveSuccess);
			break;
		case R.id.btn_employInfoNext:  //应聘信息上的下一步进入到个人信息的填写	
			String address=et_address.getText().toString();
			String mobile=et_mobile.getText().toString();
			String urgentContact=et_urgentContact.getText().toString();
			String urgentMobile=et_urgentMobile.getText().toString();
			String residence=et_residence.getText().toString();
			if (TextUtils.isEmpty(address)) {
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
			} else if (TextUtils.isEmpty(residence)) {
				Toast.show(self, resources.getString(R.string.entryRegions));
				return;
			} else if (!isMobile(et_mobile.getText().toString())) {
				Toast.show(self, resources.getString(R.string.mobileFormat));
				return;
			} else if (!isMobile(et_urgentMobile.getText().toString())) {
				Toast.show(self, resources.getString(R.string.urgentMobileFormat));
				return;  
			} else {
				step2();
				
			}
			break;
			//个人信息
		case R.id.layout_goBackEmployInfo:   //个人信息上的上一步返回到应聘信息页面
			step1();
			break;
		case R.id.btn_saveMyselfInfo:  //个人信息
			saveAll(2,saveSuccess);
			break;
		case R.id.btn_myselfInfoNext:  //个人信息上的下一步进入到证件信息
			String personName=et_personName.getText().toString();
			String regions=et_regions.getText().toString();
			String idNo=et_idNo.getText().toString();
			String enducationNo=et_educationNo.getText().toString();
			String depositBank=et_depositBank.getText().toString();
			String depositCardNo=et_depositCardNo.getText().toString();
			if (TextUtils.isEmpty(personName)) {
				Toast.show(self, resources.getString(R.string.entryPersonName));
				return;
			} else if (TextUtils.isEmpty(regions)) {
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
				step3();
			}
			break;
			
			//证件信息
		case R.id.layout_goBackMyselfInfo:  //证件信息上的上一步返回到个人信息
			step2();
			break;
		case R.id.btn_savePapersInfo:  //保存证件信息
			saveImage();
			saveAll(3,saveSuccess);
			break;
		case R.id.btn_papersInfoNext:  //证件信息的下一步进入到个人经历
			step4();
			break;
			//个人经历
		case R.id.layout_goBackPapersInfo:  //个人经历中的上一步返回到证件信息
			step3();
			break;
		case R.id.btn_saveHistory:  //保存经历信息
			saveAll(4,saveSuccess);
			break;
		case R.id.btn_historyNext:  //个人经历的下一步进入到家庭信息
			step5();
			break;
		case R.id.layout_education:
			showMenu(v.getId());
			list_educationInfo.setVisibility(View.VISIBLE);
	   		list_workExperienceInfo.setVisibility(View.GONE);
			btn_addEducation.setVisibility(View.VISIBLE);
			btn_addWorkExperience.setVisibility(View.GONE);
			break;
		case R.id.layout_workExperience:
			showMenu(v.getId());
			list_educationInfo.setVisibility(View.GONE);
	   		list_workExperienceInfo.setVisibility(View.VISIBLE);
	   		btn_addEducation.setVisibility(View.GONE);
			btn_addWorkExperience.setVisibility(View.VISIBLE);
			break;
		case R.id.btn_addEducation://添加学习经历
			final EntryAddInfoManager entryAddEducationInfo ;
			entryAddEducationInfo=new EntryAddInfoManager(self,self);
			entryAddEducationInfo.addEducationInfo(educationAdapter);
			break;
		case R.id.btn_addWorkExperience://添加工作经验
			final EntryAddInfoManager entryAddWorkExperienceInfo ;
			entryAddWorkExperienceInfo=new EntryAddInfoManager(self,self);
			entryAddWorkExperienceInfo.addWorkExperienceInfo(workExperienceAdapter);
			break;
			//家庭信息
		case R.id.layout_goBackHistory:  //家庭信息中的上一步返回到个人经历
			step4();
			break;
			//添加亲属信息
		case R.id.btn_addFamilyInfo:
			final EntryAddInfoManager entryAddFamilyInfo ;
			entryAddFamilyInfo=new EntryAddInfoManager(self,self);
			entryAddFamilyInfo.addFamilyInfo(adapterFamily);
			break;
		case R.id.btn_saveFamilyInfo:  //保存证件信息
			saveAll(5,saveSuccess);
			break;
		case R.id.btn_familyInfoNext:  //家庭信息的下一步进入到兴趣爱好
			step6();
			break;
			//兴趣爱好 
		case R.id.layout_goBackFamilyInfo:  //家庭信息中的上一步返回到个人经历
			step5();
			break;
		case R.id.btn_saveHobbies:
			saveAll(6,saveSuccess);
			break;
			//提交改变状态位
		case R.id.btn_sumbit:
			submited(1,upLoadSuccess);
			break;
		default:
			break;
	}
  }
	/**
	 * 初始化数据
	 */
    private void initData()
	 {
		 OperationBuilder builder = new OperationBuilder().append(new EntryManageOp(), null);
			OnOperationListener listener = new OnOperationListener() {
				@Override
				public void onOperationFinished(List<Object> resList) {
					 employInfo = (LinearLayout)findViewById(R.id.employInfo);
					 myselfInfo = (LinearLayout)findViewById(R.id.myselfInfo);
					 papersInfo = (LinearLayout)findViewById(R.id.papersInfo);
					 history = (LinearLayout)findViewById(R.id.history);
					 familyInfo = (LinearLayout)findViewById(R.id.layout_familyInfo);
					 hobbies = (LinearLayout)findViewById(R.id.hobbies);
					//应聘信息			
					EntryEntity entity = (EntryEntity) resList.get(0);
					entryManageEntity = entity.getRetObj();
					if (entryManageEntity != null) {
						if(entryManageEntity.getComDep()==null)
						{
							sp_comDep.setSelection(0,true);
						}else{
							 ArrayAdapter<SpinnerData> comDep=(ArrayAdapter<SpinnerData>)sp_comDep.getAdapter();
							   for(int i=0;i<comDep.getCount();i++)
							    {
							      if(entryManageEntity.getComDep().equals(comDep.getItem(i).getValue().toString()))
							        {
							          sp_comDep.setSelection(i,true);
							    	 }
							     }
						}
						if(entryManageEntity.getComPos()==null)
						{
							sp_comPos.setSelection(0,true);
						}else{
							   ArrayAdapter<SpinnerData> comPos=(ArrayAdapter<SpinnerData>)sp_comPos.getAdapter();
							   for(int i=0;i<comPos.getCount();i++)
							    {
							      if(entryManageEntity.getComPos().equals(comPos.getItem(i).getValue().toString()))
							        {
							          sp_comPos.setSelection(i,true);
							    	 }
							     }
						}
						SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
					    et_comEntryDate.setText(date.format(new Date(Long.parseLong(entryManageEntity.getComEntryDate().substring(6, 19)))));
					    et_residence.setText(entryManageEntity.getResidence());
					    et_mobile.setText(entryManageEntity.getMobile());
					    et_urgentContact.setText(entryManageEntity.getUrgentContact());
					    et_urgentMobile.setText(entryManageEntity.getUrgentMobile());
					    et_regions.setText(entryManageEntity.getRegions());
					    //个人信息
					    et_personName.setText(entryManageEntity.getPersonName());
					    et_englishName.setText(entryManageEntity.getEnglishName());
					    if((Integer.toString((entryManageEntity.getGender()))).equals(null)){
					    }else{
					    	if((Integer.toString((entryManageEntity.getGender()))).equals("1")){
						    	femaleButton.setChecked(true);
						    }else{
						    	maleButton.setChecked(true);
						    }
					    }
					    et_address.setText(entryManageEntity.getAddress());
					    et_idNo.setText(entryManageEntity.getIdNo());
					    et_educationNo.setText(entryManageEntity.getEducationNo());
					    et_depositBank.setText(entryManageEntity.getDepositBank());
					    et_depositCardNo.setText(entryManageEntity.getDepositCardNo());
					    et_accumFund.setText(entryManageEntity.getAccumFund());
					    //证件信息
					    //个人照片
//					    String fileName = "/storage/sdcard0/DCIM/Screenshots/aa.png";
					    String materials=entryManageEntity.getMaterials();
					    if(materials!=null){
					        try {
								JSONObject jsonObjectMaterials =new JSONObject(materials);
								 new DownImgAsyncTask().execute(jsonObjectMaterials.getString("Certificates"));
								 Log.e("+++++++++++++++++++++++=", jsonObjectMaterials.getString("Certificates"));
						         imgViewPhoto.setMaxHeight(200);  
						         imgViewPhoto.setScaleType(ImageView.ScaleType.CENTER_CROP);
						         new DownImgAsyncTask().execute(jsonObjectMaterials.getString("LearningCertificate"));
						         Log.e("===========================", jsonObjectMaterials.getString("LearningCertificate"));
						         imgViewAcademic.setMaxHeight(200);  
						         imgViewAcademic.setScaleType(ImageView.ScaleType.CENTER_CROP);
						         
							} catch (JSONException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
					    }
					    //个人经历
					    String experienceInfo=entryManageEntity.getExperiences();
					    if(experienceInfo!=null){
					    	try {
								JSONObject jsonObject2 =new JSONObject(experienceInfo);
								JSONArray jsonArrayLearning = jsonObject2.getJSONArray("Learning");
								JSONArray jsonArrayJob = jsonObject2.getJSONArray("Job");
								JsonParser parser = new JsonParser();
						    	JsonArray jsonArray1;
						    	JsonElement jsonElement;
						    	Iterator it ;
								if(jsonArrayLearning.length()!=0){
								EntryManageEducationInfoEntity tempEducation;
						        jsonElement = parser.parse(jsonArrayLearning.toString());
						    	if(jsonElement.isJsonArray()){
						    		jsonArray1 = jsonElement.getAsJsonArray();
							        it = jsonArray1.iterator();
							    	while(it.hasNext()){
							    		JsonElement e = (JsonElement)it.next();
							    		//JsonElement转换为JavaBean对象
							    		tempEducation = new Gson().fromJson(e, EntryManageEducationInfoEntity.class);
							    		educationAdapter.addItem(tempEducation);
							    		educationAdapter.notifyDataSetChanged();
							    	}
						    	  }
							    }
								if(jsonArrayJob.length()!=0){
									EntryManageWorkExperienceInfoEntity tempWorkExperience;
								    jsonElement = parser.parse(jsonArrayJob.toString());
									if(jsonElement.isJsonArray()){
							    		jsonArray1 = jsonElement.getAsJsonArray();
								        it = jsonArray1.iterator();
								    	while(it.hasNext()){
								    		JsonElement e = (JsonElement)it.next();
								    		//JsonElement转换为JavaBean对象
								    		tempWorkExperience = new Gson().fromJson(e, EntryManageWorkExperienceInfoEntity.class);
								    		workExperienceAdapter.addItem(tempWorkExperience);
								    		workExperienceAdapter.notifyDataSetChanged();
								    		
								    	}
							    	  }
								}
							} catch (JSONException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
					    } 
					    //家庭信息
					    String familyDetailsInfo=entryManageEntity.getFamily();	
					       /* 数据静态添加到tempFaimly中
					    	EntryManageFamilyInfoEntity tempFamily=new EntryManageFamilyInfoEntity();
					    	tempFamily.setName("1");
					    	tempFamily.setAddress("2");
					    	tempFamily.setBirthday("3");
					    	tempFamily.setRelationShip("4");
					    	adapterFamily.addItem(tempFamily);
				    	    adapterFamily.notifyDataSetChanged();
				    	    */
					    if(familyDetailsInfo!=null){
					    	try {
								JSONObject relativesJsonObject =new JSONObject(familyDetailsInfo);
								JSONArray relativesJsonArray = relativesJsonObject.getJSONArray("Relatives");
								JsonParser parser = new JsonParser();
						    	JsonArray jsonArray1;
						    	JsonElement jsonElement;
						    	Iterator it ;
								if(relativesJsonArray.length()!=0){
							    EntryManageFamilyInfoEntity tempFamily;
						        jsonElement = parser.parse(relativesJsonArray.toString());
						    	if(jsonElement.isJsonArray()){
						    		jsonArray1 = jsonElement.getAsJsonArray();
							        it = jsonArray1.iterator();
							    	while(it.hasNext()){
							    		JsonElement e = (JsonElement)it.next();
							    		//JsonElement转换为JavaBean对象
							    		tempFamily = new Gson().fromJson(e, EntryManageFamilyInfoEntity.class);
							    		adapterFamily.addItem(tempFamily);
							    		adapterFamily.notifyDataSetChanged();
							    	}
						    	  }
							    }
							} catch (JSONException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}	  
					    }
					    /*
					    if(familyDetailsInfo!=null){
					    	JsonParser parser = new JsonParser();
					    	JsonArray jsonArray1;
					    	EntryManageFamilyInfoEntity tempFamily;
					    	JsonElement jsonElement = parser.parse(familyDetailsInfo);
					    	if(jsonElement.isJsonArray()){
					    		jsonArray1 = jsonElement.getAsJsonArray();
						    	Iterator it = jsonArray1.iterator();
						    	while(it.hasNext()){
						    		JsonElement e = (JsonElement)it.next();
						    		Log.e("----------------------------",e.toString());
						    		//JsonElement转换为JavaBean对象
						    		tempFamily = new Gson().fromJson(e, EntryManageFamilyInfoEntity.class);
						    		adapterFamily.addItem(tempFamily);
						    		adapterFamily.notifyDataSetChanged();
						    	}
					    	}	
					    	  				    	
					    }
					    */
					    //兴趣爱好
					    String getHobbies=entryManageEntity.getInteresting();
					    String hobbies1="";
					    if(getHobbies!=null){
					    	String[] arrayHobbies = getHobbies.split(",");
					    	for(int m=0;m<arrayHobbies.length;m++){
					    		if(m==0){
					    			arrayHobbies[m]=arrayHobbies[m].replace("[\"", "");
						    		hobbies1=arrayHobbies[m].replace("\"", "");
					    		}else if(m==(arrayHobbies.length-1)){
					    			arrayHobbies[m]=arrayHobbies[m].replace("\"", "");
						    		hobbies1=arrayHobbies[m].replace("\"", "");
						    		hobbies1=hobbies1.replace("]", "");
					    		}else{
					    			arrayHobbies[m]=arrayHobbies[m].replace("\"", "");
						    		hobbies1=arrayHobbies[m].replace("\"", "");
					    		}
					    			if(hobbies1.equals("打篮球"))
					    				 basketball.setChecked(true);
					    			if(hobbies1.equals("踢足球"))
					    				 football.setChecked(true);
					    			if(hobbies1.equals("打羽毛球"))
					    				badminton.setChecked(true);
					    			if(hobbies1.equals("打乒乓球"))
					    				table_tennis.setChecked(true);
					    			if(hobbies1.equals("爬山"))
					    				mountains.setChecked(true);
					    			if(hobbies1.equals("唱歌"))
					    				sing.setChecked(true);
					    			if(hobbies1.equals("看书"))
					    				book.setChecked(true);
					    			if(hobbies1.equals("烹饪"))
					    				cooking.setChecked(true);
					    			if(hobbies1.equals("画画"))
					    				drawing.setChecked(true);
					    			if(hobbies1.equals("舞蹈"))
					    				dance.setChecked(true);
					    			if(hobbies1.equals("旅游"))
					    				travel.setChecked(true);
					    			if(hobbies1.equals("摄影"))
					    				photography.setChecked(true);
					    	}
					    }
	                      if(entryManageEntity.getCurrentStep()==1){
					    		step1();
					    	}else if(entryManageEntity.getCurrentStep()==2){
					    		step2();
					    	}else if(entryManageEntity.getCurrentStep()==3){
					    		step3();
					    	}else if(entryManageEntity.getCurrentStep()==4){
					    		step4();
					    	}else if(entryManageEntity.getCurrentStep()==5){
					    		step5();
					    	}else if(entryManageEntity.getCurrentStep()==6){
					    		step6();
					    	}else{
					    		step1();
					    	}
					}
				}
				@Override
				public void onOperationError(Exception e) {
					e.printStackTrace();
				}
			};
			JsonCommon task = new JsonCommon(self, builder, listener,
					JsonCommon.PROGRESSLOADING);
			task.execute();
	 }
    /**
     * 绑定部门和职位
     * @param type
     * @param parentId
     */
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
				        list_comDep = new ArrayList<SpinnerData>();
				        for(int i=0;i<departmentList.size();i++)
				        {
				        	SpinnerData comDep = new SpinnerData(departmentList.get(i).getSysValue(),departmentList.get(i).getSysKeyName());
				        	list_comDep.add(comDep);
				        }
				        //适配器
				        comDep_adapter= new ArrayAdapter<SpinnerData>(EntryManagementActiviy.this, android.R.layout.simple_spinner_item, list_comDep);
				        //设置样式
				        comDep_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item );
				        //加载适配器
				        sp_comDep.setAdapter(comDep_adapter);
					}else if(type=="Compos"&&parentId==-1){
						//数据
				        list_comPos = new ArrayList<SpinnerData>();
				        for(int i=0;i<departmentList.size();i++)
				        {
				        	SpinnerData comPos = new SpinnerData(departmentList.get(i).getSysValue(),departmentList.get(i).getSysKeyName());
				        	list_comPos.add(comPos);
				        }
				        
				        //适配器
				        comPos_adapter= new ArrayAdapter<SpinnerData>(EntryManagementActiviy.this, android.R.layout.simple_spinner_item, list_comPos);
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
	/**
	 * 保存证件图片
	 */
	private void saveImage(){
		 EntryManageEntity entity=new EntryManageEntity();
		 entity.setLoginName(SharedPreferencesUtils.getLoginName(JoyApplication.getSelf()));
		 entity.setMaterials(picturePath);
		 entity.setCurrentStep(3);
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
	 * @param submited 最后一步是否提交状态位
	 * @param currentStep当前第几步
	 * @param retMessage 返回的是否保存成功还是上传成功
	 */
	private void saveAll(final int currentStep,final String retMessage)
	 {
		OperationBuilder builder = new OperationBuilder().append(new EntryManageOp(), null);
		OnOperationListener listener = new OnOperationListener() {
			@Override
			public void onOperationFinished(List<Object> resList) {	
				EntryEntity entity = (EntryEntity) resList.get(0);
				 entryManageEntity = entity.getRetObj();
				 EntryManageEntity entity1=new EntryManageEntity();
				 EntryManageExperiencesListEntity experience=new EntryManageExperiencesListEntity();
				 EntryManageFamilyListEntity family=new EntryManageFamilyListEntity();
				 entity1.setLoginName(SharedPreferencesUtils.getLoginName(JoyApplication.getSelf()));
				 //应聘信息
				 entity1.setComDep(((SpinnerData)sp_comDep.getSelectedItem()).getValue());
				 entity1.setComPos(((SpinnerData)sp_comPos.getSelectedItem()).getValue());
				 entity1.setComEntryDate(et_comEntryDate.getText().toString());
				 entity1.setResidence(et_residence.getText().toString());
				 if(TextUtils.isEmpty(et_mobile.getText().toString())) {
					}else{
						if(!isMobile(et_mobile.getText().toString())){
							Toast.show(self, resources.getString(R.string.mobileFormat));
							return;
						 }else{
							 entity1.setMobile(et_mobile.getText().toString());
						 }
					} 
				 if(TextUtils.isEmpty(et_urgentMobile.getText().toString())) {
					}else{
						if(!isMobile(et_urgentMobile.getText().toString())){
							Toast.show(self, resources.getString(R.string.mobileFormat));
							return;
						 }else{
							 entity1.setUrgentMobile(et_urgentMobile.getText().toString());
						 }
					} 
				 entity1.setUrgentContact(et_urgentContact.getText().toString());
				 entity1.setRegions(et_regions.getText().toString());
				 //个人信息
				 entity1.setPersonName(et_personName.getText().toString());
				 entity1.setEnglishName(et_englishName.getText().toString());
				 if(femaleButton.isChecked())
				 {
					 entity1.setGender(1);
				 }else{
					 entity1.setGender(0);
				 }
				 entity1.setAddress(et_address.getText().toString());
				 if(TextUtils.isEmpty(et_idNo.getText().toString())) {
					}else{
						if(!isIdNo(et_idNo.getText().toString())){
							Toast.show(self, resources.getString(R.string.idNoFormat));
							return;
						 }else{
							 entity1.setIdNo(et_idNo.getText().toString());
						 }
					} 
				 entity1.setEducationNo(et_educationNo.getText().toString());
				 entity1.setAccumFund(et_accumFund.getText().toString());
				 entity1.setDepositBank(et_depositBank.getText().toString());
				 entity1.setDepositCardNo(et_depositCardNo.getText().toString());
				 //个人经历
				 experience.setLearning(educationAdapter.getData());//学习经历的数据
				 experience.setJob(workExperienceAdapter.getData());//工作经历的数据
				 entity1.setExperiences(new Gson().toJson(experience));
				 //家庭信息
				 family.setRelatives(adapterFamily.getData());
				 entity1.setFamily(new Gson().toJson(family));
				 //兴趣爱好
				 //判断 是否被选中得到其被选中的值，拼成数组放到后台
				 checkBoxHobbiesList = new ArrayList<String>();
				 if(basketball.isChecked())
					 checkBoxHobbiesList.add(basketball.getText().toString());
				if(football.isChecked())
					 checkBoxHobbiesList.add(football.getText().toString());
				if(badminton.isChecked())
					 checkBoxHobbiesList.add(badminton.getText().toString());
				if(table_tennis.isChecked())
					 checkBoxHobbiesList.add(table_tennis.getText().toString());
				if(mountains.isChecked())
					 checkBoxHobbiesList.add(mountains.getText().toString());
				if(sing.isChecked())
					 checkBoxHobbiesList.add(sing.getText().toString());
				if(book.isChecked())
					 checkBoxHobbiesList.add(book.getText().toString());
				if(cooking.isChecked())
					 checkBoxHobbiesList.add(cooking.getText().toString());
				if(drawing.isChecked())
					 checkBoxHobbiesList.add(drawing.getText().toString());
				if(dance.isChecked())
					 checkBoxHobbiesList.add(dance.getText().toString());
				if(travel.isChecked())
					 checkBoxHobbiesList.add(travel.getText().toString());
				if(photography.isChecked())
					 checkBoxHobbiesList.add(photography.getText().toString());
				 entity1.setInteresting(new Gson().toJson(checkBoxHobbiesList));
				 entity1.setCurrentStep(currentStep);
				 if(entryManageEntity.getSubmited()==1){
					 entity1.setSubmited(1);
				 }else{
					 entity1.setSubmited(0);
				 }
				 OperationBuilder builder1 = new OperationBuilder().append(
							new EntrySaveOp(), entity1);
			    	OnOperationListener listener1 = new OnOperationListener() {
						@Override
						public void onOperationFinished(List<Object> resList) {
							if (self.isFinishing()) {
								return;
							}else if(resList==null){
								Toast.show(self,"连接超时");
								return;
							}else{
								Toast.show(self,retMessage);
							}
						}
						@Override
						public void onOperationError(Exception e) {
							e.printStackTrace();
						}
			    	};
			    	JsonCommon task1 = new JsonCommon(self, builder1, listener1,
							JsonCommon.PROGRESSCOMMIT);
					task1.execute();
			}
			@Override
			public void onOperationError(Exception e) {
				e.printStackTrace();
			}
		};
		JsonCommon task = new JsonCommon(self, builder, listener,null);
		task.execute();
	 }
	private void submited(int currentStep,final String retMessage){
		 EntryManageEntity entity=new EntryManageEntity();
		 EntryManageExperiencesListEntity experience=new EntryManageExperiencesListEntity();
		 EntryManageFamilyListEntity family=new EntryManageFamilyListEntity();
		 entity.setLoginName(SharedPreferencesUtils.getLoginName(JoyApplication.getSelf()));
		 //应聘信息
		 entity.setComDep(((SpinnerData)sp_comDep.getSelectedItem()).getValue());
		 entity.setComPos(((SpinnerData)sp_comPos.getSelectedItem()).getValue());
		 entity.setComEntryDate(et_comEntryDate.getText().toString());
		 entity.setResidence(et_residence.getText().toString());
		 if(TextUtils.isEmpty(et_mobile.getText().toString())) {
			}else{
				if(!isMobile(et_mobile.getText().toString())){
					Toast.show(self, resources.getString(R.string.mobileFormat));
					return;
				 }else{
					 entity.setMobile(et_mobile.getText().toString());
				 }
			} 
		 if(TextUtils.isEmpty(et_urgentMobile.getText().toString())) {
			}else{
				if(!isMobile(et_urgentMobile.getText().toString())){
					Toast.show(self, resources.getString(R.string.mobileFormat));
					return;
				 }else{
					 entity.setUrgentMobile(et_urgentMobile.getText().toString());
				 }
			} 
		 entity.setUrgentContact(et_urgentContact.getText().toString());
		 entity.setRegions(et_regions.getText().toString());
		 //个人信息
		 entity.setPersonName(et_personName.getText().toString());
		 entity.setEnglishName(et_englishName.getText().toString());
		 if(femaleButton.isChecked())
		 {
			 entity.setGender(1);
		 }else{
			 entity.setGender(0);
		 }
		 entity.setAddress(et_address.getText().toString());
		 if(TextUtils.isEmpty(et_idNo.getText().toString())) {
			}else{
				if(!isIdNo(et_idNo.getText().toString())){
					Toast.show(self, resources.getString(R.string.idNoFormat));
					return;
				 }else{
					 entity.setIdNo(et_idNo.getText().toString());
				 }
			} 
		 entity.setEducationNo(et_educationNo.getText().toString());
		 entity.setAccumFund(et_accumFund.getText().toString());
		 entity.setDepositBank(et_depositBank.getText().toString());
		 entity.setDepositCardNo(et_depositCardNo.getText().toString());
		 //个人经历
		 experience.setLearning(educationAdapter.getData());//学习经历的数据
		 experience.setJob(workExperienceAdapter.getData());//工作经历的数据
		 entity.setExperiences(new Gson().toJson(experience));
		 //家庭信息
		 family.setRelatives(adapterFamily.getData());
		 entity.setFamily(new Gson().toJson(family));
		 //兴趣爱好
		 //判断 是否被选中得到其被选中的值，拼成数组放到后台
		 checkBoxHobbiesList = new ArrayList<String>();
		 if(basketball.isChecked())
			 checkBoxHobbiesList.add(basketball.getText().toString());
		if(football.isChecked())
			 checkBoxHobbiesList.add(football.getText().toString());
		if(badminton.isChecked())
			 checkBoxHobbiesList.add(badminton.getText().toString());
		if(table_tennis.isChecked())
			 checkBoxHobbiesList.add(table_tennis.getText().toString());
		if(mountains.isChecked())
			 checkBoxHobbiesList.add(mountains.getText().toString());
		if(sing.isChecked())
			 checkBoxHobbiesList.add(sing.getText().toString());
		if(book.isChecked())
			 checkBoxHobbiesList.add(book.getText().toString());
		if(cooking.isChecked())
			 checkBoxHobbiesList.add(cooking.getText().toString());
		if(drawing.isChecked())
			 checkBoxHobbiesList.add(drawing.getText().toString());
		if(dance.isChecked())
			 checkBoxHobbiesList.add(dance.getText().toString());
		if(travel.isChecked())
			 checkBoxHobbiesList.add(travel.getText().toString());
		if(photography.isChecked())
			 checkBoxHobbiesList.add(photography.getText().toString());
		 entity.setInteresting(new Gson().toJson(checkBoxHobbiesList));
		 entity.setCurrentStep(currentStep);
		 entity.setSubmited(1);//上传状态位
		 entity.setCurrentStep(6);
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
						Toast.show(self,retMessage);
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
	 * 手机格式
	 * @param mobile
	 * @return
	 */
	public boolean isMobile(String mobile)
	{
		Pattern p=Pattern.compile("^(13[0-9]|14[0-9]|15[0-9]|17[0-9]|18[0-9])\\d{8}$");//正则表达式验证手机的正确性
		Matcher m=p.matcher(mobile);
		return m.matches();
	}
	/**
	 * 身份证判断格式(只能为15位或者18位数字)
	 * @param idNo
	 * @return
	 */
	public boolean isIdNo(String idNo)
	{
		 Pattern idNoPattern = Pattern.compile("(\\d{14}[0-9a-zA-Z])|(\\d{17}[0-9a-zA-Z])");  
	     //通过Pattern获得Matcher  
	     Matcher idNoMatcher = idNoPattern.matcher(idNo);
	     return idNoMatcher.matches();
    }
	 /**
	 * 从指定URL获取图片
	 * @param url
	 * @return
	 */
    private Bitmap getImageBitmap(String url){
		URL imgUrl = null;
		Bitmap bitmap = null;
		try {
			imgUrl = new URL(url);
			HttpURLConnection conn = (HttpURLConnection)imgUrl.openConnection();
			conn.setDoInput(true);
			conn.connect();
			InputStream is = conn.getInputStream();
			bitmap = BitmapFactory.decodeStream(is);
			is.close();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch(IOException e){
			e.printStackTrace();
		}
		return bitmap;
	}
    class DownImgAsyncTask extends AsyncTask<String, Void, Bitmap>{
		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			imgViewPhoto.setImageBitmap(null);
			imgViewAcademic.setImageBitmap(null);
		}
		@Override
		protected Bitmap doInBackground(String... params) {
			// TODO Auto-generated method stub
			Bitmap b = getImageBitmap(params[0]);
			return b;
		}
		@Override
		protected void onPostExecute(Bitmap result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			if(result!=null){
				imgViewPhoto.setImageBitmap(result);
				imgViewAcademic.setImageBitmap(result);
			}
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
	         picturePath = cursor.getString(columnIndex);
	         cursor.close();	          
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
	         Log.e("图片路径图片路径图片路径图片路径图片路径", picturePath);
	         imgViewPhoto.setImageBitmap(bitmap);  
	         imgViewPhoto.setMaxHeight(200);  
	         imgViewPhoto.setScaleType(ImageView.ScaleType.CENTER_CROP);
	        }
	    }  
}  

