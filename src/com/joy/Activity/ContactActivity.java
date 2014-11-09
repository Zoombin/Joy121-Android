package com.joy.Activity;


/**
 * 公司通讯录
 * @author daiye
 *
 */


import gejw.android.quickandroid.widget.Toast;

import java.util.List;

import com.joy.R;
import com.joy.Utils.Constants;
import com.joy.Widget.ContactAdapter;
import com.joy.json.JsonCommon;
import com.joy.json.JsonCommon.OnOperationListener;
import com.joy.json.model.CompAppSet;
import com.joy.json.model.ContactEntity;
import com.joy.json.model.ContactListEntity;
import com.joy.json.operation.OperationBuilder;
import com.joy.json.operation.impl.ContactOp;
import com.umeng.analytics.MobclickAgent;

import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.inputmethod.EditorInfo;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.TextView.OnEditorActionListener;

public class ContactActivity extends BaseActivity implements OnClickListener {


	private RelativeLayout layout_title;
	private TextView tv_ret;
	private TextView tv_title;
	private EditText tv_search;
	private ListView list_contacts;
	private ContactAdapter adapter;
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
		View v = inflater.inflate(R.layout.activity_contact, null);
		resources = getResources();
		setContentView(v);
		initView();
		initData("");
		return v;
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
	
	private void initView() {
		layout_title = (RelativeLayout) findViewById(R.id.layout_title);
		uiAdapter.setMargin(layout_title, LayoutParams.MATCH_PARENT, Constants.TitleHeight, 0, 0,
				0, 0);

		tv_ret = (TextView) findViewById(R.id.tv_ret);
		uiAdapter.setTextSize(tv_ret, Constants.TitleRetSize);
		uiAdapter.setMargin(tv_ret, LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT, 20, 0, 0, 0);
		tv_ret.setOnClickListener(this);

		tv_title = (TextView) findViewById(R.id.tv_title);
		uiAdapter.setTextSize(tv_title, Constants.TitleSize);
		tv_title.setText(R.string.contact);//通讯录
		
		tv_search = (EditText) findViewById(R.id.tv_search);
		uiAdapter.setMargin(tv_search, LayoutParams.MATCH_PARENT,
				LayoutParams.WRAP_CONTENT, 10, 10, 10, 0);
		
		list_contacts = (ListView) findViewById(R.id.list_contacts);
		uiAdapter.setMargin(list_contacts, LayoutParams.MATCH_PARENT,
				LayoutParams.WRAP_CONTENT, 10, 10, 10, 0);
		adapter = new ContactAdapter(self, self);
		list_contacts.setAdapter(adapter);
		
		 
		tv_search.setOnEditorActionListener(new OnEditorActionListener() {
			@Override
			public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
				// TODO Auto-generated method stub
				 if ((actionId == 0 || actionId == 3) && event != null) {
					 //list_contacts.clearDisappearingChildren();
					 adapter = new ContactAdapter(self, self);
					 list_contacts.setAdapter(adapter);
					 initData(((EditText) v).getText().toString());
				 }
				return false;
			}
        });

	}
	
	private void initData(String qvalue) {
		OperationBuilder builder = new OperationBuilder().append(new ContactOp(qvalue),
				null);
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
				ContactListEntity entity = (ContactListEntity) resList.get(0);
				List<ContactEntity> contactEntityList = entity.getRetobj();
				if (contactEntityList == null) {
					Toast.show(self, "无此联系人！");
					finish();
					return;
				}
				int position = 0;
				for (ContactEntity entity1 : contactEntityList) {
					if (position%2 == 1) {
						entity1.setBackground(Color.WHITE);
					} else {
						entity1.setBackground(Color.GREEN);
					}
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
