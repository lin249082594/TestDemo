package com.linxf.lintestdemo.entity;

import android.util.Log;

import com.alibaba.fastjson.JSON;

import java.util.List;

/**
 * 类说明
 * Created by Linxf on 2019/5/27.
 */

public class Translation {

    private String TAG = "Translation";

    private int status;

    private content content;

    private static class content {
        private String ph_am;
        private String ph_am_mp3;
        private String ph_en;
        private String ph_tts_mp3;
        private List<String> word_mean;
    }

    //定义 输出返回数据 的方法
    public void show() {
        Log.e(TAG, status + "");

        Log.e(TAG, content.ph_am);
        Log.e(TAG, content.ph_am_mp3);
        Log.e(TAG, content.ph_en);
        Log.e(TAG, content.ph_tts_mp3);
        Log.e(TAG, content.word_mean + "");
    }

}
