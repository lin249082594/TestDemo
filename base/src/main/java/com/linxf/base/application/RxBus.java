package com.linxf.base.application;




import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.processors.FlowableProcessor;
import io.reactivex.processors.PublishProcessor;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;

/**
 * 类说明
 * Created by Linxf on 2019/5/10.
 */

public class RxBus {
//    private volatile static RxBus mDefaultInstance;
//    private final Subject<Object> mBus;
//
//    private RxBus() {
//        mBus = PublishSubject.create().toSerialized();
//    }
//
//    public static RxBus getInstance() {
//        if (mDefaultInstance == null) {
//            synchronized (RxBus.class) {
//                if (mDefaultInstance == null) {
//                    mDefaultInstance = new RxBus();
//                }
//            }
//        }
//        return mDefaultInstance;
//    }
//
//    /**
//     * 发送事件
//     */
//    public void post(Object event) {
//        mBus.onNext(event);
//    }
//
//    /**
//     * 根据传递的 eventType 类型返回特定类型(eventType)的 被观察者
//     */
//    public <T> Observable<T> toObservable(final Class<T> eventType) {
//        return mBus.ofType(eventType);
//    }
//
//    /**
//     * 判断是否有订阅者
//     */
//    public boolean hasObservers() {
//        return mBus.hasObservers();
//    }
//
//    public void reset() {
//        mDefaultInstance = null;
//    }


//    private static Bus sBus;
//    public static synchronized Bus get() {
//        if (sBus == null) {
//            sBus = new Bus();
//        }
//        return sBus;
//    }


//    //背压 防止阻塞
//    private final FlowableProcessor<Object> mBus;
//
//    private RxBus() {
//        // toSerialized method made bus thread safe
//        mBus = PublishProcessor.create().toSerialized();
//
//    }
//
//
//
//    public static RxBus get() {
//        return Holder.BUS;
//    }
//
//    public void post(Object obj) {
//        mBus.onNext(obj);
//    }
//
//    public <T> Flowable<T> toFlowable(Class<T> tClass) {
//        return mBus.ofType(tClass);
//    }
//
//    public Flowable<Object> toFlowable() {
//        return mBus;
//    }
//
//    public boolean hasSubscribers() {
//        return mBus.hasSubscribers();
//    }
//
//    private static class Holder {
//        private static final RxBus BUS = new RxBus();
//    }



    //无背压
//    private final Subject<Object> mBus;
//
//    private RxBus() {
//        // toSerialized method made bus thread safe
//        mBus = PublishSubject.create().toSerialized();
//    }

//    public static RxBus get() {
//        return Holder.BUS;
//    }
//
//    public void post(Object obj) {
//        mBus.onNext(obj);
//    }
//
//    public <T> Observable<T> toObservable(Class<T> tClass) {
//        return mBus.ofType(tClass);
//    }
//
//    public Observable<Object> toObservable() {
//        return mBus;
//    }
//
//    public boolean hasObservers() {
//        return mBus.hasObservers();
//    }
//
//    private static class Holder {
//        private static final RxBus BUS = new RxBus();
//    }

}
