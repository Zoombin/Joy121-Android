package com.joy.Activity;

import gejw.android.quickandroid.QActivity;
import gejw.android.quickandroid.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
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
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.joy.JoyApplication;
import com.joy.R;
import com.joy.Utils.Constants;
import com.joy.Utils.EntryAddInfoManager;
import com.joy.Utils.EntryDate;
import com.joy.Utils.LoadImageRunnable;
import com.joy.Utils.SharedPreferencesUtils;
import com.joy.Widget.EntryEducationDetailAdapter;
import com.joy.Widget.EntryFamilyDetailAdapter;
import com.joy.Widget.EntryWrokExperienceDetailAdapter;
import com.joy.Widget.ActivityAdapter.ViewHolder;
import com.joy.json.JsonCommon;
import com.joy.json.JsonCommon.OnOperationListener;
import com.joy.json.model.CompAppSet;
import com.joy.json.model.EntryDepartmentDetailEntity;
import com.joy.json.model.EntryDepartmentEntity;
import com.joy.json.model.EntryEntity;
import com.joy.json.model.EntryManageBankCardImageEntity;
import com.joy.json.model.EntryManageEducationInfoEntity;
import com.joy.json.model.EntryManageEntity;
import com.joy.json.model.EntryManageExperiencesListEntity;
import com.joy.json.model.EntryManageFamilyInfoEntity;
import com.joy.json.model.EntryManageFamilyListEntity;
import com.joy.json.model.EntryManageIDImageEntity;
import com.joy.json.model.EntryManageImageEntity;
import com.joy.json.model.EntryManageWorkExperienceInfoEntity;
import com.joy.json.model.EntryUploadImageEntity;
import com.joy.json.model.SpinnerData;
import com.joy.json.operation.OperationBuilder;
import com.joy.json.operation.impl.ComGroupSysData;
import com.joy.json.operation.impl.EntryDepartmentOp;
import com.joy.json.operation.impl.EntryManageOp;
import com.joy.json.operation.impl.EntrySaveOp;
import com.joy.json.operation.impl.UploadImgOp;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.umeng.analytics.MobclickAgent;

import android.R.integer;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.AnimationUtils;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

/**
 * @rainbow 入职管理
 */
public class EntryManagementActiviy extends BaseActivity implements
		OnClickListener {
	protected QActivity mActivity;
	private static int RESULT_LOAD_IMAGE = 1;

	private RelativeLayout layout_title;
	private LinearLayout layout_ret, layout_goBackEmployInfo,
			layout_goBackMyselfInfo, layout_goBackPapersInfo,
			layout_goBackHistory, layout_goBackFamilyInfo;
	private ImageView iv_step1, iv_step2, iv_step3, iv_step4, iv_step5,
			iv_step6;
	private TextView tv_title;
	EntryManageEntity entryManageEntity;
	private int gender;
	// 应聘信息
	private String initDate = "2015-08-14";
	private List<SpinnerData> list_comDep, list_comPos;
	private ArrayAdapter<SpinnerData> comDep_adapter, comPos_adapter;
	private ImageView iv_comDep, iv_comPos, iv_comEntryDate, iv_residence,
			iv_mobile, iv_urgentContact, iv_urgentMobile, iv_regions,
			iv_urgentAddr;
	private TextView tv_comDep, tv_comPos, tv_comEntryDate, tv_residence,
			tv_mobile, tv_urgentContact, tv_urgentMobile, tv_regions,
			tv_urgentAddr;
	private EditText et_comEntryDate, et_residence, et_mobile,
			et_urgentContact, et_urgentMobile, et_regions, et_urgentAddr;
	private Spinner sp_comDep, sp_comPos;
	private Button btn_saveEmployInfo, btn_employInfoNext;
	// 个人信息
	private List<SpinnerData> list_maritalStatus, list_politicalStatus,
			list_healthCondition, list_culturalDegree, list_nation,list_regions,
			list_depositBank;
	private ArrayAdapter<SpinnerData> maritalStatus_adapter,
			politicalStatus_adapter, healthCondition_adapter,
			culturalDegree_adapter, nation_adapter, depositBank_adapter,regions_adapter;
	private ImageView iv_personName, iv_englishName, iv_gender, iv_address,
			iv_idNo, iv_educationNo, iv_accumFund, iv_depositBank,
			iv_depositCardNo, iv_nation, iv_maritalStatus, iv_politicalStatus,
			iv_healthCondition, iv_culturalDegree, iv_major,
			iv_socialSecurityNo;
	private TextView tv_personName, tv_englishName, tv_gender, tv_address,
			tv_idNo, tv_educationNo, tv_accumFund, tv_depositBank,
			tv_depositCardNo, tv_nation, tv_maritalStatus, tv_politicalStatus,
			tv_healthCondition, tv_culturalDegree, tv_major,
			tv_socialSecurityNo;
	private Spinner sp_nation, sp_maritalStatus, sp_politicalStatus,
			sp_healthCondition, sp_culturalDegree, sp_depositBank,sp_regions;
	private RadioGroup radiogender;
	private RadioButton maleButton, femaleButton;
	private EditText et_personName, et_englishName, et_address, et_idNo,
			et_educationNo, et_accumFund, et_depositCardNo, et_major,
			et_socialSecurityNo;
	private Button btn_saveMyselfInfo, btn_myselfInfoNext;
	// 证件信息
	private ImageView iv_myselfPhoto, iv_myselfVideo, iv_academicPhoto,
			iv_idPhoto,iv_bankCardPhoto,iv_repairOrder, iv_checkupReporting;
	private ImageView imgViewPhoto, imgViewVedio, imgViewAcademic,
			imgViewIdPhoto1, imgViewIdPhoto2,imgViewBankCardPositive,imgViewBankCardReverse, imgViewRepairOrder,
			imgViewCheckupReporting;
	private TextView tv_myselfPhoto, tv_myselfVideo, tv_academicPhoto,
			tv_idPhoto,tv_bankCardPhoto, tv_repairOrder, tv_checkupReporting;
	private LinearLayout ll_popup;// 证件信息调用拍照或者从相册选择布局
	private PopupWindow pop = null;
	private Button btn_savePapersInfo, btn_papersInfoNext;
	private String certificates = "", video, learningCertificate = "",
			positive = "", reverse = "",cardpositive = "", cardreverse = "", retirement = "", physical = "";
	private int submite=0;
	// 个人经历
	private LinearLayout layout_menu, layout_education, layout_workExperience;
	private TextView tv_education, tv_workExperience;
	public ListView list_educationInfo, list_workExperienceInfo;
	private EntryEducationDetailAdapter educationAdapter;
	private EntryWrokExperienceDetailAdapter workExperienceAdapter;
	private Button btn_addEducation, btn_addWorkExperience, btn_saveHistory,
			btn_historyNext;
	// 家庭信息
	private TextView tv_familyInfo;
	private View line_familyInfo;
	private EntryFamilyDetailAdapter adapterFamily;
	private ListView list_familyInfo;
	private Button btn_saveFamilyInfo, btn_familyInfoNext, btn_addFamilyInfo;
	// 兴趣爱好
	private CheckBox basketball, football, badminton, table_tennis, mountains,
			sing, book, cooking, drawing, dance, travel, photography;
	private List<String> checkBoxHobbiesList;
	private Button btn_saveHobbies;
	private Button btn_sumbit;
	CompAppSet appSet;
	int color;
	private Resources resources;
	private ScrollView scroll_myselfInfo;
	private LinearLayout employInfo, myselfInfo, papersInfo, history,
			familyInfo, hobbies;
	private RelativeLayout employInfoNext, myselfInfoNext, papersInfoNext,
			historyNext, familyInfoNext, hobbiesSumbit;
	int j = 0;// 从图片库选着图片的线程显示
	int m = 6;// 调用系统照相机的线程显示

	// 创建线程显示图片
	// private static final int THREAD_Photo = 1;
	// private static final int THREAD_Academic = 2;
	// private static final int THREAD_IdPhoto1 = 3;
	// private static final int THREAD_IdPhoto2 = 4;
	// private static final int THREAD_RepairOrder = 5;
	// private static final int THREAD_CheckupReporting = 6;
	Window window;
	Bitmap bitmap;

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
		setContentView(v);
		initEmployInfo();
		bindDepartmentOrPos("CostCenterno", -1);// 传入-1显示全部部门
		bindDepartmentOrPos("Compos", -1);
		bindSysData();
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
		initImage();
		return v;
	}

	private void initImage() {
		pop = new PopupWindow(EntryManagementActiviy.this);
		View view = getLayoutInflater().inflate(R.layout.item_popupwindows,
				null);

		ll_popup = (LinearLayout) view.findViewById(R.id.ll_popup);

		pop.setWidth(LayoutParams.MATCH_PARENT);
		pop.setHeight(LayoutParams.WRAP_CONTENT);
		pop.setBackgroundDrawable(new BitmapDrawable());
		pop.setFocusable(true);
		pop.setOutsideTouchable(true);
		pop.setContentView(view);

		RelativeLayout parent = (RelativeLayout) view.findViewById(R.id.parent);
		Button bt1 = (Button) view.findViewById(R.id.item_popupwindows_camera);
		Button bt2 = (Button) view.findViewById(R.id.item_popupwindows_Photo);
		Button bt3 = (Button) view.findViewById(R.id.item_popupwindows_cancel);
		parent.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				pop.dismiss();
				ll_popup.clearAnimation();
			}
		});
		bt1.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				photo(m);
				pop.dismiss();
				ll_popup.clearAnimation();
			}
		});
		bt2.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent i = new Intent(
						Intent.ACTION_PICK,
						android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
				startActivityForResult(i, j);
				pop.dismiss();
				ll_popup.clearAnimation();
			}
		});
		bt3.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				pop.dismiss();
				ll_popup.clearAnimation();
			}
		});
		// 响应img图片选择图库相片
		imgViewPhoto.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
				// intent.setType("image/*");
				// intent.putExtra("crop", true);
				// intent.putExtra("return-data", true);
				// startActivityForResult(intent, 0);
				j = 0;
				m = 8;
				pop.showAtLocation(imgViewPhoto, Gravity.BOTTOM, 0, 0);
				// Intent i = new Intent(
				// Intent.ACTION_PICK,
				// android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
				// startActivityForResult(i, 0);
				//
			}
		});
		imgViewAcademic.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				j = 1;
				m = 9;
				pop.showAtLocation(imgViewAcademic, Gravity.BOTTOM, 0, 0);
			}
		});
		imgViewIdPhoto1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				j = 2;
				m = 10;
				pop.showAtLocation(imgViewIdPhoto1, Gravity.BOTTOM, 0, 0);
			}
		});
		imgViewIdPhoto2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				j = 3;
				m = 11;
				pop.showAtLocation(imgViewIdPhoto2, Gravity.BOTTOM, 0, 0);
			}
		});
		imgViewRepairOrder.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				j = 4;
				m = 12;
				pop.showAtLocation(imgViewRepairOrder, Gravity.BOTTOM, 0, 0);
			}
		});
		imgViewCheckupReporting.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// Intent i = new Intent(
				// Intent.ACTION_PICK,
				// android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
				// startActivityForResult(i, 5);
				j = 5;
				m = 13;
				pop.showAtLocation(imgViewCheckupReporting, Gravity.BOTTOM, 0,
						0);
			}
		});
		imgViewBankCardPositive.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				j = 6;
				m = 14;
				pop.showAtLocation(imgViewBankCardPositive, Gravity.BOTTOM, 0, 0);
			}
		});
		imgViewBankCardReverse.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				j = 7;
				m = 15;
				pop.showAtLocation(imgViewBankCardReverse, Gravity.BOTTOM, 0, 0);
			}
		});
	}

	/**
	 * 应聘信息
	 */
	private void initEmployInfo() {
		layout_title = (RelativeLayout) findViewById(R.id.layout_title);
		uiAdapter.setMargin(layout_title, LayoutParams.MATCH_PARENT,
				Constants.SubTitleHeight, 0, 0, 0, 0);
		layout_ret = (LinearLayout) findViewById(R.id.layout_ret);
		layout_ret.setOnClickListener(this);
		layout_goBackEmployInfo = (LinearLayout) findViewById(R.id.layout_goBackEmployInfo);
		layout_goBackEmployInfo.setOnClickListener(this);
		layout_goBackMyselfInfo = (LinearLayout) findViewById(R.id.layout_goBackMyselfInfo);
		layout_goBackMyselfInfo.setOnClickListener(this);

		layout_goBackPapersInfo = (LinearLayout) findViewById(R.id.layout_goBackPapersInfo);
		layout_goBackPapersInfo.setOnClickListener(this);

		layout_goBackHistory = (LinearLayout) findViewById(R.id.layout_goBackHistory);
		layout_goBackHistory.setOnClickListener(this);

		layout_goBackFamilyInfo = (LinearLayout) findViewById(R.id.layout_goBackFamilyInfo);
		layout_goBackFamilyInfo.setOnClickListener(this);

		iv_step1 = (ImageView) findViewById(R.id.iv_step1);
		iv_step2 = (ImageView) findViewById(R.id.iv_step2);
		iv_step3 = (ImageView) findViewById(R.id.iv_step3);
		iv_step4 = (ImageView) findViewById(R.id.iv_step4);
		iv_step5 = (ImageView) findViewById(R.id.iv_step5);
		iv_step6 = (ImageView) findViewById(R.id.iv_step6);

		tv_title = (TextView) findViewById(R.id.tv_title);
		uiAdapter.setTextSize(tv_title, Constants.TitleSize);
		// 应聘部门
		iv_comDep = (ImageView) findViewById(R.id.iv_comDep);
		uiAdapter.setMargin(iv_comDep, LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT, 20, 22, 0, 10);
		tv_comDep = (TextView) findViewById(R.id.tv_comDep);
		uiAdapter.setTextSize(tv_comDep, 18);
		uiAdapter.setMargin(tv_comDep, LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT, 10, 15, 0, 10);
		sp_comDep = (Spinner) findViewById(R.id.sp_comDep);
		uiAdapter.setMargin(sp_comDep, LayoutParams.MATCH_PARENT, 37, 5, 20,
				45, 0);

		// 应聘职位
		iv_comPos = (ImageView) findViewById(R.id.iv_comPos);
		uiAdapter.setMargin(iv_comPos, LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT, 20, 22, 0, 10);
		tv_comPos = (TextView) findViewById(R.id.tv_comPos);
		uiAdapter.setTextSize(tv_comPos, 18);
		uiAdapter.setMargin(tv_comPos, LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT, 10, 15, 0, 10);
		sp_comPos = (Spinner) findViewById(R.id.sp_comPos);
		uiAdapter.setMargin(sp_comPos, LayoutParams.MATCH_PARENT, 37, 5, 20,
				45, 0);
		// 入职日期
		iv_comEntryDate = (ImageView) findViewById(R.id.iv_comEntryDate);
		uiAdapter.setMargin(iv_comEntryDate, LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT, 20, 22, 0, 10);
		tv_comEntryDate = (TextView) findViewById(R.id.tv_comEntryDate);
		uiAdapter.setTextSize(tv_comEntryDate, 18);
		uiAdapter.setMargin(tv_comEntryDate, LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT, 10, 15, 0, 10);
		et_comEntryDate = (EditText) findViewById(R.id.et_comEntryDate);
		et_comEntryDate.setText(initDate);
		uiAdapter.setMargin(et_comEntryDate, LayoutParams.MATCH_PARENT, 37, 5,
				20, 45, 0);
		et_comEntryDate.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				EntryDate dateTimePicKDialog = new EntryDate(
						EntryManagementActiviy.this, initDate);
				dateTimePicKDialog.dateTimePicKDialog(et_comEntryDate);
			}
		});
		// 现居地址
		iv_residence = (ImageView) findViewById(R.id.iv_residence);
		uiAdapter.setMargin(iv_residence, LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT, 20, 25, 0, 10);
		tv_residence = (TextView) findViewById(R.id.tv_residence);
		uiAdapter.setTextSize(tv_residence, 18);
		uiAdapter.setMargin(tv_residence, LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT, 10, 15, 0, 10);
		et_address = (EditText) findViewById(R.id.et_address);
		uiAdapter.setMargin(et_address, LayoutParams.MATCH_PARENT, 37, 5, 20,
				45, 0);
		// 联系方式
		iv_mobile = (ImageView) findViewById(R.id.iv_mobile);
		uiAdapter.setMargin(iv_mobile, LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT, 20, 25, 0, 10);
		tv_mobile = (TextView) findViewById(R.id.tv_mobile);
		uiAdapter.setTextSize(tv_mobile, 18);
		uiAdapter.setMargin(tv_mobile, LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT, 10, 15, 0, 10);
		et_mobile = (EditText) findViewById(R.id.et_mobile);
		uiAdapter.setMargin(et_mobile, LayoutParams.MATCH_PARENT, 37, 5, 20,
				45, 0);
		// 紧急联系人
		iv_urgentContact = (ImageView) findViewById(R.id.iv_urgentContact);
		uiAdapter.setMargin(iv_urgentContact, LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT, 20, 25, 0, 10);
		tv_urgentContact = (TextView) findViewById(R.id.tv_urgentContact);
		uiAdapter.setTextSize(tv_urgentContact, 18);
		uiAdapter.setMargin(tv_urgentContact, LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT, 10, 15, 0, 10);
		et_urgentContact = (EditText) findViewById(R.id.et_urgentContact);
		uiAdapter.setMargin(et_urgentContact, LayoutParams.MATCH_PARENT, 37, 5,
				20, 45, 0);
		// 紧急联系方式
		iv_urgentMobile = (ImageView) findViewById(R.id.iv_urgentMobile);
		uiAdapter.setMargin(iv_urgentMobile, LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT, 20, 25, 0, 10);
		tv_urgentMobile = (TextView) findViewById(R.id.tv_urgentMobile);
		uiAdapter.setTextSize(tv_urgentMobile, 18);
		uiAdapter.setMargin(tv_urgentMobile, LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT, 10, 15, 0, 10);
		et_urgentMobile = (EditText) findViewById(R.id.et_urgentMobile);
		uiAdapter.setMargin(et_urgentMobile, LayoutParams.MATCH_PARENT, 37, 5,
				20, 45, 0);

		// 紧急联系人地址
		iv_urgentAddr = (ImageView) findViewById(R.id.iv_urgentAddr);
		uiAdapter.setMargin(iv_urgentAddr, LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT, 20, 25, 0, 10);
		tv_urgentAddr = (TextView) findViewById(R.id.tv_urgentAddr);
		uiAdapter.setTextSize(tv_urgentAddr, 18);
		uiAdapter.setMargin(tv_urgentAddr, LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT, 10, 15, 0, 10);
		et_urgentAddr = (EditText) findViewById(R.id.et_urgentAddr);
		uiAdapter.setMargin(et_urgentAddr, LayoutParams.MATCH_PARENT, 37, 5,
				20, 45, 0);
		// 户口所在地
		iv_regions = (ImageView) findViewById(R.id.iv_regions);
		uiAdapter.setMargin(iv_regions, LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT, 20, 25, 0, 10);
		tv_regions = (TextView) findViewById(R.id.tv_regions);
		uiAdapter.setTextSize(tv_regions, 18);
		uiAdapter.setMargin(tv_regions, LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT, 10, 15, 0, 10);
		et_residence = (EditText) findViewById(R.id.et_residence);
		uiAdapter.setMargin(et_residence, LayoutParams.MATCH_PARENT, 37, 5, 20,
				45, 0);
		employInfoNext = (RelativeLayout) findViewById(R.id.employInfoNext);
		// 保存
		btn_saveEmployInfo = (Button) findViewById(R.id.btn_saveEmployInfo);
		uiAdapter.setTextSize(btn_saveEmployInfo, 24);

		// 下一步
		btn_employInfoNext = (Button) findViewById(R.id.btn_employInfoNext);
		uiAdapter.setTextSize(btn_employInfoNext, 24);

	}

	/**
	 * 个人信息
	 */
	private void initViewMyselfInfo() {
		// 中文名
		iv_personName = (ImageView) findViewById(R.id.iv_personName);
		uiAdapter.setMargin(iv_personName, LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT, 20, 22, 0, 10);
		tv_personName = (TextView) findViewById(R.id.tv_personName);
		uiAdapter.setTextSize(tv_personName, 18);
		uiAdapter.setMargin(tv_personName, LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT, 10, 15, 0, 10);
		et_personName = (EditText) findViewById(R.id.et_personName);
		uiAdapter.setMargin(et_personName, LayoutParams.MATCH_PARENT, 37, 5,
				20, 45, 0);
		// 英文名
		iv_englishName = (ImageView) findViewById(R.id.iv_englishName);
		uiAdapter.setMargin(iv_englishName, LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT, 20, 22, 0, 10);
		tv_englishName = (TextView) findViewById(R.id.tv_englishName);
		uiAdapter.setTextSize(tv_englishName, 18);
		uiAdapter.setMargin(tv_englishName, LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT, 10, 15, 0, 10);
		et_englishName = (EditText) findViewById(R.id.et_englishName);
		uiAdapter.setMargin(et_englishName, LayoutParams.MATCH_PARENT, 37, 5,
				20, 45, 0);
		uiAdapter.setPadding(et_englishName, 10, 0, 0, 0);
		// 性别
		iv_gender = (ImageView) findViewById(R.id.iv_gender);
		uiAdapter.setMargin(iv_gender, LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT, 17, 15, 0, 10);
		tv_gender = (TextView) findViewById(R.id.tv_gender);
		uiAdapter.setTextSize(tv_gender, 18);
		uiAdapter.setMargin(tv_gender, LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT, 10, 20, 0, 10);
		radiogender = (RadioGroup) findViewById(R.id.radiogender);
		maleButton = (RadioButton) findViewById(R.id.male);
		femaleButton = (RadioButton) findViewById(R.id.female);
		uiAdapter.setPadding(radiogender, 10, 15, 0, 0);
		// 民族sp_nation
		iv_nation = (ImageView) findViewById(R.id.iv_nation);
		uiAdapter.setMargin(iv_nation, LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT, 20, 22, 0, 10);
		tv_nation = (TextView) findViewById(R.id.tv_nation);
		uiAdapter.setTextSize(tv_nation, 18);
		uiAdapter.setMargin(tv_nation, LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT, 10, 15, 0, 10);
		sp_nation = (Spinner) findViewById(R.id.sp_nation);
		uiAdapter.setMargin(sp_nation, LayoutParams.MATCH_PARENT, 37, 5, 20,
				45, 0);
		// 籍贯
		iv_address = (ImageView) findViewById(R.id.iv_address);
		uiAdapter.setMargin(iv_address, LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT, 20, 22, 0, 10);
		tv_address = (TextView) findViewById(R.id.tv_address);
		uiAdapter.setTextSize(tv_address, 18);
		uiAdapter.setMargin(tv_address, LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT, 10, 15, 0, 10);
		sp_regions = (Spinner) findViewById(R.id.sp_regions);
		uiAdapter.setMargin(sp_regions, LayoutParams.MATCH_PARENT, 37, 5, 20,
				45, 0);		
		// 身份证号
		iv_idNo = (ImageView) findViewById(R.id.iv_idNo);
		uiAdapter.setMargin(iv_idNo, LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT, 20, 22, 0, 10);
		tv_idNo = (TextView) findViewById(R.id.tv_idNo);
		uiAdapter.setTextSize(tv_idNo, 18);
		uiAdapter.setMargin(tv_idNo, LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT, 10, 15, 0, 10);
		et_idNo = (EditText) findViewById(R.id.et_idNo);
		uiAdapter.setMargin(et_idNo, LayoutParams.MATCH_PARENT, 37, 5, 20, 45,
				0);
		uiAdapter.setPadding(et_idNo, 10, 0, 0, 0);
		// 婚姻状况
		iv_maritalStatus = (ImageView) findViewById(R.id.iv_maritalStatus);
		uiAdapter.setMargin(iv_maritalStatus, LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT, 20, 22, 0, 10);
		tv_maritalStatus = (TextView) findViewById(R.id.tv_maritalStatus);
		uiAdapter.setTextSize(tv_maritalStatus, 18);
		uiAdapter.setMargin(tv_maritalStatus, LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT, 10, 15, 0, 10);
		sp_maritalStatus = (Spinner) findViewById(R.id.sp_maritalStatus);
		uiAdapter.setMargin(sp_maritalStatus, LayoutParams.MATCH_PARENT, 37, 5,
				20, 45, 0);
		// 政治面貌
		iv_politicalStatus = (ImageView) findViewById(R.id.iv_politicalStatus);
		uiAdapter.setMargin(iv_politicalStatus, LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT, 20, 22, 0, 10);
		tv_politicalStatus = (TextView) findViewById(R.id.tv_politicalStatus);
		uiAdapter.setTextSize(tv_politicalStatus, 18);
		uiAdapter.setMargin(tv_politicalStatus, LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT, 10, 15, 0, 10);
		sp_politicalStatus = (Spinner) findViewById(R.id.sp_politicalStatus);
		uiAdapter.setMargin(sp_politicalStatus, LayoutParams.MATCH_PARENT, 37,
				5, 20, 45, 0);
		// 健康状况
		iv_healthCondition = (ImageView) findViewById(R.id.iv_healthCondition);
		uiAdapter.setMargin(iv_healthCondition, LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT, 20, 22, 0, 10);
		tv_healthCondition = (TextView) findViewById(R.id.tv_healthCondition);
		uiAdapter.setTextSize(tv_healthCondition, 18);
		uiAdapter.setMargin(tv_healthCondition, LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT, 10, 15, 0, 10);
		sp_healthCondition = (Spinner) findViewById(R.id.sp_healthCondition);
		uiAdapter.setMargin(sp_healthCondition, LayoutParams.MATCH_PARENT, 37,
				5, 20, 45, 0);
		// 文化程度
		iv_culturalDegree = (ImageView) findViewById(R.id.iv_culturalDegree);
		uiAdapter.setMargin(iv_culturalDegree, LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT, 20, 22, 0, 10);
		tv_culturalDegree = (TextView) findViewById(R.id.tv_culturalDegree);
		uiAdapter.setTextSize(tv_culturalDegree, 18);
		uiAdapter.setMargin(tv_culturalDegree, LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT, 10, 15, 0, 10);
		sp_culturalDegree = (Spinner) findViewById(R.id.sp_culturalDegree);
		uiAdapter.setMargin(sp_culturalDegree, LayoutParams.MATCH_PARENT, 37,
				5, 20, 45, 0);
		// 学历号
		iv_educationNo = (ImageView) findViewById(R.id.iv_educationNo);
		uiAdapter.setMargin(iv_educationNo, LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT, 20, 22, 0, 10);
		tv_educationNo = (TextView) findViewById(R.id.tv_educationNo);
		uiAdapter.setTextSize(tv_educationNo, 18);
		uiAdapter.setMargin(tv_educationNo, LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT, 10, 15, 0, 10);
		et_educationNo = (EditText) findViewById(R.id.et_educationNo);
		uiAdapter.setMargin(et_educationNo, LayoutParams.MATCH_PARENT, 37, 5,
				20, 45, 0);
		uiAdapter.setPadding(et_educationNo, 10, 0, 0, 0);
		// 专业
		iv_major = (ImageView) findViewById(R.id.iv_major);
		uiAdapter.setMargin(iv_major, LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT, 20, 22, 0, 10);
		tv_major = (TextView) findViewById(R.id.tv_major);
		uiAdapter.setTextSize(tv_major, 18);
		uiAdapter.setMargin(tv_major, LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT, 10, 15, 0, 10);
		et_major = (EditText) findViewById(R.id.et_major);
		uiAdapter.setMargin(et_major, LayoutParams.MATCH_PARENT, 37, 5, 20, 45,
				0);
		uiAdapter.setPadding(et_major, 10, 0, 0, 0);
		// 开户银行
		iv_depositBank = (ImageView) findViewById(R.id.iv_depositBank);
		uiAdapter.setMargin(iv_depositBank, LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT, 20, 22, 0, 10);
		tv_depositBank = (TextView) findViewById(R.id.tv_depositBank);
		uiAdapter.setTextSize(tv_depositBank, 18);
		uiAdapter.setMargin(tv_depositBank, LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT, 10, 15, 0, 10);
		sp_depositBank = (Spinner) findViewById(R.id.sp_depositBank);
		uiAdapter.setMargin(sp_depositBank, LayoutParams.MATCH_PARENT, 37, 5,
				20, 45, 0);
		// 银行账号
		iv_depositCardNo = (ImageView) findViewById(R.id.iv_depositCardNo);
		uiAdapter.setMargin(iv_depositCardNo, LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT, 20, 22, 0, 10);
		tv_depositCardNo = (TextView) findViewById(R.id.tv_depositCardNo);
		uiAdapter.setTextSize(tv_depositCardNo, 18);
		uiAdapter.setMargin(tv_depositCardNo, LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT, 10, 15, 0, 10);
		et_depositCardNo = (EditText) findViewById(R.id.et_depositCardNo);
		uiAdapter.setMargin(et_depositCardNo, LayoutParams.MATCH_PARENT, 37, 5,
				20, 45, 0);
		uiAdapter.setPadding(et_depositCardNo, 10, 0, 0, 0);
		// 社会公积金编号
		iv_accumFund = (ImageView) findViewById(R.id.iv_accumFund);
		uiAdapter.setMargin(iv_accumFund, LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT, 20, 22, 0, 10);
		tv_accumFund = (TextView) findViewById(R.id.tv_accumFund);
		uiAdapter.setTextSize(tv_accumFund, 18);
		uiAdapter.setMargin(tv_accumFund, LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT, 10, 15, 0, 10);
		et_accumFund = (EditText) findViewById(R.id.et_accumFund);
		uiAdapter.setMargin(et_accumFund, LayoutParams.MATCH_PARENT, 37, 5, 20,
				45, 0);
		uiAdapter.setPadding(et_accumFund, 10, 0, 0, 0);
		// 社保账号
		iv_socialSecurityNo = (ImageView) findViewById(R.id.iv_socialSecurityNo);
		uiAdapter.setMargin(iv_socialSecurityNo, LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT, 20, 22, 0, 10);
		tv_socialSecurityNo = (TextView) findViewById(R.id.tv_socialSecurityNo);
		uiAdapter.setTextSize(tv_socialSecurityNo, 18);
		uiAdapter.setMargin(tv_socialSecurityNo, LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT, 10, 15, 0, 10);
		et_socialSecurityNo = (EditText) findViewById(R.id.et_socialSecurityNo);
		uiAdapter.setMargin(et_socialSecurityNo, LayoutParams.MATCH_PARENT, 30,
				5, 20, 45, 0);
		uiAdapter.setPadding(et_socialSecurityNo, 10, 0, 0, 0);

		myselfInfoNext = (RelativeLayout) findViewById(R.id.myselfInfoNext);
		// 保存
		btn_saveMyselfInfo = (Button) findViewById(R.id.btn_saveMyselfInfo);
		uiAdapter.setTextSize(btn_saveMyselfInfo, 24);
		// 下一步
		btn_myselfInfoNext = (Button) findViewById(R.id.btn_myselfInfoNext);
		uiAdapter.setTextSize(btn_myselfInfoNext, 24);
	}

	/**
	 * 证件信息
	 */
	private void initViewPapersInfo() {
		imgViewPhoto = (ImageView) findViewById(R.id.imgViewPhoto);
		
		// imgViewVedio = (ImageView) findViewById(R.id.imgViewVideo);
		imgViewAcademic = (ImageView) findViewById(R.id.imgViewAcademic);
		imgViewIdPhoto1 = (ImageView) findViewById(R.id.imgViewIdPhoto1);// 身份证正面
		imgViewIdPhoto2 = (ImageView) findViewById(R.id.imgViewIdPhoto2);// 身份证反面
		imgViewBankCardPositive = (ImageView) findViewById(R.id.imgViewBankCardPositive);//银行卡正面
		imgViewBankCardReverse = (ImageView) findViewById(R.id.imgViewBankCardReverse);// 银行卡反面
		imgViewRepairOrder = (ImageView) findViewById(R.id.imgViewRepairOrder);
		imgViewCheckupReporting = (ImageView) findViewById(R.id.imgViewCheckupReporting);
		// 个人照片
		iv_myselfPhoto = (ImageView) findViewById(R.id.iv_myselfPhoto);
		uiAdapter.setMargin(iv_myselfPhoto, LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT, 20, 30, 0, 10);
		tv_myselfPhoto = (TextView) findViewById(R.id.tv_myselfPhoto);
		uiAdapter.setTextSize(tv_myselfPhoto, 18);
		uiAdapter.setMargin(tv_myselfPhoto, LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT, 10, 30, 0, 10);
		
		// 个人视频
		// iv_myselfVideo = (ImageView) findViewById(R.id.iv_myselfVideo);
		// uiAdapter.setMargin(iv_myselfVideo, LayoutParams.WRAP_CONTENT,
		// LayoutParams.WRAP_CONTENT, 20, 30, 0, 10);
		// tv_myselfVideo = (TextView) findViewById(R.id.tv_myselfVideo);
		// uiAdapter.setTextSize(tv_myselfVideo, 18);
		// uiAdapter.setMargin(tv_myselfVideo, LayoutParams.WRAP_CONTENT,
		// LayoutParams.WRAP_CONTENT, 10, 30, 0, 10);
		// 学历证书
		iv_academicPhoto = (ImageView) findViewById(R.id.iv_academicPhoto);
		uiAdapter.setMargin(iv_academicPhoto, LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT, 20, 30, 0, 10);
		tv_academicPhoto = (TextView) findViewById(R.id.tv_academicPhoto);
		uiAdapter.setTextSize(tv_academicPhoto, 18);
		uiAdapter.setMargin(tv_academicPhoto, LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT, 10, 30, 0, 10);
		// 身份证
		iv_idPhoto = (ImageView) findViewById(R.id.iv_idPhoto);
		uiAdapter.setMargin(iv_idPhoto, LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT, 20, 30, 0, 10);
		tv_idPhoto = (TextView) findViewById(R.id.tv_idPhoto);
		uiAdapter.setTextSize(tv_idPhoto, 18);
		uiAdapter.setMargin(tv_idPhoto, LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT, 10, 30, 0, 10);
		//银行卡
		iv_bankCardPhoto = (ImageView) findViewById(R.id.iv_bankCardPhoto);
		uiAdapter.setMargin(iv_bankCardPhoto, LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT, 20, 30, 0, 10);
		tv_bankCardPhoto = (TextView) findViewById(R.id.tv_bankCardPhoto);
		uiAdapter.setTextSize(tv_bankCardPhoto, 18);
		uiAdapter.setMargin(tv_bankCardPhoto, LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT, 10, 30, 0, 10);
		// 退工单
		iv_repairOrder = (ImageView) findViewById(R.id.iv_repairOrder);
		uiAdapter.setMargin(iv_repairOrder, LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT, 20, 30, 0, 10);
		tv_repairOrder = (TextView) findViewById(R.id.tv_repairOrder);
		uiAdapter.setTextSize(tv_repairOrder, 18);
		uiAdapter.setMargin(tv_repairOrder, LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT, 10, 30, 0, 10);
		// 体检报告
		iv_checkupReporting = (ImageView) findViewById(R.id.iv_checkupReporting);
		uiAdapter.setMargin(iv_checkupReporting, LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT, 20, 30, 0, 10);
		tv_checkupReporting = (TextView) findViewById(R.id.tv_checkupReporting);
		uiAdapter.setTextSize(tv_checkupReporting, 18);
		uiAdapter.setMargin(tv_checkupReporting, LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT, 10, 30, 0, 10);
		papersInfoNext = (RelativeLayout) findViewById(R.id.papersInfoNext);

		// 保存
		btn_savePapersInfo = (Button) findViewById(R.id.btn_savePapersInfo);
		uiAdapter.setTextSize(btn_savePapersInfo, 24);
		// 下一步
		btn_papersInfoNext = (Button) findViewById(R.id.btn_papersInfoNext);
		uiAdapter.setTextSize(btn_papersInfoNext, 24);
	}

	/**
	 * 个人经历
	 */
	private void initViewHistory() {
		layout_menu = (LinearLayout) findViewById(R.id.layout_menu);
		// 学习经历
		layout_education = (LinearLayout) findViewById(R.id.layout_education);
		layout_education.setOnClickListener(this);
		tv_education = (TextView) findViewById(R.id.tv_education);
		btn_addEducation = (Button) findViewById(R.id.btn_addEducation);
		uiAdapter.setMargin(btn_addEducation, LayoutParams.MATCH_PARENT, 45,
				10, 20, 10, 0);
		uiAdapter.setTextSize(btn_addEducation, 20);
		list_educationInfo = (ListView) findViewById(R.id.list_educationInfo);
		uiAdapter.setMargin(list_educationInfo, LayoutParams.MATCH_PARENT,
				LayoutParams.WRAP_CONTENT, 0, 0, 0, 0);
		educationAdapter = new EntryEducationDetailAdapter(self, self);
		list_educationInfo.setAdapter(educationAdapter);// 给listView设置适配器

		// 工作经历
		layout_workExperience = (LinearLayout) findViewById(R.id.layout_workExperience);
		layout_workExperience.setOnClickListener(this);
		tv_workExperience = (TextView) findViewById(R.id.tv_workExperience);
		btn_addWorkExperience = (Button) findViewById(R.id.btn_addWorkExperience);
		uiAdapter.setMargin(btn_addWorkExperience, LayoutParams.MATCH_PARENT,
				45, 10, 20, 10, 0);
		uiAdapter.setTextSize(btn_addWorkExperience, 20);

		list_workExperienceInfo = (ListView) findViewById(R.id.list_workExperienceInfo);
		uiAdapter.setMargin(list_workExperienceInfo, LayoutParams.MATCH_PARENT,
				LayoutParams.WRAP_CONTENT, 0, 0, 0, 0);
		workExperienceAdapter = new EntryWrokExperienceDetailAdapter(self, self);
		list_workExperienceInfo.setAdapter(workExperienceAdapter);
		historyNext = (RelativeLayout) findViewById(R.id.historyNext);

		// 保存
		btn_saveHistory = (Button) findViewById(R.id.btn_saveHistory);
		uiAdapter.setTextSize(btn_saveHistory, 24);
		// 下一步
		btn_historyNext = (Button) findViewById(R.id.btn_historyNext);
		uiAdapter.setTextSize(btn_historyNext, 24);
		defaultShow();// 初始化显示经历的后背景框
	}

	@SuppressWarnings("deprecation")
	private void defaultShow() {
		layout_education.setBackgroundDrawable(this.getResources().getDrawable(
				R.drawable.entry_educationclick));
		layout_workExperience.setBackgroundDrawable(this.getResources()
				.getDrawable(R.drawable.entry_workexperience));
		list_educationInfo.setVisibility(View.VISIBLE);
		list_workExperienceInfo.setVisibility(View.GONE);
		btn_addEducation.setVisibility(View.VISIBLE);
		btn_addWorkExperience.setVisibility(View.GONE);
	}

	@SuppressWarnings("deprecation")
	private void showMenu(int layout) {
		switch (layout) {
		case R.id.layout_education:
			layout_education.setBackgroundDrawable(this.getResources()
					.getDrawable(R.drawable.entry_educationclick));
			layout_workExperience.setBackgroundDrawable(this.getResources()
					.getDrawable(R.drawable.entry_workexperience));
			break;
		case R.id.layout_workExperience:
			layout_education.setBackgroundDrawable(this.getResources()
					.getDrawable(R.drawable.entry_education));
			layout_workExperience.setBackgroundDrawable(this.getResources()
					.getDrawable(R.drawable.entry_workexperienceclick));
		default:
			break;
		}
	}

	/**
	 * 家庭信息
	 */
	private void initViewFamilyInfo() {
		// 亲属信息
		list_familyInfo = (ListView) findViewById(R.id.list_familyInfo);
		uiAdapter.setMargin(list_familyInfo, LayoutParams.MATCH_PARENT,
				LayoutParams.WRAP_CONTENT, 0, 0, 0, 0);
		adapterFamily = new EntryFamilyDetailAdapter(self, self);
		list_familyInfo.setAdapter(adapterFamily);

		btn_addFamilyInfo = (Button) findViewById(R.id.btn_addFamilyInfo);
		uiAdapter.setMargin(btn_addFamilyInfo, LayoutParams.MATCH_PARENT, 45,
				10, 20, 10, 0);
		uiAdapter.setTextSize(btn_addFamilyInfo, 20);
		familyInfoNext = (RelativeLayout) findViewById(R.id.familyInfoNext);

		// 保存
		btn_saveFamilyInfo = (Button) findViewById(R.id.btn_saveFamilyInfo);
		uiAdapter.setTextSize(btn_saveFamilyInfo, 24);
		// 下一步
		btn_familyInfoNext = (Button) findViewById(R.id.btn_familyInfoNext);
		uiAdapter.setTextSize(btn_familyInfoNext, 24);
	}

	/**
	 * 兴趣爱好
	 */
	private void initViewHobbies() {
		basketball = (CheckBox) findViewById(R.id.basketball);
		basketball.setBackgroundResource(R.drawable.check_hobbies);

		table_tennis = (CheckBox) findViewById(R.id.table_tennis);
		table_tennis.setBackgroundResource(R.drawable.check_hobbies);

		book = (CheckBox) findViewById(R.id.book);
		book.setBackgroundResource(R.drawable.check_hobbies);

		dance = (CheckBox) findViewById(R.id.dance);
		dance.setBackgroundResource(R.drawable.check_hobbies);

		football = (CheckBox) findViewById(R.id.football);
		football.setBackgroundResource(R.drawable.check_hobbies);

		mountains = (CheckBox) findViewById(R.id.mountains);
		mountains.setBackgroundResource(R.drawable.check_hobbies);

		cooking = (CheckBox) findViewById(R.id.cooking);
		cooking.setBackgroundResource(R.drawable.check_hobbies);

		travel = (CheckBox) findViewById(R.id.travel);
		travel.setBackgroundResource(R.drawable.check_hobbies);

		badminton = (CheckBox) findViewById(R.id.badminton);
		badminton.setBackgroundResource(R.drawable.check_hobbies);

		sing = (CheckBox) findViewById(R.id.sing);
		sing.setBackgroundResource(R.drawable.check_hobbies);

		drawing = (CheckBox) findViewById(R.id.drawing);
		drawing.setBackgroundResource(R.drawable.check_hobbies);

		photography = (CheckBox) findViewById(R.id.photography);
		photography.setBackgroundResource(R.drawable.check_hobbies);
		hobbiesSumbit = (RelativeLayout) findViewById(R.id.hobbiesSumbit);
		// 保存
		btn_saveHobbies = (Button) findViewById(R.id.btn_saveHobbies);
		uiAdapter.setTextSize(btn_saveHobbies, 24);
		// 上传
		btn_sumbit = (Button) findViewById(R.id.btn_sumbit);
		uiAdapter.setTextSize(btn_sumbit, 24);
	}

	/**
	 * 响应按钮显示和隐藏的页面及控件
	 */
	private void step1() {
		employInfo.setVisibility(View.VISIBLE);
		myselfInfo.setVisibility(View.GONE);
		scroll_myselfInfo.setVisibility(View.GONE);
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
		employInfoNext.setVisibility(View.VISIBLE);
		myselfInfoNext.setVisibility(View.GONE);
		papersInfoNext.setVisibility(View.GONE);
		historyNext.setVisibility(View.GONE);
		familyInfoNext.setVisibility(View.GONE);
		hobbiesSumbit.setVisibility(View.GONE);
		tv_title.setText("应聘信息");
	}

	private void step2() {

		employInfo.setVisibility(View.GONE);
		myselfInfo.setVisibility(View.VISIBLE);
		scroll_myselfInfo.setVisibility(View.VISIBLE);
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
		employInfoNext.setVisibility(View.GONE);
		myselfInfoNext.setVisibility(View.VISIBLE);
		papersInfoNext.setVisibility(View.GONE);
		historyNext.setVisibility(View.GONE);
		familyInfoNext.setVisibility(View.GONE);
		hobbiesSumbit.setVisibility(View.GONE);
	}

	private void step3() {
		employInfo.setVisibility(View.GONE);
		myselfInfo.setVisibility(View.GONE);
		scroll_myselfInfo.setVisibility(View.GONE);
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
		employInfoNext.setVisibility(View.GONE);
		myselfInfoNext.setVisibility(View.GONE);
		papersInfoNext.setVisibility(View.VISIBLE);
		historyNext.setVisibility(View.GONE);
		familyInfoNext.setVisibility(View.GONE);
		hobbiesSumbit.setVisibility(View.GONE);
	}

	private void step4() {
		employInfo.setVisibility(View.GONE);
		myselfInfo.setVisibility(View.GONE);
		scroll_myselfInfo.setVisibility(View.GONE);
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
		employInfoNext.setVisibility(View.GONE);
		myselfInfoNext.setVisibility(View.GONE);
		papersInfoNext.setVisibility(View.GONE);
		historyNext.setVisibility(View.VISIBLE);
		familyInfoNext.setVisibility(View.GONE);
		hobbiesSumbit.setVisibility(View.GONE);
	}

	private void step5() {
		employInfo.setVisibility(View.GONE);
		myselfInfo.setVisibility(View.GONE);
		scroll_myselfInfo.setVisibility(View.GONE);
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
		employInfoNext.setVisibility(View.GONE);
		myselfInfoNext.setVisibility(View.GONE);
		papersInfoNext.setVisibility(View.GONE);
		historyNext.setVisibility(View.GONE);
		familyInfoNext.setVisibility(View.VISIBLE);
		hobbiesSumbit.setVisibility(View.GONE);
	}

	private void step6() {
		employInfo.setVisibility(View.GONE);
		myselfInfo.setVisibility(View.GONE);
		scroll_myselfInfo.setVisibility(View.GONE);
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
		employInfoNext.setVisibility(View.GONE);
		myselfInfoNext.setVisibility(View.GONE);
		papersInfoNext.setVisibility(View.GONE);
		historyNext.setVisibility(View.GONE);
		familyInfoNext.setVisibility(View.GONE);
		hobbiesSumbit.setVisibility(View.VISIBLE);
	}

	@Override
	public void onClick(View v) {
		String saveSuccess = "保存成功";
		String upLoadSuccess = "提交成功";
		employInfo = (LinearLayout) findViewById(R.id.employInfo);
		myselfInfo = (LinearLayout) findViewById(R.id.myselfInfo);
		scroll_myselfInfo = (ScrollView) findViewById(R.id.scroll_myselfInfo);
		papersInfo = (LinearLayout) findViewById(R.id.papersInfo);
		history = (LinearLayout) findViewById(R.id.history);
		familyInfo = (LinearLayout) findViewById(R.id.layout_familyInfo);
		hobbies = (LinearLayout) findViewById(R.id.hobbies);
		switch (v.getId()) {
		case R.id.layout_ret:
			finish();
			break;
		// 应聘信息
		case R.id.btn_saveEmployInfo: // 保存应聘信息
			if(submite==1){
				saveAll(6, saveSuccess);
			}else{
				saveAll(1, saveSuccess);
			}
			break;
		case R.id.btn_employInfoNext: // 应聘信息上的下一步进入到个人信息的填写
			String address = et_address.getText().toString();
			String mobile = et_mobile.getText().toString();
			String urgentContact = et_urgentContact.getText().toString();
			String urgentMobile = et_urgentMobile.getText().toString();
			String residence = et_residence.getText().toString();
			if (TextUtils.isEmpty(address)) {
				Toast.show(self, resources.getString(R.string.entryResidence));
				return;
			} else if (TextUtils.isEmpty(mobile)) {
				Toast.show(self, resources.getString(R.string.entryMobile));
				return;
			} else if (TextUtils.isEmpty(urgentContact)) {
				Toast.show(self,
						resources.getString(R.string.entryUrgentContact));
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
				Toast.show(self,
						resources.getString(R.string.urgentMobileFormat));
				return;
			} else {
				step2();

			}
			break;
		// 个人信息
		case R.id.layout_goBackEmployInfo: // 个人信息上的上一步返回到应聘信息页面
			step1();
			break;
		case R.id.btn_saveMyselfInfo: // 个人信息
			if(submite==1){
				saveAll(6, saveSuccess);
			}else{
				saveAll(2, saveSuccess);
			}
			break;
		case R.id.btn_myselfInfoNext: // 个人信息上的下一步进入到证件信息
			String personName = et_personName.getText().toString();
//			String regions = et_regions.getText().toString();
			String idNo = et_idNo.getText().toString();
			String enducationNo = et_educationNo.getText().toString();
			String depositCardNo = et_depositCardNo.getText().toString();
			if (TextUtils.isEmpty(personName)) {
				Toast.show(self, resources.getString(R.string.entryPersonName));
				return;
			} else if (TextUtils.isEmpty(idNo)) {
				Toast.show(self, resources.getString(R.string.entryIdNo));
				return;
			} else if (!isIdNo(et_idNo.getText().toString())) {
				Toast.show(self, resources.getString(R.string.idNoFormat));
				return;
			} else {
				step3();
			}
			break;
		// 证件信息
		case R.id.layout_goBackMyselfInfo: // 证件信息上的上一步返回到个人信息
			step2();
			break;
		case R.id.btn_savePapersInfo: // 保存证件信息
			if(submite==1){
				saveAll(6, saveSuccess);
			}else{
				saveAll(3, saveSuccess);
			}
			break;
		case R.id.btn_papersInfoNext: // 证件信息的下一步进入到个人经历
			 if (positive.equals("")) {
				Toast.show(self, "请添加身份证正面照");
				return;
			} else if (reverse.equals("")) {
				Toast.show(self, "请添加身份证反面照");
				return;
			}else {
				step4();
			}
			break;
		// 个人经历
		case R.id.layout_goBackPapersInfo: // 个人经历中的上一步返回到证件信息
			step3();
			break;
		case R.id.btn_saveHistory: // 保存经历信息
			if(submite==1){
				saveAll(6, saveSuccess);
			}else{
				saveAll(4, saveSuccess);
			}
			break;
		case R.id.btn_historyNext: // 个人经历的下一步进入到家庭信息
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
		case R.id.btn_addEducation:// 添加学习经历
			final EntryAddInfoManager entryAddEducationInfo;
			entryAddEducationInfo = new EntryAddInfoManager(self, self);
			entryAddEducationInfo.addEducationInfo(educationAdapter);
			break;
		case R.id.btn_addWorkExperience:// 添加工作经验
			final EntryAddInfoManager entryAddWorkExperienceInfo;
			entryAddWorkExperienceInfo = new EntryAddInfoManager(self, self);
			entryAddWorkExperienceInfo
					.addWorkExperienceInfo(workExperienceAdapter);
			break;
		// 家庭信息
		case R.id.layout_goBackHistory: // 家庭信息中的上一步返回到个人经历
			step4();
			break;
		// 添加亲属信息
		case R.id.btn_addFamilyInfo:
			final EntryAddInfoManager entryAddFamilyInfo;
			entryAddFamilyInfo = new EntryAddInfoManager(self, self);
			entryAddFamilyInfo.addFamilyInfo(adapterFamily);
			break;
		case R.id.btn_saveFamilyInfo: // 保存证件信息
			if(submite==1){
				saveAll(6, saveSuccess);
			}else{
				saveAll(5, saveSuccess);
			}
			break;
		case R.id.btn_familyInfoNext: // 家庭信息的下一步进入到兴趣爱好
			step6();
			break;
		// 兴趣爱好
		case R.id.layout_goBackFamilyInfo: // 家庭信息中的上一步返回到个人经历
			step5();
			break;
		case R.id.btn_saveHobbies:
			if(submite==1){
				saveAll(6, saveSuccess);
			}else{
				saveAll(6, saveSuccess);
			}
			break;
		// 提交改变状态位
		case R.id.btn_sumbit:
			submited(1, upLoadSuccess);
			break;
		default:
			break;
		}
	}

	/**
	 * 初始化数据
	 */
	private void initData() {
		OperationBuilder builder = new OperationBuilder().append(
				new EntryManageOp(), null);
		OnOperationListener listener = new OnOperationListener() {
			@SuppressWarnings("unchecked")
			@Override
			public void onOperationFinished(List<Object> resList) {
				employInfo = (LinearLayout) findViewById(R.id.employInfo);
				myselfInfo = (LinearLayout) findViewById(R.id.myselfInfo);
				scroll_myselfInfo = (ScrollView) findViewById(R.id.scroll_myselfInfo);
				papersInfo = (LinearLayout) findViewById(R.id.papersInfo);
				history = (LinearLayout) findViewById(R.id.history);
				familyInfo = (LinearLayout) findViewById(R.id.layout_familyInfo);
				hobbies = (LinearLayout) findViewById(R.id.hobbies);
				// 应聘信息
				EntryEntity entity = (EntryEntity) resList.get(0);
				entryManageEntity = entity.getRetObj();
				submite=entryManageEntity.getSubmited();
				if (entryManageEntity != null) {
					if (entryManageEntity.getComDep() == null) {
						sp_comDep.setSelection(0, true);
					} else {
						ArrayAdapter<SpinnerData> comDep = (ArrayAdapter<SpinnerData>) sp_comDep
								.getAdapter();
						for (int i = 0; i < comDep.getCount(); i++) {
							if (entryManageEntity.getComDep().equals(
									comDep.getItem(i).getValue().toString())) {
								sp_comDep.setSelection(i, true);
							}
						}
					}
					if (entryManageEntity.getComPos() == null) {
						sp_comPos.setSelection(0, true);
					} else {
						ArrayAdapter<SpinnerData> comPos = (ArrayAdapter<SpinnerData>) sp_comPos
								.getAdapter();
						for (int i = 0; i < comPos.getCount(); i++) {
							if (entryManageEntity.getComPos().equals(
									comPos.getItem(i).getValue().toString())) {
								sp_comPos.setSelection(i, true);
							}
						}
					}

					SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
					et_comEntryDate.setText(date.format(new Date(Long
							.parseLong(entryManageEntity.getComEntryDate()
									.substring(6, 19)))));
					et_residence.setText(entryManageEntity.getResidence());
					et_mobile.setText(entryManageEntity.getMobile());
					et_urgentContact.setText(entryManageEntity
							.getUrgentContact());
					et_urgentMobile
							.setText(entryManageEntity.getUrgentMobile());
					et_urgentAddr.setText(entryManageEntity.getUrgentAddr());
//					et_regions.setText(entryManageEntity.getRegions());
					if (entryManageEntity.getRegions() != null) {
						ArrayAdapter<SpinnerData> regions = (ArrayAdapter<SpinnerData>) sp_regions
								.getAdapter();
						for (int i = 0; i < regions.getCount(); i++) {
							if (entryManageEntity.getRegions().equals(
									regions.getItem(i).getValue())) {
								sp_regions.setSelection(i, true);
							}
						}
					} else {
						sp_regions.setSelection(0, true);

					}
					// 个人信息
					et_personName.setText(entryManageEntity.getPersonName());
					et_englishName.setText(entryManageEntity.getEnglishName());
					if ((Integer.toString((entryManageEntity.getGender())))
							.equals(null)) {
					} else {
						if ((Integer.toString((entryManageEntity.getGender())))
								.equals("1")) {
							femaleButton.setChecked(true);
						} else {
							maleButton.setChecked(true);
						}
					}
					et_address.setText(entryManageEntity.getAddress());
					et_idNo.setText(entryManageEntity.getIdNo());
					et_educationNo.setText(entryManageEntity.getEducationNo());
					et_major.setText(entryManageEntity.getMajor());
					et_depositCardNo.setText(entryManageEntity
							.getDepositCardNo());
					et_accumFund.setText(entryManageEntity.getAccumFund());
					et_socialSecurityNo.setText(entryManageEntity
							.getSocialSecurityNo());
					if (entryManageEntity.getMaritalStatus() != null) {
						ArrayAdapter<SpinnerData> maritalStatus = (ArrayAdapter<SpinnerData>) sp_maritalStatus
								.getAdapter();
						for (int i = 0; i < maritalStatus.getCount(); i++) {
							if (entryManageEntity.getMaritalStatus().equals(
									maritalStatus.getItem(i).getValue())) {
								sp_maritalStatus.setSelection(i, true);
							}
						}
					} else {
						sp_maritalStatus.setSelection(0, true);

					}
					if (entryManageEntity.getPoliticalStatus() != null) {
						ArrayAdapter<SpinnerData> politicalStatus = (ArrayAdapter<SpinnerData>) sp_politicalStatus
								.getAdapter();
						for (int i = 0; i < politicalStatus.getCount(); i++) {
							if (entryManageEntity.getPoliticalStatus().equals(
									politicalStatus.getItem(i).getValue())) {
								sp_politicalStatus.setSelection(i, true);
							}
						}
					} else {
						sp_politicalStatus.setSelection(0, true);

					}
					if (entryManageEntity.getHealthCondition() != null) {
						ArrayAdapter<SpinnerData> healthCondition = (ArrayAdapter<SpinnerData>) sp_healthCondition
								.getAdapter();
						for (int i = 0; i < healthCondition.getCount(); i++) {
							if (entryManageEntity.getHealthCondition().equals(
									healthCondition.getItem(i).getValue())) {
								sp_healthCondition.setSelection(i, true);
							}
						}
					} else {
						sp_healthCondition.setSelection(0, true);
					}
					if (entryManageEntity.getCulturalDegree() != null) {
						ArrayAdapter<SpinnerData> culturalDegree = (ArrayAdapter<SpinnerData>) sp_culturalDegree
								.getAdapter();
						for (int i = 0; i < culturalDegree.getCount(); i++) {
							if (entryManageEntity.getCulturalDegree().equals(
									culturalDegree.getItem(i).getValue())) {
								sp_culturalDegree.setSelection(i, true);
							}
						}
					} else {
						sp_culturalDegree.setSelection(0, true);

					}
					if (entryManageEntity.getNation() != null) {
						ArrayAdapter<SpinnerData> nation = (ArrayAdapter<SpinnerData>) sp_nation
								.getAdapter();
						for (int i = 0; i < nation.getCount(); i++) {
							if (entryManageEntity.getNation().equals(
									nation.getItem(i).getValue())) {
								sp_nation.setSelection(i, true);
							}
						}
					} else {
						sp_nation.setSelection(0, true);
					}
					// 开户银行
					if (entryManageEntity.getDepositBank() != null) {
						ArrayAdapter<SpinnerData> depositBank = (ArrayAdapter<SpinnerData>) sp_depositBank
								.getAdapter();
						for (int i = 0; i < depositBank.getCount(); i++) {
							if (entryManageEntity.getDepositBank().equals(
									depositBank.getItem(i).getValue())) {
								sp_depositBank.setSelection(i, true);
							}
						}
					} else {
						sp_depositBank.setSelection(0, true);
						// list_depositBank.remove(0);
					}

					// 证件信息
					String materials = entryManageEntity.getMaterials();
					if (materials != null) {
						try {
							JSONObject jsonObjectMaterials = new JSONObject(
									materials);
							//身份证
							JSONObject jsonObjectIDImage = new JSONObject(
									jsonObjectMaterials.getString("IDImage"));
							String positive1 = "";
							String reverse1 = "";
							if (jsonObjectMaterials.getString("IDImage") != null) {
								positive1 = jsonObjectIDImage
										.getString("Positive");
								reverse1 = jsonObjectIDImage
										.getString("Reverse");
							}
							//银行卡
							JSONObject jsonObjectBankCardImage = new JSONObject(
									jsonObjectMaterials.getString("BankCard"));
							String cardpositive1 = "";
							String cardreverse1 = "";
							if (jsonObjectMaterials.getString("BankCard") != null) {
								cardpositive1 = jsonObjectBankCardImage
										.getString("BankCardPositive");
								cardreverse1 = jsonObjectBankCardImage
										.getString("BankCardReverse");
							}
							String[] urls = new String[] {
									jsonObjectMaterials
											.getString("Certificates"),
									jsonObjectMaterials
											.getString("LearningCertificate"),
									positive1,
									reverse1,
									jsonObjectMaterials.getString("Retirement"),
									jsonObjectMaterials.getString("Physical") ,
									cardpositive1,
									cardreverse1
									};
							if (urls[0].equals("")) {
							} else {
								ImageLoader.getInstance().displayImage(
										urls[0].trim(), imgViewPhoto);
							}
							if (urls[1].equals("")) {
							} else {
								// new Thread(new LoadImageRunnable(mHandler,
								// THREAD_Academic, urls[1])).start();
								ImageLoader.getInstance().displayImage(
										urls[1].trim(), imgViewAcademic);
								// ImageLoader.getInstance().displayImage("http://test.joy121.com:999/api/OutFile/GetImage?imageEncryptedName=9110caedb154b75587b185d2487abbcb.jpg",
								// imgViewAcademic);
							}
							if (urls[2].equals("")) {
							} else {
								ImageLoader.getInstance().displayImage(
										urls[2].trim(), imgViewIdPhoto1);
								// ImageLoader.getInstance().displayImage("http://test.joy121.com:999/api/OutFile/GetImage?imageEncryptedName=9110caedb154b75587b185d2487abbcb.jpg",
								// imgViewIdPhoto1);
							}
							if (urls[3].equals("")) {
							} else {
								ImageLoader.getInstance().displayImage(
										urls[3].trim(), imgViewIdPhoto2);
								// ImageLoader.getInstance().displayImage("http://test.joy121.com:999/api/OutFile/GetImage?imageEncryptedName=9110caedb154b75587b185d2487abbcb.jpg",
								// imgViewIdPhoto2);
							}
							if (urls[4].equals("")) {
							} else {
								ImageLoader.getInstance().displayImage(
										urls[4].trim(), imgViewRepairOrder);
								// ImageLoader.getInstance().displayImage("http://test.joy121.com:999/api/OutFile/GetImage?imageEncryptedName=9110caedb154b75587b185d2487abbcb.jpg",
								// imgViewRepairOrder);
							}
							if (urls[5].equals("")) {
							} else {
								ImageLoader.getInstance()
										.displayImage(urls[5].trim(),
												imgViewCheckupReporting);
								// ImageLoader.getInstance().displayImage("http://test.joy121.com:999/api/OutFile/GetImage?imageEncryptedName=9110caedb154b75587b185d2487abbcb.jpg",
								// imgViewCheckupReporting);
							}
							if (urls[6].equals("")) {
							} else {
								ImageLoader.getInstance().displayImage(
										urls[6].trim(), imgViewBankCardPositive);
							}
							if (urls[7].equals("")) {
							} else {
								ImageLoader.getInstance().displayImage(
										urls[7].trim(), imgViewBankCardReverse);
							}
							certificates = urls[0];
							learningCertificate = urls[1];
							positive = urls[2];
							reverse = urls[3];
							retirement = urls[4];
							physical = urls[5];
							cardpositive=urls[6];
							cardreverse=urls[7];
							Log.e("retirement",urls[4]);
							Log.e("cardpositive",urls[6]);
							Log.e("cardreverse",urls[7]);
							
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					// 个人经历
					String experienceInfo = entryManageEntity.getExperiences();
					if (experienceInfo != null) {
						try {
							JSONObject jsonObject2 = new JSONObject(
									experienceInfo);
							JSONArray jsonArrayLearning = jsonObject2
									.getJSONArray("Learning");
							JSONArray jsonArrayJob = jsonObject2
									.getJSONArray("Job");
							JsonParser parser = new JsonParser();
							JsonArray jsonArray1;
							JsonElement jsonElement;
							Iterator it;
							if (jsonArrayLearning.length() != 0) {
								EntryManageEducationInfoEntity tempEducation;
								jsonElement = parser.parse(jsonArrayLearning
										.toString());
								if (jsonElement.isJsonArray()) {
									jsonArray1 = jsonElement.getAsJsonArray();
									it = jsonArray1.iterator();
									while (it.hasNext()) {
										JsonElement e = (JsonElement) it.next();
										// JsonElement转换为JavaBean对象
										tempEducation = new Gson()
												.fromJson(
														e,
														EntryManageEducationInfoEntity.class);
										educationAdapter.addItem(tempEducation);
										educationAdapter.notifyDataSetChanged();
									}
								}
							}
							if (jsonArrayJob.length() != 0) {
								EntryManageWorkExperienceInfoEntity tempWorkExperience;
								jsonElement = parser.parse(jsonArrayJob
										.toString());
								if (jsonElement.isJsonArray()) {
									jsonArray1 = jsonElement.getAsJsonArray();
									it = jsonArray1.iterator();
									while (it.hasNext()) {
										JsonElement e = (JsonElement) it.next();
										// JsonElement转换为JavaBean对象
										tempWorkExperience = new Gson()
												.fromJson(
														e,
														EntryManageWorkExperienceInfoEntity.class);
										workExperienceAdapter
												.addItem(tempWorkExperience);
										workExperienceAdapter
												.notifyDataSetChanged();

									}
								}
							}
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					// 家庭信息
					String familyDetailsInfo = entryManageEntity.getFamily();
					/*
					 * 数据静态添加到tempFaimly中 EntryManageFamilyInfoEntity
					 * tempFamily=new EntryManageFamilyInfoEntity();
					 * tempFamily.setName("1"); tempFamily.setAddress("2");
					 * tempFamily.setBirthday("3");
					 * tempFamily.setRelationShip("4");
					 * adapterFamily.addItem(tempFamily);
					 * adapterFamily.notifyDataSetChanged();
					 */
					if (familyDetailsInfo != null) {
						try {
							JSONObject relativesJsonObject = new JSONObject(
									familyDetailsInfo);
							JSONArray relativesJsonArray = relativesJsonObject
									.getJSONArray("Relatives");
							JsonParser parser = new JsonParser();
							JsonArray jsonArray1;
							JsonElement jsonElement;
							Iterator it;
							if (relativesJsonArray.length() != 0) {
								EntryManageFamilyInfoEntity tempFamily;
								jsonElement = parser.parse(relativesJsonArray
										.toString());
								if (jsonElement.isJsonArray()) {
									jsonArray1 = jsonElement.getAsJsonArray();
									it = jsonArray1.iterator();
									while (it.hasNext()) {
										JsonElement e = (JsonElement) it.next();
										// JsonElement转换为JavaBean对象
										tempFamily = new Gson()
												.fromJson(
														e,
														EntryManageFamilyInfoEntity.class);
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
					 * if(familyDetailsInfo!=null){ JsonParser parser = new
					 * JsonParser(); JsonArray jsonArray1;
					 * EntryManageFamilyInfoEntity tempFamily; JsonElement
					 * jsonElement = parser.parse(familyDetailsInfo);
					 * if(jsonElement.isJsonArray()){ jsonArray1 =
					 * jsonElement.getAsJsonArray(); Iterator it =
					 * jsonArray1.iterator(); while(it.hasNext()){ JsonElement e
					 * = (JsonElement)it.next();
					 * Log.e("----------------------------",e.toString());
					 * //JsonElement转换为JavaBean对象 tempFamily = new
					 * Gson().fromJson(e, EntryManageFamilyInfoEntity.class);
					 * adapterFamily.addItem(tempFamily);
					 * adapterFamily.notifyDataSetChanged(); } }
					 * 
					 * }
					 */
					// 兴趣爱好
					String getHobbies = entryManageEntity.getInteresting();
					String hobbies1 = "";
					if (getHobbies != null) {
						String[] arrayHobbies = getHobbies.split(",");
						for (int m = 0; m < arrayHobbies.length; m++) {
							if (m == 0) {
								arrayHobbies[m] = arrayHobbies[m].replace(
										"[\"", "");
								hobbies1 = arrayHobbies[m].replace("\"", "");
							} else if (m == (arrayHobbies.length - 1)) {
								arrayHobbies[m] = arrayHobbies[m].replace("\"",
										"");
								hobbies1 = arrayHobbies[m].replace("\"", "");
								hobbies1 = hobbies1.replace("]", "");
							} else {
								arrayHobbies[m] = arrayHobbies[m].replace("\"",
										"");
								hobbies1 = arrayHobbies[m].replace("\"", "");
							}
							if (hobbies1.equals("打篮球"))
								basketball.setChecked(true);
							if (hobbies1.equals("踢足球"))
								football.setChecked(true);
							if (hobbies1.equals("打羽毛球"))
								badminton.setChecked(true);
							if (hobbies1.equals("打乒乓球"))
								table_tennis.setChecked(true);
							if (hobbies1.equals("爬山"))
								mountains.setChecked(true);
							if (hobbies1.equals("唱歌"))
								sing.setChecked(true);
							if (hobbies1.equals("看书"))
								book.setChecked(true);
							if (hobbies1.equals("烹饪"))
								cooking.setChecked(true);
							if (hobbies1.equals("画画"))
								drawing.setChecked(true);
							if (hobbies1.equals("舞蹈"))
								dance.setChecked(true);
							if (hobbies1.equals("旅游"))
								travel.setChecked(true);
							if (hobbies1.equals("摄影"))
								photography.setChecked(true);
						}
					}
					if (entryManageEntity.getCurrentStep() == 1) {
						step1();
					} else if (entryManageEntity.getCurrentStep() == 2) {
						step2();
					} else if (entryManageEntity.getCurrentStep() == 3) {
						step3();
					} else if (entryManageEntity.getCurrentStep() == 4) {
						step4();
					} else if (entryManageEntity.getCurrentStep() == 5) {
						step5();
					} else if (entryManageEntity.getCurrentStep() == 6) {
						step6();
					} else {
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
	 * private Handler mHandler = new Handler() {
	 * 
	 * // 利用handleMessage更新UI
	 * 
	 * @SuppressWarnings("deprecation") public void handleMessage(Message msg) {
	 *                                  switch (msg.what) { case
	 *                                  EntryManagementActiviy.THREAD_Photo:
	 *                                  BitmapFactory.Options options = new
	 *                                  BitmapFactory.Options(); // options
	 *                                  设为true时，构造出的bitmap没有图片，只有一些长宽等配置信息，但比较快，
	 *                                  设为false时，才有图片 options.inJustDecodeBounds
	 *                                  = true;
	 * 
	 *                                  int scale = (int) (options.outWidth /
	 *                                  (float) 100); if (scale <= 0) scale = 2;
	 *                                  options.inSampleSize = scale;
	 *                                  options.inJustDecodeBounds = false;
	 *                                  Object m=msg.obj;
	 *                                  Log.e("img============================="
	 *                                  , m.toString()); // bitmap =
	 *                                  BitmapFactory.decodeFile(retirement,
	 *                                  options); // bitmap =
	 *                                  BitmapFactory.decodeFile( msg.obj,
	 *                                  options);
	 *                                  imgViewPhoto.setImageBitmap((Bitmap
	 *                                  )msg.obj);
	 *                                  imgViewPhoto.setScaleType(ImageView
	 *                                  .ScaleType.CENTER_CROP); break; case
	 *                                  EntryManagementActiviy.THREAD_Academic:
	 *                                  imgViewAcademic.setImageBitmap((Bitmap)
	 *                                  msg.obj);
	 *                                  imgViewAcademic.setScaleType(ImageView
	 *                                  .ScaleType.CENTER_CROP); break; case
	 *                                  EntryManagementActiviy.THREAD_IdPhoto1:
	 *                                  imgViewIdPhoto1.setImageBitmap((Bitmap)
	 *                                  msg.obj);
	 *                                  imgViewIdPhoto1.setScaleType(ImageView
	 *                                  .ScaleType.CENTER_CROP); break; case
	 *                                  EntryManagementActiviy.THREAD_IdPhoto2:
	 *                                  imgViewIdPhoto2.setImageBitmap((Bitmap)
	 *                                  msg.obj);
	 *                                  imgViewIdPhoto2.setScaleType(ImageView
	 *                                  .ScaleType.CENTER_CROP); break; case
	 *                                  EntryManagementActiviy
	 *                                  .THREAD_RepairOrder:
	 *                                  imgViewRepairOrder.setImageBitmap
	 *                                  ((Bitmap) msg.obj);
	 *                                  imgViewRepairOrder.setScaleType
	 *                                  (ImageView.ScaleType.CENTER_CROP);
	 *                                  break; case
	 *                                  EntryManagementActiviy.THREAD_CheckupReporting
	 *                                  :
	 *                                  imgViewCheckupReporting.setImageBitmap(
	 *                                  (Bitmap) msg.obj);
	 *                                  imgViewCheckupReporting
	 *                                  .setScaleType(ImageView
	 *                                  .ScaleType.CENTER_CROP); break; //
	 *                                  如有异常会有提示 default: break; }
	 * 
	 *                                  } };
	 */
	/**
	 * 绑定部门和职位
	 * 
	 * @param type
	 * @param parentId
	 */
	private void bindDepartmentOrPos(final String type, final int parentId) {
		EntryDepartmentDetailEntity entry = new EntryDepartmentDetailEntity();
		entry.setSysKey(type);
		entry.setParentId(parentId);
		OperationBuilder builder = new OperationBuilder().append(
				new EntryDepartmentOp(), entry);
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
				EntryDepartmentEntity entity = (EntryDepartmentEntity) resList
						.get(0);
				List<EntryDepartmentDetailEntity> departmentList = entity
						.getRetObj();
				if (type == "CostCenterno" && parentId == -1) {
					// 数据
					list_comDep = new ArrayList<SpinnerData>();
					for (int i = 0; i < departmentList.size(); i++) {
						SpinnerData comDep = new SpinnerData(departmentList
								.get(i).getSysValue(), departmentList.get(i)
								.getSysKeyName());
						list_comDep.add(comDep);
					}
					// 适配器
					comDep_adapter = new ArrayAdapter<SpinnerData>(
							EntryManagementActiviy.this,
							android.R.layout.simple_spinner_item, list_comDep);
					// 设置样式
					comDep_adapter
							.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
					// 加载适配器
					sp_comDep.setAdapter(comDep_adapter);
				} else if (type == "Compos" && parentId == -1) {
					// 数据
					list_comPos = new ArrayList<SpinnerData>();
					for (int i = 0; i < departmentList.size(); i++) {
						SpinnerData comPos = new SpinnerData(departmentList
								.get(i).getSysValue(), departmentList.get(i)
								.getSysKeyName());
						list_comPos.add(comPos);
					}

					// 适配器
					comPos_adapter = new ArrayAdapter<SpinnerData>(
							EntryManagementActiviy.this,
							android.R.layout.simple_spinner_item, list_comPos);
					// 设置样式
					comPos_adapter
							.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
					// 加载适配器
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
	 * 根据syskey绑定syskeyname
	 * 
	 */
	private void bindSysData() {
		EntryDepartmentDetailEntity entry = new EntryDepartmentDetailEntity();
		OperationBuilder builder = new OperationBuilder().append(
				new ComGroupSysData(), entry);
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
				EntryDepartmentEntity entity = (EntryDepartmentEntity) resList
						.get(0);
				List<EntryDepartmentDetailEntity> allList = entity.getRetObj();
				// SpinnerData a = new SpinnerData("", "");
				list_maritalStatus = new ArrayList<SpinnerData>();

				list_politicalStatus = new ArrayList<SpinnerData>();
				list_healthCondition = new ArrayList<SpinnerData>();
				list_culturalDegree = new ArrayList<SpinnerData>();
				list_nation = new ArrayList<SpinnerData>();
				list_depositBank = new ArrayList<SpinnerData>();
				list_regions=new ArrayList<SpinnerData>();
				// list_maritalStatus.add(a);
				// list_politicalStatus.add(a);
				// list_culturalDegree.add(a);
				// list_depositBank.add(a);

				for (int i = 0; i < allList.size(); i++) {
					if (allList.get(i).getSysKey().equals("maritalStatus")) {
						SpinnerData maritalStatus = new SpinnerData(allList
								.get(i).getSysValue(), allList.get(i)
								.getSysKeyName());
						list_maritalStatus.add(maritalStatus);
					} else if (allList.get(i).getSysKey()
							.equals("politicalStatus")) {
						SpinnerData politicalStatus = new SpinnerData(allList
								.get(i).getSysValue(), allList.get(i)
								.getSysKeyName());
						list_politicalStatus.add(politicalStatus);
					} else if (allList.get(i).getSysKey()
							.equals("healthCondition")) {
						SpinnerData healthCondition = new SpinnerData(allList
								.get(i).getSysValue(), allList.get(i)
								.getSysKeyName());
						list_healthCondition.add(healthCondition);
					} else if (allList.get(i).getSysKey()
							.equals("culturalDegree")) {
						SpinnerData culturalDegree = new SpinnerData(allList
								.get(i).getSysValue(), allList.get(i)
								.getSysKeyName());
						list_culturalDegree.add(culturalDegree);
					} else if (allList.get(i).getSysKey().equals("nation")) {
						SpinnerData nation = new SpinnerData(allList.get(i)
								.getSysValue(), allList.get(i).getSysKeyName());
						list_nation.add(nation);
					} else if (allList.get(i).getSysKey().equals("depositBank")) {
						SpinnerData depositBank = new SpinnerData(allList
								.get(i).getSysValue(), allList.get(i)
								.getSysKeyName());
						list_depositBank.add(depositBank);
					}else if(allList.get(i).getSysKey().equals("province")){
						SpinnerData regions = new SpinnerData(allList
								.get(i).getSysValue(), allList.get(i)
								.getSysKeyName());
						list_regions.add(regions);
					}
				}

				// 适配器
				maritalStatus_adapter = new ArrayAdapter<SpinnerData>(
						EntryManagementActiviy.this,
						android.R.layout.simple_spinner_item,
						list_maritalStatus);
				politicalStatus_adapter = new ArrayAdapter<SpinnerData>(
						EntryManagementActiviy.this,
						android.R.layout.simple_spinner_item,
						list_politicalStatus);
				healthCondition_adapter = new ArrayAdapter<SpinnerData>(
						EntryManagementActiviy.this,
						android.R.layout.simple_spinner_item,
						list_healthCondition);
				culturalDegree_adapter = new ArrayAdapter<SpinnerData>(
						EntryManagementActiviy.this,
						android.R.layout.simple_spinner_item,
						list_culturalDegree);
				nation_adapter = new ArrayAdapter<SpinnerData>(
						EntryManagementActiviy.this,
						android.R.layout.simple_spinner_item, list_nation);
				depositBank_adapter = new ArrayAdapter<SpinnerData>(
						EntryManagementActiviy.this,
						android.R.layout.simple_spinner_item, list_depositBank);
				regions_adapter = new ArrayAdapter<SpinnerData>(
						EntryManagementActiviy.this,
						android.R.layout.simple_spinner_item, list_regions);
				// 设置样式
				maritalStatus_adapter
						.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
				politicalStatus_adapter
						.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
				healthCondition_adapter
						.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
				culturalDegree_adapter
						.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
				nation_adapter
						.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
				depositBank_adapter
						.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
				regions_adapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
				// 加载适配器
				sp_maritalStatus.setAdapter(maritalStatus_adapter);
				sp_politicalStatus.setAdapter(politicalStatus_adapter);
				sp_healthCondition.setAdapter(healthCondition_adapter);
				sp_culturalDegree.setAdapter(culturalDegree_adapter);
				sp_nation.setAdapter(nation_adapter);
				sp_depositBank.setAdapter(depositBank_adapter);
				sp_regions.setAdapter(regions_adapter);
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
	 * @param submited
	 *            最后一步是否提交状态位
	 * @param currentStep当前第几步
	 * @param retMessage
	 *            返回的是否保存成功还是上传成功
	 */
	private void saveAll(final int currentStep, final String retMessage) {
		OperationBuilder builder = new OperationBuilder().append(
				new EntryManageOp(), null);
		OnOperationListener listener = new OnOperationListener() {
			@Override
			public void onOperationFinished(List<Object> resList) {
				EntryEntity entity = (EntryEntity) resList.get(0);
				entryManageEntity = entity.getRetObj();
				EntryManageEntity entity1 = new EntryManageEntity();
				EntryManageImageEntity image = new EntryManageImageEntity();
				EntryManageExperiencesListEntity experience = new EntryManageExperiencesListEntity();
				EntryManageFamilyListEntity family = new EntryManageFamilyListEntity();
				entity1.setLoginName(SharedPreferencesUtils
						.getLoginName(JoyApplication.getSelf()));
				// 应聘信息
				entity1.setComDep(((SpinnerData) sp_comDep.getSelectedItem())
						.getValue());
				entity1.setComPos(((SpinnerData) sp_comPos.getSelectedItem())
						.getValue());
				entity1.setComEntryDate(et_comEntryDate.getText().toString());
				entity1.setResidence(et_residence.getText().toString());
				if (TextUtils.isEmpty(et_mobile.getText().toString())) {
				} else {
					if (!isMobile(et_mobile.getText().toString())) {
						Toast.show(self,
								resources.getString(R.string.mobileFormat));
						return;
					} else {
						entity1.setMobile(et_mobile.getText().toString());
					}
				}
				if (TextUtils.isEmpty(et_urgentMobile.getText().toString())) {
				} else {
					if (!isMobile(et_urgentMobile.getText().toString())) {
						Toast.show(self,
								resources.getString(R.string.mobileFormat));
						return;
					} else {
						entity1.setUrgentMobile(et_urgentMobile.getText()
								.toString());
					}
				}
				entity1.setUrgentContact(et_urgentContact.getText().toString());
				entity1.setUrgentAddr(et_urgentAddr.getText().toString());
//				entity1.setRegions(et_regions.getText().toString());
				entity1.setRegions(((SpinnerData) sp_regions.getSelectedItem())
						.getValue());
				// 个人信息
				entity1.setPersonName(et_personName.getText().toString());
				entity1.setEnglishName(et_englishName.getText().toString());
				if (femaleButton.isChecked()) {
					entity1.setGender(1);
				} else {
					entity1.setGender(0);
				}
				entity1.setAddress(et_address.getText().toString());
				if (TextUtils.isEmpty(et_idNo.getText().toString())) {
				} else {
					if (!isIdNo(et_idNo.getText().toString())) {
						Toast.show(self,
								resources.getString(R.string.idNoFormat));
						return;
					} else {
						entity1.setIdNo(et_idNo.getText().toString());
					}
				}
				entity1.setEducationNo(et_educationNo.getText().toString());
				entity1.setNation(((SpinnerData) sp_nation.getSelectedItem())
						.getValue());
				entity1.setMaritalStatus(((SpinnerData) sp_maritalStatus
						.getSelectedItem()).getValue());
				entity1.setPoliticalStatus(((SpinnerData) sp_politicalStatus
						.getSelectedItem()).getValue());
				entity1.setHealthCondition(((SpinnerData) sp_healthCondition
						.getSelectedItem()).getValue());
				entity1.setCulturalDegree(((SpinnerData) sp_culturalDegree
						.getSelectedItem()).getValue());
				entity1.setAccumFund(et_accumFund.getText().toString());
				entity1.setDepositBank(((SpinnerData) sp_depositBank
						.getSelectedItem()).getValue());
				entity1.setDepositCardNo(et_depositCardNo.getText().toString());
				entity1.setMajor(et_major.getText().toString());
				entity1.setSocialSecurityNo(et_socialSecurityNo.getText()
						.toString());

				// certificates,video,learningCertificate,
				// positive,reverse,retirement,physical;
				// 个人证件
				// 避免解析的时候解析掉等于号
				GsonBuilder gb = new GsonBuilder();
				gb.disableHtmlEscaping();
				EntryManageIDImageEntity idImage = new EntryManageIDImageEntity();
				image.setCertificates(certificates);
				image.setLearningCertificate(learningCertificate);
				idImage.setPositive(positive);
				idImage.setReverse(reverse);
				image.setIDImage(gb.create().toJson(idImage));
				
				EntryManageBankCardImageEntity bankCardImage = new EntryManageBankCardImageEntity();
				bankCardImage.setBankCardPositive(cardpositive);
				bankCardImage.setBankCardReverse(cardreverse);
				image.setBankCard(gb.create().toJson(bankCardImage));
				
				image.setRetirement(retirement);
				image.setPhysical(physical);
				String a=gb.create().toJson(image);
				a=a.replace("\\","");
				a=a.replace("\"{","{");
				a=a.replace("}\"","}");
				entity1.setMaterials(a);
				// 个人经历
				experience.setLearning(educationAdapter.getData());// 学习经历的数据
				experience.setJob(workExperienceAdapter.getData());// 工作经历的数据
				entity1.setExperiences(new Gson().toJson(experience));
				// 家庭信息
				family.setRelatives(adapterFamily.getData());
				entity1.setFamily(new Gson().toJson(family));
				// 兴趣爱好
				// 判断 是否被选中得到其被选中的值，拼成数组放到后台
				checkBoxHobbiesList = new ArrayList<String>();
				if (basketball.isChecked())
					checkBoxHobbiesList.add(basketball.getText().toString());
				if (football.isChecked())
					checkBoxHobbiesList.add(football.getText().toString());
				if (badminton.isChecked())
					checkBoxHobbiesList.add(badminton.getText().toString());
				if (table_tennis.isChecked())
					checkBoxHobbiesList.add(table_tennis.getText().toString());
				if (mountains.isChecked())
					checkBoxHobbiesList.add(mountains.getText().toString());
				if (sing.isChecked())
					checkBoxHobbiesList.add(sing.getText().toString());
				if (book.isChecked())
					checkBoxHobbiesList.add(book.getText().toString());
				if (cooking.isChecked())
					checkBoxHobbiesList.add(cooking.getText().toString());
				if (drawing.isChecked())
					checkBoxHobbiesList.add(drawing.getText().toString());
				if (dance.isChecked())
					checkBoxHobbiesList.add(dance.getText().toString());
				if (travel.isChecked())
					checkBoxHobbiesList.add(travel.getText().toString());
				if (photography.isChecked())
					checkBoxHobbiesList.add(photography.getText().toString());
				entity1.setInteresting(new Gson().toJson(checkBoxHobbiesList));
				entity1.setCurrentStep(currentStep);
				if (entryManageEntity.getSubmited() == 1) {
					entity1.setSubmited(1);
				} else {
					entity1.setSubmited(0);
				}
				OperationBuilder builder1 = new OperationBuilder().append(
						new EntrySaveOp(), entity1);
				OnOperationListener listener1 = new OnOperationListener() {
					@Override
					public void onOperationFinished(List<Object> resList) {
						if (self.isFinishing()) {
							return;
						} else if (resList == null) {
							Toast.show(self, "连接超时");
							return;
						} else {
							Toast.show(self, retMessage);
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
		JsonCommon task = new JsonCommon(self, builder, listener, null);
		task.execute();
	}

	private void submited(int currentStep, final String retMessage) {
		EntryManageEntity entity = new EntryManageEntity();
		EntryManageExperiencesListEntity experience = new EntryManageExperiencesListEntity();
		EntryManageImageEntity image = new EntryManageImageEntity();
		EntryManageFamilyListEntity family = new EntryManageFamilyListEntity();
		entity.setLoginName(SharedPreferencesUtils.getLoginName(JoyApplication
				.getSelf()));
		// 应聘信息
		entity.setComDep(((SpinnerData) sp_comDep.getSelectedItem()).getValue());
		entity.setComPos(((SpinnerData) sp_comPos.getSelectedItem()).getValue());
		entity.setComEntryDate(et_comEntryDate.getText().toString());
		entity.setResidence(et_residence.getText().toString());
		if (TextUtils.isEmpty(et_mobile.getText().toString())) {
		} else {
			if (!isMobile(et_mobile.getText().toString())) {
				Toast.show(self, resources.getString(R.string.mobileFormat));
				return;
			} else {
				entity.setMobile(et_mobile.getText().toString());
			}
		}
		if (TextUtils.isEmpty(et_urgentMobile.getText().toString())) {
		} else {
			if (!isMobile(et_urgentMobile.getText().toString())) {
				Toast.show(self, resources.getString(R.string.mobileFormat));
				return;
			} else {
				entity.setUrgentMobile(et_urgentMobile.getText().toString());
			}
		}
		entity.setUrgentContact(et_urgentContact.getText().toString());
		entity.setUrgentAddr(et_urgentAddr.getText().toString());
		entity.setRegions(((SpinnerData) sp_regions.getSelectedItem()).getValue());
		// 个人信息
		entity.setPersonName(et_personName.getText().toString());
		entity.setEnglishName(et_englishName.getText().toString());
		if (femaleButton.isChecked()) {
			entity.setGender(1);
		} else {
			entity.setGender(0);
		}
		entity.setAddress(et_address.getText().toString());
		if (TextUtils.isEmpty(et_idNo.getText().toString())) {
		} else {
			if (!isIdNo(et_idNo.getText().toString())) {
				Toast.show(self, resources.getString(R.string.idNoFormat));
				return;
			} else {
				entity.setIdNo(et_idNo.getText().toString());
			}
		}
		entity.setEducationNo(et_educationNo.getText().toString());
		entity.setNation(((SpinnerData) sp_nation.getSelectedItem()).getValue());
		entity.setMaritalStatus(((SpinnerData) sp_maritalStatus
				.getSelectedItem()).getValue());
		entity.setPoliticalStatus(((SpinnerData) sp_politicalStatus
				.getSelectedItem()).getValue());
		entity.setHealthCondition(((SpinnerData) sp_healthCondition
				.getSelectedItem()).getValue());
		entity.setCulturalDegree(((SpinnerData) sp_culturalDegree
				.getSelectedItem()).getValue());
		entity.setAccumFund(et_accumFund.getText().toString());
		entity.setDepositBank(((SpinnerData) sp_depositBank.getSelectedItem())
				.getValue());
		entity.setDepositCardNo(et_depositCardNo.getText().toString());
		entity.setMajor(et_major.getText().toString());
		entity.setSocialSecurityNo(et_socialSecurityNo.getText().toString());
		// 个人证件
		// 避免解析的时候解析掉等于号
		GsonBuilder gb = new GsonBuilder();
		gb.disableHtmlEscaping();
		EntryManageIDImageEntity idImage = new EntryManageIDImageEntity();
		image.setCertificates(certificates);
		image.setLearningCertificate(learningCertificate);
		idImage.setPositive(positive);
		idImage.setReverse(reverse);
		image.setIDImage(gb.create().toJson(idImage));
		
		EntryManageBankCardImageEntity bankCardImage = new EntryManageBankCardImageEntity();
		bankCardImage.setBankCardPositive(cardpositive);
		bankCardImage.setBankCardReverse(cardreverse);
		image.setBankCard(gb.create().toJson(bankCardImage));
		
		image.setRetirement(retirement);
		image.setPhysical(physical);
		String a=gb.create().toJson(image);
		a=a.replace("\\","");
		a=a.replace("\"{","{");
		a=a.replace("}\"","}");
		entity.setMaterials(a);
		// 个人经历
		experience.setLearning(educationAdapter.getData());// 学习经历的数据
		experience.setJob(workExperienceAdapter.getData());// 工作经历的数据
		entity.setExperiences(new Gson().toJson(experience));
		// 家庭信息
		family.setRelatives(adapterFamily.getData());
		entity.setFamily(new Gson().toJson(family));
		// 兴趣爱好
		// 判断 是否被选中得到其被选中的值，拼成数组放到后台
		checkBoxHobbiesList = new ArrayList<String>();
		if (basketball.isChecked())
			checkBoxHobbiesList.add(basketball.getText().toString());
		if (football.isChecked())
			checkBoxHobbiesList.add(football.getText().toString());
		if (badminton.isChecked())
			checkBoxHobbiesList.add(badminton.getText().toString());
		if (table_tennis.isChecked())
			checkBoxHobbiesList.add(table_tennis.getText().toString());
		if (mountains.isChecked())
			checkBoxHobbiesList.add(mountains.getText().toString());
		if (sing.isChecked())
			checkBoxHobbiesList.add(sing.getText().toString());
		if (book.isChecked())
			checkBoxHobbiesList.add(book.getText().toString());
		if (cooking.isChecked())
			checkBoxHobbiesList.add(cooking.getText().toString());
		if (drawing.isChecked())
			checkBoxHobbiesList.add(drawing.getText().toString());
		if (dance.isChecked())
			checkBoxHobbiesList.add(dance.getText().toString());
		if (travel.isChecked())
			checkBoxHobbiesList.add(travel.getText().toString());
		if (photography.isChecked())
			checkBoxHobbiesList.add(photography.getText().toString());
		entity.setInteresting(new Gson().toJson(checkBoxHobbiesList));
		entity.setCurrentStep(currentStep);
		entity.setSubmited(1);// 上传状态位
		entity.setCurrentStep(6);
		OperationBuilder builder = new OperationBuilder().append(
				new EntrySaveOp(), entity);
		OnOperationListener listener = new OnOperationListener() {
			@Override
			public void onOperationFinished(List<Object> resList) {
				if (self.isFinishing()) {
					return;
				} else if (resList == null) {
					Toast.show(self, "连接超时");
					return;
				} else {
					Toast.show(self, retMessage);
					finish();
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
	 * 
	 * @param mobile
	 * @return
	 */
	public boolean isMobile(String mobile) {
		Pattern p = Pattern
				.compile("^(13[0-9]|14[0-9]|15[0-9]|17[0-9]|18[0-9])\\d{8}$");// 正则表达式验证手机的正确性
		Matcher m = p.matcher(mobile);
		return m.matches();
	}

	/**
	 * 身份证判断格式(只能为15位或者18位数字)
	 * 
	 * @param idNo
	 * @return
	 */
	public boolean isIdNo(String idNo) {
		Pattern idNoPattern = Pattern
				.compile("(\\d{14}[0-9a-zA-Z])|(\\d{17}[0-9a-zA-Z])");
		// 通过Pattern获得Matcher
		Matcher idNoMatcher = idNoPattern.matcher(idNo);
		return idNoMatcher.matches();
	}

	/**
	 * 0-7为选择图片库图片并且上传到服务器 8-15为调用系统照相机照相并且上传到服务器
	 */
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		switch (requestCode) {
		case 0:
			if (resultCode == RESULT_OK) {
				Uri selectedImage = data.getData();
				String[] filePathColumn = { MediaStore.Images.Media.DATA };
				Cursor cursor = getContentResolver().query(selectedImage,
						filePathColumn, null, null, null);
				cursor.moveToFirst();
				BitmapFactory.Options options = new BitmapFactory.Options();
				// options 设为true时，构造出的bitmap没有图片，只有一些长宽等配置信息，但比较快，设为false时，才有图片
				options.inJustDecodeBounds = true;

				int scale = (int) (options.outWidth / (float) 100);
				if (scale <= 0)
					scale = 2;
				options.inSampleSize = scale;
				options.inJustDecodeBounds = false;
				int columnIndex = cursor.getColumnIndex(filePathColumn[0]);

				File file;
				long size;
				certificates = cursor.getString(columnIndex);
				cursor.close();
				bitmap = BitmapFactory.decodeFile(certificates, options);
				file = scal(certificates);

				OperationBuilder builder = new OperationBuilder().append(
						new UploadImgOp(), file);
				OnOperationListener listener = new OnOperationListener() {
					@Override
					public void onOperationFinished(List<Object> resList) {
						EntryUploadImageEntity uploadImgEntity = (EntryUploadImageEntity) resList
								.get(0);
						certificates = uploadImgEntity.getRetFilePath();
						if (self.isFinishing()) {
							return;
						} else if (resList == null) {
							Toast.show(self, "连接超时");
							return;
						} else {
							// ImageLoader.getInstance().displayImage(certificates,
							// imgViewPhoto);
							imgViewPhoto.setImageBitmap(bitmap);
							imgViewPhoto.setMaxHeight(200);
							imgViewPhoto
									.setScaleType(ImageView.ScaleType.CENTER_CROP);
							Toast.show(self, "上传成功");
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
			break;
		case 1:
			if (resultCode == RESULT_OK) {
				Uri selectedImage = data.getData();
				String[] filePathColumn = { MediaStore.Images.Media.DATA };
				Cursor cursor = getContentResolver().query(selectedImage,
						filePathColumn, null, null, null);
				cursor.moveToFirst();
				BitmapFactory.Options options = new BitmapFactory.Options();
				// options 设为true时，构造出的bitmap没有图片，只有一些长宽等配置信息，但比较快，设为false时，才有图片
				options.inJustDecodeBounds = true;

				int scale = (int) (options.outWidth / (float) 100);
				if (scale <= 0)
					scale = 2;
				options.inSampleSize = scale;
				options.inJustDecodeBounds = false;
				int columnIndex = cursor.getColumnIndex(filePathColumn[0]);

				File file;
				// long size;
				learningCertificate = cursor.getString(columnIndex);
				cursor.close();
				bitmap = BitmapFactory.decodeFile(learningCertificate, options);
				file = scal(learningCertificate);
				// 限制上传图片的大小
				// size = file.length();
				// if(size>2097152){
				// Toast.show(self, "上传失败，请选择小于2M的图片");
				// }else{
				OperationBuilder builder = new OperationBuilder().append(
						new UploadImgOp(), file);
				OnOperationListener listener = new OnOperationListener() {
					@Override
					public void onOperationFinished(List<Object> resList) {
						EntryUploadImageEntity uploadImgEntity = (EntryUploadImageEntity) resList
								.get(0);
						learningCertificate = uploadImgEntity.getRetFilePath();
						if (self.isFinishing()) {
							return;
						} else if (resList == null) {
							Toast.show(self, "连接超时");
							return;
						} else {
							imgViewAcademic.setImageBitmap(bitmap);
							imgViewAcademic.setMaxHeight(200);
							imgViewAcademic
									.setScaleType(ImageView.ScaleType.CENTER_CROP);
							Toast.show(self, "上传成功");

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
				// }
			}
			break;
		case 2:
			if (resultCode == RESULT_OK) {
				Uri selectedImage = data.getData();
				String[] filePathColumn = { MediaStore.Images.Media.DATA };
				Cursor cursor = getContentResolver().query(selectedImage,
						filePathColumn, null, null, null);
				cursor.moveToFirst();
				BitmapFactory.Options options = new BitmapFactory.Options();
				// options 设为true时，构造出的bitmap没有图片，只有一些长宽等配置信息，但比较快，设为false时，才有图片
				options.inJustDecodeBounds = true;
				int scale = (int) (options.outWidth / (float) 100);
				if (scale <= 0)
					scale = 2;
				options.inSampleSize = scale;
				options.inJustDecodeBounds = false;
				int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
				File file;
				positive = cursor.getString(columnIndex);
				cursor.close();
				bitmap = BitmapFactory.decodeFile(positive, options);
				file = scal(positive);
				OperationBuilder builder = new OperationBuilder().append(
						new UploadImgOp(), file);
				OnOperationListener listener = new OnOperationListener() {
					@Override
					public void onOperationFinished(List<Object> resList) {
						EntryUploadImageEntity uploadImgEntity = (EntryUploadImageEntity) resList
								.get(0);
						positive = uploadImgEntity.getRetFilePath();
						if (self.isFinishing()) {
							return;
						} else if (resList == null) {
							Toast.show(self, "连接超时");
							return;
						} else {
							imgViewIdPhoto1.setImageBitmap(bitmap);
							imgViewIdPhoto1.setMaxHeight(200);
							imgViewIdPhoto1
									.setScaleType(ImageView.ScaleType.CENTER_CROP);
							Toast.show(self, "上传成功");
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
			break;
		case 3:
			if (resultCode == RESULT_OK) {
				Uri selectedImage = data.getData();
				String[] filePathColumn = { MediaStore.Images.Media.DATA };
				Cursor cursor = getContentResolver().query(selectedImage,
						filePathColumn, null, null, null);
				cursor.moveToFirst();
				BitmapFactory.Options options = new BitmapFactory.Options();
				// options 设为true时，构造出的bitmap没有图片，只有一些长宽等配置信息，但比较快，设为false时，才有图片
				options.inJustDecodeBounds = true;
				int scale = (int) (options.outWidth / (float) 100);
				if (scale <= 0)
					scale = 2;
				options.inSampleSize = scale;
				options.inJustDecodeBounds = false;
				int columnIndex = cursor.getColumnIndex(filePathColumn[0]);

				File file;
				reverse = cursor.getString(columnIndex);
				cursor.close();
				bitmap = BitmapFactory.decodeFile(reverse, options);
				file = scal(reverse);
				OperationBuilder builder = new OperationBuilder().append(
						new UploadImgOp(), file);
				OnOperationListener listener = new OnOperationListener() {
					@Override
					public void onOperationFinished(List<Object> resList) {
						EntryUploadImageEntity uploadImgEntity = (EntryUploadImageEntity) resList
								.get(0);
						reverse = uploadImgEntity.getRetFilePath();
						if (self.isFinishing()) {
							return;
						} else if (resList == null) {
							Toast.show(self, "连接超时");
							return;
						} else {
							imgViewIdPhoto2.setImageBitmap(bitmap);
							imgViewIdPhoto2.setMaxHeight(200);
							imgViewIdPhoto2
									.setScaleType(ImageView.ScaleType.CENTER_CROP);
							Toast.show(self, "上传成功");
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
			break;
		case 4:
			if (resultCode == RESULT_OK) {
				Uri selectedImage = data.getData();
				String[] filePathColumn = { MediaStore.Images.Media.DATA };
				Cursor cursor = getContentResolver().query(selectedImage,
						filePathColumn, null, null, null);
				cursor.moveToFirst();
				BitmapFactory.Options options = new BitmapFactory.Options();
				// options 设为true时，构造出的bitmap没有图片，只有一些长宽等配置信息，但比较快，设为false时，才有图片
				options.inJustDecodeBounds = true;
				int scale = (int) (options.outWidth / (float) 100);
				if (scale <= 0)
					scale = 2;
				options.inSampleSize = scale;
				options.inJustDecodeBounds = false;
				int columnIndex = cursor.getColumnIndex(filePathColumn[0]);

				File file;
				retirement = cursor.getString(columnIndex);
				cursor.close();
				bitmap = BitmapFactory.decodeFile(retirement, options);
				file = scal(retirement);

				OperationBuilder builder = new OperationBuilder().append(
						new UploadImgOp(), file);
				OnOperationListener listener = new OnOperationListener() {
					@Override
					public void onOperationFinished(List<Object> resList) {
						EntryUploadImageEntity uploadImgEntity = (EntryUploadImageEntity) resList
								.get(0);
						retirement = uploadImgEntity.getRetFilePath();
						if (self.isFinishing()) {
							return;
						} else if (resList == null) {
							Toast.show(self, "连接超时");
							return;
						} else {
							imgViewRepairOrder.setImageBitmap(bitmap);
							imgViewRepairOrder.setMaxHeight(200);
							imgViewRepairOrder
									.setScaleType(ImageView.ScaleType.CENTER_CROP);
							Toast.show(self, "上传成功");
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
			break;
		case 5:
			if (resultCode == RESULT_OK) {
				Uri selectedImage = data.getData();
				String[] filePathColumn = { MediaStore.Images.Media.DATA };
				Cursor cursor = getContentResolver().query(selectedImage,
						filePathColumn, null, null, null);
				cursor.moveToFirst();
				BitmapFactory.Options options = new BitmapFactory.Options();
				// options 设为true时，构造出的bitmap没有图片，只有一些长宽等配置信息，但比较快，设为false时，才有图片
				options.inJustDecodeBounds = true;

				int scale = (int) (options.outWidth / (float) 100);
				if (scale <= 0)
					scale = 2;
				options.inSampleSize = scale;
				options.inJustDecodeBounds = false;
				int columnIndex = cursor.getColumnIndex(filePathColumn[0]);

				File file;
				physical = cursor.getString(columnIndex);
				cursor.close();
				bitmap = BitmapFactory.decodeFile(physical, options);
				file = scal(physical);

				OperationBuilder builder = new OperationBuilder().append(
						new UploadImgOp(), file);
				OnOperationListener listener = new OnOperationListener() {
					@Override
					public void onOperationFinished(List<Object> resList) {
						EntryUploadImageEntity uploadImgEntity = (EntryUploadImageEntity) resList
								.get(0);
						physical = uploadImgEntity.getRetFilePath();
						if (self.isFinishing()) {
							return;
						} else if (resList == null) {
							Toast.show(self, "连接超时");
							return;
						} else {
							imgViewCheckupReporting.setImageBitmap(bitmap);
							imgViewCheckupReporting.setMaxHeight(200);
							imgViewCheckupReporting
									.setScaleType(ImageView.ScaleType.CENTER_CROP);
							Toast.show(self, "上传成功");
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
			break;
		case 6:
			if (resultCode == RESULT_OK) {
				Uri selectedImage = data.getData();
				String[] filePathColumn = { MediaStore.Images.Media.DATA };
				Cursor cursor = getContentResolver().query(selectedImage,
						filePathColumn, null, null, null);
				cursor.moveToFirst();
				BitmapFactory.Options options = new BitmapFactory.Options();
				// options 设为true时，构造出的bitmap没有图片，只有一些长宽等配置信息，但比较快，设为false时，才有图片
				options.inJustDecodeBounds = true;
				int scale = (int) (options.outWidth / (float) 100);
				if (scale <= 0)
					scale = 2;
				options.inSampleSize = scale;
				options.inJustDecodeBounds = false;
				int columnIndex = cursor.getColumnIndex(filePathColumn[0]);

				File file;
				cardpositive = cursor.getString(columnIndex);
				cursor.close();
				bitmap = BitmapFactory.decodeFile(cardpositive, options);
				file = scal(cardpositive);

				OperationBuilder builder = new OperationBuilder().append(
						new UploadImgOp(), file);
				OnOperationListener listener = new OnOperationListener() {
					@Override
					public void onOperationFinished(List<Object> resList) {
						EntryUploadImageEntity uploadImgEntity = (EntryUploadImageEntity) resList
								.get(0);
						cardpositive = uploadImgEntity.getRetFilePath();
						if (self.isFinishing()) {
							return;
						} else if (resList == null) {
							Toast.show(self, "连接超时");
							return;
						} else {
							imgViewBankCardPositive.setImageBitmap(bitmap);
							imgViewBankCardPositive.setMaxHeight(200);
							imgViewBankCardPositive
									.setScaleType(ImageView.ScaleType.CENTER_CROP);
							Toast.show(self, "上传成功");
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
			break;
		case 7:
			if (resultCode == RESULT_OK) {
				Uri selectedImage = data.getData();
				String[] filePathColumn = { MediaStore.Images.Media.DATA };
				Cursor cursor = getContentResolver().query(selectedImage,
						filePathColumn, null, null, null);
				cursor.moveToFirst();
				BitmapFactory.Options options = new BitmapFactory.Options();
				// options 设为true时，构造出的bitmap没有图片，只有一些长宽等配置信息，但比较快，设为false时，才有图片
				options.inJustDecodeBounds = true;

				int scale = (int) (options.outWidth / (float) 100);
				if (scale <= 0)
					scale = 2;
				options.inSampleSize = scale;
				options.inJustDecodeBounds = false;
				int columnIndex = cursor.getColumnIndex(filePathColumn[0]);

				File file;
				cardreverse = cursor.getString(columnIndex);
				cursor.close();
				bitmap = BitmapFactory.decodeFile(cardreverse, options);
				file = scal(cardreverse);

				OperationBuilder builder = new OperationBuilder().append(
						new UploadImgOp(), file);
				OnOperationListener listener = new OnOperationListener() {
					@Override
					public void onOperationFinished(List<Object> resList) {
						EntryUploadImageEntity uploadImgEntity = (EntryUploadImageEntity) resList
								.get(0);
						cardreverse = uploadImgEntity.getRetFilePath();
						if (self.isFinishing()) {
							return;
						} else if (resList == null) {
							Toast.show(self, "连接超时");
							return;
						} else {
							imgViewBankCardReverse.setImageBitmap(bitmap);
							imgViewBankCardReverse.setMaxHeight(200);
							imgViewBankCardReverse
									.setScaleType(ImageView.ScaleType.CENTER_CROP);
							Toast.show(self, "上传成功");
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
			break;
		case 8:
			if (resultCode == RESULT_OK) {
				String fileName = String.valueOf(System.currentTimeMillis());
				Bitmap bm = (Bitmap) data.getExtras().get("data");
				// String SDPATH = Environment.getExternalStorageDirectory()+
				// "/Photo_LJ/";
				String SDPATH = "/sdcard/DCIM/Camera";
				File f = new File(SDPATH, fileName + ".JPEG");
				try {
					FileOutputStream out = new FileOutputStream(f);
					bm.compress(Bitmap.CompressFormat.JPEG, 90, out);
					out.flush();
					out.close();
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				File file1;
				certificates = f.toString();
				bitmap = BitmapFactory.decodeFile(certificates);
				// file1 = new File(certificates);
				file1 = scal(certificates);
				OperationBuilder builder = new OperationBuilder().append(
						new UploadImgOp(), file1);
				OnOperationListener listener = new OnOperationListener() {
					@Override
					public void onOperationFinished(List<Object> resList) {
						EntryUploadImageEntity uploadImgEntity = (EntryUploadImageEntity) resList
								.get(0);
						certificates = uploadImgEntity.getRetFilePath();
						if (self.isFinishing()) {
							return;
						} else if (resList == null) {
							Toast.show(self, "连接超时");
							return;
						} else {
							// ImageLoader.getInstance().displayImage(certificates,
							// imgViewPhoto);
							imgViewPhoto.setImageBitmap(bitmap);
							imgViewPhoto.setMaxHeight(200);
							imgViewPhoto
									.setScaleType(ImageView.ScaleType.CENTER_CROP);
							Toast.show(self, "上传成功");
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
			break;
		case 9:
			if (resultCode == RESULT_OK) {
				String fileName = String.valueOf(System.currentTimeMillis());
				Bitmap bm = (Bitmap) data.getExtras().get("data");
				// String SDPATH = Environment.getExternalStorageDirectory()+
				// "/Photo_LJ/";
				String SDPATH = "/sdcard/DCIM/Camera";
				File f = new File(SDPATH, fileName + ".JPEG");
				try {
					FileOutputStream out = new FileOutputStream(f);
					bm.compress(Bitmap.CompressFormat.JPEG, 90, out);
					out.flush();
					out.close();
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				File file1;
				learningCertificate = f.toString();
				bitmap = BitmapFactory.decodeFile(learningCertificate);
				file1 = scal(learningCertificate);
				OperationBuilder builder = new OperationBuilder().append(
						new UploadImgOp(), file1);
				OnOperationListener listener = new OnOperationListener() {
					@Override
					public void onOperationFinished(List<Object> resList) {
						EntryUploadImageEntity uploadImgEntity = (EntryUploadImageEntity) resList
								.get(0);
						learningCertificate = uploadImgEntity.getRetFilePath();
						if (self.isFinishing()) {
							return;
						} else if (resList == null) {
							Toast.show(self, "连接超时");
							return;
						} else {
							// ImageLoader.getInstance().displayImage(certificates,
							// imgViewPhoto);
							imgViewAcademic.setImageBitmap(bitmap);
							imgViewAcademic.setMaxHeight(200);
							imgViewAcademic
									.setScaleType(ImageView.ScaleType.CENTER_CROP);
							Toast.show(self, "上传成功");
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
			break;
		case 10:
			if (resultCode == RESULT_OK) {
				String fileName = String.valueOf(System.currentTimeMillis());
				Bitmap bm = (Bitmap) data.getExtras().get("data");
				String SDPATH = "/sdcard/DCIM/Camera";
				File f = new File(SDPATH, fileName + ".JPEG");
				try {
					FileOutputStream out = new FileOutputStream(f);
					bm.compress(Bitmap.CompressFormat.JPEG, 90, out);
					out.flush();
					out.close();
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				File file1;
				positive = f.toString();
				bitmap = BitmapFactory.decodeFile(positive);
				file1 = scal(positive);
				OperationBuilder builder = new OperationBuilder().append(
						new UploadImgOp(), file1);
				OnOperationListener listener = new OnOperationListener() {
					@Override
					public void onOperationFinished(List<Object> resList) {
						EntryUploadImageEntity uploadImgEntity = (EntryUploadImageEntity) resList
								.get(0);
						positive = uploadImgEntity.getRetFilePath();
						if (self.isFinishing()) {
							return;
						} else if (resList == null) {
							Toast.show(self, "连接超时");
							return;
						} else {
							// ImageLoader.getInstance().displayImage(certificates,
							// imgViewPhoto);
							imgViewIdPhoto1.setImageBitmap(bitmap);
							imgViewIdPhoto1.setMaxHeight(200);
							imgViewIdPhoto1
									.setScaleType(ImageView.ScaleType.CENTER_CROP);
							Toast.show(self, "上传成功");
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
			break;
		case 11:
			if (resultCode == RESULT_OK) {
				String fileName = String.valueOf(System.currentTimeMillis());
				Bitmap bm = (Bitmap) data.getExtras().get("data");
				String SDPATH = "/sdcard/DCIM/Camera";
				File f = new File(SDPATH, fileName + ".JPEG");
				try {
					FileOutputStream out = new FileOutputStream(f);
					bm.compress(Bitmap.CompressFormat.JPEG, 90, out);
					out.flush();
					out.close();
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				File file1;
				reverse = f.toString();
				bitmap = BitmapFactory.decodeFile(reverse);
				file1 = new File(reverse);
				OperationBuilder builder = new OperationBuilder().append(
						new UploadImgOp(), file1);
				OnOperationListener listener = new OnOperationListener() {
					@Override
					public void onOperationFinished(List<Object> resList) {
						EntryUploadImageEntity uploadImgEntity = (EntryUploadImageEntity) resList
								.get(0);
						reverse = uploadImgEntity.getRetFilePath();
						if (self.isFinishing()) {
							return;
						} else if (resList == null) {
							Toast.show(self, "连接超时");
							return;
						} else {
							// ImageLoader.getInstance().displayImage(certificates,
							// imgViewPhoto);
							imgViewIdPhoto2.setImageBitmap(bitmap);
							imgViewIdPhoto2.setMaxHeight(200);
							imgViewIdPhoto2
									.setScaleType(ImageView.ScaleType.CENTER_CROP);
							Toast.show(self, "上传成功");
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
			break;
		case 12:
			if (resultCode == RESULT_OK) {
				String fileName = String.valueOf(System.currentTimeMillis());
				Bitmap bm = (Bitmap) data.getExtras().get("data");
				String SDPATH = "/sdcard/DCIM/Camera";
				File f = new File(SDPATH, fileName + ".JPEG");
				try {
					FileOutputStream out = new FileOutputStream(f);
					bm.compress(Bitmap.CompressFormat.JPEG, 90, out);
					out.flush();
					out.close();
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				File file1;
				retirement = f.toString();
				bitmap = BitmapFactory.decodeFile(retirement);
				file1 = scal(retirement);
				OperationBuilder builder = new OperationBuilder().append(
						new UploadImgOp(), file1);
				OnOperationListener listener = new OnOperationListener() {
					@Override
					public void onOperationFinished(List<Object> resList) {
						EntryUploadImageEntity uploadImgEntity = (EntryUploadImageEntity) resList
								.get(0);
						retirement = uploadImgEntity.getRetFilePath();
						if (self.isFinishing()) {
							return;
						} else if (resList == null) {
							Toast.show(self, "连接超时");
							return;
						} else {
							// ImageLoader.getInstance().displayImage(certificates,
							// imgViewPhoto);
							imgViewRepairOrder.setImageBitmap(bitmap);
							imgViewRepairOrder.setMaxHeight(200);
							imgViewRepairOrder
									.setScaleType(ImageView.ScaleType.CENTER_CROP);
							Toast.show(self, "上传成功");
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
			break;
		case 13:
			if (resultCode == RESULT_OK) {
				String fileName = String.valueOf(System.currentTimeMillis());
				Bitmap bm = (Bitmap) data.getExtras().get("data");
				String SDPATH = "/sdcard/DCIM/Camera";
				File f = new File(SDPATH, fileName + ".JPEG");
				try {
					FileOutputStream out = new FileOutputStream(f);
					bm.compress(Bitmap.CompressFormat.JPEG, 90, out);
					out.flush();
					out.close();
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				File file1;
				physical = f.toString();
				bitmap = BitmapFactory.decodeFile(physical);
				file1 = scal(physical);
				OperationBuilder builder = new OperationBuilder().append(
						new UploadImgOp(), file1);
				OnOperationListener listener = new OnOperationListener() {
					@Override
					public void onOperationFinished(List<Object> resList) {
						EntryUploadImageEntity uploadImgEntity = (EntryUploadImageEntity) resList
								.get(0);
						physical = uploadImgEntity.getRetFilePath();
						if (self.isFinishing()) {
							return;
						} else if (resList == null) {
							Toast.show(self, "连接超时");
							return;
						} else {
							// ImageLoader.getInstance().displayImage(certificates,
							// imgViewPhoto);
							imgViewCheckupReporting.setImageBitmap(bitmap);
							imgViewCheckupReporting.setMaxHeight(200);
							imgViewCheckupReporting
									.setScaleType(ImageView.ScaleType.CENTER_CROP);
							Toast.show(self, "上传成功");
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
			break;
		case 14:
			if (resultCode == RESULT_OK) {
				String fileName = String.valueOf(System.currentTimeMillis());
				Bitmap bm = (Bitmap) data.getExtras().get("data");
				String SDPATH = "/sdcard/DCIM/Camera";
				File f = new File(SDPATH, fileName + ".JPEG");
				try {
					FileOutputStream out = new FileOutputStream(f);
					bm.compress(Bitmap.CompressFormat.JPEG, 90, out);
					out.flush();
					out.close();
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				File file1;
				cardpositive = f.toString();
				bitmap = BitmapFactory.decodeFile(cardpositive);
				file1 = scal(cardpositive);
				OperationBuilder builder = new OperationBuilder().append(
						new UploadImgOp(), file1);
				OnOperationListener listener = new OnOperationListener() {
					@Override
					public void onOperationFinished(List<Object> resList) {
						EntryUploadImageEntity uploadImgEntity = (EntryUploadImageEntity) resList
								.get(0);
						cardpositive = uploadImgEntity.getRetFilePath();
						if (self.isFinishing()) {
							return;
						} else if (resList == null) {
							Toast.show(self, "连接超时");
							return;
						} else {
							// ImageLoader.getInstance().displayImage(certificates,
							// imgViewPhoto);
							imgViewBankCardPositive.setImageBitmap(bitmap);
							imgViewBankCardPositive.setMaxHeight(200);
							imgViewBankCardPositive
									.setScaleType(ImageView.ScaleType.CENTER_CROP);
							Toast.show(self, "上传成功");
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
			break;
		case 15:
			if (resultCode == RESULT_OK) {
				String fileName = String.valueOf(System.currentTimeMillis());
				Bitmap bm = (Bitmap) data.getExtras().get("data");
				String SDPATH = "/sdcard/DCIM/Camera";
				File f = new File(SDPATH, fileName + ".JPEG");
				try {
					FileOutputStream out = new FileOutputStream(f);
					bm.compress(Bitmap.CompressFormat.JPEG, 90, out);
					out.flush();
					out.close();
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				File file1;
				cardreverse = f.toString();
				bitmap = BitmapFactory.decodeFile(cardreverse);
				file1 = new File(cardreverse);
				OperationBuilder builder = new OperationBuilder().append(
						new UploadImgOp(), file1);
				OnOperationListener listener = new OnOperationListener() {
					@Override
					public void onOperationFinished(List<Object> resList) {
						EntryUploadImageEntity uploadImgEntity = (EntryUploadImageEntity) resList
								.get(0);
						cardreverse = uploadImgEntity.getRetFilePath();
						if (self.isFinishing()) {
							return;
						} else if (resList == null) {
							Toast.show(self, "连接超时");
							return;
						} else {
							// ImageLoader.getInstance().displayImage(certificates,
							// imgViewPhoto);
							imgViewBankCardReverse.setImageBitmap(bitmap);
							imgViewBankCardReverse.setMaxHeight(200);
							imgViewBankCardReverse
									.setScaleType(ImageView.ScaleType.CENTER_CROP);
							Toast.show(self, "上传成功");
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
			break;
		}
	}

	// 在退出Activity时，将bitmap回收
	@Override
	protected void onDestroy() {
		if (bitmap != null && !bitmap.isRecycled())
			bitmap.recycle();
		super.onDestroy();
	}

	private static final int TAKE_PICTURE = 0x000001;

	public void photo(int k) {
		Intent openCameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		startActivityForResult(openCameraIntent, k);
	}

	// 压缩图片
	public static File scal(String path) {
		// String path = fileUri.getPath();
		File outputFile = new File(path);
		long fileSize = outputFile.length();
		final long fileMaxSize = 200 * 1024;
		if (fileSize >= fileMaxSize) {
			BitmapFactory.Options options = new BitmapFactory.Options();
			options.inJustDecodeBounds = true;
			BitmapFactory.decodeFile(path, options);
			int height = options.outHeight;
			int width = options.outWidth;

			double scale = Math.sqrt((float) fileSize / fileMaxSize);
			options.outHeight = (int) (height / scale);
			options.outWidth = (int) (width / scale);
			options.inSampleSize = (int) (scale + 0.5);
			options.inJustDecodeBounds = false;

			Bitmap bitmap = BitmapFactory.decodeFile(path, options);
			outputFile = new File(EntryManagementActiviy.createImageFile()
					.getPath());
			FileOutputStream fos = null;
			try {
				fos = new FileOutputStream(outputFile);
				bitmap.compress(Bitmap.CompressFormat.JPEG, 50, fos);
				fos.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (!bitmap.isRecycled()) {
				bitmap.recycle();
			} else {
				File tempFile = outputFile;
				outputFile = new File(EntryManagementActiviy.createImageFile()
						.getPath());
				EntryManagementActiviy.copyFileUsingFileChannels(tempFile,
						outputFile);
			}

		}
		return outputFile;

	}

	public static Uri createImageFile() {
		// Create an image file name
		String time = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		String SDPATH = "/sdcard/DCIM/Camera";
		File image = new File(SDPATH, time + ".JPEG");
		try {
			FileOutputStream out = new FileOutputStream(image);
			out.flush();
			out.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		// Save a file: path for use with ACTION_VIEW intents
		return Uri.fromFile(image);
	}

	public static void copyFileUsingFileChannels(File source, File dest) {
		FileChannel inputChannel = null;
		FileChannel outputChannel = null;
		try {
			try {
				inputChannel = new FileInputStream(source).getChannel();
				outputChannel = new FileOutputStream(dest).getChannel();
				outputChannel
						.transferFrom(inputChannel, 0, inputChannel.size());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} finally {
			try {
				inputChannel.close();
				outputChannel.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
