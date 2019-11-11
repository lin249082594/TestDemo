package com.linxf.lintestdemo;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.linxf.base.BaseActivity;
//import com.linxf.base.GlideApp;
import com.linxf.lintestdemo.adapter.ImageAdapter;
import com.linxf.lintestdemo.entity.Image;

import java.util.ArrayList;
import java.util.List;

/**
 * glide测试类
 * Created by Linxf on 2019/4/22.
 */

public class GlideTestActivity extends BaseActivity {
    private RecyclerView recyclerView;
    private TextView memorySizeTxV;
    private TextView clearBtn;
    private ImageAdapter imageAdapter;
    private List<Image> imageList = new ArrayList<>();
    @Override
    public int getLayoutId() {
        return R.layout.activity_glide_test;
    }

    @Override
    public void initView() {
        recyclerView = (RecyclerView) findViewById(R.id.recycleView);
        memorySizeTxV = (TextView) findViewById(R.id.memorySizeTxV);
        clearBtn = (TextView) findViewById(R.id.clearBtn);


        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        imageAdapter = new ImageAdapter(this,imageList);
        recyclerView.setAdapter(imageAdapter);
    }

    @Override
    public void initData() {

    }
}
