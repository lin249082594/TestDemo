package com.linxf.base.http;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * http请求累封装
 * Created by Linxf on 2018/10/9.
 */
public class MyOkhttpClient extends OkHttpClient {
    @Override
    public Call newCall(Request request) {
        RequestBody requestBody = request.body();

        System.out.println("bd:" + requestBody.toString());
        return super.newCall(request);
    }
}
