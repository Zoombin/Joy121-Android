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
import android.util.Log;
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
import com.joy.Activity.ActivityActivity;
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
	boolean cancel = false;
	
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
			uiAdapter.setTextSize(holder.tv_activityname, 18);
			uiAdapter.setMargin(holder.tv_activityname,
					LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 0, 0,
					0, 0);

			holder.layout_activity = (LinearLayout) convertView
					.findViewById(R.id.layout_activity);

			holder.iv_actpicture = (ImageView) convertView
					.findViewById(R.id.iv_actpicture);
			uiAdapter.setMargin(holder.iv_actpicture, 200, 125, 10, 10, 10, 10);

			holder.tv_startitme = (TextView) convertView
					.findViewById(R.id.tv_starttime);
			uiAdapter.setTextSize(holder.tv_startitme, 18);
			uiAdapter.setMargin(holder.tv_startitme, LayoutParams.WRAP_CONTENT,
					LayoutParams.WRAP_CONTENT, 0, 0, 10, 0);
			
			holder.tv_locationaddr = (TextView) convertView
					.findViewById(R.id.tv_locationaddr);
			uiAdapter.setTextSize(holder.tv_locationaddr, 18);
			uiAdapter.setMargin(holder.tv_locationaddr, LayoutParams.WRAP_CONTENT,
					LayoutParams.WRAP_CONTENT, 0, 0, 10, 0);
			
			holder.tv_deadline = (TextView) convertView
					.findViewById(R.id.tv_deadline);
			uiAdapter.setTextSize(holder.tv_deadline, 18);
			uiAdapter.setMargin(holder.tv_deadline, LayoutParams.WRAP_CONTENT,
					LayoutParams.WRAP_CONTENT, 0, 0, 10, 0);
			
			holder.tv_count = (TextView) convertView
					.findViewById(R.id.tv_count);
			uiAdapter.setTextSize(holder.tv_count, 18);
			uiAdapter.setMargin(holder.tv_count, LayoutParams.WRAP_CONTENT,
					LayoutParams.WRAP_CONTENT, 0, 0, 10, 0);
			
			holder.tv_point = (TextView) convertView
					.findViewById(R.id.tv_point);
			uiAdapter.setTextSize(holder.tv_point, 18);
			uiAdapter.setMargin(holder.tv_point, LayoutParams.WRAP_CONTENT,
					LayoutParams.WRAP_CONTENT, 0, 0, 10, 0);

			holder.btn_actjoin = (Button) convertView
					.findViewById(R.id.btn_actjoin);
			uiAdapter.setTextSize(holder.btn_actjoin, 18);
			uiAdapter.setMargin(holder.btn_actjoin, 120, 28, 0, 0, 10, 5);

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

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		int actTypeId = entity.getActTypeId();
		if (actTypeId == 1) {
			String startTime = sdf.format(new Date(Long.parseLong(entity.getStartTime().substring(6, 19))));
			holder.tv_startitme.setText("活动时间：" + startTime);
			String locationAddr = entity.getLocationAddr();
			holder.tv_locationaddr.setText("活动地点：" + locationAddr);
		} else if (actTypeId == 2) {
			String startTime = sdf.format(new Date(Long.parseLong(entity.getStartTime().substring(6, 19))));
			holder.tv_startitme.setText("培训时间：" + startTime);
			String locationAddr = entity.getLocationAddr();
			holder.tv_locationaddr.setText("培训地点：" + locationAddr);
		}
		
		String deadline = sdf.format(new Date(Long.parseLong(entity.getDeadLine().substring(6, 19))));
		holder.tv_deadline.setText("报名截止：" + deadline);
		String count = entity.getCurrentCount() + "/" + entity.getLimitCount();
		holder.tv_count.setText("报名人数：" + count);
		String point = entity.getAwardPoint() + "/" + entity.getPunishPoint();
		holder.tv_point.setText("奖惩积分：" + point);
		
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

		final String joinTxt = entity.getStatus(entity.getLoginName());
		holder.btn_actjoin.setText(joinTxt);
		if (entity.getIsEnabled(entity.getLoginName())) {
			holder.btn_actjoin.setClickable(true);
			holder.btn_actjoin.setTag(entity);
			holder.btn_actjoin.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(final View v) {
					// TODO Auto-generated method stub
					String msgTitle = "";
					String msgContent ="";
					if("未报名".equals(joinTxt)){
						msgTitle = "报名确认";
						msgContent = "确定要参加吗？";
						cancel = false;
					}else if("取消报名".equals(joinTxt)){
						msgTitle = "报名取消";
						msgContent = "确定要取消吗？";
						cancel = true;
					}
					dUtil.showDialog(msgTitle, 0, msgContent, "确定", "取消", new DialogButtonClickCallback() {
						@Override
						public void positiveButtonClick() {
							// TODO Auto-generated method stub
							final ActivityDetailEntity activitydetailentity = (ActivityDetailEntity) v
									.getTag();
							singUp(v, activitydetailentity,cancel);
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
					.setImageResource(R.drawable.com_activity_expired);
		} else {
			holder.iv_activity.setImageResource(R.drawable.com_activity_now);
		}

		return convertView;
	}

	
	private void singUp(View v,final ActivityDetailEntity activitydetailentity,final boolean cancel){
		final Button btn = (Button) v;
		activitydetailentity.setActionCancel(cancel);
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
					if(cancel){
						Toast.show(mContext, "取消失败！");
					}else{
						Toast.show(mContext, "报名失败！");
					}
					return;
				} else {
					String ret = result.getResult();
					if(!"1".equals(ret)){
						if(cancel){
							Toast.show(mContext, "取消失败！");
						}else{
							Toast.show(mContext, "报名失败！");
						}
						return;
					}else{
						if(cancel){
							Toast.show(mContext, "取消成功！");
						}else{
							Toast.show(mContext, "报名成功！");
						}
						
						//重新加载数据
						((ActivityActivity)mContext).reLoad();
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
		TextView tv_startitme;
		TextView tv_locationaddr;
		TextView tv_deadline;
		TextView tv_count;
		TextView tv_point;
		Button btn_actjoin;
	}
}
