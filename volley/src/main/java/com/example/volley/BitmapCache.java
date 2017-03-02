package com.example.volley;

import android.graphics.Bitmap;
import android.util.Log;
import android.util.LruCache;

import com.android.volley.toolbox.ImageLoader;

/**
 * Created by huangjh on 2017/3/1 0001 14:38
 * Email：huangjihy@163.com
 */
public class BitmapCache implements ImageLoader.ImageCache {

    private LruCache<String, Bitmap> lruCache;

    public BitmapCache() {
//        int maxSize=10*1024*1024;
        int maxSize = (int) (Runtime.getRuntime().maxMemory() / 8);
        lruCache = new LruCache<String, Bitmap>(maxSize) {
            @Override
            protected int sizeOf(String key, Bitmap value) {
                Log.e("MainActivity", value.getRowBytes() + "/" + value.getHeight());
                return value.getRowBytes() * value.getHeight();
            }
        };
    }

    @Override
    public Bitmap getBitmap(String s) {
        return lruCache.get(s);
    }

    @Override
    public void putBitmap(String s, Bitmap bitmap) {
        lruCache.put(s, bitmap);
    }

}
