package com.joy.Widget;

import gejw.android.quickandroid.ui.adapter.UIAdapter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.joy.R;
import com.joy.json.model.CommoditySet;
import com.joy.json.model.UserOrderEntity;

public class OrderQueryAdapter extends BaseAdapter {

	/**
	 * 上下文对象
	 */
	private Context mContext = null;
	private ArrayList<UserOrderEntity> data = new ArrayList<UserOrderEntity>();
	private UIAdapter uiAdapter;
	private Resources resources;
	
	/**
	 * @param mainActivity
	 */
	public OrderQueryAdapter(Context ctx) {
		mContext = ctx;
		uiAdapter = UIAdapter.getInstance(ctx);
		resources = ctx.getResources();
	}

	public void addItem(UserOrderEntity entity) {
		data.add(entity);
	}

	public void addSeparatorItem(UserOrderEntity entity) {
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
		UserOrderEntity entity = data.get(position);

		ViewHolder holder;
		if (convertView == null) {
			convertView = LayoutInflater.from(mContext).inflate(
					R.layout.order_list_item, parent, false);
			holder = new ViewHolder();

			holder.layout_order_info = (RelativeLayout) convertView
					.findViewById(R.id.layout_order_info);
			uiAdapter.setMargin(holder.layout_order_info, LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT, 0, 2, 0, 0);
			
			// orderid
			holder.tv_orderid = (TextView) convertView
					.findViewById(R.id.tv_orderid);
			uiAdapter.setTextSize(holder.tv_orderid, 16);
			uiAdapter.setMargin(holder.tv_orderid, LayoutParams.WRAP_CONTENT,
					LayoutParams.WRAP_CONTENT, 10, 10, 0, 0);

			// createTime
			holder.tv_createTime = (TextView) convertView.findViewById(R.id.tv_createTime);
			uiAdapter.setTextSize(holder.tv_createTime, 16);
			uiAdapter.setMargin(holder.tv_createTime, LayoutParams.WRAP_CONTENT,
					LayoutParams.WRAP_CONTENT, 0, 10, 10, 0);

			// points
			holder.tv_points = (TextView) convertView
					.findViewById(R.id.tv_points);
			uiAdapter.setTextSize(holder.tv_points, 16);
			uiAdapter.setMargin(holder.tv_points, LayoutParams.WRAP_CONTENT,
					LayoutParams.WRAP_CONTENT, 10, 10, 0, 10);

			// flag
			holder.tv_flag = (TextView) convertView
					.findViewById(R.id.tv_flag);
			uiAdapter.setTextSize(holder.tv_flag, 16);
			uiAdapter.setMargin(holder.tv_flag, LayoutParams.WRAP_CONTENT,
					LayoutParams.WRAP_CONTENT, 0, 10, 10, 10);
			
			holder.layout_order = (RelativeLayout) convertView.findViewById(R.id.layout_order);
			uiAdapter.setMargin(holder.layout_order, LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT, 
					10, 0, 10, 0);
			
			holder.grid_order = (CannotRollListView) convertView.findViewById(R.id.grid_order);

			holder.iv_arrowright = (ImageView) convertView.findViewById(R.id.iv_arrowright);
			uiAdapter.setMargin(holder.iv_arrowright, 15, uiAdapter.CalcHeight(15, 16, 28), 0, 10, 10, 10);
			
			/*holder.layout_ordernum = (RelativeLayout) convertView.findViewById(R.id.layout_ordernum);
			uiAdapter.setMargin(holder.layout_ordernum, LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT, 
					10, 0, 10, 5);
			uiAdapter.setPadding(holder.layout_ordernum, 0, 10, 10, 10);*/
			
			holder.tv_ordernum = (TextView) convertView.findViewById(R.id.tv_ordernum);
			uiAdapter.setTextSize(holder.tv_ordernum, 16);
			uiAdapter.setMargin(holder.tv_ordernum, LayoutParams.WRAP_CONTENT,
					LayoutParams.WRAP_CONTENT, 10, 10, 0, 10);
			
			convertView.setTag(holder);
		} else {// 有直接获得ViewHolder
			holder = (ViewHolder) convertView.getTag();
		}

		holder.tv_orderid.setText("订单号：" + entity.getOrderId());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		holder.tv_createTime.setText(sdf.format(new Date(Long.parseLong(entity.getCreateTime().substring(6, 19)))));
		holder.tv_points.setText("积分：" + entity.getPoints());
//		 1-待处理，2-已确认
		holder.tv_flag.setText(entity.getFlag().equals("1") ? "待处理" : "已确认");
		
		List<CommoditySet> LstCommoditySet = entity.getLstCommoditySet();
		if (LstCommoditySet != null) {
			OrderGridViewAdapter adapter = new OrderGridViewAdapter(mContext, LstCommoditySet);
			holder.grid_order.setAdapter(adapter);
			holder.tv_ordernum.setText("商品数量：" + LstCommoditySet.size() );
		} else {
			holder.tv_ordernum.setText("商品数量：0");
		}
		
		return convertView;
	}

	public class ViewHolder {
		RelativeLayout layout_order_info;
		TextView tv_orderid;
		TextView tv_createTime;
		TextView tv_points;
		TextView tv_flag;
		RelativeLayout layout_order;
		CannotRollListView grid_order;
		ImageView iv_arrowright;
		//RelativeLayout layout_ordernum;
		TextView tv_ordernum;
	}
}
