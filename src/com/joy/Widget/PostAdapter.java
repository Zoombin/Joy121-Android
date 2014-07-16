package com.joy.Widget;

import gejw.android.quickandroid.ui.adapter.UIAdapter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.joy.R;
import com.joy.json.model.PostDetailEntity;

public class PostAdapter extends BaseAdapter {

	/**
	 * 上下文对象
	 */
	private Context mContext = null;
	private ArrayList<PostDetailEntity> data = new ArrayList<PostDetailEntity>();
	private UIAdapter uiAdapter;
	
	/**
	 * @param mainActivity
	 */
	public PostAdapter(Context ctx) {
		mContext = ctx;
		uiAdapter = UIAdapter.getInstance(ctx);
	}

	public void addItem(PostDetailEntity entity) {
		data.add(entity);
	}

	public void addSeparatorItem(PostDetailEntity entity) {
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
	
	public void removeAll() {
		data.clear();
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		PostDetailEntity entity = data.get(position);

		ViewHolder holder;
		if (convertView == null) {
			convertView = LayoutInflater.from(mContext).inflate(
					R.layout.post_list_item, parent, false);
			holder = new ViewHolder();
			
			holder.iv_post = (ImageView) convertView
					.findViewById(R.id.iv_post);
			uiAdapter.setMargin(holder.iv_post, 60,
					uiAdapter.CalcHeight(60, 133, 94), 0, 0, 0, 0);

			holder.tv_posttitle = (TextView) convertView.findViewById(R.id.tv_posttitle);
			uiAdapter.setTextSize(holder.tv_posttitle, 20);
			uiAdapter.setMargin(holder.tv_posttitle, LayoutParams.WRAP_CONTENT,
					LayoutParams.WRAP_CONTENT, 0, 0, 0, 0);

			holder.tv_posttime = (TextView) convertView
					.findViewById(R.id.tv_posttime);
			uiAdapter.setTextSize(holder.tv_posttime, 20);
			uiAdapter.setMargin(holder.tv_posttime, LayoutParams.WRAP_CONTENT,
					LayoutParams.WRAP_CONTENT, 0, 0, 10, 0);

			holder.tv_postcontent = (TextView) convertView
					.findViewById(R.id.tv_postcontent);
			uiAdapter.setTextSize(holder.tv_postcontent, 20);
			uiAdapter.setPadding(holder.tv_postcontent, 20, 10, 10, 10);
			
			convertView.setTag(holder);
		} else {// 有直接获得ViewHolder
			holder = (ViewHolder) convertView.getTag();
		}

		holder.tv_posttitle.setText(entity.getTitle());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		holder.tv_posttime.setText(sdf.format(new Date(Long.parseLong(entity.getPostTime().substring(6, 19)))));
		holder.tv_postcontent.setText(Html.fromHtml(entity.getContent()));
		
		return convertView;
	}

	public class ViewHolder {
		ImageView iv_post;
		TextView tv_posttitle;
		TextView tv_posttime;
		TextView tv_postcontent;
	}
}
