package com.joy.Fragment;

import gejw.android.quickandroid.QFragment;

import java.util.ArrayList;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.joy.R;

/**
 * 订单跟踪模块
 * 
 * @author daiye
 * 
 */
public class MallFragment extends QFragment {

	private LinearLayout layout_order_title;
	private TextView order_title_name;
	private LinearLayout order_input;
	private AutoCompleteTextView et_input;
	private Button bt_ordersearch;
	private TextView txt_result;
	ArrayAdapter<String> av;
	ArrayList<String> historyarr;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_mall, container, false);
		return v;
	}

}
