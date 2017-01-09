package com.example.gson;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ShowDetailsActivity extends AppCompatActivity {

    @Bind(R.id.text)
    TextView text;
    @Bind(R.id.text1)
    TextView text1;
    @Bind(R.id.text2)
    TextView text2;
    @Bind(R.id.text3)
    TextView text3;
    @Bind(R.id.text4)
    TextView text4;
    @Bind(R.id.text5)
    TextView text5;
    @Bind(R.id.text6)
    TextView text6;
    @Bind(R.id.text7)
    TextView text7;
    @Bind(R.id.text8)
    TextView text8;
    @Bind(R.id.small)
    ImageView small;
    @Bind(R.id.middle)
    ImageView middle;
    @Bind(R.id.large)
    ImageView large;
    @Bind(R.id.btn)
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_details);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("bundle");
        Book book = bundle.getParcelable("book");
        Log.e("ShowDetailsActivity", book.toString());

        text.setText(book.getSummary());
        text1.setText(book.getOrigin_title());
        text2.setText(book.getPublisher());
        text3.setText(book.getUrl());



//        small.setImageResource(book.getImages().getSmall());


    }

    @OnClick(R.id.btn)
    public void onClick() {
    }
}
