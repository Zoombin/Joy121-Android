package com.joy.Activity;

import com.joy.R;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;


public class PhysicalActivity extends Activity {
	  // 设置是否展开
	  private boolean isFolded = true;
	  // 设置控件
	  private FrameLayout layout = null;
	  private ImageView imageBooking;
	  private ImageView image;
	  private Button unfoldButton = null;
	  private TextView textView = null;
	  public void onCreate(Bundle savedInstanceState){
		  super.onCreate(savedInstanceState);
		    requestWindowFeature(Window.FEATURE_NO_TITLE);
		    setContentView(R.layout.activity_physical);
		    initView();
		    imageBooking.setImageResource(R.drawable.physical_booking1 ); 
	  }
  @Override
  protected void onResume() {
    // TODO Auto-generated method stu
    super.onResume();
    isFolded = true;
  }

  // 初始化
  private void initView() {
    layout = (FrameLayout) findViewById(R.id.layout);
    image=(ImageView)findViewById(R.id.image);
    imageBooking=(ImageView)findViewById(R.id.imageBooking);
    unfoldButton = (Button) findViewById(R.id.unfoldButton);
    unfoldButton.setOnClickListener(new UnfoldClickListener());
  }

  // 按钮监听，展开一个透明的显示文本的遮挡层
  private class UnfoldClickListener implements OnClickListener {
    public void onClick(View v) {
      if (isFolded) {
        image.setBackgroundColor(Color.parseColor("#86222222"));
        unfoldButton.setText("取消遮罩");
        image.setImageResource(R.drawable.physical_booking1 ); 
        isFolded = false;
      } else {
        unfoldButton.setText("显示遮罩");
        isFolded = true;
        layout.removeView(textView);
      }
    }
  }
}