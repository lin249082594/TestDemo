package com.linxf.lintestdemo.entity;

import com.alibaba.fastjson.JSON;

/**
 * 类说明
 * Created by Linxf on 2019/4/23.
 */

public class Image {
    private String image;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
