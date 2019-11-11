package com.linxf.base.utils;

import android.util.Log;

/**
 * 日志工具打印
 * Created by Linxf on 2018/10/9.
 */

public class LogUtil {
    public static boolean enableLog = true;

    /**
     * 打印error日志
     *
     * @param tag
     * @param msg
     */
    public static void error(String tag, String msg) {
        if(enableLog){
            Log.e(tag, msg);
        }
    }

    public static void error(String msg) {
        if(enableLog){
            Log.e("test", msg);
        }
    }

    /**
     * 打印error日志
     *
     * @param tag
     * @param msg
     * @param e
     */
    public static void error(String tag, String msg, Throwable e) {
        if(enableLog){
            Log.e(tag, msg, e);
        }
    }

    /**
     * 打印info日志
     *
     * @param tag
     * @param msg
     */
    public static void info(String tag, String msg) {
        if(enableLog){
            Log.i(tag, msg);
        }
    }
}
