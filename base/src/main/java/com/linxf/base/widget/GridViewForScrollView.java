package com.linxf.base.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

/**
 * 自定义gridview 防治嵌套在scrollerview 显示不全
 * Created by Linxf on 2018/12/4.
 */

public class GridViewForScrollView extends GridView {
    public boolean isOnMeasure ;
    public GridViewForScrollView(Context context) {
        super(context);
    }

    public GridViewForScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public GridViewForScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * 重写onMeasure方法，重新计算高度，达到使GridView适应ScrollView的效果
     *
     * @param widthMeasureSpec  宽度测量规则
     * @param heightMeasureSpec 高度测量规则
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        isOnMeasure = true;
        //Integer.MAX_VALUE:表示int类型能够表示的最大值，值为2的31次方-1
        //>>2:右移N位相当于除以2的N的幂
        //MeasureSpec.AT_MOST：子布局可以根据自己的大小选择任意大小的模式
        int newHeightMeasureSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, newHeightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        isOnMeasure = false;
        super.onLayout(changed, l, t, r, b);
    }
}

