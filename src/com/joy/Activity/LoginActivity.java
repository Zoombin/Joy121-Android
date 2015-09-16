package com.joy.Activity;

import gejw.android.quickandroid.QActivity;
import gejw.android.quickandroid.widget.Toast;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import android.content.Intent;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.api.TagAliasCallback;

import com.joy.JoyApplication;
import com.joy.R;
import com.joy.Utils.MobileCodeManager;
import com.joy.Utils.SharedPreferencesUtils;
import com.joy.Utils.UpdateManager;
import com.joy.json.JsonCommon;
import com.joy.json.JsonCommon.OnOperationListener;
import com.joy.json.model.CompAppSet;
import com.joy.json.model.LoginEntity;
import com.joy.json.model.UserInfoEntity;
import com.joy.json.model.VersionEntity;
import com.joy.json.model.VersionEntity.VersionInfoEntity;
import com.joy.json.operation.OperationBuilder;
import com.joy.json.operation.impl.LoginOp;
import com.umeng.analytics.MobclickAgent;

/**
 * 登录
 * @author daiye
 *
 */
public class LoginActivity extends QActivity {

	private TextView tv_login;
	private LinearLayout layout_login;
	private ImageView iv_user;
	private EditText et_user;
	private ImageView iv_pwd;
	private EditText et_pwd;
	private CheckBox ckb_auto;
	private TextView tv_auto;
	private TextView tv_forgetpwd;
	
	private Button ib_login;

	private TextView tv_consult;
	private Resources resources;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		resources = getResources();

		String loginname = SharedPreferencesUtils.getLoginName(self);
		String loginpwd = SharedPreferencesUtils.getLoginPwd(self);
		final MobileCodeManager smsmager ;
		smsmager=new MobileCodeManager(self,self);
		if (!loginpwd.equals("")) {
			login(loginname, loginpwd);
			return;
		}
		setContentView(R.layout.activity_login);
		
		//响应tv_forgetPwd事件
				tv_forgetpwd=(TextView)findViewById(R.id.tv_forgetpwd);
				tv_forgetpwd.setOnClickListener(new OnClickListener(){
					public void onClick(View v)
					{
						smsmager.forgetPwd();
					}});
		initView();
	}

	private void initView() {
		tv_login = (TextView) findViewById(R.id.tv_login);
		uiAdapter.setTextSize(tv_login, 30);
		uiAdapter.setMargin(tv_login, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 0, 40, 0, 0);
		
		layout_login = (LinearLayout) findViewById(R.id.layout_login);
		uiAdapter.setMargin(layout_login, LayoutParams.MATCH_PARENT, 240, 20, 20, 20, 0);
		
		iv_user = (ImageView) findViewById(R.id.iv_user);
		uiAdapter.setMargin(iv_user, 50, uiAdapter.CalcHeight(50, 1, 1), 23, 34, 0, 0);
		uiAdapter.setPadding(iv_user, 10, 10, 10, 10);
		
		et_user = (EditText) findViewById(R.id.et_user);
		uiAdapter.setMargin(et_user, LayoutParams.MATCH_PARENT, 50, 0, 34, 20, 20);
		uiAdapter.setPadding(et_user, 10, 0, 0, 0);
		
		iv_pwd = (ImageView)  findViewById(R.id.iv_pwd);
		uiAdapter.setMargin(iv_pwd, 50, uiAdapter.CalcHeight(50, 1, 1), 23, 8, 0, 0);
		uiAdapter.setPadding(iv_pwd, 10, 10, 10, 10);
		
		et_pwd = (EditText) findViewById(R.id.et_pwd);
		uiAdapter.setMargin(et_pwd, LayoutParams.MATCH_PARENT, 50, 0, 8, 20, 30);
		uiAdapter.setPadding(et_pwd, 10, 0, 0, 0);
		
		ckb_auto = (CheckBox) findViewById(R.id.ckb_auto);
		uiAdapter.setMargin(ckb_auto, 22, 22, 23, 7, 5, 14);
		
		tv_auto = (TextView) findViewById(R.id.tv_auto);
		uiAdapter.setTextSize(tv_auto, 18);
		
		tv_forgetpwd = (TextView) findViewById(R.id.tv_forgetpwd);
		uiAdapter.setTextSize(tv_forgetpwd, 18);
		
		ib_login = (Button) findViewById(R.id.ib_login);
		uiAdapter.setMargin(ib_login, 98,
				uiAdapter.CalcHeight(98, 1, 1), 0, 292, 0, 0);
		ib_login.setOnClickListener(clicklistener);
		
		tv_consult = (TextView) findViewById(R.id.tv_consult);
		uiAdapter.setTextSize(tv_consult, 18);
		uiAdapter.setMargin(tv_consult, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 0, 380, 0, 0);
	}
	
	OnClickListener clicklistener = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			login();
		}
	};
	
	private void login(final String loginname, final String loginpwd) {
		if (TextUtils.isEmpty(loginname) || TextUtils.isEmpty(loginpwd)) {
			Toast.show(self, resources.getString(R.string.toast_login_empty));
			return;
		}
		
		LoginEntity loginentity = new LoginEntity();
		loginentity.setLoginname(loginname);
		loginentity.setLoginpwd(loginpwd);
		
		String currentVersion = "";
		try {
			currentVersion = this.getPackageManager().getPackageInfo(
					this.getPackageName(), 0).versionName;
		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}
		String deviceType = "Android";
		
		OperationBuilder builder = new OperationBuilder().append(
				new LoginOp(), loginentity);
		OnOperationListener listener = new OnOperationListener() {
			@Override
			public void onOperationFinished(List<Object> resList) {
				if (self.isFinishing()) {
					return;
				}
				if (resList == null) {
					Toast.show(self, resources.getString(R.string.timeout));
					setContentView(R.layout.activity_login);
					initView();
					et_user.setText(loginname);
					et_pwd.setText(loginpwd);
					return;
				}
				LoginEntity entity = (LoginEntity) resList.get(0);
				UserInfoEntity userInfoEntity = entity.getRetobj();
				if (userInfoEntity == null) {
					Toast.show(self, resources.getString(R.string.toast_login_error));
					setContentView(R.layout.activity_login);
					initView();
					et_user.setText(loginname);
					et_pwd.setText(loginpwd);
					return;
				}
				JoyApplication.getInstance().setUserinfo(userInfoEntity);
				SharedPreferencesUtils.setLoginName(self, loginname);
				SharedPreferencesUtils.setLoginPwd(self, loginpwd);
				SharedPreferencesUtils.setCompany(self, userInfoEntity.getCompany());
				Intent intent = new Intent(self, MainActivity.class);
				startActivity(intent);
				finish();
			}

			@Override
			public void onOperationError(Exception e) {
				e.printStackTrace();
			}
		};
		JsonCommon task = new JsonCommon(self, builder, listener,
				JsonCommon.PROGRESSLOGIN);
		task.execute();
	}
	
	private void updateVersion() {
		OperationBuilder builder = new OperationBuilder().append(
				new LoginOp(), null);
		OnOperationListener listener = new OnOperationListener() {
			@Override
			public void onOperationFinished(List<Object> resList) {
				if (self.isFinishing()) {
					return;
				}
				if (resList == null) {
					return;
				}
				VersionEntity entity = (VersionEntity) resList.get(0);
				VersionInfoEntity versionInfoEntity = entity.getRetobj();
				if (versionInfoEntity == null) {
					Toast.show(self, resources.getString(R.string.toast_login_error));
					setContentView(R.layout.activity_login);
					initView();
					return;
				}
				Intent intent = new Intent(self, MainActivity.class);
				startActivity(intent);
				finish();
			}

			@Override
			public void onOperationError(Exception e) {
				e.printStackTrace();
			}
		};
		JsonCommon task = new JsonCommon(self, builder, listener,
				JsonCommon.PROGRESSLOGIN);
		task.execute();
	}
	
	private void login() {
		final String loginname = et_user.getText().toString().trim();
		final String loginpwd = et_pwd.getText().toString().trim();
		if (TextUtils.isEmpty(loginname) || TextUtils.isEmpty(loginpwd)) {
			Toast.show(self, resources.getString(R.string.toast_login_empty));
			return;
		}
		String currentVersion = "";
		try {
			currentVersion = this.getPackageManager().getPackageInfo(
					this.getPackageName(), 0).versionName;
		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}
		String deviceType = "Android";
		
		LoginEntity loginentity = new LoginEntity();
		loginentity.setLoginname(loginname);
		loginentity.setLoginpwd(loginpwd);
		loginentity.setVersionname(currentVersion);
		loginentity.setDevicetype(deviceType);
		
		OperationBuilder builder = new OperationBuilder().append(
				new LoginOp(), loginentity);
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
				LoginEntity entity = (LoginEntity) resList.get(0);
				UserInfoEntity userInfoEntity = entity.getRetobj();
				if (userInfoEntity == null) {
					Toast.show(self, resources.getString(R.string.toast_login_error));
					return;
				}
				String s = userInfoEntity.getAppAccessCodes();
				String[] ss = s.split(",");
				Set<String> set = new HashSet<String>();
				
				for (int i = 0; i < ss.length; i++) {
					set.add(ss[i]);
					
				}
				setAliasAndTags(null, set);
				JoyApplication.getInstance().setUserinfo(userInfoEntity);
				SharedPreferencesUtils.setLoginName(self, loginname);
				if (ckb_auto.isChecked()) {
					SharedPreferencesUtils.setLoginPwd(self, loginpwd);
				}
				SharedPreferencesUtils.setCompany(self, userInfoEntity.getCompany());
				Intent intent = new Intent(self, MainActivity.class);
				startActivity(intent);
				finish();
			}

			@Override
			public void onOperationError(Exception e) {
				e.printStackTrace();
			}
		};

		JsonCommon task = new JsonCommon(self, builder, listener,
				JsonCommon.PROGRESSLOGIN);
		task.execute();
	}
	
	private void setAliasAndTags(final String alias, final Set<String> tags) {
		
			JPushInterface.setAliasAndTags(this, alias, tags, new TagAliasCallback() {

				@Override
				public void gotResult(int arg0, String arg1, Set<String> arg2) {
					// TODO Auto-generated method stub
					
				}
			
			});
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
