package com.linxf.lintestdemo.room;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

/**
 * 类说明
 * Created by Linxf on 2019/5/29.
 */
@Database(entities = {RoomUser.class,RoomAddress.class}, version = 1, exportSchema = false)
public abstract  class AppDatabase extends RoomDatabase{
    public abstract UserDao userDao();
    public abstract AddressDao addressDao();
}
