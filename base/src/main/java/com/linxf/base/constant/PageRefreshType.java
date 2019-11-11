package com.linxf.base.constant;

/**
 * 类说明
 * Created by Linxf on 2019/4/10.
 */

public enum PageRefreshType {
    ONLY_REFRESH(1), //只刷新
    ONLY_LOADMORE(2), //只下拉更多
    REFRESH_LOADMORE(3), //刷新 跟下拉都支持
    NONE(4); //刷新 跟下拉都不支持

    int value;
    PageRefreshType(int value){
        this.value = value;
    }

    public static  PageRefreshType getTypeBuValue(int value){
        for(PageRefreshType item:values()){
            if(item.getValue() == value){
                return item;
            }
        }
        return null;
    }

    public int getValue() {
        return value;
    }

}
