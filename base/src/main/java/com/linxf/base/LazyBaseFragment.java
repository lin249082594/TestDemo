package com.linxf.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.linxf.base.controller.BaseController;


/**
 * 懒加载 (在viewpager 中使用)
 * Created by Linxf on 2019/3/15.
 */

public abstract class LazyBaseFragment<T extends BaseController> extends BaseFragment<T> {





    private boolean mHasLoadedOnce = false;
    private boolean isPrepared = false;

    /** Fragment当前状态是否可见 */
    protected boolean isVisible;
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);

        if (getUserVisibleHint()) {
            isVisible = true;
            onVisible();
        } else {
            isVisible = false;
            onInvisible();
        }


    }

    protected void onVisible() {
        lazyLoad();
    }

    protected void onInvisible() {

    }

    protected void lazyLoad() {
        if (mHasLoadedOnce || !isPrepared || !isVisible)
            return;
        mHasLoadedOnce = true;
        initLazyData();
    }



    @Override
    public void initData() {
        isPrepared = true;
        lazyLoad();
    }
    public abstract void initLazyData();
}
