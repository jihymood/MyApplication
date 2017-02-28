package com.xpro.customview.views;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.Button;

/**
 * Created by huangjh on 2017/2/27 0027 14:42
 * Email：huangjihy@163.com
 */
public class ExtendButton2 extends Button {
    private Paint paint;


    public ExtendButton2(Context context) {
        super(context);
    }

    public ExtendButton2(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(10);
        paint.setColor(Color.LTGRAY);
    }

    public ExtendButton2(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


}
