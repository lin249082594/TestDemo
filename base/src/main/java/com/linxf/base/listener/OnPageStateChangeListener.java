package com.linxf.base.listener;

import android.view.View;

/**
 * 类说明
 * Created by Linxf on 2019/4/10.
 */

public interface OnPageStateChangeListener  {

    void emptyView(View emptyView);
    void errorView(View errorView);
    void loadingView(View errorView);

}
