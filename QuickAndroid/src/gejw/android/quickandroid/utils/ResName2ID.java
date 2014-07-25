package gejw.android.quickandroid.utils;

import android.content.Context;

/**
 * 通过资源目录下的文件名 获取对应的id
 * 
 * @author Robin
 * 
 */
public class ResName2ID {

	public static int getDrawableID(Context context, String filename) {
		return context.getResources().getIdentifier(filename, "drawable",
				context.getPackageName());
	}

	public static int getRawId(Context context, String filename) {
		return context.getResources().getIdentifier(filename, "raw",
				context.getPackageName());
	}

	public static int getLayoutID(Context context, String filename) {
		return context.getResources().getIdentifier(filename, "layout",
				context.getPackageName());
	}
	
	public static int getID(Context context, String filename) {
		return context.getResources().getIdentifier(filename, "id",
				context.getPackageName());
	}

	public static int getStringID(Context context, String filename) {
		return context.getResources().getIdentifier(filename, "string",
				context.getPackageName());
	}
	
	public static int getStyleID(Context context, String filename) {
		return context.getResources().getIdentifier(filename, "style",
				context.getPackageName());
	}
}
