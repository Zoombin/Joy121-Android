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
//		if (convertView == null) {
//			convertView = LayoutInflater.from(mContext).inflate(
//					R.layout.contacts_list_item, parent, false);
//			holder = new ViewHolder();
//			// title
//			holder.tv_title = (TextView) convertView
//					.findViewById(R.id.tv_title);
//			uiAdapter.setTextSize(holder.tv_title, 20);
//			uiAdapter.setPadding(holder.tv_title, 5, 5, 5, 5);
//
//			holder.item_left = (LinearLayout) convertView
//					.findViewById(R.id.item_left);
//			uiAdapter.setPadding(holder.item_left, 10, 10, 10, 10);
//
//			// 新信息
//			holder.iv_newmsg = (ImageView) convertView
//					.findViewById(R.id.iv_newmsg);
//			uiAdapter.setMargin(
//					holder.iv_newmsg,
//					10,
//					uiAdapter.CalcHeight(10, 1, 1),
//					iconWidth - 10,
//					uiAdapter.CalcHeight(iconWidth, 1, 1)
//							- uiAdapter.CalcHeight(10, 1, 1), 0, 0);
//
//			// 姓名
//			holder.tv_name = (TextView) convertView.findViewById(R.id.tv_name);
//			uiAdapter.setTextSize(holder.tv_name, nameTextSize);
//			uiAdapter.setPadding(holder.tv_name, 10, 0, 5, 0);
//
//			// 个性签名
//			holder.tv_signature = (TextView) convertView
//					.findViewById(R.id.tv_signature);
//			uiAdapter.setTextSize(holder.tv_signature, signatureTextSize);
//			uiAdapter.setPadding(holder.tv_signature, 10, 0, 5, 0);
//
//			convertView.setTag(holder);
//		} else {// 有直接获得ViewHolder
//			holder = (ViewHolder) convertView.getTag();
//		}
//
//		if (entity.isType()) {
//			holder.tv_title.setText(entity.getName());
//
//			holder.tv_title.setVisibility(View.VISIBLE);
//			holder.item_left.setVisibility(View.GONE);
//		} else {
//			holder.tv_title.setVisibility(View.GONE);
//			holder.item_left.setVisibility(View.VISIBLE);
//
//			// 加载头像
//			{
//				DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
//						.displayer(
//								new RoundedBitmapDisplayer(uiAdapter
//										.CalcWidth(iconWidth)))
//						.cacheInMemory(true).cacheOnDisc(true)
//						.imageScaleType(ImageScaleType.EXACTLY_STRETCHED)
//						.build();
//				ImageLoader.getInstance().displayImage(entity.getIconUrl(),
//						holder.iv_icon, defaultOptions);
//			}
//			holder.tv_name.setText(entity.getName());
//			holder.tv_signature.setText(entity.getSignature());
//
//		}

//		return convertView;
		return null;
		
	}

	public class ViewHolder {
		LinearLayout item_left;
		TextView tv_title;
		ImageView iv_newmsg;
		TextView tv_name;
		TextView tv_signature;
	}
}
