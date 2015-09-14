package com.joy.Utils;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.res.Resources;
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
import com.joy.Dialog.EntryManagementAddEductionInfoDialog;
import com.joy.Dialog.EntryManagementAddFamilyInfoDialog;
import com.joy.Dialog.DialogUtil.AddInfoDialogButtonClickCallback;
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

public class EntryAddInfoManager extends QActivity{
	/**
	 * @author rainbow   
	 */
	private Context mContext;
	private QActivity mActivity;
	private DialogUtil dialogUtil;
	private Resources resources;
	EditText et_nloginpwd,et_securitycode,et_comfirmnewpwd;
	private EntryManagementAddFamilyInfoDialog entryAddFamilyInfo;//声明变量
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
	public void addFamilyInfo(final EntryFamilyDetailAdapter adapterFamily){
		entryAddFamilyInfo=new EntryManagementAddFamilyInfoDialog(mActivity,R.style.dialog);
		entryAddFamilyInfo.show();
		entryAddFamilyInfo.setTitle("增加家庭信息");
		entryAddFamilyInfo.setButtonYes("确定");
		entryAddFamilyInfo.setButtonNo("取消");
		entryAddFamilyInfo.setOnClickButton(new AddInfoDialogButtonClickCallback(){

			@Override
			public void positiveButtonClick() {}
			@Override
			public void negativeButtonClick() {}
			@Override
			public void getAddInfoDialogButtonClickCallback(String addInfo1,
					String addInfo2, String addInfo3, String addInfo4) {
				EntryManageFamilyInfoEntity temp =new EntryManageFamilyInfoEntity();
				temp.setName(addInfo1);
				temp.setBirthday(addInfo2);
				temp.setAddress(addInfo3);
				temp.setRelationShip(addInfo4);
				adapterFamily.addItem(temp);
				adapterFamily.notifyDataSetChanged();	
				entryAddFamilyInfo.dismiss();
			}
			
		});
     }
	/**
	 * 增加学习经历
	 */
	public void addEducationInfo(final EntryEducationDetailAdapter educationAdapter){
		entryAddEducationInfo=new EntryManagementAddEductionInfoDialog(mActivity,R.style.dialog);
		entryAddEducationInfo.show();
		entryAddEducationInfo.setTitle("增加学习经历");
		entryAddEducationInfo.setButtonYes("确定");
		entryAddEducationInfo.setButtonNo("取消");
		entryAddEducationInfo.setOnClickButton(new AddInfoDialogButtonClickCallback(){

			@Override
			public void positiveButtonClick() {}
			@Override
			public void negativeButtonClick() {}
			@Override
			public void getAddInfoDialogButtonClickCallback(String addInfo1,
					String addInfo2, String addInfo3, String addInfo4) {
				EntryManageEducationInfoEntity education =new EntryManageEducationInfoEntity();
				education.setDate(addInfo1);
				education.setSchool(addInfo2);
				education.setProfession(addInfo3);
				education.setAchievement(addInfo4);
				educationAdapter.addItem(education);
				educationAdapter.notifyDataSetChanged();	
				entryAddEducationInfo.dismiss();
			}
			
		});
	}
	/**
	 * 增加工作经验
	 */
	public void addWorkExperienceInfo(final EntryWrokExperienceDetailAdapter workExperienceAdapter){
		enryAddWorkExperience=new EntryManagementAddWorkExperienceInfoDialog(mActivity,R.style.dialog);
		enryAddWorkExperience.show();
		enryAddWorkExperience.setTitle("增加工作经验");
		enryAddWorkExperience.setButtonYes("确定");
		enryAddWorkExperience.setButtonNo("取消");
		enryAddWorkExperience.setOnClickButton(new AddInfoDialogButtonClickCallback(){

			@Override
			public void positiveButtonClick() {}
			@Override
			public void negativeButtonClick() {}
			@Override
			public void getAddInfoDialogButtonClickCallback(String addInfo1,
					String addInfo2, String addInfo3, String addInfo4) {
				EntryManageWorkExperienceInfoEntity workExperience =new EntryManageWorkExperienceInfoEntity();
				workExperience.setDate(addInfo1);
				workExperience.setCompany(addInfo2);
				workExperience.setPosition(addInfo3);
				workExperience.setAchievement(addInfo4);
				workExperienceAdapter.addItem(workExperience);
				workExperienceAdapter.notifyDataSetChanged();	
				enryAddWorkExperience.dismiss();
			}
			
		});
	}
}
