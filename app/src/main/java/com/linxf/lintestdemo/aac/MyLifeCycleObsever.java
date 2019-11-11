package com.linxf.lintestdemo.aac;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.OnLifecycleEvent;
import android.util.Log;

import com.linxf.base.utils.LogUtil;

/**
 * 类说明
 * Created by Linxf on 2019/5/28.
 */

public class MyLifeCycleObsever implements LifecycleObserver {
    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    public void onCreate(){
        LogUtil.error("onCreate-----");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    public void onStart(){
       LogUtil.error( "onStart-----");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public void onResume(){
        LogUtil.error("onResume-----");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    public void onPause(){
        LogUtil.error("onPause-----");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    public void onStop(){
        LogUtil.error("onStop-----");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public void onDestroy(){
        LogUtil.error("onDestroy-----");
    }
}

