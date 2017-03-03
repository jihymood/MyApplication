package com.example.volley;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    private ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        image = (ImageView) findViewById(R.id.image);

//        stringRequest();
//        jsonObjectRequest();
//        imageRequest();
//        imageLoder();
//        imageLoader1();
        imageLoader2();
    }

    public void stringRequest() {
        RequestQueue mQueue = Volley.newRequestQueue(this);
        String url = "https://api.douban.com/v2/user/abei";
        StringRequest stringRequest = new StringRequest(url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        Log.e("MainActivity", s);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.e("MainActivity", "volleyError" + volleyError.toString());
            }
        });
        mQueue.add(stringRequest);
    }

    public void jsonObjectRequest() {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        String url = "http://api.k780.com:88/?app=weather" +
                ".future&weaid=1&&appkey=10003&sign=b59bc3ef6191eb9f747dd4e83c99f2a4&format=json";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(url,
                null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                Log.e("MainActivity", "jsonObject:" + jsonObject);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.e("MainActivity", "volleyError:" + volleyError);
            }
        });
        requestQueue.add(jsonObjectRequest);
    }

    public void jsonObjectRequest1() {
        String url = "http://api.k780.com:88/?app=weather" +
                ".future&weaid=1&&appkey=10003&sign=b59bc3ef6191eb9f747dd4e83c99f2a4&format=json";
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(url,
                null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                Log.e("MainActivity", "jsonObject:" + jsonObject);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.e("MainActivity", "volleyError:" + volleyError);
            }
        });
        requestQueue.add(jsonObjectRequest);
    }


    public void imageRequest() {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        String url = "http://img5.duitang.com/uploads/item/201406/17/20140617140412_JKnZU.thumb.700_0.jpeg";
        ImageRequest imageRequest = new ImageRequest(url,
                new Response.Listener<Bitmap>() {
                    @Override
                    public void onResponse(Bitmap bitmap) {
                        image.setImageBitmap(bitmap);
                    }
                }, 0, 0, Bitmap.Config.RGB_565, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.e("MainActivity", "volleyError:" + volleyError);
            }
        });
        requestQueue.add(imageRequest);
    }


    public void imageLoder() {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        String url = "http://img5.duitang.com/uploads/item/201406/17/20140617140412_JKnZU.thumb.700_0.jpeg";
        ImageLoader imageLoader = new ImageLoader(requestQueue,
                new ImageLoader.ImageCache() {
                    @Override
                    public Bitmap getBitmap(String s) {
                        Log.e("MainActivity", s);
                        return null;
                    }

                    @Override
                    public void putBitmap(String s, Bitmap bitmap) {
                        Log.e("MainActivity", s + "/" + bitmap);
                    }
                });
        ImageLoader.ImageListener listener = ImageLoader.getImageListener(image,
                R.mipmap.ic_launcher, R.mipmap.ic_launcher);
        imageLoader.get(url, listener, 200, 200);
    }

    public void imageLoader1() {
        String url = "http://img5.duitang.com/uploads/item/201406/17/20140617140412_JKnZU.thumb.700_0.jpeg";
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        ImageLoader imageLoader = new ImageLoader(requestQueue, new BitmapCache());
        ImageLoader.ImageListener listener = ImageLoader.getImageListener(image,
                R.mipmap.ic_launcher, R.mipmap.ic_launcher);
        imageLoader.get(url, listener, 200, 200);
    }

    public void imageLoader2() {
        String url = "http://img5.duitang.com/uploads/item/201406/17/20140617140412_JKnZU.thumb.700_0.jpeg";
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        ImageLoader imageLoader = new ImageLoader(requestQueue, new ImageLruCache());
        ImageLoader.ImageListener listener = ImageLoader.getImageListener(
                image, R.mipmap.ic_launcher, R.mipmap.ic_launcher);
        imageLoader.get(url, listener, 0, 0);
    }

    public void imageLoader3() {
        String url = "http://img5.duitang.com/uploads/item/201406/17/20140617140412_JKnZU.thumb.700_0.jpeg";
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        ImageLoader imageLoader = new ImageLoader(requestQueue, new ImageLruCache());
        ImageLoader.ImageListener imageListener = ImageLoader.getImageListener(image,
                R.mipmap.ic_launcher, R.mipmap.ic_launcher);
        imageLoader.get(url, imageListener, 0, 0);
    }

}
