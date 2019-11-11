package com.linxf.lintestdemo;

import android.util.Log;

import com.google.gson.Gson;
import com.linxf.base.BaseActivity;
import com.linxf.lintestdemo.api.Api;
import com.linxf.lintestdemo.entity.Translation;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 类说明
 * Created by Linxf on 2019/5/27.
 */

public class RetrofitActivity extends BaseActivity {
    public  static final String TAG = "122112";

    @Override
    public int getLayoutId() {
        return R.layout.activity_retrofit;
    }

    @Override
    public void initView() {
/**
 * 创建Retrofit实例时需要通过Retrofit.Builder,并调用baseUrl方法设置URL。
 * Retrofit2的baseUlr 必须以 /（斜线）结束，不然会抛出一个IllegalArgumentException
 */
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://fy.iciba.com/") // 设置 网络请求 Url
                .addConverterFactory(GsonConverterFactory.create()) //设置使用Gson解析(记得加入依赖)
                .build();

/**
 * 创建网络请求接口的实例
 * 拿到代理对象
 */
        Api request = retrofit.create(Api.class);

// 封装请求
        Call<Translation> call = request.getCall();

// 发送网络请求(异步)
        call.enqueue(new Callback<Translation>() {
            @Override
            public void onResponse(Call<Translation> call, Response<Translation> response) {
                response.body().show();
            }

            @Override
            public void onFailure(Call<Translation> call, Throwable t) {
                Log.e(TAG, "failed");
            }
        });


        Retrofit retrofitOb = new Retrofit.Builder()
                .baseUrl("http://fy.iciba.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        // 创建网络请求接口的实例
        Api requestOb = retrofitOb.create(Api.class);




        // 封装请求
        Observable<Translation> observable = requestOb.getCallObser();

        // 发送网络请求,子线程请求，主线程更新
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Translation>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.e(TAG, "开始采用subscribe连接");
                    }

                    @Override
                    public void onNext(Translation translation) {
                        translation.show();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "请求失败");
                    }

                    @Override
                    public void onComplete() {
                        Log.e(TAG, "请求成功");
                    }
                });
    }

    @Override
    public void initData() {

    }
}
