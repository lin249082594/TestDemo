package com.linxf.lintestdemo.lifecycle;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.OnLifecycleEvent;
import android.util.Log;

/**
 * 类说明
 * Created by Linxf on 2019/5/27.
 */

public class TestLifeCycle implements LifecycleObserver {

    private static final String TAG = "test";

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    public void onCreate() {
        Log.e(TAG, "onCreate: ");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    public void onStart() {
        Log.e(TAG, "onStart: ");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public void onResume() {
        Log.e(TAG, "onResume: ");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    public void onPause() {
        Log.e(TAG, "onPause: ");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    public void onStop() {
        Log.e(TAG, "onStop: ");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public void onDestroy() {
        Log.e(TAG, "onDestroy: ");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_ANY)
    public void onAny() {
        Log.e(TAG, "onAny: ");
    }

}
