package com.linxf.lintestdemo.room;

import android.arch.persistence.room.Room;

import com.linxf.lintestdemo.application.MyApplication;

/**
 * 类说明
 * Created by Linxf on 2019/5/29.
 */

public class AppDatabaseIns {
    public static AppDatabase appDatabase;

    public static AppDatabase getInstance() {
        if(appDatabase == null){
            appDatabase = Room.databaseBuilder(MyApplication.getBaseApplication(),
                    AppDatabase.class, "linxf").build();
        }
        return appDatabase;
    }
}
