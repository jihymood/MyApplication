package com.xpro.xutils;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import org.xutils.common.Callback;
import org.xutils.image.ImageOptions;
import org.xutils.x;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FirstActivity extends AppCompatActivity {

    @Bind(R.id.imageView)
    ImageView imageView;
    @Bind(R.id.download)
    Button download;
    @Bind(R.id.upload)
    Button upload;
    @Bind(R.id.jiazai)
    Button jiazai;

    private ImageOptions options;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        ButterKnife.bind(this);

        options = new ImageOptions.Builder()
                //设置加载过程中的图片
                .setLoadingDrawableId(R.mipmap.ic_launcher)
                //设置加载失败后的图片
                .setFailureDrawableId(R.mipmap.ic_launcher)
                //设置使用缓存
                .setUseMemCache(true)
                //设置显示圆形图片
                .setCircular(true)
                //设置支持gif
                .setIgnoreGif(false)
                .build();


    }


    public void getImage() {
        String url = "http://img5.duitang.com/uploads/item/201406/17/20140617140412_JKnZU.thumb.700_0.jpeg";
//        x.image().bind(imageView, url, options,);
        x.image().bind(imageView, url, options, new Callback.CommonCallback<Drawable>() {
            @Override
            public void onSuccess(Drawable result) {
                Log.d("FirstActivity", "加载成功");
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

    public void downLoad() {
        String url = "http://img5.duitang.com/uploads/item/201406/17/20140617140412_JKnZU.thumb.700_0.jpeg";
        String filepath = Environment.getExternalStorageDirectory().getAbsolutePath();
//        x.http().get

    }


    @OnClick({R.id.download, R.id.upload})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.download:
                break;
            case R.id.upload:
                break;
        }
    }

    @OnClick(R.id.jiazai)
    public void onClick() {
        getImage();
    }
}
