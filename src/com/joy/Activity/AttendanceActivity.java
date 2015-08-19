package com.joy.Activity;

import gejw.android.quickandroid.widget.Toast;

import java.util.List;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.joy.JoyApplication;
import com.joy.R;
import com.joy.Dialog.DialogUtil;
import com.joy.Utils.Constants;
import com.joy.Utils.SharedPreferencesUtils;
import com.joy.Widget.AttendanceAdapter;
import com.joy.json.JsonCommon;
import com.joy.json.JsonCommon.OnOperationListener;
import com.joy.json.model.ChangePwdEntity;
import com.joy.json.model.CompAppSet;
import com.joy.json.operation.OperationBuilder;
import com.joy.json.operation.impl.ChangePwdOp;
import com.umeng.analytics.MobclickAgent;

public class AttendanceActivity extends BaseActivity implements OnClickListener {

	private RelativeLayout layout_title;
	private ImageView iv_ret;
	private TextView tv_title;
	private ImageView iv_currpwd;
	private EditText et_currpwd;
	private ImageView iv_newpwd;
	private EditText et_newpwd;
	private ImageView iv_comfirmnewpwd;
	private EditText et_comfirmnewpwd;
	private Button btn_changepwd;
	CompAppSet appSet;
	int color;
	private Resources resources;
    private DialogUtil dialogUtil;
/*	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_changepwd);

		layout_title = (RelativeLayout) findViewById(R.id.layout_title);
		uiAdapter.setMargin(layout_title, LayoutParams.MATCH_PARENT, Constants.TitleHeight, 0, 0, 0, 0);

		tv_ret = (TextView) findViewById(R.id.tv_ret);
		tv_ret.setOnClickListener(this);
		uiAdapter.setTextSize(tv_ret, Constants.TitleRetSize);
		uiAdapter.setMargin(tv_ret, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 20, 0, 0, 0);

		tv_title = (TextView) findViewById(R.id.tv_title);
		uiAdapter.setTextSize(tv_title, Constants.TitleSize);

		iv_currpwd = (ImageView) findViewById(R.id.iv_currpwd);
		uiAdapter.setMargin(iv_currpwd, 50, uiAdapter.CalcHeight(50, 1, 1), 23, 34, 0, 0);
		uiAdapter.setPadding(iv_currpwd, 10, 10, 10, 10);

		et_currpwd = (EditText) findViewById(R.id.et_currpwd);
		uiAdapter.setMargin(et_currpwd, LayoutParams.MATCH_PARENT, 50, 0, 34, 20, 0);
		uiAdapter.setPadding(et_currpwd, 10, 0, 0, 0);

		iv_newpwd = (ImageView) findViewById(R.id.iv_newpwd);
		uiAdapter.setMargin(iv_newpwd, 50, uiAdapter.CalcHeight(50, 1, 1), 23, 34, 0, 0);
		uiAdapter.setPadding(iv_newpwd, 10, 10, 10, 10);

		et_newpwd = (EditText) findViewById(R.id.et_newpwd);
		uiAdapter.setMargin(et_newpwd, LayoutParams.MATCH_PARENT, 50, 0, 34, 20, 0);
		uiAdapter.setPadding(et_newpwd, 10, 0, 0, 0);

		iv_comfirmnewpwd = (ImageView) findViewById(R.id.iv_comfirmnewpwd);
		uiAdapter.setMargin(iv_comfirmnewpwd, 50, uiAdapter.CalcHeight(50, 1, 1), 23, 34, 0, 0);
		uiAdapter.setPadding(iv_comfirmnewpwd, 10, 10, 10, 10);

		et_comfirmnewpwd = (EditText) findViewById(R.id.et_comfirmnewpwd);
		uiAdapter.setMargin(et_comfirmnewpwd, LayoutParams.MATCH_PARENT, 50, 0, 34, 20, 20);
		uiAdapter.setPadding(et_comfirmnewpwd, 10, 0, 0, 0);

		btn_changepwd = (Button) findViewById(R.id.btn_changepwd);
		uiAdapter.setMargin(btn_changepwd, LayoutParams.MATCH_PARENT, 46, 20, 50, 20, 50);
		uiAdapter.setTextSize(btn_changepwd, 24);
		btn_changepwd.setOnClickListener(this);
	}*/

	@Override
	protected View ceateView(LayoutInflater inflater, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		resources = getResources();
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

		View v = inflater.inflate(R.layout.activity_changepwd, null);
		setContentView(v);
		layout_title = (RelativeLayout) findViewById(R.id.layout_title);
		uiAdapter.setMargin(layout_title, LayoutParams.MATCH_PARENT,
				Constants.SubTitleHeight, 0, 0, 0, 0);

		iv_ret = (ImageView) findViewById(R.id.iv_ret);
		iv_ret.setOnClickListener(this);

		tv_title = (TextView) findViewById(R.id.tv_title);
		uiAdapter.setTextSize(tv_title, Constants.TitleSize);
		tv_title.setText(R.string.titlepwd);

		iv_currpwd = (ImageView) findViewById(R.id.iv_currpwd);
		uiAdapter.setMargin(iv_currpwd, 50, uiAdapter.CalcHeight(50, 1, 1), 23,
				34, 0, 0);
		uiAdapter.setPadding(iv_currpwd, 10, 10, 10, 10);

		et_currpwd = (EditText) findViewById(R.id.et_currpwd);
		uiAdapter.setMargin(et_currpwd, LayoutParams.MATCH_PARENT, 50, 0, 34,
				20, 0);
		uiAdapter.setPadding(et_currpwd, 10, 0, 0, 0);

		iv_newpwd = (ImageView) findViewById(R.id.iv_newpwd);
		uiAdapter.setMargin(iv_newpwd, 50, uiAdapter.CalcHeight(50, 1, 1), 23,
				34, 0, 0);
		uiAdapter.setPadding(iv_newpwd, 10, 10, 10, 10);
		iv_newpwd.setBackgroundColor(color);

		et_newpwd = (EditText) findViewById(R.id.et_newpwd);
		uiAdapter.setMargin(et_newpwd, LayoutParams.MATCH_PARENT, 50, 0, 34,
				20, 0);
		uiAdapter.setPadding(et_newpwd, 10, 0, 0, 0);

		iv_comfirmnewpwd = (ImageView) findViewById(R.id.iv_comfirmnewpwd);
		uiAdapter.setMargin(iv_comfirmnewpwd, 50,
				uiAdapter.CalcHeight(50, 1, 1), 23, 34, 0, 0);
		uiAdapter.setPadding(iv_comfirmnewpwd, 10, 10, 10, 10);
		iv_comfirmnewpwd.setBackgroundColor(color);

		et_comfirmnewpwd = (EditText) findViewById(R.id.et_comfirmnewpwd);
		uiAdapter.setMargin(et_comfirmnewpwd, LayoutParams.MATCH_PARENT, 50, 0,
				34, 20, 20);
		uiAdapter.setPadding(et_comfirmnewpwd, 10, 0, 0, 0);

		btn_changepwd = (Button) findViewById(R.id.btn_changepwd);
		uiAdapter.setMargin(btn_changepwd, LayoutParams.MATCH_PARENT, 46, 10,
				50, 10, 50);
		uiAdapter.setTextSize(btn_changepwd, 24);

		int color = 0;
		if (appSet != null) {
			try {
				color = Color.parseColor(appSet.getColor2());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (color != 0) {
			// 设置颜色
			btn_changepwd.setBackgroundColor(color);
		}

		btn_changepwd.setOnClickListener(this);
		return v;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.iv_ret:
			finish();
			break;
		case R.id.btn_punchin:
			/*dialogUtil.showSingleChoiceDialog("APP考勤", 0, "确认要打卡吗？", new String[]{ "上班", "下班" }, "确定", "取消", new DialogButtonClickCallback() {
				@Override
				public void positiveButtonClick() {
					if (attendanceEntity == null) {
						Toast.show(self, "您还未定位到当前位置,打卡失败！");
					} else {
						attendanceEntity.setPunchType("0");
						punch(attendanceEntity);
					}
					
				}
				@Override
				public void negativeButtonClick() {
					
				}
			});*/
//			dialogUtil.showDialog("确认要上班打卡吗？", 0, "确定", "取消", new DialogButtonClickCallback() {
//				@Override
//				public void positiveButtonClick() {
//					if (attendanceEntity == null) {
//						Toast.show(self, "您还未定位到当前位置,打卡失败！");
//					} else {
//						attendanceEntity.setPunchType("0");
//						punch(attendanceEntity);
//					}
//				}
//				public void negativeButtonClick() {
//				}
//				
//			});
//			break;
//		case R.id.btn_punchout:
//			dialogUtil.showDialog("确认要下班打卡吗？", 0, "确定", "取消", new DialogButtonClickCallback() {
//				@Override
//				public void positiveButtonClick() {
//					if (attendanceEntity == null) {
//						Toast.show(self, "您还未定位到当前位置,打卡失败！");
//					} else {
//						attendanceEntity.setPunchType("1");
//						punch(attendanceEntity);
//					}
//				}
//				public void negativeButtonClick() {
//				}
//				
//			});
//			break;
		default:
			break;
		}
	}

	private void changepwd(String ologinpwd, final String nloginpwd) {
		ChangePwdEntity entity = new ChangePwdEntity();
		entity.setOloginpwd(ologinpwd);
		entity.setNloginpwd(nloginpwd);
		OperationBuilder builder = new OperationBuilder().append(
				new ChangePwdOp(), entity);
		OnOperationListener listener = new OnOperationListener() {
			@Override
			public void onOperationFinished(List<Object> resList) {
				if (self.isFinishing()) {
					return;
				}
				if (resList == null) {
					Toast.show(self, resources.getString(R.string.timeout));
					return;
				}
				ChangePwdEntity entity = (ChangePwdEntity) resList.get(0);
				int retobj = entity.isRetobj();
				if (retobj !=1) {
					Toast.show(self, resources.getString(R.string.oldpwderr));
					return;
				} else {
					SharedPreferencesUtils.setLoginPwd(self, nloginpwd);
					Toast.show(self, resources.getString(R.string.chgpwdsuccess));
					MainActivity.CleanShopCar(self);
					SharedPreferencesUtils.setLoginName(self, "");
					SharedPreferencesUtils.setLoginPwd(self, "");
					Intent intent = new Intent();
					intent.setClass(self, LoginActivity.class);
					startActivity(intent);
					finish();
					return;
				}
			}

			@Override
			public void onOperationError(Exception e) {
				e.printStackTrace();
			}
		};

		JsonCommon task = new JsonCommon(self, builder, listener,
				JsonCommon.PROGRESSCOMMIT);
		task.execute();
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
