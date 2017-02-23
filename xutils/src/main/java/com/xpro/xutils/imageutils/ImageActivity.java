package com.xpro.xutils.imageutils;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.xpro.xutils.R;

import org.xutils.common.Callback;

import java.util.HashMap;
import java.util.Map;

public class ImageActivity extends AppCompatActivity {

    private ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);

        String url = "http://img5.duitang.com/uploads/item/201406/17/20140617140412_JKnZU.thumb.700_0.jpeg";
        String url1 = "http://api.k780.com:88/?app=idcard.get";
        Map<String, String> map = new HashMap<>();
        map.put("appkey", "10003");
        map.put("sign", "b59bc3ef6191eb9f747dd4e83c99f2a4");
        map.put("format", "json");
        map.put("idcard", "110101199001011114");

        XUtil.Get(url1, map, new Callback.CommonCallback<PersonInfoBean>() {

            @Override
            public void onSuccess(PersonInfoBean result) {

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

        image = (ImageView) findViewById(R.id.image);
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
