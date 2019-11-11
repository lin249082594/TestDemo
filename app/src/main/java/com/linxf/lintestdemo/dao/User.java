package com.linxf.lintestdemo.dao;

import com.alibaba.fastjson.JSON;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Unique;

/**
 * 类说明
 * Created by Linxf on 2019/5/27.
 */
@Entity
public class User {
    @Id(autoincrement = true)
    private Long id;
//    @Unique
    private String name;
    private Integer age;






    @Generated(hash = 1499888241)
    public User(Long id, String name, Integer age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    @Generated(hash = 586692638)
    public User() {
    }






    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
