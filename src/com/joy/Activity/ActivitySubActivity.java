package com.joy.Activity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import gejw.android.quickandroid.QActivity;
import gejw.android.quickandroid.widget.Toast;
import android.content.Intent;
import android.content.res.Resources;
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

import com.joy.R;
import com.joy.Dialog.DialogUtil;
import com.joy.Dialog.DialogUtil.DialogButtonClickCallback;
import com.joy.Utils.Constants;
import com.joy.json.JsonCommon;
import com.joy.json.JsonCommon.OnOperationListener;
import com.joy.json.model.ActivityDetailEntity;
import com.joy.json.model.ActjoinEntity;
import com.joy.json.model.ActjoinEntity.Result;
import com.joy.json.operation.OperationBuilder;
import com.joy.json.operation.impl.ActjoinOp;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.umeng.analytics.MobclickAgent;

/**
 * 活动详情
 * 
 * @author daiye
 * 
 */
public class ActivitySubActivity extends BaseActivity implements OnClickListener {

	public static final String ActivityDetails = "activitydetails";
	private RelativeLayout layout_title;
	private TextView tv_ret;
	private TextView tv_title;
	private TextView tv_actname;
	private ImageView iv_actpicture;
	private TextView tv_count;
	private TextView tv_deadline;
	private Button btn_actjoin;
	private TextView tv_actlocationaddrtitle;
	private TextView tv_actlocationaddr;
	private TextView tv_contenttitle;
	private TextView tv_content;
	private Resources resources;
	public String acttype;
	DialogUtil dUtil;

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
		View v = inflater.inflate(R.layout.activity_activitysub, null);
		setContentView(v);
		resources = getResources();
		dUtil = new DialogUtil(self);

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

		tv_actname = (TextView) findViewById(R.id.tv_actname);
		uiAdapter.setTextSize(tv_actname, 24);
		uiAdapter.setMargin(tv_actname, LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT, 0, 10, 0, 10);

		iv_actpicture = (ImageView) findViewById(R.id.iv_actpicture);
		uiAdapter.setMargin(iv_actpicture, LayoutParams.MATCH_PARENT,
				LayoutParams.WRAP_CONTENT, 10, 0, 10, 10);

		tv_count = (TextView) findViewById(R.id.tv_count);
		uiAdapter.setMargin(tv_count, LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT, 15, 5, 0, 5);
		uiAdapter.setTextSize(tv_count, 20);

		tv_deadline = (TextView) findViewById(R.id.tv_deadline);
		uiAdapter.setMargin(tv_deadline, LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT, 15, 5, 0, 5);
		uiAdapter.setTextSize(tv_deadline, 20);

		btn_actjoin = (Button) findViewById(R.id.btn_actjoin);
		uiAdapter.setMargin(btn_actjoin, 120, 50, 0, 10, 30, 10);
		uiAdapter.setTextSize(btn_actjoin, 20);

		tv_actlocationaddrtitle = (TextView) findViewById(R.id.tv_actlocationaddrtitle);
		uiAdapter.setPadding(tv_actlocationaddrtitle, 10, 5, 0, 5);
		uiAdapter.setTextSize(tv_actlocationaddrtitle, 20);

		tv_actlocationaddr = (TextView) findViewById(R.id.tv_actlocationaddr);
		uiAdapter.setMargin(tv_actlocationaddr, LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT, 30, 5, 20, 5);
		uiAdapter.setTextSize(tv_actlocationaddr, 20);

		tv_contenttitle = (TextView) findViewById(R.id.tv_contenttitle);
		uiAdapter.setPadding(tv_contenttitle, 10, 5, 0, 5);
		uiAdapter.setTextSize(tv_contenttitle, 20);

		tv_content = (TextView) findViewById(R.id.tv_content);
		uiAdapter.setMargin(tv_content, LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT, 30, 5, 20, 5);
		uiAdapter.setTextSize(tv_content, 20);
	}

	private void initData() {
		Intent intent = getIntent();
		ActivityDetailEntity entity = (ActivityDetailEntity) intent.getSerializableExtra(ActivityDetails);
		acttype = intent.getStringExtra("acttype");

		if ("1".equals(acttype)) {
			tv_title.setText("活动详情");
		} else if ("2".equals(acttype)) {
			tv_title.setText("培训详情");
		}

		tv_actname.setText(entity.getActName());
		
		btn_actjoin.setText(entity.getStatus(entity.getLoginName()));
		if (entity.getIsEnabled(entity.getLoginName())) {
			btn_actjoin.setClickable(true);
			btn_actjoin.setTag(entity);
			btn_actjoin.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(final View v) {
					// TODO Auto-generated method stub
					dUtil.showDialog("报名确认", "确定要参加吗？", "确定", "取消", new DialogButtonClickCallback() {
						@Override
						public void positiveButtonClick() {
							// TODO Auto-generated method stub
							final ActivityDetailEntity activitydetailentity = (ActivityDetailEntity) v
									.getTag();
							singUp(v, activitydetailentity);
						}
						
						@Override
						public void negativeButtonClick() {
							// TODO Auto-generated method stub
						}
					});
				}
			});
			btn_actjoin.setBackgroundColor(self.getResources()
					.getColor(R.color.menu_text_press));
		} else {
			btn_actjoin.setClickable(false);
			btn_actjoin.setOnClickListener(null);
			btn_actjoin.setBackgroundColor(self.getResources()
					.getColor(R.color.btn_disable));
		}


		ImageLoader.getInstance().displayImage(
				Constants.IMGDETAIL
						+ entity.getActPicturePath(), iv_actpicture);
		
		tv_count.setText("报名人数" + entity.getCurrentCount() + "/"
				+ entity.getLimitCount());

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		tv_deadline.setText(sdf.format(new Date(Long.parseLong(entity
				.getDeadLine().substring(6, 19)))));

		tv_actlocationaddr.setText(entity.getLocationAddr());

		tv_content.setText(Html.fromHtml(entity.getContent()));
	}

	
	private void singUp(final View v,final ActivityDetailEntity activitydetailentity){
		ActivityDetailEntity entity = activitydetailentity;
		OperationBuilder builder = new OperationBuilder().append(
				new ActjoinOp(), entity);
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
				ActjoinEntity entity = (ActjoinEntity) resList.get(0);
				Result result  = entity.getRetobj();
				if (result == null) {
					Toast.show(ActivitySubActivity.this, "报名失败！");
					return;
				} else {
					String ret = result.getResult();
					if("0".equals(ret)){
						Toast.show(ActivitySubActivity.this, "报名失败！");
						return;
					}else{
						Toast.show(ActivitySubActivity.this, "报名成功！");
						btn_actjoin.setText("已报名");
						v.setClickable(false);
						v.setBackgroundColor(getResources().getColor(
								R.color.btn_disable));
					}
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
	
	OnClickListener clicklistener = new OnClickListener() {
		@Override
		public void onClick(final View v) {
			
		}
	};

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
