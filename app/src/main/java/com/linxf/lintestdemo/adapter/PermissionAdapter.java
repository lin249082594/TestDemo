package com.linxf.lintestdemo.adapter;

import android.content.Context;

import com.linxf.base.widget.recycleview.CommonAdapter;
import com.linxf.base.widget.recycleview.ViewHolder;
import com.linxf.lintestdemo.R;
import com.linxf.lintestdemo.entity.PermissionModel;

import java.util.List;

/**
 * 类说明
 * Created by Linxf on 2019/4/12.
 */

public class PermissionAdapter extends CommonAdapter<PermissionModel> {
    public PermissionAdapter(Context context, List<PermissionModel> datas) {
        super(context, R.layout.layout_ui_item, datas);
    }

    @Override
    public void convert(int position, ViewHolder holder, PermissionModel data) {
        holder.setText(R.id.textView,data.getName());

    }
}