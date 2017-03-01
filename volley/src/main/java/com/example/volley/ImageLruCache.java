package com.example.volley;

import android.graphics.Bitmap;
import android.util.LruCache;

import com.android.volley.toolbox.ImageLoader;

/**
 * Created by huangjh on 2017/3/1 0001 17:08
 * Email：huangjihy@163.com
 *
 */
/** Lru算法的L1缓存实现类. */
public class ImageLruCache implements ImageLoader.ImageCache {

    private LruCache<String, Bitmap> mLruCache;

    public ImageLruCache() {
        this((int) Runtime.getRuntime().maxMemory() / 8);
    }

    public ImageLruCache(final int cacheSize) {
        createLruCache(cacheSize);
    }

    private void createLruCache(final int cacheSize) {
        mLruCache = new LruCache<String, Bitmap>(cacheSize) {
            @Override
            protected int sizeOf(String key, Bitmap value) {
                return value.getRowBytes() * value.getHeight();
            }
        };
    }

    @Override
    public Bitmap getBitmap(String s) {
        return null;
    }

    @Override
    public void putBitmap(String s, Bitmap bitmap) {

    }
}
