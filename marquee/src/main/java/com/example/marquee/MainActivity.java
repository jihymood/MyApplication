package com.example.marquee;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.LinearInterpolator;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * 跑马灯效果
 */
public class MainActivity extends AppCompatActivity {

    private HorizontalScrollView horizontalScrollView;
    private LinearLayout linearLayout;
    private List<String> list;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        horizontalScrollView = (HorizontalScrollView) findViewById(R.id.horizontal);
        linearLayout = (LinearLayout) findViewById(R.id.linearLayout);

        init();
    }

    /**
     *
     */
    public void init() {
        list = new ArrayList<>();
        list.add("我是大傻逼");

        float scrollWidth = 0f;
        for (int i = 0; i < list.size(); i++) {
            TextView textView = new TextView(this);
            textView.setText(list.get(i));
            textView.setPadding(5, 5, 5, 5);

            int spec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
            textView.measure(spec, spec);
            scrollWidth += textView.getMeasuredWidth();
            Log.e("MainActivity", "scrollWidth:" + scrollWidth);
            linearLayout.addView(textView);
        }
        startScroll(scrollWidth);
    }

    /**
     * 开始滚动
     *
     * @param scrollWidth
     */
    public void startScroll(float scrollWidth) {
        if (scrollWidth != 0) {
            final long duration = (long) (scrollWidth * 20);//动画时长
            //属性动画位移，从屏幕右边向左移动到屏幕外scrollWidth长度的距离
            ObjectAnimator anim = ObjectAnimator.ofFloat(linearLayout, "translationX", getScreenWidth(), -scrollWidth);
            anim.setDuration(duration);
            anim.setInterpolator(new LinearInterpolator());
            anim.setRepeatMode(ValueAnimator.RESTART);//无限重复
            anim.setRepeatCount(ValueAnimator.INFINITE);
            anim.start();
        }
    }

    /**
     * 获得屏幕宽度
     */
    public int getScreenWidth() {
        WindowManager manager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        Display display = manager.getDefaultDisplay();
        display.getMetrics(dm);
        int widthPixels = dm.widthPixels;
        int heightPixels=dm.heightPixels;
        Log.e("MainActivity", "widthPixels:" + widthPixels+"/heightPixels："+heightPixels);
        return widthPixels;
    }


}
