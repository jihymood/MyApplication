package com.example.greedao;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private DBManager dbManager;
    private GreenDaoManager dbManager1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        dbManager = DBManager.getInstance(this);
//        dbManager1 = GreenDaoManager.getInstance(this);
//        dbManager1.getSession().g etUserDao().insert(new User(102L, "傻逼", 20));
//        dbManager1.getSession().getDogDao().insert(new Dog(2L,2,"黄色","小黑子"));


        getWeather();
        ActivityManager manager = (ActivityManager)getSystemService(Context.ACTIVITY_SERVICE);
        int heapSize = manager.getMemoryClass(); //堆大小
        Log.e("MainActivity", "heapSize:" + heapSize);

        Runtime runtime = Runtime.getRuntime();
        long l = runtime.maxMemory();//通过maxMemory()方法获取系统可为APP分配的最大内存
        long l1 = runtime.totalMemory();//totalMemory() 获取APP当前所分配的内存heap空间大小
        Log.e("MainActivity", "l:" + l/1024+"\nl1:" + l1/1024);
    }

    public void getWeather() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.k780.com:88/?app=weather.future&weaid=1&&appkey=10003&sign=b59bc3ef6191eb9f747dd4e83c99f2a4&format=json")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        BookService bookService = retrofit.create(BookService.class);
        bookService.getDetail().enqueue(new Callback<Weather>() {
            @Override
            public void onResponse(Call<Weather> call, Response<Weather> response) {
                Toast.makeText(MainActivity.this, "获取成功" + response.body().getSuccess(), Toast.LENGTH_SHORT).show();
//                Gson gson = new Gson();
//                final Weather weather = gson.fromJson(response.body().toString(), Weather.class);
//                Toast.makeText(MainActivity.this, "获取成功" + weather.getResult().get(1).getWeather(), Toast
//                        .LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Weather> call, Throwable t) {

            }
        });
    }

}
