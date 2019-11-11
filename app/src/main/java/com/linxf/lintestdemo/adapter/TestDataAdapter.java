package com.linxf.lintestdemo.adapter;

import android.content.Context;

import com.linxf.base.widget.recycleview.CommonAdapter;
import com.linxf.base.widget.recycleview.ViewHolder;
import com.linxf.lintestdemo.R;
import com.linxf.lintestdemo.entity.TestData;

import java.util.List;

/**
 * 类说明
 * Created by Linxf on 2019/4/10.
 */

public class TestDataAdapter extends CommonAdapter<TestData> {
    public TestDataAdapter(Context context, List<TestData> datas) {
        super(context, R.layout.layout_ui_item, datas);
    }

    @Override
    public void convert(int position, ViewHolder holder, TestData testData) {
        holder.setText(R.id.textView,testData.getName());
    }
}
