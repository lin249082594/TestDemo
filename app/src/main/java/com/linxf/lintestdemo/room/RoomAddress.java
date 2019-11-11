package com.linxf.lintestdemo.room;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

import com.alibaba.fastjson.JSON;

/**
 * 类说明
 * Created by Linxf on 2019/5/29.
 */
@Entity(tableName = "address")
public class RoomAddress {
    @PrimaryKey(autoGenerate = true)
    public int id;
    @ColumnInfo(name = "address")
    private String address;
    @ColumnInfo(name = "address_code")
    private String addressCode;
    @ColumnInfo(name = "user_id")
    private int  userId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddressCode() {
        return addressCode;
    }

    public void setAddressCode(String addressCode) {
        this.addressCode = addressCode;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
