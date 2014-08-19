package com.joy.Widget;

import gejw.android.quickandroid.ui.adapter.UIAdapter;
import gejw.android.quickandroid.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.joy.JoyApplication;
import com.joy.R;
import com.joy.Activity.ActivitySubActivity;
import com.joy.Dialog.DialogUtil;
import com.joy.Dialog.DialogUtil.DialogButtonClickCallback;
import com.joy.Utils.SharedPreferencesUtils;
import com.joy.json.JsonCommon;
import com.joy.json.JsonCommon.OnOperationListener;
import com.joy.json.model.ActivityDetailEntity;
import com.joy.json.model.ActjoinEntity;
import com.joy.json.model.ActjoinEntity.Result;
import com.joy.json.model.CompAppSet;
import com.joy.json.operation.OperationBuilder;
import com.joy.json.operation.impl.ActjoinOp;
import com.nostra13.universalimageloader.core.ImageLoader;

public class ActivityAdapter extends BaseAdapter {

	/**
	 * 上下文对象
	 */
	private Context mContext = null;
	private Activity mActivity = null;
	private List<ActivityDetailEntity> data = new ArrayList<ActivityDetailEntity>();
	private UIAdapter uiAdapter;
	DialogUtil dUtil;
	public String acttype;
	int color;
	CompAppSet appSet;
	
	/***
	 * @param type
	 */
	public void setType(String type){
		acttype = type;
	}

	/**
	 * @param mainActivity
	 */
	public ActivityAdapter(Activity activity, Context ctx) {
		mActivity = activity;
		mContext = ctx;
		uiAdapter = UIAdapter.getInstance(ctx);
		dUtil = new DialogUtil(ctx);
		color = 0;
		appSet = JoyApplication.getInstance().getCompAppSet();
	}

	public void setData(List<ActivityDetailEntity> data){
		this.data = data;
		this.notifyDataSetChanged();
	}
	public void addItem(ActivityDetailEntity entity) {
		data.add(entity);
	}

	public void addSeparatorItem(ActivityDetailEntity entity) {
		data.add(entity);
	}

	public void removeAll() {
		data.clear();
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
		final ActivityDetailEntity entity = data.get(position);

		ViewHolder holder;
		if (convertView == null) {
			convertView = LayoutInflater.from(mActivity).inflate(
					R.layout.activity_list_item, parent, false);
			holder = new ViewHolder();

			holder.iv_activity = (ImageView) convertView
					.findViewById(R.id.iv_activity);
			
			uiAdapter.setMargin(holder.iv_activity, 60,
					uiAdapter.CalcHeight(60, 133, 94), 0, 0, 0, 0);

			holder.tv_activityname = (TextView) convertView
					.findViewById(R.id.tv_activityname);
			uiAdapter.setTextSize(holder.tv_activityname, 20);
			uiAdapter.setMargin(holder.tv_activityname,
					LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 0, 0,
					0, 0);

			holder.layout_activity = (LinearLayout) convertView
					.findViewById(R.id.layout_activity);

			holder.iv_actpicture = (ImageView) convertView
					.findViewById(R.id.iv_actpicture);
			uiAdapter.setMargin(holder.iv_actpicture, 200, 125, 10, 10, 20, 10);

			holder.tv_detail = (TextView) convertView
					.findViewById(R.id.tv_detail);
			uiAdapter.setTextSize(holder.tv_detail, 20);
			uiAdapter.setMargin(holder.tv_detail, LayoutParams.WRAP_CONTENT,
					LayoutParams.WRAP_CONTENT, 0, 5, 10, 5);

			holder.btn_actjoin = (Button) convertView
					.findViewById(R.id.btn_actjoin);
			uiAdapter.setTextSize(holder.btn_actjoin, 20);
			uiAdapter.setMargin(holder.btn_actjoin, 120, 35, 0, 0, 10, 5);

			convertView.setTag(holder);
		} else {// 有直接获得ViewHolder
			holder = (ViewHolder) convertView.getTag();
		}

		holder.tv_activityname.setText(entity.getActName());

		if (entity.getActPicturePath() != null) {
			ImageLoader.getInstance().displayImage(
					"http://cloud.joy121.com/files/activity/"
							+ entity.getActPicturePath(), holder.iv_actpicture);
		}

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String content = "活动时间："
				+ sdf.format(new Date(Long.parseLong(entity.getStartTime()
						.substring(6, 19))))
				+ "\n"
				+ "活动地点："
				+ entity.getLocationAddr()
				+ "\n"
				+ "报名截止："
				+ sdf.format(new Date(Long.parseLong(entity.getDeadLine()
						.substring(6, 19)))) + "\n" + "报名人数："
				+ entity.getCurrentCount() + "/" + entity.getLimitCount();
		holder.tv_detail.setText(content);

		holder.layout_activity.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.putExtra("acttype", acttype);
				intent.putExtra(ActivitySubActivity.ActivityDetails, entity);
				intent.setClass(mContext, ActivitySubActivity.class);
				mActivity.startActivity(intent);
				
				
				//SubActivityFragment subFragment = new SubActivityFragment();
				//Bundle bundle = new Bundle();  
                //bundle.putSerializable(SubActivityFragment.ActivityDetails, entity);
                //subFragment.setArguments(bundle); 
                //MainActivity.mActivity.replaceChildFragment("SubActivityFragment",subFragment,true);
			}
		});

		if (entity.getIsexprired().equals("2")) {
			holder.btn_actjoin.setClickable(false);
			holder.btn_actjoin.setBackgroundColor(mActivity.getResources()
					.getColor(R.color.btn_disable));
		} else{
			holder.btn_actjoin.setClickable(true);
			if (appSet != null) {
				try {
					color = Color.parseColor(appSet.getColor2());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (color != 0) {
				// 设置颜色
				holder.btn_actjoin.setBackgroundColor(color);
			}
		}
		
		
		holder.btn_actjoin.setText(entity.getStatus(entity.getLoginName()));
		if (entity.getIsEnabled(entity.getLoginName())) {
			holder.btn_actjoin.setClickable(true);
			holder.btn_actjoin.setTag(entity);
			//holder.btn_actjoin.setOnClickListener(clicklistener);
			
			holder.btn_actjoin.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(final View v) {
					// TODO Auto-generated method stub
					dUtil.showDialog("报名确认", 0, "确定要参加吗？", "确定", "取消", new DialogButtonClickCallback() {
						@Override
						public void positiveButtonClick() {
							// TODO Auto-generated method stub
							final ActivityDetailEntity activitydetailentity = (ActivityDetailEntity) v
									.getTag();
							singUp(v, activitydetailentity);
						}
						
						@Override
						public void negativeButtonClick() {
							// TODO Auto-generated method stub
						}
					});
				}
			});
			if (appSet != null) {
				try {
					color = Color.parseColor(appSet.getColor2());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (color != 0) {
				// 设置颜色
				holder.btn_actjoin.setBackgroundColor(color);
			}
		} else {
			holder.btn_actjoin.setClickable(false);
			holder.btn_actjoin.setBackgroundColor(mActivity.getResources()
					.getColor(R.color.btn_disable));
		}
		if (entity.getIsexprired()) {
			holder.iv_activity
					.setImageResource(R.drawable.activity_expired);
		} else {
			holder.iv_activity.setImageResource(R.drawable.activity);
		}

		return convertView;
	}

	
	OnClickListener clicklistener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			final ActivityDetailEntity activitydetailentity = (ActivityDetailEntity) v
					.getTag();
			singUp(v, activitydetailentity);
		}
	};
	private void singUp(View v,final ActivityDetailEntity activitydetailentity){
		final Button btn = (Button) v;
		OperationBuilder builder = new OperationBuilder().append(
				new ActjoinOp(), activitydetailentity);
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
				Result result = entity.getRetobj();
				if (result == null) {
					Toast.show(mContext, "报名失败！");
					return;
				} else {
					String ret = result.getResult();
					if("0".equals(ret)){
						Toast.show(mContext, "报名失败！");
						return;
					}else{
						Toast.show(mContext, "报名成功！");
						btn.setText("已报名");
						btn.setClickable(false);
						btn.setBackgroundColor(mActivity.getResources()
								.getColor(R.color.btn_disable));
						int index = data.indexOf(activitydetailentity);
						// 改变报名状态
						if (index != -1) {
							((ActivityDetailEntity) data.get(index))
									.setLoginName(SharedPreferencesUtils
											.getLoginName(JoyApplication
													.getSelf()));
						}
						notifyDataSetChanged();
					}
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
	

	public class ViewHolder {
		ImageView iv_activity;
		TextView tv_activityname;
		LinearLayout layout_activity;
		ImageView iv_actpicture;
		TextView tv_detail;
		Button btn_actjoin;
	}
}
