package com.joy.Widget;

import gejw.android.quickandroid.ui.adapter.UIAdapter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.joy.R;
import com.joy.json.model.UserPointsHisEntity;

public class HispointsAdapter extends BaseAdapter {

	/**
	 * 上下文对象
	 */
	private Context mContext = null;
	private ArrayList<UserPointsHisEntity> data = new ArrayList<UserPointsHisEntity>();
	private UIAdapter uiAdapter;
	private Resources resources;
	
	/**
	 * @param mainActivity
	 */
	public HispointsAdapter(Context ctx) {
		mContext = ctx;
		uiAdapter = UIAdapter.getInstance(ctx);
		resources = ctx.getResources();
	}

	public void addItem(UserPointsHisEntity entity) {
		data.add(entity);
	}

	public void addSeparatorItem(UserPointsHisEntity entity) {
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
		UserPointsHisEntity entity = data.get(position);

		ViewHolder holder;
		if (convertView == null) {
			convertView = LayoutInflater.from(mContext).inflate(
					R.layout.points_list_item, parent, false);
			holder = new ViewHolder();

			holder.layout_pointshis = (LinearLayout) convertView
					.findViewById(R.id.layout_pointshis);
			if (position%2 == 1) {
				holder.layout_pointshis.setBackgroundColor(Color.WHITE);
			} else {
				holder.layout_pointshis.setBackgroundColor(resources.getColor(R.color.points_list_two));
			}
			
			// actiontime
			holder.tv_actiontime = (TextView) convertView
					.findViewById(R.id.tv_actiontime);
			uiAdapter.setTextSize(holder.tv_actiontime, 16);
			uiAdapter.setMargin(holder.tv_actiontime, LayoutParams.WRAP_CONTENT,
					LayoutParams.WRAP_CONTENT, 10, 10, 0, 0);

			// points
			holder.tv_points = (TextView) convertView.findViewById(R.id.tv_points);
			uiAdapter.setTextSize(holder.tv_points, 16);
			uiAdapter.setMargin(holder.tv_points, LayoutParams.WRAP_CONTENT,
					LayoutParams.WRAP_CONTENT, 10, 11, 10, 0);

			// remark
			holder.tv_remark = (TextView) convertView
					.findViewById(R.id.tv_remark);
			uiAdapter.setTextSize(holder.tv_remark, 14);
			uiAdapter.setMargin(holder.tv_remark, LayoutParams.WRAP_CONTENT,
					LayoutParams.WRAP_CONTENT, 20, 10, 0, 10);

			convertView.setTag(holder);
		} else {// 有直接获得ViewHolder
			holder = (ViewHolder) convertView.getTag();
		}

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		holder.tv_actiontime.setText(sdf.format(new Date(Long.parseLong(entity.getActionTime().substring(6, 19)))));
		holder.tv_points.setText(entity.getPoints());
		holder.tv_remark.setText("备注说明：" + entity.getRemark());
		
		return convertView;
	}

	public class ViewHolder {
		LinearLayout layout_pointshis;
		TextView tv_actiontime;
		TextView tv_points;
		TextView tv_remark;
	}
}
