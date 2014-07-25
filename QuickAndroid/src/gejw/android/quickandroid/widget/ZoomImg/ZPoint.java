package gejw.android.quickandroid.widget.ZoomImg;

/**
 * 坐标点
 * @author gejw
 *
 */
public class ZPoint {
	protected float x;
	protected float y;

	public ZPoint() {
	}

	public ZPoint(float x, float y) {
		this.x = x;
		this.y = y;
	}

	public void set(float x, float y) {
		this.x = x;
		this.y = y;
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}
}
