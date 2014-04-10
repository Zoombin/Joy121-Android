package com.joy.Activity;

import gejw.android.quickandroid.QActivity;
import gejw.android.quickandroid.bmp.BmpUtils;
import gejw.android.quickandroid.utils.ResName2ID;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import android.content.res.Resources;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v4.app.FragmentTabHost;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;

import com.joy.R;
import com.joy.Fragment.HomeFragment;
import com.joy.Fragment.LifeFragment;
import com.joy.Fragment.MallFragment;
import com.joy.Fragment.PersonalFragment;
import com.joy.Fragment.WelfareFragment;
import com.umeng.analytics.MobclickAgent;

public class MainActivity extends QActivity {

	public static MainActivity mActivity = null;
	private Resources resources;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		resources = getResources();
		initTab();
	}

	/************************** Tab布局 ***************************************/
	// 定义FragmentTabHost对象
	public FragmentTabHost mTabHost;
	// 定义一个布局
	private LayoutInflater layoutInflater;
	// 定义数组来存放Fragment界面
	private Class<?> fragmentArray[] = { HomeFragment.class,
			WelfareFragment.class, LifeFragment.class, MallFragment.class,
			PersonalFragment.class };
	// 定义数组来存放按钮图片
	private String mImageViewArray[] = { "menu_home", "menu_welfare",
			"menu_life", "menu_mall", "menu_personal" };
	// Tab选项卡的文字
	private int mTextviewArray[] = { R.string.menu_home, R.string.menu_welfare,
			R.string.menu_life, R.string.menu_mall, R.string.menu_personal };
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

	/**
	 * 给Tab按钮设置图标和文字
	 */
	private View getTabItemView(int index) {
		View view = layoutInflater
				.inflate(R.layout.layout_main_menu_item, null);
		uiAdapter.setMargin(view, 95, LayoutParams.MATCH_PARENT, 0, 0, 0, 0);

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
}
