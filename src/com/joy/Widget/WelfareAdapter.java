package com.joy.Widget;

import gejw.android.quickandroid.ui.adapter.UIAdapter;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.joy.R;
import com.joy.json.model.CommoditySet;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;

public class WelfareAdapter extends BaseAdapter {
	// 配置
	// 头像宽度
	private static int iconWidth = 60;
	// 名字字体大小
	private static int nameTextSize = 25;
	// 签名字体大小
	private static int signatureTextSize = 20;

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
			// title
			holder.tv_title = (TextView) convertView
					.findViewById(R.id.tv_title);
			uiAdapter.setTextSize(holder.tv_title, 20);
			uiAdapter.setPadding(holder.tv_title, 5, 5, 5, 5);

			holder.layout_type = (LinearLayout) convertView
					.findViewById(R.id.layout_type);
			uiAdapter.setPadding(holder.layout_type, 10, 10, 10, 10);

			// 图片
			holder.iv_icon = (ImageView) convertView
					.findViewById(R.id.iv_icon);
			uiAdapter.setMargin(
					holder.iv_icon,
					10,
					uiAdapter.CalcHeight(10, 1, 1),
					iconWidth - 10,
					uiAdapter.CalcHeight(iconWidth, 1, 1)
							- uiAdapter.CalcHeight(10, 1, 1), 0, 0);

			// 图片名
			holder.tv_name = (TextView) convertView.findViewById(R.id.tv_name);
			uiAdapter.setTextSize(holder.tv_name, nameTextSize);
			uiAdapter.setPadding(holder.tv_name, 10, 0, 5, 0);

			// 内容
			holder.tv_content = (TextView) convertView
					.findViewById(R.id.tv_content);
			uiAdapter.setTextSize(holder.tv_content, signatureTextSize);
			uiAdapter.setPadding(holder.tv_content, 10, 0, 5, 0);

			convertView.setTag(holder);
		} else {// 有直接获得ViewHolder
			holder = (ViewHolder) convertView.getTag();
		}

//		if (entity.isType()) {
//			holder.tv_title.setText(entity.getName());
//			holder.tv_title.setVisibility(View.VISIBLE);
//			holder.layout_type.setVisibility(View.GONE);
//			holder.tv_content.setVisibility(View.GONE);
//		} else {
			holder.tv_title.setVisibility(View.GONE);
			holder.layout_type.setVisibility(View.VISIBLE);
			holder.tv_content.setVisibility(View.VISIBLE);

			// 加载头像
			{
				DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
						.displayer(
								new RoundedBitmapDisplayer(uiAdapter
										.CalcWidth(iconWidth)))
						.cacheInMemory(true).cacheOnDisc(true)
						.imageScaleType(ImageScaleType.EXACTLY_STRETCHED)
						.build();
				ImageLoader.getInstance().displayImage(entity.getPicture(),//set 003.jpg
						holder.iv_icon, defaultOptions);
			}
			holder.tv_name.setText(entity.getSetName());
			holder.tv_content.setText(entity.getDescription());
//		}
		return convertView;
	}

	public class ViewHolder {
		TextView tv_title;
		LinearLayout layout_type;
		ImageView iv_icon;
		TextView tv_name;
		TextView tv_content;
	}
}
