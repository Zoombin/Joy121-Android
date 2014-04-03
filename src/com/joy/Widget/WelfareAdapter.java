package com.joy.Widget;

import gejw.android.quickandroid.ui.adapter.UIAdapter;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.joy.R;
import com.joy.Utils.Constants;
import com.joy.json.model.CommoditySet;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;

public class WelfareAdapter extends BaseAdapter {

	/**
	 * 上下文对象
	 */
	private Context mContext = null;
	private ArrayList<CommoditySet> data = new ArrayList<CommoditySet>();
	private UIAdapter uiAdapter;

	/**
	 * @param mainActivity
	 */
	public WelfareAdapter(Context ctx) {
		mContext = ctx;
		uiAdapter = UIAdapter.getInstance(ctx);
	}

	public void addItem(CommoditySet entity) {
		data.add(entity);
	}

	public void addSeparatorItem(CommoditySet entity) {
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
		CommoditySet entity = data.get(position);

		ViewHolder holder;
		if (convertView == null) {
			convertView = LayoutInflater.from(mContext).inflate(
					R.layout.welfare_list_item, parent, false);
			holder = new ViewHolder();
			
			holder.layout_title = (LinearLayout) convertView
					.findViewById(R.id.layout_title);
			
			// title
			holder.tv_title = (TextView) convertView
					.findViewById(R.id.tv_title);
			uiAdapter.setTextSize(holder.tv_title, 16);
			uiAdapter.setMargin(holder.tv_title, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 10, 10, 0, 3);

			// time
			holder.tv_time = (TextView) convertView.findViewById(R.id.tv_time);
			uiAdapter.setTextSize(holder.tv_time, 14);
			uiAdapter.setMargin(holder.tv_time, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 
					10, 12, 0, 3);
			
			holder.layout_type = (LinearLayout) convertView
					.findViewById(R.id.layout_type);

			// 图片
			holder.iv_icon = (ImageView) convertView.findViewById(R.id.iv_icon);
			uiAdapter.setMargin(holder.iv_icon, 80, uiAdapter.CalcHeight(80, 1, 1), 20, 10, 0, 5);

			// 福利名
			holder.tv_name = (TextView) convertView.findViewById(R.id.tv_name);
			uiAdapter.setTextSize(holder.tv_name, 18);
			uiAdapter.setMargin(holder.tv_name, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 10, 0, 5, 0);

			// 内容
			holder.tv_content = (TextView) convertView
					.findViewById(R.id.tv_content);
			uiAdapter.setTextSize(holder.tv_content, 16);
			uiAdapter.setMargin(holder.tv_content, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 10, 10, 5, 10);

			convertView.setTag(holder);
		} else {// 有直接获得ViewHolder
			holder = (ViewHolder) convertView.getTag();
		}

		if (entity.getSetType() == null) {
			holder.layout_title.setVisibility(View.VISIBLE);
			holder.iv_icon.setVisibility(View.GONE);
			holder.layout_type.setVisibility(View.GONE);
			holder.tv_content.setVisibility(View.GONE);
			
			holder.tv_title.setText(entity.getSetName());
			holder.tv_time.setText("有效期" + entity.getStartDate() + "~" + entity.getExpireDate());
		} else {
			holder.layout_title.setVisibility(View.GONE);
			holder.iv_icon.setVisibility(View.VISIBLE);
			holder.layout_type.setVisibility(View.VISIBLE);
			holder.tv_content.setVisibility(View.VISIBLE);

			// 加载头像
			{
				DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
				.cacheInMemory(true).cacheOnDisc(true)
						.imageScaleType(ImageScaleType.EXACTLY_STRETCHED)
						.build();
				if (entity.getAppPicture() != null) {
					ImageLoader.getInstance().displayImage(
							Constants.IMGURL + entity.getAppPicture(), holder.iv_icon,
							defaultOptions);
				}
			}
			holder.tv_name.setText(entity.getSetName());
			holder.tv_content.setText(entity.getAppDescription());
		}
		return convertView;
	}

	public class ViewHolder {
		LinearLayout layout_title;
		TextView tv_title;
		TextView tv_time;
		LinearLayout layout_type;
		ImageView iv_icon;
		TextView tv_name;
		TextView tv_content;
	}
}
