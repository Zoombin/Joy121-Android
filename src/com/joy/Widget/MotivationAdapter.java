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

public class MotivationAdapter extends BaseAdapter {

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
	public MotivationAdapter(Activity activity, Context ctx) {
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
					R.layout.motivation_list_item, parent, false);
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
			holder.tv_reportname= (TextView) convertView
					.findViewById(R.id.tv_reportname);
			TextPaint tp_period = holder.tv_reportname.getPaint(); 
			tp_period.setFakeBoldText(true); 
			uiAdapter.setTextSize(holder.tv_reportname, 18);
			uiAdapter.setPadding(holder.tv_reportname, 0, 0, 0, 0);
			
			//奖励积分
			holder.tv_award = (TextView) convertView
					.findViewById(R.id.tv_award);
			TextPaint tp_award = holder.tv_award.getPaint(); 
			tp_award.setFakeBoldText(true); 
			uiAdapter.setTextSize(holder.tv_award, 30);
			holder.tv_award.setTextColor(Color.parseColor("#FF0000"));
			uiAdapter.setPadding(holder.tv_award, 0, 0, 0, 0);
			
			// 排名
			holder.layout_ranking = (LinearLayout) convertView
					.findViewById(R.id.layout_ranking);
			uiAdapter.setPadding(holder.layout_ranking, 100, 0, 0, 0);
			
			holder.tv_score = (TextView) convertView
					.findViewById(R.id.tv_score);
			TextPaint tp_score = holder.tv_score.getPaint(); 
			tp_score.setFakeBoldText(true); 
			uiAdapter.setTextSize(holder.tv_score, 20);
			//holder.tv_score.setTextColor(Color.parseColor("#FF0000"));
			uiAdapter.setPadding(holder.tv_score, 0, 0, 0, 0);
			
			holder.tv_ranking = (TextView) convertView
					.findViewById(R.id.tv_ranking);
			TextPaint tp_rankig = holder.tv_ranking.getPaint(); 
			tp_rankig.setFakeBoldText(true); 
			uiAdapter.setTextSize(holder.tv_ranking, 20);
			uiAdapter.setPadding(holder.tv_ranking, 0, 0, 0, 0);
			
			holder.tv_total = (TextView) convertView
					.findViewById(R.id.tv_total);
			TextPaint tp_total = holder.tv_total.getPaint(); 
			tp_total.setFakeBoldText(true); 
			uiAdapter.setTextSize(holder.tv_total, 20);
			uiAdapter.setPadding(holder.tv_total, 0, 0, 0, 0);
			
			// 百分比
			holder.tv_percent = (TextView) convertView
					.findViewById(R.id.tv_percent);
			TextPaint tp_percent = holder.tv_percent.getPaint(); 
			tp_percent.setFakeBoldText(true); 
			uiAdapter.setTextSize(holder.tv_percent, 20);
			uiAdapter.setPadding(holder.tv_percent, 5, 0, 0, 0);
			
			
			//百分比条
			//holder.progressbar = (CustomProgressBar) convertView
			//		.findViewById(R.id.progressbar);
			//Drawable drawable = (GradientDrawable) holder.progressbar.getProgressDrawable();

			/*if (entity.getPeriod().contains("季")) {
				ClipDrawable d = new ClipDrawable(new ColorDrawable(Color.parseColor("#58d22f")), Gravity.LEFT, ClipDrawable.HORIZONTAL);
				holder.progressbar.setProgressDrawable(d);
				holder.progressbar.setBackgroundColor(Color.parseColor("#909090"));
			} else if (entity.getPeriod().contains("月")) {
				ClipDrawable d = new ClipDrawable(new ColorDrawable(Color.parseColor("#6f93f4")), Gravity.LEFT, ClipDrawable.HORIZONTAL);
				holder.progressbar.setProgressDrawable(d);
				holder.progressbar.setBackgroundColor(Color.parseColor("#909090"));
			}*/
			holder.layout_motivation = (LinearLayout) convertView
					.findViewById(R.id.layout_motivation);
			holder.layout_motivation.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					Intent intent = new Intent();
					intent.putExtra(RosterActivity.entity, entity);
					intent.setClass(mContext, RosterActivity.class);
					mActivity.startActivity(intent);
					//Log.d("0", "------entity = " + entity.getPeriod());
				}
			});
			
			convertView.setTag(holder);
		} else {// 有直接获得ViewHolder
			holder = (ViewHolder) convertView.getTag();
		}
		holder.tv_reportname.setText(entity.getReportName());
		holder.tv_score.setText(entity.getPerformanceScore());
		holder.tv_ranking.setText(entity.getPerformanceSeq());
		holder.tv_total.setText(entity.getTotalNum());
		holder.tv_award.setText(entity.getPerformancePoints());
		DecimalFormat df = new DecimalFormat(".00");
		double percent = Double.parseDouble(entity.getPerformanceSeq()) * 100 / Double.parseDouble(entity.getTotalNum());
		//String progressbarText = String.valueOf("排名前" + Math.round(percent)+"%");
		//String format = df.format(percent);
		//holder.progressbar.setText(progressbarText);
		//holder.progressbar.setMax(Integer.parseInt(entity.getTotal()));
		//holder.progressbar.setProgress(Integer.parseInt(entity.getRanking()));
		holder.tv_percent.setText(String.valueOf(Math.round(percent)) +"%");
		return convertView;
	}

	public class ViewHolder {
		LinearLayout layout_motivation;
		View v_line;
		//CustomProgressBar progressbar;
		TextView tv_reportname;
		LinearLayout layout_ranking;
		TextView tv_score;
		TextView tv_ranking;
		TextView tv_total;
		TextView tv_award;
		TextView tv_percent;
	}
}
