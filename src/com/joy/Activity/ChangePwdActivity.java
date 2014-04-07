package com.joy.Activity;

import gejw.android.quickandroid.QActivity;
import gejw.android.quickandroid.widget.Toast;
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

public class ChangePwdActivity extends QActivity implements OnClickListener {
	
	private RelativeLayout layout_title;
	private TextView tv_ret;
	private TextView tv_title;
	private ImageView img_ok;
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
		uiAdapter.setMargin(layout_title, LayoutParams.MATCH_PARENT, 74, 0, 0, 0, 0);

		tv_ret = (TextView) findViewById(R.id.tv_ret);
		uiAdapter.setTextSize(tv_ret, Constants.TitleRetSize);
		uiAdapter.setMargin(tv_ret, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 20, 0, 0, 0);
		
		tv_title = (TextView) findViewById(R.id.tv_title);
		uiAdapter.setTextSize(tv_title, Constants.TitleSize);
		
		img_ok = (ImageView) findViewById(R.id.img_ok);
		uiAdapter.setMargin(img_ok, 34, 34, 0, 0, 20, 0);
		
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
		case R.id.btn_changepwd:
			String currpwd = et_currpwd.getText().toString();
			String newpwd = et_newpwd.getText().toString();
			String comfirmnewpwd = et_comfirmnewpwd.getText().toString();
			if (TextUtils.isEmpty(currpwd) || TextUtils.isEmpty(newpwd) || TextUtils.isEmpty(comfirmnewpwd)) {
				Toast.show(self, "请输入密码！");
				return;
			} else if (newpwd.equals(comfirmnewpwd)) {
				Toast.show(self, "新密码输入与确认密码不同！");
				return;
			} else {
				changepwd();
			}
			break;
		default:
			break;
		}
	}
	
	private void changepwd() {
		Toast.show(self, "没有借口！");
		return;
	}
}
