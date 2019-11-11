package com.linxf.lintestdemo.api;

import com.linxf.lintestdemo.entity.Translation;


import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;



/**
 * 类说明
 * Created by Linxf on 2019/5/27.
 */

public interface Api {
    // 注解里传入网络请求的部分URL地址
    @GET("ajax.php?a=fy&f=auto&t=auto&w=hello")
    // getCall()是接受网络请求数据的方法
    Call<Translation> getCall();

    @GET("ajax.php?a=fy&f=auto&t=auto&w=hello")
    Observable<Translation> getCallObser();

}
