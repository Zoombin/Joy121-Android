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
	private ImageView iv_ret;
	private TextView tv_title;
	private TextView tv_actname;
	private ImageView iv_actpicture;
	private TextView tv_count;
	private TextView tv_deadline;
	private TextView tv_point;
	private Button btn_actjoin;
	private TextView tv_acttimetitle;
	private TextView tv_acttime;
	private TextView tv_actlocationaddrtitle;
	private TextView tv_actlocationaddr;
	private TextView tv_contenttitle;
	private TextView tv_content;
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
		View v = inflater.inflate(R.layout.activity_activitysub, null);
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
				LayoutParams.WRAP_CONTENT, 10, 0, 0, 0);
		uiAdapter.setTextSize(tv_count, 18);

		tv_deadline = (TextView) findViewById(R.id.tv_deadline);
		uiAdapter.setMargin(tv_deadline, LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT, 10, 0, 0, 0);
		uiAdapter.setTextSize(tv_deadline, 18);
		
		tv_point = (TextView) findViewById(R.id.tv_point);
		uiAdapter.setMargin(tv_point, LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT, 10, 0, 0, 0);
		uiAdapter.setTextSize(tv_point, 18);

		btn_actjoin = (Button) findViewById(R.id.btn_actjoin);
		uiAdapter.setMargin(btn_actjoin, 150, 45, 0, 10, 10, 10);
		uiAdapter.setTextSize(btn_actjoin, 20);
		
		tv_acttimetitle = (TextView) findViewById(R.id.tv_acttimetitle);
		uiAdapter.setPadding(tv_acttimetitle, 10, 5, 0, 5);
		uiAdapter.setTextSize(tv_acttimetitle, 20);

		tv_acttime = (TextView) findViewById(R.id.tv_acttime);
		uiAdapter.setMargin(tv_acttime, LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT, 30, 5, 20, 5);
		uiAdapter.setTextSize(tv_acttime, 20);
		
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
		//Log.d("0", "@@@@@@"+entity.getContent());
		acttype = intent.getStringExtra("acttype");

		if ("1".equals(acttype)) {
			tv_title.setText(resources.getString(R.string.activitydetail));
		} else if ("2".equals(acttype)) {
			tv_title.setText(resources.getString(R.string.trainingdetail));
		}

		tv_actname.setText(entity.getActName());
		
		joinTxt = entity.getStatus(entity.getLoginName());
		btn_actjoin.setText(joinTxt);
		if (entity.getIsEnabled(entity.getLoginName())) {
			btn_actjoin.setClickable(true);
			btn_actjoin.setTag(entity);
			btn_actjoin.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(final View v) {
					// TODO Auto-generated method stub
					String msgTitle = "";
					String msgContent ="";
					if(resources.getString(R.string.noenroll).equals(joinTxt)){
						msgTitle = resources.getString(R.string.enrollconfirm);
						msgContent = resources.getString(R.string.isenrollconfirm);
						cancel = false;
					}else if(resources.getString(R.string.cancelenroll).equals(joinTxt)){
						msgTitle = resources.getString(R.string.cancelenroll);
						msgContent = resources.getString(R.string.isenrollcancel);
						cancel = true;
					}
					
					dUtil.showDialog(msgTitle, 0, msgContent, resources.getString(R.string.confirm), resources.getString(R.string.cancel)
							, new DialogButtonClickCallback() {
						@Override
						public void positiveButtonClick() {
							// TODO Auto-generated method stub
							final ActivityDetailEntity activitydetailentity = (ActivityDetailEntity) v
									.getTag();
							singUp(v, activitydetailentity,cancel);
						}
						
						@Override
						public void negativeButtonClick() {
							// TODO Auto-generated method stub
						}

					});
				}
			});
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
				btn_actjoin.setBackgroundColor(color);
			}

		} else {
			btn_actjoin.setClickable(false);
			btn_actjoin.setOnClickListener(null);
			btn_actjoin.setBackgroundColor(self.getResources()
					.getColor(R.color.btn_disable));
		}


		ImageLoader.getInstance().displayImage(
				Constants.IMGDETAIL
						+ entity.getActPicturePath(), iv_actpicture);
		
		tv_count.setText(resources.getString(R.string.applicants) + entity.getCurrentCount() + "/"
				+ entity.getLimitCount());

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		tv_deadline.setText(resources.getString(R.string.deadline) + sdf.format(new Date(Long.parseLong(entity
				.getDeadLine().substring(6, 19)))));
		
		tv_point.setText(resources.getString(R.string.actpoint) + entity.getAwardPoint() + "/"
				+ entity.getPunishPoint());
		
		String startTime = sdf.format(new Date(Long.parseLong(entity.getStartTime().substring(6, 19))));
		String endTime = sdf.format(new Date(Long.parseLong(entity.getEndTime().substring(6, 19))));
		
		int actTypeId = entity.getActTypeId();
		if (actTypeId == 1) {
			tv_acttimetitle.setText(resources.getString(R.string.acttime));
			tv_actlocationaddrtitle.setText(resources.getString(R.string.locationaddr));
			tv_contenttitle.setText(resources.getString(R.string.activity_content));
		} else if (actTypeId == 2) {
			tv_acttimetitle.setText(resources.getString(R.string.trainingtime));
			tv_actlocationaddrtitle.setText(resources.getString(R.string.trainingaddr));
			tv_contenttitle.setText(resources.getString(R.string.trainingcontent));

		}
		tv_acttime.setText(startTime + " ~ " + endTime);

		tv_actlocationaddr.setText(entity.getLocationAddr());
		//html格式显示
		tv_content.setText(Html.fromHtml(entity.getContent()));
		//text格式显示
		//tv_content.setText(entity.getContent());
	}
	
	private void singUp(View v,final ActivityDetailEntity activitydetailentity,final boolean cancel){
		final Button btn = (Button) v;
		activitydetailentity.setActionCancel(cancel);
		OperationBuilder builder = new OperationBuilder().append(
				new ActjoinOp(), activitydetailentity);
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
				ActjoinEntity entity = (ActjoinEntity) resList.get(0);
				Result result = entity.getRetobj();
				if (result == null) {
					if(cancel){
						Toast.show(self, resources.getString(R.string.cancelenroll));
					}else{
						Toast.show(self, resources.getString(R.string.enrollfailed));
					}
					return;
				} else {
					String ret = result.getResult();
					if(!"1".equals(ret)){
						if(cancel){
							Toast.show(self, resources.getString(R.string.cancelfailed));
						}else{
							Toast.show(self, resources.getString(R.string.enrollfailed));
						}
						return;
					}else{
						int count = Integer.parseInt(activitydetailentity.getCurrentCount());
						if(cancel){
							Toast.show(self, resources.getString(R.string.cancelsuccess));
							count = count - 1;
							joinTxt = resources.getString(R.string.noenroll);
						}else{
							Toast.show(self, resources.getString(R.string.enrollsuccess));
							count = count + 1;
							joinTxt = resources.getString(R.string.cancelenroll);;
						}
						tv_count.setText(resources.getString(R.string.applicants) + count + "/"
								+ activitydetailentity.getLimitCount());
						activitydetailentity.setCurrentCount(count+"");
						btn_actjoin.setText(joinTxt);
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
