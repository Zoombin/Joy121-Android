package com.joy.Widget;

import gejw.android.quickandroid.QActivity;
import gejw.android.quickandroid.ui.adapter.UIAdapter;
import java.util.ArrayList;

import com.joy.R;
import com.joy.Dialog.EntryManagementAddFamilyInfoDialog;
import com.joy.json.model.EntryManageEducationInfoEntity;
import com.joy.json.model.EntryManageWorkExperienceInfoEntity;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class EntryWrokExperienceDetailAdapter extends BaseAdapter{
	/**
	 * educationInfo上下文对象
	 * rainbow 2015-09-11
	 */
	private Context mContext = null;
	private QActivity mActivity ;
	private ArrayList<EntryManageWorkExperienceInfoEntity> data = new ArrayList<EntryManageWorkExperienceInfoEntity>();
	private UIAdapter uiAdapter;
	private Resources resources;
	private EntryManagementAddFamilyInfoDialog entryAddInfo;//声明变量
	
	/**
	 * @param mainActivity
	 */
	public EntryWrokExperienceDetailAdapter(QActivity activity, Context ctx) {
		mActivity = activity;
		mContext = ctx;
		uiAdapter = UIAdapter.getInstance(ctx);
		resources = ctx.getResources();
	}
	public void addItem(EntryManageWorkExperienceInfoEntity entity) {
		data.add(entity);
	}
	public void removeItem(EntryManageWorkExperienceInfoEntity entity) {
		data.remove(entity);
	}
	
	public void updateItem(int position, EntryManageWorkExperienceInfoEntity entity) {
		data.set(position, entity);
	}
	public void addSeparatorItem(EntryManageWorkExperienceInfoEntity entity) {
		data.add(entity);
	}
	
	public ArrayList<EntryManageWorkExperienceInfoEntity> getData() {
		return data;
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return data.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return data.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		final EntryManageWorkExperienceInfoEntity entity = data.get(position);
		ViewHolder holder;
		if(convertView==null){
			convertView=LayoutInflater.from(mContext).inflate(R.layout.activity_entry_workexperiencedetail, parent, false);
			holder = new ViewHolder();
			//横线
			holder.v_line = (View) convertView.findViewById(R.id.v_line);
			//时间
			holder.layout_educationDate=(LinearLayout) convertView.findViewById(R.id.layout_educationDate);
			holder.tv_educationDate=(TextView) convertView.findViewById(R.id.tv_educationDate);
			holder.tv_educationDate1=(TextView) convertView.findViewById(R.id.tv_educationDate1);
			//学校
			holder.layout_educationSchool=(LinearLayout) convertView.findViewById(R.id.layout_educationSchool);
			holder.tv_educationSchool=(TextView) convertView.findViewById(R.id.tv_educationSchool);
			holder.tv_educationSchool1=(TextView) convertView.findViewById(R.id.tv_educationSchool1);
			//专业
			holder.layout_educationProfession=(LinearLayout) convertView.findViewById(R.id.layout_educationProfession);
			holder.tv_educationProfession=(TextView) convertView.findViewById(R.id.tv_educationProfession);
			holder.tv_educationProfession1=(TextView) convertView.findViewById(R.id.tv_educationProfession1);
			//收获
			holder.layout_educationAchievement=(LinearLayout) convertView.findViewById(R.id.layout_educationAchievement);
			holder.tv_educationAchievement=(TextView) convertView.findViewById(R.id.tv_educationAchievement);
			holder.tv_educationAchievement1=(TextView) convertView.findViewById(R.id.tv_educationAchievement1);
			//删除
			holder.btn_remove=(Button)convertView.findViewById(R.id.btn_remove);
			//编辑
			holder.btn_edit=(Button)convertView.findViewById(R.id.btn_edit);
			convertView.setTag(holder);
		}else{
			holder = (ViewHolder) convertView.getTag();
		}
//		holder.tv_educationDate1.setText(entity.getDate());
//		holder.tv_educationSchool1.setText(entity.getSchool());
//		holder.tv_educationProfession1.setText(entity.getProfession());
//		holder.tv_educationAchievement1.setText(entity.getAchievement());
		//删除
		holder.btn_remove.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				removeItem(entity);
				notifyDataSetChanged();
			}
			
		});
		//编辑
		holder.btn_edit.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
//				entryAddInfo=new EntryManagementAddFamilyInfoDialog(mActivity,R.style.dialog);
//				entryAddInfo.show();
//				entryAddInfo.setTitle("修改学习经历");
//				entryAddInfo.et_familyName.setText(entity.getName());
//				entryAddInfo.et_familyBirthday.setText(entity.getBirthday());
//				entryAddInfo.et_familyAddress.setText(entity.getAddress());
//				entryAddInfo.et_faimlyRelation.setText(entity.getRelationShip());
//				entryAddInfo.setButtonYes("确定");
//				entryAddInfo.setButtonNo("取消");
//				entryAddInfo.setOnClickButton(new AddInfoDialogButtonClickCallback(){
//
//					@Override
//					public void positiveButtonClick() {}
//					@Override
//					public void negativeButtonClick() {}
//					@Override
//					public void getAddInfoDialogButtonClickCallback(String addInfo1,
//							String addInfo2, String addInfo3, String addInfo4) {
//						EntryManageFamilyInfoEntity temp =new EntryManageFamilyInfoEntity();
//						temp.setName(addInfo1);
//						temp.setBirthday(addInfo2);
//						temp.setAddress(addInfo3);
//						temp.setRelationShip(addInfo4);
//						for(int i=0;i<getCount();i++){
//							if(entity==getItem(i)){
//								updateItem(i,temp);
//							}
//						}
//						
//						notifyDataSetChanged();	
//						entryAddInfo.dismiss();
//					}
//				});
			} 
			
		});
		return convertView;
	}
	public class ViewHolder {
		LinearLayout layout_educationIfo;
		View v_line;
		LinearLayout layout_educationDate;
		TextView tv_educationDate;
		TextView tv_educationDate1;
		
		LinearLayout layout_educationSchool;
		TextView tv_educationSchool;
		TextView tv_educationSchool1;
		
		LinearLayout layout_educationProfession;
		TextView tv_educationProfession;
		TextView tv_educationProfession1;
		
		LinearLayout layout_educationAchievement;
		TextView tv_educationAchievement;
		TextView tv_educationAchievement1;
		
		Button btn_remove;
		Button btn_edit;
		
	}

}
