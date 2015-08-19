package com.joy.Fragment;

import gejw.android.quickandroid.widget.Toast;

import java.util.List;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.text.TextUtils;
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
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.joy.JoyApplication;
import com.joy.R;
import com.joy.Activity.ActivityActivity;
import com.joy.Activity.AttendanceActivity;
import com.joy.Activity.ContactActivity;
import com.joy.Activity.EntryManagementActiviy;
import com.joy.Activity.MainActivity;
import com.joy.Activity.MotivationActivity;
import com.joy.Activity.PayrollActivity;
import com.joy.Activity.PerformanceActivity;
import com.joy.Activity.PostActivity;
import com.joy.Activity.RuleActivity;
import com.joy.Activity.SurveyActivity;
import com.joy.Activity.WelfareActivity;
import com.joy.Dialog.DialogUtil;
import com.joy.Fragment.portals.logostore.LogoStoreFragment;
import com.joy.Fragment.rent.RentGoodsListFragment;
import com.joy.Utils.Constants;
import com.joy.Utils.Utils;
import com.joy.json.JsonCommon;
import com.joy.json.JsonCommon.OnOperationListener;
import com.joy.json.model.ActivityDetailEntity;
import com.joy.json.model.PortalsModule;
import com.joy.json.model.PortalsModule.Module;
import com.joy.json.operation.OperationBuilder;
import com.joy.json.operation.impl.PortalsModulesOp;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

/**
 * 公司门户
 * 
 * @author daiye
 * 
 */
public class PortalsFragment extends BaseFragment implements OnClickListener {

	private final int IMAGEVIEWWIDTH = 48;
	private final int IMAGEVIEWHEIGHT = 48;
	private final int TEXTSIZE = 18;
	private Resources resources;
	private RelativeLayout layout_title;
	private ImageView iv_title;
	private TextView tv_title;
	private ImageView ivLogo;
	
	private String[] colors;
	private int colorIndex;
	
	private DialogUtil dialogUtil;
	
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
		dialogUtil = new DialogUtil(getActivity(), v);
		initView(v);
		
		//colors = new String[]{"#80"+appSet.getColor1().substring(1), "#80"+appSet.getColor1().substring(1), "#80"+appSet.getColor1().substring(1)
		//		, "#80"+appSet.getColor1().substring(1), "#80"+appSet.getColor1().substring(1), "#80"+appSet.getColor1().substring(1)
		//		, "#80"+appSet.getColor1().substring(1), "#80"+appSet.getColor1().substring(1), "#80"+appSet.getColor1().substring(1)
		//		, "#80"+appSet.getColor1().substring(1), "#80"+appSet.getColor1().substring(1), "#80"+appSet.getColor1().substring(1)};
		colors = new String[]{"#996f93f4", "#99466dd7", "#99113db2", "#998de86e", "#9958d22f", "#992ca205"
				, "#99fab65f", "#99fca433", "#99fd8f02", "#99fd6565", "#99fe3d3d", "#99f10028"}; 

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
			DisplayImageOptions options = Utils.getImageOptions();
			ImageLoader.getInstance().displayImage(Constants.IMGLOGO + appSet.getLogo(), ivLogo, options);
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
		
		moduleGrid = (GridView) v.findViewById(R.id.modeule_grid);
		moduleGrid.setOnItemClickListener(itemClickListener);
		moduleGrid.setAdapter(adapter);
	}
	
	@Override
	public void onClick(View v) {
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
		final Intent intent = new Intent();
		if(name.contains("公司福利")){
			//公司福利
			//MainActivity.mActivity.replaceChildFragment(
				//	"WelfareFragment", new WelfareFragment(), true);
			intent.setClass(mActivity, WelfareActivity.class);
			startActivity(intent);

		}else if(name.contains("Logo商店")){
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
		}else if(name.contains("通讯录")){
			//通讯簿
			intent.setClass(mActivity, ContactActivity.class);
			startActivity(intent);
			/*dialogUtil.showPwdDialog("提示", 0, "请输入登录密码查看通讯录"
					, SharedPreferencesUtils.getLoginPwd(JoyApplication.getSelf()), "确认", "取消", new DialogButtonClickCallback() {
				@Override
				public void positiveButtonClick() {
					intent.setClass(mActivity, ContactActivity.class);
					startActivity(intent);
				}
				@Override
				public void negativeButtonClick() {
				}
			});*/
		}else if(name.contains("团购")){
			//限时团购
			
		}else if(name.contains("工资单")){
			//工资单
			intent.setClass(mActivity, PayrollActivity.class);
			startActivity(intent);
			/*dialogUtil.showPwdDialog("提示", 0, "请输入登录密码查看工资单"
					, SharedPreferencesUtils.getLoginPwd(JoyApplication.getSelf()), "确认", "取消", new DialogButtonClickCallback() {
				@Override
				public void positiveButtonClick() {
					intent.setClass(mActivity, PayrollActivity.class);
					startActivity(intent);
				}
				@Override
				public void negativeButtonClick() {
				}
			});*/
		}else if(name.contains("物品领用")){
			//物品领用
			MainActivity.mActivity.replaceChildFragment(
			"RentGoodsListFragment", new RentGoodsListFragment(), true);
		}else if(name.contains("APP考勤")){
			//APP考勤
			intent.setClass(mActivity, AttendanceActivity.class);
			startActivity(intent);
		}else if(name.contains("规章制度")){
			//规章制度
			intent.setClass(mActivity, RuleActivity.class);
			startActivity(intent);
		}else if(name.contains("绩效")){
			//绩效考核
			intent.setClass(mActivity, PerformanceActivity.class);
			startActivity(intent);
		}else if(name.contains("激励")){
			//员工激励
			intent.setClass(mActivity, MotivationActivity.class);
			startActivity(intent);
		}else if(name.contains("入职管理")){
			//入职管理
			intent.setClass(mActivity,EntryManagementActiviy.class);
			startActivity(intent);
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
			uiAdapter.setPadding(tag.moduleName, 0, 0, 0, 25);
			uiAdapter.setMargin(tag.moduleIcon, IMAGEVIEWWIDTH, IMAGEVIEWHEIGHT, 0, 30, 0, 12);
			
			Module data = (Module) getItem(position);
			if(data != null){
				String name = data.getModuleName();
				if(!TextUtils.isEmpty(name)){
					tag.moduleName.setText(name);
					colorIndex = position;
					if (position+1 > colors.length) {
						colorIndex = position % colors.length;
					}
					//conventView.setBackgroundColor(Color.parseColor(colors[colorIndex]));
					GradientDrawable shapeDrawable = (GradientDrawable) conventView.getBackground();
					shapeDrawable.setColor(Color.parseColor(colors[colorIndex]));
					fitView(name, conventView, tag.moduleIcon);
				}
			}
			return conventView;
		}
		
		private void fitView(String name,View v,ImageView imageView){
			if(name.contains("公司福利")){
				//公司福利
				//v.setBackgroundColor(Color.parseColor("#2e8aef"));
				imageView.setImageResource(R.drawable.com_benefits1);
			}else if(name.contains("Logo商店")){
				//Logo商店
				//v.setBackgroundColor(Color.parseColor("#474cfd"));
				imageView.setImageResource(R.drawable.com_logostore1);
			}else if(name.contains("活动")){
				//活动
				//v.setBackgroundColor(Color.parseColor("#5e3cba"));
				imageView.setImageResource(R.drawable.com_activity1);
			}else if(name.contains("培训")){
				//培训
				//v.setBackgroundColor(Color.parseColor("#7ab102"));
				imageView.setImageResource(R.drawable.com_training1);
			}else if(name.contains("公告")){
				//公告
				//v.setBackgroundColor(Color.parseColor("#01a31c"));
				imageView.setImageResource(R.drawable.com_notice1);
			}else if(name.contains("调查")){
				//调查
				//v.setBackgroundColor(Color.parseColor("#13771c"));
				imageView.setImageResource(R.drawable.com_survey1);
			}else if(name.contains("通讯录")){
				//通讯簿
				//v.setBackgroundColor(Color.parseColor("#dfb700"));
				imageView.setImageResource(R.drawable.com_contacts1);
			}else if(name.contains("团购")){
				//限时团购
				//v.setBackgroundColor(Color.parseColor("#f7a211"));
				imageView.setImageResource(R.drawable.com_groupon);
			}else if(name.contains("工资单")){
				//工资
				//v.setBackgroundColor(Color.parseColor("#f7a211"));
				imageView.setImageResource(R.drawable.com_payroll1);
			}else if(name.contains("物品领用")){
				//物品领用
				//v.setBackgroundColor(Color.parseColor("#fe8649"));
				imageView.setImageResource(R.drawable.com_depot1);
			}else if(name.contains("APP考勤")){
				//app考勤
				//v.setBackgroundColor(Color.parseColor("#f87474"));
				imageView.setImageResource(R.drawable.com_attence1);
			}else if(name.contains("规章制度")){
				//规章制度
				//v.setBackgroundColor(Color.parseColor("#d83333"));
				imageView.setImageResource(R.drawable.com_rule);
			}else if(name.contains("销售跟踪")){
				//销售跟踪
				//v.setBackgroundColor(Color.parseColor("#d83333"));
				imageView.setImageResource(R.drawable.com_sales_new);
			}else if(name.contains("内部招聘")){
				//内部招聘
				//v.setBackgroundColor(Color.parseColor("#d83333"));
				imageView.setImageResource(R.drawable.com_recruit_new);
			}else if(name.contains("福利共享")){
				//福利共享
				//v.setBackgroundColor(Color.parseColor("#d83333"));
				imageView.setImageResource(R.drawable.com_share_new);
			}else if(name.contains("我的圈子")){
				//我的圈子
				//v.setBackgroundColor(Color.parseColor("#d83333"));
				imageView.setImageResource(R.drawable.com_social_new);
			}else if(name.contains("绩效")){
				//绩效
				//v.setBackgroundColor(Color.parseColor("#d83333"));
				imageView.setImageResource(R.drawable.com_performance1);
			}else if(name.contains("激励")){
				//激励
				//v.setBackgroundColor(Color.parseColor("#d83333"));
				imageView.setImageResource(R.drawable.com_motivation1);
			}else if(name.contains("入职管理")){
				//入职管理
				imageView.setImageResource(R.drawable.com_entry_management);
			}else{
				//v.setBackgroundColor(Color.parseColor("#a2063c"));
				//imageView.setImageResource(R.drawable.com_businessman);
			}
		}
		class Tag{
			ImageView moduleIcon;
			TextView moduleName;
		}
	}
}
