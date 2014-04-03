package com.joy.Fragment;

import gejw.android.quickandroid.QFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.joy.JoyApplication;
import com.joy.R;
import com.joy.Utils.Constants;
import com.joy.json.model.UserInfo;

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
	private LinearLayout layout_personinfo;
	private TextView tv_name;
	private ImageView img_company;
	private TextView tv_company;
	private TextView tv_points;
	private ImageView img_edit;
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
		initData();
		return v;
	}

	private void initView(View v) {
		layout_title = (RelativeLayout) v.findViewById(R.id.layout_title);
		uiAdapter.setMargin(layout_title, LayoutParams.MATCH_PARENT, 74, 0, 0, 0, 0);

		tv_ret = (TextView) v.findViewById(R.id.tv_ret);
		uiAdapter.setTextSize(tv_ret, Constants.TitleRetSize);
		uiAdapter.setMargin(tv_ret, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 20, 0, 0, 0);
		
		tv_title = (TextView) v.findViewById(R.id.tv_title);
		uiAdapter.setTextSize(tv_title, Constants.TitleSize);

		img_ok = (ImageView) v.findViewById(R.id.img_ok);
		uiAdapter.setMargin(img_ok, 34, 34, 0, 0, 20, 0);
		
		layout_personinfo = (LinearLayout) v.findViewById(R.id.layout_personinfo);
		uiAdapter.setMargin(layout_personinfo, LayoutParams.MATCH_PARENT, 113, 0, 0, 0, 0);
		
		tv_name = (TextView) v.findViewById(R.id.tv_name);
		uiAdapter.setTextSize(tv_name, 24);
		uiAdapter.setMargin(tv_name, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 10, 10, 0, 0);
		
		img_company = (ImageView) v.findViewById(R.id.img_company);
		uiAdapter.setMargin(img_company, 76, 76, 30, 20, 8, 0);
		
		tv_company = (TextView) v.findViewById(R.id.tv_company);
		uiAdapter.setTextSize(tv_company, 16);
		uiAdapter.setMargin(tv_company, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 30, 5, 0, 0);;
		
		tv_points = (TextView) v.findViewById(R.id.tv_points);
		tv_points.setText("300");
		uiAdapter.setTextSize(tv_points, 16);
		uiAdapter.setMargin(tv_points, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 0, 5, 0, 0);;
		
		img_edit = (ImageView) v.findViewById(R.id.img_edit);
		uiAdapter.setMargin(img_edit, 30, uiAdapter.CalcHeight(30, 1, 1), 0, 15, 20, 0);
		
		layout_changepwd = (RelativeLayout) v.findViewById(R.id.layout_changepwd);
		uiAdapter.setMargin(layout_changepwd, LayoutParams.MATCH_PARENT, 54, 0, 0, 0, 0);
		tv_changepwd = (TextView) v.findViewById(R.id.tv_changepwd);
		uiAdapter.setTextSize(tv_changepwd, 20);
		uiAdapter.setMargin(tv_changepwd, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 34, 0, 0, 0);
		img_changepwd_arrow = (ImageView) v.findViewById(R.id.img_changepwd_arrow);
		uiAdapter.setMargin(img_changepwd_arrow, 12, uiAdapter.CalcHeight(12, 16, 28), 0, 0, 20, 0);
		
		layout_orderquery = (RelativeLayout) v.findViewById(R.id.layout_orderquery);
		uiAdapter.setMargin(layout_orderquery, LayoutParams.MATCH_PARENT, 54, 0, 0, 0, 0);
		tv_orderquery = (TextView) v.findViewById(R.id.tv_orderquery);
		uiAdapter.setTextSize(tv_orderquery, 20);
		uiAdapter.setMargin(tv_orderquery, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 34, 0, 0, 0);
		img_orderquery_arrow = (ImageView) v.findViewById(R.id.img_orderquery_arrow);
		uiAdapter.setMargin(img_orderquery_arrow, 12, uiAdapter.CalcHeight(12, 16, 28), 0, 0, 20, 0);
		
		layout_integrationhistory = (RelativeLayout) v.findViewById(R.id.layout_integrationhistory);
		uiAdapter.setMargin(layout_integrationhistory, LayoutParams.MATCH_PARENT, 54, 0, 0, 0, 0);
		tv_integrationhistory = (TextView) v.findViewById(R.id.tv_integrationhistory);
		uiAdapter.setTextSize(tv_integrationhistory, 20);
		uiAdapter.setMargin(tv_integrationhistory, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 34, 0, 0, 0);
		img_integrationhistory_arrow = (ImageView) v.findViewById(R.id.img_integrationhistory_arrow);
		uiAdapter.setMargin(img_integrationhistory_arrow, 12, uiAdapter.CalcHeight(12, 16, 28), 0, 0, 20, 0);
		
		layout_checkupdate = (RelativeLayout) v.findViewById(R.id.layout_checkupdate);
		uiAdapter.setMargin(layout_checkupdate, LayoutParams.MATCH_PARENT, 54, 0, 54, 0, 0);
		tv_checkupdate = (TextView) v.findViewById(R.id.tv_checkupdate);
		uiAdapter.setTextSize(tv_checkupdate, 20);
		uiAdapter.setMargin(tv_checkupdate, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 34, 0, 20, 0);
		img_checkupdate_arrow = (ImageView) v.findViewById(R.id.img_checkupdate_arrow);
		uiAdapter.setMargin(img_checkupdate_arrow, 12, uiAdapter.CalcHeight(12, 16, 28), 0, 0, 20, 0);
		
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
	
	private void initData() {
		UserInfo userinfo = JoyApplication.getInstance().getUserinfo();
		if (userinfo != null) {
			tv_name.setText(userinfo.getUserName());
			tv_company.setText(userinfo.getCompanyName());
			tv_points.setText("积分 : " + userinfo.getPoints());
		}
	}
	
	/**
	 * 退出
	 */
	private void exit() {
		
	}
}
