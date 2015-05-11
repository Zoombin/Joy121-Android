package com.joy.Activity;

import gejw.android.quickandroid.widget.Toast;

import java.util.List;

import android.app.ActionBar.LayoutParams;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.joy.R;
import com.joy.Utils.Constants;
import com.joy.Widget.RuleAdapter;
import com.joy.json.JsonCommon;
import com.joy.json.JsonCommon.OnOperationListener;
import com.joy.json.model.RuleCategorys;
import com.joy.json.model.RuleCategorys.RuleCategory;
import com.joy.json.model.RuleListEntity;
import com.joy.json.model.RuleEntity;
import com.joy.json.operation.OperationBuilder;
import com.joy.json.operation.impl.RuleCategoryOp;
import com.joy.json.operation.impl.RuleListOp;

/******
 * 通讯录详细页面
 * 
 * @author Ryan Zhou 2014-12-20
 * 
 */
public class RuleActivity extends BaseActivity implements OnClickListener {
	
	private RelativeLayout layout_title;
	private LinearLayout layout_category;
	private ImageView iv_ret;
	private TextView tv_title;
	private ListView list_rules;
	private RuleAdapter adapter;

	
	@Override
	protected View ceateView(LayoutInflater inflater, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View v = inflater.inflate(R.layout.activity_rule, null);
		setContentView(v);
		initView();
		return v;
	}
	
	

	private void initView() {
		layout_title = (RelativeLayout) findViewById(R.id.layout_title);
		uiAdapter.setMargin(layout_title, LayoutParams.MATCH_PARENT, Constants.SubTitleHeight, 0, 0,
				0, 0);

		iv_ret = (ImageView) findViewById(R.id.iv_ret);
		iv_ret.setOnClickListener(this);

		tv_title = (TextView) findViewById(R.id.tv_title);
		uiAdapter.setTextSize(tv_title, Constants.TitleSize);
		tv_title.setText(R.string.row_rule);//规章制度
		
		layout_category = (LinearLayout) findViewById(R.id.layout_category);
		
		list_rules = (ListView) findViewById(R.id.list_rules);
		
		
		adapter = new RuleAdapter(self, self);
		list_rules.setAdapter(adapter);
		
		/*String[] categoryids  = new String[]{"1", "2", "3", "4", "5", "6", "7", "8"};
		String[] categorynames  = new String[]{"要闻", "娱乐", "体育", "财经", "科技", "社会", "时尚", "文学"};
		for (int i=0; i<categorynames.length; i++) {
			TextView textView = new TextView(this);
			textView.setText(categorynames[i]);
			layout_category.addView(textView);
			uiAdapter.setMargin(textView, LayoutParams.WRAP_CONTENT,
					LayoutParams.WRAP_CONTENT, 10, 5, 10, 5);
			uiAdapter.setPadding(textView, 10, 5, 10, 5);
			textView.setTextSize(18);
			//textView.setBackground(getResources().getDrawable(R.drawable.searchtext_background));
			textView.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					TextView temp = (TextView) v;
					TextView tv_content = (TextView) findViewById(R.id.tv_content);
					tv_content.setText(temp.getText());
					for (int j=0; j<layout_category.getChildCount(); j++) {
						TextView temp1 = (TextView) layout_category.getChildAt(j);
						temp1.setBackground(null);
					}
					temp.setBackground(getResources().getDrawable(R.drawable.rulecategory_background));
				}
			});
		}*/
		getRuleCategorys();

	}
	
	/***
	 * 获取接口模块
	 */
	private void getRuleCategorys() {
		OperationBuilder builder = new OperationBuilder().append(
				new RuleCategoryOp(), null);
		OnOperationListener listener = new OnOperationListener() {
			@Override
			public void onOperationFinished(List<Object> resList) {
				if (self.isFinishing()) {
					return;
				}
				if (resList == null) {
					// Toast.show(mActivity, "连接超时");
					return;
				}
				RuleCategorys entity = (RuleCategorys) resList.get(0);
				if(1== entity.getFlag()){
					List<RuleCategory> ruleCategoryList = entity.getRetobj();
					if(ruleCategoryList != null && ruleCategoryList.size()>0){
						for (int i=0; i<ruleCategoryList.size(); i++) {
							TextView textView = new TextView(self);
							textView.setText(ruleCategoryList.get(i).getTypeName());
							textView.setTag(ruleCategoryList.get(i).getId());
							layout_category.addView(textView);
							uiAdapter.setMargin(textView, LayoutParams.WRAP_CONTENT,
									LayoutParams.WRAP_CONTENT, 10, 5, 10, 5);
							uiAdapter.setPadding(textView, 10, 5, 10, 5);
							textView.setTextSize(16);
							if (i == 0) {
								textView.setBackground(getResources().getDrawable(R.drawable.rulecategory_background));
								GradientDrawable shapeDrawable = (GradientDrawable) textView.getBackground();
								shapeDrawable.setColor(Color.LTGRAY);
								getRuleList(ruleCategoryList.get(i).getId() + "");
							}
							//textView.setBackground(getResources().getDrawable(R.drawable.searchtext_background));
							textView.setOnClickListener(new OnClickListener() {
								@Override
								public void onClick(View v) {
									TextView temp = (TextView) v;
									for (int j=0; j<layout_category.getChildCount(); j++) {
										TextView temp1 = (TextView) layout_category.getChildAt(j);
										temp1.setBackground(null);
									}
									temp.setBackground(getResources().getDrawable(R.drawable.rulecategory_background));
									GradientDrawable shapeDrawable = (GradientDrawable) temp.getBackground();
									shapeDrawable.setColor(Color.LTGRAY);
									getRuleList(v.getTag()+"");
								}
							});
						}
					}
				}else{
					Toast.show(self, entity.getMsg());
				}
			}
			@Override
			public void onOperationError(Exception e) {
				e.printStackTrace();
			}
		};
		JsonCommon task = new JsonCommon(self, builder, listener, true);
		task.execute();
	}
	
	private void getRuleList(String id) {
		OperationBuilder builder = new OperationBuilder().append(
				new RuleListOp(), id);
		OnOperationListener listener = new OnOperationListener() {
			@Override
			public void onOperationFinished(List<Object> resList) {
				if (self.isFinishing()) {
					return;
				}
				if (resList == null) {
					// Toast.show(mActivity, "连接超时");
					return;
				}
				RuleListEntity entity = (RuleListEntity) resList.get(0);
				if(1== entity.getFlag()){
					List<RuleEntity> ruleEntityList = entity.getRetobj();
					if (ruleEntityList == null || ruleEntityList.size() == 0) {
						Toast.show(self, "无内容");
						//finish();
						return;
					}
					adapter.setData(ruleEntityList);
					adapter.notifyDataSetChanged();
				}else{
					Toast.show(self, entity.getMsg());
				}
			}
			@Override
			public void onOperationError(Exception e) {
				e.printStackTrace();
			}
		};
		JsonCommon task = new JsonCommon(self, builder, listener, true);
		task.execute();
	}
	
	/*****
	 * 设置数据
	 * 
	 * @param payroll entity
	 */
	private void setData() {
		
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
	
}
