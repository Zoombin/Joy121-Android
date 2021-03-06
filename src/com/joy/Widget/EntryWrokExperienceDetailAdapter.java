package com.joy.Widget;

import gejw.android.quickandroid.QActivity;
import gejw.android.quickandroid.ui.adapter.UIAdapter;
import gejw.android.quickandroid.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import com.joy.R;
import com.joy.Dialog.DialogUtil;
import com.joy.Dialog.DialogUtil.AddExperienceInfoDialogButtonClickCallback;
import com.joy.Dialog.EntryManagementAddEductionInfoDialog;
import com.joy.Dialog.EntryManagementAddWorkExperienceInfoDialog;
import com.joy.Dialog.DialogUtil.DialogButtonClickCallback;
import com.joy.json.model.EntryManageEducationInfoEntity;
import com.joy.json.model.EntryManageWorkExperienceInfoEntity;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.text.TextUtils;
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
	 * workExperienceInfo上下文对象
	 * rainbow 2015-09-11
	 */
	private Context mContext = null;
	private QActivity mActivity ;
	private ArrayList<EntryManageWorkExperienceInfoEntity> data = new ArrayList<EntryManageWorkExperienceInfoEntity>();
	private UIAdapter uiAdapter;
	private Resources resources;
	private EntryManagementAddWorkExperienceInfoDialog entryAddInfo;//声明变量
	
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
			GradientDrawable shapeDrawable = (GradientDrawable) holder.v_line.getBackground();
			shapeDrawable.setColor(Color.parseColor("#6f93f4"));
			//开始时间
			holder.layout_workSDate=(LinearLayout) convertView.findViewById(R.id.layout_workSDate);
			holder.tv_workSDate=(TextView) convertView.findViewById(R.id.tv_workSDate);
			holder.tv_workSDate1=(TextView) convertView.findViewById(R.id.tv_workSDate1);
			//结束时间
			holder.layout_workEDate=(LinearLayout) convertView.findViewById(R.id.layout_workEDate);
			holder.tv_workEDate=(TextView) convertView.findViewById(R.id.tv_workEDate);
			holder.tv_workEDate1=(TextView) convertView.findViewById(R.id.tv_workEDate1);
			//学校
			holder.layout_workCompany=(LinearLayout) convertView.findViewById(R.id.layout_workCompany);
			holder.tv_workCompany=(TextView) convertView.findViewById(R.id.tv_workCompany);
			holder.tv_workCompany1=(TextView) convertView.findViewById(R.id.tv_workCompany1);
			//专业
			holder.layout_workPosition=(LinearLayout) convertView.findViewById(R.id.layout_workPosition);
			holder.tv_workPosition=(TextView) convertView.findViewById(R.id.tv_workPosition);
			holder.tv_workPosition1=(TextView) convertView.findViewById(R.id.tv_workPosition1);
			//收获
			holder.layout_workAchievement=(LinearLayout) convertView.findViewById(R.id.layout_workAchievement);
			holder.tv_workAchievement=(TextView) convertView.findViewById(R.id.tv_workAchievement);
			holder.tv_workAchievement1=(TextView) convertView.findViewById(R.id.tv_workAchievement1);
			//删除
			holder.btn_remove=(Button)convertView.findViewById(R.id.btn_remove);
			//编辑
			holder.btn_edit=(Button)convertView.findViewById(R.id.btn_edit);
			convertView.setTag(holder);
		}else{
			holder = (ViewHolder) convertView.getTag();
		}
		holder.tv_workSDate1.setText(entity.getSDate());
		holder.tv_workEDate1.setText(entity.getEDate());
		holder.tv_workCompany1.setText(entity.getCompany());
		holder.tv_workPosition1.setText(entity.getPosition());
		holder.tv_workAchievement1.setText(entity.getAchievement());
		//删除
		holder.btn_remove.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				DialogUtil dUtil = new DialogUtil(mActivity);
				dUtil.showDialog("您确定要删除该条工作经验？", 0, "确定", "取消",
						new DialogButtonClickCallback() {
							@Override
							public void positiveButtonClick() {
								// TODO Auto-generated method stub
								removeItem(entity);
								notifyDataSetChanged();
							}
							@Override
							public void negativeButtonClick() {
								// TODO Auto-generated method stub
							}
						});
			}
		});
				//编辑
				holder.btn_edit.setOnClickListener(new OnClickListener(){
					@Override
					public void onClick(View v) {
						entryAddInfo=new EntryManagementAddWorkExperienceInfoDialog(mActivity,R.style.dialog);
						entryAddInfo.show();
						entryAddInfo.setTitle("修改工作经验");
						entryAddInfo.et_sDate.setText(entity.getSDate());
						entryAddInfo.et_eDate.setText(entity.getEDate());
						entryAddInfo.et_workCompany.setText(entity.getCompany());
						entryAddInfo.et_workPosition.setText(entity.getPosition());
						entryAddInfo.et_workAchievement.setText(entity.getAchievement());
						entryAddInfo.setButtonYes("确定");
						entryAddInfo.setButtonNo("取消");
						entryAddInfo.setOnClickButton(new AddExperienceInfoDialogButtonClickCallback(){

							@Override
							public void positiveButtonClick() {}
							@Override
							public void negativeButtonClick() {}
							@Override
							public void getAddExperienceInfoDialogButtonClickCallback(String addSDate, String addEDate,
									String addInfo1, String addInfo2,String addAchievement) {
								EntryManageWorkExperienceInfoEntity temp =new EntryManageWorkExperienceInfoEntity();
								 boolean convertSuccess=true;
								 boolean convertSuccess1=true;
									SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
									try {
										if(TextUtils.isEmpty(addSDate)){											
										}else{
											format.setLenient(false);
											format.parse(addSDate);
										}
									} catch (java.text.ParseException e) {
										// TODO Auto-generated catch block
										convertSuccess = false;
										e.printStackTrace();
									}
									try {
										if(TextUtils.isEmpty(addEDate)){											
										}else{
											format.setLenient(false);
											format.parse(addEDate);
										}
									} catch (java.text.ParseException e) {
										// TODO Auto-generated catch block
										convertSuccess1 = false;
										e.printStackTrace();
									}
									if (convertSuccess == false) {
										Toast.show(mActivity, "开始时间格式为：2010-01-01");
										return;
									} else if(convertSuccess1 == false){
										Toast.show(mActivity, "结束时间格式为：2010-01-01");
										return;
									}else{
										temp.setSDate(addSDate);
										temp.setEDate(addEDate);
										temp.setCompany(addInfo1);
										temp.setPosition(addInfo2);
										temp.setAchievement(addAchievement);
										for(int i=0;i<getCount();i++){
											if(entity==getItem(i)){
												updateItem(i,temp);
											}
										}
										notifyDataSetChanged();	
										entryAddInfo.dismiss();
									}
							}
						});
					} 
					
				});
		return convertView;
	}
	public class ViewHolder {
		LinearLayout layout_workExperienceInfo;
		View v_line;
		LinearLayout layout_workSDate;
		TextView tv_workSDate;
		TextView tv_workSDate1;
		
		LinearLayout layout_workEDate;
		TextView tv_workEDate;
		TextView tv_workEDate1;
		
		LinearLayout layout_workCompany;
		TextView tv_workCompany;
		TextView tv_workCompany1;
		
		LinearLayout layout_workPosition;
		TextView tv_workPosition;
		TextView tv_workPosition1;
		
		LinearLayout layout_workAchievement;
		TextView tv_workAchievement;
		TextView tv_workAchievement1;
		
		Button btn_remove;
		Button btn_edit;
	}

} 
