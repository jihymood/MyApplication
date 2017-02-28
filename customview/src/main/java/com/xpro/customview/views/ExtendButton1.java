package com.xpro.customview.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.Button;

/**
 * Created by huangjh on 2017/2/27 0027 13:58
 * Email：huangjihy@163.com
 */
public class ExtendButton1 extends Button implements Runnable {
    private Paint paint;
    private int radius;

    public ExtendButton1(Context context) {
        super(context);
    }

    public ExtendButton1(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(10);
        paint.setColor(Color.LTGRAY);
    }

    public ExtendButton1(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle(200, 100, radius, paint);
    }


    @Override
    public void run() {
        while (true) {
            try {
                if (radius <= 200) {
                    radius += 10;
                    postInvalidate();
                } else {
                    radius = 0;
                }
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
