package com.joy.Fragment;

import gejw.android.quickandroid.QFragment;
import gejw.android.quickandroid.ui.adapter.UIAdapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.joy.R;
import com.joy.Utils.Constants;

/**
 * 生活服务模块
 * 
 * @author daiye
 * 
 */
public class LifeFragment extends BaseFragment implements OnClickListener {

	private RelativeLayout layout_title;
	private ImageView iv_title;
	private TextView tv_title;
	private LinearLayout layout_menu;
	private LinearLayout layout_life;
	private ImageView iv_life;
	private TextView tv_life;
	private LinearLayout layout_health;
	private ImageView iv_health;
	private TextView tv_health;
	private LinearLayout layout_safety;
	private ImageView iv_safety;
	private TextView tv_safety;
	private ListView list_life;
	private ListView list_health;
	private ListView list_safety;
	private Resources resources;
	private final String[] lifelist = {"电影票务", "游泳健身", "休闲旅游", "教育培训"};
	private final String[] healthlist = {"入职体检", "年度体检", "牙齿健康", "心理咨询"};
	private final String[] safetylist = {"意外险", "医疗险", "雇主险", "家属险"};
	
	/*@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_life, container, false);
		
		resources = getResources();
		initView(v);
		return v;
	}*/
	
	@Override
	protected View initContentView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View v = inflater.inflate(R.layout.fragment_life, container, false);
		
		resources = getResources();
		initView(v);
		return v;
	}
	
	private void initView(View v) {
		layout_title = (RelativeLayout) v.findViewById(R.id.layout_title);
		uiAdapter.setMargin(layout_title, LayoutParams.MATCH_PARENT, Constants.TitleHeight, 0, 0,
				0, 0);

		iv_title = (ImageView) v.findViewById(R.id.iv_title);
		uiAdapter.setMargin(iv_title, Constants.TitleIvWidth, Constants.TitleIvWidth, 10, 0, 10, 0);
		
		tv_title = (TextView) v.findViewById(R.id.tv_title);
		uiAdapter.setTextSize(tv_title, Constants.TitleSize);
		
		layout_menu = (LinearLayout) v.findViewById(R.id.layout_menu);
		uiAdapter.setMargin(layout_menu, LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT, 0, 0, 0, 10);
		
		layout_life = (LinearLayout) v.findViewById(R.id.layout_life);
		layout_life.setOnClickListener(this);
		
		iv_life = (ImageView) v.findViewById(R.id.iv_life);
		uiAdapter.setMargin(iv_life, 72, 72, 0, 20, 0, 6);
		
		tv_life = (TextView) v.findViewById(R.id.tv_life);
		uiAdapter.setTextSize(tv_life, 20);
		uiAdapter.setMargin(tv_life, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 0, 0, 0, 20);
		
		layout_health = (LinearLayout) v.findViewById(R.id.layout_health);
		layout_health.setOnClickListener(this);
		
		iv_health = (ImageView) v.findViewById(R.id.iv_health);
		uiAdapter.setMargin(iv_health, 72, 72, 0, 20, 0, 6);
		
		tv_health = (TextView) v.findViewById(R.id.tv_health);
		uiAdapter.setTextSize(tv_health, 20);
		uiAdapter.setMargin(tv_health, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 0, 0, 0, 20);
		
		layout_safety = (LinearLayout) v.findViewById(R.id.layout_safety);
		layout_safety.setOnClickListener(this);
		
		iv_safety = (ImageView) v.findViewById(R.id.iv_safety);
		uiAdapter.setMargin(iv_safety, 72, 72, 0, 20, 0, 6);
		
		tv_safety = (TextView) v.findViewById(R.id.tv_safety);
		uiAdapter.setTextSize(tv_safety, 20);
		uiAdapter.setMargin(tv_safety, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 0, 0, 0, 20);
		
		list_life = (ListView) v.findViewById(R.id.list_life);

		list_life.setAdapter(new LifeListAdapter(mActivity, setLiftEntityList(lifelist)));
		
		list_health = (ListView) v.findViewById(R.id.list_health);
		list_health.setAdapter(new LifeListAdapter(mActivity, setLiftEntityList(healthlist)));
		
		list_safety = (ListView) v.findViewById(R.id.list_safety);
		list_safety.setAdapter(new LifeListAdapter(mActivity, setLiftEntityList(safetylist)));
	}

	private List<LiftEntity> setLiftEntityList(String[] list) {
		List<LiftEntity> data = new ArrayList<LiftEntity>();
		for (int i=0; i<list.length; i++) {
			LiftEntity entity = new LiftEntity();
			entity.setName(list[i]);
			data.add(entity);
		}
		return data;
	}
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.layout_life:
		case R.id.layout_health:
		case R.id.layout_safety:
			showMenu(v.getId());
			break;
		default:
			break;
		}
	}

	private void showMenu(int layout) {
		switch (layout) {
		case R.id.layout_life:
			iv_life.setImageResource(R.drawable.life_pressed);
			tv_life.setTextColor(resources.getColor(R.color.title_bg));
			iv_health.setImageResource(R.drawable.health);
			tv_health.setTextColor(resources.getColor(R.color.BLACK));
			iv_safety.setImageResource(R.drawable.safety);
			tv_safety.setTextColor(resources.getColor(R.color.BLACK));
			list_life.setVisibility(View.VISIBLE);
			list_health.setVisibility(View.GONE);
			list_safety.setVisibility(View.GONE);
			break;
		case R.id.layout_health:
			iv_life.setImageResource(R.drawable.life);
			tv_life.setTextColor(resources.getColor(R.color.BLACK));
			iv_health.setImageResource(R.drawable.health_pressed);
			tv_health.setTextColor(resources.getColor(R.color.title_bg));
			iv_safety.setImageResource(R.drawable.safety);
			tv_safety.setTextColor(resources.getColor(R.color.BLACK));
			list_life.setVisibility(View.GONE);
			list_health.setVisibility(View.VISIBLE);
			list_safety.setVisibility(View.GONE);
			break;
		case R.id.layout_safety:
			iv_life.setImageResource(R.drawable.life);
			tv_life.setTextColor(resources.getColor(R.color.BLACK));
			iv_health.setImageResource(R.drawable.health);
			tv_health.setTextColor(resources.getColor(R.color.BLACK));
			iv_safety.setImageResource(R.drawable.safety_pressed);
			tv_safety.setTextColor(resources.getColor(R.color.title_bg));
			list_life.setVisibility(View.GONE);
			list_health.setVisibility(View.GONE);
			list_safety.setVisibility(View.VISIBLE);
			break;
		default:
			break;
		}
	}
	
	private class LiftEntity {
		private String name;

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}
	}
	
	private class LifeListAdapter extends BaseAdapter {

		/**
		 * 上下文对象
		 */
		private Context mContext = null;
		private List<LiftEntity> data = new ArrayList<LiftEntity>();
		private UIAdapter uiAdapter;

		public LifeListAdapter(Context ctx, List<LiftEntity> data) {
			mContext = ctx;
			this.data = data;
			uiAdapter = UIAdapter.getInstance(ctx);
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
			final LiftEntity entity = data.get(position);

			ViewHolder holder;
			if (convertView == null) {
				convertView = LayoutInflater.from(mContext).inflate(
						R.layout.life_list_item, parent, false);
				holder = new ViewHolder();

				holder.tv_name = (TextView) convertView.findViewById(R.id.tv_name);
				uiAdapter.setTextSize(holder.tv_name, 20);
				uiAdapter.setMargin(holder.tv_name, LayoutParams.WRAP_CONTENT,
						LayoutParams.WRAP_CONTENT, 34, 0, 0, 0);

				holder.iv_arrow = (ImageView) convertView.findViewById(R.id.iv_arrow);
				uiAdapter.setMargin(holder.iv_arrow, 12, uiAdapter.CalcHeight(12, 16, 28), 0, 21, 20, 21);
				
				convertView.setTag(holder);
			} else {// 有直接获得ViewHolder
				holder = (ViewHolder) convertView.getTag();
			}

			holder.tv_name.setText(entity.getName());

			return convertView;
		}
		
		public class ViewHolder {
			TextView tv_name;
			ImageView iv_arrow;
		}
	}
}
