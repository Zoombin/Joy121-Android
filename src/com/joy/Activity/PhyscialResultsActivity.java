package com.joy.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.joy.R;
import com.joy.Utils.Constants;
import com.nostra13.universalimageloader.core.ImageLoader;

public class PhyscialResultsActivity extends BaseActivity implements OnClickListener {
	private RelativeLayout layout_title;
	private ImageView iv_ret;
	private TextView tv_title;
	private ImageView imageResults;
	final Intent intent = new Intent();
	@Override
	protected View ceateView(LayoutInflater inflater, Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.activity_physical_results, null);
		setContentView(v);
		initView();
		setData();
		return v;
	}
	// 初始化
		private void initView() {
			layout_title = (RelativeLayout) findViewById(R.id.layout_title);
			uiAdapter.setMargin(layout_title, LayoutParams.MATCH_PARENT, Constants.SubTitleHeight, 0, 0,
					0, 0);
			iv_ret = (ImageView) findViewById(R.id.iv_ret);
			iv_ret.setOnClickListener(this);

			tv_title = (TextView) findViewById(R.id.tv_title);
			uiAdapter.setTextSize(tv_title, Constants.TitleSize);
			imageResults=(ImageView)findViewById(R.id.imageResults);
		}
		private void setData(){
			ImageLoader.getInstance().displayImage("http://a.hiphotos.baidu.com/image/w%3D310/sign=ad259ac9b0fb43161a1f7c7b10a44642/e850352ac65c1038c30b34c0b0119313b07e89bf.jpg", imageResults);
		}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.iv_ret:
			finish();
			break;
		
		default:
			break;
		}
	}
}
