package com.example.bitmap;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = (ImageView) findViewById(R.id.image);
//        imageView.setImageResource(R.mipmap.ic_launcher);
//        imageView.setImageBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.tupian));
//        BitmapDrawable bitmapDrawable = new BitmapDrawable(BitmapFactory.decodeResource(getResources(), R.drawable
//                .tupian));
//        imageView.setImageDrawable(bitmapDrawable);
//        imageView.setImageDrawable(new MyDrawable());

        imageView.setImageBitmap(BitmapUtils.toRoundCorner(BitmapFactory.decodeResource(getResources(), R.drawable
                .tupian), 200));
    }
}
