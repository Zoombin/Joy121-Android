package gejw.android.quickandroid.widget;

import gejw.android.quickandroid.R;
import gejw.android.quickandroid.ui.adapter.UIAdapter;
import android.content.Context;
import android.view.Gravity;
import android.widget.TextView;

public class Toast {
	
	public static void show(Context context,String msg){
		UIAdapter uiAdapter = UIAdapter.getInstance(context);
		TextView txt = new TextView(context);
		txt.setText(msg);
		txt.setTextColor(0xFFFFFFFF);
		txt.setBackgroundResource(R.drawable.rectangle_666666);
		uiAdapter.setTextSize(txt, 20);
		uiAdapter.setPadding(txt, 40, 10, 40, 10);
		android.widget.Toast toast = new android.widget.Toast(context);
		toast.setView(txt);
		toast.setDuration(android.widget.Toast.LENGTH_LONG);
		toast.setGravity(Gravity.CENTER, 0, 0);
		toast.show();
		
	}
}
