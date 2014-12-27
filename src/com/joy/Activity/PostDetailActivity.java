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
public class PostDetailActivity extends BaseActivity implements OnClickListener {

	public static final String PostDetails = "postdetails";
	private RelativeLayout layout_title;
	private TextView tv_ret;
	private TextView tv_title;
	private TextView tv_postname;
	private ImageView iv_postpicture;
	private TextView tv_postcontent;
	private TextView tv_posttime;
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
		View v = inflater.inflate(R.layout.activity_postdetail, null);
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
				Constants.TitleHeight, 0, 0, 0, 0);

		tv_ret = (TextView) findViewById(R.id.tv_ret);
		tv_ret.setOnClickListener(this);
		uiAdapter.setTextSize(tv_ret, Constants.TitleRetSize);
		uiAdapter.setMargin(tv_ret, LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT, 20, 0, 0, 0);

		tv_title = (TextView) findViewById(R.id.tv_title);
		uiAdapter.setTextSize(tv_title, Constants.TitleSize);

		tv_postname = (TextView) findViewById(R.id.tv_postname);
		uiAdapter.setTextSize(tv_postname, 24);
		uiAdapter.setMargin(tv_postname, LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT, 0, 10, 0, 10);

		iv_postpicture = (ImageView) findViewById(R.id.iv_postpicture);
		uiAdapter.setMargin(iv_postpicture, LayoutParams.MATCH_PARENT,
				LayoutParams.WRAP_CONTENT, 10, 0, 10, 10);
		
		tv_postcontent = (TextView) findViewById(R.id.tv_postcontent);
		uiAdapter.setMargin(tv_postcontent, LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT, 30, 5, 20, 5);
		uiAdapter.setTextSize(tv_postcontent, 22);

		tv_posttime = (TextView) findViewById(R.id.tv_posttime);
		uiAdapter.setMargin(tv_posttime, LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT, 30, 5, 20, 5);
		uiAdapter.setTextSize(tv_posttime, 22);
	}

	private void initData() {
		Intent intent = getIntent();
		PostDetailEntity entity = (PostDetailEntity) intent.getSerializableExtra(PostDetails);
		tv_postname.setText(entity.getTitle());
		if (entity.getPicture() == null || "".equals(entity.getPicture())) {
			iv_postpicture.setVisibility(View.GONE);
		} else {
			ImageLoader.getInstance().displayImage(Constants.IMGPOST+ entity.getPicture(), iv_postpicture);
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		tv_posttime.setText(sdf.format(new Date(Long.parseLong(entity
				.getPostTime().substring(6, 19)))));
		tv_postcontent.setText(Html.fromHtml(entity.getContent()));

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.tv_ret:
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
