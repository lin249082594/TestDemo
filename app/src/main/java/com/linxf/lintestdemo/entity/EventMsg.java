package com.linxf.lintestdemo.entity;

import com.alibaba.fastjson.JSON;

/**
 * 类说明
 * Created by Linxf on 2019/5/10.
 */

public class EventMsg {
    private String msg;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
