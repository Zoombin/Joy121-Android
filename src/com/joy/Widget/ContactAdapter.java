package com.joy.Widget;

import gejw.android.quickandroid.ui.adapter.UIAdapter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
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
import com.joy.json.model.ContactEntity;

public class ContactAdapter extends BaseAdapter {

	/**
	 * contact上下文对象
	 * ryan zhou 2014-11-03
	 */
	private Context mContext = null;
	private Activity mActivity = null;
	private ArrayList<ContactEntity> data = new ArrayList<ContactEntity>();
	private UIAdapter uiAdapter;
	private Resources resources;
	public String personname;
	
	
	/**
	 * @param mainActivity
	 */
	public ContactAdapter(Activity activity, Context ctx) {
		mActivity = activity;
		mContext = ctx;
		uiAdapter = UIAdapter.getInstance(ctx);
		resources = ctx.getResources();
	}

	public void addItem(ContactEntity entity) {
		data.add(entity);
	}

	public void addSeparatorItem(ContactEntity entity) {
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
		final ContactEntity entity = data.get(position);

		ViewHolder holder;
		if (convertView == null) {
			convertView = LayoutInflater.from(mContext).inflate(
					R.layout.contact_list_item, parent, false);
			holder = new ViewHolder();

			holder.layout_contact = (LinearLayout) convertView
					.findViewById(R.id.layout_contact);
			// contact picture
			holder.iv_contactpic = (ImageView) convertView
					.findViewById(R.id.iv_contactpic);
			uiAdapter.setMargin(holder.iv_contactpic, 60, 60, 6, 6, 0, 6);
			
			// person name
			holder.tv_personname = (TextView) convertView
					.findViewById(R.id.tv_personname);
			uiAdapter.setTextSize(holder.tv_personname, 22);
			TextPaint tp = holder.tv_personname.getPaint(); 
			tp.setFakeBoldText(true); //中文粗体
			uiAdapter.setMargin(holder.tv_personname, LayoutParams.WRAP_CONTENT,
					LayoutParams.WRAP_CONTENT, 10, 5, 0, 0);

			// English name
			holder.tv_englishname = (TextView) convertView
					.findViewById(R.id.tv_englishname);
			uiAdapter.setTextSize(holder.tv_englishname, 22);
			uiAdapter.setMargin(holder.tv_englishname, LayoutParams.WRAP_CONTENT,
					LayoutParams.WRAP_CONTENT, 10, 5, 0, 0);

			// department
			holder.tv_comdep = (TextView) convertView
					.findViewById(R.id.tv_comdep);
			uiAdapter.setTextSize(holder.tv_comdep, 16);
			uiAdapter.setMargin(holder.tv_comdep, LayoutParams.WRAP_CONTENT,
					LayoutParams.WRAP_CONTENT, 30, 10, 0, 5);

			convertView.setTag(holder);
		} else {// 有直接获得ViewHolder
			holder = (ViewHolder) convertView.getTag();
		}

		holder.tv_personname.setText(entity.getPersonName());
		personname = entity.getPersonName();
		holder.tv_englishname.setText(entity.getEnglishName());
		holder.tv_comdep.setText(entity.getComDep());
		holder.layout_contact.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.putExtra(ContactDetailActivity.ContactDetails, entity);
				intent.setClass(mContext, ContactDetailActivity.class);
				mActivity.startActivity(intent);
			}
		});
		
		return convertView;
	}

	public class ViewHolder {
		LinearLayout layout_contact;
		ImageView iv_contactpic;
		TextView tv_personname;
		TextView tv_englishname;
		TextView tv_comdep;
	}
}
