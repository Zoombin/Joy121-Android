package com.joy.Widget;

import gejw.android.quickandroid.ui.adapter.UIAdapter;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.joy.R;
import com.joy.Activity.ContactDetailActivity;
import com.joy.Utils.Constants;
import com.joy.Widget.PayrollAdapter.ViewHolder;
import com.joy.json.model.PayrollBriefEntity;
import com.joy.json.model.CategoriesGoodsDEntity.CategoriesGoods;
import com.joy.json.model.RuleListEntity.RuleEntity;
import com.nostra13.universalimageloader.core.ImageLoader;


public class RuleAdapter extends BaseAdapter {
	private List<RuleEntity> datas;
	private Context mContext = null;
	private Activity mActivity = null;
	private UIAdapter uiAdapter;
	private Resources resources;
	
	public RuleAdapter(Activity activity, Context ctx) {
		mActivity = activity;
		mContext = ctx;
		uiAdapter = UIAdapter.getInstance(ctx);
		resources = ctx.getResources();
	}

	
	public void setData(List<RuleEntity> datas) {
		this.datas = datas;
		this.notifyDataSetChanged();
	}

	@Override
	public int getCount() {
		return datas == null ? 0 : datas.size();
	}

	@Override
	public Object getItem(int position) {
		return datas == null ? null : datas.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		if (convertView == null) {
			convertView = LayoutInflater.from(mContext).inflate(
					R.layout.rule_list_item, parent, false);
			holder = new ViewHolder();
			
			holder.layout_rule = (LinearLayout) convertView.findViewById(R.id.layout_rule);
			holder.iv_pic = (ImageView) convertView.findViewById(R.id.iv_pic);
			holder.tv_title = (TextView) convertView.findViewById(R.id.tv_title);
			holder.tv_content = (TextView) convertView.findViewById(R.id.tv_content);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		final RuleEntity data = (RuleEntity) getItem(position);
		if (data != null) {
			holder.tv_title.setText(data.getTitle());
			//holder.tv_content.setText(data.getContent());
			if (TextUtils.isEmpty(data.getPic())) {
				holder.iv_pic.setImageResource(R.drawable.img_default);
			} else {
				ImageLoader.getInstance().displayImage(data.getPic(), holder.iv_pic);
			}
		}
		holder.layout_rule.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
			}
		});
		return convertView;
	}

	class ViewHolder {
		LinearLayout layout_rule;
		ImageView iv_pic;
		TextView tv_title;
		TextView tv_content;
	}

}