package com.joy.Activity;

import gejw.android.quickandroid.QActivity;
import gejw.android.quickandroid.widget.Toast;

import java.util.List;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.EditText;

import com.joy.JoyApplication;
import com.joy.R;
import com.joy.json.JsonCommon;
import com.joy.json.JsonCommon.OnOperationListener;
import com.joy.json.model.LoginEntity;
import com.joy.json.model.UserInfo;
import com.joy.json.operation.OperationBuilder;
import com.joy.json.operation.impl.LoginOp;

/**
 * 登录
 * @author daiye
 *
 */
public class LoginActivity extends QActivity {

	private EditText et_user;
	private EditText et_pwd;
	private Button btn_login;
	private Resources resources;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);

		resources = getResources();
		initView();
	}

	private void initView() {
		et_user = (EditText) findViewById(R.id.et_user);
		uiAdapter.setMargin(et_user, LayoutParams.MATCH_PARENT, 60, 50, 100, 50, 0);
		et_user.setText("steven");
		
		et_pwd = (EditText) findViewById(R.id.et_pwd);
		uiAdapter.setMargin(et_pwd, LayoutParams.MATCH_PARENT,
				60, 50, 50, 50, 0);
		et_pwd.setText("121");
		
		btn_login = (Button) findViewById(R.id.btn_login);
		uiAdapter.setMargin(btn_login, LayoutParams.MATCH_PARENT,
				60, 50, 50, 50, 0);
		btn_login.setOnClickListener(clicklistener);
	}
	
	OnClickListener clicklistener = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			String loginname = et_user.getText().toString().trim();
			String loginpwd = et_pwd.getText().toString().trim();
			if (TextUtils.isEmpty(loginname) || TextUtils.isEmpty(loginpwd)) {
				Toast.show(self, resources.getString(R.string.toast_login_empty));
				return;
			}
			
			LoginEntity loginentity = new LoginEntity();
			loginentity.setLoginname(loginname);
			loginentity.setLoginpwd(loginpwd);
			
			OperationBuilder builder = new OperationBuilder().append(
					new LoginOp(), loginentity);
			OnOperationListener listener = new OnOperationListener() {
				@Override
				public void onOperationFinished(List<Object> resList) {
					if (self.isFinishing()) {
						return;
					}
					if (resList == null) {
						Toast.show(self, "连接超时");
						return;
					}
					LoginEntity entity = (LoginEntity) resList.get(0);
					UserInfo userInfoEntity = entity.getRetobj();
					if (userInfoEntity == null) {
						Toast.show(self, "用户名或密码错误！");
						return;
					}
					JoyApplication.getInstance().setUserinfo(userInfoEntity);
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
	};
}
