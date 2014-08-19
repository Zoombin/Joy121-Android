package com.joy.Fragment;

import gejw.android.quickandroid.QFragment;
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

import com.joy.JoyApplication;
import com.joy.R;
import com.joy.Activity.MainActivity;
import com.joy.Utils.Constants;
import com.joy.json.JsonCommon;
import com.joy.json.JsonCommon.OnOperationListener;
import com.joy.json.model.CommitResultEntity;
import com.joy.json.model.CompAppSet;
import com.joy.json.model.CommitResultEntity.CommitResult;
import com.joy.json.model.ShoppingCarGoods;
import com.joy.json.operation.OperationBuilder;
import com.joy.json.operation.impl.CommitShopCarOp;
import com.nostra13.universalimageloader.core.ImageLoader;

public class ShoppingCarFragment extends BaseFragment {
	private RelativeLayout layout_title;
	private ImageView iv_title;
	private TextView tv_title,tv_message;
	private ImageView ivLogo;
	private ListView carList;
	private Button commitBt;
	private static ShoppingCarFragment shoppingCarFragment = null;
	private CarAdapter adapter;
	public String acttype;
	CompAppSet appSet;
	int color;

	@Override
	public void onCreate(Bundle savedInstanceState) { 
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
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

	/*@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_shoppingcar, container, false);
		initView(v);
		initData();
		return v;
	}*/
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
			if(imgid != 0){
				tv_title.setVisibility(View.GONE);
				ivLogo.setVisibility(View.VISIBLE);
				ivLogo.setImageResource(imgid);
			}
		}
	}

	private void initView(View v) {
		tv_message = (TextView) v.findViewById(R.id.tv_message);
		layout_title = (RelativeLayout) v.findViewById(R.id.layout_title);
		uiAdapter.setMargin(layout_title, LayoutParams.MATCH_PARENT, Constants.TitleHeight, 0, 0, 0, 0);

		iv_title = (ImageView) v.findViewById(R.id.iv_title);
		uiAdapter.setMargin(iv_title, Constants.TitleIvWidth, Constants.TitleIvWidth, 10, 0, 10, 0);

		tv_title = (TextView) v.findViewById(R.id.tv_title);
		uiAdapter.setTextSize(tv_title, Constants.TitleSize);
		
		ivLogo = (ImageView) v.findViewById(R.id.iv_logo);
		carList = (ListView) v.findViewById(R.id.car_list);
		commitBt = (Button) v.findViewById(R.id.commit_bt);
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
		refreshTxt();
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
					Toast.show(mActivity, "连接超时");
					return;
				}
				CommitResultEntity entity = (CommitResultEntity) resList.get(0);
				CommitResult result = entity.getRetobj();
				if (result == null) {
					Toast.show(mActivity, "提交失败!");
					return;
				}
				String ret = result.getStatusFlag();
				if("1".equals(ret)){
					Toast.show(mActivity, "提交成功");
					adapter.cleanData();
					MainActivity.setNotice();
				}else{
					Toast.show(mActivity, "提交失败!");
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
		refreshTxt();
		shoppingCarFragment.adapter.setData(MainActivity.goods_list);
	}
	
	public static void refreshTxt(){
		if(MainActivity.goods_list.size() == 0){
			shoppingCarFragment.tv_message.setText(R.string.nullshoppingcar_txt);
		}else{
			shoppingCarFragment.tv_message.setText(R.string.shoppingcar_txt);
		}
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
				tag.goodsNum = (TextView) convertView.findViewById(R.id.txt_num);
				tag.goodsProperty = (TextView) convertView.findViewById(R.id.txt_property);
				tag.minus = (ImageView) convertView.findViewById(R.id.img_minus);
				tag.plus = (ImageView) convertView.findViewById(R.id.img_plus);
				convertView.setTag(tag);
			} else {
				tag = (Tag) convertView.getTag();
			}

			final ShoppingCarGoods data = (ShoppingCarGoods) getItem(position);
			if (data != null) {
				ImageLoader.getInstance().displayImage(Constants.IMGSURL + data.getGoods_img(), tag.goodsImg);
				tag.goodsName.setText(data.getGoods_name());
				tag.goodsNum.setText(data.getCount() + "");

				String color = "";
				if (!TextUtils.isEmpty(data.getColor())) {
					color = data.getColor();
				}
				String size = "";
				if (!TextUtils.isEmpty(data.getSize_cloth())) {
					size = data.getSize_cloth();
				}
				tag.goodsProperty.setText(color + "   " + size);

				tag.minus.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						int num = data.getCount();
						num--;
						if (num == 0) {
							datas.remove(position);
							refreshTxt();
							MainActivity.setNotice();
						} else {
							data.setCount(num);
						}
						notifyDataSetChanged();
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
