package com.linxf.lintestdemo.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.linxf.base.adapter.BaseListAdapter;
import com.linxf.lintestdemo.R;
import com.linxf.lintestdemo.entity.Goods;

import java.util.List;

/**
 * 类说明
 * Created by Linxf on 2019/5/8.
 */

public class TestGoodsFooterAdapter  extends BaseListAdapter<Goods> {


    public TestGoodsFooterAdapter(Context context, List<Goods> list) {
        super(context);
        setList(list);
    }

    @Override
    public int getLayoutResource() {
        return R.layout.item_footer_goods;
    }

    @Override
    public void onBindView(int position, View convertView, Goods goods) {
        ImageView imageView = findView(convertView,R.id.mImV);
        TextView textView = findView(convertView,R.id.mTmV);


        Glide.with(getContext()).load(goods.getLogo()).into(imageView);
        textView.setText(goods.getName());
    }
}
