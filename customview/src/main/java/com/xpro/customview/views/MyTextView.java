package com.xpro.customview.views;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

import com.xpro.customview.R;

/**
 * Created by huangjh on 2017/2/24 0024 11:13
 * Email：huangjihy@163.com
 */
public class MyTextView extends TextView {
    public MyTextView(Context context) {
        super(context);
    }

    public MyTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        context.getTheme().obtainStyledAttributes(attrs, R.styleable.MyTextView, defStyleAttr, 0);



    }
}
