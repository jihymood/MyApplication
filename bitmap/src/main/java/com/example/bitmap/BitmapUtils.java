package com.example.bitmap;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;

/**
 * Created by HASEE on 2017/5/19 15:44
 */

public class BitmapUtils {

    public static Bitmap readBitmapFromFile(String filePath, int width, int height) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        Bitmap bitmap = BitmapFactory.decodeFile(filePath, options);
        int outWidth = options.outWidth;
        int outHeight = options.outHeight;
        int inSampleSize = 1;
        if (outHeight > height || outWidth > width) {
            if (outWidth > outHeight) {
                inSampleSize = Math.round(outHeight / height);
            } else {
                inSampleSize = Math.round(outWidth / width);
            }
        }
        options.inJustDecodeBounds = true;
        options.inSampleSize = inSampleSize;
        return BitmapFactory.decodeFile(filePath, options);
    }

    /**
     * 设置图片的圆角，返回设置后的BitMap
     */
    public static Bitmap toRoundCorner(Bitmap bitmap, int pixels) {
        Bitmap roundCornerBitmap = Bitmap.createBitmap(bitmap.getWidth(),
                bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(roundCornerBitmap);
        int color = 0xff424242;// int color = 0xff424242;
        Paint paint = new Paint();
        paint.setColor(color);
        // 防止锯齿
        paint.setAntiAlias(true);
        Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        RectF rectF = new RectF(rect);
        float roundPx = pixels;
        // 相当于清屏
        canvas.drawARGB(0, 0, 0, 0);
        // 先画了一个带圆角的矩形
        canvas.drawRoundRect(rectF, roundPx, roundPx, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        // 再把原来的bitmap画到现在的bitmap！！！注意这个理解
        canvas.drawBitmap(bitmap, rect, rect, paint);
        return roundCornerBitmap;
    }
}
