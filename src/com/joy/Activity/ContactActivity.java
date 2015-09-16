package com.joy.Activity;


/**
 * 公司通讯录
 * @author ryan zhou 2014-11-3
 *
 */


import gejw.android.quickandroid.widget.Toast;

import java.util.List;

import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

import com.joy.JoyApplication;
import com.joy.R;
import com.joy.Utils.Constants;
import com.joy.Widget.ContactAdapter;
import com.joy.Widget.RelationContactAdapter;
import com.joy.json.JsonCommon;
import com.joy.json.JsonCommon.OnOperationListener;
import com.joy.json.model.CompAppSet;
import com.joy.json.model.ContactEntity;
import com.joy.json.model.ContactListEntity;
import com.joy.json.model.RelationContactEntity;
import com.joy.json.model.RelationContactListEntity;
import com.joy.json.operation.OperationBuilder;
import com.joy.json.operation.impl.ContactOp;
import com.joy.json.operation.impl.RelationContactOp;
import com.umeng.analytics.MobclickAgent;

public class ContactActivity extends BaseActivity implements OnClickListener {


	private RelativeLayout layout_title;
	private ImageView iv_ret;
	private TextView tv_title;
	private LinearLayout layout_menu;
	private LinearLayout layout_common;
	private TextView tv_common;
	private View line_common;
	private LinearLayout layout_relation;
	private TextView tv_relation;
	private View line_relation;
	private EditText tv_search;
	private ListView list_contacts;
	private ListView list_relationContacts;
	private ContactAdapter adapter;
	private RelationContactAdapter relationAdapter;
	private Resources resources;
	
	private int pageNum = 1;
	private int pageSize = 20;
	
	private int visibleLastIndex = 0;   //最后的可视项索引  
    private int visibleItemCount1 = 0;       // 当前窗口可见项总数 
    CompAppSet appSet;
	int color;
	private String isSelect;//表示当时选中的时候哪个选项 onResume的时候刷新数据
	private LinearLayout layout_list_contacts,layout_list_RelationContacts;
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
		  color = Color.parseColor("#ffa800");
			appSet = JoyApplication.getInstance().getCompAppSet();
			
			if (appSet != null) {
				try {
					color = Color.parseColor(appSet.getColor2());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		View v = inflater.inflate(R.layout.activity_contact, null);
		resources = getResources();
		setContentView(v);
		initView();
		contactInitData("", pageSize, 1);
		relationContactInitData();
		return v;
	}
	private void showMenu(int layout) {
		switch (layout) {
		case R.id.layout_common:
			tv_common.setTextColor(color);
			line_common.setBackgroundColor(color);
			tv_relation.setTextColor(resources.getColor(R.color.gray));
			line_relation.setBackgroundColor(resources.getColor(R.color.WHITE));
			break;
		case R.id.layout_relation:
			tv_common.setTextColor(resources.getColor(R.color.gray));
			line_common.setBackgroundColor(resources.getColor(R.color.WHITE));
			tv_relation.setTextColor(color);
			line_relation.setBackgroundColor(color);
			break;
		default:
			break;
		}
	}
	
	@Override
	public void onClick(View v) {
		layout_list_contacts=(LinearLayout) findViewById(R.id.layout_list_contacts);
		layout_list_RelationContacts=(LinearLayout) findViewById(R.id.layout_list_RelationContacts);
		switch (v.getId()) {
		case R.id.layout_common:
			showMenu(v.getId());
			layout_list_contacts.setVisibility(View.VISIBLE);
			layout_list_RelationContacts.setVisibility(View.GONE);
			break;
		case R.id.layout_relation:
			showMenu(v.getId());
			layout_list_contacts.setVisibility(View.GONE);
			layout_list_RelationContacts.setVisibility(View.VISIBLE);
			break;
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
		tv_title.setText(R.string.contact);//通讯录
		
        layout_menu = (LinearLayout) findViewById(R.id.layout_menu);
		
		layout_common = (LinearLayout) findViewById(R.id.layout_common);
		layout_common.setOnClickListener(this);
		
		tv_common = (TextView) findViewById(R.id.tv_common);
	    line_common = (View) findViewById(R.id.line_common);
		
		layout_relation = (LinearLayout) findViewById(R.id.layout_relation);
		layout_relation.setOnClickListener(this);
		
		tv_relation = (TextView) findViewById(R.id.tv_relation);
		line_relation = (View) findViewById(R.id.line_relation);
		
		tv_search = (EditText) findViewById(R.id.tv_search);
		uiAdapter.setMargin(tv_search, LayoutParams.MATCH_PARENT,
				LayoutParams.WRAP_CONTENT, 10, 10, 10, 0);
		
		list_contacts = (ListView) findViewById(R.id.list_contacts);
		uiAdapter.setMargin(list_contacts, LayoutParams.MATCH_PARENT,
				LayoutParams.WRAP_CONTENT, 10, 10, 10, 0);
		adapter = new ContactAdapter(self, self);
		list_contacts.setAdapter(adapter);
		list_contacts.setOnScrollListener(new OnScrollListener() {
			@Override  
            public void onScrollStateChanged(AbsListView paramAbsListView, int scrollState) {
				int itemsLastIndex = adapter.getCount();    //数据集最后一项的索引  
		        int lastIndex = itemsLastIndex - 1;             //加上底部的loadMoreView项
		        if (scrollState == OnScrollListener.SCROLL_STATE_IDLE && visibleLastIndex == lastIndex && itemsLastIndex % pageSize == 0) {  
		            //如果是自动加载,可以在这里放置异步加载数据的代码
		        	//adapter = new ContactAdapter(self, self);
					//list_contacts.setAdapter(adapter);
		        	pageNum = pageNum + 1;
		        	contactInitData(tv_search.getText().toString(), pageSize, pageNum);
		        }  
			}
			
			 @Override  
	         public void onScroll(AbsListView paramAbsListView, int firstVisibleItem
	        		 , int visibleItemCount, int totalItemCount) { 
			      visibleLastIndex = firstVisibleItem + visibleItemCount - 1;  
			 }
		});
		
		 
		tv_search.setOnEditorActionListener(new OnEditorActionListener() {
			@Override
			public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
				// TODO Auto-generated method stub
				 
				 if ((actionId == 0 || actionId == 3) && event != null) {
					 //list_contacts.clearDisappearingChildren();
					 adapter = new ContactAdapter(self, self);
					 list_contacts.setAdapter(adapter);
					 pageNum = 1;
					 contactInitData(((EditText) v).getText().toString(), pageSize, pageNum);
				 }
				return false;
			}
        });
		
		list_relationContacts=(ListView)findViewById(R.id.list_relationContacts);
		uiAdapter.setMargin(list_relationContacts, LayoutParams.MATCH_PARENT,
				LayoutParams.WRAP_CONTENT, 10, 10, 10, 0);
		relationAdapter = new RelationContactAdapter(self, self);
		list_relationContacts.setAdapter(relationAdapter);
		defaultshow();

	}
	private void defaultshow()
	{
		layout_list_contacts=(LinearLayout) findViewById(R.id.layout_list_contacts);
		layout_list_RelationContacts=(LinearLayout) findViewById(R.id.layout_list_RelationContacts);
		tv_common.setTextColor(color);
		line_common.setBackgroundColor(color);
		tv_relation.setTextColor(resources.getColor(R.color.gray));
		layout_list_contacts.setVisibility(View.VISIBLE);
		layout_list_RelationContacts.setVisibility(View.GONE);
	}
	private void contactInitData(String qvalue, int PageSize, int pageNum) {
		OperationBuilder builder = new OperationBuilder().append(new ContactOp(qvalue, pageSize, pageNum),
				null);
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
				ContactListEntity entity = (ContactListEntity) resList.get(0);
				List<ContactEntity> contactEntityList = entity.getRetobj();
				if (contactEntityList == null || contactEntityList.size() == 0) {
					Toast.show(self, resources.getString(R.string.nocontact));
					//finish();
					return;
				}
				int position = 0;
				for (ContactEntity entity1 : contactEntityList) {
					adapter.addItem(entity1);
					position++;
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
	private void relationContactInitData(){
		
		OperationBuilder builder = new OperationBuilder().append(new RelationContactOp(),
				null);
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
				RelationContactListEntity entity = (RelationContactListEntity) resList.get(0);
				List<RelationContactEntity> relationContactEntityList = entity.getRetObj();
				if (relationContactEntityList == null || relationContactEntityList.size() == 0) {
					Toast.show(self, resources.getString(R.string.nocontact));
					//finish();
					return;
				}
				int position = 0;
				for (RelationContactEntity entity1 : relationContactEntityList) {
					relationAdapter.addItem(entity1);
					position++;
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
