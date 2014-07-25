package gejw.android.quickandroid.widget.ZoomImg;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.widget.ImageView;

/**
 * @author gejw
 * 
 */
public class ZoomView extends ImageView {
	public enum TouchType {
		_NONE, _DRAG, _ZOOM;
	}

	public ZoomView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init(context);
	}

	public ZoomView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}

	public ZoomView(Context context) {
		super(context);
		init(context);
	}

	private Context mContext;
	// 触摸类型
	private TouchType touchType = TouchType._NONE;
	// 地图的bmp
	private Bitmap mapBitmap;
	// 当前的缩放比例
	private float scale = 1.0f;
	// 最小的缩放比例
	private float minScale = 1.0f;
	// 最大的缩放比例
	private float maxScale = 1.0f;
	// 控件宽度
	private float viewWidth;
	// 控件高度
	private float viewHeight;
	// 地图bmp宽度
	private float mapWidth;
	// 地图bmp高度
	private float mapHeight;
	// 地图bmp的左上角坐标x
	private float mapLeft;
	// 地图bmp的左上角坐标y
	private float mapTop;
	// 标记是否进行过初始化计算
	private boolean isCalc = false;

	private ScaleGestureDetector mScaleDetector;

	private void init(Context context) {
		this.mContext = context;
		mScaleDetector = new ScaleGestureDetector(context,
				new ScaleGestureListener());
		setFocusable(true);
		initPaint();
	}

	private Paint bmpPaint;

	private void initPaint() {
		bmpPaint = new Paint();
		bmpPaint.setAntiAlias(true);
	}

	/**
	 * 初始化计算
	 */
	private void createCalc() {
		if (mapBitmap == null)
			return;
		if (isCalc)
			return;
		ZoomUtils.DefaultWidth = mapBitmap.getWidth();
		ZoomUtils.DefaultHeight = mapBitmap.getHeight();
		viewWidth = getWidth();
		viewHeight = getHeight();

		float ScaleW = viewWidth / ZoomUtils.DefaultWidth;
		float ScaleH = viewHeight / ZoomUtils.DefaultHeight;
		minScale = ScaleW;
		maxScale = minScale * 4.0f;

		mapWidth = viewWidth;
		mapHeight = ZoomUtils.CalcNewHeight(mapWidth);
		mapLeft = 0;
		mapTop = ((viewHeight - mapHeight) / 2.0f);
		isCalc = true;
	}

	public void Draw(Canvas canvas) {
		if (mapBitmap == null)
			return;
		canvas.drawBitmap(mapBitmap, null, new Rect((int) mapLeft,
				(int) mapTop, (int) (mapLeft + mapWidth),
				(int) (mapTop + mapHeight)), bmpPaint);
	}

	private ZPoint last = new ZPoint();

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		super.onTouchEvent(event);
		float curX = event.getX();
		float curY = event.getY();
		mScaleDetector.onTouchEvent(event);
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			touchType = TouchType._DRAG;
			last.set(curX, curY);
			break;
		case MotionEvent.ACTION_MOVE:
			if (touchType == TouchType._DRAG) {
				// 移动
				mapLeft += (curX - last.getX());
				mapTop += (curY - last.getY());

				checkRounds();
				last.set(curX, curY);
			}

			break;
		case MotionEvent.ACTION_UP:
			touchType = TouchType._NONE;
			last.set(mapLeft, mapTop);

			break;
		default:
			break;
		}

		postInvalidate();
		return true;
	}

	private void checkRounds() {
		// 检测上边界
		if (mapTop > 0)
			mapTop = 0;
		// 检测下边界
		if (mapTop + mapHeight < viewHeight)
			mapTop = (viewHeight - mapHeight);
		// 检测左边界
		if (mapLeft > 0)
			mapLeft = 0;
		// 检测右边界
		if (mapLeft + mapWidth < viewWidth)
			mapLeft = (viewWidth - mapWidth);

		if (mapHeight < viewHeight)
			mapTop = ((viewHeight - mapHeight) / 2.0f);
	}

	public class ScaleGestureListener extends
			ScaleGestureDetector.SimpleOnScaleGestureListener {

		ZPoint last = new ZPoint();
		float lastScale = 0;

		@Override
		public boolean onScale(ScaleGestureDetector detector) {
			scale *= detector.getScaleFactor();
			if (scale > maxScale) {
				scale = maxScale;
			} else if (scale < minScale) {
				scale = minScale;
			}

			mapWidth = ZoomUtils.DefaultWidth * scale;
			mapHeight = ZoomUtils.DefaultHeight * scale;
			if (lastScale == scale)
				return true;

			mapLeft = (detector.getFocusX() - detector.getScaleFactor()
					* (detector.getFocusX() - mapLeft));
			mapTop = (detector.getFocusY() - detector.getScaleFactor()
					* (detector.getFocusY() - mapTop));
			checkRounds();

			lastScale = scale;
			return true;
		}

		@Override
		public boolean onScaleBegin(ScaleGestureDetector detector) {
			touchType = TouchType._ZOOM;
			last.set(detector.getFocusX(), detector.getFocusY());
			return true;
		}
	}

	@Override
	protected void onDraw(Canvas canvas) {
		createCalc();
		Draw(canvas);
	}

	public Bitmap getMapBitmap() {
		return mapBitmap;
	}

	public void setMapBitmap(Bitmap mapBitmap,boolean isRecycle) {
		if (this.mapBitmap != null&&isRecycle) {
			mapBitmap.recycle();
			mapBitmap = null;
		}
		this.mapBitmap = mapBitmap;
		isCalc = false;
		postInvalidate();
	}

}
