package com.linxf.lintestdemo;

import android.databinding.DataBindingUtil;


import com.linxf.base.BaseActivity;
import com.linxf.base.application.BusAction;
import com.linxf.base.application.RxBus;
import com.linxf.base.utils.LogUtil;
import com.linxf.lintestdemo.aac.StUser;
import com.linxf.lintestdemo.databinding.ActivityRxjavaBinding;
import com.linxf.lintestdemo.datas.DatasUtils;
import com.linxf.lintestdemo.entity.Goods;
import com.linxf.lintestdemo.entity.Mitem;
import com.linxf.lintestdemo.entity.SubItem;
import com.linxf.lintestdemo.prsenter.RxPresenter;
import com.linxf.lintestdemo.uentity.RxEntity;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;

/**
 * rxjava测试 //TODO https://www.jianshu.com/p/cd3557b1a474
 * Created by Linxf on 2019/5/9.
 */

public class RxJavaActivity extends BaseActivity {
    private  Disposable mDisposable;
    private ActivityRxjavaBinding binding;
    private List<String> list;

    private List<Mitem> dataList;
    List<Mitem> list3 = new ArrayList<>();

    //二、Rxpermissions等类库的使用
    //基于RxJava的开源类库Rxpermissions、RxBinding以及RxBus在很多项目中已经非常常

    private RxEntity rxEntity;
    private RxPresenter presenter;
    @Override
    public int getLayoutId() {
        binding = DataBindingUtil.setContentView(this,R.layout.activity_rxjava);
        rxEntity = new RxEntity();
        binding.setRxEntity(rxEntity);
        presenter = new RxPresenter();
        binding.setPresenter(presenter);
        return 0;
    }

    @Override
    public void initView() {
        //同步
        Observable novel=Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                LogUtil.error("当前线程" + Thread.currentThread().getId());
                emitter.onNext("连载1");
                emitter.onNext("2");
                emitter.onNext("连载2");
                emitter.onNext("连载3");
                emitter.onComplete();
            }
        });

        Observer<String> reader=new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                mDisposable=d;
                LogUtil.error("绑定订阅");
            }

            @Override
            public void onNext(String value) {
                LogUtil.error("当前线程" + Thread.currentThread().getId());
                LogUtil.error("接受消息:" + value);
//                if ("2".equals(value)){
//                    mDisposable.dispose();
//                    return;
//                }

            }

            @Override
            public void onError(Throwable e) {
                LogUtil.error("收到异常:" + e.toString());
            }

            @Override
            public void onComplete() {
                LogUtil.error("完成");
                LogUtil.error("当前线程" + Thread.currentThread().getId());
            }
        };
        novel.subscribe(reader);


        //异步
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                LogUtil.error("当前线程" + Thread.currentThread().getId());
                emitter.onNext("连载1");
                emitter.onNext("2");
                emitter.onNext("连载2");
                emitter.onNext("连载3");
                emitter.onComplete();
            }
        }) .observeOn(AndroidSchedulers.mainThread())//回调在主线程
                .subscribeOn(Schedulers.io())//执行在io线程
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String value) {
                        LogUtil.error("接受消息:" + value);
                        LogUtil.error("当前线程" + Thread.currentThread().getId());
                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtil.error("收到异常:" + e.toString());
                    }

                    @Override
                    public void onComplete() {
                        LogUtil.error("完成");
                        LogUtil.error("当前线程" + Thread.currentThread().getId());
                    }
                });


        //TODO rx action用法

        LogUtil.error("reactList:用法");

        list = new ArrayList<>();
        list.add("t1");
        list.add("t2");
        list.add("t3");
        Observable.fromIterable(list).flatMap(new Function<String, ObservableSource<String>>() {
            @Override
            public ObservableSource<String> apply(String s) throws Exception {
                return Observable.just("test:" + s);
            }
        }).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Consumer<String>() {
            @Override
            public void accept(String o) throws Exception {
                LogUtil.error("接受消息:" + o);
            }
        });


        LogUtil.error("reactList 复杂用法:用法");

        dataList = DatasUtils.getItemList();
        Observable.fromIterable(dataList).flatMap(new Function<Mitem, ObservableSource<SubItem>>() {
            @Override
            public ObservableSource<SubItem> apply(Mitem mitem) throws Exception {
                return Observable.fromIterable(mitem.getSubList());
            }
        }).filter(new Predicate<SubItem>() {
            @Override
            public boolean test(SubItem subItem) throws Exception {
                //判断是不是偶数
                return !(subItem.getI() % 2 == 0);
            }
        }).map(new Function<SubItem, Goods>() {
            @Override
            public Goods apply(SubItem subItem) throws Exception {
                if(subItem.getI() < 10){
                    //故意报错 尝试throw
                    int m = 10 / 0;
                }
                //封装goods
                Goods goods = new Goods();
                goods.setName("name:" + subItem.getI());
                return goods;
            }
        }).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Consumer<Goods>() {
                    @Override
                    public void accept(Goods o) throws Exception {
                        LogUtil.error("接受消息:" + o.getName());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        LogUtil.error("接受异常:" + throwable.toString());
                    }
                });



        // list transform



        list3 = Observable.fromIterable(list).flatMap(new Function<String, ObservableSource<Mitem>>() {
            @Override
            public ObservableSource<Mitem> apply(String s) throws Exception {
                LogUtil.error("list3当前线程" + Thread.currentThread().getId());
                Mitem mitem = new Mitem();
                mitem.setName(s);
                return Observable.just(mitem);
            }
        }).toList().blockingGet();
        LogUtil.error("list3:" + list3.toString());


    }

    @Override
    public void initData() {


//        RxBus.get().post(BusAction.REFRESH_CAST,"rxbus 传递消息测试");


        rxEntity.setText1("防止重复点击 (2s间隔)");

    }


}
