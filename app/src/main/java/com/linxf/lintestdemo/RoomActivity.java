package com.linxf.lintestdemo;

import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import com.linxf.base.BaseActivity;
import com.linxf.base.utils.StringUtil;
import com.linxf.lintestdemo.aac.MAdapter;
import com.linxf.lintestdemo.room.AppDatabaseIns;
import com.linxf.lintestdemo.room.RoomAdapter;
import com.linxf.lintestdemo.room.RoomUser;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * 类说明
 * Created by Linxf on 2019/5/29.
 */
// 参考 https://www.jianshu.com/p/82ba6c700397
// 基于sqlite  主要难点 多用户 以及 数据库更新 如增加字段 减少字段等操作
public class RoomActivity extends BaseActivity {
    private EditText nameEdV;
    private ListView listView;
    private List<RoomUser> list = new ArrayList<>();
    private RoomAdapter adapter ;
    @Override
    public int getLayoutId() {
        return R.layout.activity_room;
    }

    @Override
    public void initView() {
        nameEdV = findViewById(R.id.nameEdV);
        listView = findViewById(R.id.listView);

        adapter = new RoomAdapter(this,list);
        listView.setAdapter(adapter);


    }

    @Override
    public void initData() {

    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        if(v.getId() == R.id.saveBtn){
            if(StringUtil.isNotEmpty(nameEdV.getText().toString())){

                RoomUser roomUser = new RoomUser();
                roomUser.setUserName(nameEdV.getText().toString());
                AppDatabaseIns.getInstance().userDao().insertUsers(roomUser);

                //更新
                list.clear();
                list.addAll(AppDatabaseIns.getInstance().userDao().loadAllUsers());
                adapter.notifyDataSetChanged();

            }


        }
    }
}
