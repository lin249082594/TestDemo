package com.linxf.lintestdemo.entity;

import com.alibaba.fastjson.JSON;

/**
 * 类说明
 * Created by Linxf on 2019/4/3.
 */

public class Goods {
    private String name;
    private String logo;
    private int viewType;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public int getViewType() {
        return viewType;
    }

    public void setViewType(int viewType) {
        this.viewType = viewType;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(toString());
    }
}
