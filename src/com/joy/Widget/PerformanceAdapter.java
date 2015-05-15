package com.joy.Widget;

import gejw.android.quickandroid.ui.adapter.UIAdapter;

import java.text.DecimalFormat;
import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.ClipDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.GradientDrawable;
import android.text.TextPaint;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;

import com.joy.Activity.RosterActivity;
import com.joy.Utils.CustomProgressBar;
import android.widget.TextView;

import com.joy.R;
import com.joy.json.model.PerformanceEntity;

public class PerformanceAdapter extends BaseAdapter {

	/**
	 * performance上下文对象
	 * ryan zhou 2015-01-12
	 */
	private Context mContext = null;
	private Activity mActivity = null;
	private ArrayList<PerformanceEntity> data = new ArrayList<PerformanceEntity>();
	private UIAdapter uiAdapter;
	private Resources resources;
	
	/**
	 * @param mainActivity
	 */
	public PerformanceAdapter(Activity activity, Context ctx) {
		mActivity = activity;
		mContext = ctx;
		uiAdapter = UIAdapter.getInstance(ctx);
		resources = ctx.getResources();
	}

	public void addItem(PerformanceEntity entity) {
		data.add(entity);
	}

	public void addSeparatorItem(PerformanceEntity entity) {
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
		final PerformanceEntity entity = data.get(position);

		ViewHolder holder;
		if (convertView == null) {
			convertView = LayoutInflater.from(mContext).inflate(
					R.layout.performance_list_item, parent, false);
			holder = new ViewHolder();
			//横线
			holder.v_line = (View) convertView
					.findViewById(R.id.v_line);
			if (entity.getPeriod() != null && entity.getPeriod().length() == 4) {
				GradientDrawable shapeDrawable = (GradientDrawable) holder.v_line.getBackground();
				shapeDrawable.setColor(Color.parseColor("#fca433"));	
			} else {
				if (entity.getPeriod().contains("Q")) {
					GradientDrawable shapeDrawable = (GradientDrawable) holder.v_line.getBackground();
					shapeDrawable.setColor(Color.parseColor("#58d22f"));	
				} else {
					GradientDrawable shapeDrawable = (GradientDrawable) holder.v_line.getBackground();
					shapeDrawable.setColor(Color.parseColor("#6f93f4"));
				}
			}
			// 期间
			holder.tv_period= (TextView) convertView
					.findViewById(R.id.tv_period);
			TextPaint tp_period = holder.tv_period.getPaint(); 
			tp_period.setFakeBoldText(true); 
			uiAdapter.setTextSize(holder.tv_period, 18);
			uiAdapter.setPadding(holder.tv_period, 0, 0, 0, 0);
			
			//奖励积分
			holder.tv_score = (TextView) convertView
					.findViewById(R.id.tv_score);
			TextPaint tp_award = holder.tv_score.getPaint(); 
			tp_award.setFakeBoldText(true); 
			uiAdapter.setTextSize(holder.tv_score, 30);
			holder.tv_score.setTextColor(Color.parseColor("#FF0000"));
			uiAdapter.setPadding(holder.tv_score, 0, 0, 0, 0);

			holder.layout_performance = (LinearLayout) convertView
					.findViewById(R.id.layout_performance);
			holder.layout_performance.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					/*Intent intent = new Intent();
					intent.putExtra(RosterActivity.entity, entity);
					intent.setClass(mContext, RosterActivity.class);
					mActivity.startActivity(intent);*/
				}
			});
			
			convertView.setTag(holder);
		} else {// 有直接获得ViewHolder
			holder = (ViewHolder) convertView.getTag();
		}
		holder.tv_period.setText(entity.getReportName());
		holder.tv_score.setText(entity.getPerformanceScore());
		return convertView;
	}

	public class ViewHolder {
		LinearLayout layout_performance;
		View v_line;
		//CustomProgressBar progressbar;
		TextView tv_period;
		TextView tv_score;
	}
}
