package com.joy.Activity;

import gejw.android.quickandroid.QActivity;
import gejw.android.quickandroid.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.text.Html;
import android.text.TextUtils;
import android.util.Log;
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

import com.google.gson.Gson;
import com.joy.JoyApplication;
import com.joy.R;
import com.joy.Dialog.DialogUtil;
import com.joy.Dialog.DialogUtil.DialogButtonClickCallback;
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
 * 福利订单详情
 * @author daiye
 *
 */
public class WelfareDetailActivity extends BaseActivity implements OnClickListener {
	
	private int commsetid;
	public static final String EXTRA_COMMSETID = "commsetid";
	public static final String EXTRA_COMMODITYSET = "commoditySet";
	private RelativeLayout layout_title;
	private ImageView iv_ret;
	private TextView tv_title;
	private TextView tv_setname;
	private ViewPager viewpager;
	private LinearLayout mNumLayout;
	private TextView tv_points;
	private Button btn_shopping;
	private TextView tv_product_detail;
	CommoditySet commoditySet;
	CompAppSet appSet;
	int color2;
	DialogUtil dUtil;
	private ImageView ivAdd,ivSub;
	private TextView tvGoodNum;
	int goodsNum =1;//商品选择的个数
	
	/*@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_orderdetail);

		commsetid = getIntent().getIntExtra(EXTRA_COMMSETID, -1);
		
		initView();
		initData();
	}*/
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		dUtil = new DialogUtil(WelfareDetailActivity.this);
		super.onCreate(savedInstanceState);
	}
	
	@Override
	protected View ceateView(LayoutInflater inflater, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View v = inflater.inflate(R.layout.activity_orderdetail, null);
		setContentView(v);
		commsetid = getIntent().getIntExtra(EXTRA_COMMSETID, -1);
		//默认color2颜色
		color2 =  Color.parseColor("#ffa800");
		 appSet = JoyApplication.getInstance().getCompAppSet();
		 if(appSet != null){
				try {
					color2 = Color.parseColor(appSet.getColor2());
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
		uiAdapter.setMargin(layout_title, LayoutParams.MATCH_PARENT, Constants.SubTitleHeight, 0, 0, 0, 0);

		iv_ret = (ImageView) findViewById(R.id.iv_ret);
		iv_ret.setOnClickListener(this);
		
		tv_title = (TextView) findViewById(R.id.tv_title);
		uiAdapter.setTextSize(tv_title, Constants.TitleSize);
		tv_title.setText(R.string.welfaredetail);//福利商品详情
		
		viewpager = (ViewPager) findViewById(R.id.viewpager);
		uiAdapter.setMargin(viewpager, LayoutParams.MATCH_PARENT, 210, 0, 0, 0, 0);

		mNumLayout = (LinearLayout) findViewById(R.id.ll_pager_num);
		uiAdapter.setMargin(mNumLayout, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 0, 196, 0, 0);
		
		tv_setname = (TextView) findViewById(R.id.tv_setname);
		uiAdapter.setTextSize(tv_setname, 20);
		uiAdapter.setPadding(tv_setname, 20, 5, 0, 5);
		
		tv_points = (TextView) findViewById(R.id.tv_points);
		uiAdapter.setTextSize(tv_points, 20);
		uiAdapter.setMargin(tv_points, LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT, 0, 0, 0, 0);
		uiAdapter.setPadding(tv_points, 40, 5, 0, 5);
		
		tv_product_detail = (TextView) findViewById(R.id.tv_product_detail);
		uiAdapter.setTextSize(tv_product_detail, 20);
		uiAdapter.setPadding(tv_product_detail, 20, 10, 20, 10);
		
		tvGoodNum = (TextView) findViewById(R.id.txt_num);
		uiAdapter.setTextSize(tvGoodNum, 20);
		uiAdapter.setMargin(tvGoodNum, -2, 40, 5, 0, 5, 0);
		uiAdapter.setPadding(tvGoodNum, 16, 0, 16, 0);
		tvGoodNum.setText(goodsNum + "");
		
		
		ivAdd = (ImageView) findViewById(R.id.img_plus);
		//uiAdapter.setMargin(ivAdd, 60, 60, 0, 0, 0, 0);
		ivAdd.setOnClickListener(this);
		
		ivSub = (ImageView) findViewById(R.id.img_minus);
		//uiAdapter.setMargin(ivSub, 60, 60, 0, 0, 0, 0);
		ivSub.setOnClickListener(this);
		
		btn_shopping = (Button) findViewById(R.id.btn_shopping);
		GradientDrawable shapeDrawable = (GradientDrawable) btn_shopping.getBackground();
		shapeDrawable.setColor(color2);
		btn_shopping.setOnClickListener(this);
		uiAdapter.setTextSize(btn_shopping, 20);
		uiAdapter.setMargin(btn_shopping, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 0, 0, 10, 0);
		uiAdapter.setPadding(btn_shopping, 36, 8, 36, 8);
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
					Toast.show(self, getResources().getString(R.string.timeout));
					return;
				}
				OrderDetailEntity entity = (OrderDetailEntity) resList.get(0);
				CommoditySet commoditySet = entity.getRetobj();
				if (commoditySet == null) {
					Toast.show(self, getResources().getString(R.string.nodetailinfo));
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
		
		tv_points.setText(getResources().getString(R.string.pointsneeded) + commoditySet.getPoints());
		
		//tv_product_detail.setText(Html.fromHtml(commoditySet.getDescription()));
		tv_product_detail.setText(commoditySet.getDescription());
	}
	
	Button mPreSelectedBt;
	private void setviewpager(List<String> piclist) {
		PagerviewAdapter pagerviewAdapter = new PagerviewAdapter(self,
				piclist);
		for (int i = 0; i < piclist.size(); i++) {
			Button bt = new Button(self);
			LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(uiAdapter.CalcWidth(10), uiAdapter.CalcWidth(10));
			params.setMargins(5, 0, 5, 0);
			bt.setLayoutParams(params);
			bt.setBackgroundResource(R.drawable.point_press);
			if (i == 0) {
				if (color2 != 0) {
					bt.setBackgroundColor(color2);
				}
			}
			mNumLayout.addView(bt);
		}
		viewpager.setAdapter(pagerviewAdapter);
		viewpager.setOnPageChangeListener(new OnPageChangeListener() {
			@Override
			public void onPageSelected(int position) {
				if (mPreSelectedBt != null) {
					mPreSelectedBt.setBackgroundResource(R.drawable.point_press);
				}
				Button currentBt = (Button) mNumLayout.getChildAt(position);
				if(color2 != 0 ){
					currentBt.setBackgroundColor(color2);
				}
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
	//福利商品中的添加购物车
	@Override
	public void onClick(final View v) {
		switch (v.getId()) {
		case R.id.btn_shopping:
			//Intent intent = new Intent();
			//intent.setClass(self, OrderConfirmActivity.class);
			//intent.putExtra(EXTRA_COMMODITYSET, commoditySet);
			//startActivity(intent);
			
			CommoditySet entity = (CommoditySet) v.getTag();
			if(entity != null){
				GoodsDetail detail = new GoodsDetail();
				detail.setGoods_name(entity.getSetName());
				detail.setGoods_img(entity.getPicture());
			    detail.setPoints(entity.getPoints());
				detail.setIsLogoStore(false);
				detail.setGoods_id(String.format("%d", entity.getId()));
				MainActivity.Add2ShopCar(WelfareDetailActivity.this, detail, goodsNum);
				Toast.show(WelfareDetailActivity.this, getResources().getString(R.string.shopptingadded));
			}
			
			/*dUtil.showDialog("加入购物车？", 0, "确定", "取消", new DialogButtonClickCallback() {
				@Override
				public void positiveButtonClick() {
					// TODO Auto-generated method stub
					CommoditySet entity = (CommoditySet) v.getTag();
					if(entity != null){
						GoodsDetail detail = new GoodsDetail();
						detail.setGoods_name(entity.getDescription());
						detail.setGoods_img(entity.getPicture());
						detail.setIsLogoStore(false);
						detail.setGoods_id(String.format("%d", entity.getId()));
						MainActivity.Add2ShopCar(OrderDetailActivity.this, detail, goodsNum);
						Toast.show(OrderDetailActivity.this, "商品已加入购物车");
					}
				}
				@Override
				public void negativeButtonClick() {
					// TODO Auto-generated method stub
				}
			});*/
			break;
		case R.id.img_plus:
			goodsNum++;
			tvGoodNum.setText(goodsNum+"");
			break;
		case R.id.img_minus:
			goodsNum--;
			if(goodsNum <=1){
				goodsNum =1;
			}
			tvGoodNum.setText(goodsNum+"");
			break;
		case R.id.iv_ret:
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
