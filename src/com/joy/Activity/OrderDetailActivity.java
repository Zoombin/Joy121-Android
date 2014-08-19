package com.joy.Activity;

import gejw.android.quickandroid.QActivity;
import gejw.android.quickandroid.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.text.Html;
import android.text.TextUtils;
import android.view.LayoutInflater;
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

import com.joy.JoyApplication;
import com.joy.R;
import com.joy.Utils.Constants;
import com.joy.Widget.PagerviewAdapter;
import com.joy.json.JsonCommon;
import com.joy.json.JsonCommon.OnOperationListener;
import com.joy.json.model.CommoditySet;
import com.joy.json.model.CompAppSet;
import com.joy.json.model.GoodsDetail;
import com.joy.json.model.OrderDetailEntity;
import com.joy.json.operation.OperationBuilder;
import com.joy.json.operation.impl.OrderDetailOp;
import com.umeng.analytics.MobclickAgent;

/**
 * 订单详情
 * @author daiye
 *
 */
public class OrderDetailActivity extends BaseActivity implements OnClickListener {
	
	private int commsetid;
	public static final String EXTRA_COMMSETID = "commsetid";
	public static final String EXTRA_COMMODITYSET = "commoditySet";
	private RelativeLayout layout_title;
	private TextView tv_ret;
	private TextView tv_title;
	private TextView tv_setname;
	private ViewPager viewpager;
	private LinearLayout mNumLayout;
	private TextView tv_points;
	private Button btn_shopping;
	private TextView tv_product_title;
	private TextView tv_product_detail;
	CommoditySet commoditySet;
	CompAppSet appSet;
	int color;
	
	/*@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_orderdetail);

		commsetid = getIntent().getIntExtra(EXTRA_COMMSETID, -1);
		
		initView();
		initData();
	}*/
	
	@Override
	protected View ceateView(LayoutInflater inflater, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View v = inflater.inflate(R.layout.activity_orderdetail, null);
		setContentView(v);
		commsetid = getIntent().getIntExtra(EXTRA_COMMSETID, -1);
		color =  Color.parseColor("#ffa800");
		 appSet = JoyApplication.getInstance().getCompAppSet();
		 if(appSet != null){
				try {
					color = Color.parseColor(appSet.getColor2());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		initView();
		initData();
		return v;
	}

	private void initView() {
		layout_title = (RelativeLayout) findViewById(R.id.layout_title);
		uiAdapter.setMargin(layout_title, LayoutParams.MATCH_PARENT, Constants.TitleHeight, 0, 0, 0, 0);

		tv_ret = (TextView) findViewById(R.id.tv_ret);
		tv_ret.setOnClickListener(this);
		uiAdapter.setTextSize(tv_ret, Constants.TitleRetSize);
		uiAdapter.setMargin(tv_ret, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 20, 0, 0, 0);
		
		tv_title = (TextView) findViewById(R.id.tv_title);
		uiAdapter.setTextSize(tv_title, Constants.TitleSize);
		
		tv_setname = (TextView) findViewById(R.id.tv_setname);
		uiAdapter.setTextSize(tv_setname, 20);
		uiAdapter.setPadding(tv_setname, 0, 10, 0, 5);
		
		viewpager = (ViewPager) findViewById(R.id.viewpager);
		uiAdapter.setMargin(viewpager, LayoutParams.MATCH_PARENT, 224, 0, 0, 0,
				0);

		mNumLayout = (LinearLayout) findViewById(R.id.ll_pager_num);
		uiAdapter.setMargin(mNumLayout, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 0, 200, 0, 0);
		
		tv_points = (TextView) findViewById(R.id.tv_points);
		uiAdapter.setTextSize(tv_points, 20);
		uiAdapter.setMargin(tv_points, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 20, 0, 0, 0);
		
		btn_shopping = (Button) findViewById(R.id.btn_shopping);
		btn_shopping.setBackgroundColor(color);
		btn_shopping.setOnClickListener(this);
		uiAdapter.setMargin(btn_shopping, 160, 40, 140, 20, 0, 20);
		uiAdapter.setTextSize(btn_shopping, 20);
		
		tv_product_title = (TextView) findViewById(R.id.tv_product_title);
		uiAdapter.setTextSize(tv_product_title, 20);
		uiAdapter.setMargin(tv_product_title, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 20, 0, 0, 5);
		
		tv_product_detail = (TextView) findViewById(R.id.tv_product_detail);
		uiAdapter.setTextSize(tv_product_detail, 20);
		uiAdapter.setPadding(tv_product_detail, 20, 10, 20, 10);
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
		if(commoditySet != null){
			btn_shopping.setTag(commoditySet);
		}
		this.commoditySet = commoditySet;
		tv_setname.setText(commoditySet.getSetName());
		
		String[] pics = commoditySet.getAppPicture().split(";");
		List<String> piclist = new ArrayList<String>();
		for (String pic : pics) {
			piclist.add(Constants.IMGURL + pic);
		}
		setviewpager(piclist);
		
		tv_points.setText("所需积分：" + commoditySet.getPoints());
		
		tv_product_detail.setText(Html.fromHtml(commoditySet.getDescription()));
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
				
			}

			@Override
			public void onPageScrollStateChanged(int arg0) {
				
			}
		});
	}
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_shopping:
			//Intent intent = new Intent();
			//intent.setClass(self, OrderConfirmActivity.class);
			//intent.putExtra(EXTRA_COMMODITYSET, commoditySet);
			//startActivity(intent);
			
			CommoditySet entity = (CommoditySet) v.getTag();
			if(entity != null){
				GoodsDetail detail = new GoodsDetail();
				detail.setGoods_name(entity.getDescription());
				detail.setGoods_img(entity.getPicture());
				detail.setIsLogoStore(false);
				detail.setGoods_id(String.format("%d", entity.getId()));
				MainActivity.Add2ShopCar(OrderDetailActivity.this, detail, 1);
				Toast.show(OrderDetailActivity.this, "商品已加入购物车");
			}
			break;
		case R.id.tv_ret:
			finish();
			break;
		default:
			break;
		}
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
