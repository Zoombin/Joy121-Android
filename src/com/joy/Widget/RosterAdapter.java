package com.joy.Widget;

import gejw.android.quickandroid.ui.adapter.UIAdapter;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.text.TextPaint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.joy.R;
import com.joy.json.model.RosterEntity;

public class RosterAdapter extends BaseAdapter {

	/**
	 * contact上下文对象
	 * ryan zhou 2014-11-03
	 */
	private Context mContext = null;
	private Activity mActivity = null;
	private ArrayList<RosterEntity> data = new ArrayList<RosterEntity>();
	private UIAdapter uiAdapter;
	private Resources resources;
	public String personname;
	
	
	/**
	 * @param mainActivity
	 */
	public RosterAdapter(Activity activity, Context ctx) {
		mActivity = activity;
		mContext = ctx;
		uiAdapter = UIAdapter.getInstance(ctx);
		resources = ctx.getResources();
	}

	public void addItem(RosterEntity entity) {
		data.add(entity);
	}

	public void addSeparatorItem(RosterEntity entity) {
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
		final RosterEntity entity = data.get(position);

		ViewHolder holder;
		if (convertView == null) {
			convertView = LayoutInflater.from(mContext).inflate(
					R.layout.roster_list_item, parent, false);
			holder = new ViewHolder();

			holder.layout_roster = (RelativeLayout) convertView
					.findViewById(R.id.layout_roster);
			// ranking
			holder.tv_roster_ranking = (TextView) convertView
					.findViewById(R.id.tv_roster_ranking);
			uiAdapter.setTextSize(holder.tv_roster_ranking, 22);
			uiAdapter.setMargin(holder.tv_roster_ranking, 60, LayoutParams.WRAP_CONTENT, 5, 0, 0, 0);
			
			// person name
			holder.tv_roster_personname = (TextView) convertView
					.findViewById(R.id.tv_roster_personname);
			uiAdapter.setTextSize(holder.tv_roster_personname, 22);
			//TextPaint tp = holder.tv_roster_personname.getPaint(); 
			//tp.setFakeBoldText(true); 
			uiAdapter.setMargin(holder.tv_roster_personname, LayoutParams.WRAP_CONTENT,
					LayoutParams.WRAP_CONTENT, 10, 0, 0, 0);

			// score
			holder.tv_roster_score = (TextView) convertView
					.findViewById(R.id.tv_roster_score);
			uiAdapter.setTextSize(holder.tv_roster_score, 20);
			uiAdapter.setMargin(holder.tv_roster_score, LayoutParams.WRAP_CONTENT,
					LayoutParams.WRAP_CONTENT, 0, 0, 0, 0);

			// department
			holder.tv_roster_comdep = (TextView) convertView
					.findViewById(R.id.tv_roster_comdep);
			uiAdapter.setTextSize(holder.tv_roster_comdep, 16);
			uiAdapter.setMargin(holder.tv_roster_comdep, LayoutParams.WRAP_CONTENT,
					LayoutParams.WRAP_CONTENT, 10, 0, 0, 0);
			
			// award
			holder.tv_roster_award = (TextView) convertView
					.findViewById(R.id.tv_roster_award);
			uiAdapter.setTextSize(holder.tv_roster_award, 20);
			//uiAdapter.setMargin(holder.tv_award, LayoutParams.WRAP_CONTENT,
			//		LayoutParams.WRAP_CONTENT, 30, 10, 0, 5);

			convertView.setTag(holder);
		} else {// 有直接获得ViewHolder
			holder = (ViewHolder) convertView.getTag();
		}
		holder.tv_roster_ranking.setText(entity.getPerformanceSeq());
		holder.tv_roster_personname.setText(entity.getUserName());
		holder.tv_roster_score.setText(entity.getPerformanceScore());
		holder.tv_roster_comdep.setText(entity.getCostCenterNo());
		holder.tv_roster_award.setText(entity.getPerformancePoints());
		/*holder.layout_roster.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.putExtra(ContactDetailActivity.ContactDetails, entity);
				intent.setClass(mContext, ContactDetailActivity.class);
				mActivity.startActivity(intent);
			}
		});*/
		
		return convertView;
	}

	public class ViewHolder {
		RelativeLayout layout_roster;
		TextView tv_roster_ranking;
		TextView tv_roster_personname;
		TextView tv_roster_score;
		TextView tv_roster_comdep;
		TextView tv_roster_award;
	}
}
