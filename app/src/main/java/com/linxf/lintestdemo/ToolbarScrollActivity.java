package com.linxf.lintestdemo;

import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.linxf.base.BaseActivity;
import com.linxf.lintestdemo.adapter.MainItemAdapter;
import com.linxf.lintestdemo.entity.MainUi;

import java.util.ArrayList;
import java.util.List;

/**
 * 类说明
 * Created by Linxf on 2019/4/28.
 */

public class ToolbarScrollActivity extends BaseActivity {
    private List<MainUi> list = new ArrayList<>();
    private MainItemAdapter mainItemAdapter;
    private RecyclerView recyclerview;
    @Override
    public int getLayoutId() {
        return R.layout.activity_toolbar_scroll;
    }

    @Override
    public void initView() {
        recyclerview = (RecyclerView) findViewById(R.id.recyclerview);
    }

    @Override
    public void initData() {
        for(int i=0;i<20;i++){
            MainUi mainUi = new MainUi();
            mainUi.setName("test:" + i);
            list.add(mainUi);
        }

        mainItemAdapter = new MainItemAdapter(this,list);
        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        recyclerview.setAdapter(mainItemAdapter);
        recyclerview.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
    }
}
