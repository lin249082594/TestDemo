package com.linxf.lintestdemo.entity;

/**
 * 类说明
 * Created by Linxf on 2019/4/4.
 */

public class MainUi {
    private String name;
    private Class<?> path;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Class<?> getPath() {
        return path;
    }

    public void setPath(Class<?> path) {
        this.path = path;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
