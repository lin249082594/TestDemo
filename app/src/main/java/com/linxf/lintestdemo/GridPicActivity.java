package com.linxf.lintestdemo;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.linxf.base.BaseActivity;
import com.linxf.base.controller.BaseController;
import com.linxf.lintestdemo.adapter.PicAdapter;
import com.linxf.lintestdemo.databinding.ActivityGridpicBinding;
import com.linxf.lintestdemo.datas.DatasUtils;
import com.linxf.lintestdemo.entity.PicItem;

import java.util.ArrayList;
import java.util.List;

/**
 * 九宫格图片布局
 * Created by Linxf on 2019/8/8.
 */

public class GridPicActivity extends BaseActivity {
    private ActivityGridpicBinding binding;
    private PicAdapter adapter;
    private List<PicItem> list = new ArrayList<>();


    @Override
    public int getLayoutId() {

        binding = DataBindingUtil.setContentView(this,R.layout.activity_gridpic);
        return 0;
    }

    @Override
    public void initView() {
        list.addAll(DatasUtils.getPicList());
        adapter = new PicAdapter(this,R.layout.item_pic_1,list);

        binding.recycleView.setLayoutManager( new LinearLayoutManager(this));
        binding.recycleView.setAdapter(adapter);
        binding.recycleView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
    }

    @Override
    public void initData() {

    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()){
            case R.id.text1:
                adapter = new PicAdapter(this,R.layout.item_pic_1,list);

                binding.recycleView.setLayoutManager( new LinearLayoutManager(this));
                binding.recycleView.setAdapter(adapter);
                binding.recycleView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
                break;
            case R.id.text2:
                adapter = new PicAdapter(this,R.layout.item_pic_2,list);

                binding.recycleView.setLayoutManager( new LinearLayoutManager(this));
                binding.recycleView.setAdapter(adapter);
                binding.recycleView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
                break;
        }
    }
}
