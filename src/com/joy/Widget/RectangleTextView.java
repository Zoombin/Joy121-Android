package com.joy.Widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.widget.TextView;

public class RectangleTextView extends TextView {
	public RectangleTextView(Context context, AttributeSet attrs,
			int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		// TODO Auto-generated constructor stub
	}

	public RectangleTextView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	public RectangleTextView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	Paint p;
	int color;

	@Override
	protected void onDraw(Canvas canvas) {
		if (p == null)
			p = new Paint();
		p.setStyle(Paint.Style.FILL);// 充满
		p.setColor(color);
		p.setAntiAlias(true);// 设置画笔的锯齿效果
		RectF oval3 = new RectF(0, 0, getWidth(), getHeight());// 设置个新的长方形
		canvas.drawRoundRect(oval3, 5, 5, p);// 第二个参数是x半径，第三个参数是y半径
		super.onDraw(canvas);
	}

	public int getColor() {
		return color;
	}

	public void setColor(int color) {
		this.color = color;
		postInvalidate();
	}
}
