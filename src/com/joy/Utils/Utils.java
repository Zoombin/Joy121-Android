package com.joy.Utils;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;

import android.content.Context;
import android.telephony.TelephonyManager;

/**
 * 工具类
 * @author daiye
 *
 */
public class Utils {

	/**
	 * 获取设备号
	 * @param context
	 * @return
	 */
	public static String getDeviceImei(Context context) {
		String imei = ((TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE))
				.getDeviceId();
		if (imei == null) {
			return "";
		} else {
			return imei;
		}
	}
	
    /**
     * 设置常用的设置项
     * @return
     */
    public static DisplayImageOptions getImageOptions() {
        DisplayImageOptions options = new DisplayImageOptions.Builder()  
        .cacheInMemory(true)//设置下载的图片是否缓存在内存中  
        .cacheOnDisk(true)//设置下载的图片是否缓存在SD卡中  
        .imageScaleType(ImageScaleType.IN_SAMPLE_INT)//设置图片以如何的编码方式显示  
        .build();//构建完成
        return options;
    }
}
