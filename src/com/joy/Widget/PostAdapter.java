package com.joy.Widget;

import gejw.android.quickandroid.ui.adapter.UIAdapter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.Html;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.joy.R;
import com.joy.Activity.ContactDetailActivity;
import com.joy.Activity.PostDetailActivity;
import com.joy.Utils.Constants;
import com.joy.json.model.PostDetailEntity;
import com.nostra13.universalimageloader.core.ImageLoader;

public class PostAdapter extends BaseAdapter {

	/**
	 * 上下文对象
	 */
	private Context mContext = null;
	private Activity mActivity = null;
	private List<PostDetailEntity> data;
	private UIAdapter uiAdapter;
	String isexpired;

	/**
	 * @param mainActivity
	 */
	public PostAdapter(Activity activity, Context ctx) {
		mActivity = activity;
		mContext = ctx;
		uiAdapter = UIAdapter.getInstance(ctx);
	}

	public void addItem(PostDetailEntity entity) {
		if (data == null) {
			data = new ArrayList<PostDetailEntity>();
		}
		data.add(entity);
	}

	public void setIsexpired(String isexpired) {
		this.isexpired = isexpired;
	}

	public void setData(List<PostDetailEntity> data) {
		this.data = data;
		this.notifyDataSetChanged();
	}

	public void addSeparatorItem(PostDetailEntity entity) {
		data.add(entity);
	}

	@Override
	public int getCount() {
		return data == null ? 0 : data.size();
	}

	@Override
	public Object getItem(int position) {
		return data == null ? null : data.get(position);
	}

	@Override
	public long getItemId(int position) {
		return data == null ? 0 : position;
	}

	public void removeAll() {
		if (data != null)
			data.clear();
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if (convertView == null) {
			convertView = LayoutInflater.from(mContext).inflate(R.layout.post_list_item, parent, false);
			holder = new ViewHolder();

			holder.iv_post = (ImageView) convertView.findViewById(R.id.iv_post);
			uiAdapter.setMargin(holder.iv_post, 40, 40, 0, 0, 0, 0);
			holder.tv_posttitle = (TextView) convertView.findViewById(R.id.tv_posttitle);
			TextPaint titleTextPaint = holder.tv_posttitle.getPaint(); 
			titleTextPaint.setFakeBoldText(true); 
			uiAdapter.setTextSize(holder.tv_posttitle, 20);
			uiAdapter.setMargin(holder.tv_posttitle, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 0, 5, 0, 5);

			holder.tv_posttime = (TextView) convertView.findViewById(R.id.tv_posttime);
			TextPaint timeTextPaint = holder.tv_posttime.getPaint(); 
			timeTextPaint.setFakeBoldText(true); 
			uiAdapter.setTextSize(holder.tv_posttime, 20);
			uiAdapter.setMargin(holder.tv_posttime, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 0, 5, 10, 5);
			
			holder.tv_postcontent = (TextView) convertView.findViewById(R.id.tv_postcontent);
			uiAdapter.setTextSize(holder.tv_postcontent, 18);
			uiAdapter.setMargin(holder.tv_postcontent, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 20, 5, 10, 5);
			// uiAdapter.setMargin(holder.iv_img, 60,
			// uiAdapter.CalcHeight(60, 133, 94), 0, 0, 0, 0);

			convertView.setTag(holder);
		} else {// 有直接获得ViewHolder
			holder = (ViewHolder) convertView.getTag();
		}

		final PostDetailEntity entity = data.get(position);
		if (entity != null) {
			if (entity.getIsOutOfDate()) {
				holder.iv_post.setImageResource(R.drawable.com_notice_expired);
			} else {
				holder.iv_post.setImageResource(R.drawable.com_notice_now);
			}
			//String imgUrl = entity.getPicture();
			/*if (TextUtils.isEmpty(imgUrl)) {
				holder.iv_img.setImageResource(R.drawable.img_default);
			} else {
				ImageLoader.getInstance().displayImage(Constants.IMGPOST + imgUrl, holder.iv_img);
			}*/
			holder.tv_posttitle.setText(entity.getTitle());
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			holder.tv_posttime.setText(sdf.format(new Date(Long.parseLong(entity.getPostTime().substring(6, 19)))));
			holder.tv_postcontent.setText(Html.fromHtml(entity.getContent()));
			
			convertView.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					Intent intent = new Intent();
					intent.putExtra(PostDetailActivity.PostDetails, entity);
					intent.setClass(mContext, PostDetailActivity.class);
					mActivity.startActivity(intent);
				}
			});
		}
		return convertView;
	}

	public class ViewHolder {
		LinearLayout layout_post;
		ImageView iv_post;
		TextView tv_posttitle;
		TextView tv_posttime;
		TextView tv_postcontent;
	}
}
