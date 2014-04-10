package com.joy.Activity;

import gejw.android.quickandroid.QActivity;
import gejw.android.quickandroid.widget.Toast;

import java.util.List;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.joy.R;
import com.joy.Utils.Constants;
import com.joy.Utils.SharedPreferencesUtils;
import com.joy.json.JsonCommon;
import com.joy.json.JsonCommon.OnOperationListener;
import com.joy.json.model.ChangePwdEntity;
import com.joy.json.operation.OperationBuilder;
import com.joy.json.operation.impl.ChangePwdOp;
import com.umeng.analytics.MobclickAgent;

public class ChangePwdActivity extends QActivity implements OnClickListener {
	
	private RelativeLayout layout_title;
	private TextView tv_ret;
	private TextView tv_title;
	private ImageView iv_currpwd;
	private EditText et_currpwd;
	private ImageView iv_newpwd;
	private EditText et_newpwd;
	private ImageView iv_comfirmnewpwd;
	private EditText et_comfirmnewpwd;
	private Button btn_changepwd;
	
	@Override
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
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.tv_ret:
			finish();
			break;
		case R.id.btn_changepwd:
			String currpwd = et_currpwd.getText().toString();
			String newpwd = et_newpwd.getText().toString();
			String comfirmnewpwd = et_comfirmnewpwd.getText().toString();
			if (TextUtils.isEmpty(currpwd) || TextUtils.isEmpty(newpwd) || TextUtils.isEmpty(comfirmnewpwd)) {
				Toast.show(self, "请输入密码！");
				return;
			} else if (!newpwd.equals(comfirmnewpwd)) {
				Toast.show(self, "新密码输入与确认密码不同！");
				return;
			} else {
				changepwd(currpwd, newpwd);
			}
			break;
		default:
			break;
		}
	}
	
	private void changepwd(String ologinpwd, final String nloginpwd) {
		ChangePwdEntity entity = new ChangePwdEntity();
		entity.setOloginpwd(ologinpwd);
		entity.setNloginpwd(nloginpwd);
		OperationBuilder builder = new OperationBuilder().append(new ChangePwdOp(),
				entity);
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
				ChangePwdEntity entity = (ChangePwdEntity) resList.get(0);
				boolean retobj = entity.isRetobj();
				if (!retobj) {
					Toast.show(self, "旧密码不正确！");
					return;
				} else {
					SharedPreferencesUtils.setLoginPwd(self, nloginpwd);
					Toast.show(self, "修改密码成功！");
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
