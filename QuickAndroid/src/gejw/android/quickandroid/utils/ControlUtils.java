package gejw.android.quickandroid.utils;

import gejw.android.quickandroid.QApplication;
import android.graphics.Color;
import android.os.Handler;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.EditText;

/**
 * 控件类
 * 
 * @author gejw(gejw0623@yeah.net)
 * 
 */
public class ControlUtils {
	/**
	 * 设置错误提示框
	 * 
	 * @param et
	 * @param strID
	 */
	public static void setError(EditText et, int strID) {
		et.setFocusable(true);
		et.setFocusableInTouchMode(true);
		et.requestFocus();
		et.requestFocusFromTouch();
		String estring = QApplication.getContext().getResources()
				.getString(strID);
		ForegroundColorSpan fgcspan = new ForegroundColorSpan(Color.GRAY);
		SpannableStringBuilder ssbuilder = new SpannableStringBuilder(estring);
		ssbuilder.setSpan(fgcspan, 0, estring.length(), 0);
		et.setError(ssbuilder);
		// et.setError(Html.fromHtml(String.format("<font>121---%s</font>",
		// getString(strID))));
	}

	/**
	 * 滚动条定位
	 * 
	 * @param scroll
	 * @param inner
	 */
	public static void scrollToBottom(final View scroll, final View inner) {

		Handler mHandler = new Handler();

		mHandler.post(new Runnable() {
			public void run() {
				if (scroll == null || inner == null) {
					return;
				}

				int offset = inner.getMeasuredHeight() - scroll.getHeight();
				if (offset < 0) {
					offset = 0;
				}

				scroll.scrollTo(0, offset);
			}
		});
	}

}
