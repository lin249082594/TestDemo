package com.linxf.lintestdemo.entity;

import com.alibaba.fastjson.JSON;

import java.io.Serializable;

/**
 * 类说明
 * Created by Linxf on 2019/8/8.
 */

public class PicItem implements Serializable {
    private String pic;
    private String content;

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
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
