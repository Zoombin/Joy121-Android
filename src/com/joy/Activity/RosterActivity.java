package com.joy.Activity;


/**
 * 员工激励计划排行单
 * @author ryan zhou 2014-11-3
 *
 */


import gejw.android.quickandroid.widget.Toast;

import java.text.DecimalFormat;
import java.util.List;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.text.TextPaint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.joy.R;
import com.joy.Utils.Constants;
import com.joy.Widget.RosterAdapter;
import com.joy.json.JsonCommon;
import com.joy.json.JsonCommon.OnOperationListener;
import com.joy.json.model.PerformanceEntity;
import com.joy.json.model.RosterEntity;
import com.joy.json.model.RosterListEntity;
import com.joy.json.operation.OperationBuilder;
import com.joy.json.operation.impl.RosterOp;
import com.umeng.analytics.MobclickAgent;

public class RosterActivity extends BaseActivity implements OnClickListener {

	public static final String entity = "entity";
	private PerformanceEntity performanceEntity;
	private RelativeLayout layout_title;
	private ImageView iv_ret;
	private TextView tv_title;
	private View v_line;
	private TextView tv_period;
	private TextView tv_award;
	private LinearLayout layout_ranking;
	private TextView tv_score;
	private TextView tv_ranking;
	private TextView tv_total;
	private TextView tv_percent;
	private ListView list_roster;
	private RosterAdapter adapter;
	private Resources resources;
	
	/*@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_survey);
		resources = getResources();
		initView();
		initData("1");
	}*/
	
	@Override
	protected View ceateView(LayoutInflater inflater, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View v = inflater.inflate(R.layout.activity_roster, null);
		resources = getResources();
		setContentView(v);
		initView();
		Intent intent = getIntent();
		if (intent.hasExtra(entity)) {
			performanceEntity = (PerformanceEntity) intent.getSerializableExtra(entity);
			setData(performanceEntity);
		}
		initData();
		return v;
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
	
	private void initView() {
		layout_title = (RelativeLayout) findViewById(R.id.layout_title);
		uiAdapter.setMargin(layout_title, LayoutParams.MATCH_PARENT, Constants.SubTitleHeight, 0, 0,
				0, 0);

		iv_ret = (ImageView) findViewById(R.id.iv_ret);
		iv_ret.setOnClickListener(this);

		tv_title = (TextView) findViewById(R.id.tv_title);
		uiAdapter.setTextSize(tv_title, Constants.TitleSize);
		tv_title.setText(R.string.motivationroster);//激励排行榜
		
		//横线
		v_line = (View) findViewById(R.id.v_line);
		// 期间
		tv_period= (TextView) findViewById(R.id.tv_period);
		TextPaint tp_period = tv_period.getPaint(); 
		tp_period.setFakeBoldText(true); 
		uiAdapter.setTextSize(tv_period, 18);
		uiAdapter.setPadding(tv_period, 0, 0, 0, 0);
		
		//奖励积分
		tv_award = (TextView) findViewById(R.id.tv_award);
		TextPaint tp_award = tv_award.getPaint(); 
		tp_award.setFakeBoldText(true); 
		uiAdapter.setTextSize(tv_award, 30);
		tv_award.setTextColor(Color.parseColor("#FF0000"));
		uiAdapter.setPadding(tv_award, 0, 0, 0, 0);
		
		// 排名
		layout_ranking = (LinearLayout) findViewById(R.id.layout_ranking);
		uiAdapter.setPadding(layout_ranking, 100, 0, 0, 0);
		
		tv_score = (TextView) findViewById(R.id.tv_score);
		TextPaint tp_score = tv_score.getPaint(); 
		tp_score.setFakeBoldText(true); 
		uiAdapter.setTextSize(tv_score, 20);
		//holder.tv_score.setTextColor(Color.parseColor("#FF0000"));
		uiAdapter.setPadding(tv_score, 0, 0, 0, 0);
		
		tv_ranking = (TextView) findViewById(R.id.tv_ranking);
		TextPaint tp_rankig = tv_ranking.getPaint(); 
		tp_rankig.setFakeBoldText(true); 
		uiAdapter.setTextSize(tv_ranking, 20);
		uiAdapter.setPadding(tv_ranking, 0, 0, 0, 0);
		
		tv_total = (TextView) findViewById(R.id.tv_total);
		TextPaint tp_total = tv_total.getPaint(); 
		tp_total.setFakeBoldText(true); 
		uiAdapter.setTextSize(tv_total, 20);
		uiAdapter.setPadding(tv_total, 0, 0, 0, 0);
		
		// 百分比
		tv_percent = (TextView) findViewById(R.id.tv_percent);
		TextPaint tp_percent = tv_percent.getPaint(); 
		tp_percent.setFakeBoldText(true); 
		uiAdapter.setTextSize(tv_percent, 20);
		uiAdapter.setPadding(tv_percent, 5, 0, 0, 0);
		
		list_roster = (ListView) findViewById(R.id.list_roster);
		uiAdapter.setMargin(list_roster, LayoutParams.MATCH_PARENT,
				LayoutParams.WRAP_CONTENT, 10, 10, 10, 0);
		adapter = new RosterAdapter(self, self);
		list_roster.setAdapter(adapter);
		/*list_roster.setOnScrollListener(new OnScrollListener() {
			@Override  
            public void onScrollStateChanged(AbsListView paramAbsListView, int scrollState) {
				int itemsLastIndex = adapter.getCount();    //数据集最后一项的索引  
		        int lastIndex = itemsLastIndex - 1;             //加上底部的loadMoreView项
		        if (scrollState == OnScrollListener.SCROLL_STATE_IDLE && visibleLastIndex == lastIndex && itemsLastIndex % pageSize == 0) {  
		            //如果是自动加载,可以在这里放置异步加载数据的代码
		        	//adapter = new ContactAdapter(self, self);
					//list_contacts.setAdapter(adapter);
		        	pageNum = pageNum + 1;
		        	initData(tv_search.getText().toString(), pageSize, pageNum);
		        }  
			}
			
			 @Override  
	         public void onScroll(AbsListView paramAbsListView, int firstVisibleItem
	        		 , int visibleItemCount, int totalItemCount) { 
			      visibleLastIndex = firstVisibleItem + visibleItemCount - 1;  
			 }
		});*/
		
		 
		/*tv_search.setOnEditorActionListener(new OnEditorActionListener() {
			@Override
			public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
				// TODO Auto-generated method stub
				 
				 if ((actionId == 0 || actionId == 3) && event != null) {
					 //list_contacts.clearDisappearingChildren();
					 adapter = new RosterAdapter(self, self);
					 list_roster.setAdapter(adapter);
					 pageNum = 1;
					 initData(((EditText) v).getText().toString(), pageSize, pageNum);
				 }
				return false;
			}
        });*/

	}
	
	private void initData() {
		/*RosterEntity rosterEntity1 = new RosterEntity("张飞", "上海宝山万达TOMMY专柜", "1", "95.50", "200");
		RosterEntity rosterEntity2 = new RosterEntity("关羽", "上海百联南京店TOMMY专柜", "2", "95.35", "200");
		RosterEntity rosterEntity3 = new RosterEntity("赵云", "上海东方商厦徐汇店TOMMY专柜", "3", "94.66", "200");
		RosterEntity rosterEntity4 = new RosterEntity("黄忠", "上海东方商厦淮海店TOMMY专柜", "4", "93.13", "200");
		RosterEntity rosterEntity5 = new RosterEntity("马超", "上海高岛屋百货TOMMY专柜", "5", "93.08", "200");
		RosterEntity rosterEntity6 = new RosterEntity("夏侯惇", "上海虹桥友谊TOMMY专柜", "6", "92.60", "200");
		RosterEntity rosterEntity7 = new RosterEntity("甘宁", "上海久光百货TOMMY男装专柜", "7", "92.40", "200");
		RosterEntity rosterEntity8 = new RosterEntity("陆逊", "上海西郊百联TOMMY专柜", "8", "92.08", "200");
		RosterEntity rosterEntity9 = new RosterEntity("周瑜", "上海百联南京店TOMMY专柜", "9", "90.11", "200");
		RosterEntity rosterEntity10 = new RosterEntity("吕蒙", "上海新世界TOMMY专柜", "10", "89.99", "200");
		RosterEntity rosterEntity11 = new RosterEntity("周泰", "上海梅陇镇TOMMY专柜", "11", "89.12", "200");
		RosterEntity rosterEntity12 = new RosterEntity("太史慈", "上海新世界TOMMY专柜", "12", "88.54", "200");
		RosterEntity rosterEntity13 = new RosterEntity("吕布", "上海梅陇镇TOMMY专柜", "13", "88.33", "200");
		RosterEntity rosterEntity14 = new RosterEntity("诸葛亮", "上海新世界TOMMY专柜", "14", "87.00", "0");
		RosterEntity rosterEntity15 = new RosterEntity("凌统", "上海梅陇镇TOMMY专柜", "15", "86.65", "0");
		adapter.addItem(rosterEntity1);
		adapter.addItem(rosterEntity2);
		adapter.addItem(rosterEntity3);
		adapter.addItem(rosterEntity4);
		adapter.addItem(rosterEntity5);
		adapter.addItem(rosterEntity6);
		adapter.addItem(rosterEntity7);
		adapter.addItem(rosterEntity8);
		adapter.addItem(rosterEntity9);
		adapter.addItem(rosterEntity10);
		adapter.addItem(rosterEntity11);
		adapter.addItem(rosterEntity12);
		adapter.addItem(rosterEntity13);
		adapter.addItem(rosterEntity14);
		adapter.addItem(rosterEntity15);
		adapter.notifyDataSetChanged();*/
		OperationBuilder builder = new OperationBuilder().append(new RosterOp(),
				performanceEntity.getReportCaseId());
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
				RosterListEntity entity = (RosterListEntity) resList.get(0);
				List<RosterEntity> rosterEntityList = entity.getRetobj();
				if (rosterEntityList == null || rosterEntityList.size() == 0) {
					Toast.show(self, resources.getString(R.string.nocontact));
					//finish();
					return;
				}
				for (RosterEntity entity1 : rosterEntityList) {
					adapter.addItem(entity1);
				}
				adapter.notifyDataSetChanged();
			}

			@Override
			public void onOperationError(Exception e) {
				e.printStackTrace();
			}
		};
		JsonCommon task = new JsonCommon(self, builder, listener,
				JsonCommon.PROGRESSQUERY);
		task.execute();
	}
	
	private void setData(PerformanceEntity entity) {
		//横线
		if (entity.getPeriod() != null && entity.getPeriod().length() == 4) {
			GradientDrawable shapeDrawable = (GradientDrawable) v_line.getBackground();
			shapeDrawable.setColor(Color.parseColor("#fca433"));	
		} else {
			if (entity.getPeriod().contains("Q")) {
				GradientDrawable shapeDrawable = (GradientDrawable) v_line.getBackground();
				shapeDrawable.setColor(Color.parseColor("#58d22f"));	
			} else {
				GradientDrawable shapeDrawable = (GradientDrawable) v_line.getBackground();
				shapeDrawable.setColor(Color.parseColor("#6f93f4"));
			}
		}
		tv_period.setText(entity.getReportName());
		tv_score.setText(entity.getPerformanceScore());
		tv_ranking.setText(entity.getPerformanceSeq());
		tv_total.setText(entity.getTotalNum());
		tv_award.setText(entity.getPerformancePoints());
		DecimalFormat df = new DecimalFormat(".00");
		double percent = Double.parseDouble(entity.getPerformanceSeq()) * 100 / Double.parseDouble(entity.getTotalNum());
		//String progressbarText = String.valueOf("排名前" + Math.round(percent)+"%");
		//String format = df.format(percent);
		//holder.progressbar.setText(progressbarText);
		//holder.progressbar.setMax(Integer.parseInt(entity.getTotal()));
		//holder.progressbar.setProgress(Integer.parseInt(entity.getRanking()));
		tv_percent.setText(String.valueOf(Math.round(percent)) +"%");
	}
	
	//重调接口刷新数据
	public void reLoad(){

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
