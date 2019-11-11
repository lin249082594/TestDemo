package com.linxf.lintestdemo.fragment;

import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.linxf.base.BaseFragment;
import com.linxf.base.widget.recycleview.OnItemClickListener;
import com.linxf.lintestdemo.GridPicActivity;
import com.linxf.lintestdemo.R;
import com.linxf.lintestdemo.RecycleAddHeaderActivity;
import com.linxf.lintestdemo.RecycleStaggeredActivity;
import com.linxf.lintestdemo.RefreshActivity;
import com.linxf.lintestdemo.SlideMenuActivity;
import com.linxf.lintestdemo.TabFragmentActivity;
import com.linxf.lintestdemo.adapter.MainItemAdapter;
import com.linxf.lintestdemo.entity.MainUi;

import java.util.ArrayList;
import java.util.List;

/**
 * 界面fragment
 * Created by Linxf on 2019/3/27.
 */

public class ActivityFragment extends BaseFragment {
    private List<MainUi> list = new ArrayList<>();
    private RecyclerView recyclerView;
    private MainItemAdapter mainItemAdapter;

    @Override
    public int getLayoutId(LayoutInflater inflater,ViewGroup container) {
        return R.layout.fragment_main;
    }

    @Override
    public void initView() {
        recyclerView = (RecyclerView) findView(R.id.rv);
    }

    @Override
    public void initData() {
        MainUi item = new MainUi();
        item.setName("activity refreshPage");
        item.setPath(RefreshActivity.class);
        list.add(item);
        item = new MainUi();
        item.setName("activity viewpager fragment");
        item.setPath(TabFragmentActivity.class);
        list.add(item);
        item = new MainUi();
        item.setName("recycleView addHeader");
        item.setPath(RecycleAddHeaderActivity.class);
        list.add(item);

        item = new MainUi();
        item.setName("瀑布流 recycleView");
        item.setPath(RecycleStaggeredActivity.class);
        list.add(item);

        item = new MainUi();
        item.setName("抽屉界面");

        item.setPath(SlideMenuActivity.class);
        list.add(item);

        item = new MainUi();
        item.setName("九宫格");
        item.setPath(GridPicActivity.class);

        list.add(item);


        mainItemAdapter = new MainItemAdapter(getContext(),list);
        mainItemAdapter.setOnItemClickListener(new OnItemClickListener<MainUi>() {


            @Override
            public void onItemClick(ViewGroup parent, View view, MainUi mainUi, int position) {
                jump(mainUi.getPath(),null);
            }

            @Override
            public boolean onItemLongClick(ViewGroup parent, View view, MainUi mainUi, int position) {
                return false;
            }
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(mainItemAdapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL));
    }
}
