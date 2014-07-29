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

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Point;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;

import com.joy.R;
import com.joy.Fragment.BaseFragment;
import com.joy.Fragment.LifeFragment;
import com.joy.Fragment.MallFragment;
import com.joy.Fragment.PersonalFragment;
import com.joy.Fragment.ShoppingCarFragment;
import com.joy.Fragment.PortalsFragment;
import com.joy.Fragment.TopFragment.TopPortalsFragment;
import com.joy.json.model.GoodsDetail;
import com.joy.json.model.PopularGoods;
import com.joy.json.model.ShoppingCarGoods;
import com.umeng.analytics.MobclickAgent;

public class MainActivity extends QActivity {

	public static MainActivity mActivity = null;
	private Resources resources;
	public static List<ShoppingCarGoods> goods_list = new ArrayList<ShoppingCarGoods>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mActivity = this;
		resources = getResources();
		initTab();
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
		context.sendBroadcast(intent);
	}

	public static void Add2ShopCar(Context context, GoodsDetail goods, int count) {
		//Intent intent = new Intent(ShoppingCarAction);
		//intent.putExtra("action", "add");
		ShoppingCarGoods carGoods = new ShoppingCarGoods();
		carGoods.setCount(count);
		carGoods.setGoods_id(goods.getGoods_id());
		carGoods.setGoods_img(goods.getGoods_img());
		carGoods.setGoods_name(goods.getGoods_name());
		carGoods.setShop_price(goods.getShop_price());
		carGoods.setMarket_price(goods.getMarket_price());
		carGoods.setCost_integral(goods.getCost_integral());
		carGoods.setColor(goods.getColor());
		carGoods.setSize_cloth(goods.getSize_cloth());
		carGoods.setIsLogoStore(goods.getIsLogoStore());
		
		/*for (ShoppingCarGoods sgoods : MainActivity.goods_list) {
			if (carGoods.getGoods_id().equals(sgoods.getGoods_id())) {
				sgoods.setCount(sgoods.getCount()
						+ carGoods.getCount());
				//return;
			}
		}*/
		MainActivity.goods_list.add(carGoods);
		ShoppingCarFragment.updateShoppingcar();
		MainActivity.setNotice(MainActivity.goods_list.size());
		//intent.putExtra("goods", carGoods);
		//context.sendBroadcast(intent);
	}
	
	public static void CleanShopCar(Context context) {
		Intent intent = new Intent(ShoppingCarAction);
		intent.putExtra("action", "clean");
		context.sendBroadcast(intent);
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
			TopPortalsFragment.class, MallFragment.class,ShoppingCarFragment.class,
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
		uiAdapter
				.setMargin(mTabHost, LayoutParams.MATCH_PARENT, 76, 0, 0, 0, 0);
		uiAdapter.setPadding(mTabHost, 0, 0, 0, 0);
		mTabHost.setOnTabChangedListener(new OnTabChangeListener() {

			@Override
			public void onTabChanged(String tabId) {
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
							.getColor(R.color.menu_text_press) : resources
							.getColor(R.color.menu_text));
				}
			}
		});

		// 得到fragment的个数
		int count = fragmentArray.length;
		for (int i = 0; i < count; i++) {
			// 为每一个Tab按钮设置图标、文字和内容
			TabSpec tabSpec = mTabHost.newTabSpec(getString(mTextviewArray[i]))
					.setIndicator(getTabItemView(i));
			// 将Tab按钮添加进Tab选项卡中
			mTabHost.addTab(tabSpec, fragmentArray[i], null);
		}
	}
	
	TextView notice ;//购物车数量
	public static void setNotice(int notice) {
		if (mActivity == null || mActivity.notice == null) {
			mActivity.notice.setVisibility(View.GONE);
			return;
		}

		if (notice <= 0)
			mActivity.notice.setVisibility(View.GONE);
		else
			mActivity.notice.setVisibility(View.VISIBLE);
		mActivity.notice.setText(String.valueOf(notice));
	}

	/**
	 * 给Tab按钮设置图标和文字
	 */
	private View getTabItemView(int index) {
		View view = layoutInflater
				.inflate(R.layout.layout_main_menu_item, null);
		uiAdapter.setMargin(view, 120, LayoutParams.MATCH_PARENT, 0, 0, 0, 0);

		// 图标
		ImageView imageView = (ImageView) view.findViewById(R.id.img_menu);
		int iconid = ResName2ID.getDrawableID(self, mImageViewArray[index]);
		imageView.setImageResource(iconid);
		imageView.setTag(mImageViewArray[index]);
		Point bmpSize = BmpUtils.getBmpSizeFromRes(getResources(), iconid);
		float width = uiAdapter.CalcWidth(40, bmpSize.x, bmpSize.y);
		uiAdapter.setMargin(imageView, width, 40, 0, 5, 0, 3);

		// 文字
		TextView textView = (TextView) view.findViewById(R.id.txt_menu);
		textView.setText(mTextviewArray[index]);
		uiAdapter.setTextSize(textView, 16);
		
		if(index == 2){
			notice = (TextView) view.findViewById(R.id.tx_notice);
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
			Toast.show(self, "再按一次退出程序");
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
}
