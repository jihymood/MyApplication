package com.example.service.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.service.ActivityUtils;

import org.json.JSONObject;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by huangjh on 2017/3/30 0030 20:01
 */
public class ReliefTaskStateService extends Service {

    private Timer timer;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        timer = new Timer();
        timer.schedule(new MyTimerTask(), 1000, 1000 * 10);
        return super.onStartCommand(intent, flags, startId);

    }


    @Override
    public void onDestroy() {
        super.onDestroy();
    }


    /**
     * 定时器
     */
    class MyTimerTask extends TimerTask {
        @Override
        public void run() {
            String url = "https://api.douban.com/v2/user/abei";
            RequestQueue requestQueue = Volley.newRequestQueue(ReliefTaskStateService.this);
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(url, null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject jsonObject) {
                            Log.e("MainActivity", "jsonObject:" + jsonObject);
                            Toast.makeText(ReliefTaskStateService.this, "jsonObject:" + jsonObject, Toast
                                    .LENGTH_SHORT).show();
                            ActivityUtils.finishActivity(); //结束所有的activity
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError volleyError) {

                }
            });
            requestQueue.add(jsonObjectRequest);
        }
    }
}
