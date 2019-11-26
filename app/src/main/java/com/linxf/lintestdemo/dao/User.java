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
    private String content;



    private Integer grade;






    @Generated(hash = 452524530)
    public User(Long id, String name, String content, Integer grade) {
        this.id = id;
        this.name = name;
        this.content = content;
        this.grade = grade;
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

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }


}
