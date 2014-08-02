package com.joy.Widget;

import gejw.android.quickandroid.ui.adapter.UIAdapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
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
			ImageLoader.getInstance().displayImage(Constants.IMGSURL + entity.getPicture(), holder.img_icon);
			holder.img_icon.setOnClickListener(clicklistener);
			holder.img_icon.setTag(entity.getId());
			uiAdapter.setMargin(holder.img_icon, 70,
					uiAdapter.CalcHeight(70, 1, 1), 2, 0, 10, 0);
			
			holder.subTitle = (TextView) convertView.findViewById(R.id.order_sub_title);
			holder.subDetail= (TextView) convertView.findViewById(R.id.order_sub_detail);

			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		CommoditySet singleSet = data.get(position);
		if(singleSet != null){
			String urlString = singleSet.getProductPicture();
			if(!TextUtils.isEmpty(urlString)){
				ImageLoader.getInstance().displayImage(Constants.IMGDETAIL+urlString, holder.img_icon);
			}else{
				holder.img_icon.setImageResource(R.drawable.img_default);
			}
			String title = singleSet.getProductName();
			if(TextUtils.isEmpty(title)){
				holder.subTitle.setVisibility(View.GONE);
			}else{
				holder.subTitle.setVisibility(View.VISIBLE);
				holder.subTitle.setText(singleSet.getProductName());
			}
			holder.subDetail.setText(singleSet.getAmount()
					+ "   "
					+ singleSet.getProperty().replaceAll("color:", "")
							.replaceAll("size_trousers:", "")
							.replaceAll("size_cloth:", "").replaceAll(";", " ; "));
		}
		
		return convertView;
	}

	public class ViewHolder {
		
		ImageView img_icon;
		TextView subTitle;
		TextView subDetail;
	}
	
	OnClickListener clicklistener = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.img_icon:
				Intent intent = new Intent();
				intent.putExtra(OrderDetailActivity.EXTRA_COMMSETID, (Integer) v.getTag());
				intent.setClass(mContext, OrderDetailActivity.class);
				mContext.startActivity(intent);
				break;
			default:
				break;
			}
		}
	};
}
