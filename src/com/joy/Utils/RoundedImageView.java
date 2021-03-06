package com.joy.Utils;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.widget.ImageView;

public class RoundedImageView extends ImageView {

	public RoundedImageView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	public RoundedImageView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	public RoundedImageView(Context context, AttributeSet attrs,
			int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		// TODO Auto-generated constructor stub
	}
	
	@Override  
    protected void onDraw(Canvas canvas) {  
        Path clipPath = new Path();  
        int w = this.getWidth();  
        int h = this.getHeight();  
        clipPath.addRoundRect(new RectF(0, 0, w, h), 5.0f, 5.0f, Path.Direction.CW);  
        canvas.clipPath(clipPath);  
        super.onDraw(canvas);  
    }  

}
