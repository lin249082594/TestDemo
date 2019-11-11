package com.linxf.lintestdemo.entity;

import com.alibaba.fastjson.JSON;

/**
 * 类说明
 * Created by Linxf on 2019/4/10.
 */

public class TestData {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
