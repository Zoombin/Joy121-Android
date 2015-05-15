package com.joy.Activity;

import gejw.android.quickandroid.QActivity;
import gejw.android.quickandroid.bmp.BmpUtils;
import gejw.android.quickandroid.log.PLog;
import gejw.android.quickandroid.utils.ResName2ID;
import gejw.android.quickandroid.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import android.app.Fragment;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;

import com.joy.JoyApplication;
import com.joy.R;
import com.joy.Fragment.BaseFragment;
import com.joy.Fragment.LifeFragment;
import com.joy.Fragment.MallFragment;
import com.joy.Fragment.PersonalFragment;
import com.joy.Fragment.ShoppingCarFragment;
import com.joy.Fragment.PortalsFragment;
import com.joy.Fragment.TopFragment.TopMallFragment;
import com.joy.Fragment.TopFragment.TopPortalsFragment;
import com.joy.Fragment.portals.logostore.LogoStoreFragment;
import com.joy.Utils.MobileCodeManager;
import com.joy.Utils.SharedPreferencesUtils;
import com.joy.Utils.UpdateManager;
import com.joy.json.JsonCommon;
import com.joy.json.JsonCommon.OnOperationListener;
import com.joy.json.model.CompAppSet;
import com.joy.json.model.GoodsDetail;
import com.joy.json.model.LoginEntity;
import com.joy.json.model.PopularGoods;
import com.joy.json.model.ShoppingCarGoods;
import com.joy.json.model.UserInfoEntity;
import com.joy.json.operation.OperationBuilder;
import com.joy.json.operation.impl.LoginOp;
import com.joy.receiver.PushUtil;
import com.joy.receiver.PushUtil.CommondModel;
import com.umeng.analytics.MobclickAgent;

public class MainActivity extends QActivity {

	public static MainActivity mActivity = null;
	private Resources resources;
	CompAppSet appSet;
	private final String LOCATION = "main";
	public static List<ShoppingCarGoods> goods_list = new ArrayList<ShoppingCarGoods>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);//父类的生命周期方法
		setContentView(R.layout.activity_main);//在窗口上显示activity_main元件界面
		mActivity = this;
		resources = getResources();
		appSet = JoyApplication.getInstance().getCompAppSet();
		final UpdateManager updatemanager;
<<<<<<< HEAD
=======
		
>>>>>>> 34acdd014449076be19c67258f14caec9568e50d
		if(JoyApplication.getInstance().getUserinfo() == null){
			login(mActivity);
		}
		
		initTab();
		updatemanager = new UpdateManager(self, self);
		
		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				checkPush(getIntent());
<<<<<<< HEAD
				updatemanager.checkUpdate(LOCATION);
=======
				//updatemanager.checkUpdate(LOCATION);
>>>>>>> 34acdd014449076be19c67258f14caec9568e50d
			}
		}, 500);
	}
	
	@Override
	protected void onNewIntent(Intent intent) {
		// TODO Auto-generated method stub
		checkPush(intent);
	}
	
	private void checkPush(Intent intent) {
		// /＝＝＝ 推送
		Bundle pushBundle = intent.getExtras();
		if (pushBundle != null && pushBundle.containsKey("push")) {
			CommondModel model = (CommondModel) pushBundle
					.getSerializable("push");
			if (model != null) {
				mTabHost.setCurrentTab(0);
				PushUtil pushUtil = new PushUtil(MainActivity.this);
				pushUtil.dispatch(model);
			}
		}
	}
	
	public static void Add2ShopCar(Context context, PopularGoods goods) {
		Add2ShopCar(context, goods, 1);
	}

	public static void Add2ShopCar(Context context, PopularGoods goods,
			int count) {
		Intent intent = new Intent(ShoppingCarAction);
		intent.putExtra("action", "add");
		ShoppingCarGoods carGoods = new ShoppingCarGoods();
		carGoods.setCount(count);
		carGoods.setGoods_id(goods.getGoods_id());
		carGoods.setGoods_img(goods.getGoods_thumb());
		carGoods.setGoods_name(goods.getGoods_name());
		carGoods.setShop_price(goods.getShop_price());
		carGoods.setMarket_price(goods.getMarket_price());
		carGoods.setCost_integral(goods.getCost_integral());
		intent.putExtra("goods", carGoods);
		//此方法没处理
		//context.sendBroadcast(intent);
	}

	public static void Add2ShopCar(Context context, GoodsDetail good, int count) {
		//Intent intent = new Intent(ShoppingCarAction);
		//intent.putExtra("action", "add");
		ShoppingCarGoods carGoods = new ShoppingCarGoods();
		carGoods.setCount(count);
		carGoods.setGoods_id(good.getGoods_id());
		carGoods.setGoods_img(good.getGoods_img());
		carGoods.setGoods_name(good.getGoods_name());
		carGoods.setShop_price(good.getShop_price());
		carGoods.setMarket_price(good.getMarket_price());
		carGoods.setCost_integral(good.getCost_integral());
		carGoods.setColor(good.getColor());
		carGoods.setSize_cloth(good.getSize_cloth());
		carGoods.setIsLogoStore(good.getIsLogoStore());
		carGoods.setPoints(good.getPoints());
		
		
		boolean add2Car = true;
		for (ShoppingCarGoods tempgoods : MainActivity.goods_list) {
			if (carGoods.getGoods_id().equals(tempgoods.getGoods_id())) {
				if(carGoods.getIsLogoStore()){
					if ((!"".equals(tempgoods.getColor()) && tempgoods.getColor().equals(carGoods.getColor()))
							&& (!"".equals(tempgoods.getSize_cloth()) && tempgoods.getSize_cloth().equals(carGoods.getSize_cloth()))) {
						//不用添加
						tempgoods.setCount(tempgoods.getCount() + carGoods.getCount());
						add2Car = false;
					}
				}else{
					//不用添加
					tempgoods.setCount(tempgoods.getCount() + carGoods.getCount());
					add2Car = false;
				}
			}
		}
		
		if(add2Car){
			MainActivity.goods_list.add(0,carGoods);
			ShoppingCarFragment.updateShoppingcar();
			MainActivity.setNotice();
		}
		//intent.putExtra("goods", carGoods);
		//context.sendBroadcast(intent);
	}
	
	/**
	 * 清空购物车
	 * @param context
	 */
	public static void CleanShopCar(Context context) {
		//Intent intent = new Intent(ShoppingCarAction);
		//intent.putExtra("action", "clean");
		//context.sendBroadcast(intent);
		
		MainActivity.goods_list.clear();;
		ShoppingCarFragment.updateShoppingcar();
		MainActivity.setNotice();
	}
	
	/***
	 * 刷新tab颜色
	 * @param color
	 */
	public void refreshTobColor() {
		if(mActivity == null && mTabHost == null){
			return;
		}
		
		if(appSet != null){
			
			int tabColor = 0;
			try {
				tabColor = Color.parseColor(appSet.getColor1());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(tabColor !=0){
				mTabHost.setBackgroundColor(tabColor);
			}
		}
	}

	/*-------------------广播处理------------------------------*/
	public static final String ShoppingCarAction = "me.dushuhu.android.ShoppingCarAction";
	BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {

		@Override
		public void onReceive(Context context, Intent intent) {
			if (intent != null && intent.getAction() != null
					&& intent.getAction().equals(ShoppingCarAction)) {
				// 接收到广播
				String action = intent.getStringExtra("action");
				if (action.equals("add")) {
					// 添加到购物车
					ShoppingCarGoods carGoods = (ShoppingCarGoods) intent
							.getSerializableExtra("goods");
					for (ShoppingCarGoods goods : MainActivity.goods_list) {
						if (carGoods.getGoods_id().equals(goods.getGoods_id())) {
							goods.setCount(goods.getCount()
									+ carGoods.getCount());
							return;
						}
					}
					MainActivity.goods_list.add(carGoods);
					ShoppingCarFragment.updateShoppingcar();
				}
				else if(action.equals("clean")){
					goods_list.clear();
					ShoppingCarFragment.updateShoppingcar();
				}
			}
		}
	};
	
	
	
	/************************** Tab布局 ***************************************/
	// 定义FragmentTabHost对象
	public FragmentTabHost mTabHost;
	// 定义一个布局
	private LayoutInflater layoutInflater;
	// 定义数组来存放Fragment界面
	private Class<?> fragmentArray[] = {
			TopPortalsFragment.class, TopMallFragment.class,ShoppingCarFragment.class,
			PersonalFragment.class };
	// 定义数组来存放按钮图片
	private String mImageViewArray[] = {"menu_welfare",
			 "menu_store","menu_mall", "menu_personal" };
//	"menu_life", 生活服务
//	R.string.menu_life
//  LifeFragment.class	
	
	// Tab选项卡的文字
	private int mTextviewArray[] = {R.string.menu_welfare, R.string.menu_mall, R.string.menu_buycar, R.string.menu_personal };
	private HashMap<String, View> tabViewItem = new HashMap<String, View>();

	private void initTab() {
		// 实例化布局对象
		layoutInflater = LayoutInflater.from(this);

		// 实例化TabHost对象，得到TabHost
		mTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
		mTabHost.setup(self, getSupportFragmentManager(), R.id.layout_fragment);
		uiAdapter.setMargin(mTabHost, LayoutParams.MATCH_PARENT, 70, 0, 0, 0, 0);
		uiAdapter.setPadding(mTabHost, 0, 0, 0, 0);
		/*
		 * 改用View 的点击 getTabItemView();
		 * 
		 * mTabHost.setOnTabChangedListener(new OnTabChangeListener() {
			@Override
			public void onTabChanged(String tabId) {
				tabChange(tabId);
			}
		});*/

		// 得到fragment的个数
		int count = fragmentArray.length;
		for (int i = 0; i < count; i++) {
			// 为每一个Tab按钮设置图标、文字和内容
			TabSpec tabSpec = mTabHost.newTabSpec(getString(mTextviewArray[i])).setIndicator(getTabItemView(i));
			// 将Tab按钮添加进Tab选项卡中
			mTabHost.addTab(tabSpec, fragmentArray[i], null);
		}
		mTabHost.setCurrentTab(0);
		tabChange(getString(mTextviewArray[0]));
	}
	
	
	private void tabChange(String tabId){
		View v = tabViewItem.get(tabId);
		ImageView imageView = (ImageView) v.findViewById(R.id.img_menu);
		TextView textView = (TextView) tabViewItem.get(tabId)
				.findViewById(R.id.txt_menu);

		Iterator iterator = tabViewItem.entrySet().iterator();
		while (iterator.hasNext()) {
			Map.Entry entry = (Map.Entry) iterator.next();
			ImageView img_view = (ImageView) ((View) entry.getValue())
					.findViewById(R.id.img_menu);
			int iconid = ResName2ID.getDrawableID(self, String.format(
					"%s%s", img_view.getTag().toString(),
					imageView == img_view ? "_press" : ""));
			img_view.setImageResource(iconid);

			TextView tv_view = (TextView) ((View) entry.getValue())
					.findViewById(R.id.txt_menu);
			tv_view.setTextColor(textView == tv_view ? resources
					.getColor(R.color.WHITE) : resources
					.getColor(R.color.menu_text));
		}
	}
	
	TextView notice ;//购物车数量
	public static void setNotice() {
		if (mActivity == null || mActivity.notice == null) {
			mActivity.notice.setVisibility(View.GONE);
			return;
		}
		
		int notice = MainActivity.goods_list.size();

		if (notice <= 0)
			mActivity.notice.setVisibility(View.GONE);
		else
			mActivity.notice.setVisibility(View.VISIBLE);
		mActivity.notice.setText(String.valueOf(notice));
	}
	
	/**
	 * 给Tab按钮设置图标和文字
	 */
	private View getTabItemView(final int index) {
		View view = layoutInflater
				.inflate(R.layout.layout_main_menu_item, null);
		uiAdapter.setMargin(view, 120, LayoutParams.MATCH_PARENT, 0, 0, 0, 0);
		
		LinearLayout tabItem = (LinearLayout) view.findViewById(R.id.tab_item);
		tabItem.setTag(getString(mTextviewArray[index]));
		tabItem.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				tabChange((String) v.getTag());
				if(isCurentTab((String) v.getTag())){
					cleanTop();
				}else{
					mTabHost.setCurrentTab(index);
				}
			}
		});

		// 图标
		ImageView imageView = (ImageView) view.findViewById(R.id.img_menu);
		int iconid = ResName2ID.getDrawableID(self, mImageViewArray[index]);
		imageView.setImageResource(iconid);
		imageView.setTag(mImageViewArray[index]);
		Point bmpSize = BmpUtils.getBmpSizeFromRes(getResources(), iconid);
		float width = uiAdapter.CalcWidth(40, bmpSize.x, bmpSize.y);
		uiAdapter.setMargin(imageView, width, 40, 0, 4, 0, 1);
		
		// 文字
		TextView textView = (TextView) view.findViewById(R.id.txt_menu);
		textView.setText(mTextviewArray[index]);
		uiAdapter.setTextSize(textView, 16);
		
		if(index == 2){
			notice = (TextView) view.findViewById(R.id.tx_notice);
			GradientDrawable shapeDrawable = (GradientDrawable) notice.getBackground();
			shapeDrawable.setColor(Color.parseColor(appSet.getColor2()));
			notice.setVisibility(View.VISIBLE);
			if(goods_list.size() == 0){
				notice.setVisibility(View.GONE);
			}
		}else{
			view.findViewById(R.id.tx_notice).setVisibility(View.GONE);
		}

		tabViewItem.put(getString(mTextviewArray[index]), view);
		return view;
	}

	public void onResume() {
		super.onResume();
		MobclickAgent.onResume(this);
		
		refreshTobColor();
	}

	public void onPause() {
		super.onPause();
		MobclickAgent.onPause(this);
	}
	
	/**
	 * 获取子类中的数量
	 * 
	 * @return
	 */
	public int getCurChildFragmentCount() {
		FragmentManager fm = getCurChildFragmentManager();
		if (fm != null)
			return fm.getBackStackEntryCount();
		return 0;
	}
	
	/**
	 * 跳转
	 * 
	 * @param key
	 * @param fragment
	 * @param canBack
	 */
	public void replaceChildFragment(String key, BaseFragment fragment,
			boolean canBack) {

		FragmentTransaction ft = getCurChildFragmentManager()
				.beginTransaction();
		ft.replace(R.id.child_fragment, fragment, key);
		ft.setTransition(FragmentTransaction.TRANSIT_ENTER_MASK);
		if (canBack)
			ft.addToBackStack(null);
		try {
			ft.commitAllowingStateLoss();
		} catch (Exception e) {
			PLog.e("%s", e.getMessage());
		}
	}
	
	/**
	 * 返回当前的子fragment管理
	 * 
	 * @return
	 */
	public FragmentManager getCurChildFragmentManager() {
		FragmentManager fm = getSupportFragmentManager().findFragmentByTag(
				mTabHost.getCurrentTabTag()).getChildFragmentManager();
		return fm;
	}
	public boolean Back() {
		FragmentManager fm = getCurChildFragmentManager();
		PLog.e("%s  fm.getBackStackEntryCount() = %s",
				mTabHost.getCurrentTabTag(), fm.getBackStackEntryCount());
		if (fm.getBackStackEntryCount() > 1) {
			fm.popBackStack();
			return true;
		}
		if (fm.getBackStackEntryCount() <= 1) {
			exit();
			return true;
		}
		return false;
	}
	
	
	/***
	 * 返回fragment顶层
	 * 
	 * @return
	 */
	public boolean cleanTop() {
		try {
			FragmentManager fm = getCurChildFragmentManager();
			PLog.e("%s  fm.getBackStackEntryCount() = %s",
					mTabHost.getCurrentTabTag(), fm.getBackStackEntryCount());
			if (fm.getBackStackEntryCount() > 1) {
				for(int i =0;i<fm.getBackStackEntryCount()-1;i++){
					fm.popBackStack();
				}
				return true;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;
	}
	
	
	public boolean isCurentTab(String tabTag) {
		try {
			String tag = mTabHost.getCurrentTabTag();
			if(tag.equals(tabTag)){
				return true;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;
	}
	
	
	boolean isExit;

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			if (Back()) {
				return true;
			} 
		}
		return super.onKeyDown(keyCode, event);
	}

	public void exit() {
		if (!isExit) {
			isExit = true;
			Toast.show(self, resources.getString(R.string.toast_clicktwice));
			mHandler.sendEmptyMessageDelayed(0, 2000);
		} else {
			Intent intent = new Intent(Intent.ACTION_MAIN);
			intent.addCategory(Intent.CATEGORY_HOME);
			startActivity(intent);
			System.exit(0);
		}
	}

	Handler mHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			isExit = false;
		}
	};
	
	/***
	 * 登陆获取用户数据
	 * @param context
	 * @param loginname
	 * @param loginpwd
	 */
	public void login(final Context context ) {
		final String loginname = SharedPreferencesUtils.getLoginName(context);
		final String loginpwd = SharedPreferencesUtils.getLoginPwd(context);
		if (TextUtils.isEmpty(loginname) || TextUtils.isEmpty(loginpwd)) {
			return;
		}
		LoginEntity loginentity = new LoginEntity();
		loginentity.setLoginname(loginname);
		loginentity.setLoginpwd(loginpwd);
		
		OperationBuilder builder = new OperationBuilder().append(
				new LoginOp(), loginentity);
		OnOperationListener listener = new OnOperationListener() {
			@Override
			public void onOperationFinished(List<Object> resList) {
				if (resList == null) {
					return;
				}
				LoginEntity entity = (LoginEntity) resList.get(0);
				UserInfoEntity userInfoEntity = entity.getRetobj();
				if (userInfoEntity == null) {
					return;
				}
				
				JoyApplication.getInstance().setUserinfo(userInfoEntity);
				SharedPreferencesUtils.setLoginName(context, loginname);
				SharedPreferencesUtils.setLoginPwd(context, loginpwd);
				SharedPreferencesUtils.setCompany(context, userInfoEntity.getCompany());
				refreshTobColor();
			}
			@Override
			public void onOperationError(Exception e) {
				e.printStackTrace();
			}
		};
		JsonCommon task = new JsonCommon(context, builder, listener,
				false);
		task.execute();
	}
}
