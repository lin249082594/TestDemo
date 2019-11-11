package com.linxf.base.application;

import android.content.Context;
import android.os.Environment;
import android.support.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.Registry;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.load.engine.cache.ExternalPreferredCacheDiskCacheFactory;
import com.bumptech.glide.load.engine.cache.InternalCacheDiskCacheFactory;
import com.bumptech.glide.load.engine.cache.LruResourceCache;
import com.bumptech.glide.module.AppGlideModule;
import com.linxf.base.utils.LogUtil;

/**
 * 类说明
 * Created by Linxf on 2019/4/22.
 */

@GlideModule
public class BaseGlideConfig extends AppGlideModule {
    @Override
    public boolean isManifestParsingEnabled() {
//        return super.isManifestParsingEnabled();
        return false;
    }

    @Override
    public void applyOptions(@NonNull Context context, @NonNull GlideBuilder builder) {
        long maxSize  = Runtime.getRuntime().maxMemory()/8;
        long maxDiskSize = 50*1024*1024;
        LogUtil.error("最大内存:" + maxSize/1024/1024);

        //        设置内存缓存大小
        builder.setMemoryCache(new LruResourceCache(maxSize));
        //        根据SD卡是否可用选择是在内部缓存还是SD卡缓存
        // TODO 犹豫需要权限才能获取大小 暂时设置50M 有其他需要再配置
        if(Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())){
            builder.setDiskCache(new ExternalPreferredCacheDiskCacheFactory(context, "HYManagerImages", maxDiskSize));
        }   else {
            builder.setDiskCache(new InternalCacheDiskCacheFactory(context, "HYManagerImages", maxDiskSize));
        }


    }

    @Override
    public void registerComponents(@NonNull Context context, @NonNull Glide glide, @NonNull Registry registry) {
        super.registerComponents(context, glide, registry);
    }
}
