package com.joy.Fragment;

import gejw.android.quickandroid.utils.ResName2ID;
import gejw.android.quickandroid.widget.Toast;

import java.util.List;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ImageView.ScaleType;

import com.joy.JoyApplication;
import com.joy.R;
import com.joy.Activity.MainActivity;
import com.joy.Dialog.DialogUtil;
import com.joy.Dialog.DialogUtil.DialogButtonClickCallback;
import com.joy.Utils.Constants;
import com.joy.Utils.Utils;
import com.joy.json.JsonCommon;
import com.joy.json.JsonCommon.OnOperationListener;
import com.joy.json.model.CommitResultEntity;
import com.joy.json.model.CommitResultEntity.CommitResult;
import com.joy.json.model.CompAppSet;
import com.joy.json.model.ShoppingCarGoods;
import com.joy.json.operation.OperationBuilder;
import com.joy.json.operation.impl.CommitShopCarOp;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

public class ShoppingCarFragment extends BaseFragment {
	private RelativeLayout layout_title;
	private ImageView iv_title;
	private TextView tv_title,tv_message;
	private ImageView ivLogo;
	private ListView carList;
	private static ShoppingCarFragment shoppingCarFragment = null;
	private CarAdapter adapter;
	public String acttype;
	CompAppSet appSet;
	int color;
	DialogUtil dUtil;
	
	View footView;
	private Button commitBt;
	private TextView tvInfo;
	private TextView tvSumPoints;

	@Override
	public void onCreate(Bundle savedInstanceState) { 
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		dUtil = new DialogUtil(mActivity);
		color = Color.parseColor("#ffa800");
		appSet = JoyApplication.getInstance().getCompAppSet();
		if (appSet != null) {
			try {
				color = Color.parseColor(appSet.getColor2());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		adapter = new CarAdapter(mActivity);
		shoppingCarFragment = this;
	}

	@Override
	protected View initContentView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View v = inflater.inflate(R.layout.fragment_shoppingcar, container, false);
		initView(v);
		initData();
		return v;
	}
	
	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		if(appSet != null){
			int imgid = 0;
			try {
				imgid =	ResName2ID.getDrawableID(mActivity, appSet.getLogo().replaceAll(".png", ""));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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
		}
	}

	private void initView(View v) {
		footView = LayoutInflater.from(mActivity).inflate(R.layout.fragment_shoppingcar_footview, null);
		tv_message = (TextView) v.findViewById(R.id.tv_message);
		layout_title = (RelativeLayout) v.findViewById(R.id.layout_title);
		uiAdapter.setMargin(layout_title, LayoutParams.MATCH_PARENT, Constants.TitleHeight, 0, 0, 0, 0);

		iv_title = (ImageView) v.findViewById(R.id.iv_title);
		uiAdapter.setMargin(iv_title, Constants.TitleIvWidth, Constants.TitleIvWidth, 10, 0, 10, 0);

		tv_title = (TextView) v.findViewById(R.id.tv_title);
		uiAdapter.setTextSize(tv_title, Constants.TitleSize);
		
		ivLogo = (ImageView) v.findViewById(R.id.iv_logo);
		carList = (ListView) v.findViewById(R.id.car_list);
		carList.addFooterView(footView);
		
		tvInfo = (TextView) footView.findViewById(R.id.tv_info);
		tvSumPoints = (TextView) footView.findViewById(R.id.sum_points);
		commitBt = (Button) footView.findViewById(R.id.commit_bt);
		uiAdapter.setMargin(commitBt, LayoutParams.MATCH_PARENT, 46, 10, 0, 10, 50);
		uiAdapter.setTextSize(commitBt, 24);
		commitBt.setBackgroundColor(color);
		commitBt.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				List<ShoppingCarGoods> datas = adapter.getData();
				if(datas != null && datas.size()>0){
					commitShoppingCar(datas);
				}
			}
		});
	}

	private void initData() {
		// TODO Auto-generated method stub
		carList.setAdapter(adapter);
		refreshView();
		adapter.setData(MainActivity.goods_list);
	}

	private void commitShoppingCar(List<ShoppingCarGoods> carGoods) {
		OperationBuilder builder = new OperationBuilder().append(new CommitShopCarOp(), carGoods);
		OnOperationListener listener = new OnOperationListener() {
			@Override
			public void onOperationFinished(List<Object> resList) {
				if (mActivity.isFinishing()) {
					return;
				}
				if (resList == null) {
					Toast.show(mActivity, getResources().getString(R.string.timeout));
					return;
				}
				CommitResultEntity entity = (CommitResultEntity) resList.get(0);
				CommitResult result = entity.getRetobj();
				if (result == null) {
					Toast.show(mActivity, getResources().getString(R.string.commitfailed));
					return;
				}
				String ret = result.getStatusFlag();
				if("1".equals(ret)){
					Toast.show(mActivity, getResources().getString(R.string.commitsuccess));
					adapter.cleanData();
					MainActivity.setNotice();
					refreshView();
				}else{
					Toast.show(mActivity, getResources().getString(R.string.commitfailed2) + result.getStatusRemark());
				}
			}

			@Override
			public void onOperationError(Exception e) {
				e.printStackTrace();
			}
		};

		JsonCommon task = new JsonCommon(mActivity, builder, listener, JsonCommon.PROGRESSQUERY);
		task.execute();
	}

	public static void updateShoppingcar() {
		if (shoppingCarFragment == null)
			return;
		refreshView();
		shoppingCarFragment.adapter.setData(MainActivity.goods_list);
	}
	
	public static void refreshView(){
		if (shoppingCarFragment == null)
			return;
		if(MainActivity.goods_list.size() == 0){
			shoppingCarFragment.tv_message.setText(R.string.nullshoppingcar_txt);
			shoppingCarFragment.commitBt.setVisibility(View.GONE);
			shoppingCarFragment.tvInfo.setVisibility(View.VISIBLE);
			shoppingCarFragment.tvSumPoints.setVisibility(View.GONE);
		}else{
			shoppingCarFragment.tv_message.setText(R.string.shoppingcar_txt);
			shoppingCarFragment.commitBt.setVisibility(View.VISIBLE);
			shoppingCarFragment.tvInfo.setVisibility(View.GONE);
			shoppingCarFragment.tvSumPoints.setVisibility(View.VISIBLE);
			countAllPoints();
		}
	}
	
	private static void countAllPoints(){
		if (shoppingCarFragment == null)
			return;
		//计算总积分
		int sum = 0;
		for(ShoppingCarGoods carGoods:MainActivity.goods_list){
			if(!carGoods.getIsLogoStore()){
				sum+= carGoods.getCount()*carGoods.getPoints();
			}
		}
		Log.e("33333333333333333333333333333333333333333",(shoppingCarFragment.getActivity()==null)+"");
//		shoppingCarFragment.tvSumPoints.setText(String.format(shoppingCarFragment.getActivity().getString(R.string.format_sum_points), sum));
		shoppingCarFragment.tvSumPoints.setText(String.format("总计：%1$s积分", sum));
		
	}

	/***
	 * 适配
	 * 
	 * @author ADMIN
	 * 
	 */
	public class CarAdapter extends BaseAdapter {
		Context context;
		List<ShoppingCarGoods> datas;

		public CarAdapter(Context context) {
			// TODO Auto-generated constructor stub
			this.context = context;
		}
		
		public void cleanData(){
			datas.clear();
			this.notifyDataSetChanged();
		}

		public void setData(List<ShoppingCarGoods> datas) {
			this.datas = datas;
			this.notifyDataSetChanged();
		}

		public List<ShoppingCarGoods> getData() {
			return datas == null ? null : datas;
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
		public View getView(final int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			Tag tag;
			if (convertView == null) {
				tag = new Tag();
				convertView = LayoutInflater.from(context).inflate(R.layout.layout_shoppingcar_item, null);
				tag.goodsImg = (ImageView) convertView.findViewById(R.id.img_icon);
				tag.goodsName = (TextView) convertView.findViewById(R.id.txt_goodsname);
				uiAdapter.setMargin(tag.goodsName, LayoutParams.WRAP_CONTENT,
						LayoutParams.WRAP_CONTENT, 0, 0, 0, 0);
				tag.goodsNum = (TextView) convertView.findViewById(R.id.txt_num);
				uiAdapter.setPadding(tag.goodsNum, 0, 0, 0, 5);
				tag.goodsProperty = (TextView) convertView.findViewById(R.id.txt_property);
				uiAdapter.setPadding(tag.goodsProperty, 0, 0, 0, 5);
				tag.minus = (ImageView) convertView.findViewById(R.id.img_minus);
				uiAdapter.setPadding(tag.minus, 0, 0, 0, 5);
				tag.plus = (ImageView) convertView.findViewById(R.id.img_plus);
				uiAdapter.setPadding(tag.plus, 0, 0, 0, 5);
				convertView.setTag(tag);
			} else {
				tag = (Tag) convertView.getTag();
			}

			final ShoppingCarGoods data = (ShoppingCarGoods) getItem(position);
			if (data != null) {
				ImageLoader.getInstance().displayImage(Constants.IMGSURL + data.getGoods_img(), tag.goodsImg);
				tag.goodsName.setText(data.getGoods_name());
				int count = data.getCount() ;
				tag.goodsNum.setText(count + "");

				if(data.getIsLogoStore()){
					String color = "";
					if (!TextUtils.isEmpty(data.getColor())) {
						color = data.getColor();
					}
					String size = "";
					if (!TextUtils.isEmpty(data.getSize_cloth())) {
						size = data.getSize_cloth();
					}
					tag.goodsProperty.setText(color + "   " + size);
				}else{
					int points = data.getPoints();
					int sumPoints = count*points;
					if(sumPoints != 0){
						tag.goodsProperty.setText("x "+points+"="+sumPoints+"积分");
					}
				}

				tag.minus.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						int num = data.getCount();
						if (num <= 1) {
							num = 1;
							data.setCount(num);
							dUtil.showDialog("是否删除该商品?", 0, "确定", "取消", new DialogButtonClickCallback() {
								@Override
								public void positiveButtonClick() {
									// TODO Auto-generated method stub
									datas.remove(position);
									refreshView();
									MainActivity.setNotice();
								}
								@Override
								public void negativeButtonClick() {
									// TODO Auto-generated method stub
								}
								
							});
						} else {
							num--;
							data.setCount(num);
						}
						notifyDataSetChanged();
						countAllPoints();
					}
				});
				tag.plus.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						int num = data.getCount();
						num++;
						data.setCount(num);
						notifyDataSetChanged();
						countAllPoints();
					}
				});
			}

			return convertView;
		}

		class Tag {
			ImageView goodsImg;
			TextView goodsName;
			TextView goodsNum;
			TextView goodsProperty;
			ImageView minus, plus;
		}

	}

}
