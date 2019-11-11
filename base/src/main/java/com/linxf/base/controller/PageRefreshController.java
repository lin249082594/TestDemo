package com.linxf.base.controller;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.linxf.base.BaseFragment;
import com.linxf.base.constant.PageRefreshType;
import com.linxf.base.listener.OnPageRefreshListener;
import com.linxf.base.model.FragmentTab;
import com.linxf.base.utils.StringUtil;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

/**
 * 类说明
 * Created by Linxf on 2019/3/27.
 */

public class PageRefreshController extends BaseController {
    private RefreshLayout refreshLayout;
    private int pageNo = 1;
    private int pageSize= 10;
    private int pageCount = 0;
    private boolean isMore = true;

    public boolean enableRefresh = true;
    public boolean enableLoadMore = true;

    private OnPageRefreshListener onPageRefreshListener;

    public PageRefreshController(Activity context) {
        super(context);
    }

    /**
     *
     * @param context
     * @param refreshLayout
     * @param refreshHeader
     * @param loadType  1只刷新 2只加载更多   3都加载 4全都不加载
     * @param listener
     */
    public void initRefreshLayout(Context context,RefreshLayout refreshLayout,RefreshHeader refreshHeader,int loadType, OnPageRefreshListener listener){
        this.onPageRefreshListener = listener;
        this.refreshLayout = refreshLayout;
        if(refreshHeader == null)
            refreshLayout.setRefreshHeader(new ClassicsHeader(context));
        else
            refreshLayout.setRefreshHeader(refreshHeader);

        setLadEnable(loadType);


        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {

                pageNo = 1;
                if(onPageRefreshListener != null)
                    onPageRefreshListener.loadData();

            }
        });
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshlayout) {
                if(pageNo<=pageCount){
                    pageNo ++ ;
                    if(onPageRefreshListener != null)
                        onPageRefreshListener.loadData();
                } else {
                    if(onPageRefreshListener != null)
                        onPageRefreshListener.noMoreData();
                }

            }
        });
    }

    /**
     * 1结束刷新 2结束加载更多 -1全部刷新
     * @param type
     */
    public void finishLoadAnim(int type){
        if(type == 1){
            refreshLayout.finishRefresh(500);
        } else if(type == 2){
            refreshLayout.finishLoadMore(500);
        } else {
            refreshLayout.finishRefresh(500);
            refreshLayout.finishLoadMore(500);
        }
    }

    private void setLadEnable(int loadType){
        switch (PageRefreshType.getTypeBuValue(loadType)){
            case ONLY_REFRESH:
                refreshLayout.setEnableRefresh(true);
                refreshLayout.setEnableLoadMore(false);
                break;
            case ONLY_LOADMORE:
                refreshLayout.setEnableRefresh(false);
                refreshLayout.setEnableLoadMore(true);
                break;
            case REFRESH_LOADMORE:
                refreshLayout.setEnableRefresh(true);
                refreshLayout.setEnableLoadMore(true);
                break;
            case NONE:
                refreshLayout.setEnableRefresh(false);
                refreshLayout.setEnableLoadMore(false);
                break;
        }
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public boolean isMore() {
        return isMore;
    }

    public void setMore(boolean more) {
        isMore = more;
    }
}
