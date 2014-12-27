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
import com.joy.Activity.PayrollDetailActivity;
import com.joy.json.model.PayrollBriefEntity;
import com.joy.json.model.PayrollEntity;

public class PayrollAdapter extends BaseAdapter {

	/**
	 * contact上下文对象
	 * ryan zhou 2014-11-12
	 */
	private Context mContext = null;
	private Activity mActivity = null;
	private ArrayList<PayrollBriefEntity> data = new ArrayList<PayrollBriefEntity>();
	private UIAdapter uiAdapter;
	private Resources resources;
	
	/**
	 * @param mainActivity
	 */
	public PayrollAdapter(Activity activity, Context ctx) {
		mActivity = activity;
		mContext = ctx;
		uiAdapter = UIAdapter.getInstance(ctx);
		resources = ctx.getResources();
	}

	public void addItem(PayrollBriefEntity entity) {
		data.add(entity);
	}

	public void addSeparatorItem(PayrollBriefEntity entity) {
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
		final PayrollBriefEntity entity = data.get(position);

		ViewHolder holder;
		if (convertView == null) {
			convertView = LayoutInflater.from(mContext).inflate(
					R.layout.payroll_list_item, parent, false);
			holder = new ViewHolder();

			holder.layout_payroll = (LinearLayout) convertView
					.findViewById(R.id.layout_payroll);
			uiAdapter.setPadding(holder.layout_payroll, 0, 15, 0, 15);
			
			holder.layout_wages = (LinearLayout) convertView
					.findViewById(R.id.layout_wages);
			uiAdapter.setMargin(holder.layout_wages, LayoutParams.WRAP_CONTENT,
					LayoutParams.WRAP_CONTENT, 30, 0, 20, 0);
			uiAdapter.setPadding(holder.layout_wages, 30, 5, 30, 5);
			
			// real wages 实发工资
			holder.tv_realwages = (TextView) convertView
					.findViewById(R.id.tv_realwages);
			uiAdapter.setTextSize(holder.tv_realwages, 28);
			TextPaint tp = holder.tv_realwages.getPaint(); 
			tp.setFakeBoldText(true); 
			uiAdapter.setMargin(holder.tv_realwages, LayoutParams.WRAP_CONTENT,
					LayoutParams.WRAP_CONTENT, 0, 0, 0, 0);
			
			//real wages title  
			holder.tv_realwagestitle = (TextView) convertView
					.findViewById(R.id.tv_realwagestitle);
			uiAdapter.setTextSize(holder.tv_realwagestitle, 18);
			uiAdapter.setMargin(holder.tv_realwagestitle, LayoutParams.WRAP_CONTENT,
					LayoutParams.WRAP_CONTENT, 0, 0, 0, 0);

			// payroll period   工资发放日期
			holder.tv_period = (TextView) convertView
					.findViewById(R.id.tv_period);
			uiAdapter.setTextSize(holder.tv_period, 24);
			uiAdapter.setPadding(holder.tv_period, 0, 0, 30, 0);


			convertView.setTag(holder);
		} else {// 有直接获得ViewHolder
			holder = (ViewHolder) convertView.getTag();
		}
		holder.tv_realwages.setText("¥ " + entity.getRealwages());
		holder.tv_period.setText(entity.getPeriod() + "   >");
		holder.layout_payroll.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.putExtra(PayrollDetailActivity.Period, entity.getPeriod());
				intent.setClass(mContext, PayrollDetailActivity.class);
				mActivity.startActivity(intent);
			}
		});
		
		return convertView;
	}

	public class ViewHolder {
		LinearLayout layout_payroll;
		LinearLayout layout_wages;
		TextView tv_realwages;
		TextView tv_realwagestitle;
		TextView tv_period;
	}
}
