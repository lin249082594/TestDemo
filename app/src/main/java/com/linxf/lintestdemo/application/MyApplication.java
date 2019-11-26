package com.linxf.lintestdemo.application;


import android.database.sqlite.SQLiteDatabase;

import com.bumptech.glide.request.target.ViewTarget;
import com.linxf.base.BaseApplication;
import com.linxf.base.widget.StateLayout;
import com.linxf.lintestdemo.R;
import com.linxf.lintestdemo.greendao.gen.DaoMaster;
import com.linxf.lintestdemo.greendao.gen.DaoSession;
import com.linxf.lintestdemo.utils.GreenDaoUpgradeHelper;


import org.greenrobot.greendao.database.Database;

/**
 * 类说明
 * Created by Linxf on 2019/4/10.
 */

public class MyApplication extends BaseApplication {
    public static final boolean ENCRYPTED = true;

    private DaoSession daoSession;

    @Override
    public void onCreate() {
        super.onCreate();
        StateLayout.init(new StateLayout.Builder()
                .setEmptyRes(R.layout.statelayout_empty)
                .setErrorRes(R.layout.statelayout_error)
                .setLoadingRes(R.layout.statelayout_loading)
                .build()
        );

        //glide绑定
        ViewTarget.setTagId(R.id.glide_tag);

        GreenDaoUpgradeHelper helper = new GreenDaoUpgradeHelper(this, ENCRYPTED ? "users-db-encrypted" : "users-db",null);
        Database db =  helper.getWritableDb();
        daoSession = new DaoMaster(db).newSession();
    }

    public DaoSession getDaoSession() {
        return daoSession;
    }


}
