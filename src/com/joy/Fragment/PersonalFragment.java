package com.joy.Fragment;

import gejw.android.quickandroid.QFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.joy.R;

/**
 * 个人中心
 * 
 * @author daiye
 * 
 */
public class PersonalFragment extends QFragment {

	private RelativeLayout layout_title;
	private TextView tv_ret;
	private TextView tv_title;
	private ImageView img_ok;
	private RelativeLayout layout_personinfo;
	private TextView tv_name;
	private ImageView img_headportrait;
	private TextView tv_city;
	private ImageButton img_level;
	private RelativeLayout layout_changepwd;
	private TextView tv_changepwd;
	private ImageView img_changepwd_arrow;
	private RelativeLayout layout_orderquery;
	private TextView tv_orderquery;
	private ImageView img_orderquery_arrow;
	private RelativeLayout layout_integrationhistory;
	private TextView tv_integrationhistory;
	private ImageView img_integrationhistory_arrow;
	private RelativeLayout layout_checkupdate;
	private TextView tv_checkupdate;
	private ImageView img_checkupdate_arrow;
	private Button btn_loginout;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_personal, container, false);
		initView(v);
		return v;
	}

	private void initView(View v) {
		layout_title = (RelativeLayout) v.findViewById(R.id.layout_title);
		uiAdapter.setMargin(layout_title, LayoutParams.MATCH_PARENT, 74, 0, 0, 0, 0);

		tv_ret = (TextView) v.findViewById(R.id.tv_ret);
		uiAdapter.setTextSize(tv_ret, 14);
		
		tv_title = (TextView) v.findViewById(R.id.tv_title);
		uiAdapter.setTextSize(tv_title, 20);
		
		img_ok = (ImageView) v.findViewById(R.id.img_ok);
		uiAdapter.setMargin(img_ok, 34, 34, 0, 0, 0, 0);
		
		layout_personinfo = (RelativeLayout) v.findViewById(R.id.layout_personinfo);
		uiAdapter.setMargin(layout_personinfo, LayoutParams.MATCH_PARENT, 113, 0, 0, 0, 0);
		
		tv_name = (TextView) v.findViewById(R.id.tv_name);
		uiAdapter.setTextSize(tv_name, 16);
		uiAdapter.setMargin(tv_name, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 0, 34, 0, 0);
		tv_name.setText("张某某");
		
		img_headportrait = (ImageView) v.findViewById(R.id.img_headportrait);
		uiAdapter.setMargin(img_headportrait, 76, 76, 0, 0, 0, 0);
		
		tv_city = (TextView) v.findViewById(R.id.tv_city);
		uiAdapter.setTextSize(tv_city, 14);
		
		img_level = (ImageButton) v.findViewById(R.id.img_level);
		uiAdapter.setMargin(img_level, 47, uiAdapter.CalcHeight(47, 62, 24), 0, 0, 0, 0);
		
		layout_changepwd = (RelativeLayout) v.findViewById(R.id.layout_changepwd);
		uiAdapter.setMargin(layout_changepwd, LayoutParams.MATCH_PARENT, 54, 0, 0, 0, 0);
		tv_changepwd = (TextView) v.findViewById(R.id.tv_changepwd);
		uiAdapter.setTextSize(tv_changepwd, 16);
		img_changepwd_arrow = (ImageView) v.findViewById(R.id.img_changepwd_arrow);
		uiAdapter.setMargin(img_changepwd_arrow, 12, uiAdapter.CalcHeight(12, 16, 28), 0, 0, 0, 0);
		
		layout_orderquery = (RelativeLayout) v.findViewById(R.id.layout_orderquery);
		uiAdapter.setMargin(layout_orderquery, LayoutParams.MATCH_PARENT, 54, 0, 0, 0, 0);
		tv_orderquery = (TextView) v.findViewById(R.id.tv_orderquery);
		uiAdapter.setTextSize(tv_orderquery, 16);
		img_orderquery_arrow = (ImageView) v.findViewById(R.id.img_orderquery_arrow);
		uiAdapter.setMargin(img_orderquery_arrow, 12, uiAdapter.CalcHeight(12, 16, 28), 0, 0, 0, 0);
		
		layout_integrationhistory = (RelativeLayout) v.findViewById(R.id.layout_integrationhistory);
		uiAdapter.setMargin(layout_integrationhistory, LayoutParams.MATCH_PARENT, 54, 0, 0, 0, 0);
		tv_integrationhistory = (TextView) v.findViewById(R.id.tv_integrationhistory);
		uiAdapter.setTextSize(tv_integrationhistory, 16);
		img_integrationhistory_arrow = (ImageView) v.findViewById(R.id.img_integrationhistory_arrow);
		uiAdapter.setMargin(img_integrationhistory_arrow, 12, uiAdapter.CalcHeight(12, 16, 28), 0, 0, 0, 0);
		
		layout_checkupdate = (RelativeLayout) v.findViewById(R.id.layout_checkupdate);
		uiAdapter.setMargin(layout_checkupdate, LayoutParams.MATCH_PARENT, 54, 0, 54, 0, 0);
		tv_checkupdate = (TextView) v.findViewById(R.id.tv_checkupdate);
		uiAdapter.setTextSize(tv_checkupdate, 16);
		img_checkupdate_arrow = (ImageView) v.findViewById(R.id.img_checkupdate_arrow);
		uiAdapter.setMargin(img_checkupdate_arrow, 12, uiAdapter.CalcHeight(12, 16, 28), 0, 0, 0, 0);
		
		btn_loginout = (Button) v.findViewById(R.id.btn_loginout);
		uiAdapter.setMargin(btn_loginout, LayoutParams.MATCH_PARENT, 46, 10, 160, 10, 0);
		uiAdapter.setTextSize(btn_loginout, 24);
		btn_loginout.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				exit();
			}
		});
	}
	
	/**
	 * 退出
	 */
	private void exit() {
		
	}
}
