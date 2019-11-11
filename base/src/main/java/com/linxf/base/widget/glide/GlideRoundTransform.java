package com.linxf.base.widget.glide;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.support.annotation.NonNull;

import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;
import com.bumptech.glide.util.Util;
import com.linxf.base.utils.VUtils;

import java.security.MessageDigest;

/**
 * 类说明
 * Created by Linxf on 2018/12/4.
 */

public class GlideRoundTransform extends BitmapTransformation {

    private static float leftTop = 0f;
    private static float rightTop = 0f;
    private static float rightBottom = 0f;
    private static float leftBottom = 0f;


    /**
     * 构造函数
     *
     * @param context Context
     */
    public GlideRoundTransform(Context context, int leftTop,int rightTop,int rightBottom,int leftBottom) {
        this.leftTop = VUtils.dip2px(context,leftTop);
        this.rightTop = VUtils.dip2px(context,rightTop);
        this.rightBottom = VUtils.dip2px(context,rightBottom);
        this.leftBottom = VUtils.dip2px(context,leftBottom);
    }



    @Override
    protected Bitmap transform(@NonNull BitmapPool bitmapPool, @NonNull Bitmap bitmap, int i, int i1) {
        return roundCrop(bitmapPool, bitmap);
    }


    private static Bitmap roundCrop(BitmapPool pool, Bitmap source) {

        if (source == null) return null;

        Bitmap result = pool.get(source.getWidth(), source.getHeight(), Bitmap.Config.ARGB_8888);
        if (result == null) {
            result = Bitmap.createBitmap(source.getWidth(), source.getHeight(), Bitmap.Config.ARGB_8888);
        }

        Canvas canvas = new Canvas(result);
        Paint paint = new Paint();
        paint.setShader(new BitmapShader(source, BitmapShader.TileMode.CLAMP, BitmapShader.TileMode.CLAMP));
        paint.setAntiAlias(true);
        RectF rectF = new RectF(0f, 0f, source.getWidth(), source.getHeight());
        Path path = new Path();
        float[] floatArray = new float[]{leftTop,leftTop,rightTop,rightTop,rightBottom,rightBottom,leftBottom,leftBottom};
        path.addRoundRect(rectF,floatArray,Path.Direction.CW);
        canvas.drawPath(path, paint);
        return result;
    }

    @Override
    public void updateDiskCacheKey(@NonNull MessageDigest messageDigest) {

    }
}
