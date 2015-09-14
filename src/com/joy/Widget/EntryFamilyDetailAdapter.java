package com.joy.Widget;

import gejw.android.quickandroid.QActivity;
import gejw.android.quickandroid.ui.adapter.UIAdapter;
import java.util.ArrayList;

import com.joy.R;
import com.joy.Dialog.DialogUtil;
import com.joy.Dialog.EntryManagementAddFamilyInfoDialog;
import com.joy.Dialog.DialogUtil.AddInfoDialogButtonClickCallback;
import com.joy.Dialog.DialogUtil.DialogButtonClickCallback;
import com.joy.json.model.EntryManageEducationInfoEntity;
import com.joy.json.model.EntryManageFamilyInfoEntity;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class EntryFamilyDetailAdapter extends BaseAdapter{
	/**
	 * FamilyInfo上下文对象
	 * rainbow 2015-09-10
	 */
	private Context mContext = null;
	private QActivity mActivity ;
	private ArrayList<EntryManageFamilyInfoEntity> data = new ArrayList<EntryManageFamilyInfoEntity>();
	private UIAdapter uiAdapter;
	private Resources resources;
	private EntryManagementAddFamilyInfoDialog entryAddInfo;//声明变量
	
	/**
	 * @param mainActivity
	 */
	public EntryFamilyDetailAdapter(QActivity activity, Context ctx) {
		mActivity = activity;
		mContext = ctx;
		uiAdapter = UIAdapter.getInstance(ctx);
		resources = ctx.getResources();
	}
	public void addItem(EntryManageFamilyInfoEntity entity) {
		data.add(entity);
	}
	public void removeItem(EntryManageFamilyInfoEntity entity) {
		data.remove(entity);
	}
	
	public void updateItem(int position, EntryManageFamilyInfoEntity entity) {
		data.set(position, entity);
	}
	public void addSeparatorItem(EntryManageFamilyInfoEntity entity) {
		data.add(entity);
	}
	public ArrayList<EntryManageFamilyInfoEntity> getData() {
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
		final EntryManageFamilyInfoEntity entity = data.get(position);
		ViewHolder holder;
		if(convertView==null){
			convertView=LayoutInflater.from(mContext).inflate(R.layout.activity_entry_familydetail, parent, false);
			holder = new ViewHolder();
			//横线
			holder.v_line = (View) convertView.findViewById(R.id.v_line);
			GradientDrawable shapeDrawable = (GradientDrawable) holder.v_line.getBackground();
			shapeDrawable.setColor(Color.parseColor("#6f93f4"));
			//姓名
			holder.layout_familyName=(LinearLayout) convertView.findViewById(R.id.layout_familyName);
			holder.tv_familyName=(TextView) convertView.findViewById(R.id.tv_familyName);
			holder.tv_familyName1=(TextView) convertView.findViewById(R.id.tv_familyName1);
			//生日
			holder.layout_familyBirthday=(LinearLayout) convertView.findViewById(R.id.layout_familyBirthday);
			holder.tv_familyBirthday=(TextView) convertView.findViewById(R.id.tv_familyBirthday);
			holder.tv_familyBirthday1=(TextView) convertView.findViewById(R.id.tv_familyBirthday1);
			//地址
			holder.layout_familyAddress=(LinearLayout) convertView.findViewById(R.id.layout_familyAddress);
			holder.tv_familyAddress=(TextView) convertView.findViewById(R.id.tv_familyAddress);
			holder.tv_familyAddress1=(TextView) convertView.findViewById(R.id.tv_familyAddress1);
			//关系
			holder.layout_familyRelationShip=(LinearLayout) convertView.findViewById(R.id.layout_familyRelationShip);
			holder.tv_familyRelationShip=(TextView) convertView.findViewById(R.id.tv_familyRelationShip);
			holder.tv_familyRelationShip1=(TextView) convertView.findViewById(R.id.tv_familyRelationShip1);
			//删除
			holder.btn_remove=(Button)convertView.findViewById(R.id.btn_remove);
			//编辑
			holder.btn_edit=(Button)convertView.findViewById(R.id.btn_edit);
			convertView.setTag(holder);
		}else{
			holder = (ViewHolder) convertView.getTag();
		}
		holder.tv_familyName1.setText(entity.getName());
		holder.tv_familyBirthday1.setText(entity.getBirthday());
		holder.tv_familyAddress1.setText(entity.getAddress());
		holder.tv_familyRelationShip1.setText(entity.getRelationShip());
		//删除
		holder.btn_remove.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				DialogUtil dUtil = new DialogUtil(mActivity);
				dUtil.showDialog("您确定要删除该条家庭信息？", 0, "确定", "取消",
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
				entryAddInfo=new EntryManagementAddFamilyInfoDialog(mActivity,R.style.dialog);
				entryAddInfo.show();
				entryAddInfo.setTitle("修改家庭信息");
				entryAddInfo.et_familyName.setText(entity.getName());
				entryAddInfo.et_familyBirthday.setText(entity.getBirthday());
				entryAddInfo.et_familyAddress.setText(entity.getAddress());
				entryAddInfo.et_faimlyRelation.setText(entity.getRelationShip());
				entryAddInfo.setButtonYes("确定");
				entryAddInfo.setButtonNo("取消");
				entryAddInfo.setOnClickButton(new AddInfoDialogButtonClickCallback(){

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
						for(int i=0;i<getCount();i++){
							if(entity==getItem(i)){
								updateItem(i,temp);
							}
						}
						
						notifyDataSetChanged();	
						entryAddInfo.dismiss();
					}
				});
			} 
			
		});
		return convertView;
	}
	public class ViewHolder {
		LinearLayout layout_familyInfo;
		View v_line;
		LinearLayout layout_familyName;
		TextView tv_familyName;
		TextView tv_familyName1;
		
		LinearLayout layout_familyBirthday;
		TextView tv_familyBirthday;
		TextView tv_familyBirthday1;
		
		LinearLayout layout_familyAddress;
		TextView tv_familyAddress;
		TextView tv_familyAddress1;
		
		LinearLayout layout_familyRelationShip;
		TextView tv_familyRelationShip;
		TextView tv_familyRelationShip1;
		
		Button btn_remove;
		Button btn_edit;
		
	}

}
