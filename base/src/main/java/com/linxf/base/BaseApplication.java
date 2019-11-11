package com.linxf.base;

import android.app.Activity;
import android.app.Application;
import android.support.v7.app.AppCompatActivity;
import android.util.SparseArray;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * 类说明
 * Created by Linxf on 2019/3/27.
 */

public class BaseApplication extends Application {
    private List<Activity> activityList = new LinkedList<Activity>();
    private static BaseApplication baseApplication;
    private SparseArray<Integer> animSparse;
    @Override
    public void onCreate() {
        super.onCreate();
        baseApplication = this;
    }

    /**
     * 获取系统上下文：用于ToastUtil类
     */
    public static BaseApplication  getBaseApplication() {
        return baseApplication;
    }

    // 添加Activity到容器中
    public void addActivity(Activity activity) {
        activityList.add(activity);
    }

    public void finish() {
        for (Activity activity : activityList) {
            activity.finish();
        }

    }

    // 遍历所有Activity并finish
    public void exit() {
        for (Activity activity : activityList) {
            activity.finish();
        }
        System.exit(0);
    }

    public void addAnim(String name,int type){

        if(animSparse == null){
            animSparse = new SparseArray<>();
        }
        animSparse.put(name.hashCode(),type);
    }

    public int getAnim(String name){

        if(animSparse == null){
            return  0;
        } else {
            if(animSparse.get(name.hashCode()) != null){
                return animSparse.get(name.hashCode()).intValue();
            } else {
                return 0;
            }

        }
    }
}
