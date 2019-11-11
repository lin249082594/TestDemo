package com.linxf.lintestdemo;

import com.linxf.base.BaseActivity;
import com.linxf.base.utils.LogUtil;
import com.linxf.lintestdemo.application.MyApplication;
import com.linxf.lintestdemo.dao.DaoSession;
import com.linxf.lintestdemo.dao.User;
import com.linxf.lintestdemo.dao.UserDao;

import org.greenrobot.greendao.query.Query;

import java.util.List;

/**
 * 数据库操作
 * Created by Linxf on 2019/5/27.
 */

public class GreenDaoActivity extends BaseActivity {
    private Query<User> userQuery;
    UserDao userDao;
    @Override
    public int getLayoutId() {
        return R.layout.activity_greendao;
    }

    @Override
    public void initView() {
        DaoSession daoSession =((MyApplication) getApplication()).getDaoSession();
        userDao = daoSession.getUserDao();
        userQuery = userDao.queryBuilder().orderAsc(UserDao.Properties.Id).build();
    }

    @Override
    public void initData() {
        User user = new User(null,"lin",10);
        userDao.insert(user);

        LogUtil.error("结果:" + queryList().toString());
    }

    //查询全部的数据
    private List<User> queryList(){
        List<User> users = userQuery.list();
        return users;
    }

}
