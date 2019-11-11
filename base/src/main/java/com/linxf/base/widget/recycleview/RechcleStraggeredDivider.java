package com.linxf.base.widget.recycleview;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

/**
 * 类说明
 * Created by Linxf on 2019/5/8.
 */

public class RechcleStraggeredDivider extends RecyclerView.ItemDecoration {
    private int space;
    private int color;
    private int headCount;
    private Paint mPaint;

    /**
     * 默认的，垂直方向 横纵1px 的分割线 颜色透明
     */
    public RechcleStraggeredDivider() {
        this(1);
    }

    /**
     * 自定义宽度的透明分割线
     *
     * @param space 指定宽度
     */
    public RechcleStraggeredDivider(int space) {
        this(space, Color.TRANSPARENT);
    }

    /**
     * 自定义宽度，并指定颜色的分割线
     *
     * @param space 指定宽度
     * @param color 指定颜色
     */

    public RechcleStraggeredDivider(int space, int color) {
        this.space = space;
        this.color = color;
        initPaint();
    }
    public RechcleStraggeredDivider(int space, int color, int headCount) {
        this.space = space;
        this.color = color;
        this.headCount = headCount;
        initPaint();
    }

    private void initPaint() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(color);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setStrokeWidth(space);
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        StaggeredGridLayoutManager manager = (StaggeredGridLayoutManager) parent.getLayoutManager();
        int childSize = parent.getChildCount();
        int span = manager.getSpanCount();
        //为了Item大小均匀，将设定分割线平均分给左右两边Item各一半
        int offset = space / 2;
//        for (int i = 0; i < childSize; i++) {
//
//        }
        //得到View的位置
        int childPosition = parent.getChildAdapterPosition(view);
        if(headCount > 0 && childPosition < headCount){
            return;
        }
        childPosition = childPosition - headCount;

        //第一排，顶部不画
        if (childPosition < span) {
            //最左边的，左边不画
            if (childPosition % span == 0) {
                outRect.set(space, 0, offset, 0);
                //最右边，右边不画
            } else if (childPosition % span == span - 1) {
                outRect.set(offset, 0, space, 0);
            } else {
                outRect.set(offset, 0, offset, 0);
            }
        } else {
            //上下的分割线，就从第二排开始，每个区域的顶部直接添加设定大小，不用再均分了
            if (childPosition % span == 0) {
                outRect.set(space, space, offset, 0);
            } else if (childPosition % span == span - 1) {
                outRect.set(offset, space,  space,0);
            } else {
                outRect.set(offset, space, offset, 0);
            }
        }

    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
        c.drawPaint(mPaint);
    }
}

