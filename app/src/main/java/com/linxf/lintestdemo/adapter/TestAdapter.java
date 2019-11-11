package com.linxf.lintestdemo.adapter;

import android.content.Context;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.linxf.base.utils.LogUtil;
import com.linxf.base.widget.recycleview.CommonAdapter;
import com.linxf.base.widget.recycleview.ViewHolder;
import com.linxf.lintestdemo.R;
import com.linxf.lintestdemo.entity.Goods;

import java.util.List;

/**
 * 类说明
 * Created by Linxf on 2019/4/3.
 */

public class TestAdapter extends CommonAdapter<Goods> {
    private int width;
    private int height;
    public TestAdapter(Context context, int layoutId, List<Goods> datas, int width, int height) {
        super(context, layoutId, datas);
        this.width = width;
        this.height = height;
    }

    @Override
    public void convert(int position,ViewHolder holder, Goods goods) {

        ImageView imageView = holder.findView(R.id.imageView);
        TextView textView = holder.findView(R.id.nameTxV);
        LinearLayout itemLay = holder.findView(R.id.itemLay);
//        itemLay.setLayoutParams(new LinearLayout.LayoutParams(width,height));
        imageView.setLayoutParams(new LinearLayout.LayoutParams(width,width));


        Glide.with(getmContext()).load(goods.getLogo()).into(imageView);
        textView.setText(goods.getName());
    }
}
