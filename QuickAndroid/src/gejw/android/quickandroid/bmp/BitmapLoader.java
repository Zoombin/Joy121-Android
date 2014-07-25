package gejw.android.quickandroid.bmp;

import gejw.android.quickandroid.R;
import gejw.android.quickandroid.ui.adapter.UIAdapter;
import gejw.android.quickandroid.widget.QProgressDialog;

import java.io.IOException;
import java.lang.ref.SoftReference;
import java.util.HashMap;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;

/**
 * 
 * @author Robin
 * @date 2013-05-22
 */
public class BitmapLoader {
	// 内存缓存,SoftReference实现自动回收
	private static HashMap<String, SoftReference<Bitmap>> imageCache = new HashMap<String, SoftReference<Bitmap>>();

	/**
	 * 自动判断从内存还是从资源获取
	 * 
	 * @param resID
	 * @return
	 */
	public static Bitmap loadBitmap(Context context, int resID) {
		String str_resID = Integer.toString(resID);
		Bitmap bm = null;
		if (imageCache.containsKey(str_resID)) {// 从内存中获取
			SoftReference<Bitmap> reference = imageCache.get(str_resID);
			bm = reference.get();
		}
		if (null == bm) {// 通过id获取
			bm = BitmapFactory.decodeResource(context.getResources(), resID);
			if (null != bm) {
				imageCache.put(str_resID, new SoftReference<Bitmap>(bm)); // 保存到内存
			}
		}
		return bm;
	}

	/**
	 * 自动判断从内存还是从assets获取
	 * 
	 * @param assetPath
	 *            路径
	 * @return
	 */
	public static Bitmap loadBitmap(Context context, String assetsPath) {
		Bitmap bm = null;
		if (imageCache.containsKey(assetsPath)) {// 从内存中获取
			SoftReference<Bitmap> reference = imageCache.get(assetsPath);
			bm = reference.get();
		}
		if (null == bm) {// 通过id获取
			try {
				bm = BitmapFactory.decodeStream(context.getAssets().open(
						assetsPath));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (null != bm) {
				imageCache.put(assetsPath, new SoftReference<Bitmap>(bm)); // 保存到内存
			}
		}
		return bm;
	}

	/**
	 * 自动判断从内存还是从路径获取
	 * 
	 * @param path
	 *            全路径
	 * @return
	 */
	public static Bitmap loadBitmap(String path) {
		Bitmap bm = null;
		if (imageCache.containsKey(path)) {// 从内存中获取
			SoftReference<Bitmap> reference = imageCache.get(path);
			bm = reference.get();
		}
		if (null == bm) {// 通过id获取
			try {
				bm = BitmapFactory.decodeFile(path);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (null != bm) {
				imageCache.put(path, new SoftReference<Bitmap>(bm)); // 保存到内存
			}
		}
		return bm;
	}

	/**
	 * 自动判断从内存还是从路径获取
	 * 
	 * @param path
	 *            全路径
	 * @return
	 */
	public static Bitmap loadBitmap(String keyname, Bitmap bmp) {
		Bitmap bm = null;
		if (imageCache.containsKey(keyname)) {// 从内存中获取
			SoftReference<Bitmap> reference = imageCache.get(keyname);
			bm = reference.get();
		}
		if (null == bm) {// 通过id获取
			bm = bmp;
			imageCache.put(keyname, new SoftReference<Bitmap>(bm)); // 保存到内存

		}
		return bm;
	}

	/**
	 * @param keyname
	 */
	public static void removeBitmap(String keyname) {
		if (imageCache.containsKey(keyname)) {// 从内存中获取
			imageCache.remove(keyname);
		}
	}
	
	/**
	 * @param keyname
	 */
	public static void Clean() {
		imageCache.clear();
	}

	
	
	/**
	 * @param url
	 * @param imageView
	 */
	public static void LoadImage(String url, ImageView imageView) {
		ImageLoader.getInstance().displayImage(url, imageView,
				new SimpleImageLoadingListener());
	}

	/**
	 * 加载网络图片
	 * 
	 * @param url
	 * @param imageView
	 * @param uiAdapter
	 * @param width
	 * @param height
	 */
	public static void LoadImage(String url, ImageView imageView,
			final UIAdapter uiAdapter, final int width) {
		ImageLoader.getInstance().displayImage(url, imageView,
				new SimpleImageLoadingListener() {

					@Override
					public void onLoadingComplete(String imageUri, View view,
							Bitmap loadedImage) {
						super.onLoadingComplete(imageUri, view, loadedImage);
						if (view != null && loadedImage != null && width != 0) {

							uiAdapter.setMargin(
									view,
									width,
									uiAdapter.CalcHeight(width,
											loadedImage.getWidth(),
											loadedImage.getHeight()), 0, 0, 0,
									0);
						}
					}

				});
	}

	/**
	 * 加载网络图片
	 * 
	 * @param url
	 * @param imageView
	 * @param uiAdapter
	 * @param width
	 * @param height
	 */
	public static void LoadImage(String url, ImageView imageView,
			final UIAdapter uiAdapter, final int width, final int left,
			final int top, final int right, final int bottom) {
		ImageLoader.getInstance().displayImage(url, imageView,
				new SimpleImageLoadingListener() {

					@Override
					public void onLoadingComplete(String imageUri, View view,
							Bitmap loadedImage) {
						super.onLoadingComplete(imageUri, view, loadedImage);
						if (view != null && loadedImage != null && width != 0) {

							uiAdapter.setMargin(
									view,
									width,
									uiAdapter.CalcHeight(width,
											loadedImage.getWidth(),
											loadedImage.getHeight()), left,
									top, right, bottom);
						}
					}

				});
	}

	/**
	 * 带对话框的图片加载
	 * 
	 * @param url
	 * @param imageView
	 * @param uiAdapter
	 * @param width
	 * @param height
	 */
	public static void LoadImageWithDialog(final Context context, String url,
			ImageView imageView, final UIAdapter uiAdapter, final int width,
			final int height) {
		ImageLoader.getInstance().displayImage(url, imageView,
				new SimpleImageLoadingListener() {
					QProgressDialog dialog;

					@Override
					public void onLoadingComplete(String imageUri, View view,
							Bitmap loadedImage) {
						super.onLoadingComplete(imageUri, view, loadedImage);
						try {
							dialog.dismiss();
						} catch (Exception e) {
							// TODO: handle exception
						}
						if (view != null && loadedImage != null) {
							uiAdapter.setMargin(
									view,
									width,
									uiAdapter.CalcHeight(width,
											loadedImage.getWidth(),
											loadedImage.getHeight()), 0, 0, 0,
									0);
						}
					}

					@Override
					public void onLoadingStarted(String imageUri, View view) {
						// TODO Auto-generated method stub
						super.onLoadingStarted(imageUri, view);
						try {
							dialog = new QProgressDialog(context,
									R.style.Dialog).setMessage("正在加载...");
							dialog.show();
						} catch (Exception e) {
							// TODO: handle exception
						}

					}

					@Override
					public void onLoadingFailed(String imageUri, View view,
							FailReason failReason) {
						// TODO Auto-generated method stub
						super.onLoadingFailed(imageUri, view, failReason);
						try {
							dialog.dismiss();
						} catch (Exception e) {
							// TODO: handle exception
						}
					}
				});

	}
}
