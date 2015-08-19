package com.joy.Activity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import gejw.android.quickandroid.QActivity;
import gejw.android.quickandroid.widget.Toast;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.joy.JoyApplication;
import com.joy.R;
import com.joy.Dialog.DialogUtil;
import com.joy.Dialog.DialogUtil.DialogButtonClickCallback;
import com.joy.Utils.Constants;
import com.joy.json.JsonCommon;
import com.joy.json.JsonCommon.OnOperationListener;
import com.joy.json.model.ActivityDetailEntity;
import com.joy.json.model.ActjoinEntity;
import com.joy.json.model.ActjoinEntity.Result;
import com.joy.json.model.CompAppSet;
import com.joy.json.model.PostDetailEntity;
import com.joy.json.model.RuleEntity;
import com.joy.json.operation.OperationBuilder;
import com.joy.json.operation.impl.ActjoinOp;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.umeng.analytics.MobclickAgent;

/**
 * 公告详情
 * 
 * @author ryan zhou 2014-12-22
 * 
 */
public class RuleDetailActivity extends BaseActivity implements OnClickListener {

	public static final String RuleDetails = "ruledetails";
	private RelativeLayout layout_title;
	private ImageView iv_ret;
	private TextView tv_title;
	private TextView tv_rulename;
	private TextView tv_rulecontent;
	private TextView tv_ruletime;
	private Resources resources;
	public String acttype;
	DialogUtil dUtil;
	CompAppSet appSet;
	int color;
	boolean cancel = false;
	String joinTxt;

	/*@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_activitysub);

		resources = getResources();
		dUtil = new DialogUtil(self);

		initView();
		initData();
	}*/
	
	@Override
	protected View ceateView(LayoutInflater inflater, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View v = inflater.inflate(R.layout.activity_ruledetail, null);
		setContentView(v);
		resources = getResources();
		dUtil = new DialogUtil(self);
		color = 0;
		appSet = JoyApplication.getInstance().getCompAppSet();
		initView();
		initData();
		return v;
	}

	private void initView() {
		layout_title = (RelativeLayout) findViewById(R.id.layout_title);
		uiAdapter.setMargin(layout_title, LayoutParams.MATCH_PARENT,
				Constants.SubTitleHeight, 0, 0, 0, 0);

		iv_ret = (ImageView) findViewById(R.id.iv_ret);
		iv_ret.setOnClickListener(this);

		tv_title = (TextView) findViewById(R.id.tv_title);
		tv_title.setText(R.string.ruledetail);
		uiAdapter.setTextSize(tv_title, Constants.TitleSize);

		tv_rulename = (TextView) findViewById(R.id.tv_rulename);
		uiAdapter.setTextSize(tv_rulename, 24);
		uiAdapter.setMargin(tv_rulename, LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT, 0, 0, 0, 0);
		
		tv_ruletime = (TextView) findViewById(R.id.tv_ruletime);
		uiAdapter.setMargin(tv_ruletime, LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT, 0, 0, 0, 0);
		uiAdapter.setTextSize(tv_ruletime, 20);
		
		tv_rulecontent = (TextView) findViewById(R.id.tv_rulecontent);
		uiAdapter.setMargin(tv_rulecontent, LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT, 30, 5, 20, 5);
		uiAdapter.setTextSize(tv_rulecontent, 22);

	}

	private void initData() {
		Intent intent = getIntent();
		RuleEntity entity = (RuleEntity) intent.getSerializableExtra(RuleDetails);
		tv_rulename.setText(entity.getTitle());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		tv_ruletime.setText(sdf.format(new Date(Long.parseLong(entity.getPublishDate().substring(6, 19)))));
		tv_rulecontent.setText(Html.fromHtml(entity.getContent()));

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.iv_ret:
			finish();
			break;

		default:
			break;
		}
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
