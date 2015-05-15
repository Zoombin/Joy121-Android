package com.joy.Utils;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ProgressBar;

public class CustomProgressBar extends ProgressBar {  
    String text;  
    Paint mPaint;  
  
    public CustomProgressBar (Context context) {  
        super(context);  
        System.out.println("1");  
        initText();  
    }  
  
    public CustomProgressBar (Context context, AttributeSet attrs, int defStyle) {  
        super(context, attrs, defStyle);  
        System.out.println("2");  
        initText();  
    }  
  
    public CustomProgressBar (Context context, AttributeSet attrs) {  
        super(context, attrs);  
        System.out.println("3");  
        initText();  
    }  
  
    @Override  
    public synchronized void setProgress(int progress) {  
        super.setProgress(progress);  
    }  
  
    @Override  
    protected synchronized void onDraw(Canvas canvas) {  
        super.onDraw(canvas);  
        Rect rect = new Rect();  
        this.mPaint.getTextBounds(this.text, 0, this.text.length(), rect);
        this.mPaint.setTextSize(22);
        this.mPaint.setFakeBoldText(true);
        this.mPaint.setColor(Color.parseColor("#ffffff"));
        int x = (getWidth() / 2) - rect.centerX();  
        int y = (getHeight() / 2) - rect.centerY();  
        canvas.drawText(this.text, x, y, this.mPaint);  
    }  
  
//初始化，画笔  
    private void initText() {  
        this.mPaint = new Paint();  
        this.mPaint.setColor(Color.WHITE);  
    }  
  
//  private void setText() {  
//      setText(this.getProgress());  
//  }  
  
//设置文字内容  
    public void setText(String text) {  
        this.text = text;
    }  
}  