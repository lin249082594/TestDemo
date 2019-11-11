package com.linxf.lintestdemo.aac;

import android.content.Context;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.linxf.base.adapter.BaseListAdapter;
import com.linxf.lintestdemo.R;

import java.util.List;

/**
 * 类说明
 * Created by Linxf on 2019/5/28.
 */

public class MAdapter extends BaseListAdapter<String> {

    public MAdapter(Context context,List<String> list) {
        super(context);
        setList(list);
    }

    @Override
    public int getLayoutResource() {
        return R.layout.fragment_aac_item;
    }

    @Override
    public void onBindView(int position, View convertView, String s) {
        TextView textView = findView(convertView,R.id.textView);
        textView.setText(s);
    }
}
