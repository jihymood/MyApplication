package com.xpro.customview.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.Button;

import com.xpro.customview.utils.ScreenUtil;

/**
 * Created by huangjh on 2017/2/27 0027 10:27
 * Email：huangjihy@163.com
 * 继承最简单的自定义控件
 */
public class ExtendButton extends Button implements Runnable{
    private Context mContext;
    private Paint paint;
    private int radiu;

    public ExtendButton(Context context) {
        super(context);
    }

    public ExtendButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        init();

    }

    public ExtendButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    public void init() {
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStrokeWidth(10);
        paint.setColor(Color.LTGRAY);
        paint.setStyle(Paint.Style.STROKE);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int width = ScreenUtil.getScreenWidth(mContext);
        int height = ScreenUtil.getScreenHeight(mContext);
        canvas.drawCircle(150, 50, radiu, paint);

    }

    @Override
    public void run() {
        while (true) {
            try {
                if (radiu <= 200) {
                    radiu+=10;

//                    invalidate();
                    postInvalidate();
                }else{
                    radiu=0;
                }
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
