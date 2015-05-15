package com.joy.Activity;

import gejw.android.quickandroid.widget.Toast;

import java.util.List;

import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.joy.JoyApplication;
import com.joy.R;
import com.joy.Utils.Constants;
import com.joy.Widget.PostAdapter;
import com.joy.json.JsonCommon;
import com.joy.json.JsonCommon.OnOperationListener;
import com.joy.json.model.CompAppSet;
import com.joy.json.model.PostDetailEntity;
import com.joy.json.model.PostEntity;
import com.joy.json.operation.OperationBuilder;
import com.joy.json.operation.impl.PostOp;
import com.umeng.analytics.MobclickAgent;

/**
 * 公告列表
 * @author daiye
 *
 */
public class PostActivity extends BaseActivity implements OnClickListener {
	
	private RelativeLayout layout_title;
	private ImageView iv_ret;
	private TextView tv_title;
	private ListView list_post;
	private PostAdapter adapter;
	private View line_useful;
	private View line_expired;
	
	private LinearLayout layout_menu;
	private LinearLayout layout_useful;
	private TextView tv_useful;
	private View line_useful;
	private LinearLayout layout_expired;
	private View line_expired;
	private TextView tv_expired;
	private Resources resources;
	CompAppSet appSet;
	int color;
	private String isSelect;//表示当时选中的时候哪个选项 onResume的时候刷新数据
	
	
	/*@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_post);
		resources = this.getResources();
		initView();
		initData("1");
	}*/
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		isSelect = "1";
	}

	
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
		View v = inflater.inflate(R.layout.activity_post, null);
		setContentView(v);
		resources =getResources();
		initView();
	//	initData("1",true);//在这里加回重复加载公告信息
		return v;
	}
	public void onResume() {
		super.onResume();
		MobclickAgent.onResume(this);
		//刷新数据
		adapter.removeAll();
		adapter.notifyDataSetChanged();
		initData(isSelect,true);
	}
	public void reLoad() {
		// 刷新数据
		adapter.removeAll();
		initData(isSelect,true);
	}
	
	private void initView() {
		layout_title = (RelativeLayout) findViewById(R.id.layout_title);
		uiAdapter.setMargin(layout_title, LayoutParams.MATCH_PARENT, Constants.SubTitleHeight, 0, 0,
				0, 0);

		iv_ret = (ImageView) findViewById(R.id.iv_ret);
		iv_ret.setOnClickListener(this);

		tv_title = (TextView) findViewById(R.id.tv_title);
		uiAdapter.setTextSize(tv_title, Constants.TitleSize);
		tv_title.setText(R.string.row_notice);//公告
		
		
		list_post = (ListView) findViewById(R.id.list_post);
		uiAdapter.setMargin(list_post, LayoutParams.MATCH_PARENT,
				LayoutParams.WRAP_CONTENT, 0, 0, 0, 0);
		adapter = new PostAdapter(self, self);
		list_post.setAdapter(adapter);	
		
		layout_menu = (LinearLayout) findViewById(R.id.layout_menu);
		
		layout_useful = (LinearLayout) findViewById(R.id.layout_useful);
		layout_useful.setOnClickListener(this);
		
		tv_useful = (TextView) findViewById(R.id.tv_useful);
		line_useful = (View) findViewById(R.id.line_useful);
		
		layout_expired = (LinearLayout) findViewById(R.id.layout_expired);
		layout_expired.setOnClickListener(this);
		
		tv_expired = (TextView) findViewById(R.id.tv_expired);
		line_expired = (View) findViewById(R.id.line_expired);
<<<<<<< HEAD
	
=======
<<<<<<< HEAD
	
=======
		list_post = (ListView) findViewById(R.id.list_post);
		uiAdapter.setMargin(list_post, LayoutParams.MATCH_PARENT,
				LayoutParams.WRAP_CONTENT, 0, 0, 0, 0);
		adapter = new PostAdapter(self, self);
		list_post.setAdapter(adapter);	
		
>>>>>>> 34acdd014449076be19c67258f14caec9568e50d
>>>>>>> bfc1dd98ae18f2af35989a913a31b9cdaa58e226
		defaultColor();
	}
	
	private void defaultColor()
	{
<<<<<<< HEAD
		//layout_useful.setBackgroundColor(color);
		//layout_expired.setBackgroundColor(getResources().getColor(R.color.btn_disable));
=======
<<<<<<< HEAD
		//layout_useful.setBackgroundColor(color);
		//layout_expired.setBackgroundColor(getResources().getColor(R.color.btn_disable));
=======
		/*layout_useful.setBackgroundColor(color);
		layout_expired.setBackgroundColor(getResources().getColor(R.color.btn_disable));
		tv_useful.setTextColor(resources.getColor(R.color.WHITE));
		tv_expired.setTextColor(resources.getColor(R.color.WHITE));*/
		
>>>>>>> 34acdd014449076be19c67258f14caec9568e50d
>>>>>>> bfc1dd98ae18f2af35989a913a31b9cdaa58e226
		tv_useful.setTextColor(color);
		line_useful.setBackgroundColor(color);
		tv_expired.setTextColor(resources.getColor(R.color.gray));
	}


	private void initData(final String isexpired,boolean pro){
		PostEntity post = new PostEntity();
		post.isexpired = isexpired;
		OperationBuilder builder = new OperationBuilder().append(new PostOp(),
				post);
		OnOperationListener listener = new OnOperationListener() {
			@Override
			public void onOperationFinished(List<Object> resList) {
				if (self.isFinishing()) {
					return;
				}
				if (resList == null) {
					Toast.show(self, getResources().getString(R.string.timeout));
					return;
				}
				PostEntity entity = (PostEntity) resList.get(0);
				List<PostDetailEntity> postlist = entity.getRetobj();
				if (postlist == null || postlist.size() == 0) {
					Toast.show(self, resources.getString(R.string.nopostinfo));
					//finish();
					return;
				}
				for (PostDetailEntity entity1 : postlist) {
					entity1.setIsexpired(isexpired);
					adapter.addItem(entity1);
				}
				adapter.notifyDataSetChanged();
				//adapter.setData(postlist);
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

	private void showMenu(int layout) {
		switch (layout) {
		case R.id.layout_useful:
<<<<<<< HEAD
			//layout_useful.setBackgroundColor(color);
			//layout_expired.setBackgroundColor(getResources().getColor(R.color.btn_disable));
=======
<<<<<<< HEAD
			//layout_useful.setBackgroundColor(color);
			//layout_expired.setBackgroundColor(getResources().getColor(R.color.btn_disable));
=======
			/*layout_useful.setBackgroundColor(color);
			layout_expired.setBackgroundColor(getResources().getColor(R.color.btn_disable));
			tv_useful.setTextColor(resources.getColor(R.color.WHITE));
			tv_expired.setTextColor(resources.getColor(R.color.WHITE));*/
>>>>>>> 34acdd014449076be19c67258f14caec9568e50d
>>>>>>> bfc1dd98ae18f2af35989a913a31b9cdaa58e226
			tv_useful.setTextColor(color);
			line_useful.setBackgroundColor(color);
			tv_expired.setTextColor(resources.getColor(R.color.gray));
			line_expired.setBackgroundColor(resources.getColor(R.color.WHITE));
			adapter.removeAll();
			adapter.notifyDataSetChanged();
<<<<<<< HEAD
			isSelect = "1";
			initData("1",true);
			break;
		case R.id.layout_expired:
			//layout_expired.setBackgroundColor(color);
			//layout_useful.setBackgroundColor(getResources().getColor(R.color.btn_disable));
=======
<<<<<<< HEAD
			isSelect = "1";
			initData("1",true);
			break;
		case R.id.layout_expired:
			//layout_expired.setBackgroundColor(color);
			//layout_useful.setBackgroundColor(getResources().getColor(R.color.btn_disable));
=======
			initData("1");
			break;
		case R.id.layout_expired:
			/*layout_expired.setBackgroundColor(color);
			layout_useful.setBackgroundColor(getResources().getColor(R.color.btn_disable));
			tv_expired.setTextColor(resources.getColor(R.color.WHITE));
			tv_useful.setTextColor(resources.getColor(R.color.WHITE));*/
>>>>>>> 34acdd014449076be19c67258f14caec9568e50d
>>>>>>> bfc1dd98ae18f2af35989a913a31b9cdaa58e226
			tv_useful.setTextColor(resources.getColor(R.color.gray));
			line_useful.setBackgroundColor(resources.getColor(R.color.WHITE));
			tv_expired.setTextColor(color);
			line_expired.setBackgroundColor(color);
			adapter.removeAll();
			adapter.notifyDataSetChanged();
<<<<<<< HEAD
			isSelect = "2";
			initData("2",true);
=======
<<<<<<< HEAD
			isSelect = "2";
			initData("2",true);
=======
			initData("2");
>>>>>>> 34acdd014449076be19c67258f14caec9568e50d
>>>>>>> bfc1dd98ae18f2af35989a913a31b9cdaa58e226
			break;
		default:
			break;
		}
	}	
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.layout_useful:
		case R.id.layout_expired:
			showMenu(v.getId());
			break;
		case R.id.iv_ret:
			finish();
			break;
		default:
			break;
		}
	}
	public void onPause() {
		super.onPause();
		MobclickAgent.onPause(this);
	}
	
}
