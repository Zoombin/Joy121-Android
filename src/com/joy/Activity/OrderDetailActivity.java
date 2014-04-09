package com.joy.Activity;

import gejw.android.quickandroid.QActivity;
import gejw.android.quickandroid.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.joy.R;
import com.joy.Utils.Constants;
import com.joy.Widget.PagerviewAdapter;
import com.joy.json.JsonCommon;
import com.joy.json.JsonCommon.OnOperationListener;
import com.joy.json.model.CommoditySet;
import com.joy.json.model.OrderDetailEntity;
import com.joy.json.operation.OperationBuilder;
import com.joy.json.operation.impl.OrderDetailOp;

/**
 * 订单详情
 * @author daiye
 *
 */
public class OrderDetailActivity extends QActivity implements OnClickListener {
	
	private int commsetid;
	public static final String EXTRA_COMMSETID = "commsetid";
	private RelativeLayout layout_title;
	private TextView tv_ret;
	private TextView tv_title;
	private TextView tv_setname;
	private ViewPager viewpager;
	private LinearLayout mNumLayout;
	private ImageView iv_shop;
	private TextView tv_points;
	private ImageView iv_shopping_minus;
	private EditText et_shoppingnum;
	private ImageView iv_shopping_plus;
	private Button btn_shopping;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_orderdetail);

		commsetid = getIntent().getIntExtra(EXTRA_COMMSETID, -1);
		
		initView();
		initData();
	}

	private void initView() {
		layout_title = (RelativeLayout) findViewById(R.id.layout_title);
		uiAdapter.setMargin(layout_title, LayoutParams.MATCH_PARENT, 74, 0, 0, 0, 0);

		tv_ret = (TextView) findViewById(R.id.tv_ret);
		tv_ret.setOnClickListener(this);
		uiAdapter.setTextSize(tv_ret, Constants.TitleRetSize);
		uiAdapter.setMargin(tv_ret, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 20, 0, 0, 0);
		
		tv_title = (TextView) findViewById(R.id.tv_title);
		uiAdapter.setTextSize(tv_title, Constants.TitleSize);
		
		tv_setname = (TextView) findViewById(R.id.tv_setname);
		uiAdapter.setTextSize(tv_setname, 18);
		uiAdapter.setMargin(tv_setname, LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT, 0, 10, 0, 10);
		
		viewpager = (ViewPager) findViewById(R.id.viewpager);
		uiAdapter.setMargin(viewpager, LayoutParams.MATCH_PARENT, 228, 10, 0, 0,
				0);

		mNumLayout = (LinearLayout) findViewById(R.id.ll_pager_num);
		uiAdapter.setMargin(mNumLayout, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 0, 190, 0, 0);
		
		iv_shop = (ImageView) findViewById(R.id.iv_shop);
		uiAdapter.setMargin(iv_shop, 30, uiAdapter.CalcHeight(30, 79, 70), 20, 20, 20, 20);
		
		tv_points = (TextView) findViewById(R.id.tv_points);
		uiAdapter.setTextSize(tv_points, 20);
		
		iv_shopping_minus = (ImageView) findViewById(R.id.iv_shopping_minus);
		uiAdapter.setMargin(iv_shopping_minus, 15, 15, 10, 10, 10, 10);
		
		et_shoppingnum = (EditText) findViewById(R.id.et_shoppingnum);
		uiAdapter.setMargin(et_shoppingnum, 30, LayoutParams.WRAP_CONTENT, 0, 0, 0, 0);
		
		iv_shopping_plus = (ImageView) findViewById(R.id.iv_shopping_plus);
		uiAdapter.setMargin(iv_shopping_plus, 15, 15, 10, 10, 10, 10);
		
		btn_shopping = (Button) findViewById(R.id.btn_shopping);
		uiAdapter.setMargin(btn_shopping, 80, 40, 0, 0, 20, 0);
	}
	
	private void initData() {
		OperationBuilder builder = new OperationBuilder().append(
				new OrderDetailOp(), commsetid);
		OnOperationListener listener = new OnOperationListener() {
			@Override
			public void onOperationFinished(List<Object> resList) {
				if (self.isFinishing()) {
					return;
				}
				if (resList == null) {
					Toast.show(self, "连接超时");
					return;
				}
				OrderDetailEntity entity = (OrderDetailEntity) resList.get(0);
				CommoditySet commoditySet = entity.getRetobj();
				if (commoditySet == null) {
					Toast.show(self, "无详情信息！");
					finish();
					return;
				}
				initViewForData(commoditySet);
			}

			@Override
			public void onOperationError(Exception e) {
				e.printStackTrace();
			}
		};

		JsonCommon task = new JsonCommon(self, builder, listener,
				JsonCommon.PROGRESSQUERY);
		task.execute();
	}
	
	private void initViewForData(CommoditySet commoditySet) {
		tv_setname.setText(commoditySet.getSetName());
		
		String[] pics = commoditySet.getAppPicture().split(";");
		List<String> piclist = new ArrayList<String>();
		for (String pic : pics) {
			piclist.add(Constants.IMGURL + pic);
		}
		setviewpager(piclist);
		
		tv_points.setText(Integer.toString(commoditySet.getPoints()));
	}
	
	Button mPreSelectedBt;
	private void setviewpager(List<String> piclist) {
		PagerviewAdapter pagerviewAdapter = new PagerviewAdapter(self,
				piclist);
		for (int i = 0; i < piclist.size(); i++) {
			Button bt = new Button(self);
			bt.setLayoutParams(new ViewGroup.LayoutParams(uiAdapter.CalcWidth(10), uiAdapter.CalcWidth(10)));
			bt.setBackgroundResource(R.drawable.point);
			mNumLayout.addView(bt);
		}
		viewpager.setAdapter(pagerviewAdapter);
		
		viewpager.setOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageSelected(int position) {
				if (mPreSelectedBt != null) {
					mPreSelectedBt
							.setBackgroundResource(R.drawable.point);
				}

				Button currentBt = (Button) mNumLayout.getChildAt(position);
				currentBt
						.setBackgroundResource(R.drawable.point_press);
				uiAdapter.setMargin(currentBt, 12, 12, 5, 0, 5, 0);
				mPreSelectedBt = currentBt;
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onPageScrollStateChanged(int arg0) {
				// TODO Auto-generated method stub

			}
		});
	}
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.tv_ret:
			finish();
			break;
		default:
			break;
		}
	}
}
