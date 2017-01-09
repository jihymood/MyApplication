package com.xpro.customview.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by huangjh on 2017/1/4 0004 16:24
 */

public class PercentView extends View {

    private Paint paint;
    private RectF rectf;

    public PercentView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        paint = new Paint();
        paint.setAntiAlias(true);
        rectf = new RectF();
    }

    public PercentView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public PercentView(Context context) {
        super(context);
        init();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int withMode = MeasureSpec.getMode(widthMeasureSpec);
        int withSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        switch (withMode) {
            case MeasureSpec.EXACTLY:

                break;
            case MeasureSpec.AT_MOST:
                break;
            case MeasureSpec.UNSPECIFIED:
                break;
        }
        Log.d("PercentView", "withMode:" + withMode);
        Log.d("PercentView", "withSize:" + withSize);
        Log.d("PercentView", "heightMode:" + heightMode);
        Log.d("PercentView", "heightSize:" + heightSize);

        Log.d("onMeasure", "onMeasure");
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.d("onDraw", "onDraw");
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        int width = getWidth();
        int height = getHeight();
        Log.d("PercentView", width + " " + height);
        float radius = width / 4; //半径
        /**
         * cx：圆心的x坐标。
         * cy：圆心的y坐标。
         * radius：圆的半径。
         * paint：绘制时所使用的画笔。
         */
        canvas.drawCircle(width / 2, width / 2, radius, paint);


        /**
         * left：是矩形距离左边的X轴
         * top：是矩形距离上边的Y轴
         * right：是矩形距离右边的X轴
         * bottom：是矩形距离下边的Y轴
         */
        paint.setColor(Color.GREEN);
        rectf.set(width / 2 - radius, width / 2 - radius, width / 2 + radius, width / 2 + radius);//用于定义的圆弧的形状和大小的界限
        /**
         * startAngle 从右边水平半径线开始，顺时针起点角度
         * sweepAngle 弧度
         */
        canvas.drawArc(rectf, 360, 120, true, paint);  //根据进度画圆弧
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        Log.d("onLayout", "onLayout");
    }
}
