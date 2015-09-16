package com.joy.Widget;

import gejw.android.quickandroid.ui.adapter.UIAdapter;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.text.TextPaint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.joy.R;
import com.joy.Activity.ContactDetailActivity;
import com.joy.Activity.RelationContactActivity;
import com.joy.json.model.ContactEntity;
import com.joy.json.model.RelationContactEntity;

public class RelationContactAdapter extends BaseAdapter {

	/**
	 * relationContact上下文对象
	 * rainbow 2015-9-15
	 */
	private Context mContext = null;
	private Activity mActivity = null;
	private ArrayList<RelationContactEntity> data = new ArrayList<RelationContactEntity>();
	private UIAdapter uiAdapter;
	private Resources resources;
	public String personname;
	
	
	/**
	 * @param mainActivity
	 */
	public RelationContactAdapter(Activity activity, Context ctx) {
		mActivity = activity;
		mContext = ctx;
		uiAdapter = UIAdapter.getInstance(ctx);
		resources = ctx.getResources();
	}

	public void addItem(RelationContactEntity entity) {
		data.add(entity);
	}

	public void addSeparatorItem(RelationContactEntity entity) {
		data.add(entity);
	}

	@Override
	public int getCount() {
		return data.size();
	}

	@Override
	public Object getItem(int position) {
		return data.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		final RelationContactEntity entity = data.get(position);

		ViewHolder holder;
		if (convertView == null) {
			convertView = LayoutInflater.from(mContext).inflate(
					R.layout.relationcontact_list_item, parent, false);
			holder = new ViewHolder();

			holder.layout_relationContact = (LinearLayout) convertView
					.findViewById(R.id.layout_relationContact);
			// contact picture
			holder.iv_contactpic = (ImageView) convertView
					.findViewById(R.id.iv_contactpic);
			uiAdapter.setMargin(holder.iv_contactpic, 60, 60, 6, 6, 0, 6);
			
			// person name
			holder.tv_referUserName = (TextView) convertView
					.findViewById(R.id.tv_referUserName);
			uiAdapter.setTextSize(holder.tv_referUserName, 22);
			TextPaint tp = holder.tv_referUserName.getPaint(); 
			tp.setFakeBoldText(true); //中文粗体
			uiAdapter.setMargin(holder.tv_referUserName, LayoutParams.WRAP_CONTENT,
					LayoutParams.WRAP_CONTENT, 10, 5, 0, 0);
			//关系职位
			holder.tv_referRelationTypeName = (TextView) convertView
					.findViewById(R.id.tv_referRelationTypeName);
			uiAdapter.setTextSize(holder.tv_referRelationTypeName, 16);
			uiAdapter.setMargin(holder.tv_referRelationTypeName, LayoutParams.WRAP_CONTENT,
					LayoutParams.WRAP_CONTENT, 30, 10, 0, 5);

			convertView.setTag(holder);
		} else {// 有直接获得ViewHolder
			holder = (ViewHolder) convertView.getTag();
		}

		holder.tv_referUserName.setText(entity.getReferUserName());
		Log.e("tv_referRelationTypeNametv_referRelationTypeNametv_referRelationTypeName", entity.getReferRelationTypeName());
		holder.tv_referRelationTypeName.setText(entity.getReferRelationTypeName());
		holder.layout_relationContact.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.putExtra(RelationContactActivity.relationContactDetails, entity);
				intent.setClass(mContext, RelationContactActivity.class);
				mActivity.startActivity(intent);
			}
		});
		
		return convertView;
	}

	public class ViewHolder {
		LinearLayout layout_relationContact;
		ImageView iv_contactpic;
		TextView tv_referUserName;
		TextView tv_referRelationTypeName;
	}
}
