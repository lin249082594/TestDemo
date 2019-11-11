package com.linxf.lintestdemo.entity;

import com.alibaba.fastjson.JSON;

/**
 * 类说明
 * Created by Linxf on 2019/4/12.
 */

public class PermissionModel {
    private String name;
    private String[] permissions;
    private int permissionCode;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String[] getPermissions() {
        return permissions;
    }

    public void setPermissions(String[] permissions) {
        this.permissions = permissions;
    }

    public int getPermissionCode() {
        return permissionCode;
    }

    public void setPermissionCode(int permissionCode) {
        this.permissionCode = permissionCode;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }

}
