package gejw.android.quickandroid.widget.ZoomImg;

/**
 * 用于缩放计算处理
 * @author gejw
 *
 */
public class ZoomUtils {

	public static float DefaultWidth = 0;
	public static float DefaultHeight = 0;

	/**
	 * @param newHeight
	 * @return
	 */
	public static float CalcNewWidth(float newHeight) {
		return DefaultWidth * newHeight / DefaultHeight;
	}

	/**
	 * @param newWidth
	 * @return
	 */
	public static float CalcNewHeight(float newWidth) {
		return  (DefaultHeight * newWidth)/DefaultWidth;
	}
}
