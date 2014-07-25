package gejw.android.quickandroid.bmp;

import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.graphics.Point;

public class BmpUtils {

	/**
	 * 用于读取bitmap的大小
	 * 
	 * @param res
	 * @param id
	 * @return
	 */
	public static Point getBmpSizeFromRes(Resources res, int id) {
		BitmapFactory.Options options = new BitmapFactory.Options();
		options.inJustDecodeBounds = true;
		BitmapFactory.decodeResource(res, id, options);
		return new Point(options.outWidth, options.outHeight);
	}

	/**
	 * 用于读取bitmap的大小
	 * 
	 * @param pathName
	 *            路径
	 * @return
	 */
	public static Point getBmpSizeFromPath(String pathName) {
		BitmapFactory.Options options = new BitmapFactory.Options();
		options.inJustDecodeBounds = true;
		BitmapFactory.decodeFile(pathName, options);
		return new Point(options.outWidth, options.outHeight);
	}
//	
//	public static Point getBmpSizeFromAssets(Context context,String fileName) {
//		BitmapFactory.Options options = new BitmapFactory.Options();
//		options.inJustDecodeBounds = true;
//		BitmapFactory.decodeStream(context.getAssets().open(fileName), options);
//		return new Point(options.outWidth, options.outHeight);
//	}
}
