package com.example.gson;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.IOException;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    @Bind(R.id.text)
    TextView text;
    @Bind(R.id.btn)
    Button btn;
    Book book;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        getRequestData();
    }

    public void getRequestData() {
//        String url = "https://api.douban.com/v2/book/1220562";
        String url = "https://api.douban.com/v2/book/1220562";
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request
                .Builder()
                .url(url)
                .build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e(TAG, "onFailure:" + e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String json = response.body().string();

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Log.e(TAG, "onResponse:" + json);
                        Gson gson = new Gson();
                        book = gson.fromJson(json, Book.class);
                        text.setText(book.getSummary());
                    }
                });

            }
        });
    }

    /**
     * get请求并且fastjson解析
     */
    public void getHttpRequest() {
        String url = "http://api.k780.com:88/?app=weather" +
                ".future&weaid=1&&appkey=10003&sign=b59bc3ef6191eb9f747dd4e83c99f2a4&format=json";
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder().url(url).build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("MainActivity", e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String jsonStr = response.body().string();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(MainActivity.this, jsonStr, Toast.LENGTH_LONG).show();
                        Log.e("MainActivity", jsonStr);
                    }
                });

//                WeatherRoot root = JSON.parseObject(jsonStr, WeatherRoot.class);
//                List<WeatherBody> list = root.getResult();
//                String week = list.get(0).getWeek();
//                Log.e("MainActivity", week);
//                Toast.makeText(MainActivity.this,jsonStr,Toast.LENGTH_LONG).show();
            }
        });
    }

    @OnClick({R.id.text, R.id.btn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.text:
                break;
            case R.id.btn:
                Toast.makeText(this, "hhdd", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, ShowDetailsActivity.class);
                Bundle bundle = new Bundle();
                bundle.putParcelable("book", book);
                Log.e(TAG, "book:" + book.toString());
                intent.putExtra("bundle", bundle);
                startActivity(intent);
                break;
        }
    }
}
