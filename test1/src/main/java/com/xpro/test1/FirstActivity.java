package com.xpro.test1;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class
FirstActivity extends AppCompatActivity {

    @Bind(R.id.wifiImageView)
    ImageView wifiImageView;
    @Bind(R.id.jiantouImageView)
    ImageView jiantouImageView;
    @Bind(R.id.bluetoothImageView)
    ImageView bluetoothImageView;
    @Bind(R.id.button)
    Button button;

    private AnimationDrawable wifi,jiantou,buletooth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        ButterKnife.bind(this);

        /**
         * wifi动画
         */
        wifi = (AnimationDrawable) wifiImageView.getBackground();
        wifi.start();

        /**
         * 箭头动画
         */
        jiantou = (AnimationDrawable) jiantouImageView.getBackground();
        jiantou.start();

        /**
         * 蓝牙动画
         */
        buletooth = (AnimationDrawable) bluetoothImageView.getBackground();
//        buletooth.start();

    }


    @OnClick({R.id.button})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button:
                wifiImageView.setVisibility(View.GONE);
                bluetoothImageView.setVisibility(View.VISIBLE);
                buletooth.start();
                break;
        }
    }
}
