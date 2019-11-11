package com.linxf.lintestdemo.room;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

/**
 * 类说明
 * Created by Linxf on 2019/5/29.
 */
@Dao
public interface AddressDao {
    @Insert
    public void insertAddress(RoomAddress... addresses);
    @Query("SELECT * FROM address where user_id = :userId")
    public List<RoomAddress> getAddressByUserId(int userId);

}
