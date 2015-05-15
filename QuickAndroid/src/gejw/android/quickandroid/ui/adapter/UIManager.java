package gejw.android.quickandroid.ui.adapter;

import gejw.android.quickandroid.bmp.BmpUtils;
import gejw.android.quickandroid.ui.adapter.UIEntityList.UIEntity;
import gejw.android.quickandroid.utils.ResName2ID;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;

import javax.security.auth.Destroyable;

import android.app.Activity;
import android.graphics.Point;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

public class UIManager {

	private HashMap<String, UIEntity> uiHashMap = new LinkedHashMap<String, UIEntityList.UIEntity>();
	private UIAdapter uiAdapter;
	private OnClickListener clickListener;
	private Activity mActivity;

	public UIManager(Activity activity) {
		mActivity = activity;
		uiAdapter = UIAdapter.getInstance(activity);
	}

	/**
	 * 初始化json文件
	 * 
	 * @param jsonFileName
	 */
	public void init(String jsonFileName) {
		try {
			readJson("UIJson/" + jsonFileName
					+ (jsonFileName.lastIndexOf(".json") != -1 ? "" : ".json"));
		} catch (Exception e) {
			Toast.makeText(mActivity, e.getMessage(), Toast.LENGTH_LONG).show();
		}

	}

	/**
	 * 
	 * 解析整个json文件 生成hashmap
	 * 
	 * @param jsonFileName
	 */
	private void readJson(String jsonFileName) {
		// uiHashMap.clear();
		try {
			// Return an AssetManager instance for your application's package
			InputStream is = mActivity.getAssets().open(jsonFileName);
			int size = is.available();

			// Read the entire asset into a local byte buffer.
			byte[] buffer = new byte[size];
			is.read(buffer);
			is.close();
			String json = new String(buffer, "UTF-8");
			UIEntityList entityList = new Gson().fromJson(json,
					UIEntityList.class);

			for (UIEntity entity : entityList.getList()) {
				uiHashMap.put(entity.getViewKey(), entity);
			}
		} catch (IOException e) {
			// Should never happen!
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	/**
	 * 解析根布局
	 * 
	 * @param activity
	 */
	public void matchingUIAllFromJson(Activity activity) {
		Iterator iterator = uiHashMap.keySet().iterator();
		while (iterator.hasNext()) {
			UIEntity entity = uiHashMap.get(iterator.next());
			matchingUIFromJson(activity, entity.getViewKey());
		}
	}

	/**
	 * 解析子布局
	 * 
	 * @param v
	 */
	public void matchingUIAllFromJson(View v) {
		Iterator iterator = uiHashMap.keySet().iterator();
		while (iterator.hasNext()) {
			UIEntity entity = uiHashMap.get(iterator.next());
			matchingUIFromJson(v, entity.getViewKey());
		}
	}

	public void matchingUIFromJson(Activity activity, String jsonKey) {
		View v = activity.findViewById(ResName2ID.getID(activity, jsonKey));
		if (v == null)
			return;
		UIEntity entity = uiHashMap.get(jsonKey);
		matchingUI(v, entity);
	}

	public void matchingUIFromJson(View parentView, String jsonKey) {
		View v = parentView.findViewById(ResName2ID.getID(mActivity, jsonKey));
		if (v == null)
			return;
		UIEntity entity = uiHashMap.get(jsonKey);
		matchingUI(v, entity);
	}

	private void matchingUI(View v, UIEntity entity) {

		float imgWidth = 0;
		float imgHeight = 0;
		int imgid = ResName2ID.getDrawableID(mActivity, entity.getImg());
		if (imgid != 0) {
			Point point = BmpUtils.getBmpSizeFromRes(mActivity.getResources(),
					imgid);
			imgWidth = point.x;
			imgHeight = point.y;
		}
		float viewWidth = 0;
		float viewHeight = 0;
		if (entity.getViewHeight() == -3)
			viewHeight = uiAdapter.CalcHeight(entity.getViewWidth(), imgWidth,
					imgHeight);
		else
			viewHeight = entity.getViewHeight();

		if (entity.getViewWidth() == -3)
			viewWidth = uiAdapter.CalcWidth(entity.getViewHeight(), imgWidth,
					imgHeight);
		else
			viewWidth = entity.getViewWidth();

		if (entity.getViewWeight() >= 0) {
			// 带有比重
			uiAdapter.setMargin(v, viewWidth, viewHeight, entity.getMarginL(),
					entity.getMarginT(), entity.getMarginR(),
					entity.getMarginB(), entity.getViewWeight());
		} else {
			// 不带比重
			uiAdapter.setMargin(v, viewWidth, viewHeight, entity.getMarginL(),
					entity.getMarginT(), entity.getMarginR(),
					entity.getMarginB());
		}

		int paddingL = entity.getPaddingL();
		int paddingT = entity.getPaddingT();
		int paddingR = entity.getPaddingR();
		int paddingB = entity.getPaddingB();
		if (paddingL == paddingB && paddingB == paddingR
				&& paddingR == paddingT && paddingT == paddingL) {
			paddingT = paddingB = uiAdapter.CalcReverseHeight(uiAdapter
					.CalcWidth(paddingL));
		}
		// 设置内间距
		uiAdapter.setPadding(v, entity.getPaddingL(), entity.getPaddingT(),
				entity.getPaddingR(), entity.getPaddingB());

		if ((v instanceof TextView) || (v instanceof Button)
				|| (v instanceof CheckBox)) {
			uiAdapter.setTextSize(v, entity.getTextSize());
		}
		if (entity.isClick() && clickListener != null) {
			v.setOnClickListener(clickListener);
		}
	}

	public void setClickListener(OnClickListener clickListener) {
		this.clickListener = clickListener;
	}
	
	public void Destroy() {
		uiHashMap.clear();
	}
}
