package com.linxf.lintestdemo.entity;

import com.alibaba.fastjson.JSON;

import java.util.List;

/**
 * 类说明
 * Created by Linxf on 2019/5/9.
 */

public class Mitem {
    private String name;
    private List<SubItem> subList;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<SubItem> getSubList() {
        return subList;
    }

    public void setSubList(List<SubItem> subList) {
        this.subList = subList;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
