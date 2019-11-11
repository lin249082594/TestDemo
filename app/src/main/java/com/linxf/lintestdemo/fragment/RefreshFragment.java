package com.linxf.lintestdemo.fragment;

import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.linxf.base.BaseFragment;
import com.linxf.base.constant.PageRefreshType;
import com.linxf.base.controller.PageRefreshController;
import com.linxf.base.listener.OnPageRefreshListener;
import com.linxf.base.listener.OnPageStateChangeListener;
import com.linxf.base.utils.LogUtil;
import com.linxf.base.widget.StateLayout;
import com.linxf.lintestdemo.R;
import com.linxf.lintestdemo.adapter.TestDataAdapter;
import com.linxf.lintestdemo.datas.DatasUtils;
import com.linxf.lintestdemo.entity.TestData;
import com.scwang.smartrefresh.layout.api.RefreshLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * 类说明
 * Created by Linxf on 2019/4/11.
 */

public class RefreshFragment extends BaseFragment<PageRefreshController> implements OnPageRefreshListener,OnPageStateChangeListener {
    private RefreshLayout refreshLayout;
    private RecyclerView recyclerView;

    private List<TestData> list = new ArrayList<>();
    private TestDataAdapter adapter;
    private StateLayout stateLayout;
    @Override
    public int getLayoutId(LayoutInflater inflater, ViewGroup container) {
        initContorller(PageRefreshController.class);

        stateLayout = new StateLayout(getContext()).setContent(R.layout.activity_refresh);
        mRootView = stateLayout;

        return -1;
    }

    @Override
    public void initView() {
        refreshLayout = (RefreshLayout) stateLayout.getContentView().findViewById(R.id.refreshLayout);
        recyclerView = (RecyclerView) stateLayout.getContentView().findViewById(R.id.listView);

        getController().setStateLayout(stateLayout,this);
        //初始化loading界面
        getController().showLoading(0);

        //初始化refreshLayout
        getController().initRefreshLayout(getContext(),refreshLayout,null, PageRefreshType.REFRESH_LOADMORE.getValue(),this);

        adapter =  new TestDataAdapter(getContext(),list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL));

        //设置最大页数
        getController().setPageCount(5);

        //测试
//        loadData();
        getController().showEmpty(1000);

    }

    @Override
    public void initData() {

    }

    @Override
    public void loadData() {
        LogUtil.error("加载数据");

        //TODO 注意list添加数据 最要跟adapter刷新 都在主线程  listview有线程安全问题
        if(getController().getPageNo() == 1){
            list.clear();
        }
        list.addAll(DatasUtils.getTestData(getController().getPageNo(),getController().getPageSize(),getController().getPageCount()));
        getController().finishLoadAnim(-1);
        adapter.notifyDataSetChanged();
        getController().showContent(0);
    }

    @Override
    public void noMoreData() {
        showToast("没有更多数据了");
        getController().finishLoadAnim(-1);
    }



    @Override
    public void emptyView(View emptyView) {
        TextView retryBtn = emptyView.findViewById(R.id.retryBtn);
        retryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadData();
            }
        });
    }

    @Override
    public void errorView(View errorView) {
        TextView retryBtn = errorView.findViewById(R.id.retryBtn);
        retryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadData();
            }
        });
    }

    @Override
    public void loadingView(View errorView) {

    }
}

