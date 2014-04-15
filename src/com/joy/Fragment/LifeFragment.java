package com.joy.Fragment;

import gejw.android.quickandroid.QFragment;
import gejw.android.quickandroid.ui.adapter.UIAdapter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.joy.R;
import com.joy.Activity.OrderConfirmActivity;
import com.joy.Activity.OrderDetailActivity;
import com.joy.Utils.Constants;
import com.joy.Widget.GridViewAdapter;
import com.joy.Widget.GridViewEntity;
import com.joy.Widget.GridViewAdapter.ViewHolder;
import com.joy.json.model.CommoditySet;
import com.nostra13.universalimageloader.core.ImageLoader;

/**
 * 生活服务模块
 * 
 * @author daiye
 * 
 */
public class LifeFragment extends QFragment implements OnClickListener, OnItemClickListener {

	private RelativeLayout layout_title;
	private ImageView iv_title;
	private TextView tv_title;
	private ImageView iv_phone;
	private GridView grid_tab;
	private Resources resources;
	private ListView list_life;
	private ListView list_health;
	private ListView list_safety;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_life, container, false);
		
		resources = getResources();
		
		init(v);
		return v;
	}
	
	private void init(View v) {
		layout_title = (RelativeLayout) v.findViewById(R.id.layout_title);
		uiAdapter.setMargin(layout_title, LayoutParams.MATCH_PARENT, Constants.TitleHeight, 0, 0,
				0, 0);

		iv_title = (ImageView) v.findViewById(R.id.iv_title);
		uiAdapter.setMargin(iv_title, Constants.TitleIvWidth, Constants.TitleIvWidth, 10, 0, 10, 0);
		
		tv_title = (TextView) v.findViewById(R.id.tv_title);
		uiAdapter.setTextSize(tv_title, Constants.TitleSize);

		iv_phone = (ImageView) v.findViewById(R.id.iv_phone);
		uiAdapter.setMargin(iv_phone, 28, 28, 0, 0, 30, 0);
		iv_phone.setOnClickListener(this);
		
		grid_tab = (GridView) v.findViewById(R.id.grid_tab);
		ArrayList<GridViewEntity> data = new ArrayList<GridViewEntity>();

		// 生活
		GridViewEntity onlineinquery = new GridViewEntity();
		onlineinquery.setIcon(R.drawable.onlineinquery);
		onlineinquery.setName(resources.getString(R.string.life));
		data.add(onlineinquery);

		// 健康
		GridViewEntity holidaywelfare = new GridViewEntity();
		holidaywelfare.setIcon(R.drawable.holidaywelfare);
		holidaywelfare.setName(resources
				.getString(R.string.health));
		data.add(holidaywelfare);

		// 保险
		GridViewEntity sale = new GridViewEntity();
		sale.setIcon(R.drawable.sale);
		sale.setName(resources.getString(R.string.safety));
		data.add(sale);
		
		grid_tab.setNumColumns(3);
		grid_tab.setAdapter(new LifeGridViewAdapter(mActivity, data));
		grid_tab.setOnItemClickListener(this);
		
		list_life = (ListView) v.findViewById(R.id.list_life);
		list_life.setAdapter(new LifeListAdapter(mActivity));
		
		list_health = (ListView) v.findViewById(R.id.list_health);
		list_health.setAdapter(new LifeListAdapter(mActivity));
		
		list_safety = (ListView) v.findViewById(R.id.list_safety);
		list_safety.setAdapter(new LifeListAdapter(mActivity));
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.iv_phone:
			// 跳转到拨号面板
			Uri uri = Uri.parse("tel:4008558121");
			Intent intent = new Intent(Intent.ACTION_DIAL, uri);     
			startActivity(intent);
			break;
		default:
			break;
		}
	}

	/**
	 * gridview适配器
	 * 
	 * @author daiye
	 * 
	 */
	public class LifeGridViewAdapter extends BaseAdapter {

		private Context mContext;
		private UIAdapter uiAdapter;

		private ArrayList<GridViewEntity> data = new ArrayList<GridViewEntity>();

		public LifeGridViewAdapter(Context context, ArrayList<GridViewEntity> data) {
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
	
	public class LifeListAdapter extends BaseAdapter {

		/**
		 * 上下文对象
		 */
		private Context mContext = null;
		private ArrayList<CommoditySet> data = new ArrayList<CommoditySet>();
		private UIAdapter uiAdapter;

		public LifeListAdapter(Context ctx) {
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
				SimpleDateFormat sdf= new SimpleDateFormat("yyyy.MM.dd");
				String startDate = sdf.format(new Date(Long.parseLong(entity.getStartDate())));
				String expireDate = sdf.format(new Date(Long.parseLong(entity.getEXPIREDDATE())));
				holder.tv_time.setText("选购日期：" + startDate + " - "
						+ expireDate);
			} else {
				holder.layout_title.setVisibility(View.GONE);
				holder.layout_type.setVisibility(View.VISIBLE);
				holder.layout_type.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						Intent intent = new Intent();
						intent.putExtra(OrderDetailActivity.EXTRA_COMMSETID, entity.getId());
						intent.setClass(mContext, OrderDetailActivity.class);
						mContext.startActivity(intent);
					}
				});
				holder.btn_buy.setTag(entity);
				
				// 加载头像
				if (entity.getAppPicture() != null) {
					ImageLoader.getInstance().displayImage(
							Constants.IMGSURL + entity.getPicture(),
							holder.iv_icon);
				}

				holder.tv_name.setText(entity.getSetName());
				holder.tv_content.setText("描    述：" + Html.fromHtml(entity.getDescription()));
			}
			return convertView;
		}

		OnClickListener clicklistener = new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				switch (v.getId()) {
				case R.id.btn_buy:
					Intent intent = new Intent();
					intent.putExtra(OrderDetailActivity.EXTRA_COMMODITYSET, (CommoditySet) v.getTag());
					intent.setClass(mContext, OrderConfirmActivity.class);
					mContext.startActivity(intent);
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
		}
	}
	
	@Override
	public void onItemClick(AdapterView<?> arg0, View v, int arg2, long arg3) {
//		switch (v.getId()) {
//		case R.id.:
//			
//			break;
//
//		default:
//			break;
//		}
	}
}
