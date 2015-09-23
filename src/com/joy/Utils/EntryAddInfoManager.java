package com.joy.Utils;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.res.Resources;
import android.net.ParseException;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.google.gson.Gson;
import com.joy.JoyApplication;
import com.joy.R;
import com.joy.Activity.EntryManagementActiviy;
import com.joy.Dialog.DialogUtil;
import com.joy.Dialog.DialogUtil.AddExperienceInfoDialogButtonClickCallback;
import com.joy.Dialog.EntryManagementAddEductionInfoDialog;
import com.joy.Dialog.EntryManagementAddFamilyInfoDialog;
import com.joy.Dialog.DialogUtil.AddFamilyInfoDialogButtonClickCallback;
import com.joy.Dialog.EntryManagementAddWorkExperienceInfoDialog;
import com.joy.Widget.EntryEducationDetailAdapter;
import com.joy.Widget.EntryFamilyDetailAdapter;
import com.joy.Widget.EntryWrokExperienceDetailAdapter;
import com.joy.json.JsonCommon;
import com.joy.json.JsonCommon.OnOperationListener;
import com.joy.json.model.EntryManageEducationInfoEntity;
import com.joy.json.model.EntryManageEntity;
import com.joy.json.model.EntryManageFamilyInfoEntity;
import com.joy.json.model.EntryManageWorkExperienceInfoEntity;
import com.joy.json.operation.OperationBuilder;
import com.joy.json.operation.impl.EntrySaveOp;

import gejw.android.quickandroid.QActivity;
import gejw.android.quickandroid.widget.Toast;

public class EntryAddInfoManager extends QActivity {
	/**
	 * @author rainbow
	 */
	private Context mContext;
	private QActivity mActivity;
	private DialogUtil dialogUtil;
	private Resources resources;
	EditText et_nloginpwd, et_securitycode, et_comfirmnewpwd;
	private EntryManagementAddFamilyInfoDialog entryAddFamilyInfo;// 声明变量
	private EntryManagementAddEductionInfoDialog entryAddEducationInfo;
	private EntryManagementAddWorkExperienceInfoDialog enryAddWorkExperience;

	public EntryAddInfoManager(Context context, QActivity mActivity) {
		this.mContext = context;
		this.mActivity = mActivity;
		dialogUtil = new DialogUtil(mContext);
	}

	/**
	 * 增加家庭信息
	 */
	public void addFamilyInfo(final EntryFamilyDetailAdapter adapterFamily) {
		entryAddFamilyInfo = new EntryManagementAddFamilyInfoDialog(mActivity,
				R.style.dialog);
		entryAddFamilyInfo.show();
		entryAddFamilyInfo.setTitle("增加家庭信息");
		entryAddFamilyInfo.setButtonYes("确定");
		entryAddFamilyInfo.setButtonNo("取消");
		entryAddFamilyInfo.setOnClickButton(new AddFamilyInfoDialogButtonClickCallback() {
					@Override
					public void positiveButtonClick() {
					}

					@Override
					public void negativeButtonClick() {
					}

					@Override
					public void getAddFamilyInfoDialogButtonClickCallback(
							String addName, String addBirthday,
							String addAddress, String addRelationShip) {
						EntryManageFamilyInfoEntity temp = new EntryManageFamilyInfoEntity();
						boolean convertSuccess = true;
						// 指定日期格式为四位年/两位月份/两位日期，注意yyyy-MM-dd区分大小写；
						SimpleDateFormat format = new SimpleDateFormat(
								"yyyy-MM-dd");
						// 设置lenient为false.
						// 否则SimpleDateFormat会比较宽松地验证日期，比如2007/02/29会被接受，并转换成2007/03/01
						try {
							if (TextUtils.isEmpty(addBirthday)) {
							} else {
								format.setLenient(false);
								format.parse(addBirthday);
							}
						} catch (java.text.ParseException e) {
							// TODO Auto-generated catch block
							convertSuccess = false;
							e.printStackTrace();
						}
						if (convertSuccess == false) {
							Toast.show(mActivity, "生日格式为：1990-01-01");
							return;
						} else {
							temp.setName(addName);
							temp.setBirthday(addBirthday);
							temp.setAddress(addAddress);
							temp.setRelationShip(addRelationShip);
							adapterFamily.addItem(temp);
							adapterFamily.notifyDataSetChanged();
							entryAddFamilyInfo.dismiss();
						}
					}
				});
	}

	/**
	 * 增加学习经历
	 */
	public void addEducationInfo(
			final EntryEducationDetailAdapter educationAdapter) {
		entryAddEducationInfo = new EntryManagementAddEductionInfoDialog(
				mActivity, R.style.dialog);
		entryAddEducationInfo.show();
		entryAddEducationInfo.setTitle("增加学习经历");
		entryAddEducationInfo.setButtonYes("确定");
		entryAddEducationInfo.setButtonNo("取消");
		entryAddEducationInfo.setOnClickButton(new AddExperienceInfoDialogButtonClickCallback() {

			@Override
			public void positiveButtonClick() {}
			@Override
			public void negativeButtonClick() {}
			@Override
			public void getAddExperienceInfoDialogButtonClickCallback(
					String addSDate, String addEDate, String addInfo1,
					String addInfo2, String addAchievement) {
				EntryManageEducationInfoEntity education = new EntryManageEducationInfoEntity();
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
				boolean convertSuccess=true;
				boolean convertSuccess1=true;
				try {
					if(TextUtils.isEmpty(addSDate)){
					}else{
						format.setLenient(false);
						format.parse(addSDate);
					}
				} catch (java.text.ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					convertSuccess = false;
				}
				try {
					if(TextUtils.isEmpty(addEDate)){
					}else{
						format.setLenient(false);
						format.parse(addEDate);
					}
				} catch (java.text.ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					convertSuccess1 = false;
				}
				if (convertSuccess == false) {
					Toast.show(mActivity, "开始时间格式为：2010-01-01");
					return;
				} else if(convertSuccess1 == false){
					Toast.show(mActivity, "结束时间格式为：2010-01-01");
					return;
				}else{
					education.setSDate(addSDate);
					education.setEDate(addEDate);
					education.setSchool(addInfo1);
					education.setProfession(addInfo2);
					education.setAchievement(addAchievement);
					educationAdapter.addItem(education);
					educationAdapter.notifyDataSetChanged();
					entryAddEducationInfo.dismiss();
				}
			}
					
		});
	}

	/**
	 * 增加工作经验
	 */
	public void addWorkExperienceInfo(
			final EntryWrokExperienceDetailAdapter workExperienceAdapter) {
		enryAddWorkExperience = new EntryManagementAddWorkExperienceInfoDialog(
				mActivity, R.style.dialog);
		enryAddWorkExperience.show();
		enryAddWorkExperience.setTitle("增加工作经验");
		enryAddWorkExperience.setButtonYes("确定");
		enryAddWorkExperience.setButtonNo("取消");
		enryAddWorkExperience.setOnClickButton(new AddExperienceInfoDialogButtonClickCallback() {

			@Override
			public void positiveButtonClick() {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void negativeButtonClick() {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void getAddExperienceInfoDialogButtonClickCallback(
					String addSDate, String addEDate, String addInfo1,
					String addInfo2, String addAchievement) {
				EntryManageWorkExperienceInfoEntity workExperience =new EntryManageWorkExperienceInfoEntity();
				 SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
				 boolean convertSuccess=true;
				 boolean convertSuccess1=true;
						try {
							if(TextUtils.isEmpty(addSDate)){
							}else{
								format.setLenient(false);
								format.parse(addSDate);
							}
						} catch (java.text.ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							convertSuccess = false;
						}
						try {
							if(TextUtils.isEmpty(addEDate)){
							}else{
								format.setLenient(false);
								format.parse(addEDate);
							}
						} catch (java.text.ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							convertSuccess1 = false;
						}
						if (convertSuccess == false) {
							Toast.show(mActivity, "开始时间格式为：2010-01-01");
							return;
						} else if(convertSuccess1 == false){
							Toast.show(mActivity, "结束时间格式为：2010-01-01");
							return;
						}else{
							workExperience.setSDate(addSDate);
							workExperience.setEDate(addEDate);
							workExperience.setCompany(addInfo1);
							workExperience.setPosition(addInfo2);
							workExperience.setAchievement(addAchievement);
							workExperienceAdapter.addItem(workExperience);
							workExperienceAdapter.notifyDataSetChanged();
							enryAddWorkExperience.dismiss();
						}
			}
		});
	}
}
