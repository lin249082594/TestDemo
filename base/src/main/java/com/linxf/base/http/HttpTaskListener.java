package com.linxf.base.http;

import com.alibaba.fastjson.JSONObject;

/**
 * 常量
 * Created by Linxf on 2018/10/9.
 */

public interface HttpTaskListener {

    public void onSucess(int id, JSONObject param, String result);

    public void onException(int id,JSONObject param, String error);
}
