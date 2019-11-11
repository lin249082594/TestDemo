package com.linxf.lintestdemo;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.view.ViewGroup;
import com.linxf.base.BaseActivity;
import com.linxf.base.widget.recycleview.OnItemClickListener;
import com.linxf.lintestdemo.adapter.MainItemAdapter;
import com.linxf.lintestdemo.databinding.FragmentPermissionBinding;
import com.linxf.lintestdemo.entity.MainUi;

import java.util.ArrayList;
import java.util.List;

/**
 * toolbar
 * Created by Linxf on 2019/4/28.
 */

public class ToolbarActivity extends BaseActivity {

    private List<MainUi> list = new ArrayList<>();
    private MainItemAdapter mainItemAdapter;
    private FragmentPermissionBinding binding;
    @Override
    public int getLayoutId() {
        binding = DataBindingUtil.setContentView(this,R.layout.fragment_permission);
        return 0;
    }

    @Override
    public void initView() {
        MainUi item = new MainUi();
        item.setName("普通toolbar");
        item.setPath(ToolbarNormalActivity.class);
        list.add(item);
        item = new MainUi();
        item.setName("可折叠");
        item.setPath(ToolbarSlideActivity.class);
        list.add(item);
        item = new MainUi();
        item.setName("可滑动");
        item.setPath(ToolbarScrollActivity.class);
        list.add(item);

        mainItemAdapter = new MainItemAdapter(this,list);
        binding.recycleView.setLayoutManager(new LinearLayoutManager(this));
        binding.recycleView.setAdapter(mainItemAdapter);
        binding.recycleView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));

        mainItemAdapter.setOnItemClickListener(new OnItemClickListener<MainUi>(){
            @Override
            public void onItemClick(ViewGroup parent, View view, MainUi mainUi, int position) {
                jump(mainUi.getPath(),null);
            }

            @Override
            public boolean onItemLongClick(ViewGroup parent, View view, MainUi mainUi, int position) {
                return false;
            }
        });
    }

    @Override
    public void initData() {

    }
}
