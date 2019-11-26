package com.linxf.lintestdemo;

import android.view.View;
import android.widget.TextView;

import com.linxf.base.BaseActivity;
import com.linxf.lintestdemo.application.MyApplication;

import com.linxf.lintestdemo.dao.User;
import com.linxf.lintestdemo.greendao.gen.DaoSession;
import com.linxf.lintestdemo.greendao.gen.UserDao;


import org.greenrobot.greendao.query.Query;

import java.util.List;

/**
 * 数据库操作
 * Created by Linxf on 2019/5/27.
 */

public class GreenDaoActivity extends BaseActivity {
    private Query<User> userQuery;

    UserDao userDao;

    private TextView resultTxV;
    @Override
    public int getLayoutId() {
        return R.layout.activity_greendao;
    }

    @Override
    public void initView() {
        resultTxV = findViewById(R.id.resultTxV);

        DaoSession daoSession =((MyApplication) getApplication()).getDaoSession();
        userDao = daoSession.getUserDao();
        userQuery = userDao.queryBuilder().orderAsc(UserDao.Properties.Id).build();

    }

    @Override
    public void initData() {
       queryList();
    }

    //查询全部的数据
    private List<User> queryList(){
        List<User> users = userQuery.list();
        resultTxV.setText(users.toString());
        return users;
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()){
            case R.id.insertBtn:
                User user = new User(null,"lin3","3333",90);
                userDao.insert(user);



                queryList();
                break;
        }
    }
}
