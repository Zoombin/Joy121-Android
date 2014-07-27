package com.joy.Fragment;

import gejw.android.quickandroid.QFragment;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.os.Bundle;
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

import com.joy.R;
import com.joy.Activity.MainActivity;
import com.joy.Utils.Constants;
import com.joy.json.model.ShoppingCarGoods;
import com.nostra13.universalimageloader.core.ImageLoader;

public class ShoppingCarFragment extends QFragment {
	private RelativeLayout layout_title;
	private ImageView iv_title;
	private TextView tv_title;
	private ListView carList;
	private Button commitBt;
	private static ShoppingCarFragment shoppingCarFragment = null;
	private CarAdapter adapter;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		Log.e("LSD", "onCreate");
		adapter = new CarAdapter(mActivity);
		shoppingCarFragment = this;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_shoppingcar, container, false);
		Log.e("LSD", "onCreateView");
		initView(v);
		initData();
		return v;
	}

	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	}

	private void initView(View v) {
		layout_title = (RelativeLayout) v.findViewById(R.id.layout_title);
		uiAdapter.setMargin(layout_title, LayoutParams.MATCH_PARENT, Constants.TitleHeight, 0, 0, 0, 0);

		iv_title = (ImageView) v.findViewById(R.id.iv_title);
		uiAdapter.setMargin(iv_title, Constants.TitleIvWidth, Constants.TitleIvWidth, 10, 0, 10, 0);

		tv_title = (TextView) v.findViewById(R.id.tv_title);
		uiAdapter.setTextSize(tv_title, Constants.TitleSize);

		carList = (ListView) v.findViewById(R.id.car_list);
		commitBt = (Button) v.findViewById(R.id.commit_bt);
	}

	private void initData() {
		// TODO Auto-generated method stub
		carList.setAdapter(adapter);
		adapter.setData(MainActivity.goods_list);
	}

	public static void updateShoppingcar() {
		if (shoppingCarFragment == null)
			return;
		shoppingCarFragment.adapter.setData(MainActivity.goods_list);
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

		public void setData(List<ShoppingCarGoods> datas) {
			this.datas = datas;
			Log.e("LSD", datas.size() + "");
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

			ShoppingCarGoods data = (ShoppingCarGoods) getItem(position);
			if (data != null) {
				ImageLoader.getInstance().displayImage(Constants.IMGSURL + data.getGoods_img(), tag.goodsImg);
				tag.goodsName.setText(data.getGoods_name());
				tag.goodsNum.setText(data.getCount() + "");
				tag.goodsProperty.setText(data.getCount() + "   " + data.getSize_cloth());

				tag.minus.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						//getItem(position);
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
