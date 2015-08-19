
package gejw.android.quickandroid;

import android.app.Application;
import android.content.Context;

public class QApplication extends Application{
	
	@Override
	public void onCreate() {
		super.onCreate();
		mContext = getApplicationContext();
	}
	/**
	 * 全局的上下文.
	 */
	private static Context mContext;

	/**
	 * 获取Context.
	 * 
	 * @return
	 */
	public static Context getContext() {
		return mContext;
	}
}
