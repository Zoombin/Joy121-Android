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
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.joy.R;
import com.joy.json.JsonCommon;
import com.joy.json.JsonCommon.OnOperationListener;
import com.joy.json.model.ActivityDetailEntity;
import com.joy.json.model.ActjoinEntity;
import com.joy.json.model.SurveyDetailEntity;
import com.joy.json.operation.OperationBuilder;
import com.joy.json.operation.impl.ActjoinOp;

public class SurveyAdapter extends BaseAdapter {

	/**
	 * 上下文对象
	 */
	private Context mContext = null;
	private Activity mActivity = null;
	private ArrayList<SurveyDetailEntity> data = new ArrayList<SurveyDetailEntity>();
	private UIAdapter uiAdapter;

	/**
	 * @param mainActivity
	 */
	public SurveyAdapter(Activity activity, Context ctx) {
		mActivity = activity;
		mContext = ctx;
		uiAdapter = UIAdapter.getInstance(ctx);
	}

	public void addItem(SurveyDetailEntity entity) {
		data.add(entity);
	}

	public void addSeparatorItem(SurveyDetailEntity entity) {
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
		SurveyDetailEntity entity = data.get(position);

		ViewHolder holder;
		if (convertView == null) {
			convertView = LayoutInflater.from(mContext).inflate(
					R.layout.survey_list_item, parent, false);
			holder = new ViewHolder();

			holder.iv_survey = (ImageView) convertView
					.findViewById(R.id.iv_survey);
			uiAdapter.setMargin(holder.iv_survey, 60,
					uiAdapter.CalcHeight(60, 133, 94), 0, 0, 0, 0);

			holder.tv_surveyname = (TextView) convertView
					.findViewById(R.id.tv_surveyname);
			uiAdapter.setTextSize(holder.tv_surveyname, 20);
			uiAdapter.setMargin(holder.tv_surveyname,
					LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 0, 0,
					0, 0);

			holder.tv_expiretime = (TextView) convertView
					.findViewById(R.id.tv_expiretime);
			uiAdapter.setTextSize(holder.tv_expiretime, 20);
			uiAdapter.setMargin(holder.tv_expiretime,
					LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 20, 5,
					0, 5);

			holder.layout_multichoice = (LinearLayout) convertView
					.findViewById(R.id.layout_multichoice);
			uiAdapter.setMargin(holder.layout_multichoice,
					LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 50,
					10, 0, 10);
			
			holder.btn_survey = (Button) convertView
					.findViewById(R.id.btn_survey);
			uiAdapter.setTextSize(holder.btn_survey, 20);
			uiAdapter.setMargin(holder.btn_survey, 120, 35, 0, 0, 10, 5);
			holder.btn_survey.setOnClickListener(clicklistener);

			convertView.setTag(holder);
		} else {// 有直接获得ViewHolder
			holder = (ViewHolder) convertView.getTag();
		}

		holder.tv_surveyname.setText(entity.getTitle());

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		holder.tv_expiretime.setText("截止日期："
				+ sdf.format(new Date(Long.parseLong(entity.getExpireTime()
						.substring(6, 19)))));

		String[] questionlist = entity.getQuestions().split("\\^");
		if (holder.layout_multichoice.getChildCount() == 0) {
			for (String question : questionlist) {
				CheckBox checkbox = new CheckBox(mContext);
				checkbox.setText(question);
				holder.layout_multichoice.addView(checkbox);
			}
		}

		holder.btn_survey.setTag(entity);

		return convertView;
	}

	OnClickListener clicklistener = new OnClickListener() {

		@Override
		public void onClick(final View v) {
			ActivityDetailEntity entity = (ActivityDetailEntity) v.getTag();
			OperationBuilder builder = new OperationBuilder().append(
					new ActjoinOp(), entity);
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
						v.setBackgroundColor(mActivity.getResources().getColor(
								R.color.welfare_item_tab_bg));
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
		ImageView iv_survey;
		TextView tv_surveyname;
		TextView tv_expiretime;
		LinearLayout layout_multichoice;
		Button btn_survey;
	}
}
