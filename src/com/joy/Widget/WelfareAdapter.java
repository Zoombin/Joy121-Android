package com.joy.Widget;

import gejw.android.quickandroid.ui.adapter.UIAdapter;
import gejw.android.quickandroid.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.joy.R;
import com.joy.Activity.MainActivity;
import com.joy.Activity.OrderConfirmActivity;
import com.joy.Activity.OrderDetailActivity;
import com.joy.Utils.Constants;
import com.joy.json.model.CommoditySet;
import com.joy.json.model.GoodsDetail;
import com.nostra13.universalimageloader.core.ImageLoader;

public class WelfareAdapter extends BaseAdapter {

	/**
	 * 上下文对象
	 */
	private Context mContext = null;
	private List<CommoditySet> data = new ArrayList<CommoditySet>();
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
	
	public void setData(List<CommoditySet> data){
		this.data = data;
		this.notifyDataSetChanged();
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
		final CommoditySet entity = data.get(position);

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
			uiAdapter.setMargin(holder.tv_title, LayoutParams.WRAP_CONTENT,
					LayoutParams.WRAP_CONTENT, 10, 10, 0, 3);

			// time
			holder.tv_time = (TextView) convertView.findViewById(R.id.tv_time);
			uiAdapter.setTextSize(holder.tv_time, 14);
			uiAdapter.setMargin(holder.tv_time, LayoutParams.WRAP_CONTENT,
					LayoutParams.WRAP_CONTENT, 10, 11, 0, 3);

			holder.layout_type = (LinearLayout) convertView
					.findViewById(R.id.layout_type);

			// 图片
			holder.iv_icon = (ImageView) convertView.findViewById(R.id.iv_icon);
			uiAdapter.setMargin(holder.iv_icon, 120,
					uiAdapter.CalcHeight(120, 1, 1), 20, 5, 20, 5);

			// 福利名
			holder.tv_name = (TextView) convertView.findViewById(R.id.tv_name);
			uiAdapter.setTextSize(holder.tv_name, 18);
			uiAdapter.setMargin(holder.tv_name, LayoutParams.WRAP_CONTENT,
					LayoutParams.WRAP_CONTENT, 10, 10, 50, 0);
			
			//所需积分
			holder.credit = (TextView) convertView.findViewById(R.id.tv_credit);
			uiAdapter.setTextSize(holder.credit, 18);
					
			// 内容
			holder.tv_content = (TextView) convertView
					.findViewById(R.id.tv_content);
			uiAdapter.setTextSize(holder.tv_content, 16);
			uiAdapter.setMargin(holder.tv_content, LayoutParams.WRAP_CONTENT,
					LayoutParams.WRAP_CONTENT, 10, 5, 50, 10);

			holder.btn_buy = (Button) convertView.findViewById(R.id.btn_buy);
			holder.btn_buy.setOnClickListener(clicklistener);
			uiAdapter.setTextSize(holder.btn_buy, 16);
			uiAdapter.setMargin(holder.btn_buy, 120, 35, 0, 5, 50, 10);

			convertView.setTag(holder);
		} else {// 有直接获得ViewHolder
			holder = (ViewHolder) convertView.getTag();
		}

		if (entity.getSetType() == null) {
			holder.layout_title.setVisibility(View.VISIBLE);
			holder.layout_type.setVisibility(View.GONE);

			holder.tv_title.setText(entity.getSetName());
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
			String startDate = sdf.format(new Date(Long.parseLong(entity
					.getStartDate())));
			String expireDate = sdf.format(new Date(Long.parseLong(entity
					.getEXPIREDDATE())));
			holder.tv_time.setText("选购日期：" + startDate + " - " + expireDate);
		} else {
			holder.layout_title.setVisibility(View.GONE);
			holder.layout_type.setVisibility(View.VISIBLE);
			holder.layout_type.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					Intent intent = new Intent();
					intent.putExtra(OrderDetailActivity.EXTRA_COMMSETID,
							entity.getId());
					intent.setClass(mContext, OrderDetailActivity.class);
					mContext.startActivity(intent);
				}
			});
			holder.btn_buy.setText("加入购物车");
			holder.btn_buy.setTag(entity);
			holder.credit.setText("所需积分："+entity.getPoints());

			// 加载头像
			if (entity.getAppPicture() != null) {
				ImageLoader.getInstance()
						.displayImage(Constants.IMGSURL + entity.getPicture(),
								holder.iv_icon);
			}

			holder.tv_name.setText(entity.getSetName());
			holder.tv_content.setText("描    述："
					+ Html.fromHtml(entity.getDescription()));
		}
		return convertView;
	}

	OnClickListener clicklistener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.btn_buy:
				CommoditySet entity = (CommoditySet) v.getTag();
				GoodsDetail detail = new GoodsDetail();
				detail.setGoods_name(entity.getDescription());
				detail.setGoods_img(entity.getPicture());
				detail.setIsLogoStore(false);
				detail.setGoods_id(String.format("%d", entity.getId()));
				MainActivity.Add2ShopCar(mContext, detail, 1);
				Toast.show(mContext, "商品已加入购物车");
				break;
			default:
				break;
			}
		}
	};

	public class ViewHolder {
		LinearLayout layout_title;
		TextView tv_title;
		TextView tv_time;
		LinearLayout layout_type;
		ImageView iv_icon;
		TextView tv_name;
		TextView tv_content;
		Button btn_buy;
		TextView credit;
	}
}
