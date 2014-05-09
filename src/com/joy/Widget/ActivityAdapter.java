package com.joy.Widget;

import gejw.android.quickandroid.ui.adapter.UIAdapter;
import gejw.android.quickandroid.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.app.Activity;
import android.content.Context;
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
import com.joy.json.JsonCommon;
import com.joy.json.JsonCommon.OnOperationListener;
import com.joy.json.model.ActivityDetailEntity;
import com.joy.json.model.ActjoinEntity;
import com.joy.json.operation.OperationBuilder;
import com.joy.json.operation.impl.ActjoinOp;
import com.nostra13.universalimageloader.core.ImageLoader;

public class ActivityAdapter extends BaseAdapter {

	/**
	 * 上下文对象
	 */
	private Context mContext = null;
	private Activity mActivity = null;
	private ArrayList<ActivityDetailEntity> data = new ArrayList<ActivityDetailEntity>();
	private UIAdapter uiAdapter;
	
	/**
	 * @param mainActivity
	 */
	public ActivityAdapter(Activity activity, Context ctx) {
		mActivity = activity;
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
					125, 5, 5, 5, 5);
			
			holder.tv_detail = (TextView) convertView
					.findViewById(R.id.tv_detail);
			uiAdapter.setTextSize(holder.tv_detail, 20);
			uiAdapter.setMargin(holder.tv_detail, LayoutParams.WRAP_CONTENT,
					LayoutParams.WRAP_CONTENT, 0, 5, 10, 5);

			holder.btn_actjoin = (Button) convertView
					.findViewById(R.id.btn_actjoin);
			uiAdapter.setTextSize(holder.btn_actjoin, 20);
			uiAdapter.setMargin(holder.btn_actjoin, 120, 35, 0, 0, 10, 5);
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
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String content = "活动开始时间：" + sdf.format(new Date(Long.parseLong(entity.getStartTime().substring(6, 19)))) + "\n"
				+ "活动地点：" + entity.getLocationAddr() + "\n"
				+ "报名截止日期：" + sdf.format(new Date(Long.parseLong(entity.getDeadLine().substring(6, 19)))) + "\n"
				+ "已报名人数/报名人数限制：" + entity.getCurrentCount() + "/" + entity.getLimitCount();
		holder.tv_detail.setText(content);
		
		holder.btn_actjoin.setTag(entity);
		
		return convertView;
	}

	OnClickListener clicklistener = new OnClickListener() {
		
		@Override
		public void onClick(final View v) {
			ActivityDetailEntity entity = (ActivityDetailEntity) v.getTag();
			OperationBuilder builder = new OperationBuilder().append(new ActjoinOp(),
					entity);
			OnOperationListener listener = new OnOperationListener() {
				@Override
				public void onOperationFinished(List<Object> resList) {
					if (mActivity.isFinishing()) {
						return;
					}
					if (resList == null) {
						Toast.show(mContext, "连接超时");
						return;
					}
					ActjoinEntity entity = (ActjoinEntity) resList.get(0);
					int retobj = entity.getRetobj();
					if (retobj == 0) {
						Toast.show(mContext, "报名失败！");
						return;
					} else {
						Toast.show(mContext, "报名成功！");
						v.setClickable(false);
						v.setBackgroundColor(mActivity.getResources().getColor(R.color.welfare_item_tab_bg));
						notifyDataSetChanged();
					}
				}

				@Override
				public void onOperationError(Exception e) {
					e.printStackTrace();
				}
			};

			JsonCommon task = new JsonCommon(mContext, builder, listener,
					JsonCommon.PROGRESSCOMMIT);
			task.execute();
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
