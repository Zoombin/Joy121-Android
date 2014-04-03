package com.joy.Widget;

import gejw.android.quickandroid.ui.adapter.UIAdapter;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.joy.R;
import com.nostra13.universalimageloader.core.ImageLoader;

/**
 * gridview适配器
 * 
 * @author daiye
 * 
 */
public class GridViewAdapter extends BaseAdapter {

	private Context mContext;
	private UIAdapter uiAdapter;

	private ArrayList<GridViewEntity> data = new ArrayList<GridViewEntity>();

	public GridViewAdapter(Context context, ArrayList<GridViewEntity> data) {
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
		GridViewEntity entity = data.get(position);
		ViewHolder holder;

		if (convertView == null) {
			convertView = LayoutInflater.from(mContext).inflate(
					R.layout.gridview_item, parent, false);

			holder = new ViewHolder();

			holder.img_icon = (ImageView) convertView
					.findViewById(R.id.img_icon);
			holder.img_icon.setImageResource(entity.getIcon());
			uiAdapter.setMargin(holder.img_icon, 90,
					uiAdapter.CalcHeight(90, 1, 1), 0, 10, 0, 0);

			holder.icon_name = (TextView) convertView
					.findViewById(R.id.icon_name);
			holder.icon_name.setText(entity.getName());
			uiAdapter.setTextSize(holder.icon_name, 18);

			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		return convertView;
	}

	public class ViewHolder {
		ImageView img_icon;
		TextView icon_name;
	}
}
