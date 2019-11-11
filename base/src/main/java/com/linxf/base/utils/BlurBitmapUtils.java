package com.linxf.base.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;
import android.widget.ImageView;

/**
 * RenderScript图片高斯模糊
 * Created by jameson on 9/2/16.
 */
public class BlurBitmapUtils {
    /**
     * 建议模糊度(在0.0到25.0之间)
     */
    private static final int BLUR_RADIUS = 20;
    private static final int SCALED_WIDTH = 100;
    private static final int SCALED_HEIGHT = 100;

    public static void blur(ImageView imageView, Bitmap bitmap) {
        blur(imageView, bitmap, BLUR_RADIUS);
    }

    public static void blur(ImageView imageView, Bitmap bitmap, int radius) {
        imageView.setImageBitmap(getBlurBitmap(imageView.getContext(), bitmap, radius));
    }

    public static Bitmap getBlurBitmap(Context context, Bitmap bitmap) {
        return getBlurBitmap(context, bitmap, BLUR_RADIUS);
    }

    /**
     * 得到模糊后的bitmap
     * thanks http://wl9739.github.io/2016/07/14/教你一分钟实现模糊效果/
     *
     * @param context
     * @param bitmap
     * @param radius
     * @return
     */
    public static Bitmap getBlurBitmap(Context context, Bitmap bitmap, int radius) {


        // 将缩小后的图片做为预渲染的图片。
        Bitmap inputBitmap = Bitmap.createScaledBitmap(bitmap, SCALED_WIDTH, SCALED_HEIGHT, false);
        // 创建一张渲染后的输出图片。
        Bitmap outputBitmap = Bitmap.createBitmap(inputBitmap);

        // 创建RenderScript内核对象
        RenderScript rs = RenderScript.create(context);
        // 创建一个模糊效果的RenderScript的工具对象
        ScriptIntrinsicBlur blurScript = ScriptIntrinsicBlur.create(rs, Element.U8_4(rs));

        // 由于RenderScript并没有使用VM来分配内存,所以需要使用Allocation类来创建和分配内存空间。
        // 创建Allocation对象的时候其实内存是空的,需要使用copyTo()将数据填充进去。
        Allocation tmpIn = Allocation.createFromBitmap(rs, inputBitmap);
        Allocation tmpOut = Allocation.createFromBitmap(rs, outputBitmap);

        // 设置渲染的模糊程度, 25f是最大模糊度
        blurScript.setRadius(radius);
        // 设置blurScript对象的输入内存
        blurScript.setInput(tmpIn);
        // 将输出数据保存到输出内存中
        blurScript.forEach(tmpOut);

        // 将数据填充到Allocation中
        tmpOut.copyTo(outputBitmap);

        return outputBitmap;
    }


    // 图片缩放比例(即模糊度)
    private static final float BITMAP_SCALE = 0.4f;

    /**
     * @param context 上下文对象
     * @param image   需要模糊的图片
     * @return 模糊处理后的Bitmap
     */
    public static Bitmap blurBitmap(Context context, Bitmap image, float blurRadius) {
        // 计算图片缩小后的长宽
        int width = Math.round(image.getWidth() * BITMAP_SCALE);
        int height = Math.round(image.getHeight() * BITMAP_SCALE);

        if(image.getConfig() == Bitmap.Config.RGB_565){
            image = RGB565toARGB888(image);
        }

        // 将缩小后的图片做为预渲染的图片
        Bitmap inputBitmap = Bitmap.createScaledBitmap(image, width, height, false);
        // 创建一张渲染后的输出图片
        Bitmap outputBitmap = Bitmap.createBitmap(inputBitmap);

        // 创建RenderScript内核对象
        RenderScript rs = RenderScript.create(context);
        // 创建一个模糊效果的RenderScript的工具对象
        ScriptIntrinsicBlur blurScript = ScriptIntrinsicBlur.create(rs, Element.U8_4(rs));

        // 由于RenderScript并没有使用VM来分配内存,所以需要使用Allocation类来创建和分配内存空间
        // 创建Allocation对象的时候其实内存是空的,需要使用copyTo()将数据填充进去
        Allocation tmpIn = Allocation.createFromBitmap(rs, inputBitmap);
        Allocation tmpOut = Allocation.createFromBitmap(rs, outputBitmap);

        // 设置渲染的模糊程度, 25f是最大模糊度
        blurScript.setRadius(blurRadius);
        // 设置blurScript对象的输入内存
        blurScript.setInput(tmpIn);
        // 将输出数据保存到输出内存中
        blurScript.forEach(tmpOut);

        // 将数据填充到Allocation中
        tmpOut.copyTo(outputBitmap);

        return outputBitmap;
    }

    public static Bitmap RGB565toARGB888(Bitmap img) {
        int numPixels = img.getWidth()* img.getHeight();
        int[] pixels = new int[numPixels];

        //Get JPEG pixels.  Each int is the color values for one pixel.
        img.getPixels(pixels, 0, img.getWidth(), 0, 0, img.getWidth(), img.getHeight());

        //Create a Bitmap of the appropriate format.
        Bitmap result = Bitmap.createBitmap(img.getWidth(), img.getHeight(), Bitmap.Config.ARGB_8888);

        //Set RGB pixels.
        result.setPixels(pixels, 0, result.getWidth(), 0, 0, result.getWidth(), result.getHeight());
        return result;
    }



}
