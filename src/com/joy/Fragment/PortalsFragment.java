package com.joy.Fragment;

import gejw.android.quickandroid.utils.ResName2ID;
import gejw.android.quickandroid.widget.Toast;

import java.util.List;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.joy.R;
import com.joy.R.layout;
import com.joy.Activity.ActivityActivity;
import com.joy.Activity.ContactActivity;
import com.joy.Activity.MainActivity;
import com.joy.Activity.PostActivity;
import com.joy.Activity.SurveyActivity;
import com.joy.Fragment.portals.logostore.LogoStoreFragment;
import com.joy.Fragment.portals.welfare.WelfareFragment;
import com.joy.Utils.Constants;
import com.joy.json.JsonCommon;
import com.joy.json.JsonCommon.OnOperationListener;
import com.joy.json.model.PortalsModule;
import com.joy.json.model.PortalsModule.Module;
import com.joy.json.operation.OperationBuilder;
import com.joy.json.operation.impl.PortalsModulesOp;
import com.nostra13.universalimageloader.core.ImageLoader;

/**
 * 公司门户
 * 
 * @author daiye
 * 
 */
public class PortalsFragment extends BaseFragment implements OnClickListener {

	private final int IMAGEVIEWWH = 80;
	private final int TEXTSIZE = 20;
	private Resources resources;
	private RelativeLayout layout_title;
	private ImageView iv_title;
	private TextView tv_title;
	private ImageView ivLogo;
	
	private LinearLayout layout_welfare;
	private TextView tv_welfare;
	private ImageView iv_welfare;
	
	private LinearLayout layout_logostore;
	private TextView tv_logostore;
	private ImageView iv_logostore;
	
	private LinearLayout layout_activity;
	private TextView tv_activity;
	private ImageView iv_activity;
	private LinearLayout layout_train;
	private TextView tv_train;
	private ImageView iv_train;
	private LinearLayout layout_notice;
	private TextView tv_notice;
	private ImageView iv_notice;
	private LinearLayout layout_suivey;
	private TextView tv_suivey;
	private ImageView iv_suivey;
	private LinearLayout layout_contact;
	private TextView tv_contact;
	private ImageView iv_contact;
	private LinearLayout layout_groupbuy;
	private TextView tv_groupbuy;
	private ImageView iv_groupbuy;
	private LinearLayout layout_shop;
	private TextView tv_shop;
	private ImageView iv_shop;
	
	private GridView moduleGrid;
	private ModulesAdapter adapter;
	List<Module> temp;
	
	/*@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_welfare, container, false);
		
		resources = getResources();
		initView(v);

		return v;
	}*/
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		resources = getResources();
		
		if(adapter == null){
			adapter = new ModulesAdapter();
		}
	}
	@Override
	protected View initContentView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View v = inflater.inflate(R.layout.fragment_welfare, container, false);
		
		initView(v);

		if(temp != null && temp.size()>0){
			//第二次显示，从temp读取modules数据
			adapter.setData(temp);
		}else{
			//第一次显示，从API读书modules数据
			getModules();
		}
		return v;
	}
	
	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		if(appSet != null){
			/*int imgid = 0;
			try {
				imgid =	ResName2ID.getDrawableID(mActivity, appSet.getLogo().replaceAll(".png", ""));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
			//if(imgid != 0){
			//	tv_title.setVisibility(View.GONE);
			//	ivLogo.setVisibility(View.VISIBLE);
			//	ivLogo.setImageResource(imgid);
			//}
			tv_title.setVisibility(View.GONE);
			ImageLoader.getInstance().displayImage(Constants.IMGLOGO + appSet.getLogo(), ivLogo);
			ivLogo.setScaleType(ScaleType.CENTER_INSIDE);
			ivLogo.setVisibility(View.VISIBLE);
			
			
			
			//LayoutParams mParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
			//ivLogo.setLayoutParams(mParams);
			
		}
	}

	private void initView(View v) {
		layout_title = (RelativeLayout) v.findViewById(R.id.layout_title);
		uiAdapter.setMargin(layout_title, LayoutParams.MATCH_PARENT, Constants.TitleHeight, 0, 0, 0, 0);
		
		iv_title = (ImageView) v.findViewById(R.id.iv_title);
		uiAdapter.setMargin(iv_title, Constants.TitleIvWidth, Constants.TitleIvWidth, 10, 0, 10, 0);
		
		tv_title = (TextView) v.findViewById(R.id.tv_title);
		uiAdapter.setTextSize(tv_title, Constants.TitleSize);
		
		ivLogo = (ImageView) v.findViewById(R.id.iv_logo);
		// 公司福利
		layout_welfare = (LinearLayout) v.findViewById(R.id.layout_welfare);
		layout_welfare.setOnClickListener(this);
		
		tv_welfare = (TextView) v.findViewById(R.id.tv_welfare);
		uiAdapter.setTextSize(tv_welfare, TEXTSIZE);
		uiAdapter.setPadding(tv_welfare, 5, 5, 0, 0);
		
		iv_welfare = (ImageView) v.findViewById(R.id.iv_welfare);
		uiAdapter.setMargin(iv_welfare, IMAGEVIEWWH, IMAGEVIEWWH, 0, 10, 0, 20);
		
		// Logo商店
		layout_logostore = (LinearLayout) v.findViewById(R.id.layout_logostore);
		layout_logostore.setOnClickListener(this);
				
		tv_logostore = (TextView) v.findViewById(R.id.tv_logostore);
		uiAdapter.setTextSize(tv_logostore, TEXTSIZE);
		uiAdapter.setPadding(tv_logostore, 5, 5, 0, 0);
				
		iv_logostore = (ImageView) v.findViewById(R.id.iv_logostore);
		uiAdapter.setMargin(iv_logostore, IMAGEVIEWWH, IMAGEVIEWWH, 0, 10, 0, 20);
		
		// 活动
		layout_activity = (LinearLayout) v.findViewById(R.id.layout_activity);
		layout_activity.setOnClickListener(this);
						
		tv_activity = (TextView) v.findViewById(R.id.tv_activity);
		uiAdapter.setTextSize(tv_activity , TEXTSIZE);
		uiAdapter.setPadding(tv_activity , 5, 5, 0, 0);
						
		iv_activity = (ImageView) v.findViewById(R.id.iv_activity);
		uiAdapter.setMargin(iv_activity, IMAGEVIEWWH, IMAGEVIEWWH, 0, 10, 0, 20);
		
		// 培训
		layout_train = (LinearLayout) v.findViewById(R.id.layout_train);
		layout_train.setOnClickListener(this);
								
		tv_train = (TextView) v.findViewById(R.id.tv_train);
		uiAdapter.setTextSize(tv_train, TEXTSIZE);
		uiAdapter.setPadding(tv_train, 5, 5, 0, 0);
								
		iv_train = (ImageView) v.findViewById(R.id.iv_train);
		uiAdapter.setMargin(iv_train, IMAGEVIEWWH, IMAGEVIEWWH, 0, 10, 0, 20);	
				
		// 通知
		layout_notice = (LinearLayout) v.findViewById(R.id.layout_notice);
		layout_notice.setOnClickListener(this);
								
		tv_notice = (TextView) v.findViewById(R.id.tv_notice);
		uiAdapter.setTextSize(tv_notice, TEXTSIZE);
		uiAdapter.setPadding(tv_notice, 5, 5, 0, 0);
								
		iv_notice = (ImageView) v.findViewById(R.id.iv_notice);
		uiAdapter.setMargin(iv_notice, IMAGEVIEWWH, IMAGEVIEWWH, 0, 10, 0, 20);
		
		// 调查
		layout_suivey = (LinearLayout) v.findViewById(R.id.layout_suivey);
		layout_suivey.setOnClickListener(this);
										
		tv_suivey = (TextView) v.findViewById(R.id.tv_suivey);
		uiAdapter.setTextSize(tv_suivey, TEXTSIZE);
		uiAdapter.setPadding(tv_suivey, 5, 5, 0, 0);
										
		iv_suivey = (ImageView) v.findViewById(R.id.iv_suivey);
		uiAdapter.setMargin(iv_suivey, IMAGEVIEWWH, IMAGEVIEWWH, 0, 10, 0, 20);

		// 通讯录
		layout_contact = (LinearLayout) v.findViewById(R.id.layout_contact);
		layout_contact.setOnClickListener(this);
												
		tv_contact = (TextView) v.findViewById(R.id.tv_contact);
		uiAdapter.setTextSize(tv_contact, TEXTSIZE);
		uiAdapter.setPadding(tv_contact, 5, 5, 0, 0);
												
		iv_contact = (ImageView) v.findViewById(R.id.iv_contact);
		uiAdapter.setMargin(iv_contact, IMAGEVIEWWH, IMAGEVIEWWH, 0, 10, 0, 20);
		
		// 限时团购
		layout_groupbuy = (LinearLayout) v.findViewById(R.id.layout_groupbuy);
		layout_groupbuy.setOnClickListener(this);
														
		tv_groupbuy = (TextView) v.findViewById(R.id.tv_groupbuy);
		uiAdapter.setTextSize(tv_groupbuy, TEXTSIZE);
		uiAdapter.setPadding(tv_groupbuy, 5, 5, 0, 0);
														
		iv_groupbuy = (ImageView) v.findViewById(R.id.iv_groupbuy);
		uiAdapter.setMargin(iv_groupbuy, IMAGEVIEWWH, IMAGEVIEWWH, 0, 10, 0, 20);
				
		// 特约商户
		layout_shop = (LinearLayout) v.findViewById(R.id.layout_shop);
		layout_shop.setOnClickListener(this);
														
		tv_shop = (TextView) v.findViewById(R.id.tv_shop);
		uiAdapter.setTextSize(tv_shop, TEXTSIZE);
		uiAdapter.setPadding(tv_shop, 5, 5, 0, 0);
														
		iv_shop = (ImageView) v.findViewById(R.id.iv_shop);
		uiAdapter.setMargin(iv_shop, IMAGEVIEWWH, IMAGEVIEWWH, 0, 10, 0, 20);
		
		
		moduleGrid = (GridView) v.findViewById(R.id.modeule_grid);
		moduleGrid.setOnItemClickListener(itemClickListener);
		moduleGrid.setAdapter(adapter);
	}
	
	@Override
	public void onClick(View v) {
		Intent intent = new Intent();
		switch (v.getId()) {
		case R.id.layout_welfare:
			//福利
			//intent.setClass(mActivity, WelfareActivity.class);
			//startActivity(intent);
			
			MainActivity.mActivity.replaceChildFragment(
					"WelfareFragment", new WelfareFragment(), true);
			break;
		case R.id.layout_notice:
			//公告
			intent.setClass(mActivity, PostActivity.class);
			startActivity(intent);
			break;
		case R.id.layout_activity:
			//活动
			intent.setClass(mActivity, ActivityActivity.class);
			intent.putExtra("acttype", "1");
			startActivity(intent);
			
			/*ActivityFragment aFragment = new ActivityFragment();
            Bundle bundle = new Bundle();  
            bundle.putString("acttype", "1");  
            aFragment.setArguments(bundle); 
            
        	MainActivity.mActivity.replaceChildFragment("ActivityFragment", aFragment, true);*/
			break;
		case R.id.layout_suivey:
			//调查
			intent.setClass(mActivity, SurveyActivity.class);
			startActivity(intent);
			break;
		case R.id.layout_train:
			//培训
			intent.setClass(mActivity, ActivityActivity.class);
			intent.putExtra("acttype", "2");
			startActivity(intent);
			break;
		case R.id.layout_logostore:
			//intent.setClass(mActivity, LogoStoreActivity.class);
			//startActivity(intent);
			///new WelfFragmentOp().replace(new LogoStoreFragment(), true);
			MainActivity.mActivity.replaceChildFragment(
					"LogoStoreFragment", new LogoStoreFragment(), true);
			break;
		default:
			break;
		}
	}
	
	
	
	/***
	 * 获取接口模块
	 */
	private void getModules() {
		OperationBuilder builder = new OperationBuilder().append(
				new PortalsModulesOp(), null);
		OnOperationListener listener = new OnOperationListener() {
			@Override
			public void onOperationFinished(List<Object> resList) {
				if (mActivity.isFinishing()) {
					return;
				}
				if (resList == null) {
					// Toast.show(mActivity, "连接超时");
					return;
				}
				PortalsModule entity = (PortalsModule) resList.get(0);
				if(1== entity.getFlag()){
					List<Module> modules = entity.getRetobj();
					if(modules != null && modules.size()>0){
						adapter.setData(modules);
						temp = modules;
					}
				}else{
					Toast.show(mActivity, entity.getMsg());
				}
			}

			@Override
			public void onOperationError(Exception e) {
				e.printStackTrace();
			}
		};
		JsonCommon task = new JsonCommon(mActivity, builder, listener, true);
		task.execute();
	}
	
	
	/***
	 * GridView选项点击
	 */
	OnItemClickListener itemClickListener = new OnItemClickListener() {
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
			// TODO Auto-generated method stub
			Module module = (Module) adapter.getItem(position);
			String name = module.getModuleName();
			if(!TextUtils.isEmpty(name)){
				dispatch(name);
			};
		}
	};
	
	
	private void dispatch(String name){
		Intent intent = new Intent();
		if(name.contains("福利")){
			//公司福利
			MainActivity.mActivity.replaceChildFragment(
					"WelfareFragment", new WelfareFragment(), true);
		}else if(name.contains("商店")){
			//Logo商店
			MainActivity.mActivity.replaceChildFragment(
					"LogoStoreFragment", new LogoStoreFragment(), true);
		}else if(name.contains("活动")){
			//活动
			intent.setClass(mActivity, ActivityActivity.class);
			intent.putExtra("acttype", "1");
			startActivity(intent);
		}else if(name.contains("培训")){
			//培训
			intent.setClass(mActivity, ActivityActivity.class);
			intent.putExtra("acttype", "2");
			startActivity(intent);
		}else if(name.contains("公告")){
			//公告
			intent.setClass(mActivity, PostActivity.class);
			startActivity(intent);
		}else if(name.contains("调查")){
			//调查
			intent.setClass(mActivity, SurveyActivity.class);
			startActivity(intent);
		}else if(name.contains("通讯")){
			//通讯簿
			intent.setClass(mActivity, ContactActivity.class);
			startActivity(intent);
		}else if(name.contains("团购")){
			//限时团购
		}else if(name.contains("商户")){
			//特约商户
		}else{
		}
	}
	
	
	
	class ModulesAdapter extends BaseAdapter {
		List<Module> datas;
		public void setData(List<Module> datas){
			this.datas = datas;
			this.notifyDataSetChanged();
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return datas == null ? 0 : datas.size();
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return datas == null ? null : datas.get(position);
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return datas == null ? 0 : position;
		}

		@Override
		public View getView(int position, View conventView, ViewGroup parent) {
			// TODO Auto-generated method stub
			Tag tag;
			if(conventView == null){
				tag = new Tag();
				conventView = LayoutInflater.from(parent.getContext()).inflate(R.layout.portals_module_item, null);
				tag.moduleIcon = (ImageView) conventView.findViewById(R.id.iv_icon);
				tag.moduleName= (TextView) conventView.findViewById(R.id.tv_name);
				conventView.setTag(tag);
			}else{
				tag = (Tag) conventView.getTag();
			}
			
			uiAdapter.setTextSize(tag.moduleName, TEXTSIZE);
			uiAdapter.setPadding(tag.moduleName, 5, 5, 0, 0);
			
			uiAdapter.setMargin(tag.moduleIcon, IMAGEVIEWWH, IMAGEVIEWWH, 0, 10, 0, 20);
			
			Module data = (Module) getItem(position);
			if(data != null){
				String name = data.getModuleName();
				if(!TextUtils.isEmpty(name)){
					tag.moduleName.setText(name);
					fitView(name, conventView, tag.moduleIcon);
				}
			}
			return conventView;
		}
		
		private void fitView(String name,View v,ImageView imageView){
			if(name.contains("福利")){
				//公司福利
				v.setBackgroundColor(Color.parseColor("#2e8aef"));
				imageView.setImageResource(R.drawable.com_benefits);
			}else if(name.contains("商店")){
				//Logo商店
				v.setBackgroundColor(Color.parseColor("#474cfd"));
				imageView.setImageResource(R.drawable.com_logostore);
			}else if(name.contains("活动")){
				//活动
				v.setBackgroundColor(Color.parseColor("#5e3cba"));
				imageView.setImageResource(R.drawable.com_event);
			}else if(name.contains("培训")){
				//培训
				v.setBackgroundColor(Color.parseColor("#7ab102"));
				imageView.setImageResource(R.drawable.com_training);
			}else if(name.contains("公告")){
				//公告
				v.setBackgroundColor(Color.parseColor("#01a31c"));
				imageView.setImageResource(R.drawable.com_notice);
			}else if(name.contains("调查")){
				//调查
				v.setBackgroundColor(Color.parseColor("#13771c"));
				imageView.setImageResource(R.drawable.com_survey);
			}else if(name.contains("通讯")){
				//通讯簿
				v.setBackgroundColor(Color.parseColor("#dfb700"));
				imageView.setImageResource(R.drawable.com_contacts);
			}else if(name.contains("团购")){
				//限时团购
				v.setBackgroundColor(Color.parseColor("#f7a211"));
				imageView.setImageResource(R.drawable.com_groupon);
			}else if(name.contains("工资")){
				//特约商户
				v.setBackgroundColor(Color.parseColor("#fe8649"));
				imageView.setImageResource(R.drawable.com_businessman);
			}else{
				v.setBackgroundColor(Color.parseColor("#fe8649"));
				imageView.setImageResource(R.drawable.img_default);
			}
		}

		class Tag{
			ImageView moduleIcon;
			TextView moduleName;
		}
	}
}
