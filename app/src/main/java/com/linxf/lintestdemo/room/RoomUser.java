package com.linxf.lintestdemo.room;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import com.alibaba.fastjson.JSON;

/**
 * 类说明
 * Created by Linxf on 2019/5/29.
 */

@Entity(tableName = "user")
public class RoomUser {
    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "user_name")
    public String userName;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }



    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
