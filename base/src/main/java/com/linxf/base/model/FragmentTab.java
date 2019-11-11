package com.linxf.base.model;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.alibaba.fastjson.JSON;

import java.io.Serializable;

/**
 * 类说明
 * Created by Linxf on 2018/12/27.
 */

public class FragmentTab implements Serializable {

    private int tabIndex;
    private Class<? extends Fragment> clazz;

    private String tabName;
    private String param;
    private int menuId;



    public int getTabIndex() {
        return tabIndex;
    }

    public void setTabIndex(int tabIndex) {

        this.tabIndex = tabIndex;
    }

    public Class<? extends Fragment> getClazz() {
        return clazz;
    }

    public void setClazz(Class<? extends Fragment> clazz) {
        this.clazz = clazz;
    }





    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }

    public String getTabName() {
        return tabName;
    }

    public void setTabName(String tabName) {
        this.tabName = tabName;
    }

    public int getMenuId() {
        return menuId;
    }

    public void setMenuId(int menuId) {
        this.menuId = menuId;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
