//package com.linxf.base;
//
//import com.scwang.smartrefresh.layout.api.RefreshHeader;
//import com.scwang.smartrefresh.layout.api.RefreshLayout;
//import com.scwang.smartrefresh.layout.header.ClassicsHeader;
//import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
//import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
//
///**
// * 类说明
// * Created by Linxf on 2019/4/4.
// */
//
//public abstract class BaseRefreshActivity extends BaseActivity {
//    private RefreshLayout refreshLayout;
//    private int pageNo = 1;
//    private int pageSize= 10;
//    private int pageCount = 0;
//    private boolean isMore = false;
//
//    public boolean enableRefresh = true;
//    public boolean enableLoadMore = true;
//    public RefreshHeader refreshHeader;
//
//    @Override
//    public void initData() {
//        //TODO 如果要更改模式配置 在初始化前修改值
//        if(refreshHeader == null)
//            refreshLayout.setRefreshHeader(new ClassicsHeader(this));
//        else
//            refreshLayout.setRefreshHeader(refreshHeader);
//
//        refreshLayout.setEnableRefresh(enableRefresh);
//        refreshLayout.setEnableLoadMore(enableLoadMore);
//        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
//            @Override
//            public void onRefresh(RefreshLayout refreshlayout) {
//                finishLoadAnim(1);
//                pageNo = 1;
//                loadData();
//            }
//        });
//        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
//            @Override
//            public void onLoadMore(RefreshLayout refreshlayout) {
//                if(isMore){
//                    pageNo ++ ;
//                    loadData();
//                } else {
//                    finishLoadAnim(2);
//                    noMoreView();
//                }
//
//            }
//        });
//
//        loadData();
//    }
//
//
//
//
//    public void finishLoadAnim(int type){
//        if(type == 1){
//            refreshLayout.finishRefresh(500);
//        } else {
//            refreshLayout.finishLoadMore(500);
//        }
//    }
//
//    /**
//     * 没有更多数据了
//     */
//    protected abstract void noMoreView();
//
//    /**
//     * 空数据
//     */
//    protected abstract void emptyView();
//
//    /**
//     * 加载数据
//     */
//    protected abstract void loadData();
//
//}
