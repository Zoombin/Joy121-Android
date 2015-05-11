package com.joy.Fragment;

import gejw.android.quickandroid.QFragment;
import gejw.android.quickandroid.log.PLog;
import gejw.android.quickandroid.ui.adapter.UIAdapter;
import gejw.android.quickandroid.utils.ResName2ID;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ImageView.ScaleType;

import com.joy.R;
import com.joy.Activity.MainActivity;
import com.joy.Fragment.mall.MallGoodsListFragment;
import com.joy.Fragment.portals.welfare.WelfareFragment;
import com.joy.Utils.Constants;
import com.joy.Utils.Utils;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

/**
 * 在线商城模块
 * 
 * @author daiye
 * 
 */
public class MallFragment extends BaseFragment {

	private RelativeLayout layout_title;
	private ImageView iv_title;
	private TextView tv_title;
	private ImageView ivLogo;
	private ListView list_mall;
	private final int[] iconlist = { R.drawable.sc, R.drawable.sg,
			R.drawable.rql, R.drawable.dftc, R.drawable.jg, R.drawable.hwg };
	private final String[] titlelist = { "有机蔬菜", "时令水果", "肉禽蛋类", "地方特产",
			"坚果炒货", "海外直购" };
	private final String[] contentlist = { "花叶菜/根茎菜/菌菇菜/薯芋菜/瓜果菜...",
			"国产水果/进口水果/季节水果/水果礼盒...", "牛羊肉/猪鸡肉/草鸡蛋/青壳蛋/进口肉...",
			"西北特产/东北特产/西南特产/台湾特产...", "榛子/核桃/松子/腰果/杏仁/开心果/碧...",
			"进口红酒/进口牛奶/进口巧克力/进口零..." };
	private final String[] categorylist = {"1","2", "3", "4", "5", "6"};

	/*@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_mall, container, false);

		initView(v);
		return v;
	}*/
	
	@Override
	protected View initContentView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View v = inflater.inflate(R.layout.fragment_mall, container, false);

		initView(v);
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
				//tv_title.setVisibility(View.GONE);
				//ivLogo.setVisibility(View.VISIBLE);
				//ivLogo.setImageResource(imgid);
			tv_title.setVisibility(View.GONE);
			DisplayImageOptions options = Utils.getImageOptions();
			ImageLoader.getInstance().displayImage(Constants.IMGLOGO + appSet.getLogo(), ivLogo, options);
			ivLogo.setScaleType(ScaleType.CENTER);
			ivLogo.setVisibility(View.VISIBLE);
			//}
		}
	}
	
	private InputStream openHttpConnection(String urlString) throws IOException {
		InputStream in = null;
		int response = -1;
		URL url = new URL(urlString);
		URLConnection conn = url.openConnection();
		if (!(conn instanceof HttpURLConnection))
			throw new IOException("Not an HTTP connection");
		try {
			HttpURLConnection httpConn = (HttpURLConnection) conn;
			httpConn.setAllowUserInteraction(false);
			httpConn.setInstanceFollowRedirects(true);
			httpConn.setRequestMethod("GET");
			httpConn.connect();
			response = httpConn.getResponseCode();
			if (response == HttpURLConnection.HTTP_OK) {
				in = httpConn.getInputStream();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return in;
	}
	
	private Bitmap downloadImage(String URL) {
		Bitmap bitmap = null;
		InputStream in = null;
		try {
			in = openHttpConnection(URL);
			bitmap = BitmapFactory.decodeStream(in);
			in.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		return bitmap;
	}

	private void initView(View v) {
		layout_title = (RelativeLayout) v.findViewById(R.id.layout_title);
		uiAdapter.setMargin(layout_title, LayoutParams.MATCH_PARENT,
				Constants.TitleHeight, 0, 0, 0, 0);

		iv_title = (ImageView) v.findViewById(R.id.iv_title);
		uiAdapter.setMargin(iv_title, Constants.TitleIvWidth,
				Constants.TitleIvWidth, 10, 0, 10, 0);

		tv_title = (TextView) v.findViewById(R.id.tv_title);
		uiAdapter.setTextSize(tv_title, Constants.TitleSize);
		
		ivLogo = (ImageView) v.findViewById(R.id.iv_logo);
		list_mall = (ListView) v.findViewById(R.id.list_mall);

		List<MallEntity> data = new ArrayList<MallEntity>();

		MallEntity entity;
		for (int i = 0; i < iconlist.length; i++) {
			entity = new MallEntity();
			entity.setIcon(iconlist[i]);
			entity.setTitle(titlelist[i]);
			entity.setContent(contentlist[i]);
			entity.setCategory(categorylist[i]);
			data.add(entity);
		}

		list_mall.setAdapter(new ListMallAdapter(mActivity, data));
	}

	private class MallEntity {
		private int icon;

		private String title;

		private String content;
		
		private String category;

		public int getIcon() {
			return icon;
		}

		public void setIcon(int icon) {
			this.icon = icon;
		}

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}

		public String getContent() {
			return content;
		}
		
		public void setContent(String content) {
			this.content = content;
		}

		public String getCategory() {
			return this.category;
		}
		
		public void setCategory(String category) {
			this.category = category;
		}

		
	}

	private class ListMallAdapter extends BaseAdapter {

		/**
		 * 上下文对象
		 */
		private Context mContext = null;
		private List<MallEntity> data = new ArrayList<MallEntity>();
		private UIAdapter uiAdapter;

		public ListMallAdapter(Context ctx, List<MallEntity> data) {
			mContext = ctx;
			this.data = data;
			uiAdapter = UIAdapter.getInstance(ctx);
		}

		@Override
		public int getCount() {
			return data.size();
		}

		@Override
		public Object getItem(int position) {
			return data.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(final int position, View convertView,
				ViewGroup parent) {
			final MallEntity entity = data.get(position);

			ViewHolder holder;
			if (convertView == null) {
				convertView = LayoutInflater.from(mContext).inflate(
						R.layout.mall_list_item, parent, false);
				holder = new ViewHolder();
				// category row layout
				holder.layout_category = (LinearLayout) convertView.findViewById(R.id.layout_category);
				holder.layout_category.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						/*MallGoodsListFragment mallGoodsListFragment = new MallGoodsListFragment();
						Bundle bundle = new Bundle();
		                bundle.putSerializable(MallGoodsListFragment.EXTRA_CATEGROY1ID, entity.getCategory());
		                bundle.putSerializable(MallGoodsListFragment.EXTRA_CATEGROY1NAME, entity.getTitle());
		                mallGoodsListFragment.setArguments(bundle); 
		                FragmentManager fragmentManager = getFragmentManager();
		                FragmentTransaction transaction = fragmentManager.beginTransaction();
		                transaction.replace(R.id.layout_fragment, mallGoodsListFragment);
		                transaction.addToBackStack(null);
		        		transaction.commit();*/
		        		
		        		MallGoodsListFragment mallGoodsListFragment = new MallGoodsListFragment();
		        		Bundle bundle = new Bundle();
		        		bundle.putSerializable(MallGoodsListFragment.EXTRA_CATEGROY1ID, entity.getCategory());
		                bundle.putSerializable(MallGoodsListFragment.EXTRA_CATEGROY1NAME, entity.getTitle());
		                mallGoodsListFragment.setArguments(bundle); 
		        		MainActivity.mActivity.replaceChildFragment(
		    					"MallGoodsListFragment", mallGoodsListFragment, true);
					}
				});

				// 图片
				holder.iv_icon = (ImageView) convertView
						.findViewById(R.id.iv_icon);
				uiAdapter.setMargin(holder.iv_icon, 50,
						uiAdapter.CalcHeight(50, 1, 1), 20, 5, 20, 5);

				// title
				holder.tv_title = (TextView) convertView
						.findViewById(R.id.tv_title);
				uiAdapter.setTextSize(holder.tv_title, 24);
				uiAdapter.setMargin(holder.tv_title, LayoutParams.WRAP_CONTENT,
						LayoutParams.WRAP_CONTENT, 10, 10, 0, 10);

				// 内容
				holder.tv_content = (TextView) convertView
						.findViewById(R.id.tv_content);
				uiAdapter.setTextSize(holder.tv_content, 18);
				uiAdapter.setMargin(holder.tv_content,
						LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT,
						10, 5, 0, 10);

				// 图片
				holder.iv_arrow = (ImageView) convertView
						.findViewById(R.id.iv_arrow);
				uiAdapter.setMargin(holder.iv_arrow, 10,
						uiAdapter.CalcHeight(10, 16, 28), 10, 10, 20, 10);

				convertView.setTag(holder);
			} else {// 有直接获得ViewHolder
				holder = (ViewHolder) convertView.getTag();
			}

			ImageLoader.getInstance().displayImage(
					String.format("%s%s", "drawable://", entity.getIcon()),
					holder.iv_icon);
			holder.tv_title.setText(entity.getTitle());
			holder.tv_content.setText(entity.getContent());

			return convertView;
		}

		public class ViewHolder {
			LinearLayout layout_category;
			ImageView iv_icon;
			TextView tv_title;
			TextView tv_content;
			ImageView iv_arrow;
		}
	}
}
