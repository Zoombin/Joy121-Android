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
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.joy.R;
import com.joy.json.model.AttendanceEntity;

public class AttendanceAdapter extends BaseAdapter {

	/**
	 * contact上下文对象
	 * ryan zhou 2014-12-12
	 */
	private Context mContext = null;
	private Activity mActivity = null;
	private ArrayList<AttendanceEntity> data = new ArrayList<AttendanceEntity>();
	private UIAdapter uiAdapter;
	private Resources resources;
	
	/**
	 * @param mainActivity
	 */
	public AttendanceAdapter(Activity activity, Context ctx) {
		mActivity = activity;
		mContext = ctx;
		uiAdapter = UIAdapter.getInstance(ctx);
		resources = ctx.getResources();
	}

	public void addItem(AttendanceEntity entity) {
		data.add(entity);
	}

	public void addSeparatorItem(AttendanceEntity entity) {
		data.add(entity);
	}

	@Override
	public int getCount() {
		return data.size();
	}
	
	public void clear(){
		data.clear();
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
		final AttendanceEntity entity = data.get(position);

		ViewHolder holder;
		if (convertView == null) {
			convertView = LayoutInflater.from(mContext).inflate(
					R.layout.attendance_list_item, parent, false);
			holder = new ViewHolder();

			holder.layout_attendance = (LinearLayout) convertView
					.findViewById(R.id.layout_attendance);
			uiAdapter.setPadding(holder.layout_attendance, 10, 5, 10, 5);
			
			// punch time 打卡时间
			holder.tv_punchtime = (TextView) convertView.findViewById(R.id.tv_punchtime);
			uiAdapter.setTextSize(holder.tv_punchtime, 20);
			//TextPaint tp = holder.tv_punchtime.getPaint(); 
			//tp.setFakeBoldText(true); 
			uiAdapter.setMargin(holder.tv_punchtime, LayoutParams.WRAP_CONTENT,
					LayoutParams.WRAP_CONTENT, 0, 0, 0, 0);
			uiAdapter.setPadding(holder.tv_punchtime, 10, 0, 0, 0);
			
			//punch type 打卡类型
			holder.tv_punchtype = (TextView) convertView.findViewById(R.id.tv_punchtype);
			uiAdapter.setTextSize(holder.tv_punchtype, 20);
			uiAdapter.setMargin(holder.tv_punchtype, LayoutParams.WRAP_CONTENT,
					LayoutParams.WRAP_CONTENT, 0, 0, 0, 0);
			uiAdapter.setPadding(holder.tv_punchtype, 0, 0, 10, 0);


			convertView.setTag(holder);
		} else {// 有直接获得ViewHolder
			holder = (ViewHolder) convertView.getTag();
		}
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
		holder.tv_punchtime.setText(format.format(new Date(Long.parseLong(entity.getPunchTime().substring(6, 19)))));
		
		holder.tv_punchtype.setText(entity.getPunchType());
		if ("0".equals(entity.getPunchType())) {
			holder.tv_punchtype.setText("上班打卡");
		} else if ("1".equals(entity.getPunchType())) {
			holder.tv_punchtype.setText("下班打卡");
		}
		/*holder.layout_payroll.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.putExtra(PayrollDetailActivity.Period, entity.getPeriod());
				intent.setClass(mContext, PayrollDetailActivity.class);
				mActivity.startActivity(intent);
			}
		});*/
		
		return convertView;
	}

	public class ViewHolder {
		LinearLayout layout_attendance;
		TextView tv_punchtime;
		TextView tv_punchtype;
	}
}
