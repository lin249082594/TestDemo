package com.linxf.lintestdemo.room;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.linxf.lintestdemo.dao.User;

import java.util.List;

/**
 * 类说明
 * Created by Linxf on 2019/5/29.
 */
@Dao
public interface UserDao {
    @Insert
    public void insertUsers(RoomUser... users);

    @Query("SELECT * FROM user")
    public List<RoomUser> loadAllUsers();
}
