package com.linxf.lintestdemo.room;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.linxf.base.adapter.BaseListAdapter;
import com.linxf.lintestdemo.R;

import java.util.List;

/**
 * 类说明
 * Created by Linxf on 2019/5/29.
 */

public class RoomAdapter extends BaseListAdapter<RoomUser> {

    public RoomAdapter(Context context, List<RoomUser> list) {
        super(context);
        setList(list);
    }

    @Override
    public int getLayoutResource() {
        return R.layout.fragment_aac_item;
    }

    @Override
    public void onBindView(int position, View convertView, RoomUser s) {
        TextView textView = findView(convertView,R.id.textView);
        textView.setText("用户id:" + s.getId() + "  用户名字:" + s.getUserName());
    }
}
