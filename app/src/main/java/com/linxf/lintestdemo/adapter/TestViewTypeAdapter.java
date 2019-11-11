package com.linxf.lintestdemo.adapter;

import android.content.Context;
import android.util.SparseArray;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.linxf.base.application.GlideApp;
import com.linxf.base.utils.ImageLoader;
import com.linxf.base.widget.recycleview.CommonViewTypeAdapter;
import com.linxf.base.widget.recycleview.ViewHolder;
import com.linxf.lintestdemo.R;
import com.linxf.lintestdemo.entity.Goods;

import java.util.List;

/**
 * 类说明
 * Created by Linxf on 2019/5/8.
 */

public class TestViewTypeAdapter extends CommonViewTypeAdapter<Goods> {
    private ImageLoader imageLoader;

    public TestViewTypeAdapter(Context context, SparseArray<Integer> layoutId, List<Goods> datas,ImageLoader imageLoader) {
        super(context, layoutId, datas);
        this.imageLoader = imageLoader;
    }

    @Override
    public void convert(int position, ViewHolder holder, Goods goods) {
        ImageView imageView = holder.findView(R.id.imageView);
        TextView textView = holder.findView(R.id.nameTxV);
        LinearLayout itemLay = holder.findView(R.id.itemLay);


        imageLoader.loadImage(goods.getLogo(),imageView);
        textView.setText(goods.getName());
    }

    @Override
    public int getItemViewType(int position) {
        if(mDatas != null){
            return mDatas.get(position).getViewType();
        } else {
            return super.getItemViewType(position);
        }

    }
}
