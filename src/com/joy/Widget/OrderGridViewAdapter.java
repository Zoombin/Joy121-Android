package com.joy.Widget;

import gejw.android.quickandroid.ui.adapter.UIAdapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.joy.R;
import com.joy.Utils.Constants;
import com.joy.json.model.CommoditySet;
import com.nostra13.universalimageloader.core.ImageLoader;

/**
 * gridview适配器
 * 
 * @author daiye
 * 
 */
public class OrderGridViewAdapter extends BaseAdapter {

	private Context mContext;
	private UIAdapter uiAdapter;

	private List<CommoditySet> data = new ArrayList<CommoditySet>();

	public OrderGridViewAdapter(Context context, List<CommoditySet> data) {
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
		CommoditySet entity = data.get(position);
		ViewHolder holder;

		if (convertView == null) {
			convertView = LayoutInflater.from(mContext).inflate(
					R.layout.order_gridview_item, parent, false);

			holder = new ViewHolder();

			holder.img_icon = (ImageView) convertView
					.findViewById(R.id.img_icon);
			ImageLoader.getInstance().displayImage(Constants.IMGURL + entity.getAppPicture(), holder.img_icon);
			uiAdapter.setMargin(holder.img_icon, 70,
					uiAdapter.CalcHeight(70, 1, 1), 10, 0, 10, 0);

			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		return convertView;
	}

	public class ViewHolder {
		ImageView img_icon;
	}
}
