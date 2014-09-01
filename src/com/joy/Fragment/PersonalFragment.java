package com.joy.Fragment;

import gejw.android.quickandroid.QFragment;
import gejw.android.quickandroid.utils.ResName2ID;
import gejw.android.quickandroid.widget.Toast;

import java.util.List;

import android.content.Intent;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
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

import com.joy121.R;
import com.joy.Activity.ChangePwdActivity;
import com.joy.Activity.HisPointsActivity;
import com.joy.Activity.LoginActivity;
import com.joy.Activity.MainActivity;
import com.joy.Activity.OrderqueryActivity;
import com.joy.Activity.PersonalInfoActivity;
import com.joy.Dialog.DialogUtil;
import com.joy.Dialog.DialogUtil.DialogButtonClickCallback;
import com.joy.Utils.Constants;
import com.joy.Utils.SharedPreferencesUtils;
import com.joy.Utils.UpdateManager;
import com.joy.json.JsonCommon;
import com.joy.json.JsonCommon.OnOperationListener;
import com.joy.json.model.UpdateEntity;
import com.joy.json.model.UpdateEntity.Version;
import com.joy.json.model.UserEntity;
import com.joy.json.model.UserInfoEntity;
import com.joy.json.operation.OperationBuilder;
import com.joy.json.operation.impl.UpdateOp;
import com.joy.json.operation.impl.UserinfoOp;
import com.nostra13.universalimageloader.core.ImageLoader;

/**
 * 个人中心
 * 
 * @author daiye
 * 
 */
public class PersonalFragment extends BaseFragment implements OnClickListener {

	private RelativeLayout layout_title;
	private ImageView iv_title;
	private TextView tv_title;
	private ImageView ivLogo;
	private LinearLayout layout_personinfo;
	private TextView tv_name;
	private ImageView img_company;
	private TextView tv_company;
	private TextView tv_points_title;
	private TextView tv_points;
	private RelativeLayout layout_personalinfo;
	private TextView tv_personalinfo;
	private ImageView img_personalinfo_arrow;
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

	public static final String EXTRA_USERINFO = "userinfo";
	UserInfoEntity userinfo;
	// private LinearLayout layout_personal_info;
	// private TextView tv_loginname_title;
	// private TextView tv_loginname;
	// private TextView tv_username_title;
	// private TextView tv_username;
	// private TextView tv_idno_title;
	// private TextView tv_idno;
	// private TextView tv_gender_title;
	// private TextView tv_gender;
	// private TextView tv_birthday_title;
	// private TextView tv_birthday;
	// private TextView tv_mail_title;
	// private TextView tv_mail;
	// private TextView tv_cellnumber_title;
	// private TextView tv_cellnumber;
	// private TextView tv_createtime_title;
	// private TextView tv_createtime;

	/*@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_personal, container, false);

		initView(v);
		initData();
		return v;
	}*/
	
	@Override
	protected View initContentView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View v = inflater.inflate(R.layout.fragment_personal, container, false);

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
		layout_title = (RelativeLayout) v.findViewById(R.id.layout_title);
		uiAdapter.setMargin(layout_title, LayoutParams.MATCH_PARENT,
				Constants.TitleHeight, 0, 0, 0, 0);

		iv_title = (ImageView) v.findViewById(R.id.iv_title);
		uiAdapter.setMargin(iv_title, Constants.TitleIvWidth,
				Constants.TitleIvWidth, 10, 0, 10, 0);

		tv_title = (TextView) v.findViewById(R.id.tv_title);
		uiAdapter.setTextSize(tv_title, Constants.TitleSize);
		
		ivLogo = (ImageView) v.findViewById(R.id.iv_logo);
		layout_personinfo = (LinearLayout) v
				.findViewById(R.id.layout_personinfo);
		uiAdapter.setMargin(layout_personinfo, LayoutParams.MATCH_PARENT, 113,
				0, 0, 0, 0);

		img_company = (ImageView) v.findViewById(R.id.img_company);
		uiAdapter.setMargin(img_company, 76, 76, 30, 10, 30, 20);

		tv_name = (TextView) v.findViewById(R.id.tv_name);
		uiAdapter.setTextSize(tv_name, 20);
		uiAdapter.setMargin(tv_name, LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT, 0, 10, 0, 0);

		tv_company = (TextView) v.findViewById(R.id.tv_company);
		uiAdapter.setTextSize(tv_company, 16);
		uiAdapter.setMargin(tv_company, LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT, 0, 5, 0, 0);

		tv_points_title = (TextView) v.findViewById(R.id.tv_points_title);
		uiAdapter.setTextSize(tv_points_title, 16);
		uiAdapter.setMargin(tv_points_title, LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT, 0, 5, 0, 20);

		tv_points = (TextView) v.findViewById(R.id.tv_points);
		uiAdapter.setTextSize(tv_points, 16);
		uiAdapter.setMargin(tv_points, LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT, 0, 5, 0, 0);

		layout_personalinfo = (RelativeLayout) v
				.findViewById(R.id.layout_personalinfo);
		layout_personalinfo.setOnClickListener(this);
		uiAdapter.setMargin(layout_personalinfo, LayoutParams.MATCH_PARENT, 54, 0,
				0, 0, 0);
		tv_personalinfo = (TextView) v.findViewById(R.id.tv_personalinfo);
		uiAdapter.setTextSize(tv_personalinfo, 20);
		uiAdapter.setMargin(tv_personalinfo, LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT, 34, 0, 0, 0);
		img_personalinfo_arrow = (ImageView) v
				.findViewById(R.id.img_personalinfo_arrow);
		uiAdapter.setMargin(img_personalinfo_arrow, 12,
				uiAdapter.CalcHeight(12, 16, 28), 0, 0, 20, 0);

		layout_changepwd = (RelativeLayout) v
				.findViewById(R.id.layout_changepwd);
		layout_changepwd.setOnClickListener(this);
		uiAdapter.setMargin(layout_changepwd, LayoutParams.MATCH_PARENT, 54, 0,
				0, 0, 0);
		tv_changepwd = (TextView) v.findViewById(R.id.tv_changepwd);
		uiAdapter.setTextSize(tv_changepwd, 20);
		uiAdapter.setMargin(tv_changepwd, LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT, 34, 0, 0, 0);
		img_changepwd_arrow = (ImageView) v
				.findViewById(R.id.img_changepwd_arrow);
		uiAdapter.setMargin(img_changepwd_arrow, 12,
				uiAdapter.CalcHeight(12, 16, 28), 0, 0, 20, 0);

		layout_orderquery = (RelativeLayout) v
				.findViewById(R.id.layout_orderquery);
		layout_orderquery.setOnClickListener(this);
		uiAdapter.setMargin(layout_orderquery, LayoutParams.MATCH_PARENT, 54,
				0, 0, 0, 0);
		tv_orderquery = (TextView) v.findViewById(R.id.tv_orderquery);
		uiAdapter.setTextSize(tv_orderquery, 20);
		uiAdapter.setMargin(tv_orderquery, LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT, 34, 0, 0, 0);
		img_orderquery_arrow = (ImageView) v
				.findViewById(R.id.img_orderquery_arrow);
		uiAdapter.setMargin(img_orderquery_arrow, 12,
				uiAdapter.CalcHeight(12, 16, 28), 0, 0, 20, 0);

		layout_integrationhistory = (RelativeLayout) v
				.findViewById(R.id.layout_integrationhistory);
		layout_integrationhistory.setOnClickListener(this);
		uiAdapter.setMargin(layout_integrationhistory,
				LayoutParams.MATCH_PARENT, 54, 0, 0, 0, 0);
		tv_integrationhistory = (TextView) v
				.findViewById(R.id.tv_integrationhistory);
		uiAdapter.setTextSize(tv_integrationhistory, 20);
		uiAdapter.setMargin(tv_integrationhistory, LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT, 34, 0, 0, 0);
		img_integrationhistory_arrow = (ImageView) v
				.findViewById(R.id.img_integrationhistory_arrow);
		uiAdapter.setMargin(img_integrationhistory_arrow, 12,
				uiAdapter.CalcHeight(12, 16, 28), 0, 0, 20, 0);

		layout_checkupdate = (RelativeLayout) v
				.findViewById(R.id.layout_checkupdate);
		uiAdapter.setMargin(layout_checkupdate, LayoutParams.MATCH_PARENT, 54,
				0, 54, 0, 0);
		layout_checkupdate.setOnClickListener(this);
		tv_checkupdate = (TextView) v.findViewById(R.id.tv_checkupdate);
		uiAdapter.setTextSize(tv_checkupdate, 20);
		uiAdapter.setMargin(tv_checkupdate, LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT, 34, 0, 20, 0);
		img_checkupdate_arrow = (ImageView) v
				.findViewById(R.id.img_checkupdate_arrow);
		uiAdapter.setMargin(img_checkupdate_arrow, 12,
				uiAdapter.CalcHeight(12, 16, 28), 0, 0, 20, 0);

		// tv_loginname_title = (TextView)
		// v.findViewById(R.id.tv_loginname_title);
		// uiAdapter.setTextSize(tv_loginname_title, 20);
		// uiAdapter.setMargin(tv_loginname_title, LayoutParams.WRAP_CONTENT,
		// LayoutParams.WRAP_CONTENT, 60, 10, 20, 10);
		//
		// tv_loginname = (TextView) v.findViewById(R.id.tv_loginname);
		// uiAdapter.setTextSize(tv_loginname, 20);
		//
		// tv_username_title = (TextView)
		// v.findViewById(R.id.tv_username_title);
		// uiAdapter.setTextSize(tv_username_title, 20);
		// uiAdapter.setMargin(tv_username_title, LayoutParams.WRAP_CONTENT,
		// LayoutParams.WRAP_CONTENT, 60, 10, 20, 10);
		//
		// tv_username = (TextView) v.findViewById(R.id.tv_username);
		// uiAdapter.setTextSize(tv_username, 20);
		//
		// tv_idno_title = (TextView) v.findViewById(R.id.tv_idno_title);
		// uiAdapter.setTextSize(tv_idno_title, 20);
		// uiAdapter.setMargin(tv_idno_title, LayoutParams.WRAP_CONTENT,
		// LayoutParams.WRAP_CONTENT, 60, 10, 20, 10);
		//
		// tv_idno = (TextView) v.findViewById(R.id.tv_idno);
		// uiAdapter.setTextSize(tv_idno, 20);
		//
		// tv_gender_title = (TextView) v.findViewById(R.id.tv_gender_title);
		// uiAdapter.setTextSize(tv_gender_title, 20);
		// uiAdapter.setMargin(tv_gender_title, LayoutParams.WRAP_CONTENT,
		// LayoutParams.WRAP_CONTENT, 60, 10, 20, 10);
		//
		// tv_gender = (TextView) v.findViewById(R.id.tv_gender);
		// uiAdapter.setTextSize(tv_gender, 20);
		//
		// tv_birthday_title = (TextView)
		// v.findViewById(R.id.tv_birthday_title);
		// uiAdapter.setTextSize(tv_birthday_title, 20);
		// uiAdapter.setMargin(tv_birthday_title, LayoutParams.WRAP_CONTENT,
		// LayoutParams.WRAP_CONTENT, 60, 10, 20, 10);
		//
		// tv_birthday = (TextView) v.findViewById(R.id.tv_birthday);
		// uiAdapter.setTextSize(tv_birthday, 20);
		//
		// tv_mail_title = (TextView) v.findViewById(R.id.tv_mail_title);
		// uiAdapter.setTextSize(tv_mail_title, 20);
		// uiAdapter.setMargin(tv_mail_title, LayoutParams.WRAP_CONTENT,
		// LayoutParams.WRAP_CONTENT, 60, 10, 20, 10);
		//
		// tv_mail = (TextView) v.findViewById(R.id.tv_mail);
		// uiAdapter.setTextSize(tv_mail, 20);
		//
		// tv_cellnumber_title = (TextView)
		// v.findViewById(R.id.tv_cellnumber_title);
		// uiAdapter.setTextSize(tv_cellnumber_title, 20);
		// uiAdapter.setMargin(tv_cellnumber_title, LayoutParams.WRAP_CONTENT,
		// LayoutParams.WRAP_CONTENT, 60, 10, 20, 10);
		//
		// tv_cellnumber = (TextView) v.findViewById(R.id.tv_cellnumber);
		// uiAdapter.setTextSize(tv_cellnumber, 20);
		//
		// tv_createtime_title = (TextView)
		// v.findViewById(R.id.tv_createtime_title);
		// uiAdapter.setTextSize(tv_createtime_title, 20);
		// uiAdapter.setMargin(tv_createtime_title, LayoutParams.WRAP_CONTENT,
		// LayoutParams.WRAP_CONTENT, 60, 10, 20, 10);
		//
		// tv_createtime = (TextView) v.findViewById(R.id.tv_createtime);
		// uiAdapter.setTextSize(tv_createtime, 20);

		btn_loginout = (Button) v.findViewById(R.id.btn_loginout);
		uiAdapter.setMargin(btn_loginout, LayoutParams.MATCH_PARENT, 46, 10, 0,
				10, 50);
		uiAdapter.setTextSize(btn_loginout, 24);
		int color =0;
		if(appSet != null){
			try {
				color = Color.parseColor(appSet.getColor2());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(color !=0){
			//设置颜色
			btn_loginout.setBackgroundColor(color);
		}
		
		btn_loginout.setOnClickListener(this);
	}

	private void initData() {
		OperationBuilder builder = new OperationBuilder().append(
				new UserinfoOp(), null);
		OnOperationListener listener = new OnOperationListener() {
			@Override
			public void onOperationFinished(List<Object> resList) {
				if (mActivity.isFinishing()) {
					return;
				}
				if (resList == null) {
					// Toast.show(mActivity, "连接超时");
					return;
				}
				UserEntity entity = (UserEntity) resList.get(0);
				userinfo = entity.getRetobj();
				if (userinfo != null) {
					ImageLoader.getInstance().displayImage(
							Constants.IMGLOGO
									+ userinfo.getCompanyInfo().getCompLogo(),
							img_company);

					

					tv_name.setText(userinfo.getUserName());
					tv_company.setText(userinfo.getCompanyName());
					tv_points.setText(userinfo.getPoints() + "");

					// tv_loginname.setText(userinfo.getLoginName());
					// tv_username.setText(userinfo.getUserName());
					// tv_idno.setText(userinfo.getIdNo());
					// tv_gender.setText(userinfo.getGender().equals("0") ? "男"
					// : "女");
					// tv_birthday.setText(sdf.format(new
					// Date(Long.parseLong(userinfo.getBirthDay().substring(6,
					// 18)))));
					// tv_mail.setText(userinfo.getMail());
					// tv_cellnumber.setText(userinfo.getCellNumber());
					//
					// tv_createtime.setText(sdf.format(new
					// Date(Long.parseLong(userinfo.getCreateTime().substring(6,
					// 19)))));
				}
			}

			@Override
			public void onOperationError(Exception e) {
				e.printStackTrace();
			}
		};
		JsonCommon task = new JsonCommon(mActivity, builder, listener, false);
		task.execute();
	}

	@Override
	public void onClick(View v) {
		Intent intent = new Intent();
		switch (v.getId()) {
		case R.id.layout_personalinfo:
			if (userinfo == null) {
				Toast.show(mActivity, "没有用户信息！");
				return;
			}
			intent.putExtra(EXTRA_USERINFO, userinfo);
			intent.setClass(mActivity, PersonalInfoActivity.class);
			startActivity(intent);
			break;
		case R.id.layout_changepwd:
			intent.setClass(mActivity, ChangePwdActivity.class);
			startActivity(intent);
			break;
		case R.id.layout_integrationhistory:
			intent.setClass(mActivity, HisPointsActivity.class);
			startActivity(intent);
			break;
		case R.id.btn_loginout:
			// 清空用户名密码
			DialogUtil dUtil = new DialogUtil(mActivity);
			dUtil.showDialog("是否注销登录？", 0,"确定", "取消", new DialogButtonClickCallback() {
				@Override
				public void positiveButtonClick() {
					// TODO Auto-generated method stub
					MainActivity.CleanShopCar(mActivity);
					SharedPreferencesUtils.setLoginName(mActivity, "");
					SharedPreferencesUtils.setLoginPwd(mActivity, "");
					Intent intent = new Intent();
					intent.setClass(mActivity, LoginActivity.class);
					startActivity(intent);
					mActivity.finish();
				}
				@Override
				public void negativeButtonClick() {
					// TODO Auto-generated method stub
				}
			});
			break;
		case R.id.layout_orderquery:
			//我的订单
			intent.setClass(mActivity, OrderqueryActivity.class);
			startActivity(intent);
			break;
		case R.id.layout_checkupdate:
			checkUpdate();
			break;
		default:
			break;
		}
	}
	
	private void checkUpdate() {
		OperationBuilder builder = new OperationBuilder().append(
				new UpdateOp(), null);
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
				UpdateEntity entity = (UpdateEntity) resList.get(0);
				Version newversion = entity.getRetobj();
				if (newversion.getVersion() != null) {
					String version = "";
					try {
						version = mActivity.getPackageManager().getPackageInfo(
								mActivity.getPackageName(), 0).versionName;
					} catch (NameNotFoundException e) {
						e.printStackTrace();
					}
					if (!newversion.getVersion().equals(version)) {
						UpdateManager updatemanager = new UpdateManager(mActivity);
						updatemanager.showNoticeDialog();
					} else {
						Toast.show(mActivity, "软件是最新版本！");
					}
				}
			}

			@Override
			public void onOperationError(Exception e) {
				e.printStackTrace();
			}
		};
		JsonCommon task = new JsonCommon(mActivity, builder, listener, JsonCommon.PROGRESSUPDATE);
		task.execute();
	}
}
