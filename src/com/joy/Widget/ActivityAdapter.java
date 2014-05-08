package com.joy.Widget;

import gejw.android.quickandroid.ui.adapter.UIAdapter;

import java.util.ArrayList;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.joy.R;
import com.joy.Utils.Constants;
import com.joy.json.model.ActivityDetailEntity;
import com.nostra13.universalimageloader.core.ImageLoader;

public class ActivityAdapter extends BaseAdapter {

	/**
	 * 上下文对象
	 */
	private Context mContext = null;
	private ArrayList<ActivityDetailEntity> data = new ArrayList<ActivityDetailEntity>();
	private UIAdapter uiAdapter;
	
	/**
	 * @param mainActivity
	 */
	public ActivityAdapter(Context ctx) {
		mContext = ctx;
		uiAdapter = UIAdapter.getInstance(ctx);
	}

	public void addItem(ActivityDetailEntity entity) {
		data.add(entity);
	}

	public void addSeparatorItem(ActivityDetailEntity entity) {
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
		ActivityDetailEntity entity = data.get(position);

		ViewHolder holder;
		if (convertView == null) {
			convertView = LayoutInflater.from(mContext).inflate(
					R.layout.activity_list_item, parent, false);
			holder = new ViewHolder();
			
			holder.iv_activity = (ImageView) convertView
					.findViewById(R.id.iv_activity);
			uiAdapter.setMargin(holder.iv_activity, 60,
					uiAdapter.CalcHeight(60, 133, 94), 0, 0, 0, 0);

			holder.tv_activityname = (TextView) convertView.findViewById(R.id.tv_activityname);
			uiAdapter.setTextSize(holder.tv_activityname, 20);
			uiAdapter.setMargin(holder.tv_activityname, LayoutParams.WRAP_CONTENT,
					LayoutParams.WRAP_CONTENT, 0, 0, 0, 0);

			holder.iv_actpicture = (ImageView) convertView
					.findViewById(R.id.iv_actpicture);
			uiAdapter.setMargin(holder.iv_actpicture, 200,
					150, 0, 0, 0, 0);
			
			holder.tv_detail = (TextView) convertView
					.findViewById(R.id.tv_detail);
			uiAdapter.setTextSize(holder.tv_detail, 20);
			uiAdapter.setMargin(holder.tv_detail, LayoutParams.WRAP_CONTENT,
					LayoutParams.WRAP_CONTENT, 0, 0, 10, 0);

			holder.btn_actjoin = (Button) convertView
					.findViewById(R.id.btn_actjoin);
			uiAdapter.setTextSize(holder.btn_actjoin, 20);
			uiAdapter.setMargin(holder.btn_actjoin, 60, 30, 0, 0, 0, 0);
			holder.btn_actjoin.setOnClickListener(clicklistener);
			
			convertView.setTag(holder);
		} else {// 有直接获得ViewHolder
			holder = (ViewHolder) convertView.getTag();
		}

		holder.tv_activityname.setText(entity.getActName());
		
		if (entity.getActPicturePath() != null) {
			ImageLoader.getInstance().displayImage(
					entity.getActPicturePath(),
					holder.iv_actpicture);
		}
		
		holder.tv_detail.setText(Html.fromHtml(entity.getContent()));
		
		return convertView;
	}

	OnClickListener clicklistener = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			
		}
	};
	
	public class ViewHolder {
		ImageView iv_activity;
		TextView tv_activityname;
		ImageView iv_actpicture;
		TextView tv_detail;
		Button btn_actjoin;
	}
}
