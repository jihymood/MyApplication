package com.example.zhangqian.myapplication.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.example.zhangqian.myapplication.R;
import com.example.zhangqian.myapplication.entity.User;
import com.example.zhangqian.myapplication.entity.WeatherResult;
import com.example.zhangqian.myapplication.entity.WeatherRoot;
import com.google.gson.Gson;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static android.media.CamcorderProfile.get;
import static com.alibaba.fastjson.JSON.parseObject;
import static java.util.logging.Logger.getLogger;

public class MainActivity extends AppCompatActivity {

    private TextView textView;
    private ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.textView);
        image = (ImageView) findViewById(R.id.image);

        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, SecondActivity.class));
            }
        });
        getHttpRequest();
//        getHttpRequest1();

//        postAsynHttp();

//        postAsynFile();

//        downAsynFile();//方法有错误

//        createStr();

//        createJson();

    }

    /**
     * Str转成json
     */
    public void createStr() {
        String jsonString = "{\"name\":\"ZhangSan\",\"age\":24,\"address\":\"南京\"}";
        Gson gson=new Gson();
        User user=gson.fromJson(jsonString,User.class);
        Log.e("User",user.getAddress()+user.getName()+user.getAge());
        Logger logger=Logger.getLogger("createStr");
        logger.setLevel(Level.ALL);
        logger.info(user.getAddress()+user.getName()+user.getAge());
        logger.info(jsonString);
    }


    /**
     * Str转成json
     */
    public void createJson() {
//        User user = new User("南京", 12, "黄继海");
//        Gson gson = new Gson();
//        String str = gson.toJson(user);
//        Log.e("str", str);

    }


    /**
     * 读取文件
     */
    public void postAsynFile() {
        final MediaType mediaType = MediaType.parse("text/x-markdown; charset=utf-8");
        String path = Environment.getExternalStorageDirectory().getPath();

        String url="";
        OkHttpClient okHttpClient = new OkHttpClient();
        File file = new File(path);


    }

    /**
     * get请求并Gson解析
     */
    public void getHttpRequest1() {
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
                String jsonStr = response.body().string();
                Gson gson = new Gson();
                WeatherRoot root = gson.fromJson(jsonStr, WeatherRoot.class);
                List<WeatherResult> list = root.getResult();

                Log.e("MainActivity", jsonStr + "\n" + list.size());
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
                List<WeatherResult> list = root.getResult();
                String week = list.get(0).getWeek();
                Log.e("MainActivity", week);
//                Toast.makeText(MainActivity.this,jsonStr,Toast.LENGTH_LONG).show();
            }
        });
    }

    /**
     * post请求
     */
    public void postAsynHttp() {
//        String url="https://api.douban.com/v2/book/search";
//        String url="http://www.kuaidi100.com/query";
        OkHttpClient okHttpClient=new OkHttpClient();
        String url = "http://www.kuaidi100.com/query?";
        RequestBody requestBody=new FormBody.Builder()
                .add("type","zhongtong")
                .add("postid","533067226270")
                .build();
        Request request=new Request.Builder().url(url).build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("MainActivity", e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String jsonStr=response.body().toString();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Log.e("MainActivity", jsonStr);
                        textView.setText(jsonStr);
                    }
                });
            }
        });

    }

    /**
     * 下载图片
     */
    public void downAsynFile() {
        String url = "http://img5.duitang.com/uploads/item/201406/17/20140617140412_JKnZU.thumb.700_0.jpeg";
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder().url(url).build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("MainActivity", e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                InputStream in = response.body().byteStream();
                FileOutputStream out = null;
                try {
                    out = new FileOutputStream(new File("sdcard/hhh.jpg"));
                    byte[] buf = new byte[1024];
                    int len = 0;
                    while ((len = in.read(buf)) != -1) {
                        out.write(buf, 0, len);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    in.close();
                    out.close();
                }
            }
        });
    }


}
