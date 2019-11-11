package com.linxf.lintestdemo.adapter;

import android.content.Context;
import android.widget.TextView;

import com.linxf.base.widget.recycleview.CommonAdapter;
import com.linxf.base.widget.recycleview.ViewHolder;
import com.linxf.lintestdemo.R;
import com.linxf.lintestdemo.entity.MainUi;

import java.util.List;

/**
 * 类说明
 * Created by Linxf on 2019/4/4.
 */

public class MainItemAdapter extends CommonAdapter<MainUi> {
    public MainItemAdapter(Context context, List<MainUi> datas) {
        super(context, R.layout.layout_ui_item, datas);
    }

    @Override
    public void convert(int position, ViewHolder holder, MainUi mainUi) {
        holder.setText(R.id.textView,mainUi.getName());

    }
}
