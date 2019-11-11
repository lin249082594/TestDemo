package com.linxf.lintestdemo.fragment;

import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.linxf.base.BaseFragment;
import com.linxf.base.widget.recycleview.OnItemClickListener;
import com.linxf.lintestdemo.BadgeViewActivity;
import com.linxf.lintestdemo.CustomRadioTxVActivity;
import com.linxf.lintestdemo.DatePickActivity;
import com.linxf.lintestdemo.DialogActivity;
import com.linxf.lintestdemo.EnumActivity;
import com.linxf.lintestdemo.GlideDemoActivity;
import com.linxf.lintestdemo.PermissionActivity;
import com.linxf.lintestdemo.R;
import com.linxf.lintestdemo.SpeedRecycleActivity;
import com.linxf.lintestdemo.ToolbarActivity;
import com.linxf.lintestdemo.XbannerDemoActivity;
import com.linxf.lintestdemo.adapter.MainItemAdapter;
import com.linxf.lintestdemo.entity.MainUi;

import java.util.ArrayList;
import java.util.List;

/**
 * 小控件fragment
 * Created by Linxf on 2019/3/27.
 */

public class WidgetFragment extends BaseFragment {
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
        item.setName("xbanner");
        item.setPath(XbannerDemoActivity.class);
        list.add(item);

        item = new MainUi();
        item.setName("seedRecycle");
        item.setPath(SpeedRecycleActivity.class);
        list.add(item);

        item = new MainUi();
        item.setName("小圆点");
        item.setPath(BadgeViewActivity.class);
        list.add(item);

        item = new MainUi();
        item.setName("申请权限");
        item.setPath(PermissionActivity.class);
        list.add(item);

        item = new MainUi();
        item.setName("dialog提示框");
        item.setPath(DialogActivity.class);
        list.add(item);

        item = new MainUi();
        item.setName("glide加载样式");
        item.setPath(GlideDemoActivity.class);
        list.add(item);

        item = new MainUi();
        item.setName("toolbar");
        item.setPath(ToolbarActivity.class);
        list.add(item);

        item = new MainUi();
        item.setName("refresh样式");
        list.add(item);


        item = new MainUi();
        item.setName("日历选择 控件 年月日 年月");
        item.setPath(DatePickActivity.class);
        list.add(item);

        item = new MainUi();
        item.setName("自定义radiobutton");
        item.setPath(CustomRadioTxVActivity.class);
        list.add(item);


        item = new MainUi();
        item.setName("enum代替类");
        item.setPath(EnumActivity.class);
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
