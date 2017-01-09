package com.example.jackson;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.example.jackson.entity.Book;
import com.example.jackson.entity.Person;
import com.example.jackson.entity.WeatherBody;
import com.example.jackson.entity.WeatherRoot;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static android.R.attr.id;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    @Bind(R.id.button)
    Button button;
    @Bind(R.id.button1)
    Button button1;
    @Bind(R.id.listView)
    ListView listView;
    @Bind(R.id.activity_main)
    LinearLayout activityMain;
    @Bind(R.id.text1)
    TextView text1;
    @Bind(R.id.text2)
    TextView text2;

    private SimpleAdapter adapter;
    private MyAdapter adapter1;
    private List<Map<String, String>> list;
    private List<Person> list1;
    private int[] icons = {R.mipmap.ic_launcher, R.mipmap.bus, R.mipmap.gzcbus, R.mipmap.trailerfree, R.mipmap.trailerbusy};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(MainActivity.this);

//        getData();
//        adapter = new SimpleAdapter(this, list,R.layout.item,
//                new String[]{"name","tel"},new int[]{R.id.nameText,R.id.tel});
//        listView.setAdapter(adapter);

        getData1();
        adapter1 = new MyAdapter(this, list1);
        listView.setAdapter(adapter1);

//        getHttpData();
//        getHttpRequest();
    }

    public List<Map<String, String>> getData() {
        list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Map<String, String> map = new HashMap<>();
            map.put("name", "傻逼" + i);
            map.put("tel", "121212121212");
            list.add(map);
        }
        return list;
    }

    public void getData1() {
        list1 = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            list1.add(new Person(icons[i],"等等"+i, "23" + i));
        }
    }

    public void getHttpData() {
        String url = "https://api.douban.com/v2/book/1220562";
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder().url(url).build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e(TAG, e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String result = response.body().string();
                Book book = JSON.parseObject(result, Book.class);
                Log.e(TAG, book.getSummary() + "\n" + book.getTags().get(3).getName());

            }
        });
    }


    /**
     * get请求并且fastjson解析
     */
    public void getHttpRequest() {
        String url = "http://api.k780.com:88/?app=weather.future&weaid=1&&appkey=10003&sign=b59bc3ef6191eb9f747dd4e83c99f2a4&format=json";
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

                WeatherRoot root = JSON.parseObject(jsonStr, WeatherRoot.class);
                List<WeatherBody> list = root.getResult();
                String week = list.get(0).getWeek();
                Log.e("MainActivity", week);
//                Toast.makeText(MainActivity.this,jsonStr,Toast.LENGTH_LONG).show();
            }
        });
    }

    @OnClick({R.id.button, R.id.button1, R.id.activity_main})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button:
                Toast.makeText(this, "我是按钮", Toast.LENGTH_SHORT).show();
                break;
            case R.id.button1:
                break;
            case R.id.activity_main:
                break;
        }
    }
}