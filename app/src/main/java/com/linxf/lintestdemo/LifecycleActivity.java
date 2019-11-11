package com.linxf.lintestdemo;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.LifecycleRegistry;

import com.linxf.base.BaseActivity;
import com.linxf.lintestdemo.lifecycle.TestLifeCycle;

/**
 * 类说明
 * Created by Linxf on 2019/5/27.
 */

public class LifecycleActivity extends BaseActivity implements LifecycleOwner {
    private LifecycleRegistry mLifecycleRegistry;

    @Override
    public Lifecycle getLifecycle() {
        return mLifecycleRegistry;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_lifecycle;
    }

    @Override
    public void initView() {
        //创建Lifecycle对象
        mLifecycleRegistry = new LifecycleRegistry(this);
        //标记状态
        mLifecycleRegistry.markState(Lifecycle.State.CREATED);
        //添加观察者
        getLifecycle().addObserver(new TestLifeCycle());

    }

    @Override
    public void initData() {

    }

    @Override
    public void onStart() {
        super.onStart();
        //标记状态

        mLifecycleRegistry.markState(Lifecycle.State.STARTED);
    }


    @Override
    protected void onResume() {
        super.onResume();
        //标记状态
        mLifecycleRegistry.markState(Lifecycle.State.RESUMED);
    }


}
