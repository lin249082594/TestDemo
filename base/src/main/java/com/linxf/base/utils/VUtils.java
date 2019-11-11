package com.linxf.base.utils;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Point;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;

import q.rorbin.badgeview.Badge;
import q.rorbin.badgeview.QBadgeView;

/**
 * 类说明
 * Created by Linxf on 2019/4/4.
 */

public class VUtils {
    /**
     * dip 转px
     * @param dp
     * @param context
     * @return
     */
    public static int dip2px(Context context,int dp)
    {
        float density = context.getResources().getDisplayMetrics().density;
        return (int) (dp*density+0.5);
    }

    /**
     * px转换dip
     * @param px
     * @param context
     * @return
     */
    public static int px2dip(Context context,int px) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (px / scale + 0.5f);
    }

    /**
     * px转换sp
     * @param pxValue
     * @param context
     * @return
     */
    public static int px2sp(Context context,int pxValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / fontScale + 0.5f);
    }

    /**
     * sp转换px
     * @param spValue
     * @param context
     * @return
     */
    public static int sp2px(Context context,int spValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

    public static int sp2pt(Context context,int spValue) {
        final float fontScale = context.getResources().getDisplayMetrics().density;
        return (int) (spValue * fontScale );
    }

    public static int dp_sp(Context context,int spValue) {
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        int mScreenWidth = dm.widthPixels;
        int mScreenHeight = dm.heightPixels;

        //以分辨率为720*1080准，计算宽高比值
        float ratioWidth = (float) mScreenWidth / 720;
        float ratioHeight = (float) mScreenHeight / 1080;
        float ratioMetrics = Math.min(ratioWidth, ratioHeight);
        int textSize = Math.round(spValue * ratioMetrics);

        return textSize;
    }


    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    public static int getScreenWidth(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Point p = new Point();
        wm.getDefaultDisplay().getSize(p);
        return p.x;
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    public static int getScreenHeight(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Point p = new Point();
        wm.getDefaultDisplay().getSize(p);
        return p.y;
    }

    /**
     * 设置
     * @param context
     * @param view
     * @param num
     * @param listener
     */
    public static Badge setViewNumBadge(Context context, View view, int num, Badge.OnDragStateChangedListener listener, int padding){

        Badge weimaiBd =  new QBadgeView(context);
        weimaiBd.bindTarget(view);
        weimaiBd.setBadgeText("");
        if(num >0){
            weimaiBd.setBadgeNumber(num);
        } else {
            weimaiBd.setBadgeText("");
        }

        weimaiBd.setBadgeGravity( Gravity.END | Gravity.TOP);
        weimaiBd.setGravityOffset(0,0,true);
        weimaiBd.setBadgePadding(padding,true);
        weimaiBd.setOnDragStateChangedListener(listener);
        return weimaiBd;
    }

    public static Badge setViewTextBadge(Context context, View view, String text, Badge.OnDragStateChangedListener listener, int padding){

        Badge weimaiBd =  new QBadgeView(context);
        weimaiBd.bindTarget(view);
        weimaiBd.setBadgeText(text);

        weimaiBd.setBadgeGravity( Gravity.END | Gravity.TOP);
        weimaiBd.setGravityOffset(0,0,true);
        weimaiBd.setBadgePadding(padding,true);
        weimaiBd.setOnDragStateChangedListener(listener);
        return weimaiBd;
    }

}
