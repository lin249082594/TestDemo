package com.linxf.lintestdemo;

import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.WindowManager;


import com.linxf.base.BaseActivity;
import com.linxf.lintestdemo.adapter.MainItemAdapter;
import com.linxf.lintestdemo.entity.MainUi;

import java.util.ArrayList;
import java.util.List;

/**
 * 类说明
 * Created by Linxf on 2019/4/28.
 */

public class ToolbarSlideActivity extends BaseActivity {
    private List<MainUi> list = new ArrayList<>();
    private MainItemAdapter mainItemAdapter;
    private RecyclerView recyclerview;
    @Override
    public int getLayoutId() {
        return R.layout.activity_toolbar_slide;
    }

    @Override
    public void initView() {
        recyclerview = (RecyclerView) findViewById(R.id.recyclerview);
        setTranslucentNavigationBar(this);
    }

    @Override
    public void initData() {
        for(int i=0;i<10;i++){
            MainUi mainUi = new MainUi();
            mainUi.setName("test:" + i);
            list.add(mainUi);
        }

        mainItemAdapter = new MainItemAdapter(this,list);
        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        recyclerview.setAdapter(mainItemAdapter);
        recyclerview.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
    }

    public void setTranslucentNavigationBar(@NonNull Activity activity) {
//        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
//            return this;
//        } else {
//            mToolbar.setPadding(0, SystemView.getStatusBarHeight(activity) >> 1, 0, 0);
//        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            activity.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            activity.getWindow().setStatusBarColor(Color.TRANSPARENT);
        } else {
            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }

    }
}
