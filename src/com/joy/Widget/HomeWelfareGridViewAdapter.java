package com.joy.Widget;

import gejw.android.quickandroid.ui.adapter.UIAdapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.joy.R;
import com.joy.Activity.OrderDetailActivity;
import com.joy.Utils.Constants;
import com.joy.json.model.CommoditySet;
import com.nostra13.universalimageloader.core.ImageLoader;

/**
 * gridview适配器
 * 
 * @author daiye
 * 
 */
public class HomeWelfareGridViewAdapter extends BaseAdapter {

	private Context mContext;
	private UIAdapter uiAdapter;

	private List<CommoditySet> data = new ArrayList<CommoditySet>();

	public HomeWelfareGridViewAdapter(Context context, List<CommoditySet> data) {
		mContext = context;
		this.data = data;
		uiAdapter = UIAdapter.getInstance(context);
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
	public View getView(int position, View convertView, ViewGroup parent) {
		final CommoditySet entity = data.get(position);
		ViewHolder holder;

		if (convertView == null) {
			convertView = LayoutInflater.from(mContext).inflate(
					R.layout.home_welfare_item, parent, false);

			holder = new ViewHolder();

			holder.layout_welfare_item = (LinearLayout) convertView
					.findViewById(R.id.layout_welfare_item);
			holder.layout_welfare_item.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					Intent intent = new Intent();
					intent.putExtra(OrderDetailActivity.EXTRA_COMMSETID, entity.getId());
					intent.setClass(mContext, OrderDetailActivity.class);
					mContext.startActivity(intent);
				}
			});
			
			holder.img_icon = (ImageView) convertView
					.findViewById(R.id.img_icon);
			if (entity.getAppPicture() != null) {
				ImageLoader.getInstance().displayImage(
						Constants.IMGSURL + entity.getPicture(),
						holder.img_icon);
			}
			uiAdapter.setMargin(holder.img_icon, 70,
					uiAdapter.CalcHeight(70, 1, 1), 5, 5, 5, 5);

			holder.tv_typename = (TextView) convertView
					.findViewById(R.id.tv_typename);
			holder.tv_typename.setText(entity.getTypeName());
			uiAdapter.setMargin(holder.tv_typename, LayoutParams.WRAP_CONTENT,
					LayoutParams.WRAP_CONTENT, 5, 5, 0, 0);
			uiAdapter.setTextSize(holder.tv_typename, 18);

			holder.tv_appdescription = (TextView) convertView
					.findViewById(R.id.tv_appdescription);
			holder.tv_appdescription.setText(entity.getAppDescription());
			uiAdapter.setMargin(holder.tv_typename, LayoutParams.WRAP_CONTENT,
					LayoutParams.WRAP_CONTENT, 5, 10, 0, 5);
			uiAdapter.setTextSize(holder.tv_appdescription, 18);

			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		return convertView;
	}

	public class ViewHolder {
		LinearLayout layout_welfare_item;
		ImageView img_icon;
		TextView tv_typename;
		TextView tv_appdescription;
	}
}
