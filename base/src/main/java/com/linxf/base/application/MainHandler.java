package com.linxf.base.application;

import android.os.Handler;
import android.os.Looper;

/**
 * 类说明
 * Created by Linxf on 2019/1/21.
 */

public class MainHandler extends Handler {
    private static volatile MainHandler instance;

    public static MainHandler getInstance() {
        if (null == instance) {
            synchronized (MainHandler.class) {
                if (null == instance) {
                    instance = new MainHandler();
                }
            }
        }
        return instance;
    }
    private MainHandler() {
        super(Looper.getMainLooper());
    }


}

