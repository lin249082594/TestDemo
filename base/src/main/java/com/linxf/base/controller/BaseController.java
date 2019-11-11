package com.linxf.base.controller;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Message;

import com.linxf.base.listener.OnPageStateChangeListener;
import com.linxf.base.widget.StateLayout;

/**
 * 类说明
 * Created by Linxf on 2019/3/27.
 */

public abstract class BaseController {
    protected Context mContext;
    //用于页面切换
    private StateLayout stateLayout;
    //页面切换回调
    private OnPageStateChangeListener onPageStateChangeListener;

    public BaseController(Context context) {
        this.mContext = context;
    }

    public void setStateLayout(StateLayout stateLayout,OnPageStateChangeListener onPageStateChangeListener){
        this.stateLayout = stateLayout;
        this.onPageStateChangeListener = onPageStateChangeListener;
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(stateLayout == null || onPageStateChangeListener == null)
                return;

            int i = msg.what % 4;
            switch (i) {
                case StateLayout.LOADING:
                    onPageStateChangeListener.loadingView(stateLayout.showLoading());
                    break;
                case StateLayout.EMPTY:
                    onPageStateChangeListener.emptyView( stateLayout.showEmpty());
                    break;
                case StateLayout.CONTENT:
                    stateLayout.showContent();
                    break;
                case StateLayout.ERROR:

                    onPageStateChangeListener.emptyView( stateLayout.showError());
                    break;

            }

        }
    };

    public void showLoading(int delay){

        handler.sendEmptyMessageDelayed( StateLayout.LOADING,delay);
    }

    public void showContent(int delay){

        handler.sendEmptyMessageDelayed( StateLayout.CONTENT,delay);
    }

    public void showEmpty(int delay){

        handler.sendEmptyMessageDelayed( StateLayout.EMPTY,delay);
    }
    public void showError(int delay){

        handler.sendEmptyMessageDelayed( StateLayout.EMPTY,delay);
    }

    public Context getmContext() {
        return mContext;
    }


}
