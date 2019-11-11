package com.linxf.base.utils;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.linxf.base.application.GlideApp;
import com.linxf.base.application.GlideRequests;
import com.linxf.base.widget.glide.GlideRoundTransform;


/**
 * 类说明
 * Created by Linxf on 2019/4/23.
 */
//override:采样率,因为ImageView就这么大,可以压缩图片,降低内存消耗
public class ImageLoader {
    private GlideRequests glideRequests;

    public ImageLoader(Activity activity) {
        glideRequests = GlideApp.with(activity);
    }

    public ImageLoader(Activity activity,int memoryCate) {
        glideRequests = GlideApp.with(activity);

    }

    public ImageLoader(Fragment fragment) {
        glideRequests = GlideApp.with(fragment);
    }

    public ImageLoader(Context context) {
        glideRequests = GlideApp.with(context);
    }

    public ImageLoader(View view) {
        glideRequests = GlideApp.with(view);
    }
    public ImageLoader(FragmentActivity fragmentActivity) {
        glideRequests = GlideApp.with(fragmentActivity);
    }


    public  void loadImage( Object object, ImageView imageView){
        glideRequests.load(object).into(imageView);
    }

    /**
     * 内存不缓存
     * @param object
     * @param imageView
     */
    public  void loadImageSkipCache( Object object, ImageView imageView){
        glideRequests.load(object).skipMemoryCache(true).into(imageView);
    }

    /**
     * 磁盘缓存
     * @param object
     * @param imageView
     */
    public  void loadImageDiskCache( Object object, ImageView imageView){
        glideRequests.load(object).skipMemoryCache(true).diskCacheStrategy(DiskCacheStrategy.ALL).into(imageView);
    }

    /** 加载圆角
     * @param object
     * @param r
     * @param imageView
     */
    public  void loadCornerImage(Object object, int r, ImageView imageView){

        glideRequests.load(object).apply(new RequestOptions()
                .transforms(new RoundedCorners((int)(r* Resources.getSystem().getDisplayMetrics().density))
                )).into(imageView);
    }


    /**
     * 圆角撑满
     * @param object
     * @param r
     * @param imageView
     */
    public  void loadCornerCenterCropImage( Object object,int r, ImageView imageView){
        glideRequests.load(object).apply(new RequestOptions()
                .transforms(new CenterCrop(), new RoundedCorners((int)(r* Resources.getSystem().getDisplayMetrics().density))
                )).into(imageView);
    }

    public  void loadCornerCenterCropByTarget(Object object, int r,  SimpleTarget simpleTarget){
        glideRequests.load(object).apply(new RequestOptions()
                .transforms(new CenterCrop(), new RoundedCorners((int)(r* Resources.getSystem().getDisplayMetrics().density))
                )).into(simpleTarget);
    }

    public  void loadCornerPartImage(Object object,Context context, int leftTop,int rightTop,int rightBottom,int leftBottom, ImageView imageView){

        glideRequests.load(object).apply(new RequestOptions()
                .transforms(new GlideRoundTransform(context,leftTop,rightTop,rightBottom,leftBottom))
                ).into(imageView);
    }

    public  void loadCornerPartCenterCropImage(Object object,Context context, int leftTop,int rightTop,int rightBottom,int leftBottom, ImageView imageView){

        glideRequests.load(object).apply(new RequestOptions()
                .transforms(new CenterCrop(),new GlideRoundTransform(context,leftTop,rightTop,rightBottom,leftBottom))
        ).into(imageView);
    }
    /**
     * 加载圆形图片
     * @param object
     * @param context
     * @param r
     * @param imageView
     */
    public  void loadRoundCircleImage( Object object,Context context,int r, ImageView imageView){
        glideRequests.load(object).circleCrop().into(imageView);
    }
}
